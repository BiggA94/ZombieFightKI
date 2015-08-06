package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ServerMessageHandlerPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadExtensionPO;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadExtensionSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadRunnerPO;

import java.lang.Runnable;
import org.sdmlib.models.pattern.AttributeConstraint;

public class ThreadRunnerPO extends PatternObject<ThreadRunnerPO, ThreadRunner> {

	public ThreadRunnerSet allMatches() {
		this.setDoAllMatches(true);

		ThreadRunnerSet matches = new ThreadRunnerSet();

		while (this.getPattern().getHasMatch()) {
			matches.add((ThreadRunner) this.getCurrentMatch());

			this.getPattern().findMatch();
		}

		return matches;
	}

	public ThreadRunnerPO() {
		newInstance(de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator
				.createIdMap("PatternObjectType"));
	}

	public ThreadRunnerPO(ThreadRunner... hostGraphObject) {
		if (hostGraphObject == null || hostGraphObject.length < 1) {
			return;
		}
		newInstance(
				de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.CreatorCreator
						.createIdMap("PatternObjectType"), hostGraphObject);
	}

	// ==========================================================================

	public void shutdown() {
		if (this.getPattern().getHasMatch()) {
			((ThreadRunner) getCurrentMatch()).shutdown();
		}
	}

	// ==========================================================================

	public boolean createThread(java.lang.Runnable runnable) {
		if (this.getPattern().getHasMatch()) {
			return ((ThreadRunner) getCurrentMatch()).createThread(runnable);
		}
		return false;
	}

	public ServerMessageHandlerPO hasServerMessageHandler() {
		ServerMessageHandlerPO result = new ServerMessageHandlerPO(
				new ServerMessageHandler[] {});

		result.setModifier(this.getPattern().getModifier());
		super.hasLink(ThreadRunner.PROPERTY_SERVERMESSAGEHANDLER, result);

		return result;
	}

	public ServerMessageHandlerPO createServerMessageHandler() {
		return this.startCreate().hasServerMessageHandler().endCreate();
	}

	public ThreadRunnerPO hasServerMessageHandler(ServerMessageHandlerPO tgt) {
		return hasLinkConstraint(tgt,
				ThreadRunner.PROPERTY_SERVERMESSAGEHANDLER);
	}

	public ThreadRunnerPO createServerMessageHandler(ServerMessageHandlerPO tgt) {
		return this.startCreate().hasServerMessageHandler(tgt).endCreate();
	}

	public ServerMessageHandler getServerMessageHandler() {
		if (this.getPattern().getHasMatch()) {
			return ((ThreadRunner) this.getCurrentMatch())
					.getServerMessageHandler();
		}
		return null;
	}

	public ThreadExtensionPO hasThreads() {
		ThreadExtensionPO result = new ThreadExtensionPO(
				new de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadExtension[] {});

		result.setModifier(this.getPattern().getModifier());
		super.hasLink(ThreadRunner.PROPERTY_THREADS, result);

		return result;
	}

	public ThreadExtensionPO createThreads() {
		return this.startCreate().hasThreads().endCreate();
	}

	public ThreadRunnerPO hasThreads(ThreadExtensionPO tgt) {
		return hasLinkConstraint(tgt, ThreadRunner.PROPERTY_THREADS);
	}

	public ThreadRunnerPO createThreads(ThreadExtensionPO tgt) {
		return this.startCreate().hasThreads(tgt).endCreate();
	}

	public ThreadExtensionSet getThreads() {
		if (this.getPattern().getHasMatch()) {
			return ((ThreadRunner) this.getCurrentMatch()).getThreads();
		}
		return null;
	}

	public ThreadRunnerPO hasRunnableContent(Runnable value) {
		new AttributeConstraint().withTgtValue(value).withSrc(this)
				.withModifier(this.getPattern().getModifier())
				.withPattern(this.getPattern());

		super.hasAttr();

		return this;
	}

	public ThreadRunnerPO createRunnableContent(Runnable value) {
		this.startCreate().hasRunnableContent(value).endCreate();
		return this;
	}

	// ==========================================================================

	public boolean startThread(java.lang.Runnable runnable) {
		if (this.getPattern().getHasMatch()) {
			return ((ThreadRunner) getCurrentMatch()).startThread(runnable);
		}
		return false;
	}

	// ==========================================================================

	public boolean interruptThread(java.lang.Runnable runnable) {
		if (this.getPattern().getHasMatch()) {
			return ((ThreadRunner) getCurrentMatch()).interruptThread(runnable);
		}
		return false;
	}

   
   //==========================================================================
   
   public boolean createAndStartThread(java.lang.Runnable runnable)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ThreadRunner) getCurrentMatch()).createAndStartThread(runnable);
      }
      return false;
   }

}
