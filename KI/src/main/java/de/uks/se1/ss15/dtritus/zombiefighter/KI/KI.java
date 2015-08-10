package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ActionList;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFAction;

/**
 * @author Alexander
 *
 */
public class KI {

	static ZombieFightInitializationThread zombieFightInitializationThread;
	private ScheduledExecutorService agentRunnerThreadPool;
	private ScheduledFuture<?> scheduledFuture;
	private AgentController agentController;

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
		agentRunnerThreadPool = Executors.newScheduledThreadPool(1);

		zombieFightInitializationThread = new ZombieFightInitializationThread(this, args);
		Future<Boolean> zombieFightInitialization = agentRunnerThreadPool.submit(zombieFightInitializationThread, true);
		// Wait for the Thread to be Finished
		zombieFightInitialization.get();
		agentController = new AgentController(1);
		scheduledFuture = agentRunnerThreadPool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				if (Mediator.getInstance().getState().equals(ZFState.INGAME_STOPPED)
						|| Mediator.getInstance().getState().equals(ZFState.GAME_LEFT)) {
					System.out.println("KI.KI(...).new Runnable() {...}.run()  - Cancel");
					KI.this.scheduledFuture.cancel(true);
				}
				if (!Mediator.getInstance().getState().equals(ZFState.INGAME_RUNNING)) {
					return;
				}

				ActionList calculateAction = agentController
						.calculateAction(Mediator.getInstance().getZombieFighter().getCurrentGame());

				doAction(calculateAction);
			}
		}, 5000, 100, TimeUnit.MILLISECONDS);
		scheduledFuture.get();
	}

	private void doAction(ActionList actionList) {
		while (!Mediator.getInstance().getZombieFighter().getServerMessageHandler().getSendBuffer().isEmpty()) {
			// wait for the old Strings to be sent
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (ZFAction action : actionList.getActionHistory()) {
			sendString(action.getMessage());
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
		agentController.shutdown();
		agentRunnerThreadPool.shutdown();
	}

}
