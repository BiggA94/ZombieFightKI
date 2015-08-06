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
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uniks.networkparser.json.JsonIdMap;

public class GameCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Game.PROPERTY_MAP,
      Game.PROPERTY_USER,
      Game.PROPERTY_ZOMBIEFIGHTER,
      Game.PROPERTY_NAME,
      Game.PROPERTY_EVENTS,
      Game.PROPERTY_STATUS,
      Game.PROPERTY_TESTGAME,
      Game.PROPERTY_TABLEROW,
      Game.PROPERTY_MAXPLAYERS,
      Game.PROPERTY_CURRENTPLAYERS,
      Game.PROPERTY_FILTERFLAG,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Game();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Game.PROPERTY_MAP.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getMap();
      }

      if (Game.PROPERTY_USER.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getUser();
      }

      if (Game.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getZombieFighter();
      }

      if (Game.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getName();
      }

      if (Game.PROPERTY_EVENTS.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getEvents();
      }

      if (Game.PROPERTY_STATUS.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getStatus();
      }

      if (Game.PROPERTY_TESTGAME.equalsIgnoreCase(attribute))
      {
         return ((Game) target).isTestgame();
      }

      if (Game.PROPERTY_MAXPLAYERS.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getMaxPlayers();
      }

      if (Game.PROPERTY_CURRENTPLAYERS.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getCurrentPlayers();
      }

      if (Game.PROPERTY_FILTERFLAG.equalsIgnoreCase(attribute))
      {
         return ((Game) target).isFilterFlag();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Game.PROPERTY_MAP.equalsIgnoreCase(attrName))
      {
         ((Game) target).setMap((Map) value);
         return true;
      }

      if (Game.PROPERTY_USER.equalsIgnoreCase(attrName))
      {
         ((Game) target).withUser((User) value);
         return true;
      }
      
      if ((Game.PROPERTY_USER + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Game) target).withoutUser((User) value);
         return true;
      }

      if (Game.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attrName))
      {
         ((Game) target).setZombieFighter((ZombieFighter) value);
         return true;
      }

      if (Game.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Game) target).withName((String) value);
         return true;
      }

      if (Game.PROPERTY_EVENTS.equalsIgnoreCase(attrName))
      {
         ((Game) target).withEvents(Integer.parseInt(value.toString()));
         return true;
      }

      if (Game.PROPERTY_STATUS.equalsIgnoreCase(attrName))
      {
         ((Game) target).withStatus((String) value);
         return true;
      }

      if (Game.PROPERTY_TESTGAME.equalsIgnoreCase(attrName))
      {
         ((Game) target).withTestgame((Boolean) value);
         return true;
      }

      if (Game.PROPERTY_MAXPLAYERS.equalsIgnoreCase(attrName))
      {
         ((Game) target).withMaxPlayers(Integer.parseInt(value.toString()));
         return true;
      }

      if (Game.PROPERTY_CURRENTPLAYERS.equalsIgnoreCase(attrName))
      {
         ((Game) target).withCurrentPlayers(Integer.parseInt(value.toString()));
         return true;
      }

      if (Game.PROPERTY_FILTERFLAG.equalsIgnoreCase(attrName))
      {
         ((Game) target).withFilterFlag((Boolean) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Game) entity).removeYou();
   }
}
