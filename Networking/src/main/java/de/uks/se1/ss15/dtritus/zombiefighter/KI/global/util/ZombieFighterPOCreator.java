package de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uniks.networkparser.json.JsonIdMap;

public class ZombieFighterPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ZombieFighterPO(new ZombieFighter[]{});
      } else {
          return new ZombieFighterPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap(sessionID);
   }
}
