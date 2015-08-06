package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZombieFighterPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.MessageHandlerPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.MessageHandlerPoolPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ServerMessageHandlerPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadRunnerPO;

import java.net.Socket;

import org.sdmlib.models.pattern.AttributeConstraint;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.Thread;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ServerMessageHandlerPO extends PatternObject<ServerMessageHandlerPO, ServerMessageHandler>
{

    public ServerMessageHandlerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ServerMessageHandlerSet matches = new ServerMessageHandlerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ServerMessageHandler) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ServerMessageHandlerPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ServerMessageHandlerPO(ServerMessageHandler... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public boolean sendString(String message)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).sendString(message);
      }
      return false;
   }

   public ServerMessageHandlerPO hasServerConnection(Socket value)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_SERVERCONNECTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO createServerConnection(Socket value)
   {
      this.startCreate().hasServerConnection(value).endCreate();
      return this;
   }
   
   public Socket getServerConnection()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).getServerConnection();
      }
      return null;
   }
   
   public ServerMessageHandlerPO withServerConnection(Socket value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerMessageHandler) getCurrentMatch()).setServerConnection(value);
      }
      return this;
   }
   
   public ServerMessageHandlerPO hasThread(Thread value)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_THREAD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO createThread(Thread value)
   {
      this.startCreate().hasThread(value).endCreate();
      return this;
   }
   
   public Thread getThread()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).getThread();
      }
      return null;
   }
   
   public ServerMessageHandlerPO withThread(Thread value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerMessageHandler) getCurrentMatch()).setThread(value);
      }
      return this;
   }
   
   public MessageHandlerPoolPO hasMessageHandlerPool()
   {
      MessageHandlerPoolPO result = new MessageHandlerPoolPO(new MessageHandlerPool[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ServerMessageHandler.PROPERTY_MESSAGEHANDLERPOOL, result);
      
      return result;
   }

   public MessageHandlerPoolPO createMessageHandlerPool()
   {
      return this.startCreate().hasMessageHandlerPool().endCreate();
   }

   public ServerMessageHandlerPO hasMessageHandlerPool(MessageHandlerPoolPO tgt)
   {
      return hasLinkConstraint(tgt, ServerMessageHandler.PROPERTY_MESSAGEHANDLERPOOL);
   }

   public ServerMessageHandlerPO createMessageHandlerPool(MessageHandlerPoolPO tgt)
   {
      return this.startCreate().hasMessageHandlerPool(tgt).endCreate();
   }

   public MessageHandlerPool getMessageHandlerPool()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) this.getCurrentMatch()).getMessageHandlerPool();
      }
      return null;
   }

   
   //==========================================================================
   
   public boolean keepAlive(int timeoutValue)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).keepAlive(timeoutValue);
      }
      return false;
   }

   public ServerMessageHandlerPO hasTimeoutValue(int value)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_TIMEOUTVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO hasTimeoutValue(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_TIMEOUTVALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO createTimeoutValue(int value)
   {
      this.startCreate().hasTimeoutValue(value).endCreate();
      return this;
   }
   
   public int getTimeoutValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).getTimeoutValue();
      }
      return 0;
   }
   
   public ServerMessageHandlerPO withTimeoutValue(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerMessageHandler) getCurrentMatch()).setTimeoutValue(value);
      }
      return this;
   }
   
   
   //==========================================================================
   
   public boolean isConnected()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).isConnected();
      }
      return false;
   }

 
   public ThreadRunnerPO hasThreadRunner()
   {
      ThreadRunnerPO result = new ThreadRunnerPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ServerMessageHandler.PROPERTY_THREADRUNNER, result);
      
      return result;
   }

   public ThreadRunnerPO createThreadRunner()
   {
      return this.startCreate().hasThreadRunner().endCreate();
   }

   public ServerMessageHandlerPO hasThreadRunner(ThreadRunnerPO tgt)
   {
      return hasLinkConstraint(tgt, ServerMessageHandler.PROPERTY_THREADRUNNER);
   }

   public ServerMessageHandlerPO createThreadRunner(ThreadRunnerPO tgt)
   {
      return this.startCreate().hasThreadRunner(tgt).endCreate();
   }

   public ThreadRunner getThreadRunner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) this.getCurrentMatch()).getThreadRunner();
      }
      return null;
   }

   public ZombieFighterPO hasZombieFighter()
   {
      ZombieFighterPO result = new ZombieFighterPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.global.ZombieFighter[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ServerMessageHandler.PROPERTY_ZOMBIEFIGHTER, result);
      
      return result;
   }

   public ZombieFighterPO createZombieFighter()
   {
      return this.startCreate().hasZombieFighter().endCreate();
   }

   public ServerMessageHandlerPO hasZombieFighter(ZombieFighterPO tgt)
   {
      return hasLinkConstraint(tgt, ServerMessageHandler.PROPERTY_ZOMBIEFIGHTER);
   }

   public ServerMessageHandlerPO createZombieFighter(ZombieFighterPO tgt)
   {
      return this.startCreate().hasZombieFighter(tgt).endCreate();
   }

   public ZombieFighter getZombieFighter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) this.getCurrentMatch()).getZombieFighter();
      }
      return null;
   }

   public ServerMessageHandlerPO hasLastMessage(String value)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_LASTMESSAGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO hasLastMessage(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_LASTMESSAGE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO createLastMessage(String value)
   {
      this.startCreate().hasLastMessage(value).endCreate();
      return this;
   }
   
   public String getLastMessage()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).getLastMessage();
      }
      return null;
   }
   
   public ServerMessageHandlerPO withLastMessage(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerMessageHandler) getCurrentMatch()).setLastMessage(value);
      }
      return this;
   }
   
   public ServerMessageHandlerPO hasLastMessageHandled(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_LASTMESSAGEHANDLED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO createLastMessageHandled(boolean value)
   {
      this.startCreate().hasLastMessageHandled(value).endCreate();
      return this;
   }
   
   public boolean getLastMessageHandled()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).isLastMessageHandled();
      }
      return false;
   }
   
   public ServerMessageHandlerPO withLastMessageHandled(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerMessageHandler) getCurrentMatch()).setLastMessageHandled(value);
      }
      return this;
   }
   
   
   //==========================================================================
   
   public boolean send(byte[] message)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).send(message);
      }
      return false;
   }

   public ServerMessageHandlerPO hasSendBuffer(LinkedBlockingDeque value)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_SENDBUFFER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO createSendBuffer(LinkedBlockingDeque value)
   {
      this.startCreate().hasSendBuffer(value).endCreate();
      return this;
   }

   
   public LinkedBlockingDeque getSendBuffer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).getSendBuffer();
      }
      return null;
   }
   
   public ServerMessageHandlerPO withSendBuffer(LinkedBlockingDeque value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerMessageHandler) getCurrentMatch()).setSendBuffer(value);
      }
      return this;
   }

   
   //==========================================================================
   
   public void connect(String url, int port)
   {
      if (this.getPattern().getHasMatch())
      {
          ((ServerMessageHandler) getCurrentMatch()).connect(url, port);
      }
   }

   public ServerMessageHandlerPO hasLastMessageShouldBeHandled(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_LASTMESSAGESHOULDBEHANDLED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO createLastMessageShouldBeHandled(boolean value)
   {
      this.startCreate().hasLastMessageShouldBeHandled(value).endCreate();
      return this;
   }
   
   public boolean getLastMessageShouldBeHandled()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).isLastMessageShouldBeHandled();
      }
      return false;
   }
   
   public ServerMessageHandlerPO withLastMessageShouldBeHandled(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerMessageHandler) getCurrentMatch()).setLastMessageShouldBeHandled(value);
      }
      return this;
   }
   
   public ServerMessageHandlerPO hasJsonProtocol(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ServerMessageHandler.PROPERTY_JSONPROTOCOL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ServerMessageHandlerPO createJsonProtocol(boolean value)
   {
      this.startCreate().hasJsonProtocol(value).endCreate();
      return this;
   }
   
   public boolean getJsonProtocol()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerMessageHandler) getCurrentMatch()).isJsonProtocol();
      }
      return false;
   }
   
   public ServerMessageHandlerPO withJsonProtocol(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerMessageHandler) getCurrentMatch()).setJsonProtocol(value);
      }
      return this;
   }
   
}
