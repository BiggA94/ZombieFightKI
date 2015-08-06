package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GamePO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;

import org.sdmlib.models.pattern.AttributeConstraint;

public class GamePO extends PatternObject<GamePO, Game>
{

    public GameSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GameSet matches = new GameSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Game) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GamePO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public GamePO(Game... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MapPO hasMap()
   {
      MapPO result = new MapPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_MAP, result);
      
      return result;
   }

   public MapPO createMap()
   {
      return this.startCreate().hasMap().endCreate();
   }

   public GamePO hasMap(MapPO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_MAP);
   }

   public GamePO createMap(MapPO tgt)
   {
      return this.startCreate().hasMap(tgt).endCreate();
   }

   public Map getMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getMap();
      }
      return null;
   }

   public UserPO hasUser()
   {
      UserPO result = new UserPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_USER, result);
      
      return result;
   }

   public UserPO createUser()
   {
      return this.startCreate().hasUser().endCreate();
   }

   public GamePO hasUser(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_USER);
   }

   public GamePO createUser(UserPO tgt)
   {
      return this.startCreate().hasUser(tgt).endCreate();
   }

   public UserSet getUser()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getUser();
      }
      return null;
   }

   public ZombieFighter getZombieFighter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getZombieFighter();
      }
      return null;
   }

   public GamePO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public GamePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Game) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public GamePO hasEvents(int value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_EVENTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO hasEvents(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_EVENTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO createEvents(int value)
   {
      this.startCreate().hasEvents(value).endCreate();
      return this;
   }
   
   public int getEvents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).getEvents();
      }
      return 0;
   }
   
   public GamePO withEvents(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Game) getCurrentMatch()).setEvents(value);
      }
      return this;
   }
   
   public GamePO hasStatus(String value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_STATUS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO hasStatus(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_STATUS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO createStatus(String value)
   {
      this.startCreate().hasStatus(value).endCreate();
      return this;
   }
   
   public String getStatus()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).getStatus();
      }
      return null;
   }
   
   public GamePO withStatus(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Game) getCurrentMatch()).setStatus(value);
      }
      return this;
   }
   
   public GamePO hasTestgame(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_TESTGAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO createTestgame(boolean value)
   {
      this.startCreate().hasTestgame(value).endCreate();
      return this;
   }
   
   public boolean getTestgame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).isTestgame();
      }
      return false;
   }
   
   public GamePO withTestgame(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Game) getCurrentMatch()).setTestgame(value);
      }
      return this;
   }
   
   public GamePO hasMaxPlayers(int value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_MAXPLAYERS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO hasMaxPlayers(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_MAXPLAYERS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO createMaxPlayers(int value)
   {
      this.startCreate().hasMaxPlayers(value).endCreate();
      return this;
   }
   
   public int getMaxPlayers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).getMaxPlayers();
      }
      return 0;
   }
   
   public GamePO withMaxPlayers(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Game) getCurrentMatch()).setMaxPlayers(value);
      }
      return this;
   }
   
   public GamePO hasCurrentPlayers(int value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_CURRENTPLAYERS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO hasCurrentPlayers(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_CURRENTPLAYERS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO createCurrentPlayers(int value)
   {
      this.startCreate().hasCurrentPlayers(value).endCreate();
      return this;
   }
   
   public int getCurrentPlayers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).getCurrentPlayers();
      }
      return 0;
   }
   
   public GamePO withCurrentPlayers(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Game) getCurrentMatch()).setCurrentPlayers(value);
      }
      return this;
   }
   
   public GamePO hasFilterFlag(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_FILTERFLAG)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public GamePO createFilterFlag(boolean value)
   {
      this.startCreate().hasFilterFlag(value).endCreate();
      return this;
   }
   
   public boolean getFilterFlag()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).isFilterFlag();
      }
      return false;
   }
   
   public GamePO withFilterFlag(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Game) getCurrentMatch()).setFilterFlag(value);
      }
      return this;
   }
   
}
