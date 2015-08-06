package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.TeamPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserSet;

import org.sdmlib.models.pattern.AttributeConstraint;

public class TeamPO extends PatternObject<TeamPO, Team>
{

    public TeamSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TeamSet matches = new TeamSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Team) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TeamPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TeamPO(Team... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public TeamPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Team.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public TeamPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Team.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public TeamPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Team) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public TeamPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Team) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public UserPO hasMembers()
   {
      UserPO result = new UserPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Team.PROPERTY_MEMBERS, result);
      
      return result;
   }

   public UserPO createMembers()
   {
      return this.startCreate().hasMembers().endCreate();
   }

   public TeamPO hasMembers(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Team.PROPERTY_MEMBERS);
   }

   public TeamPO createMembers(UserPO tgt)
   {
      return this.startCreate().hasMembers(tgt).endCreate();
   }

   public UserSet getMembers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Team) this.getCurrentMatch()).getMembers();
      }
      return null;
   }

   public ZombieFighterPO hasZombieFighter()
   {
      ZombieFighterPO result = new ZombieFighterPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Team.PROPERTY_ZOMBIEFIGHTER, result);
      
      return result;
   }

   public ZombieFighterPO createZombieFighter()
   {
      return this.startCreate().hasZombieFighter().endCreate();
   }

   public TeamPO hasZombieFighter(ZombieFighterPO tgt)
   {
      return hasLinkConstraint(tgt, Team.PROPERTY_ZOMBIEFIGHTER);
   }

   public TeamPO createZombieFighter(ZombieFighterPO tgt)
   {
      return this.startCreate().hasZombieFighter(tgt).endCreate();
   }

   public ZombieFighter getZombieFighter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Team) this.getCurrentMatch()).getZombieFighter();
      }
      return null;
   }

   public TeamPO hasId(String value)
   {
      new AttributeConstraint()
      .withAttrName(Team.PROPERTY_ID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public TeamPO hasId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Team.PROPERTY_ID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public TeamPO createId(String value)
   {
      this.startCreate().hasId(value).endCreate();
      return this;
   }
   
   public String getId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Team) getCurrentMatch()).getId();
      }
      return null;
   }
   
   public TeamPO withId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Team) getCurrentMatch()).setId(value);
      }
      return this;
   }
   
}
