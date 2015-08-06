package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.AbstractHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.util.AbstractHandlerPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler.util.AbstractHandlerSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.MessageHandlerPoolPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ServerMessageHandlerPO;

import org.sdmlib.models.pattern.AttributeConstraint;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageHandlerPoolPO extends PatternObject<MessageHandlerPoolPO, MessageHandlerPool>
{

    public MessageHandlerPoolSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MessageHandlerPoolSet matches = new MessageHandlerPoolSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MessageHandlerPool) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MessageHandlerPoolPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MessageHandlerPoolPO(MessageHandlerPool... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public boolean handle(byte[] message)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MessageHandlerPool) getCurrentMatch()).handle(message);
      }
      return false;
   }

   public ServerMessageHandlerPO hasServerMessageHandler()
   {
      ServerMessageHandlerPO result = new ServerMessageHandlerPO(new ServerMessageHandler[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MessageHandlerPool.PROPERTY_SERVERMESSAGEHANDLER, result);
      
      return result;
   }

   public ServerMessageHandlerPO createServerMessageHandler()
   {
      return this.startCreate().hasServerMessageHandler().endCreate();
   }

   public MessageHandlerPoolPO hasServerMessageHandler(ServerMessageHandlerPO tgt)
   {
      return hasLinkConstraint(tgt, MessageHandlerPool.PROPERTY_SERVERMESSAGEHANDLER);
   }

   public MessageHandlerPoolPO createServerMessageHandler(ServerMessageHandlerPO tgt)
   {
      return this.startCreate().hasServerMessageHandler(tgt).endCreate();
   }

   public ServerMessageHandler getServerMessageHandler()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MessageHandlerPool) this.getCurrentMatch()).getServerMessageHandler();
      }
      return null;
   }

   public AbstractHandlerPO hasHandler()
   {
      AbstractHandlerPO result = new AbstractHandlerPO(new AbstractHandler[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MessageHandlerPool.PROPERTY_HANDLER, result);
      
      return result;
   }

   public AbstractHandlerPO createHandler()
   {
      return this.startCreate().hasHandler().endCreate();
   }

   public MessageHandlerPoolPO hasHandler(AbstractHandlerPO tgt)
   {
      return hasLinkConstraint(tgt, MessageHandlerPool.PROPERTY_HANDLER);
   }

   public MessageHandlerPoolPO createHandler(AbstractHandlerPO tgt)
   {
      return this.startCreate().hasHandler(tgt).endCreate();
   }

   public AbstractHandlerSet getHandler()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MessageHandlerPool) this.getCurrentMatch()).getHandler();
      }
      return null;
   }

   public MessageHandlerPoolPO hasInputBuffer(LinkedList<String> value)
   {
      new AttributeConstraint()
      .withAttrName(MessageHandlerPool.PROPERTY_INPUTBUFFER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public MessageHandlerPoolPO createInputBuffer(LinkedList<String> value)
   {
      this.startCreate().hasInputBuffer(value).endCreate();
      return this;
   }
   
   public LinkedBlockingQueue<String> getInputBuffer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MessageHandlerPool) getCurrentMatch()).getInputBuffer();
      }
      return null;
   }
   
   public MessageHandlerPoolPO withInputBuffer(LinkedBlockingQueue<String> value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MessageHandlerPool) getCurrentMatch()).setInputBuffer(value);
      }
      return this;
   }
   public ZombieFighterPO hasZombieFighter()
   {
      ZombieFighterPO result = new ZombieFighterPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MessageHandlerPool.PROPERTY_ZOMBIEFIGHTER, result);
      
      return result;
   }

   public ZombieFighterPO createZombieFighter()
   {
      return this.startCreate().hasZombieFighter().endCreate();
   }

   public MessageHandlerPoolPO hasZombieFighter(ZombieFighterPO tgt)
   {
      return hasLinkConstraint(tgt, MessageHandlerPool.PROPERTY_ZOMBIEFIGHTER);
   }

   public MessageHandlerPoolPO createZombieFighter(ZombieFighterPO tgt)
   {
      return this.startCreate().hasZombieFighter(tgt).endCreate();
   }

   public ZombieFighter getZombieFighter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MessageHandlerPool) this.getCurrentMatch()).getZombieFighter();
      }
      return null;
   }

   public MessageHandlerPoolPO hasInputBuffer(LinkedBlockingQueue<String> value)
   {
      new AttributeConstraint()
      .withAttrName(MessageHandlerPool.PROPERTY_INPUTBUFFER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public MessageHandlerPoolPO createInputBuffer(LinkedBlockingQueue<String> value)
   {
      this.startCreate().hasInputBuffer(value).endCreate();
      return this;
   }

}
