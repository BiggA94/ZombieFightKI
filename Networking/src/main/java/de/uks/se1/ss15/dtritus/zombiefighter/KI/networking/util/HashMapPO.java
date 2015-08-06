package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;
import java.util.HashMap;

public class HashMapPO extends PatternObject<HashMapPO, HashMap>
{

    public HashMapSet allMatches()
   {
      this.setDoAllMatches(true);
      
      HashMapSet matches = new HashMapSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((HashMap) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public HashMapPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public HashMapPO(HashMap... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
