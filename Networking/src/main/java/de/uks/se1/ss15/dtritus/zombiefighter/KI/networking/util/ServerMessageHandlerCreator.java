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

import org.sdmlib.serialization.EntityFactory;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadObject;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner;
import de.uniks.networkparser.json.JsonIdMap;

import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.Thread;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ServerMessageHandlerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ServerMessageHandler.PROPERTY_SERVERCONNECTION,
      ThreadObject.PROPERTY_THREAD,
      ServerMessageHandler.PROPERTY_MESSAGEHANDLERPOOL,
      ServerMessageHandler.PROPERTY_TIMEOUTVALUE,
      ServerMessageHandler.PROPERTY_THREADRUNNER,
      ServerMessageHandler.PROPERTY_ZOMBIEFIGHTER,
      ServerMessageHandler.PROPERTY_LASTMESSAGE,
      ServerMessageHandler.PROPERTY_LASTMESSAGEHANDLED,
      ServerMessageHandler.PROPERTY_SENDBUFFER,
      ServerMessageHandler.PROPERTY_LASTMESSAGESHOULDBEHANDLED,
      ServerMessageHandler.PROPERTY_JSONPROTOCOL,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ServerMessageHandler();
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

      if (ServerMessageHandler.PROPERTY_SERVERCONNECTION.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).getServerConnection();
      }

      if (ThreadObject.PROPERTY_THREAD.equalsIgnoreCase(attribute))
      {
         return ((ThreadObject) target).getThread();
      }

      if (ServerMessageHandler.PROPERTY_MESSAGEHANDLERPOOL.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).getMessageHandlerPool();
      }

      if (ServerMessageHandler.PROPERTY_TIMEOUTVALUE.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).getTimeoutValue();
      }

      if (ServerMessageHandler.PROPERTY_THREADRUNNER.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).getThreadRunner();
      }

      if (ServerMessageHandler.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).getZombieFighter();
      }

      if (ServerMessageHandler.PROPERTY_LASTMESSAGE.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).getLastMessage();
      }

      if (ServerMessageHandler.PROPERTY_LASTMESSAGEHANDLED.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).isLastMessageHandled();
      }

      if (ServerMessageHandler.PROPERTY_SENDBUFFER.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).getSendBuffer();
      }

      if (ServerMessageHandler.PROPERTY_LASTMESSAGESHOULDBEHANDLED.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).isLastMessageShouldBeHandled();
      }

      if (ServerMessageHandler.PROPERTY_JSONPROTOCOL.equalsIgnoreCase(attribute))
      {
         return ((ServerMessageHandler) target).isJsonProtocol();
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

      if (ServerMessageHandler.PROPERTY_SERVERCONNECTION.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).withServerConnection((Socket) value);
         return true;
      }

      if (ThreadObject.PROPERTY_THREAD.equalsIgnoreCase(attrName))
      {
         ((ThreadObject) target).withThread((Thread) value);
         return true;
      }

      if (ServerMessageHandler.PROPERTY_MESSAGEHANDLERPOOL.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).setMessageHandlerPool((MessageHandlerPool) value);
         return true;
      }

      if (ServerMessageHandler.PROPERTY_TIMEOUTVALUE.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).withTimeoutValue(Integer.parseInt(value.toString()));
         return true;
      }

      if (ServerMessageHandler.PROPERTY_THREADRUNNER.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).setThreadRunner((ThreadRunner) value);
         return true;
      }

      if (ServerMessageHandler.PROPERTY_ZOMBIEFIGHTER.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).setZombieFighter((ZombieFighter) value);
         return true;
      }

      if (ServerMessageHandler.PROPERTY_LASTMESSAGE.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).withLastMessage((String) value);
         return true;
      }

      if (ServerMessageHandler.PROPERTY_LASTMESSAGEHANDLED.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).withLastMessageHandled((Boolean) value);
         return true;
      }

      if (ServerMessageHandler.PROPERTY_SENDBUFFER.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).withSendBuffer((LinkedBlockingDeque<String>) value);
         return true;
      }

      if (ServerMessageHandler.PROPERTY_LASTMESSAGESHOULDBEHANDLED.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).withLastMessageShouldBeHandled((Boolean) value);
         return true;
      }

      if (ServerMessageHandler.PROPERTY_JSONPROTOCOL.equalsIgnoreCase(attrName))
      {
         ((ServerMessageHandler) target).withJsonProtocol((Boolean) value);
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
      ((ServerMessageHandler) entity).removeYou();
   }
}
