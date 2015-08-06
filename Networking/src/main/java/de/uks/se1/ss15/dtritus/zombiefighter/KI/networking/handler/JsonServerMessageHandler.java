/*
   Copyright (c) 2015 Terra1450
   
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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uniks.networkparser.json.JsonObject;

public class JsonServerMessageHandler extends AbstractHandler {
	
	String serverMessage = null;
	
	@Override
	public boolean handleString(String... messages) {
		return false;
	}

	@Override
	public boolean handleJSON(String... messages) {
		// Analyze the JSON Message
		JsonObject msg = new JsonObject().withValue(messages);
		
		String timestampString = msg.getString("@ts");
		Long timestamp = Long.parseLong(timestampString);
		
		String source = msg.getString("@src");
		String property = msg.getString("@prop");
		
		String oldValue = null;
		if ( msg.has("@ov") ) {
			oldValue = msg.getString("@ov");
		}
		String newValue = null;
		if ( msg.has("@nv") ) {
			newValue = msg.getString("@nv");
		}
		
		String sourceClass = source.split("@")[0];
		
		// Is not responsible if the source is not correct
		if ( !sourceClass.equals("Message") ) {
			return false;
		}
				
		switch (property) {
			case "text":
				serverMessage = serverMessage + ": " + newValue;
				Mediator.printDebugln(serverMessage);
			break;
			case "type":
				serverMessage = newValue;
				break;
		default:
			break;
		}
		
		return true;
	}
	
	@Override
	public void removeYou() {
		super.removeYou();

		setHandlerPool(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

}
