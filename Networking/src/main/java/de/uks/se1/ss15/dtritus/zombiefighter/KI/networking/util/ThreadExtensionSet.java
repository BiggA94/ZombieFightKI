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

import org.sdmlib.models.modelsets.SDMSet;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadExtension;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadRunnerSet;

import java.util.Collection;
import org.sdmlib.models.modelsets.ObjectSet;

import java.lang.Runnable;

public class ThreadExtensionSet extends SDMSet<ThreadExtension>
{

   public static final ThreadExtensionSet EMPTY_SET = new ThreadExtensionSet().withReadOnly(true);


   public ThreadExtensionPO hasThreadExtensionPO()
   {
      return new ThreadExtensionPO(this.toArray(new ThreadExtension[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.networking.ThreadExtension";
   }


   @SuppressWarnings("unchecked")
   public ThreadExtensionSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ThreadExtension>)value);
      }
      else if (value != null)
      {
         this.add((ThreadExtension) value);
      }
      
      return this;
   }
   
   public ThreadExtensionSet without(ThreadExtension value)
   {
      this.remove(value);
      return this;
   }

   public ThreadRunnerSet getThreadRunner()
   {
      ThreadRunnerSet result = new ThreadRunnerSet();
      
      for (ThreadExtension obj : this)
      {
         result.add(obj.getThreadRunner());
      }
      
      return result;
   }

   public ThreadExtensionSet hasThreadRunner(Object value)
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
      
      ThreadExtensionSet answer = new ThreadExtensionSet();
      
      for (ThreadExtension obj : this)
      {
         if (neighbors.contains(obj.getThreadRunner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ThreadExtensionSet withThreadRunner(ThreadRunner value)
   {
      for (ThreadExtension obj : this)
      {
         obj.withThreadRunner(value);
      }
      
      return this;
   }

   public RunnableSet getRunnableContent()
   {
      RunnableSet result = new RunnableSet();
      
      for (ThreadExtension obj : this)
      {
         result.add(obj.getRunnableContent());
      }
      
      return result;
   }

   public ThreadExtensionSet hasRunnableContent(Runnable value)
   {
      ThreadExtensionSet result = new ThreadExtensionSet();
      
      for (ThreadExtension obj : this)
      {
         if (value == obj.getRunnableContent())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ThreadExtensionSet withRunnableContent(Runnable value)
   {
      for (ThreadExtension obj : this)
      {
         obj.setRunnableContent(value);
      }
      
      return this;
   }

}
