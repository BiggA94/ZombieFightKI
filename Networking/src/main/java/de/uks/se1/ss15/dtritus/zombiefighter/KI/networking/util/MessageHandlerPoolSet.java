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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.util.AbstractHandlerSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ServerMessageHandlerSet;

import org.sdmlib.models.modelsets.ObjectSet;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageHandlerPoolSet extends SDMSet<MessageHandlerPool>
{

   public static final MessageHandlerPoolSet EMPTY_SET = new MessageHandlerPoolSet().withReadOnly(true);


   public MessageHandlerPoolPO hasMessageHandlerPoolPO()
   {
      return new MessageHandlerPoolPO(this.toArray(new MessageHandlerPool[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.networking.MessageHandlerPool";
   }


   @SuppressWarnings("unchecked")
   public MessageHandlerPoolSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MessageHandlerPool>)value);
      }
      else if (value != null)
      {
         this.add((MessageHandlerPool) value);
      }
      
      return this;
   }
   
   public MessageHandlerPoolSet without(MessageHandlerPool value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public booleanList handle(byte[] message)
   {
      booleanList result = new booleanList();
      for (MessageHandlerPool obj : this)
      {
         result.add(obj.handle(message));
      }
      return result;
   }

   public ServerMessageHandlerSet getServerMessageHandler()
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (MessageHandlerPool obj : this)
      {
         result.add(obj.getServerMessageHandler());
      }
      
      return result;
   }

   public MessageHandlerPoolSet hasServerMessageHandler(Object value)
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
      
      MessageHandlerPoolSet answer = new MessageHandlerPoolSet();
      
      for (MessageHandlerPool obj : this)
      {
         if (neighbors.contains(obj.getServerMessageHandler()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MessageHandlerPoolSet withServerMessageHandler(ServerMessageHandler value)
   {
      for (MessageHandlerPool obj : this)
      {
         obj.withServerMessageHandler(value);
      }
      
      return this;
   }

   public AbstractHandlerSet getHandler()
   {
      AbstractHandlerSet result = new AbstractHandlerSet();
      
      for (MessageHandlerPool obj : this)
      {
         result.addAll(obj.getHandler());
      }
      
      return result;
   }

   public MessageHandlerPoolSet hasHandler(Object value)
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
      
      MessageHandlerPoolSet answer = new MessageHandlerPoolSet();
      
      for (MessageHandlerPool obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getHandler()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MessageHandlerPoolSet withHandler(AbstractHandler value)
   {
      for (MessageHandlerPool obj : this)
      {
         obj.withHandler(value);
      }
      
      return this;
   }

   public MessageHandlerPoolSet withoutHandler(AbstractHandler value)
   {
      for (MessageHandlerPool obj : this)
      {
         obj.withoutHandler(value);
      }
      
      return this;
   }

   public LinkedList<String> getInputBuffer()
   {
      LinkedList<String> result = new LinkedList<String>();
      
      for (MessageHandlerPool obj : this)
      {
         result.addAll(obj.getInputBuffer());
      }
      
      return result;
   }

   public MessageHandlerPoolSet hasInputBuffer(LinkedBlockingQueue<String> value)
   {
      MessageHandlerPoolSet result = new MessageHandlerPoolSet();
      
      for (MessageHandlerPool obj : this)
      {
         if (value == obj.getInputBuffer())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MessageHandlerPoolSet withInputBuffer(LinkedBlockingQueue<String> value)
   {
      for (MessageHandlerPool obj : this)
      {
         obj.setInputBuffer(value);
      }
      
      return this;
   }
   public ZombieFighterSet getZombieFighter()
   {
      ZombieFighterSet result = new ZombieFighterSet();
      
      for (MessageHandlerPool obj : this)
      {
         result.add(obj.getZombieFighter());
      }
      
      return result;
   }

   public MessageHandlerPoolSet hasZombieFighter(Object value)
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
      
      MessageHandlerPoolSet answer = new MessageHandlerPoolSet();
      
      for (MessageHandlerPool obj : this)
      {
         if (neighbors.contains(obj.getZombieFighter()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MessageHandlerPoolSet withZombieFighter(ZombieFighter value)
   {
      for (MessageHandlerPool obj : this)
      {
         obj.withZombieFighter(value);
      }
      
      return this;
   }
}
