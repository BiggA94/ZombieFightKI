/*
   Copyright (c) 2015 Alexander 
   
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
   
package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import java.util.concurrent.LinkedBlockingQueue;

import org.sdmlib.serialization.EntityFactory;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uniks.networkparser.json.JsonIdMap;

public class MessageHandlerPoolCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MessageHandlerPool.PROPERTY_SERVERMESSAGEHANDLER,
      MessageHandlerPool.PROPERTY_HANDLER,
      MessageHandlerPool.PROPERTY_INPUTBUFFER,
      MessageHandlerPool.PROPERTY_ZOMBIEFIGHTER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MessageHandlerPool();
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

      if (MessageHandlerPool.PROPERTY_SERVERMESSAGEHANDLER.equalsIgnoreCase(attribute))
      {
         return ((MessageHandlerPool) target).getServerMessageHandler();
      }

      if (MessageHandlerPool.PROPERTY_HANDLER.equalsIgnoreCase(attribute))
      {
         return ((MessageHandlerPool) target).getHandler();
      }

      if (MessageHandlerPool.PROPERTY_INPUTBUFFER.equalsIgnoreCase(attribute))
      {
         return ((MessageHandlerPool) target).getInputBuffer();
      }

      if (MessageHandlerPool.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attribute))
      {
         return ((MessageHandlerPool) target).getZombieFighter();
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

      if (MessageHandlerPool.PROPERTY_SERVERMESSAGEHANDLER.equalsIgnoreCase(attrName))
      {
         ((MessageHandlerPool) target).setServerMessageHandler((ServerMessageHandler) value);
         return true;
      }

      if (MessageHandlerPool.PROPERTY_HANDLER.equalsIgnoreCase(attrName))
      {
         ((MessageHandlerPool) target).withHandler((AbstractHandler) value);
         return true;
      }
      
      if ((MessageHandlerPool.PROPERTY_HANDLER + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((MessageHandlerPool) target).withoutHandler((AbstractHandler) value);
         return true;
      }

      if (MessageHandlerPool.PROPERTY_INPUTBUFFER.equalsIgnoreCase(attrName))
      {
         ((MessageHandlerPool) target).withInputBuffer((LinkedBlockingQueue<String>) value);
         return true;
      }

      if (MessageHandlerPool.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attrName))
      {
         ((MessageHandlerPool) target).setZombieFighter((ZombieFighter) value);
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
      ((MessageHandlerPool) entity).removeYou();
   }
}
