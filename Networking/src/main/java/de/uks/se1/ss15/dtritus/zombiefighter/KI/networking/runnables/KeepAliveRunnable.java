package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.runnables;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;

public class KeepAliveRunnable implements Runnable {
	
	private final ServerMessageHandler serverMessageHandler;
	private final String NOOP_JSON = "{\"@action\":\"NOOP\"}";
	private final String NOOP_STRING = "NOOP";

	/**
	 * @param serverMessageHandler
	 */
	public KeepAliveRunnable(ServerMessageHandler serverMessageHandler) {
		this.serverMessageHandler = serverMessageHandler;
	}

	@Override
	public void run() {
		while (!Thread.interrupted() && this.serverMessageHandler.isConnected()) {
			// send differnet messages for different protocols
			if (!serverMessageHandler.isJsonProtocol())
				this.serverMessageHandler.sendString(NOOP_STRING);
			else
				this.serverMessageHandler.sendString(NOOP_JSON);
			
			// repeat every half of time-out value
			try {
				Thread.sleep(this.serverMessageHandler.getTimeoutValue() / 2);
			} catch (InterruptedException e) {
				Mediator.printDebugln("Shutting down (From keepAliveThread)!");
				this.serverMessageHandler.interrupt();
			}
		}

	}
}