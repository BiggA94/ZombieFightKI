package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.Agents.Agent;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ThreadPool;

/**
 * @author Alexander
 *
 */
public class KI {

	static ZombieFightInitializationThread zombieFightInitializationThread;
	private ThreadPool threadPool;

	/**
	 * See also{@link ZombieFightInitializationThread}<br/>
	 * And see also
	 * {@link ZombieFightInitializationThread#ZombieFightInitializationThread(KI, String[])
	 * this}
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public KI(String[] args) throws InterruptedException, ExecutionException {
		threadPool = new ThreadPool(1, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

		zombieFightInitializationThread = new ZombieFightInitializationThread(this, args);
		Future<Boolean> zombieFightInitialization = threadPool.submit(zombieFightInitializationThread, true);
		// Wait for the Thread to be Finished
		zombieFightInitialization.get();

		initializeAgents();
	}

	private void initializeAgents() {
		// TODO initializeAgents(new XYAgent());
	}

	private void initializeAgents(Agent... agents) {
		for (Agent agent : agents) {
			addAgent(agent.getName(), agent);
		}
	}

	protected boolean sendString(String message) {
		return Mediator.getInstance().getZombieFighter().getServerMessageHandler().sendString(message);
	}

	public void close() {
		ZFState state = Mediator.getInstance().getState();
		if (state.equals(ZFState.STOPPED)) {
			return;
		} else {
			if (!state.equals(ZFState.GAME_LEFT) && !state.equals(ZFState.STOPPED)
					&& !state.equals(ZFState.LOGGED_OUT)) {
				Mediator.getInstance().getPropertyChangeSupport().addPropertyChangeListener(Mediator.PROPERTY_STATE,
						new PropertyChangeListener() {
							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (evt.getNewValue().equals(ZFState.GAME_LEFT)) {
									Mediator.getInstance().interrupt();
								}
							}
						});
				sendString("{\"@action\":\"LEAVE_GAME\"}");
			} else {
				Mediator.getInstance().interrupt();
			}
		}
		threadPool.shutdown();
	}

	LinkedHashMap<String, Agent> Agents = new LinkedHashMap<>();

	public LinkedHashMap<String, Agent> getAgents() {
		return Agents;
	}

	public Object removeAgent(String key) {
		return getAgents().remove(key);
	}

	public Object addAgent(String key, Agent value) {
		return getAgents().put(key, value);
	}
}
