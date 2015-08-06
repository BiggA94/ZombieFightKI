package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.io.OutputStreamWriter;

public class OutputStreamWriterPO extends PatternObject<OutputStreamWriterPO, OutputStreamWriter>
{

    public OutputStreamWriterSet allMatches()
   {
      this.setDoAllMatches(true);
      
      OutputStreamWriterSet matches = new OutputStreamWriterSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((OutputStreamWriter) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public OutputStreamWriterPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public OutputStreamWriterPO(OutputStreamWriter... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
