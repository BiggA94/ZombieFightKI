package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.util.AbstractHandlerPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.MessageHandlerPoolPO;

public class AbstractHandlerPO extends PatternObject<AbstractHandlerPO, AbstractHandler>
{

    public AbstractHandlerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AbstractHandlerSet matches = new AbstractHandlerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((AbstractHandler) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AbstractHandlerPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public AbstractHandlerPO(AbstractHandler... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public boolean handle(String... messages)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AbstractHandler) getCurrentMatch()).handle(messages);
      }
      return false;
   }

   public MessageHandlerPoolPO hasHandlerPool()
   {
      MessageHandlerPoolPO result = new MessageHandlerPoolPO(new MessageHandlerPool[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(AbstractHandler.PROPERTY_HANDLERPOOL, result);
      
      return result;
   }

   public MessageHandlerPoolPO createHandlerPool()
   {
      return this.startCreate().hasHandlerPool().endCreate();
   }

   public AbstractHandlerPO hasHandlerPool(MessageHandlerPoolPO tgt)
   {
      return hasLinkConstraint(tgt, AbstractHandler.PROPERTY_HANDLERPOOL);
   }

   public AbstractHandlerPO createHandlerPool(MessageHandlerPoolPO tgt)
   {
      return this.startCreate().hasHandlerPool(tgt).endCreate();
   }

   public MessageHandlerPool getHandlerPool()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AbstractHandler) this.getCurrentMatch()).getHandlerPool();
      }
      return null;
   }

}
