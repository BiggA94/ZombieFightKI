/*
   Copyright (c) 2015 Alexander 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package de.uks.se1.ss15.dtritus.zombiefighter.KI.global;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.UserAssets;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.ConnectException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

public class Mediator implements PropertyChangeInterface {

	public static LinkedBlockingDeque<String> testSignal = null;

	/**
	 * 
	 * @param string
	 * @return False if there was no TestSignal and True if writing to
	 *         testsignal was successful
	 */
	public static boolean printTestSignal(String string) {
		if (Mediator.testSignal != null) {
			try {
				Mediator.testSignal.put(string);
				return true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private static boolean running = false;

	public static boolean isRunning() {
		return running;
	}

	private static void setRunning(boolean bool) {
		if (bool == true) {
			Mediator.printTestSignal("Mediator started");
			Mediator.getInstance().setState(ZFState.STARTED);
		} else {
			Mediator.printTestSignal("Mediator stopped");
			Mediator.getInstance().setState(ZFState.STOPPED);
		}
		running = bool;
	}

	private static boolean DEBUG_MODE = false;

	public static boolean printDebugln(Object object) {
		if (object == null) {
			return false;
		}
		return printDebugln(object.toString());
	}

	public static void setDebugMode(boolean dEBUG_MODE) {
		DEBUG_MODE = dEBUG_MODE;
	}

	public static boolean DebugModeEnabled() {
		return DEBUG_MODE;
	}

	public static boolean printDebugln(String string) {
		if (DebugModeEnabled()) {
			System.out.println(string);
			return true;
		}
		return false;
	}

	private static Mediator instance;

	public static Mediator getInstance() {
		if (instance == null) {
			instance = new Mediator();
		}
		return instance;
	}

	// ==========================================================================

	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	// ==========================================================================

	public void removeYou() {
		setZombieFighter(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * Mediator ----------------------------------- ZombieFighter
	 *                                 zombieFighter
	 * </pre>
	 */

	public static final String PROPERTY_ZOMBIEFIGHTER = "zombieFighter";

	private ZombieFighter zombieFighter = null;

	public ZombieFighter getZombieFighter() {
		return this.zombieFighter;
	}

	public boolean setZombieFighter(ZombieFighter value) {
		boolean changed = false;

		if (this.zombieFighter != value) {
			ZombieFighter oldValue = this.zombieFighter;

			this.zombieFighter = value;

			getPropertyChangeSupport().firePropertyChange(PROPERTY_ZOMBIEFIGHTER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Mediator withZombieFighter(ZombieFighter value) {
		setZombieFighter(value);
		return this;
	}

	public ZombieFighter createZombieFighter() {
		ZombieFighter value = new ZombieFighter();
		withZombieFighter(value);
		return value;
	}

	public ZombieFighter createZombieFighter(String serverURL, int serverPort) {
		ZombieFighter value = new ZombieFighter();
		withZombieFighter(value);
		return value;
	}

	public void init() {
		Mediator.instance = this;
	}

	public void connect() throws ConnectException {
		connect(Mediator.getServerAddress(), Mediator.getServerport());
	}

	public void connect(String serverURL, int serverPort) throws ConnectException {
		createZombieFighter(serverURL, serverPort);
		connect(getZombieFighter());
		getZombieFighter().connect(serverURL, serverPort);
	}

	public void connect(ZombieFighter zf) {
		setZombieFighter(zf);
		this.setState(ZFState.CONNECTED);
	}

	public boolean isConnected() {
		if (this.getZombieFighter() != null) {
			if (this.getZombieFighter().getServerMessageHandler() != null) {
				return this.getZombieFighter().getServerMessageHandler().isConnected();
			}
		}
		return false;
	}

	public Mediator() {
		instance = this;
		start();
	}

	public void start() {
		// Initialize the Listeners, that handle the state
		initializeStateListeners();

		Boolean connection = ServerMessageHandler.checkServer(getServerAddress(), getServerport());
		Mediator.printDebugln("Serverconnection: " + connection);
		if (!connection) {
			System.err.println("No Serverconnecion!");
		}
		Mediator.setRunning(true);
	}

	public void stop() throws Exception {
		this.interrupt();
		Mediator.setRunning(false);
	}

	/**
	 * Interrupts all Threads
	 * 
	 * @return true - if there was a ServerMessageHandler, false if there was no
	 *         ServerMessageHandler
	 */
	public boolean interrupt() {
		ZombieFighter zombieFighter = getZombieFighter();
		if (zombieFighter != null) {
			ServerMessageHandler serverMessageHandler = zombieFighter.getServerMessageHandler();
			if (serverMessageHandler != null) {
				serverMessageHandler.interrupt();
				return true;
			}
		}
		this.setState(ZFState.STOPPED);
		return false;
	}

	private static String workingDirectory;

	// Set the working directory for file operations
	public static void setFileFolder() {
		String osName = System.getProperty("os.name");
		// Windows
		if (osName.startsWith("Windows")) {
			String userDir = System.getProperty("user.home");
			String appDirectory = userDir + System.getProperty("file.separator") + "AppData"
					+ System.getProperty("file.separator") + "Local" + System.getProperty("file.separator")
					+ "zombiefight" + System.getProperty("file.separator");
			Mediator.setWorkingDirectory(appDirectory);

			// Create if not existing
			File path = new File(appDirectory);
			if (!path.exists()) {
				try {
					path.mkdir();
				} catch (SecurityException se) {
					// handle it
					System.err.println("Could not create app folder in " + path);
				}
			}
		}
		// Unix
		else if (osName.startsWith("Mac") || osName.startsWith("Linux")) {
			String userDir = System.getProperty("user.home");
			String appDirectory = userDir + System.getProperty("file.separator") + ".zombiefight"
					+ System.getProperty("file.separator");
			Mediator.setWorkingDirectory(appDirectory);

			// Create if not existing
			File path = new File(appDirectory);
			if (!path.exists()) {
				try {
					path.mkdir();
				} catch (SecurityException se) {
					// handle it
					System.err.println("Could not create app folder in " + path);
				}
			}
		}
	}

	public static String getWorkingDirectory() {
		if (workingDirectory == null) {
			setFileFolder();
		}
		return workingDirectory;
	}

	public static void setWorkingDirectory(String path) {
		workingDirectory = path;
	}

	private static final String ServerAddress = "se1.cs.uni-kassel.de";

	public static String getServerAddress() {
		return ServerAddress;
	}

	private static final int ServerPort = 5000;

	public static int getServerport() {
		return ServerPort;
	}

	public static String PROPERTY_STATE = "STATE";

	private ZFState state = ZFState.STOPPED;

	public ZFState getState() {
		return state;
	}

	public void setState(ZFState state) {
		if (state != this.state) {
			ZFState oldValue = this.state;
			this.state = state;
			this.getPropertyChangeSupport().firePropertyChange(PROPERTY_STATE, oldValue, state);
		}
	}

	/**
	 * Initializes the Listeners that Change the State
	 */
	private void initializeStateListeners() {
		// GAME_LEFT
		if (this.getState().equals(ZFState.INGAME_WAITING)) {
			initializeStateListener_GameLeft();
		} else {
			this.getPropertyChangeSupport().addPropertyChangeListener(Mediator.PROPERTY_STATE,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							if (evt.getNewValue() != null && evt.getNewValue().equals(ZFState.INGAME_WAITING)) {
								initializeStateListener_GameLeft();
								Mediator.getInstance().getPropertyChangeSupport().removePropertyChangeListener(this);
							}
						}
					});
		}

		// INGAME_RUNNNING
		if (this.getState().equals(ZFState.INGAME_WAITING)) {
			initializeStateListener_IngameWaiting();
		} else {
			this.getPropertyChangeSupport().addPropertyChangeListener(Mediator.PROPERTY_STATE,
					new PropertyChangeListener() {
						// Add after joined and ingame
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							if (evt.getNewValue() != null && evt.getNewValue() == ZFState.INGAME_WAITING) {
								initializeStateListener_IngameRunning();
								Mediator.getInstance().getPropertyChangeSupport().removePropertyChangeListener(this);
							}
						}
					});
		}

		// INGAME_WAITING
		if (this.getState().equals(ZFState.FIELD_SELECTION)) {
			initializeStateListener_IngameWaiting();
		} else {
			this.getPropertyChangeSupport().addPropertyChangeListener(Mediator.PROPERTY_STATE,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							if (evt.getNewValue() == ZFState.FIELD_SELECTION) {
								initializeStateListener_IngameWaiting();
								Mediator.getInstance().getPropertyChangeSupport().removePropertyChangeListener(this);
							}

						}
					});
		}

		// LOGGED_IN and LOGGED_OUT
		if (Mediator.getInstance().getState().equals(ZFState.CONNECTED)) {
			// If already connected, just add Property Change for LogIn and Out
			this.getZombieFighter().getPropertyChangeSupport()
					.addPropertyChangeListener(ZombieFighter.PROPERTY_CURRENTUSER, evt -> {
						if (evt.getNewValue() != null) {
							Mediator.getInstance().setState(ZFState.LOGGED_IN);
						} else {
							Mediator.getInstance().setState(ZFState.LOGGED_OUT);
						}
					});
		} else {
			// Wait for the State to be Connected
			Mediator.getInstance().getPropertyChangeSupport().addPropertyChangeListener(Mediator.PROPERTY_STATE,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent event) {
							if (event.getNewValue() != null && event.getNewValue().equals(ZFState.CONNECTED)) {
								Mediator.getInstance().getZombieFighter().getPropertyChangeSupport()
										.addPropertyChangeListener(ZombieFighter.PROPERTY_CURRENTUSER, evt -> {
									if (evt.getNewValue() != null) {
										Mediator.getInstance().setState(ZFState.LOGGED_IN);
									} else {
										Mediator.getInstance().setState(ZFState.LOGGED_OUT);
									}
								});
								Mediator.getInstance().getPropertyChangeSupport().removePropertyChangeListener(this);
							}
						}
					});
		}
	}

	private void initializeStateListener_IngameWaiting() {
		Set<UserAssets> users = Mediator.getInstance().getZombieFighter().getCurrentGame().getUsers();
		for (Iterator<UserAssets> iterator = users.iterator(); iterator.hasNext();) {
			UserAssets userAsset = (UserAssets) iterator.next();
			// Find UserAsset for Current User
			if (userAsset.getUser().getNickname()
					.equals(Mediator.getInstance().getZombieFighter().getCurrentUser().getNick())) {
				if (userAsset.getField() == null) {
					userAsset.getPropertyChangeSupport().addPropertyChangeListener(UserAssets.PROPERTY_FIELD,
							new PropertyChangeListener() {
								@Override
								public void propertyChange(PropertyChangeEvent evt) {
									if (evt.getNewValue() != null) {
										Mediator.getInstance().setState(ZFState.INGAME_WAITING);
										userAsset.getPropertyChangeSupport().removePropertyChangeListener(this);
									}

								}
							});
				} else {
					Mediator.getInstance().setState(ZFState.INGAME_WAITING);
				}

			}
		}
	}

	private void initializeStateListener_GameLeft() {
		Set<UserAssets> users = Mediator.getInstance().getZombieFighter().getCurrentGame().getUsers();
		// Iterate over UserAssets and take the one that is
		// for the current Player
		for (Iterator<UserAssets> iterator = users.iterator(); iterator.hasNext();) {
			UserAssets userAsset = (UserAssets) iterator.next();
			if (userAsset.getUser().getNickname()
					.equals(Mediator.getInstance().getZombieFighter().getCurrentUser().getNick())) {
				// Set Game Left after the current user gets
				// removed
				Mediator.getInstance().getZombieFighter().getCurrentGame().getPropertyChangeSupport()
						.addPropertyChangeListener(ZombieFightGame.PROPERTY_USERS, new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (evt.getOldValue() != null && evt.getOldValue().equals(userAsset)
										&& !userAsset.equals(evt.getNewValue())) {
									Mediator.getInstance().setState(ZFState.GAME_LEFT);
									Mediator.getInstance().getZombieFighter().getCurrentGame()
											.getPropertyChangeSupport().removePropertyChangeListener(this);
								}

							}
						});
			}
		}
	}

	private void initializeStateListener_IngameRunning() {
		boolean running = Mediator.getInstance().getZombieFighter().getCurrentGame().getRunning();
		if (running) {
			Mediator.getInstance().setState(ZFState.INGAME_RUNNING);
		} else {
			// Add Property Change to RUNNING
			Mediator.getInstance().getZombieFighter().getCurrentGame().getPropertyChangeSupport()
					.addPropertyChangeListener(ZombieFightGame.PROPERTY_RUNNING, new PropertyChangeListener() {

						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							if (evt.getNewValue() != null && evt.getNewValue().equals(true)) {
								// Game Starts
								Mediator.getInstance().setState(ZFState.INGAME_RUNNING);
								// Don't remove Listener, because it
								// is also waiting for the game to
								// stop
							} else if (evt.getOldValue() != null && evt.getOldValue().equals(true)
									&& evt.getNewValue() != null && evt.getNewValue().equals(false)) {
								// Game ends and isn't running
								// anymore
								Mediator.getInstance().setState(ZFState.INGAME_STOPPED);
								Mediator.getInstance().getZombieFighter().getCurrentGame().getPropertyChangeSupport()
										.removePropertyChangeListener(this);
							}
						}
					});
		}
	}
}
