package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
//import zombiefight.KI.KI;

public class Main {
	private static ZombieFightThread zombieFightThread;

	public static class ZombieFightThread implements Runnable {

		private boolean shouldRun = true;

		@Override
		public void run() {
			new Mediator();
			while (!Thread.interrupted() && shouldRun) {
				try {
					if(Mediator.getInstance().getState() == ZFState.INGAME){
						//KI ki = new KI(Mediator.getInstance().getZombieFighter().getCurrentGame());
					}
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}

			Mediator.getInstance().interrupt();
		}

		public void login(String username, String password) {
			try {
				while (Mediator.getInstance() == null || !Mediator.isRunning()) {
					// Wait until Application is running
					Thread.sleep(100);
					if (!ServerMessageHandler.checkServer(Mediator.getServerAddress(), Mediator.getServerport())) {
						System.err.println("No Connection to server!!");
						return;
					}
				}
				Mediator.getInstance().connect();
				while (!Mediator.getInstance().isConnected()) {
					// wait until connected
					Thread.sleep(100);
				}
				System.err.println("Login!");
				// Login

				ZombieFighter game = Mediator.getInstance().getZombieFighter();

				Mediator.getInstance().getZombieFighter().getServerMessageHandler()
						.sendString("LOGIN " + username + " " + password);

			} catch (Exception e) {
				shouldRun = false;
			}
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
									Mediator.getInstance().getPropertyChangeSupport()
											.removePropertyChangeListener(this);
								}
							}
						});
			}
		}

		private void joinGameNow(String game) {
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
									Mediator.getInstance().getPropertyChangeSupport()
											.removePropertyChangeListener(this);
								}
							}
						});
			}
		}

		private void selectFieldNow(String field) {
			Mediator.getInstance().getZombieFighter().getServerMessageHandler().sendString(
					"{\"@action\":\"CHOOSE_FIELD\",\"properties\":{\"entry\":{\"key\":\"field\",\"value\":\"" + field
							+ "\"}}}");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		zombieFightThread = new ZombieFightThread();
		Thread thread = new Thread(zombieFightThread);
		thread.start();
		parseArgs(args);
	}

	public static void parseArgs(String[] args) throws InterruptedException {
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
			System.out.println("Automatic login as \"" + username + "\"");
			zombieFightThread.login(username, password);
		}
		// Join Game
		if (game != null) {
			System.out.println("Joining Game \"" + game + "\"");
			zombieFightThread.joinGame(game);
		}
		// Select Field
		if (field != null) {
			System.out.println("Selecting Field \"" + field + "\"");
			zombieFightThread.selectField(field);
		}

	}

}
