package de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.Team;
import de.uniks.networkparser.json.JsonIdMap;

public class TeamPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TeamPO(new Team[]{});
      } else {
          return new TeamPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap(sessionID);
   }
}
