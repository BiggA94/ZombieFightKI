package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.io.PrintWriter;

public class PrintWriterPO extends PatternObject<PrintWriterPO, PrintWriter>
{

    public PrintWriterSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PrintWriterSet matches = new PrintWriterSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PrintWriter) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PrintWriterPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PrintWriterPO(PrintWriter... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
