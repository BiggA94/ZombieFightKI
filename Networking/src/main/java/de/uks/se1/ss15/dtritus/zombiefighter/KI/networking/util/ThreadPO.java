package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.lang.Thread;

public class ThreadPO extends PatternObject<ThreadPO, Thread>
{

    public ThreadSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ThreadSet matches = new ThreadSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Thread) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ThreadPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ThreadPO(Thread... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
