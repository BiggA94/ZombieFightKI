package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.lang.Runnable;

public class RunnablePO extends PatternObject<RunnablePO, Runnable>
{

    public RunnableSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RunnableSet matches = new RunnableSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Runnable) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public RunnablePO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public RunnablePO(Runnable... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
