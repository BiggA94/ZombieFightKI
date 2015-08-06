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

import org.sdmlib.models.modelsets.StringList;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GameSet;

import org.sdmlib.models.modelsets.ObjectSet;

import java.util.Collections;

public class MapSet extends SDMSet<Map>
{

   public static final MapSet EMPTY_SET = new MapSet().withReadOnly(true);


   public MapPO hasMapPO()
   {
      return new MapPO(this.toArray(new Map[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.model.Map";
   }


   @SuppressWarnings("unchecked")
   public MapSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Map>)value);
      }
      else if (value != null)
      {
         this.add((Map) value);
      }
      
      return this;
   }
   
   public MapSet without(Map value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Map obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public MapSet hasName(String value)
   {
      MapSet result = new MapSet();
      
      for (Map obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MapSet hasName(String lower, String upper)
   {
      MapSet result = new MapSet();
      
      for (Map obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MapSet withName(String value)
   {
      for (Map obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public GameSet getGames()
   {
      GameSet result = new GameSet();
      
      for (Map obj : this)
      {
         result.addAll(obj.getGames());
      }
      
      return result;
   }

   public MapSet hasGames(Object value)
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
      
      MapSet answer = new MapSet();
      
      for (Map obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getGames()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MapSet withGames(Game value)
   {
      for (Map obj : this)
      {
         obj.withGames(value);
      }
      
      return this;
   }

   public MapSet withoutGames(Game value)
   {
      for (Map obj : this)
      {
         obj.withoutGames(value);
      }
      
      return this;
   }

   public ZombieFighterSet getZombieFighter()
   {
      ZombieFighterSet result = new ZombieFighterSet();
      
      for (Map obj : this)
      {
         result.add(obj.getZombieFighter());
      }
      
      return result;
   }

   public MapSet hasZombieFighter(Object value)
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
      
      MapSet answer = new MapSet();
      
      for (Map obj : this)
      {
         if (neighbors.contains(obj.getZombieFighter()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MapSet withZombieFighter(ZombieFighter value)
   {
      for (Map obj : this)
      {
         obj.withZombieFighter(value);
      }
      
      return this;
   }

}
