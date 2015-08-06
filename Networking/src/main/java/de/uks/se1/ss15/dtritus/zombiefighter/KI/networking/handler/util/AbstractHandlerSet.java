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
   
package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.util;

import org.sdmlib.models.modelsets.SDMSet;

import java.util.Collection;
import org.sdmlib.models.modelsets.booleanList;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.MessageHandlerPoolSet;

import org.sdmlib.models.modelsets.ObjectSet;

public class AbstractHandlerSet extends SDMSet<AbstractHandler>
{

   public static final AbstractHandlerSet EMPTY_SET = new AbstractHandlerSet().withReadOnly(true);


   public AbstractHandlerPO hasAbstractHandlerPO()
   {
      return new AbstractHandlerPO(this.toArray(new AbstractHandler[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.networking.handler.AbstractHandler";
   }


   @SuppressWarnings("unchecked")
   public AbstractHandlerSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<AbstractHandler>)value);
      }
      else if (value != null)
      {
         this.add((AbstractHandler) value);
      }
      
      return this;
   }
   
   public AbstractHandlerSet without(AbstractHandler value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public booleanList handle(String... messages)
   {
      booleanList result = new booleanList();
      for (AbstractHandler obj : this)
      {
         result.add(obj.handle(messages));
      }
      return result;
   }

   public MessageHandlerPoolSet getHandlerPool()
   {
      MessageHandlerPoolSet result = new MessageHandlerPoolSet();
      
      for (AbstractHandler obj : this)
      {
         result.add(obj.getHandlerPool());
      }
      
      return result;
   }

   public AbstractHandlerSet hasHandlerPool(Object value)
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
      
      AbstractHandlerSet answer = new AbstractHandlerSet();
      
      for (AbstractHandler obj : this)
      {
         if (neighbors.contains(obj.getHandlerPool()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public AbstractHandlerSet withHandlerPool(MessageHandlerPool value)
   {
      for (AbstractHandler obj : this)
      {
         obj.withHandlerPool(value);
      }
      
      return this;
   }

}
