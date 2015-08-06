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

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class ConnectionHandler extends AbstractHandler implements
		PropertyChangeInterface {

	@Override
	public boolean handleString(String... messages) {
		if (messages.length == 1) {
			// Don't handle the OK from NOOP
			if (!messages[0].startsWith("OK")) {

				// First is the Servername and the Serverversion,
				// Second is the Timeout-value
				String[] splitedMessage = messages[0].split(", ");

				if (splitedMessage.length == 2) {
					String server = splitedMessage[0];
					String timeout = splitedMessage[1];

					if (server.startsWith("SE1")
							&& timeout.startsWith("Timeout")) {

						String[] splitedTimeout = timeout.split(" ");
						if (splitedTimeout.length == 4) {
							// The 4th is the Timeout-value
							String timeoutValueString = splitedTimeout[3];

							// Remove the "ms" from the String

							timeoutValueString = timeoutValueString.split("ms")[0];

							int timeoutValue = Integer
									.valueOf(timeoutValueString);

							if (this.getHandlerPool() != null
									&& this.getHandlerPool()
											.getServerMessageHandler() != null) {
								this.getHandlerPool().getServerMessageHandler()
										.keepAlive(timeoutValue);
							}
						}

						// TODO check server-version
						this.getHandlerPool().getServerMessageHandler().setConnectionHeaderReceived(true);
						this.getHandlerPool().getServerMessageHandler().setLastMessageShouldBeHandled(false);
						return true;
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

	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	// ==========================================================================

	public void removeYou() {
		setHandlerPool(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}
}
