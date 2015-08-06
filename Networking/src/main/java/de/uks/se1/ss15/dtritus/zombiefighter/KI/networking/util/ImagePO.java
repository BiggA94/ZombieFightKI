package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import javafx.scene.image.Image;

public class ImagePO extends PatternObject<ImagePO, Image>
{

    public ImageSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ImageSet matches = new ImageSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Image) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ImagePO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ImagePO(Image... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
