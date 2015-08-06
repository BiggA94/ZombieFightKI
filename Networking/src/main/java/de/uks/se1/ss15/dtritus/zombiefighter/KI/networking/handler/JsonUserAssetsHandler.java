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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Defense;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Field;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.GameUser;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.UserAssets;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.Waypoint;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uniks.networkparser.json.JsonObject;

public class JsonUserAssetsHandler extends AbstractHandler {

	private static final String CLASS_USER_ASSETS = "UserAssets";

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
		if (!sourceClass.equals(CLASS_USER_ASSETS))
			return false;

		// save the data to the model
		UserAssets ua = UserAssets.getInstanceById(source);
		if (ua == null)
			ua = new UserAssets().withId(source);

		switch (property) {
		case "game":
			ZombieFightGame newGame = ZombieFightGame.getInstanceById(newValue);
			if (newGame == null && newValue != null)
				newGame = new ZombieFightGame().withId(newValue);

			ZombieFightGame oldGame = ZombieFightGame.getInstanceById(oldValue);

			if (oldValue != newValue) {
				ua.withGame(newGame);
			}
			
			break;
		case "field":
			Field newField = Field.getInstanceById(newValue);
			if (newField == null && newValue != null)
				newField = new Field().withId(newValue);

			Field oldField = Field.getInstanceById(oldValue);

			if (oldValue != newValue) {
				ua.withField(newField);
			}
			
			break;
		case "user":
			GameUser newUser = GameUser.getInstanceById(newValue);
			if (newUser == null && newValue != null)
				newUser = new GameUser().withId(newValue);

			GameUser oldUser = GameUser.getInstanceById(oldValue);

			if (oldValue != newValue) {
				ua.withUser(newUser);
			}
			
			break;
		case "color":
			ua.withColor(newValue);
			break;
		case "lives":
			ua.withLifes(Integer.parseInt(newValue));
			break;
		case "visitor":
			ua.withVisitor(Boolean.parseBoolean(newValue));
			break;
		case "balance":
			ua.withBalance(Integer.parseInt(newValue));
			break;
		case "income":
			ua.withIncome(Integer.parseInt(newValue));
			break;
		case "defenses":
			Defense newDefense = Defense.getInstanceById(newValue);
			
			if ( newDefense == null && newValue != null ) {
				newDefense = new Defense().withId(newValue);
			}
			
			Defense oldDefense = Defense.getInstanceById(oldValue);
			
			if ( oldDefense != newDefense ) {
				ua.withDefenses(newDefense);
				//ua.withoutDefense(oldDefense);
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
