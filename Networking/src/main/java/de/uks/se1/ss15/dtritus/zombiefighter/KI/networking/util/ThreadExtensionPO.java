package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadExtension;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadExtensionPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadRunnerPO;

import java.lang.Runnable;
import org.sdmlib.models.pattern.AttributeConstraint;

public class ThreadExtensionPO extends PatternObject<ThreadExtensionPO, ThreadExtension>
{

    public ThreadExtensionSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ThreadExtensionSet matches = new ThreadExtensionSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ThreadExtension) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ThreadExtensionPO(){
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ThreadExtensionPO(ThreadExtension... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ThreadRunnerPO hasThreadRunner()
   {
      ThreadRunnerPO result = new ThreadRunnerPO(new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ThreadExtension.PROPERTY_THREADRUNNER, result);
      
      return result;
   }

   public ThreadRunnerPO createThreadRunner()
   {
      return this.startCreate().hasThreadRunner().endCreate();
   }

   public ThreadExtensionPO hasThreadRunner(ThreadRunnerPO tgt)
   {
      return hasLinkConstraint(tgt, ThreadExtension.PROPERTY_THREADRUNNER);
   }

   public ThreadExtensionPO createThreadRunner(ThreadRunnerPO tgt)
   {
      return this.startCreate().hasThreadRunner(tgt).endCreate();
   }

   public ThreadRunner getThreadRunner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ThreadExtension) this.getCurrentMatch()).getThreadRunner();
      }
      return null;
   }

   public ThreadExtensionPO hasRunnableContent(Runnable value)
   {
      new AttributeConstraint()
      .withAttrName(ThreadExtension.PROPERTY_RUNNABLECONTENT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ThreadExtensionPO createRunnableContent(Runnable value)
   {
      this.startCreate().hasRunnableContent(value).endCreate();
      return this;
   }
   
   public Runnable getRunnableContent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ThreadExtension) getCurrentMatch()).getRunnableContent();
      }
      return null;
   }
   
   public ThreadExtensionPO withRunnableContent(Runnable value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ThreadExtension) getCurrentMatch()).setRunnableContent(value);
      }
      return this;
   }
   
}
