/*
   Copyright (c) 2015 Major 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util;

import org.sdmlib.models.modelsets.SDMSet;

import java.util.Collection;
import org.sdmlib.models.modelsets.booleanList;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ServerMessageHandler;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadExtension;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadRunner;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ServerMessageHandlerSet;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadExtensionSet;

import org.sdmlib.models.modelsets.ObjectSet;

import java.util.Collections;

public class ThreadRunnerSet extends SDMSet<ThreadRunner> {

	public static final ThreadRunnerSet EMPTY_SET = new ThreadRunnerSet()
			.withReadOnly(true);

	public ThreadRunnerPO hasThreadRunnerPO() {
		return new ThreadRunnerPO(this.toArray(new ThreadRunner[this.size()]));
	}

	@Override
	public String getEntryType() {
		return "de.uks.se1.ss15.dtritus.zombiefighter.networking.ThreadRunner";
	}

	@SuppressWarnings("unchecked")
	public ThreadRunnerSet with(Object value) {
		if (value instanceof java.util.Collection) {
			this.addAll((Collection<ThreadRunner>) value);
		} else if (value != null) {
			this.add((ThreadRunner) value);
		}

		return this;
	}

	public ThreadRunnerSet without(ThreadRunner value) {
		this.remove(value);
		return this;
	}

	// ==========================================================================

	public ThreadRunnerSet shutdown() {
		for (ThreadRunner obj : this) {
			obj.shutdown();
		}
		return this;
	}

	// ==========================================================================

	public booleanList createThread(java.lang.Runnable runnable) {
		booleanList result = new booleanList();
		for (ThreadRunner obj : this) {
			result.add(obj.createThread(runnable));
		}
		return result;
	}

	public ServerMessageHandlerSet getServerMessageHandler() {
		ServerMessageHandlerSet result = new ServerMessageHandlerSet();

		for (ThreadRunner obj : this) {
			result.add(obj.getServerMessageHandler());
		}

		return result;
	}

	public ThreadRunnerSet hasServerMessageHandler(Object value) {
		ObjectSet neighbors = new ObjectSet();

		if (value instanceof Collection) {
			neighbors.addAll((Collection<?>) value);
		} else {
			neighbors.add(value);
		}

		ThreadRunnerSet answer = new ThreadRunnerSet();

		for (ThreadRunner obj : this) {
			if (neighbors.contains(obj.getServerMessageHandler())) {
				answer.add(obj);
			}
		}

		return answer;
	}

	public ThreadRunnerSet withServerMessageHandler(ServerMessageHandler value) {
		for (ThreadRunner obj : this) {
			obj.withServerMessageHandler(value);
		}

		return this;
	}

	public ThreadExtensionSet getThreads() {
		ThreadExtensionSet result = new ThreadExtensionSet();

		for (ThreadRunner obj : this) {
			result.addAll(obj.getThreads());
		}

		return result;
	}

	public ThreadRunnerSet hasThreads(Object value) {
		ObjectSet neighbors = new ObjectSet();

		if (value instanceof Collection) {
			neighbors.addAll((Collection<?>) value);
		} else {
			neighbors.add(value);
		}

		ThreadRunnerSet answer = new ThreadRunnerSet();

		for (ThreadRunner obj : this) {
			if (!Collections.disjoint(neighbors, obj.getThreads())) {
				answer.add(obj);
			}
		}

		return answer;
	}

	public ThreadRunnerSet withThreads(ThreadExtension value) {
		for (ThreadRunner obj : this) {
			obj.withThreads(value);
		}

		return this;
	}

	public ThreadRunnerSet withoutThreads(ThreadExtension value) {
		for (ThreadRunner obj : this) {
			obj.withoutThreads(value);
		}

		return this;
	}

	// ==========================================================================

	public booleanList startThread(java.lang.Runnable runnable) {
		booleanList result = new booleanList();
		for (ThreadRunner obj : this) {
			result.add(obj.startThread(runnable));
		}
		return result;
	}

	// ==========================================================================

	public booleanList interruptThread(java.lang.Runnable runnable) {
		booleanList result = new booleanList();
		for (ThreadRunner obj : this) {
			result.add(obj.interruptThread(runnable));
		}
		return result;
	}

   
   //==========================================================================
   
   public booleanList createAndStartThread(java.lang.Runnable runnable)
   {
      booleanList result = new booleanList();
      for (ThreadRunner obj : this)
      {
         result.add(obj.createAndStartThread(runnable));
      }
      return result;
   }

}
