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
import de.uniks.networkparser.json.JsonIdMap;

public class MapCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Map.PROPERTY_NAME,
      Map.PROPERTY_GAMES,
      Map.PROPERTY_ZOMBIEFIGHTER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Map();
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

      if (Map.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Map) target).getName();
      }

      if (Map.PROPERTY_GAMES.equalsIgnoreCase(attribute))
      {
         return ((Map) target).getGames();
      }

      if (Map.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attribute))
      {
         return ((Map) target).getZombieFighter();
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

      if (Map.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Map) target).withName((String) value);
         return true;
      }

      if (Map.PROPERTY_GAMES.equalsIgnoreCase(attrName))
      {
         ((Map) target).withGames((Game) value);
         return true;
      }
      
      if ((Map.PROPERTY_GAMES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Map) target).withoutGames((Game) value);
         return true;
      }

      if (Map.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attrName))
      {
         ((Map) target).setZombieFighter((ZombieFighter) value);
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
      ((Map) entity).removeYou();
   }
}
