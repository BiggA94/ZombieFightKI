package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.sql.Timestamp;

public class TimestampPO extends PatternObject<TimestampPO, Timestamp>
{

    public TimestampSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TimestampSet matches = new TimestampSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Timestamp) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TimestampPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TimestampPO(Timestamp... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
