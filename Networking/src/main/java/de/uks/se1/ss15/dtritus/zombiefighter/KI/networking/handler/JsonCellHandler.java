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
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Defense;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Zombie;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uniks.networkparser.json.JsonObject;

public class JsonCellHandler extends AbstractHandler {

	private final String CLASS_CELL = "Cell";
	private final String CLASS_ZOMBIE = "Zombie";
	private final String CLASS_DEFENSE = "Defense";

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
		if (!sourceClass.equals(CLASS_CELL))
			return false;

		// save the data to the model
		Cell c = Cell.getInstanceById(source);
		if (c == null)
			c = new Cell().withId(source);

		switch (property) {
		case "map":
			Map newMap = Map.getInstanceById(newValue);
			if (newMap == null && newValue != null) {

				newMap = new Map().withId(newValue);
			} else if (oldValue != newValue) {
				c.withMap(newMap);
			}

			break;
		case "type":
			c.withType(newValue);
			break;
		case "x":
			c.withX(Integer.parseInt(newValue));
			break;
		case "y":
			c.withY(Integer.parseInt(newValue));
			break;
		case "elements":
			if (newValue != null) {
				if (newValue != null && newValue.contains(CLASS_DEFENSE)) {
					Defense newDefense = Defense.getInstanceById(newValue);

					if (newDefense == null && newValue != null) {
						newDefense = new Defense().withId(newValue);
					}

					Defense oldDefense = Defense.getInstanceById(oldValue);

					if (oldDefense != newDefense) {
						c.withDefense(newDefense);
					}
				} else if (newValue != null && newValue.contains(CLASS_ZOMBIE)) {
					Zombie newZombie = Zombie.getInstanceById(newValue);

					if (newZombie == null && newValue != null) {
						newZombie = new Zombie().withId(newValue);
					}

					Zombie oldZombie = Zombie.getInstanceById(oldValue);

					if (oldZombie != newZombie) {
						c.withZombie(newZombie);
					}
				}
			} else if (newValue == null && oldValue != null) {
				if (oldValue.contains(CLASS_ZOMBIE)) {
					Zombie oldZombie = Zombie.getInstanceById(oldValue);
					c.withoutZombie(oldZombie);
				}
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
