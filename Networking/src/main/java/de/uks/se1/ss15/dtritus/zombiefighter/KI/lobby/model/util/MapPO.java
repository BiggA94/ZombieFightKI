package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Map;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GamePO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GameSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapPO;

import org.sdmlib.models.pattern.AttributeConstraint;

public class MapPO extends PatternObject<MapPO, Map>
{

    public MapSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MapSet matches = new MapSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Map) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MapPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MapPO(Map... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MapPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Map.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public MapPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Map.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public MapPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Map) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public MapPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Map) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public GamePO hasGames()
   {
      GamePO result = new GamePO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Map.PROPERTY_GAMES, result);
      
      return result;
   }

   public GamePO createGames()
   {
      return this.startCreate().hasGames().endCreate();
   }

   public MapPO hasGames(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Map.PROPERTY_GAMES);
   }

   public MapPO createGames(GamePO tgt)
   {
      return this.startCreate().hasGames(tgt).endCreate();
   }

   public GameSet getGames()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Map) this.getCurrentMatch()).getGames();
      }
      return null;
   }

   public ZombieFighterPO hasZombieFighter()
   {
      ZombieFighterPO result = new ZombieFighterPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Map.PROPERTY_ZOMBIEFIGHTER, result);
      
      return result;
   }

   public ZombieFighterPO createZombieFighter()
   {
      return this.startCreate().hasZombieFighter().endCreate();
   }

   public MapPO hasZombieFighter(ZombieFighterPO tgt)
   {
      return hasLinkConstraint(tgt, Map.PROPERTY_ZOMBIEFIGHTER);
   }

   public MapPO createZombieFighter(ZombieFighterPO tgt)
   {
      return this.startCreate().hasZombieFighter(tgt).endCreate();
   }

   public ZombieFighter getZombieFighter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Map) this.getCurrentMatch()).getZombieFighter();
      }
      return null;
   }

}
