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
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GameSet;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import org.sdmlib.StrUtil;

public class Map implements PropertyChangeInterface
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
      withoutGames(this.getGames().toArray(new Game[this.getGames().size()]));
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
   
   public Map withName(String value)
   {
      setName(value);
      return this;
   } 

   public static final String PROPERTY_VERSION = "version";
   
   private String version;

   public String getVersion()
   {
      return this.version;
   }
   
   public void setVersion(String value)
   {
      if ( ! StrUtil.stringEquals(this.version, value))
      {
         String oldValue = this.version;
         this.version = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_VERSION, oldValue, value);
      }
   }
   
   public Map withVersion(String value)
   {
      setVersion(value);
      return this;
   } 

   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Map ----------------------------------- Game
    *              map                   games
    * </pre>
    */
   
   public static final String PROPERTY_GAMES = "games";

   private GameSet games = null;
   
   public GameSet getGames()
   {
      if (this.games == null)
      {
         return GameSet.EMPTY_SET;
      }
   
      return this.games;
   }

   public Map withGames(Game... value)
   {
      if(value==null){
         return this;
      }
      for (Game item : value)
      {
         if (item != null)
         {
            if (this.games == null)
            {
               this.games = new GameSet();
            }
            
            boolean changed = this.games.add (item);

            if (changed)
            {
               item.withMap(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_GAMES, null, item);
            }
         }
      }
      return this;
   } 

   public Map withoutGames(Game... value)
   {
      for (Game item : value)
      {
         if ((this.games != null) && (item != null))
         {
            if (this.games.remove(item))
            {
               item.setMap(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_GAMES, item, null);
            }
         }
      }
      return this;
   }

   public Game createGames()
   {
      Game value = new Game();
      withGames(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Map ----------------------------------- ZombieFighter
    *              maps                   zombieFighter
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
            oldValue.withoutMaps(this);
         }
         
         this.zombieFighter = value;
         
         if (value != null)
         {
            value.withMaps(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ZOMBIEFIGHTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Map withZombieFighter(ZombieFighter value)
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
}
