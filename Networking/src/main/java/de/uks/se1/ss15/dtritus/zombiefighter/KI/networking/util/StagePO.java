package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import javafx.stage.Stage;

public class StagePO extends PatternObject<StagePO, Stage>
{

    public StageSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StageSet matches = new StageSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Stage) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StagePO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public StagePO(Stage... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
