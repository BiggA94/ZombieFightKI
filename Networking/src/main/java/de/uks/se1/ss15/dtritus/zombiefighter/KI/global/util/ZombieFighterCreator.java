/*
   Copyright (c) 2015 Major 
   
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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util;

import org.sdmlib.serialization.EntityFactory;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uniks.networkparser.json.JsonIdMap;

public class ZombieFighterCreator extends EntityFactory {
	private final String[] properties = new String[] { ZombieFighter.PROPERTY_SERVERMESSAGEHANDLER,
			ZombieFighter.PROPERTY_MESSAGEHANDLERPOOL, ZombieFighter.PROPERTY_GAMES, ZombieFighter.PROPERTY_MAPS,
			ZombieFighter.PROPERTY_USERS, ZombieFighter.PROPERTY_TEAMS, ZombieFighter.PROPERTY_MESSAGES,
			ZombieFighter.PROPERTY_CURRENTUSER, ZombieFighter.PROPERTY_CURRENTGAME,
			ZombieFighter.PROPERTY_GAMESCENESCALING, };

	@Override
	public String[] getProperties() {
		return properties;
	}

	@Override
	public Object getSendableInstance(boolean reference) {
		return null;
		// return new ZombieFighter();
	}

	@Override
	public Object getValue(Object target, String attrName) {
		int pos = attrName.indexOf('.');
		String attribute = attrName;

		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		}

		if (ZombieFighter.PROPERTY_SERVERMESSAGEHANDLER.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getServerMessageHandler();
		}

		if (ZombieFighter.PROPERTY_MESSAGEHANDLERPOOL.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getMessageHandlerPool();
		}

		if (ZombieFighter.PROPERTY_GAMES.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getGames();
		}

		if (ZombieFighter.PROPERTY_MAPS.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getMaps();
		}

		if (ZombieFighter.PROPERTY_USERS.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getUsers();
		}

		if (ZombieFighter.PROPERTY_TEAMS.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getTeams();
		}

		if (ZombieFighter.PROPERTY_MESSAGES.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getMessages();
		}

		if (ZombieFighter.PROPERTY_CURRENTUSER.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getCurrentUser();
		}

		if (ZombieFighter.PROPERTY_CURRENTGAME.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getCurrentGame();
		}

		if (ZombieFighter.PROPERTY_GAMESCENESCALING.equalsIgnoreCase(attribute)) {
			return ((ZombieFighter) target).getGameSceneScaling();
		}

		return null;
	}

	@Override
	public boolean setValue(Object target, String attrName, Object value, String type) {
		if (JsonIdMap.REMOVE.equals(type) && value != null) {
			attrName = attrName + type;
		}

		if (ZombieFighter.PROPERTY_SERVERMESSAGEHANDLER.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).setServerMessageHandler((ServerMessageHandler) value);
			return true;
		}

		if (ZombieFighter.PROPERTY_MESSAGEHANDLERPOOL.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).setMessageHandlerPool((MessageHandlerPool) value);
			return true;
		}

		if (ZombieFighter.PROPERTY_GAMES.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withGames((Game) value);
			return true;
		}

		if ((ZombieFighter.PROPERTY_GAMES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withoutGames((Game) value);
			return true;
		}

		if (ZombieFighter.PROPERTY_MAPS.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withMaps((Map) value);
			return true;
		}

		if ((ZombieFighter.PROPERTY_MAPS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withoutMaps((Map) value);
			return true;
		}

		if (ZombieFighter.PROPERTY_USERS.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withUsers((User) value);
			return true;
		}

		if ((ZombieFighter.PROPERTY_USERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withoutUsers((User) value);
			return true;
		}

		if (ZombieFighter.PROPERTY_TEAMS.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withTeams((Team) value);
			return true;
		}

		if ((ZombieFighter.PROPERTY_TEAMS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withoutTeams((Team) value);
			return true;
		}

		if (ZombieFighter.PROPERTY_MESSAGES.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withMessages((Message) value);
			return true;
		}

		if ((ZombieFighter.PROPERTY_MESSAGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withoutMessages((Message) value);
			return true;
		}

		if (ZombieFighter.PROPERTY_CURRENTUSER.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).setCurrentUser((User) value);
			return true;
		}

		if (ZombieFighter.PROPERTY_CURRENTGAME.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withCurrentGame((ZombieFightGame) value);
			return true;
		}

		if (ZombieFighter.PROPERTY_GAMESCENESCALING.equalsIgnoreCase(attrName)) {
			((ZombieFighter) target).withGameSceneScaling(Double.parseDouble(value.toString()));
			return true;
		}

		return false;
	}

	public static JsonIdMap createIdMap(String sessionID) {
		return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap(sessionID);
	}

	// ==========================================================================

	@Override
	public void removeObject(Object entity) {
		((ZombieFighter) entity).removeYou();
	}
}
