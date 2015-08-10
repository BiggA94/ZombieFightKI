package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.util.concurrent.ExecutionException;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util.ZFState;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		KI ki = new KI(args);

		Mediator.getInstance().getPropertyChangeSupport().addPropertyChangeListener(Mediator.PROPERTY_STATE, evt -> {
			if (evt.getNewValue() != null && evt.getNewValue().equals(ZFState.INGAME_STOPPED)) {
				ki.close();
			}
		});
	}

}
