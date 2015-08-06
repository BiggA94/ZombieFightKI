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

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadObject;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadSet;

import java.util.Collection;
import java.lang.Thread;

public class ThreadObjectSet extends SDMSet<ThreadObject>
{

   public static final ThreadObjectSet EMPTY_SET = new ThreadObjectSet().withReadOnly(true);


   public ThreadObjectPO hasThreadObjectPO()
   {
      return new ThreadObjectPO(this.toArray(new ThreadObject[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.networking.ThreadObject";
   }


   @SuppressWarnings("unchecked")
   public ThreadObjectSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ThreadObject>)value);
      }
      else if (value != null)
      {
         this.add((ThreadObject) value);
      }
      
      return this;
   }
   
   public ThreadObjectSet without(ThreadObject value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public ThreadObjectSet interrupt()
   {
      for (ThreadObject obj : this)
      {
         obj.interrupt();
      }
      return this;
   }

   
   //==========================================================================
   
   public ThreadObjectSet start()
   {
      for (ThreadObject obj : this)
      {
         obj.start();
      }
      return this;
   }

   public ThreadSet getThread()
   {
      ThreadSet result = new ThreadSet();
      
      for (ThreadObject obj : this)
      {
         result.add(obj.getThread());
      }
      
      return result;
   }

   public ThreadObjectSet hasThread(Thread value)
   {
      ThreadObjectSet result = new ThreadObjectSet();
      
      for (ThreadObject obj : this)
      {
         if (value == obj.getThread())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ThreadObjectSet withThread(Thread value)
   {
      for (ThreadObject obj : this)
      {
         obj.setThread(value);
      }
      
      return this;
   }

}
