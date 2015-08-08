package de.uks.se1.ss15.dtritus.zombiefighter.KI.util;

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

}
