package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueuePO extends PatternObject<ConcurrentLinkedQueuePO, ConcurrentLinkedQueue>
{

    public ConcurrentLinkedQueueSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ConcurrentLinkedQueueSet matches = new ConcurrentLinkedQueueSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ConcurrentLinkedQueue) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ConcurrentLinkedQueuePO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ConcurrentLinkedQueuePO(ConcurrentLinkedQueue... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
