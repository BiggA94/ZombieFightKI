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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Cell;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Defense;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.UserAssets;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Waypoint;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Zombie;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uniks.networkparser.json.JsonObject;

public class JsonZombieHandler extends AbstractHandler {

	// ==========================================================================

	private final String CLASS_ZOMBIE = "Zombie";

	@Override
	public void removeYou() {
		super.removeYou();

		setHandlerPool(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

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
		if (!sourceClass.equals(this.CLASS_ZOMBIE))
			return false;

		// save the data to the model
		Zombie z = Zombie.getInstanceById(source);
		if (z == null)
			z = new Zombie().withId(source);

		switch (property) {
		case "cell":
			Cell newCell = Cell.getInstanceById(newValue);
			if (newCell == null && newValue != null)
				newCell = new Cell().withId(newValue);
			z.withCell(newCell);
			break;
		case "health":
			z.withHealth(Integer.valueOf(newValue));
			break;
		case "slowed":
			z.withSlowed(Boolean.valueOf(newValue));
			break;
		case "slowTime":
			z.withSlowTime(Double.valueOf(newValue));
			break;
		case "speed":
			z.withSpeed(Double.valueOf(newValue));
			break;
		case "type":
			z.withType(newValue);
			break;
		case "userAssets":
			UserAssets ua = UserAssets.getInstanceById(newValue);
			if (ua == null && newValue != null)
				ua = new UserAssets().withId(newValue);
			z.withUserAssets(ua);
			break;
		case "currentWaypoint":
			Waypoint wayPoint = Waypoint.getInstanceById(newValue);
			if (wayPoint == null && newValue != null)
				wayPoint = new Waypoint().withId(newValue);
			z.withCurrentWaypoint(wayPoint);
			break;
		default:
			break;
		}
		return true;
	}
}
