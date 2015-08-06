package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.util.LinkedList;

public class LinkedListPO extends PatternObject<LinkedListPO, LinkedList>
{

    public LinkedListSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LinkedListSet matches = new LinkedListSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LinkedList) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LinkedListPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LinkedListPO(LinkedList... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
