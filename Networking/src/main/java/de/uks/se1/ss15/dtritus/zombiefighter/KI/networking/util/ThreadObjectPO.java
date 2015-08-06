package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadObject;

import java.lang.Thread;
import org.sdmlib.models.pattern.AttributeConstraint;

public class ThreadObjectPO extends PatternObject<ThreadObjectPO, ThreadObject>
{

    public ThreadObjectSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ThreadObjectSet matches = new ThreadObjectSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ThreadObject) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ThreadObjectPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ThreadObjectPO(ThreadObject... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void interrupt()
   {
      if (this.getPattern().getHasMatch())
      {
          ((ThreadObject) getCurrentMatch()).interrupt();
      }
   }

   
   //==========================================================================
   
   public void start()
   {
      if (this.getPattern().getHasMatch())
      {
          ((ThreadObject) getCurrentMatch()).start();
      }
   }

   public ThreadObjectPO hasThread(Thread value)
   {
      new AttributeConstraint()
      .withAttrName(ThreadObject.PROPERTY_THREAD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ThreadObjectPO createThread(Thread value)
   {
      this.startCreate().hasThread(value).endCreate();
      return this;
   }
   
   public Thread getThread()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ThreadObject) getCurrentMatch()).getThread();
      }
      return null;
   }
   
   public ThreadObjectPO withThread(Thread value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ThreadObject) getCurrentMatch()).setThread(value);
      }
      return this;
   }
   
}
