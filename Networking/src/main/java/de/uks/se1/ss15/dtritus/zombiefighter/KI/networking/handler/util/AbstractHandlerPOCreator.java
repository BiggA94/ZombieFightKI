package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uniks.networkparser.json.JsonIdMap;

public class AbstractHandlerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AbstractHandlerPO(new AbstractHandler[]{});
      } else {
          return new AbstractHandlerPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap(sessionID);
   }
}
