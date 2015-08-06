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
   
package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.models.modelsets.SDMSet;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;

import java.util.Collections;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;

import org.sdmlib.models.modelsets.booleanList;

public class GameSet extends SDMSet<Game>
{

   public static final GameSet EMPTY_SET = new GameSet().withReadOnly(true);


   public GamePO hasGamePO()
   {
      return new GamePO(this.toArray(new Game[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.model.Game";
   }


   @SuppressWarnings("unchecked")
   public GameSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Game>)value);
      }
      else if (value != null)
      {
         this.add((Game) value);
      }
      
      return this;
   }
   
   public GameSet without(Game value)
   {
      this.remove(value);
      return this;
   }

   public MapSet getMap()
   {
      MapSet result = new MapSet();
      
      for (Game obj : this)
      {
         result.add(obj.getMap());
      }
      
      return result;
   }

   public GameSet hasMap(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if (neighbors.contains(obj.getMap()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public GameSet withMap(Map value)
   {
      for (Game obj : this)
      {
         obj.withMap(value);
      }
      
      return this;
   }

   public UserSet getUser()
   {
      UserSet result = new UserSet();
      
      for (Game obj : this)
      {
         result.addAll(obj.getUser());
      }
      
      return result;
   }

   public GameSet hasUser(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getUser()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public GameSet withUser(User value)
   {
      for (Game obj : this)
      {
         obj.withUser(value);
      }
      
      return this;
   }

   public GameSet withoutUser(User value)
   {
      for (Game obj : this)
      {
         obj.withoutUser(value);
      }
      
      return this;
   }

   public ZombieFighterSet getZombieFighter()
   {
      ZombieFighterSet result = new ZombieFighterSet();
      
      for (Game obj : this)
      {
         result.add(obj.getZombieFighter());
      }
      
      return result;
   }

   public GameSet hasZombieFighter(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if (neighbors.contains(obj.getZombieFighter()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public GameSet withZombieFighter(ZombieFighter value)
   {
      for (Game obj : this)
      {
         obj.withZombieFighter(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Game obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public GameSet hasName(String value)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet hasName(String lower, String upper)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet withName(String value)
   {
      for (Game obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public intList getEvents()
   {
      intList result = new intList();
      
      for (Game obj : this)
      {
         result.add(obj.getEvents());
      }
      
      return result;
   }

   public GameSet hasEvents(int value)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (value == obj.getEvents())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet hasEvents(int lower, int upper)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (lower <= obj.getEvents() && obj.getEvents() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet withEvents(int value)
   {
      for (Game obj : this)
      {
         obj.setEvents(value);
      }
      
      return this;
   }

   public StringList getStatus()
   {
      StringList result = new StringList();
      
      for (Game obj : this)
      {
         result.add(obj.getStatus());
      }
      
      return result;
   }

   public GameSet hasStatus(String value)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (value.equals(obj.getStatus()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet hasStatus(String lower, String upper)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (lower.compareTo(obj.getStatus()) <= 0 && obj.getStatus().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet withStatus(String value)
   {
      for (Game obj : this)
      {
         obj.setStatus(value);
      }
      
      return this;
   }

   public booleanList getTestgame()
   {
      booleanList result = new booleanList();
      
      for (Game obj : this)
      {
         result.add(obj.isTestgame());
      }
      
      return result;
   }

   public GameSet hasTestgame(boolean value)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (value == obj.isTestgame())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet withTestgame(boolean value)
   {
      for (Game obj : this)
      {
         obj.setTestgame(value);
      }
      
      return this;
   }

   public intList getMaxPlayers()
   {
      intList result = new intList();
      
      for (Game obj : this)
      {
         result.add(obj.getMaxPlayers());
      }
      
      return result;
   }

   public GameSet hasMaxPlayers(int value)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (value == obj.getMaxPlayers())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet hasMaxPlayers(int lower, int upper)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (lower <= obj.getMaxPlayers() && obj.getMaxPlayers() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet withMaxPlayers(int value)
   {
      for (Game obj : this)
      {
         obj.setMaxPlayers(value);
      }
      
      return this;
   }

   public intList getCurrentPlayers()
   {
      intList result = new intList();
      
      for (Game obj : this)
      {
         result.add(obj.getCurrentPlayers());
      }
      
      return result;
   }

   public GameSet hasCurrentPlayers(int value)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (value == obj.getCurrentPlayers())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet hasCurrentPlayers(int lower, int upper)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (lower <= obj.getCurrentPlayers() && obj.getCurrentPlayers() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet withCurrentPlayers(int value)
   {
      for (Game obj : this)
      {
         obj.setCurrentPlayers(value);
      }
      
      return this;
   }

   public booleanList getFilterFlag()
   {
      booleanList result = new booleanList();
      
      for (Game obj : this)
      {
         result.add(obj.isFilterFlag());
      }
      
      return result;
   }

   public GameSet hasFilterFlag(boolean value)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (value == obj.isFilterFlag())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GameSet withFilterFlag(boolean value)
   {
      for (Game obj : this)
      {
         obj.setFilterFlag(value);
      }
      
      return this;
   }

}
