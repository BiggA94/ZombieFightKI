/*
   Copyright (c) 2015 Julian 
   
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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.Main;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class CreateGameHandler extends AbstractHandler implements
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
		String lastMessage = this.getHandlerPool()
				.getServerMessageHandler().getLastMessage();

		if (messages.length == 1) {

			// catch last sent message
			if (messages[0].equals("OK")
					&& lastMessage.startsWith("CREATE GAME")) {

				String[] splitMessage = lastMessage.split(" ");

				String gameName = splitMessage[2];
				String players = splitMessage[4];
				String mapName = splitMessage[6];
				Boolean testGame = false;

				// game tagged with '-testgame' ?
				if (splitMessage.length > 7) {
					testGame = true;
				}

				Mediator.printDebugln("Success, game created");
				Mediator.printDebugln(
						"INFO: " + gameName + " " + players + " " + mapName
								+ " " + testGame.toString());

				return true;
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
