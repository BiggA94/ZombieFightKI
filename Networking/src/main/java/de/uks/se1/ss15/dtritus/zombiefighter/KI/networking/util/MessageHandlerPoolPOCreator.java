package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uniks.networkparser.json.JsonIdMap;

public class MessageHandlerPoolPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MessageHandlerPoolPO(new MessageHandlerPool[]{});
      } else {
          return new MessageHandlerPoolPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap(sessionID);
   }
}
