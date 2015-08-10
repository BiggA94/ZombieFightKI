package de.uks.se1.ss15.dtritus.zombiefighter.KI.util;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFActionParameters.ZFParamSendZombie;

public class ZFAction {

	public ZFAction(ZFActions action, Object parameter) {
		this.action = action;
		this.parameter = parameter;
	}

	ZFActions action;

	public ZFActions getAction() {
		return action;
	}

	Object parameter;

	public Object getParameter() {
		return parameter;
	}

	public String getMessage() {
		switch (getAction()) {
		case SEND_ZOMBIE:
			if (((ZFParamSendZombie) getParameter()).getZombieType() == null) {
				// Mediator.printDebugln("KI.doAction(): Action = SEND_ZOMBIE,
				// but ZombieType is null");
				break;
			}
			return ("{\"@action\":\"CREATE_ZOMBIE\",\"properties\":{\"entry\":{\"key\":\"zombietype\",\"value\":\""
					+ ((ZFParamSendZombie) getParameter()).getZombieType().getKey() + "\"}}}");
		default:
			break;
		}
		return ("{\"@action\":\"NOOP\"}");
	}

}
