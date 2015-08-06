/*
   Copyright (c) 2015 Michael 
   
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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class LoginHandler extends AbstractHandler implements
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
		// Variables hold's the data set's. Example: variables.get(0) = {USER},
		// variables.get(1) = {NICK, bob}.
		ArrayList<String[]> variables = new ArrayList<String[]>();

		// Parse raw server message's to a 'data set' based structure.
		if (messages.length == 3) {
			for (String line : messages) {
				String[] splitedLine = line.split(" ");

				for (int i = 0; i < splitedLine.length; i++) {
					String[] oneVar = splitedLine[i].split("=");
					variables.add(oneVar);
				}
			}
		}

		// Check if the LoginHandler is responsible for this server message.
		if (variables.size() == 8) {
			String[] user = variables.get(0);
			if ("USER".equals(user[0])) {
				String[] nick = variables.get(1);
				if ("NICK".equals(nick[0])) {
					String[] email = variables.get(2);
					if ("EMAIL".equals(email[0])) {
						String[] role = variables.get(3);
						if ("ROLE".equals(role[0])) {
							String[] team = variables.get(4);
							if ("TEAM".equals(team[0])) {
								String[] name = variables.get(5);
								if ("NAME".equals(name[0])) {
									String[] id = variables.get(6);
									if ("ID".equals(id[0])) {
										String[] ok = variables.get(7);
										if ("OK".equals(ok[0])) {
											// Save data set's!
											ZombieFighter zf = this
													.getHandlerPool()
													.getZombieFighter();

											Team newTeam = new Team().withName(
													name[1]).withId(id[1]);
											User newUser = new User()
													.withNick(nick[1])
													.withEmail(email[1])
													.withRole(role[1])
													.withTeam(newTeam);

											// Print to TestSignal
											Mediator.printTestSignal("Logged in as "
													+ nick[1]);
											Mediator.printDebugln("Logged in as "
													+ nick[1]);

											zf.withCurrentUser(newUser);
											zf.withUsers(newUser);

											// Successfully done...
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	public boolean handleJSON (String... messages) {
		return false;
	}

	// ==========================================================================

	public void removeYou() {
		setHandlerPool(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}
}
