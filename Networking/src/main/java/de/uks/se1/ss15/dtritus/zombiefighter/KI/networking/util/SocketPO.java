package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.net.Socket;

public class SocketPO extends PatternObject<SocketPO, Socket>
{

    public SocketSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SocketSet matches = new SocketSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Socket) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SocketPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SocketPO(Socket... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
