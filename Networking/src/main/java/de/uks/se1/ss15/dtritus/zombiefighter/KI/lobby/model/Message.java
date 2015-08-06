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
   
package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import java.sql.Timestamp;

public class Message implements PropertyChangeInterface
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
      setZombieFighter(null);
      setToUser(null);
      setFromUser(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_TEXT = "text";
   
   private String text;

   public String getText()
   {
      return this.text;
   }
   
   public void setText(String value)
   {
      if ( ! StrUtil.stringEquals(this.text, value))
      {
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue, value);
      }
   }
   
   public Message withText(String value)
   {
      setText(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getText());
      result.append(" ").append(this.getDate());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_DATE = "date";
   
   private Timestamp date;

   public Timestamp getDate()
   {
      return this.date;
   }
   
   public void setDate(Timestamp value)
   {
      if (this.date != value)
      {
         Timestamp oldValue = this.date;
         this.date = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DATE, oldValue, value);
      }
   }
   
   public Message withDate(Timestamp value)
   {
      setDate(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Message ----------------------------------- ZombieFighter
    *              messages                   zombieFighter
    * </pre>
    */
   
   public static final String PROPERTY_ZOMBIEFIGHTER = "zombieFighter";

   private ZombieFighter zombieFighter = null;

   public ZombieFighter getZombieFighter()
   {
      return this.zombieFighter;
   }

   public boolean setZombieFighter(ZombieFighter value)
   {
      boolean changed = false;
      
      if (this.zombieFighter != value)
      {
         ZombieFighter oldValue = this.zombieFighter;
         
         if (this.zombieFighter != null)
         {
            this.zombieFighter = null;
            oldValue.withoutMessages(this);
         }
         
         this.zombieFighter = value;
         
         if (value != null)
         {
            value.withMessages(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ZOMBIEFIGHTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Message withZombieFighter(ZombieFighter value)
   {
      setZombieFighter(value);
      return this;
   } 

   public ZombieFighter createZombieFighter()
   {
      //ZombieFighter value = new ZombieFighter();
      //withZombieFighter(value);
      return null;
   } 

   /********************************************************************
    * <pre>
    *              many                       one
    * Message ----------------------------------- User
    *              messagesInbox                   toUser
    * </pre>
    */
   
   public static final String PROPERTY_TOUSER = "toUser";

   private User toUser = null;

   public User getToUser()
   {
      return this.toUser;
   }

   public boolean setToUser(User value)
   {
      boolean changed = false;
      
      if (this.toUser != value)
      {
         User oldValue = this.toUser;
         
         if (this.toUser != null)
         {
            this.toUser = null;
            oldValue.withoutMessagesInbox(this);
         }
         
         this.toUser = value;
         
         if (value != null)
         {
            value.withMessagesInbox(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TOUSER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Message withToUser(User value)
   {
      setToUser(value);
      return this;
   } 

   public User createToUser()
   {
      User value = new User();
      withToUser(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Message ----------------------------------- User
    *              messagesOutbox                   fromUser
    * </pre>
    */
   
   public static final String PROPERTY_FROMUSER = "fromUser";

   private User fromUser = null;

   public User getFromUser()
   {
      return this.fromUser;
   }

   public boolean setFromUser(User value)
   {
      boolean changed = false;
      
      if (this.fromUser != value)
      {
         User oldValue = this.fromUser;
         
         if (this.fromUser != null)
         {
            this.fromUser = null;
            oldValue.withoutMessagesOutbox(this);
         }
         
         this.fromUser = value;
         
         if (value != null)
         {
            value.withMessagesOutbox(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FROMUSER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Message withFromUser(User value)
   {
      setFromUser(value);
      return this;
   } 

   public User createFromUser()
   {
      User value = new User();
      withFromUser(value);
      return value;
   } 
}
