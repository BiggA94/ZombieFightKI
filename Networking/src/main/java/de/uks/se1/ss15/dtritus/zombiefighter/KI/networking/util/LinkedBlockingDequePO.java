package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequePO extends PatternObject<LinkedBlockingDequePO, LinkedBlockingDeque>
{

    public LinkedBlockingDequeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LinkedBlockingDequeSet matches = new LinkedBlockingDequeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LinkedBlockingDeque) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LinkedBlockingDequePO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LinkedBlockingDequePO(LinkedBlockingDeque... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
