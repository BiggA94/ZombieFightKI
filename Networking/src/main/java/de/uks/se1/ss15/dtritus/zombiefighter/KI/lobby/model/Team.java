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
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import org.sdmlib.StrUtil;

public class Team implements PropertyChangeInterface
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
      withoutMembers(this.getMembers().toArray(new User[this.getMembers().size()]));
      setZombieFighter(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Team withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getId());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Team ----------------------------------- User
    *              team                   members
    * </pre>
    */
   
   public static final String PROPERTY_MEMBERS = "members";

   private UserSet members = null;
   
   public UserSet getMembers()
   {
      if (this.members == null)
      {
         return UserSet.EMPTY_SET;
      }
   
      return this.members;
   }

   public Team withMembers(User... value)
   {
      if(value==null){
         return this;
      }
      for (User item : value)
      {
         if (item != null)
         {
            if (this.members == null)
            {
               this.members = new UserSet();
            }
            
            boolean changed = this.members.add (item);

            if (changed)
            {
               item.withTeam(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MEMBERS, null, item);
            }
         }
      }
      return this;
   } 

   public Team withoutMembers(User... value)
   {
      for (User item : value)
      {
         if ((this.members != null) && (item != null))
         {
            if (this.members.remove(item))
            {
               item.setTeam(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MEMBERS, item, null);
            }
         }
      }
      return this;
   }

   public User createMembers()
   {
      User value = new User();
      withMembers(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Team ----------------------------------- ZombieFighter
    *              teams                   zombieFighter
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
            oldValue.withoutTeams(this);
         }
         
         this.zombieFighter = value;
         
         if (value != null)
         {
            value.withTeams(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ZOMBIEFIGHTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Team withZombieFighter(ZombieFighter value)
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

   
   //==========================================================================
   
   public static final String PROPERTY_ID = "id";
   
   private String id;

   public String getId()
   {
      return this.id;
   }
   
   public void setId(String value)
   {
      if ( ! StrUtil.stringEquals(this.id, value))
      {
         String oldValue = this.id;
         this.id = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ID, oldValue, value);
      }
   }
   
   public Team withId(String value)
   {
      setId(value);
      return this;
   } 
}
