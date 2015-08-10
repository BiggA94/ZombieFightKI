package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.reflections.Reflections;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.Agents.Agent;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ThreadPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFAction;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFActionParameters.ZFParamSendZombie;

/**
 * @author Alexander
 *
 */
public class KI {

	static ZombieFightInitializationThread zombieFightInitializationThread;
	private ThreadPool threadPool;
	private ScheduledExecutorService agentRunnerThreadPool;
	private ScheduledFuture<?> scheduledFuture;

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
		agentRunnerThreadPool = Executors.newScheduledThreadPool(1);
		scheduledFuture = agentRunnerThreadPool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				if (Mediator.getInstance().getState().equals(ZFState.INGAME_STOPPED)
						|| Mediator.getInstance().getState().equals(ZFState.GAME_LEFT)) {
					KI.this.scheduledFuture.cancel(true);
				}
				if (!Mediator.getInstance().getState().equals(ZFState.INGAME_RUNNING)) {
					return;
				}
				ZFAction calculateAction = AgentController
						.calculateAction(Mediator.getInstance().getZombieFighter().getCurrentGame());
				doAction(calculateAction);
			}
		}, 5000, 100, TimeUnit.MILLISECONDS);
		try {
			scheduledFuture.get();
		} catch (CancellationException e) {

		} catch (Exception e) {

		}

	}

	private void doAction(ZFAction zfAction) {
		while (!Mediator.getInstance().getZombieFighter().getServerMessageHandler().getSendBuffer().isEmpty()
				&& !Mediator.getInstance().getZombieFighter().getServerMessageHandler().isLastMessageHandled()) {
			// wait
		}
		switch (zfAction.getAction()) {
		case SEND_ZOMBIE:
			if (((ZFParamSendZombie) zfAction.getParameter()).getZombieType() == null) {
				// Mediator.printDebugln("KI.doAction(): Action = SEND_ZOMBIE,
				// but ZombieType is null");
				break;
			}
			sendString("{\"@action\":\"CREATE_ZOMBIE\",\"properties\":{\"entry\":{\"key\":\"zombietype\",\"value\":\""
					+ ((ZFParamSendZombie) zfAction.getParameter()).getZombieType().getKey() + "\"}}}");
			break;

		default:
			break;
		}
	}

	protected boolean sendString(String message) {
		if (!Mediator.getInstance().isConnected())
			return false;
		return Mediator.getInstance().getZombieFighter().getServerMessageHandler().sendString(message);
	}

	public void close() {
		ZFState state = Mediator.getInstance().getState();
		if (state.equals(ZFState.STOPPED) || state.equals(ZFState.STARTED)) {
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
		agentRunnerThreadPool.shutdown();
	}

}
