package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueuePO extends PatternObject<LinkedBlockingQueuePO, LinkedBlockingQueue>
{

    public LinkedBlockingQueueSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LinkedBlockingQueueSet matches = new LinkedBlockingQueueSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LinkedBlockingQueue) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LinkedBlockingQueuePO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LinkedBlockingQueuePO(LinkedBlockingQueue... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
