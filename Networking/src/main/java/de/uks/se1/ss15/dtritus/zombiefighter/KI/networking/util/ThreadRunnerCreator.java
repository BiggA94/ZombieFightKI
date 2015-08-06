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
   
package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.serialization.EntityFactory;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadExtension;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner;
import de.uniks.networkparser.json.JsonIdMap;

public class ThreadRunnerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ThreadRunner.PROPERTY_SERVERMESSAGEHANDLER,
      ThreadRunner.PROPERTY_THREADS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ThreadRunner();
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

      if (ThreadRunner.PROPERTY_SERVERMESSAGEHANDLER.equalsIgnoreCase(attribute))
      {
         return ((ThreadRunner) target).getServerMessageHandler();
      }

      if (ThreadRunner.PROPERTY_THREADS.equalsIgnoreCase(attribute))
      {
         return ((ThreadRunner) target).getThreads();
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

      if (ThreadRunner.PROPERTY_SERVERMESSAGEHANDLER.equalsIgnoreCase(attrName))
      {
         ((ThreadRunner) target).setServerMessageHandler((ServerMessageHandler) value);
         return true;
      }

      if (ThreadRunner.PROPERTY_THREADS.equalsIgnoreCase(attrName))
      {
         ((ThreadRunner) target).withThreads((ThreadExtension) value);
         return true;
      }
      
      if ((ThreadRunner.PROPERTY_THREADS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ThreadRunner) target).withoutThreads((ThreadExtension) value);
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
      ((ThreadRunner) entity).removeYou();
   }
}
