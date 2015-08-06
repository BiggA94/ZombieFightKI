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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Timestamp;
import java.util.Date;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uniks.networkparser.json.JsonObject;

public class UserMessageHandler extends AbstractHandler implements PropertyChangeInterface {

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
	// Example: MSG FROM ME TO User: "hi"
	@Override
	public boolean handleString(String... messages) {
		if (messages.length == 1) {
			// If the Message was destinated to a User and not to all users
			if (messages[0].startsWith("MSG FROM")) {
				String[] split = messages[0].split(" ", 6);

				String fromString = split[2];
				String toString = split[4];

				User from = new User();
				User to = new User();
				Message msg = new Message();

				/*
				 * Handle from
				 */
				if (fromString.equals("ME")) {
					from = this.getHandlerPool().getZombieFighter().getCurrentUser();
				} else {
					from = this.getHandlerPool().getZombieFighter().getUsers().hasNick(fromString).first();
					// If User not currently in Userlist, create him
					if (from == null) {
						User user = new User().withNick(fromString);
						Mediator.getInstance().getZombieFighter().withUsers(user);
						from = user;
					}
				}

				/*
				 * Handle to
				 */
				if (toString.equals("YOU:")) {
					to = this.getHandlerPool().getZombieFighter().getCurrentUser();
					// to.withMessagesInbox(msg);
				} else if (toString.equals("TEAM:")) {
					// TODO discuss
					to = this.getHandlerPool().getZombieFighter().getUsers().hasNick("TeamUser").first();
					to.withMessagesInbox(msg);
				} else if (toString.equals("ALL:")) {
					to = this.getHandlerPool().getZombieFighter().getUsers().hasNick("AllUsers").first();
				} else {
					to = this.getHandlerPool().getZombieFighter().getUsers()
							.hasNick(toString.substring(0, toString.length() - 1)).first(); // toString.length()-1,
																							// because
																							// of
																							// the
																							// column...
					// If User not currently in Userlist, create him
					if (to == null) {
						User user = new User().withNick(toString.substring(0, toString.length() - 1));
						Mediator.getInstance().getZombieFighter().withUsers(user);
						to = user;
					}
				}

				// Text
				String textString = split[5].substring(1, split[5].length() - 1);// remove
																					// "
																					// at
																					// beginning
																					// and
																					// end
																					// of
																					// the
																					// message

				// create message object
				msg.withFromUser(from).withToUser(to).withDate(new Timestamp(new Date().getTime()))
						.withText(textString);

				from.withMessagesOutbox(msg);
				to.withMessagesInbox(msg);

				this.getHandlerPool().getZombieFighter().withMessages(msg);

				Mediator.printDebugln(msg + " from " + msg.getFromUser() + " to " + msg.getToUser());
				this.getHandlerPool().getServerMessageHandler().setLastMessageShouldBeHandled(false);
				return true;
			}
		}
		return false;
	}

	public boolean handleJSON(String... messages) {

		JsonObject userMessage = new JsonObject().withValue(messages);
		String messageType = userMessage.getString("@prop");

		switch (messageType) {
		case "USER_MESSAGE": {
			Message msg = new Message();
			String ts = userMessage.getString("@ts");
			msg.withDate(new Timestamp(Long.parseLong(ts)));
			msg.withText(userMessage.getString("@nv"));

			// from
			String fromString = userMessage.getString("@src");

			boolean fromIsCurrentUser = this.getHandlerPool().getZombieFighter().getCurrentUser().getNick()
					.equals(fromString);

			User fromUser = fromIsCurrentUser ? this.getHandlerPool().getZombieFighter().getCurrentUser() : null;

			for (User u : this.getHandlerPool().getZombieFighter().getUsers()) {
				if (fromUser != null)
					break;
				if (u.getNick().equals(fromString))
					fromUser = u;
			}

			// to
			String toString = null;

			if (fromIsCurrentUser) {

				// get the recipient through the last message sent
				String lastMessageSentString = this.getHandlerPool().getServerMessageHandler().getLastMessage();
				if (lastMessageSentString.startsWith("{\"@action\":\"MESSAGE\"")) {
					JsonObject lastMessageSent = new JsonObject().withValue(lastMessageSentString);
					toString = lastMessageSent.getJsonObject("properties").getJsonObject("entry").getString("value");
				}
			} else {
				toString = this.getHandlerPool().getZombieFighter().getCurrentUser().getNick();
			}

			User toUser = !fromIsCurrentUser ? this.getHandlerPool().getZombieFighter().getCurrentUser() : null;

			for (User u : this.getHandlerPool().getZombieFighter().getUsers()) {
				if (toUser != null)
					break;
				if (u.getNick().equals(toString))
					toUser = u;
			}

			// if the from user does not exist create it
			if (fromUser == null) {
				User user = new User().withNick(fromString);
				Mediator.getInstance().getZombieFighter().withUsers(user);
				fromUser = user;
			}
			// if the to user does not exist create it
			if (toUser == null) {
				User user = new User().withNick(toString);
				Mediator.getInstance().getZombieFighter().withUsers(user);
				toUser = user;
			}

			if (fromUser == null || toUser == null)
				return true;

			msg.withFromUser(fromUser);
			msg.withToUser(toUser);

			fromUser.withMessagesOutbox(msg);
			toUser.withMessagesInbox(msg);
			this.getHandlerPool().getZombieFighter().withMessages(msg);

			return true;
		}
		case "TEAM_MESSAGE": {
			Message msg = new Message();
			String ts = userMessage.getString("@ts");
			msg.withDate(new Timestamp(Long.parseLong(ts)));
			msg.withText(userMessage.getString("@nv"));

			// to
			User toUser = this.getHandlerPool().getZombieFighter().getUsers().hasNick("TeamUser").first();
			msg.withToUser(toUser);

			// from
			String fromString = userMessage.getString("@src");
			User fromUser = null;
			for (User u : this.getHandlerPool().getZombieFighter().getUsers()) {
				if (u.getNick().equals(fromString))
					fromUser = u;
			}

			// if the from user does not exist create it
			if (fromUser == null) {
				User user = new User().withNick(fromString);
				Mediator.getInstance().getZombieFighter().withUsers(user);
				fromUser = user;
			}

			msg.withFromUser(fromUser);

			if (fromUser == null || toUser == null)
				return true;

			fromUser.withMessagesOutbox(msg);
			toUser.withMessagesInbox(msg);
			this.getHandlerPool().getZombieFighter().withMessages(msg);

			return true;
		}
		case "PUBLIC_MESSAGE": {
			Message msg = new Message();
			String ts = userMessage.getString("@ts");
			msg.withDate(new Timestamp(Long.parseLong(ts)));
			msg.withText(userMessage.getString("@nv"));

			// to
			User toUser = this.getHandlerPool().getZombieFighter().getUsers().hasNick("AllUsers").first();
			msg.withToUser(toUser);

			// from
			String fromString = userMessage.getString("@src");
			User fromUser = null;
			for (User u : this.getHandlerPool().getZombieFighter().getUsers()) {
				if (u.getNick().equals(fromString))
					fromUser = u;
			}

			// if the from user does not exist create it
			if (fromUser == null) {
				User user = new User().withNick(fromString);
				Mediator.getInstance().getZombieFighter().withUsers(user);
				fromUser = user;
			}

			msg.withFromUser(fromUser);

			if (fromUser == null || toUser == null)
				return true;

			fromUser.withMessagesOutbox(msg);
			toUser.withMessagesInbox(msg);
			this.getHandlerPool().getZombieFighter().withMessages(msg);

			return true;
		}
		case "MESSAGE": {
			Message msg = new Message();
			String ts = userMessage.getString("@ts");
			msg.withDate(new Timestamp(Long.parseLong(ts)));
			msg.withText(userMessage.getString("@nv"));

			// from
			String fromString = userMessage.getString("@src");

			boolean fromIsCurrentUser = this.getHandlerPool().getZombieFighter().getCurrentUser().getNick()
					.equals(fromString);

			User fromUser = fromIsCurrentUser ? this.getHandlerPool().getZombieFighter().getCurrentUser() : null;

			for (User u : this.getHandlerPool().getZombieFighter().getUsers()) {
				if (fromUser != null)
					break;
				if (u.getNick().equals(fromString))
					fromUser = u;
			}

			// to
			String toString = null;

			if (fromIsCurrentUser) {

				// get the recipient through the last message sent
				String lastMessageSentString = this.getHandlerPool().getServerMessageHandler().getLastMessage();
				if (lastMessageSentString.startsWith("{\"@action\":\"MESSAGE\"")) {
					JsonObject lastMessageSent = new JsonObject().withValue(lastMessageSentString);
					toString = lastMessageSent.getJsonObject("properties").getJsonObject("entry").getString("value");
				}
			} else {
				toString = this.getHandlerPool().getZombieFighter().getCurrentUser().getNick();
			}

			User toUser = !fromIsCurrentUser ? this.getHandlerPool().getZombieFighter().getCurrentUser() : null;

			for (User u : this.getHandlerPool().getZombieFighter().getUsers()) {
				if (toUser != null)
					break;
				if (u.getNick().equals(toString))
					toUser = u;
			}

			// if the from user does not exist create it
			if (fromUser == null) {
				User user = new User().withNick(fromString);
				Mediator.getInstance().getZombieFighter().withUsers(user);
				fromUser = user;
			}
			// if the to user does not exist create it
			if (toUser == null) {
				User user = new User().withNick(toString);
				Mediator.getInstance().getZombieFighter().withUsers(user);
				toUser = user;
			}

			if (fromUser == null || toUser == null)
				return true;

			msg.withFromUser(fromUser);
			msg.withToUser(toUser);

			fromUser.withMessagesOutbox(msg);
			toUser.withMessagesInbox(msg);
			this.getHandlerPool().getZombieFighter().withMessages(msg);

			return true;
		}
		default:
			break;
		}

		return false;
	}

	// ==========================================================================

	public void removeYou() {
		setHandlerPool(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}
}
