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

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GameSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MessageSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.TeamSet;


public class UserSet extends SDMSet<User>
{

   public static final UserSet EMPTY_SET = new UserSet().withReadOnly(true);


   public UserPO hasUserPO()
   {
      return new UserPO(this.toArray(new User[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.model.User";
   }


   @SuppressWarnings("unchecked")
   public UserSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<User>)value);
      }
      else if (value != null)
      {
         this.add((User) value);
      }
      
      return this;
   }
   
   public UserSet without(User value)
   {
      this.remove(value);
      return this;
   }

   public StringList getNick()
   {
      StringList result = new StringList();
      
      for (User obj : this)
      {
         result.add(obj.getNick());
      }
      
      return result;
   }

   public UserSet hasNick(String value)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (value.equals(obj.getNick()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet hasNick(String lower, String upper)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (lower.compareTo(obj.getNick()) <= 0 && obj.getNick().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet withNick(String value)
   {
      for (User obj : this)
      {
         obj.setNick(value);
      }
      
      return this;
   }

   public StringList getEmail()
   {
      StringList result = new StringList();
      
      for (User obj : this)
      {
         result.add(obj.getEmail());
      }
      
      return result;
   }

   public UserSet hasEmail(String value)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (value.equals(obj.getEmail()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet hasEmail(String lower, String upper)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (lower.compareTo(obj.getEmail()) <= 0 && obj.getEmail().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet withEmail(String value)
   {
      for (User obj : this)
      {
         obj.setEmail(value);
      }
      
      return this;
   }

   public StringList getRole()
   {
      StringList result = new StringList();
      
      for (User obj : this)
      {
         result.add(obj.getRole());
      }
      
      return result;
   }

   public UserSet hasRole(String value)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (value.equals(obj.getRole()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet hasRole(String lower, String upper)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (lower.compareTo(obj.getRole()) <= 0 && obj.getRole().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet withRole(String value)
   {
      for (User obj : this)
      {
         obj.setRole(value);
      }
      
      return this;
   }




   public StringList getState()
   {
      StringList result = new StringList();
      
      for (User obj : this)
      {
         result.add(obj.getState());
      }
      
      return result;
   }

   public UserSet hasState(String value)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (value.equals(obj.getState()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet hasState(String lower, String upper)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (lower.compareTo(obj.getState()) <= 0 && obj.getState().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet withState(String value)
   {
      for (User obj : this)
      {
         obj.setState(value);
      }
      
      return this;
   }

   public GameSet getGame()
   {
      GameSet result = new GameSet();
      
      for (User obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public UserSet hasGame(Object value)
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
      
      UserSet answer = new UserSet();
      
      for (User obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public UserSet withGame(Game value)
   {
      for (User obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public TeamSet getTeam()
   {
      TeamSet result = new TeamSet();
      
      for (User obj : this)
      {
         result.add(obj.getTeam());
      }
      
      return result;
   }

   public UserSet hasTeam(Object value)
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
      
      UserSet answer = new UserSet();
      
      for (User obj : this)
      {
         if (neighbors.contains(obj.getTeam()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public UserSet withTeam(Team value)
   {
      for (User obj : this)
      {
         obj.withTeam(value);
      }
      
      return this;
   }

   public ZombieFighterSet getZombieFighter()
   {
      ZombieFighterSet result = new ZombieFighterSet();
      
      for (User obj : this)
      {
         result.add(obj.getZombieFighter());
      }
      
      return result;
   }

   public UserSet hasZombieFighter(Object value)
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
      
      UserSet answer = new UserSet();
      
      for (User obj : this)
      {
         if (neighbors.contains(obj.getZombieFighter()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public UserSet withZombieFighter(ZombieFighter value)
   {
      for (User obj : this)
      {
         obj.withZombieFighter(value);
      }
      
      return this;
   }

   public MessageSet getMessagesInbox()
   {
      MessageSet result = new MessageSet();
      
      for (User obj : this)
      {
         result.addAll(obj.getMessagesInbox());
      }
      
      return result;
   }

   public UserSet hasMessagesInbox(Object value)
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
      
      UserSet answer = new UserSet();
      
      for (User obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMessagesInbox()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public UserSet withMessagesInbox(Message value)
   {
      for (User obj : this)
      {
         obj.withMessagesInbox(value);
      }
      
      return this;
   }

   public UserSet withoutMessagesInbox(Message value)
   {
      for (User obj : this)
      {
         obj.withoutMessagesInbox(value);
      }
      
      return this;
   }

   public MessageSet getMessagesOutbox()
   {
      MessageSet result = new MessageSet();
      
      for (User obj : this)
      {
         result.addAll(obj.getMessagesOutbox());
      }
      
      return result;
   }

   public UserSet hasMessagesOutbox(Object value)
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
      
      UserSet answer = new UserSet();
      
      for (User obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMessagesOutbox()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public UserSet withMessagesOutbox(Message value)
   {
      for (User obj : this)
      {
         obj.withMessagesOutbox(value);
      }
      
      return this;
   }

   public UserSet withoutMessagesOutbox(Message value)
   {
      for (User obj : this)
      {
         obj.withoutMessagesOutbox(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public SDMSet<Timestamp> getLastInteraction()
   {
	   SDMSet<Timestamp>  result = new SDMSet<Timestamp>() {

		@Override
		public String getEntryType() {
			return "Timestamp";
		}
		   
	};
      for (User obj : this)
      {
         result.add(obj.getLastInteraction());
      }
      return result;
   }

   public booleanList getUnreadMessages()
   {
      booleanList result = new booleanList();
      
      for (User obj : this)
      {
         result.add(obj.isUnreadMessages());
      }
      
      return result;
   }

   public UserSet hasUnreadMessages(boolean value)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (value == obj.isUnreadMessages())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet withUnreadMessages(boolean value)
   {
      for (User obj : this)
      {
         obj.setUnreadMessages(value);
      }
      
      return this;
   }

   public booleanList getIngame()
   {
      booleanList result = new booleanList();
      
      for (User obj : this)
      {
         result.add(obj.isIngame());
      }
      
      return result;
   }

   public UserSet hasIngame(boolean value)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (value == obj.isIngame())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UserSet withIngame(boolean value)
   {
      for (User obj : this)
      {
         obj.setIngame(value);
      }
      
      return this;
   }

}
