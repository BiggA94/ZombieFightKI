package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;

public class ZombieFightGamePO extends PatternObject<ZombieFightGamePO, ZombieFightGame>
{

    public ZombieFightGameSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ZombieFightGameSet matches = new ZombieFightGameSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ZombieFightGame) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ZombieFightGamePO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ZombieFightGamePO(ZombieFightGame... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
