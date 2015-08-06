package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;

/**
 * @author Alexander
 *
 */
public class KI {

	static ZombieFightInitializationThread zombieFightInitializationThread;

	/**
	 * See also{@link ZombieFightInitializationThread}<br/>
	 * And see also
	 * {@link ZombieFightInitializationThread#ZombieFightInitializationThread(KI, String[])
	 * this}
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public KI(String[] args) throws InterruptedException {
		zombieFightInitializationThread = new ZombieFightInitializationThread(this, args);
		Thread thread = new Thread(zombieFightInitializationThread);
		thread.start();
		thread.join();
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
	}
}
