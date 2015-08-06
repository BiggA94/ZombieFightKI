package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import javafx.scene.image.ImageView;

public class ImageViewPO extends PatternObject<ImageViewPO, ImageView>
{

    public ImageViewSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ImageViewSet matches = new ImageViewSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ImageView) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ImageViewPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ImageViewPO(ImageView... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
