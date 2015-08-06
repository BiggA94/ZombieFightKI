package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking;

import javafx.application.Platform;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;

public class MessageSender implements Runnable {

	private ServerMessageHandler serverMessageHandler;

	public MessageSender(ServerMessageHandler smHandler) {
		// TODO Auto-generated constructor stub
		serverMessageHandler = smHandler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.interrupted() && serverMessageHandler.isConnected()) {
			// Get oldest Message to send
			byte[] message = new byte[0];
			try {
				// System.out.println(Platform.isFxApplicationThread());
				if (serverMessageHandler.isLastMessageHandled()) {
					message = (byte[]) serverMessageHandler.getSendBuffer().take();
					assert(message != null);
					// Print what will be sent
					ByteArrayOutputStream output = new ByteArrayOutputStream();
					output.write(message);
					Mediator.printDebugln("Sending \"" + output.toString().substring(0, output.size() - 2) + "\"");
					// Send
					serverMessageHandler.send(message);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
