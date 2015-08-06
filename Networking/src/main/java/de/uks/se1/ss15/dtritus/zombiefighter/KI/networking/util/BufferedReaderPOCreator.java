package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import java.io.BufferedReader;

public class BufferedReaderPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BufferedReaderPO(new BufferedReader[]{});
      } else {
          return new BufferedReaderPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap(sessionID);
   }
}
