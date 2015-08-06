package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import javafx.scene.paint.Color;

public class ColorPO extends PatternObject<ColorPO, Color>
{

    public ColorSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ColorSet matches = new ColorSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Color) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ColorPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ColorPO(Color... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
