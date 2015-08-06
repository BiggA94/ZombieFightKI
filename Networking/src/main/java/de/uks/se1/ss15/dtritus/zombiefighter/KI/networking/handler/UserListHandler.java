/*
   Copyright (c) 2015 Major 
   
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

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.TeamSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class UserListHandler extends AbstractHandler implements
		PropertyChangeInterface {

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
	@Override
	public boolean handleString(String... messages) {
		String[] splitedMessage = messages[0].split(" ");
		if (messages[0].equals("OK")) {
			if (this.getHandlerPool().getServerMessageHandler()
					.getLastMessage().equals("LIST USERS")) {
				int size = this.getHandlerPool().getZombieFighter().getUsers()
						.size();
				Object users[] = this.getHandlerPool().getZombieFighter()
						.getUsers().toArray();
				for (int i = 0; i < size; i++) {
					if (users[i] != this.getHandlerPool().getZombieFighter()
							.getCurrentUser()) {
						((User) users[i]).removeYou();
					}
				}
				return true;
			} else {
				return false;
			}
		}
		if (messages.length > 1 && messages[1].split(" ")[0].equals("TEAM")) {
			return false;
		}
		// Correct form
		if (splitedMessage[0].equals("USER")
				&& messages[messages.length - 1].equals("OK")) {
			UserSet newList = new UserSet();
			newList.with(this.getHandlerPool().getZombieFighter()
					.getCurrentUser()); // Newer delete yourself
			newList.with(this.getHandlerPool().getZombieFighter().getUsers()
					.hasNick("AllUsers").first()); // Never delete All Users
													// object
			newList.with(this.getHandlerPool().getZombieFighter().getUsers()
					.hasNick("Team").first()); // Never delete Team Object

			for (int i = 0; i < messages.length - 1; i++) {
				splitedMessage = messages[i].split(" ");
				// Nick
				String nick = splitedMessage[1].split("=")[1];
				User user;
				UserSet match = this.getHandlerPool().getZombieFighter()
						.getUsers().hasNick(nick);
				// User already saved?
				if (match.isEmpty()) { // No
					user = new User().withNick(nick);
				} else { // Yes
					user = match.first();
				}
				// team
				String team = splitedMessage[2].split("=")[1];
				TeamSet teamMatch = this.getHandlerPool().getZombieFighter()
						.getTeams().hasName(team);
				// Team already saved?
				if (teamMatch.isEmpty()) { // No
					this.getHandlerPool()
							.getZombieFighter()
							.withTeams(
									new Team().withName(team).withMembers(user));
				} else { // Yes
					user.withTeam(teamMatch.first());
				}
				// status
				String status = splitedMessage[3].split("=")[1];
				user.setState(status);
				newList.add(user);
			}
			// Check for users which are new
			for (User user : newList) {
				if (this.getHandlerPool().getZombieFighter().getUsers()
						.hasNick(user.getNick()).isEmpty()) {
					this.getHandlerPool().getZombieFighter().withUsers(user);
				}
			}
			// Check for users which don't exist anymore
			// System.out.println(newList);
			int size = this.getHandlerPool().getZombieFighter().getUsers()
					.size();
			Object users[] = this.getHandlerPool().getZombieFighter()
					.getUsers().toArray();
			for (int i = 0; i < size; i++) {
				if ((newList.hasNick(((User) users[i]).getNick())).isEmpty()) {
					((User) users[i]).removeYou();
				}
			}
			return true;

		}
		return false;
	}

	public boolean handleJSON(String... messages) {
		return false;
	}

	// ==========================================================================

	public void removeYou() {
		setHandlerPool(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}
}
