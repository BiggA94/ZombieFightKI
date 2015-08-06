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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.serialization.EntityFactory;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uniks.networkparser.json.JsonIdMap;

public class UserCreator extends EntityFactory {
	private final String[] properties = new String[] { User.PROPERTY_NICK, User.PROPERTY_EMAIL, User.PROPERTY_ROLE,
			User.PROPERTY_STATE, User.PROPERTY_GAME, User.PROPERTY_TEAM, User.PROPERTY_ZOMBIEFIGHTER,
			User.PROPERTY_MESSAGESINBOX, User.PROPERTY_MESSAGESOUTBOX, User.PROPERTY_UNREADMESSAGES,
			User.PROPERTY_INGAME, };

	@Override
	public String[] getProperties() {
		return properties;
	}

	@Override
	public Object getSendableInstance(boolean reference) {
		return new User();
	}

	@Override
	public Object getValue(Object target, String attrName) {
		int pos = attrName.indexOf('.');
		String attribute = attrName;

		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		}

		if (User.PROPERTY_NICK.equalsIgnoreCase(attribute)) {
			return ((User) target).getNick();
		}

		if (User.PROPERTY_EMAIL.equalsIgnoreCase(attribute)) {
			return ((User) target).getEmail();
		}

		if (User.PROPERTY_ROLE.equalsIgnoreCase(attribute)) {
			return ((User) target).getRole();
		}

		if (User.PROPERTY_STATE.equalsIgnoreCase(attribute)) {
			return ((User) target).getState();
		}

		if (User.PROPERTY_GAME.equalsIgnoreCase(attribute)) {
			return ((User) target).getGame();
		}

		if (User.PROPERTY_TEAM.equalsIgnoreCase(attribute)) {
			return ((User) target).getTeam();
		}

		if (User.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attribute)) {
			return ((User) target).getZombieFighter();
		}

		if (User.PROPERTY_MESSAGESINBOX.equalsIgnoreCase(attribute)) {
			return ((User) target).getMessagesInbox();
		}

		if (User.PROPERTY_MESSAGESOUTBOX.equalsIgnoreCase(attribute)) {
			return ((User) target).getMessagesOutbox();
		}

		if (User.PROPERTY_UNREADMESSAGES.equalsIgnoreCase(attribute)) {
			return ((User) target).isUnreadMessages();
		}

		if (User.PROPERTY_INGAME.equalsIgnoreCase(attribute)) {
			return ((User) target).isIngame();
		}

		return null;
	}

	@Override
	public boolean setValue(Object target, String attrName, Object value, String type) {
		if (JsonIdMap.REMOVE.equals(type) && value != null) {
			attrName = attrName + type;
		}

		if (User.PROPERTY_NICK.equalsIgnoreCase(attrName)) {
			((User) target).withNick((String) value);
			return true;
		}

		if (User.PROPERTY_EMAIL.equalsIgnoreCase(attrName)) {
			((User) target).withEmail((String) value);
			return true;
		}

		if (User.PROPERTY_ROLE.equalsIgnoreCase(attrName)) {
			((User) target).withRole((String) value);
			return true;
		}

		if (User.PROPERTY_STATE.equalsIgnoreCase(attrName)) {
			((User) target).withState((String) value);
			return true;
		}

		if (User.PROPERTY_GAME.equalsIgnoreCase(attrName)) {
			((User) target).setGame((Game) value);
			return true;
		}

		if (User.PROPERTY_TEAM.equalsIgnoreCase(attrName)) {
			((User) target).setTeam((Team) value);
			return true;
		}

		if (User.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attrName)) {
			((User) target).setZombieFighter((ZombieFighter) value);
			return true;
		}

		if (User.PROPERTY_MESSAGESINBOX.equalsIgnoreCase(attrName)) {
			((User) target).withMessagesInbox((Message) value);
			return true;
		}

		if ((User.PROPERTY_MESSAGESINBOX + JsonIdMap.REMOVE).equalsIgnoreCase(attrName)) {
			((User) target).withoutMessagesInbox((Message) value);
			return true;
		}

		if (User.PROPERTY_MESSAGESOUTBOX.equalsIgnoreCase(attrName)) {
			((User) target).withMessagesOutbox((Message) value);
			return true;
		}

		if ((User.PROPERTY_MESSAGESOUTBOX + JsonIdMap.REMOVE).equalsIgnoreCase(attrName)) {
			((User) target).withoutMessagesOutbox((Message) value);
			return true;
		}

		if (User.PROPERTY_UNREADMESSAGES.equalsIgnoreCase(attrName)) {
			((User) target).withUnreadMessages((Boolean) value);
			return true;
		}

		if (User.PROPERTY_INGAME.equalsIgnoreCase(attrName)) {
			((User) target).withIngame((Boolean) value);
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
		((User) entity).removeYou();
	}
}
