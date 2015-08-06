package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.io.BufferedReader;

public class BufferedReaderPO extends PatternObject<BufferedReaderPO, BufferedReader>
{

    public BufferedReaderSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BufferedReaderSet matches = new BufferedReaderSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((BufferedReader) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BufferedReaderPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public BufferedReaderPO(BufferedReader... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
