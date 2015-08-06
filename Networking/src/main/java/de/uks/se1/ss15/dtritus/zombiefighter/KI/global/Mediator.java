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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.beans.PropertyChangeListener;
import java.net.ConnectException;
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
		if (Mediator.testSignal != null) {
			if (bool == true) {
				Mediator.printTestSignal("Mediator started");
			} else {
				Mediator.printTestSignal("Mediator stopped");
			}
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

	private static Mediator mediator;

	public static Mediator getInstance() {
		if (mediator == null) {
			mediator = new Mediator();
		}
		return mediator;
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
		Mediator.mediator = this;
	}

	public void connect() throws ConnectException {
		connect(Mediator.getServerAddress(),Mediator.getServerport());
	}

	public void connect(String serverURL, int serverPort) throws ConnectException{
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

	public Mediator(){
		start();
	}

	public void start(){
		Boolean connection = ServerMessageHandler.checkServer(getServerAddress(), getServerport());
		System.out.println("Serverconnection: " + connection);
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
		if(state != this.state){
			ZFState oldValue = this.state;
			this.state = state;
			this.getPropertyChangeSupport().firePropertyChange(PROPERTY_STATE, oldValue, state);
		}
	}
}
