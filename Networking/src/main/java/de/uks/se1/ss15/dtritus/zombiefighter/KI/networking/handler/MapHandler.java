/*
 Copyright (c) 2015 Uwe
 
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
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class MapHandler extends AbstractHandler implements PropertyChangeInterface {

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
		if (Mediator.getInstance().getZombieFighter().getServerMessageHandler().getLastMessage().equals("LIST MAPS")
				&& !messages[0].startsWith("OK") && messages[messages.length - 1].equals("OK")
				&& messages[0].startsWith("MAP")) {
			for (String string : messages) {
				if (!string.equals("OK")) {
					// message will splitted to each part
					String[] splitedMessage = string.split(" ");
					if (splitedMessage.length == 4) {

						String map = splitedMessage[1];
						String creator = splitedMessage[2];
						String version = splitedMessage[3].split("=")[1];
						String[] splitedMap = map.split("=");
						if (splitedMap[0].equals("NAME")) {
							if (splitedMap.length == 2) {
								// name of map is put at zombiefighter
								String mapValueString = splitedMap[1];
								Map newMap = new Map().withName(mapValueString).withVersion(version);
								
								// Add new map
								if (Mediator.getInstance().getZombieFighter().getMaps().hasName(newMap.getName())
										.isEmpty()) {
									Mediator.getInstance().getZombieFighter().withMaps(newMap);
								} 
								// Update map version
								else if (Mediator.getInstance().getZombieFighter().getMaps().hasName(newMap.getName())
										.first().getVersion() == null
										|| !Mediator.getInstance().getZombieFighter().getMaps()
												.hasName(newMap.getName()).first().getVersion().equals(version)) {
									Mediator.getInstance().getZombieFighter().getMaps()
										.hasName(newMap.getName()).first().setVersion(version);
								}
							}
						}
					}
				}else{
					return true;
				}
			}
			return false;
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
