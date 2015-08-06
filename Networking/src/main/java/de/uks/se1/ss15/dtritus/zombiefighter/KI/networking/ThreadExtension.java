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
   
package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking;

import java.lang.Thread;
import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.lang.Runnable;

/**
 * This Class extends Thread
 * @author Alexander
 *
 */
public class ThreadExtension extends Thread implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setThreadRunner(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * ThreadExtension ----------------------------------- ThreadRunner
    *              Threads                   ThreadRunner
    * </pre>
    */
   
   public static final String PROPERTY_THREADRUNNER = "ThreadRunner";

   private ThreadRunner ThreadRunner = null;

   public ThreadRunner getThreadRunner()
   {
      return this.ThreadRunner;
   }

   public boolean setThreadRunner(ThreadRunner value)
   {
      boolean changed = false;
      
      if (this.ThreadRunner != value)
      {
         ThreadRunner oldValue = this.ThreadRunner;
         
         if (this.ThreadRunner != null)
         {
            this.ThreadRunner = null;
            oldValue.withoutThreads(this);
         }
         
         this.ThreadRunner = value;
         
         if (value != null)
         {
            value.withThreads(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_THREADRUNNER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public ThreadExtension withThreadRunner(ThreadRunner value)
   {
      setThreadRunner(value);
      return this;
   } 

   public ThreadRunner createThreadRunner()
   {
      ThreadRunner value = new ThreadRunner();
      withThreadRunner(value);
      return value;
   } 
   
   public ThreadExtension(Runnable runnable){
	   super(runnable);
	   this.setRunnableContent(runnable);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_RUNNABLECONTENT = "runnableContent";
   
   private Runnable runnableContent;

   public Runnable getRunnableContent()
   {
      return this.runnableContent;
   }
   
   public void setRunnableContent(Runnable value)
   {
      if (this.runnableContent != value)
      {
         Runnable oldValue = this.runnableContent;
         this.runnableContent = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_RUNNABLECONTENT, oldValue, value);
      }
   }
   
   public ThreadExtension withRunnableContent(Runnable value)
   {
      setRunnableContent(value);
      return this;
   } 
}
