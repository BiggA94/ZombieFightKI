package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Game;
import de.uniks.networkparser.json.JsonIdMap;

public class GamePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GamePO(new Game[]{});
      } else {
          return new GamePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap(sessionID);
   }
}
