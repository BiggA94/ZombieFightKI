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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Cell;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Field;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Waypoint;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uniks.networkparser.json.JsonObject;

public class JsonWaypointHandler extends AbstractHandler {

	private static final String CLASS_WAYPOINT = "Waypoint";

	@Override
	public boolean handleString(String... messages) {
		return false;
	}

	@Override
	public boolean handleJSON(String... messages) {

		// analyze the JSON message
		JsonObject event = new JsonObject().withValue(messages);
		String timestampString = event.getString("@ts");
		Long timestamp = Long.parseLong(timestampString);
		String source = event.getString("@src");
		String property = event.getString("@prop");

		String oldValue = null;
		if (event.has("@ov"))
			oldValue = event.getString("@ov");
		String newValue = null;
		if (event.has("@nv"))
			newValue = event.getString("@nv");

		String sourceClass = source.split("@")[0];

		// is not responsible if the source is not correct
		if (!sourceClass.equals(CLASS_WAYPOINT))
			return false;

		// save the data to the model
		Waypoint w = Waypoint.getInstanceById(source);
		if (w == null)
			w = new Waypoint().withId(source);

		switch (property) {
		case "cell":
			Cell newCell = Cell.getInstanceById(newValue);
			if (newCell == null && newValue != null)
				newCell = new Cell().withId(newValue);

			Cell oldCell = Cell.getInstanceById(oldValue);

			if (oldValue != newValue) {
				w.withCell(newCell);
			}

			break;
		case "next":
			Waypoint newNext = Waypoint.getInstanceById(newValue);
			if (newNext == null && newValue != null)
				newNext = new Waypoint().withId(newValue);

			Waypoint oldNext = Waypoint.getInstanceById(oldValue);

			if (oldValue != newValue) {
				w.withNext(newNext);
			}

			break;
		case "previous":
			Waypoint newPrevious = Waypoint.getInstanceById(newValue);
			if (newPrevious == null && newValue != null)
				newPrevious = new Waypoint().withId(newValue);

			Waypoint oldPrevious = Waypoint.getInstanceById(oldValue);

			if (oldValue != newValue) {
				w.withPrevious(newPrevious);
			}
			
			break;
		default:
			break;
		}

		return true;
	}

	// ==========================================================================

	@Override
	public void removeYou() {
		super.removeYou();

		setHandlerPool(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}
}
