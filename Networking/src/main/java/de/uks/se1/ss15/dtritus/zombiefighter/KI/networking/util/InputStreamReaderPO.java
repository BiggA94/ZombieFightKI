package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.io.InputStreamReader;

public class InputStreamReaderPO extends PatternObject<InputStreamReaderPO, InputStreamReader>
{

    public InputStreamReaderSet allMatches()
   {
      this.setDoAllMatches(true);
      
      InputStreamReaderSet matches = new InputStreamReaderSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((InputStreamReader) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public InputStreamReaderPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public InputStreamReaderPO(InputStreamReader... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
