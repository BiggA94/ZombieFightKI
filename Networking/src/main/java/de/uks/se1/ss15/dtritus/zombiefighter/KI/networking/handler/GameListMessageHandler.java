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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GameSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class GameListMessageHandler extends AbstractHandler implements PropertyChangeInterface {

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
		if (messages.length > 1 && messages[0].toUpperCase().equals("NO GAMES IN PROGRESS")
				&& messages[messages.length - 1].equals("OK")
				&& this.getHandlerPool().getServerMessageHandler().getLastMessage().equals("LIST GAMES")) {
			int size = this.getHandlerPool().getZombieFighter().getGames().size();
			Object games[] = this.getHandlerPool().getZombieFighter().getGames().toArray();
			for (int i = 0; i < size; i++) {
				// Remove all Games
				((Game) games[i]).removeYou();

			}
		} else if (splitedMessage[0].equals("GAME") && messages[messages.length - 1].equals("OK")
				&& this.getHandlerPool().getServerMessageHandler().getLastMessage().equals("LIST GAMES")) {
			GameSet newList = new GameSet();
			// this.getHandlerPool().getServerMessageHandler().getZombieFighter().getGames().clear();
			// Fuer jede neue Zeile ein Spiel erstellen
			for (int i = 0; i < messages.length - 1; i++) {
				splitedMessage = messages[i].split(" ");
				// game name
				String gameName = splitedMessage[1].split("=")[1];
				Game game;
				GameSet match = this.getHandlerPool().getZombieFighter().getGames().hasName(gameName);
				// Game already saved?
				// No
				if (match.first() == null) {
					game = new Game().withName(gameName);
				} else { // Yes
					game = match.first();
				}
				String[] spliter;
				// Spielname
				// String[] spliter = splitedMessage[1].split("=");
				// game.setName(spliter[1]);

				// Anzahl Events
				spliter = splitedMessage[2].split("=");
				game.setEvents(Integer.parseInt(spliter[1]));
				// Maps
				spliter = splitedMessage[3].split("=");
				// Suche nach der Map mit den Namen
				MapSet map = this.getHandlerPool().getServerMessageHandler().getZombieFighter().getMaps()
						.hasName(spliter[1]);
				// Die Map existiert noch nicht
				if (map.first() == null) {
					Map newMap = this.getHandlerPool().getZombieFighter().createMaps().withName(spliter[1]);
					game.withMap(newMap);
				} else {
					// Andernfalls is die map schon da
					game.withMap(map.first());
				}
				// Add Users
				spliter = splitedMessage[4].split("=");
				spliter = spliter[1].split("/");
				game.setCurrentPlayers(Integer.valueOf(spliter[0]));
				game.setMaxPlayers(Integer.valueOf(spliter[1]));
				// Add status
				spliter = splitedMessage[5].split("=");
				game.setStatus(spliter[1]);

				// don't add games that are running
				if (game.getStatus().equals("RUNNING"))
					continue;

				// Add testgame flag
				spliter = splitedMessage[6].split("=");
				game.setTestgame(Boolean.parseBoolean(spliter[1]));
			}

			// Check for games which are new
			for (Game game : newList) {
				if (this.getHandlerPool().getZombieFighter().getGames().hasName(game.getName()).isEmpty()) {
					this.getHandlerPool().getZombieFighter().withGames(game);
				}
			}
			// Check for games which don't exist anymore
			int size = this.getHandlerPool().getZombieFighter().getGames().size();
			Object games[] = this.getHandlerPool().getZombieFighter().getGames().toArray();
			for (int i = 0; i < size; i++) {
				if ((newList.hasName(((Game) games[i]).getName())).isEmpty()) {
					((Game) games[i]).removeYou();
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
