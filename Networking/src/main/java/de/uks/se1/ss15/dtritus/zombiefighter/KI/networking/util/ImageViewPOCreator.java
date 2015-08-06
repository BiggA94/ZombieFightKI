package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import javafx.scene.image.ImageView;

public class ImageViewPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ImageViewPO(new ImageView[]{});
      } else {
          return new ImageViewPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap(sessionID);
   }
}
