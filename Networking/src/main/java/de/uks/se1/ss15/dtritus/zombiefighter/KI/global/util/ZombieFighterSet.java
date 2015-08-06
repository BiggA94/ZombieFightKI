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
   
package de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util;

import org.sdmlib.models.modelsets.SDMSet;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;

import java.util.Collections;

import org.sdmlib.models.modelsets.doubleList;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GameSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MessageSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.TeamSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.MessageHandlerPoolSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ServerMessageHandlerSet;

public class ZombieFighterSet extends SDMSet<ZombieFighter>
{

   public static final ZombieFighterSet EMPTY_SET = new ZombieFighterSet().withReadOnly(true);


   public ZombieFighterPO hasZombieFighterPO()
   {
      return new ZombieFighterPO(this.toArray(new ZombieFighter[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.uks.se1.ss15.dtritus.zombiefighter.global.ZombieFighter";
   }


   @SuppressWarnings("unchecked")
   public ZombieFighterSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ZombieFighter>)value);
      }
      else if (value != null)
      {
         this.add((ZombieFighter) value);
      }
      
      return this;
   }
   
   public ZombieFighterSet without(ZombieFighter value)
   {
      this.remove(value);
      return this;
   }

   public ServerMessageHandlerSet getServerMessageHandler()
   {
      ServerMessageHandlerSet result = new ServerMessageHandlerSet();
      
      for (ZombieFighter obj : this)
      {
         result.add(obj.getServerMessageHandler());
      }
      
      return result;
   }

   public ZombieFighterSet hasServerMessageHandler(Object value)
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
      
      ZombieFighterSet answer = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if (neighbors.contains(obj.getServerMessageHandler()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ZombieFighterSet withServerMessageHandler(ServerMessageHandler value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withServerMessageHandler(value);
      }
      
      return this;
   }

   public MessageHandlerPoolSet getMessageHandlerPool()
   {
      MessageHandlerPoolSet result = new MessageHandlerPoolSet();
      
      for (ZombieFighter obj : this)
      {
         result.add(obj.getMessageHandlerPool());
      }
      
      return result;
   }

   public ZombieFighterSet hasMessageHandlerPool(Object value)
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
      
      ZombieFighterSet answer = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if (neighbors.contains(obj.getMessageHandlerPool()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ZombieFighterSet withMessageHandlerPool(MessageHandlerPool value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withMessageHandlerPool(value);
      }
      
      return this;
   }

   public GameSet getGames()
   {
      GameSet result = new GameSet();
      
      for (ZombieFighter obj : this)
      {
         result.addAll(obj.getGames());
      }
      
      return result;
   }

   public ZombieFighterSet hasGames(Object value)
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
      
      ZombieFighterSet answer = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getGames()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ZombieFighterSet withGames(Game value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withGames(value);
      }
      
      return this;
   }

   public ZombieFighterSet withoutGames(Game value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withoutGames(value);
      }
      
      return this;
   }

   public MapSet getMaps()
   {
      MapSet result = new MapSet();
      
      for (ZombieFighter obj : this)
      {
         result.addAll(obj.getMaps());
      }
      
      return result;
   }

   public ZombieFighterSet hasMaps(Object value)
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
      
      ZombieFighterSet answer = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMaps()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ZombieFighterSet withMaps(Map value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withMaps(value);
      }
      
      return this;
   }

   public ZombieFighterSet withoutMaps(Map value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withoutMaps(value);
      }
      
      return this;
   }

   public UserSet getUsers()
   {
      UserSet result = new UserSet();
      
      for (ZombieFighter obj : this)
      {
         result.addAll(obj.getUsers());
      }
      
      return result;
   }

   public ZombieFighterSet hasUsers(Object value)
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
      
      ZombieFighterSet answer = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getUsers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ZombieFighterSet withUsers(User value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withUsers(value);
      }
      
      return this;
   }

   public ZombieFighterSet withoutUsers(User value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withoutUsers(value);
      }
      
      return this;
   }

   public TeamSet getTeams()
   {
      TeamSet result = new TeamSet();
      
      for (ZombieFighter obj : this)
      {
         result.addAll(obj.getTeams());
      }
      
      return result;
   }

   public ZombieFighterSet hasTeams(Object value)
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
      
      ZombieFighterSet answer = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTeams()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ZombieFighterSet withTeams(Team value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withTeams(value);
      }
      
      return this;
   }

   public ZombieFighterSet withoutTeams(Team value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withoutTeams(value);
      }
      
      return this;
   }

   public MessageSet getMessages()
   {
      MessageSet result = new MessageSet();
      
      for (ZombieFighter obj : this)
      {
         result.addAll(obj.getMessages());
      }
      
      return result;
   }

   public ZombieFighterSet hasMessages(Object value)
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
      
      ZombieFighterSet answer = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMessages()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ZombieFighterSet withMessages(Message value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withMessages(value);
      }
      
      return this;
   }

   public ZombieFighterSet withoutMessages(Message value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withoutMessages(value);
      }
      
      return this;
   }

   public UserSet getCurrentUser()
   {
      UserSet result = new UserSet();
      
      for (ZombieFighter obj : this)
      {
         result.add(obj.getCurrentUser());
      }
      
      return result;
   }

   public ZombieFighterSet hasCurrentUser(Object value)
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
      
      ZombieFighterSet answer = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if (neighbors.contains(obj.getCurrentUser()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ZombieFighterSet withCurrentUser(User value)
   {
      for (ZombieFighter obj : this)
      {
         obj.withCurrentUser(value);
      }
      
      return this;
   }

   public ZombieFighterSet withCurrentGame(ZombieFightGame value)
   {
      for (ZombieFighter obj : this)
      {
         obj.setCurrentGame(value);
      }
      
      return this;
   }

   public ZombieFighterSet hasCurrentGame(ZombieFightGame value)
   {
      ZombieFighterSet result = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if (value == obj.getCurrentGame())
         {
            result.add(obj);
         }
      }
      
      return result;
   }
   public doubleList getGameSceneScaling()
   {
      doubleList result = new doubleList();
      
      for (ZombieFighter obj : this)
      {
         result.add(obj.getGameSceneScaling());
      }
      
      return result;
   }

   public ZombieFighterSet hasGameSceneScaling(double value)
   {
      ZombieFighterSet result = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if (value == obj.getGameSceneScaling())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ZombieFighterSet hasGameSceneScaling(double lower, double upper)
   {
      ZombieFighterSet result = new ZombieFighterSet();
      
      for (ZombieFighter obj : this)
      {
         if (lower <= obj.getGameSceneScaling() && obj.getGameSceneScaling() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ZombieFighterSet withGameSceneScaling(double value)
   {
      for (ZombieFighter obj : this)
      {
         obj.setGameSceneScaling(value);
      }
      
      return this;
   }

}
