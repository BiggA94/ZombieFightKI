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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Cell;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Defense;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.UserAssets;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Zombie;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uniks.networkparser.json.JsonObject;

public class JsonDefenseHandler extends AbstractHandler {

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
		if (msg.has("@ov")) {
			oldValue = msg.getString("@ov");
		}
		String newValue = null;
		if (msg.has("@nv")) {
			newValue = msg.getString("@nv");
		}

		String sourceClass = source.split("@")[0];

		// Is not responsible if the source is not correct
		if (!sourceClass.equals("Defense")) {
			return false;
		}

		// Save the data to the model
		Defense d = Defense.getInstanceById(source);
		if (d == null) {
			d = new Defense().withId(source);
		}

		switch (property) {
		case "building":
			if (newValue == "true") {
				d.withBuilding(true);
			}

			if (newValue == "false") {
				d.withBuilding(false);
			}

			break;
		case "buildProgress":
			double buildProgress = Double.parseDouble(newValue);
			d.withBuildProgress(buildProgress);

			break;
		case "fireOn":
			Zombie newZombie = Zombie.getInstanceById(newValue);

			if (newZombie == null && newValue != null) {
				newZombie = new Zombie().withId(newValue);
			}

			if (oldValue != newValue) {
				d.withFireOn(newZombie);
			}

			break;
		case "level":
			int level = Integer.parseInt(newValue);
			d.withLevel(level);

			break;
		case "type":
			d.withType(newValue);

			break;
		case "upgrading":
			if (newValue == "true") {
				d.withUpgrading(true);
			}

			if (newValue == "false") {
				d.withUpgrading(false);
			}

			break;
		case "color":
			d.withColor(newValue);

			break;
		case "selling":
			if (newValue == "true") {
				d.withSelling(true);
			}

			if (newValue == "false") {
				d.withSelling(false);
			}

			break;
		case "cell":
			Cell newCell = Cell.getInstanceById(newValue);

			if (newCell == null && newValue != null) {
				newCell = new Cell().withId(newValue);
			}

			if (oldValue != newValue) {
				d.withCell(newCell);
			}

			break;
		case "userAssets":
			UserAssets newUser = UserAssets.getInstanceById(newValue);

			if (newUser == null && newValue != null) {
				newUser = new UserAssets().withId(newValue);
			}

			if (oldValue != newValue) {
				d.withUserAsset(newUser);
			}

			break;
		case "strategy":
			d.withStrategy(newValue);
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
