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

public class Game implements PropertyChangeInterface
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
      setMap(null);
      withoutUser(this.getUser().toArray(new User[this.getUser().size()]));
      setZombieFighter(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Game ----------------------------------- Map
    *              games                   map
    * </pre>
    */
   
   public static final String PROPERTY_MAP = "map";

   private Map map = null;

   public Map getMap()
   {
      return this.map;
   }

   public boolean setMap(Map value)
   {
      boolean changed = false;
      
      if (this.map != value)
      {
         Map oldValue = this.map;
         
         if (this.map != null)
         {
            this.map = null;
            oldValue.withoutGames(this);
         }
         
         this.map = value;
         
         if (value != null)
         {
            value.withGames(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Game withMap(Map value)
   {
      setMap(value);
      return this;
   } 

   public Map createMap()
   {
      Map value = new Map();
      withMap(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Game ----------------------------------- User
    *              game                   user
    * </pre>
    */
   
   public static final String PROPERTY_USER = "user";

   private UserSet user = null;
   
   public UserSet getUser()
   {
      if (this.user == null)
      {
         return UserSet.EMPTY_SET;
      }
   
      return this.user;
   }

   public Game withUser(User... value)
   {
      if(value==null){
         return this;
      }
      for (User item : value)
      {
         if (item != null)
         {
            if (this.user == null)
            {
               this.user = new UserSet();
            }
            
            boolean changed = this.user.add (item);

            if (changed)
            {
               item.withGame(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_USER, null, item);
            }
         }
      }
      return this;
   } 

   public Game withoutUser(User... value)
   {
      for (User item : value)
      {
         if ((this.user != null) && (item != null))
         {
            if (this.user.remove(item))
            {
               item.setGame(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_USER, item, null);
            }
         }
      }
      return this;
   }

   public User createUser()
   {
      User value = new User();
      withUser(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Game ----------------------------------- ZombieFighter
    *              games                   zombieFighter
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
            oldValue.withoutGames(this);
         }
         
         this.zombieFighter = value;
         
         if (value != null)
         {
            value.withGames(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ZOMBIEFIGHTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Game withZombieFighter(ZombieFighter value)
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
   
   public Game withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getEvents());
      result.append(" ").append(this.getStatus());
      result.append(" ").append(this.getMaxPlayers());
      result.append(" ").append(this.getCurrentPlayers());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_EVENTS = "events";
   
   private int events;

   public int getEvents()
   {
      return this.events;
   }
   
   public void setEvents(int value)
   {
      if (this.events != value)
      {
         int oldValue = this.events;
         this.events = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_EVENTS, oldValue, value);
      }
   }
   
   public Game withEvents(int value)
   {
      setEvents(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_STATUS = "status";
   
   private String status;

   public String getStatus()
   {
      return this.status;
   }
   
   public void setStatus(String value)
   {
      if ( ! StrUtil.stringEquals(this.status, value))
      {
         String oldValue = this.status;
         this.status = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STATUS, oldValue, value);
      }
   }
   
   public Game withStatus(String value)
   {
      setStatus(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TESTGAME = "testgame";
   
   private boolean testgame;

   public boolean isTestgame()
   {
      return this.testgame;
   }
   
   public void setTestgame(boolean value)
   {
      if (this.testgame != value)
      {
         boolean oldValue = this.testgame;
         this.testgame = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TESTGAME, oldValue, value);
      }
   }
   
   public Game withTestgame(boolean value)
   {
      setTestgame(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Game ----------------------------------- GameTableRow
    *              game                   tableRow
    * </pre>
    */
   
   public static final String PROPERTY_TABLEROW = "tableRow";

    

   
   //==========================================================================
   
   public static final String PROPERTY_MAXPLAYERS = "maxPlayers";
   
   private int maxPlayers;

   public int getMaxPlayers()
   {
      return this.maxPlayers;
   }
   
   public void setMaxPlayers(int value)
   {
      if (this.maxPlayers != value)
      {
         int oldValue = this.maxPlayers;
         this.maxPlayers = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MAXPLAYERS, oldValue, value);
      }
   }
   
   public Game withMaxPlayers(int value)
   {
      setMaxPlayers(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CURRENTPLAYERS = "currentPlayers";
   
   private int currentPlayers;

   public int getCurrentPlayers()
   {
      return this.currentPlayers;
   }
   
   public void setCurrentPlayers(int value)
   {
      if (this.currentPlayers != value)
      {
         int oldValue = this.currentPlayers;
         this.currentPlayers = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTPLAYERS, oldValue, value);
      }
   }
   
   public Game withCurrentPlayers(int value)
   {
      setCurrentPlayers(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_FILTERFLAG = "filterFlag";
   
   private boolean filterFlag = false;

   public boolean isFilterFlag()
   {
      return this.filterFlag;
   }
   
   public void setFilterFlag(boolean value)
   {
      if (this.filterFlag != value)
      {
         boolean oldValue = this.filterFlag;
         this.filterFlag = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FILTERFLAG, oldValue, value);
      }
   }
   
   public Game withFilterFlag(boolean value)
   {
      setFilterFlag(value);
      return this;
   } 
}
