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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public abstract class AbstractHandler implements PropertyChangeInterface {

	// identifier for JSON messages,
	// provided every JSON message starts with a time stamp
	public static final String JSON_IDENTIFIER_TIMESTAMP = "{\"@ts\":";

	// ==========================================================================

	// decides if a string needs to be handled or a JSON message
	public boolean handle(String... messages) {
		// String
		// provided that every JSON message starts with a time stamp
		if (!messages[0].startsWith(JSON_IDENTIFIER_TIMESTAMP)) {
			return this.handleString(messages);
		}
		// JSON
		else {
			return this.handleJSON(messages);
		}
	}

	// two different handle methods to either handle a string or a JSON message
	public abstract boolean handleString(String... messages);

	public abstract boolean handleJSON(String... messages);


   
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
      setHandlerPool(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * AbstractHandler ----------------------------------- MessageHandlerPool
    *              Handler                   HandlerPool
    * </pre>
    */
   
   public static final String PROPERTY_HANDLERPOOL = "HandlerPool";

   private MessageHandlerPool HandlerPool = null;

   public MessageHandlerPool getHandlerPool()
   {
      return this.HandlerPool;
   }

   public boolean setHandlerPool(MessageHandlerPool value)
   {
      boolean changed = false;
      
      if (this.HandlerPool != value)
      {
         MessageHandlerPool oldValue = this.HandlerPool;
         
         if (this.HandlerPool != null)
         {
            this.HandlerPool = null;
            oldValue.withoutHandler(this);
         }
         
         this.HandlerPool = value;
         
         if (value != null)
         {
            value.withHandler(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HANDLERPOOL, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public AbstractHandler withHandlerPool(MessageHandlerPool value)
   {
      setHandlerPool(value);
      return this;
   } 

   public MessageHandlerPool createHandlerPool()
   {
      MessageHandlerPool value = new MessageHandlerPool();
      withHandlerPool(value);
      return value;
   } 
}
