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
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.GameUser;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Waypoint;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uniks.networkparser.json.JsonObject;

public class JsonMapHandler extends AbstractHandler {

	private static final String CLASS_MAP = "Map";

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
		if (!sourceClass.equals(CLASS_MAP))
			return false;

		// save the data to the model
		Map m = Map.getInstanceById(source);
		if (m == null)
			m = new Map().withId(source);

		switch (property) {
		case "field":
			Field newField = Field.getInstanceById(newValue);
			if (newField == null && newValue != null)
				newField = new Field().withId(newValue);

			Field oldField = Field.getInstanceById(oldValue);

			if (oldValue != newValue) {
				m.withField(newField);
			}
			
			break;
		case "cells":
			Cell newCell = Cell.getInstanceById(newValue);
			if (newCell == null && newValue != null)
				newCell = new Cell().withId(newValue);

			Cell oldCell = Cell.getInstanceById(oldValue);

			if (oldValue != newValue) {
				m.withoutCells(oldCell);
				m.withCells(newCell);
			}
			
			break;
		case "exitWaypoint":
			Waypoint newExitWaypoint = Waypoint.getInstanceById(newValue);
			if (newExitWaypoint == null && newValue != null)
				newExitWaypoint = new Waypoint().withId(newValue);

			Waypoint oldExitWaypoint = Waypoint.getInstanceById(oldValue);

			if (oldValue != newValue) {
				m.withExitWaypoint(newExitWaypoint);
			}
			
			break;
		case "enterWaypoint":
			Waypoint newEnterWaypoint = Waypoint.getInstanceById(newValue);
			if (newEnterWaypoint == null && newValue != null)
				newEnterWaypoint = new Waypoint().withId(newValue);

			Waypoint oldEnterWaypoint = Waypoint.getInstanceById(oldValue);

			if (oldValue != newValue) {
				m.withEnterWaypoint(newEnterWaypoint);
			}
			
			break;
		case "waypoints":
			Waypoint newWaypoint = Waypoint.getInstanceById(newValue);
			if (newWaypoint == null && newValue != null)
				newWaypoint = new Waypoint().withId(newValue);

			Waypoint oldWaypoint = Waypoint.getInstanceById(oldValue);

			if (oldValue != newValue) {
				m.withoutWaypoints(oldWaypoint);
				m.withWaypoints(newWaypoint);
			}
			
			break;
		case "background":
			m.withBackground(newValue);
			break;
		case "name":
			m.withName(newValue);
			break;
		case "createBy":
			m.withCreatedBy(newValue);
			break;
		case "version":
			m.withVersion(newValue);
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
