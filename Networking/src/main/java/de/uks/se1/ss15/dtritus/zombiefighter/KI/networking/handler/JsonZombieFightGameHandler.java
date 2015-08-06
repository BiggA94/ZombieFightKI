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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Field;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.GameUser;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Guide;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.UserAssets;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;
import de.uniks.networkparser.json.JsonObject;

public class JsonZombieFightGameHandler extends AbstractHandler {

	private static final String CLASS_ZOMBIE_FIGHT_GAME = "ZombieFightGame";

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
		if (!sourceClass.equals(CLASS_ZOMBIE_FIGHT_GAME))
			return false;

		// save the data to the model
		ZombieFightGame zfg = ZombieFightGame.getInstanceById(source);
		if (zfg == null)
			zfg = Mediator.getInstance().getZombieFighter().createCurrentGame().withId(source);

		switch (property) {
		case "guide":
			Guide newGuide = Guide.getInstanceById(newValue);
			if (newGuide == null && newValue != null)
				newGuide = new Guide().withId(newValue);

			Guide oldGuide = Guide.getInstanceById(oldValue);

			if (oldValue != newValue) {
				zfg.withGuide(newGuide);
			}

			break;
		case "users":
			UserAssets newUser = UserAssets.getInstanceById(newValue);
			if (newUser == null && newValue != null)
				newUser = new UserAssets().withId(newValue);

			UserAssets oldUser = UserAssets.getInstanceById(oldValue);

			if (oldValue != newValue) {
				zfg.withoutUsers(oldUser);
				zfg.withUsers(newUser);
			}

			break;
		case "fields":
			Field newField = Field.getInstanceById(newValue);
			if (newField == null && newValue != null)
				newField = new Field().withId(newValue);

			Field oldField = Field.getInstanceById(oldValue);

			if (oldValue != newValue) {
				zfg.withoutFields(oldField);
				zfg.withFields(newField);
			}

			break;

		case "winner":
			GameUser newWinner = GameUser.getInstanceById(newValue);
//			if (newWinner == null && newValue != null)
//				newWinner = new GameUser().withId(newValue);

			GameUser oldWinner = GameUser.getInstanceById(oldValue);

			if (oldValue != newValue) {
				zfg.withWinner(newWinner);
			}

			break;
		case "name":
			zfg.withName(newValue);
			break;
		case "numPlayers":
			zfg.withNumPlayers(Integer.parseInt(newValue));
			break;
		case "running":
			zfg.withRunning(Boolean.parseBoolean(newValue));
			break;
		case "testGame":
			zfg.withTestGame(Boolean.parseBoolean(newValue));
			break;
		case "nextIncome":
			zfg.withNextIncome(Double.parseDouble(newValue));
			break;
		case "speed":
			zfg.withSpeed(Double.parseDouble(newValue));

			// Change scene after Loading datamodell
			showWaitingScreen();

			break;
		default:
			break;
		}

		return true;
	}


	// ==========================================================================

	private void showWaitingScreen() {
		Mediator.getInstance().setState(ZFState.FIELD_SELECTION);
	}

	@Override
	public void removeYou() {
		super.removeYou();

		setHandlerPool(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}
}
