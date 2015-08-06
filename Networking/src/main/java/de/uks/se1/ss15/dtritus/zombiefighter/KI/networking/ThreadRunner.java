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

package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.ThreadExtension;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.util.ThreadExtensionSet;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class ThreadRunner implements PropertyChangeInterface {

	public ThreadRunner() {
		this.Threads = new ThreadExtensionSet();
	}

	// ==========================================================================

	public void shutdown() {
		for (ThreadExtension threadExtension : Threads) {
			threadExtension.interrupt();

			// TODO warten bis thread angehalten
		}
	}

	// ==========================================================================

	public boolean createThread(java.lang.Runnable runnable) {
		if (!this.Threads.getRunnableContent().contains(runnable)) {
			// System.out.println("Creating Thread");
			ThreadExtension threadExtension = new ThreadExtension(runnable);
			if (this.Threads.add(threadExtension)) {
				// threadExtension.start();
				return true;
			}
		}
		return false;
	}

	// ==========================================================================

	public boolean createAndStartThread(java.lang.Runnable runnable) {
		if (createThread(runnable)) {
			if (startThread(runnable)) {
				return true;
			}
		}
		return false;
	}

	// ==========================================================================

	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	// ==========================================================================

	public void removeYou() {
		setServerMessageHandler(null);
		withoutThreads(this.getThreads().toArray(
				new ThreadExtension[this.getThreads().size()]));
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	/********************************************************************
	 * <pre>
	 *              one                       one
	 * ThreadRunner ----------------------------------- ServerMessageHandler
	 *              ThreadRunner                   ServerMessageHandler
	 * </pre>
	 */

	public static final String PROPERTY_SERVERMESSAGEHANDLER = "ServerMessageHandler";

	private ServerMessageHandler ServerMessageHandler = null;

	public ServerMessageHandler getServerMessageHandler() {
		return this.ServerMessageHandler;
	}

	public boolean setServerMessageHandler(ServerMessageHandler value) {
		boolean changed = false;

		if (this.ServerMessageHandler != value) {
			ServerMessageHandler oldValue = this.ServerMessageHandler;

			if (this.ServerMessageHandler != null) {
				this.ServerMessageHandler = null;
				oldValue.setThreadRunner(null);
			}

			this.ServerMessageHandler = value;

			if (value != null) {
				value.withThreadRunner(this);
			}

			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_SERVERMESSAGEHANDLER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ThreadRunner withServerMessageHandler(ServerMessageHandler value) {
		setServerMessageHandler(value);
		return this;
	}

	public ServerMessageHandler createServerMessageHandler() {
		ServerMessageHandler value = new ServerMessageHandler();
		withServerMessageHandler(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * ThreadRunner ----------------------------------- ThreadExtension
	 *              ThreadRunner                   Threads
	 * </pre>
	 */

	public static final String PROPERTY_THREADS = "Threads";

	private ThreadExtensionSet Threads = new ThreadExtensionSet();

	public ThreadExtensionSet getThreads() {
		if (this.Threads == null) {
			return ThreadExtensionSet.EMPTY_SET;
		}

		return this.Threads;
	}

	public ThreadRunner withThreads(ThreadExtension... value) {
		if (value == null) {
			return this;
		}
		for (ThreadExtension item : value) {
			if (item != null) {
				if (this.Threads == null) {
					this.Threads = new ThreadExtensionSet();
				}

				boolean changed = this.Threads.add(item);

				if (changed) {
					item.withThreadRunner(this);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_THREADS, null, item);
				}
			}
		}
		return this;
	}

	public ThreadRunner withoutThreads(ThreadExtension... value) {
		for (ThreadExtension item : value) {
			if ((this.Threads != null) && (item != null)) {
				if (this.Threads.remove(item)) {
					item.setThreadRunner(null);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_THREADS, item, null);
				}
			}
		}
		return this;
	}

	/**
	 * Isn't needed
	 * 
	 * @return
	 */
	public ThreadExtension createThreads() {
		// ThreadExtension value = new ThreadExtension();
		// withThreads(value);
		return null;
	}

	private ThreadExtension getThread(java.lang.Runnable runnable) {
		for (ThreadExtension threadExtension : Threads) {
			if (threadExtension.getRunnableContent().equals(runnable)) {
				return threadExtension;
			}
		}
		return null;
	}

	// ==========================================================================

	public boolean startThread(java.lang.Runnable runnable) {
		ThreadExtension thread = getThread(runnable);
		if (thread != null) {
			thread.start();
			return true;
		}
		return false;
	}

	// ==========================================================================

	public boolean interruptThread(java.lang.Runnable runnable) {
		ThreadExtension thread = getThread(runnable);
		if (thread != null) {
			thread.interrupt();
			return true;
		}
		return false;
	}

}
