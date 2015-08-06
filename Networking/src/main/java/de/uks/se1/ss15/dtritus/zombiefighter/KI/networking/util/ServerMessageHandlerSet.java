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

import org.sdmlib.models.modelsets.SDMSet;

import java.util.Collection;
import org.sdmlib.models.modelsets.booleanList;

import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.Thread;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.intList;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.BufferedReaderSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.InputStreamReaderSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.MessageHandlerPoolSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.OutputStreamWriterSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.PrintWriterSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.SocketSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadRunnerSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadSet;

import org.sdmlib.models.modelsets.StringList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.util.LinkedBlockingDequeSet;

public class ServerMessageHandlerSet extends SDMSet<ServerMessageHandler>
{

   public static final ServerMessageHandlerSet EMPTY_SET = new ServerMessageHandlerSet().withReadOnly(true);


   public ServerMessageHandlerPO hasServerMessageHandlerPO()
   {
      return new ServerMessageHandlerPO(this.toArray(new ServerMessageHandler[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.networking.ServerMessageHandler";
   }


   @SuppressWarnings("unchecked")
   public ServerMessageHandlerSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ServerMessageHandler>)value);
      }
      else if (value != null)
      {
         this.add((ServerMessageHandler) value);
      }
      
      return this;
   }
   
   public ServerMessageHandlerSet without(ServerMessageHandler value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public booleanList sendString(String message)
   {
      booleanList result = new booleanList();
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.sendString(message));
      }
      return result;
   }

   public SocketSet getServerConnection()
   {
      SocketSet result = new SocketSet();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.getServerConnection());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasServerConnection(Socket value)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (value == obj.getServerConnection())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet withServerConnection(Socket value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.setServerConnection(value);
      }
      
      return this;
   }

   public ThreadSet getThread()
   {
      ThreadSet result = new ThreadSet();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.getThread());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasThread(Thread value)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (value == obj.getThread())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet withThread(Thread value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.setThread(value);
      }
      
      return this;
   }

   public MessageHandlerPoolSet getMessageHandlerPool()
   {
      MessageHandlerPoolSet result = new MessageHandlerPoolSet();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.getMessageHandlerPool());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasMessageHandlerPool(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ServerMessageHandlerSet answer = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (neighbors.contains(obj.getMessageHandlerPool()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ServerMessageHandlerSet withMessageHandlerPool(MessageHandlerPool value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.withMessageHandlerPool(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public booleanList keepAlive(int timeoutValue)
   {
      booleanList result = new booleanList();
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.keepAlive(timeoutValue));
      }
      return result;
   }

   public intList getTimeoutValue()
   {
      intList result = new intList();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.getTimeoutValue());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasTimeoutValue(int value)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (value == obj.getTimeoutValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasTimeoutValue(int lower, int upper)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (lower <= obj.getTimeoutValue() && obj.getTimeoutValue() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet withTimeoutValue(int value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.setTimeoutValue(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public booleanList isConnected()
   {
      booleanList result = new booleanList();
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.isConnected());
      }
      return result;
   }

   public ThreadRunnerSet getThreadRunner()
   {
      ThreadRunnerSet result = new ThreadRunnerSet();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.getThreadRunner());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasThreadRunner(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ServerMessageHandlerSet answer = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (neighbors.contains(obj.getThreadRunner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ServerMessageHandlerSet withThreadRunner(ThreadRunner value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.withThreadRunner(value);
      }
      
      return this;
   }

   public ZombieFighterSet getZombieFighter()
   {
      ZombieFighterSet result = new ZombieFighterSet();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.getZombieFighter());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasZombieFighter(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ServerMessageHandlerSet answer = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (neighbors.contains(obj.getZombieFighter()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ServerMessageHandlerSet withZombieFighter(ZombieFighter value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.withZombieFighter(value);
      }
      
      return this;
   }

   public StringList getLastMessage()
   {
      StringList result = new StringList();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.getLastMessage());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasLastMessage(String value)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (value.equals(obj.getLastMessage()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasLastMessage(String lower, String upper)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (lower.compareTo(obj.getLastMessage()) <= 0 && obj.getLastMessage().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet withLastMessage(String value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.setLastMessage(value);
      }
      
      return this;
   }

   public booleanList getLastMessageHandled()
   {
      booleanList result = new booleanList();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.isLastMessageHandled());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasLastMessageHandled(boolean value)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (value == obj.isLastMessageHandled())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet withLastMessageHandled(boolean value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.setLastMessageHandled(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public booleanList send(byte[] message)
   {
      booleanList result = new booleanList();
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.send(message));
      }
      return result;
   }

   public LinkedBlockingDequeSet getSendBuffer()
   {
      LinkedBlockingDequeSet result = new LinkedBlockingDequeSet();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.getSendBuffer());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasSendBuffer(LinkedBlockingDeque<String> value)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (value == obj.getSendBuffer())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet withSendBuffer(LinkedBlockingDeque value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.setSendBuffer(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public ServerMessageHandlerSet connect(String url, int port)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.connect(url, port);
      }
      return this;
   }

   public booleanList getLastMessageShouldBeHandled()
   {
      booleanList result = new booleanList();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.isLastMessageShouldBeHandled());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasLastMessageShouldBeHandled(boolean value)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (value == obj.isLastMessageShouldBeHandled())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet withLastMessageShouldBeHandled(boolean value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.setLastMessageShouldBeHandled(value);
      }
      
      return this;
   }

   public booleanList getJsonProtocol()
   {
      booleanList result = new booleanList();
      
      for (ServerMessageHandler obj : this)
      {
         result.add(obj.isJsonProtocol());
      }
      
      return result;
   }

   public ServerMessageHandlerSet hasJsonProtocol(boolean value)
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ServerMessageHandler obj : this)
      {
         if (value == obj.isJsonProtocol())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerMessageHandlerSet withJsonProtocol(boolean value)
   {
      for (ServerMessageHandler obj : this)
      {
         obj.setJsonProtocol(value);
      }
      
      return this;
   }

}
