/*
   Copyright (c) 2015 dotRessel 
   
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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.Main;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class JoinGameHandler extends AbstractHandler {

	private static final String JOIN_GAME_COMMAND = "JOIN GAME";
	private ZombieFighter zombieFighter;

	@Override
	public boolean handleString(String... messages) {
		// initialize frequently used objects
		String lastMessageSent = this.getHandlerPool()
				.getServerMessageHandler().getLastMessage();
		zombieFighter = Mediator.getInstance().getZombieFighter();

		// turn the message array into a one liner
		StringBuilder messageReceived = new StringBuilder();
		for (String message : messages) {
			messageReceived.append(message);
			messageReceived.append(" ");
		}

		// The pattern (SENDING xxx EVENTS OK) that the message needs to be in.
		Pattern joinGameResponsePattern = Pattern
				.compile("SENDING\\p{Blank}\\d*\\p{Blank}EVENTS\\p{Blank}OK");
		Matcher joinGameResponseMatcher = joinGameResponsePattern
				.matcher(messageReceived);
		// True if the message matches the pattern.
		Boolean messageMatches = joinGameResponseMatcher.find();

		// If the last message sent was not the JOIN GAME command this handler
		// is not responsible. Also return false, if the pattern did not match.
		if (!lastMessageSent.startsWith(JOIN_GAME_COMMAND) || !messageMatches) {
			return false;
		} else {
			// switch to JSON protocol
			zombieFighter.getServerMessageHandler().setJsonProtocol(true);

			for (User u : zombieFighter.getUsers()) {
				u.getPropertyChangeSupport().firePropertyChange(
						User.PROPERTY_INGAME, true, false);
			}

			return true;
		}
	}

	@Override
	public boolean handleJSON(String... messages) {
		// TODO Auto-generated method stub
		return false;
	}

	// ==========================================================================

	@Override
	public void removeYou() {
		super.removeYou();

		setHandlerPool(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}
}
