package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;

public class DownloadMapHandler extends AbstractHandler implements PropertyChangeInterface {

	private static final String DOWNLOAD_MAP_COMMAND = "DOWNLOAD MAP";

	@Override
	public boolean handleString(String... messages) {
		if (this.getHandlerPool().getServerMessageHandler().getLastMessage().startsWith(DOWNLOAD_MAP_COMMAND)) {
			// System.out.println(messages[0]);
			// initialize frequently used objects
			String lastMessageSent = this.getHandlerPool().getServerMessageHandler().getLastMessage();

			// turn the message array into a one liner
			StringBuilder messageReceived = new StringBuilder();
			for (String message : messages) {
				messageReceived.append(message);
				messageReceived.append(" ");
			}
			// The pattern (SENDING xxx BYTES) that the message needs to be in.
			Pattern downloadMapResponsePattern = Pattern.compile("SENDING\\p{Blank}\\d*\\p{Blank}BYTES");
			Matcher downloadMapResponseMatcher = downloadMapResponsePattern.matcher(messageReceived);
			// True if the message matches the pattern.
			Boolean messageMatches = downloadMapResponseMatcher.find();
			if (lastMessageSent.startsWith(DOWNLOAD_MAP_COMMAND) && messageMatches) {
				// Set expected bytes that will be transfered
				int expectedBytes = Integer.parseInt(messageReceived.toString().split(" ")[1]);
				Mediator.getInstance().getZombieFighter().getServerMessageHandler().setExpectedBytes(expectedBytes);
				// Set the MessageHandlerPool to byte stream mode
				this.getHandlerPool().setByteStreamExpected(true);
				// Marks the received message as not fully handled
				Mediator.getInstance().getZombieFighter().getServerMessageHandler()
						.setLastMessageShouldBeHandled(false);
				return true;
			} else
				if (lastMessageSent.startsWith(DOWNLOAD_MAP_COMMAND) && messageReceived.toString().contains("OK")) {
				// Handle 'OK'
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean handleJSON(String... messages) {
		// TODO Auto-generated method stub
		return false;
	}

}
