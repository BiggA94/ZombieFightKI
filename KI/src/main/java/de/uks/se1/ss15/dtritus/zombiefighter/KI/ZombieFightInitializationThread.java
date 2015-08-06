package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.ConnectException;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;

/**
 * This Class Connects to the Server, Logs in, joins a game and the selects a
 * sector. After that it finishes and stops.
 * 
 * @author Alexander
 *
 */
public class ZombieFightInitializationThread implements Runnable {

	/**
	 * 
	 */
	private KI ki;
	private String[] args;

	/**
	 * <p>
	 * The args can contain the Following parameters
	 * </p>
	 * <ul>
	 * <li>"DEBUGMODE" - Enables the Debugmode</li>
	 * <li>"USERNAME" - The Username for the KI</li>
	 * <li>"PASSWORD" - The Password for the KI</li>
	 * <li>"GAME" - The Name of the Game that should be joined</li>
	 * <li>"FIELD" - The ID of the Sector that should be chosen</li>
	 * </ul>
	 * 
	 * @param ki
	 * @param args
	 */
	public ZombieFightInitializationThread(KI ki, String[] args) {
		this.ki = ki;
		this.args = args;
	}

	@Override
	public void run() {
		try {
			new Mediator();
			if (Mediator.getInstance().getState().equals(ZFState.STARTED)) {
				Mediator.getInstance().connect();
			}
			parseArgs(args);

			while (!Thread.interrupted()) {
				if (Mediator.getInstance().getState() == ZFState.INGAME_WAITING) {
					break;
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			e.printStackTrace();
		}
	}

	public void parseArgs(String[] args) throws InterruptedException {
		String username = null;
		String password = null;
		String game = null;
		String field = null;

		for (int i = 0; i < args.length; i++) {
			String[] split = args[i].split("=");

			if (split.length == 2) {
				switch (split[0].toUpperCase()) {
				case "DEBUGMODE":
					Mediator.setDebugMode(Boolean.parseBoolean(split[1]));
					break;
				case "USERNAME":
					username = split[1];
					break;
				case "PASSWORD":
					password = split[1];
					break;
				case "GAME":
					game = split[1];
					break;
				case "FIELD":
					field = split[1];
					break;
				default:
					System.err.println("Unknown argument " + args[i]);
					break;
				}
			}
		}

		// If username and password were set
		if (username != null && password != null) {
			login(username, password);
		} else {
			return;
		}
		// Join Game
		if (game != null) {
			joinGame(game);
		} else {
			return;
		}
		// Select Field
		if (field != null) {
			selectField(field);
		} else {
			return;
		}

	}

	public void login(String username, String password) {
		System.out.println("Trying to connect");
		System.out.println(Mediator.getInstance().getState());
		if (Mediator.getInstance().getState().equals(ZFState.CONNECTED)) {
			loginNow(username, password);
		} else {
			Mediator.getInstance().getPropertyChangeSupport().addPropertyChangeListener(Mediator.PROPERTY_STATE,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							if (evt.getNewValue() != null && evt.getNewValue().equals(ZFState.CONNECTED)) {
								loginNow(username, password);
								Mediator.getInstance().getPropertyChangeSupport().removePropertyChangeListener(this);
							}
						}
					});
		}
	}

	private void loginNow(String username, String password) {
		System.out.println("Automatic login as \"" + username + "\"");
		Mediator.getInstance().getZombieFighter().getServerMessageHandler()
				.sendString("LOGIN " + username + " " + password);
	}

	public void joinGame(String game) {
		if (Mediator.getInstance().getState().equals(ZFState.LOGGED_IN)) {
			joinGameNow(game);
		} else {
			// If not logged in, wait for it and the join
			Mediator.getInstance().getPropertyChangeSupport().addPropertyChangeListener(Mediator.PROPERTY_STATE,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							if (evt.getNewValue() == ZFState.LOGGED_IN) {
								joinGameNow(game);
								Mediator.getInstance().getPropertyChangeSupport().removePropertyChangeListener(this);
							}
						}
					});
		}
	}

	private void joinGameNow(String game) {
		System.out.println("Joining Game \"" + game + "\"");
		Mediator.getInstance().getZombieFighter().getServerMessageHandler().sendString("JOIN GAME " + game);
	}

	public void selectField(String field) {
		if (Mediator.getInstance().getState().equals(ZFState.FIELD_SELECTION)) {
			selectFieldNow(field);
		} else {
			Mediator.getInstance().getPropertyChangeSupport().addPropertyChangeListener(Mediator.PROPERTY_STATE,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							if (evt.getNewValue() == ZFState.FIELD_SELECTION) {
								selectFieldNow(field);
								Mediator.getInstance().getPropertyChangeSupport().removePropertyChangeListener(this);
							}
						}
					});
		}
	}

	private void selectFieldNow(String field) {
		System.out.println("Selecting Field \"" + field + "\"");
		Mediator.getInstance().getZombieFighter().getServerMessageHandler()
				.sendString("{\"@action\":\"CHOOSE_FIELD\",\"properties\":{\"entry\":{\"key\":\"field\",\"value\":\""
						+ field + "\"}}}");
	}
}