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
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;

import org.sdmlib.models.modelsets.ObjectSet;

import java.util.Collections;

public class TeamSet extends SDMSet<Team>
{

   public static final TeamSet EMPTY_SET = new TeamSet().withReadOnly(true);


   public TeamPO hasTeamPO()
   {
      return new TeamPO(this.toArray(new Team[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.model.Team";
   }


   @SuppressWarnings("unchecked")
   public TeamSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Team>)value);
      }
      else if (value != null)
      {
         this.add((Team) value);
      }
      
      return this;
   }
   
   public TeamSet without(Team value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Team obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public TeamSet hasName(String value)
   {
      TeamSet result = new TeamSet();
      
      for (Team obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeamSet hasName(String lower, String upper)
   {
      TeamSet result = new TeamSet();
      
      for (Team obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeamSet withName(String value)
   {
      for (Team obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public UserSet getMembers()
   {
      UserSet result = new UserSet();
      
      for (Team obj : this)
      {
         result.addAll(obj.getMembers());
      }
      
      return result;
   }

   public TeamSet hasMembers(Object value)
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
      
      TeamSet answer = new TeamSet();
      
      for (Team obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMembers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TeamSet withMembers(User value)
   {
      for (Team obj : this)
      {
         obj.withMembers(value);
      }
      
      return this;
   }

   public TeamSet withoutMembers(User value)
   {
      for (Team obj : this)
      {
         obj.withoutMembers(value);
      }
      
      return this;
   }

   public ZombieFighterSet getZombieFighter()
   {
      ZombieFighterSet result = new ZombieFighterSet();
      
      for (Team obj : this)
      {
         result.add(obj.getZombieFighter());
      }
      
      return result;
   }

   public TeamSet hasZombieFighter(Object value)
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
      
      TeamSet answer = new TeamSet();
      
      for (Team obj : this)
      {
         if (neighbors.contains(obj.getZombieFighter()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TeamSet withZombieFighter(ZombieFighter value)
   {
      for (Team obj : this)
      {
         obj.withZombieFighter(value);
      }
      
      return this;
   }

   public StringList getId()
   {
      StringList result = new StringList();
      
      for (Team obj : this)
      {
         result.add(obj.getId());
      }
      
      return result;
   }

   public TeamSet hasId(String value)
   {
      TeamSet result = new TeamSet();
      
      for (Team obj : this)
      {
         if (value.equals(obj.getId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeamSet hasId(String lower, String upper)
   {
      TeamSet result = new TeamSet();
      
      for (Team obj : this)
      {
         if (lower.compareTo(obj.getId()) <= 0 && obj.getId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeamSet withId(String value)
   {
      for (Team obj : this)
      {
         obj.setId(value);
      }
      
      return this;
   }

}
