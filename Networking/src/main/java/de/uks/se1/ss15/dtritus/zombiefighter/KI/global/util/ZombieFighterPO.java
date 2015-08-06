package de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GamePO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GameSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MessagePO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MessageSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.TeamPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.TeamSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.MessageHandlerPoolPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ServerMessageHandlerPO;

import org.sdmlib.models.pattern.AttributeConstraint;

public class ZombieFighterPO extends PatternObject<ZombieFighterPO, ZombieFighter>
{

    public ZombieFighterSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ZombieFighterSet matches = new ZombieFighterSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ZombieFighter) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ZombieFighterPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ZombieFighterPO(ZombieFighter... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ServerMessageHandlerPO hasServerMessageHandler()
   {
      ServerMessageHandlerPO result = new ServerMessageHandlerPO(new ServerMessageHandler[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ZombieFighter.PROPERTY_SERVERMESSAGEHANDLER, result);
      
      return result;
   }

   public ServerMessageHandlerPO createServerMessageHandler()
   {
      return this.startCreate().hasServerMessageHandler().endCreate();
   }

   public ZombieFighterPO hasServerMessageHandler(ServerMessageHandlerPO tgt)
   {
      return hasLinkConstraint(tgt, ZombieFighter.PROPERTY_SERVERMESSAGEHANDLER);
   }

   public ZombieFighterPO createServerMessageHandler(ServerMessageHandlerPO tgt)
   {
      return this.startCreate().hasServerMessageHandler(tgt).endCreate();
   }

   public ServerMessageHandler getServerMessageHandler()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) this.getCurrentMatch()).getServerMessageHandler();
      }
      return null;
   }

   public MessageHandlerPoolPO hasMessageHandlerPool()
   {
      MessageHandlerPoolPO result = new MessageHandlerPoolPO(new MessageHandlerPool[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ZombieFighter.PROPERTY_MESSAGEHANDLERPOOL, result);
      
      return result;
   }

   public MessageHandlerPoolPO createMessageHandlerPool()
   {
      return this.startCreate().hasMessageHandlerPool().endCreate();
   }

   public ZombieFighterPO hasMessageHandlerPool(MessageHandlerPoolPO tgt)
   {
      return hasLinkConstraint(tgt, ZombieFighter.PROPERTY_MESSAGEHANDLERPOOL);
   }

   public ZombieFighterPO createMessageHandlerPool(MessageHandlerPoolPO tgt)
   {
      return this.startCreate().hasMessageHandlerPool(tgt).endCreate();
   }

   public MessageHandlerPool getMessageHandlerPool()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) this.getCurrentMatch()).getMessageHandlerPool();
      }
      return null;
   }

   public GamePO hasGames()
   {
      GamePO result = new GamePO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ZombieFighter.PROPERTY_GAMES, result);
      
      return result;
   }

   public GamePO createGames()
   {
      return this.startCreate().hasGames().endCreate();
   }

   public ZombieFighterPO hasGames(GamePO tgt)
   {
      return hasLinkConstraint(tgt, ZombieFighter.PROPERTY_GAMES);
   }

   public ZombieFighterPO createGames(GamePO tgt)
   {
      return this.startCreate().hasGames(tgt).endCreate();
   }

   public GameSet getGames()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) this.getCurrentMatch()).getGames();
      }
      return null;
   }

   public MapPO hasMaps()
   {
      MapPO result = new MapPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ZombieFighter.PROPERTY_MAPS, result);
      
      return result;
   }

   public MapPO createMaps()
   {
      return this.startCreate().hasMaps().endCreate();
   }

   public ZombieFighterPO hasMaps(MapPO tgt)
   {
      return hasLinkConstraint(tgt, ZombieFighter.PROPERTY_MAPS);
   }

   public ZombieFighterPO createMaps(MapPO tgt)
   {
      return this.startCreate().hasMaps(tgt).endCreate();
   }

   public MapSet getMaps()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) this.getCurrentMatch()).getMaps();
      }
      return null;
   }

   public UserPO hasUsers()
   {
      UserPO result = new UserPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ZombieFighter.PROPERTY_USERS, result);
      
      return result;
   }

   public UserPO createUsers()
   {
      return this.startCreate().hasUsers().endCreate();
   }

   public ZombieFighterPO hasUsers(UserPO tgt)
   {
      return hasLinkConstraint(tgt, ZombieFighter.PROPERTY_USERS);
   }

   public ZombieFighterPO createUsers(UserPO tgt)
   {
      return this.startCreate().hasUsers(tgt).endCreate();
   }

   public UserSet getUsers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) this.getCurrentMatch()).getUsers();
      }
      return null;
   }

   public TeamPO hasTeams()
   {
      TeamPO result = new TeamPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ZombieFighter.PROPERTY_TEAMS, result);
      
      return result;
   }

   public TeamPO createTeams()
   {
      return this.startCreate().hasTeams().endCreate();
   }

   public ZombieFighterPO hasTeams(TeamPO tgt)
   {
      return hasLinkConstraint(tgt, ZombieFighter.PROPERTY_TEAMS);
   }

   public ZombieFighterPO createTeams(TeamPO tgt)
   {
      return this.startCreate().hasTeams(tgt).endCreate();
   }

   public TeamSet getTeams()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) this.getCurrentMatch()).getTeams();
      }
      return null;
   }

   public MessagePO hasMessages()
   {
      MessagePO result = new MessagePO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Message[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ZombieFighter.PROPERTY_MESSAGES, result);
      
      return result;
   }

   public MessagePO createMessages()
   {
      return this.startCreate().hasMessages().endCreate();
   }

   public ZombieFighterPO hasMessages(MessagePO tgt)
   {
      return hasLinkConstraint(tgt, ZombieFighter.PROPERTY_MESSAGES);
   }

   public ZombieFighterPO createMessages(MessagePO tgt)
   {
      return this.startCreate().hasMessages(tgt).endCreate();
   }

   public MessageSet getMessages()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) this.getCurrentMatch()).getMessages();
      }
      return null;
   }

   public UserPO hasCurrentUser()
   {
      UserPO result = new UserPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ZombieFighter.PROPERTY_CURRENTUSER, result);
      
      return result;
   }

   public UserPO createCurrentUser()
   {
      return this.startCreate().hasCurrentUser().endCreate();
   }

   public ZombieFighterPO hasCurrentUser(UserPO tgt)
   {
      return hasLinkConstraint(tgt, ZombieFighter.PROPERTY_CURRENTUSER);
   }

   public ZombieFighterPO createCurrentUser(UserPO tgt)
   {
      return this.startCreate().hasCurrentUser(tgt).endCreate();
   }

   public User getCurrentUser()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) this.getCurrentMatch()).getCurrentUser();
      }
      return null;
   }

   public ZombieFighterPO hasCurrentUser(User value)
   {
      new AttributeConstraint()
      .withAttrName(ZombieFighter.PROPERTY_CURRENTUSER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ZombieFighterPO createCurrentUser(User value)
   {
      this.startCreate().hasCurrentUser(value).endCreate();
      return this;
   }

   public ZombieFighterPO hasCurrentGame(ZombieFightGame value)
   {
      new AttributeConstraint()
      .withAttrName(ZombieFighter.PROPERTY_CURRENTGAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ZombieFighterPO createCurrentGame(ZombieFightGame value)
   {
      this.startCreate().hasCurrentGame(value).endCreate();
      return this;
   }
   
   public ZombieFightGame getCurrentGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) getCurrentMatch()).getCurrentGame();
      }
      return null;
   }
   
   public ZombieFighterPO withCurrentGame(ZombieFightGame value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ZombieFighter) getCurrentMatch()).setCurrentGame(value);
      }
      return this;
   }
   
   public ZombieFighterPO hasGameSceneScaling(double value)
   {
      new AttributeConstraint()
      .withAttrName(ZombieFighter.PROPERTY_GAMESCENESCALING)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ZombieFighterPO hasGameSceneScaling(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(ZombieFighter.PROPERTY_GAMESCENESCALING)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ZombieFighterPO createGameSceneScaling(double value)
   {
      this.startCreate().hasGameSceneScaling(value).endCreate();
      return this;
   }
   
   public double getGameSceneScaling()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieFighter) getCurrentMatch()).getGameSceneScaling();
      }
      return 0;
   }
   
   public ZombieFighterPO withGameSceneScaling(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ZombieFighter) getCurrentMatch()).setGameSceneScaling(value);
      }
      return this;
   }
   
}
