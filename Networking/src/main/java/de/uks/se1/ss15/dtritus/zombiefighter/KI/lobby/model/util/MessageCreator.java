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
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uniks.networkparser.json.JsonIdMap;

import java.sql.Timestamp;

public class MessageCreator extends EntityFactory {
	private final String[] properties = new String[] { Message.PROPERTY_TEXT,
			Message.PROPERTY_DATE, Message.PROPERTY_ZOMBIEFIGHTER,    Message.PROPERTY_TOUSER,
      Message.PROPERTY_FROMUSER,
   };

	@Override
	public String[] getProperties() {
		return properties;
	}

	@Override
	public Object getSendableInstance(boolean reference) {
		return new Message();
	}

	@Override
	public Object getValue(Object target, String attrName) {
		int pos = attrName.indexOf('.');
		String attribute = attrName;

		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		}

		if (Message.PROPERTY_TEXT.equalsIgnoreCase(attribute)) {
			return ((Message) target).getText();
		}

		if (Message.PROPERTY_DATE.equalsIgnoreCase(attribute)) {
			return ((Message) target).getDate();
		}

		if (Message.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attribute)) {
			return ((Message) target).getZombieFighter();
		}

      if (Message.PROPERTY_TOUSER.equalsIgnoreCase(attribute))
      {
         return ((Message) target).getToUser();
      }

      if (Message.PROPERTY_FROMUSER.equalsIgnoreCase(attribute))
      {
         return ((Message) target).getFromUser();
      }

		return null;
	}

	@Override
	public boolean setValue(Object target, String attrName, Object value,
			String type) {
		if (JsonIdMap.REMOVE.equals(type) && value != null) {
			attrName = attrName + type;
		}

		if (Message.PROPERTY_TEXT.equalsIgnoreCase(attrName)) {
			((Message) target).withText((String) value);
			return true;
		}

		if (Message.PROPERTY_DATE.equalsIgnoreCase(attrName)) {
			((Message) target).withDate((Timestamp) value);
			return true;
		}

		if (Message.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attrName)) {
			((Message) target).setZombieFighter((ZombieFighter) value);
			return true;
		}

      if (Message.PROPERTY_TOUSER.equalsIgnoreCase(attrName))
      {
         ((Message) target).setToUser((User) value);
         return true;
      }

      if (Message.PROPERTY_FROMUSER.equalsIgnoreCase(attrName))
      {
         ((Message) target).setFromUser((User) value);
         return true;
      }

		return false;
	}

	public static JsonIdMap createIdMap(String sessionID) {
		return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator
				.createIdMap(sessionID);
	}

	// ==========================================================================

	@Override
	public void removeObject(Object entity) {
		((Message) entity).removeYou();
	}
}
