package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ServerMessageHandlerCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ServerMessageHandlerPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadObjectCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadObjectPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.MessageHandlerPoolCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.MessageHandlerPoolPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GameCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.GamePOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MapPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.UserPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.TeamCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.TeamPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MessageCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.lobby.model.util.MessagePOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.SocketCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.SocketPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.InputStreamReaderCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.InputStreamReaderPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.BufferedReaderCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.BufferedReaderPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.OutputStreamWriterCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.OutputStreamWriterPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.PrintWriterCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.PrintWriterPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.LinkedListCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.LinkedListPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.TimestampCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.TimestampPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadRunnerCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadRunnerPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadExtensionCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadExtensionPOCreator());
      jsonIdMap.withCreator(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.util.AbstractHandlerPOCreator());
      return jsonIdMap;
   }
}
