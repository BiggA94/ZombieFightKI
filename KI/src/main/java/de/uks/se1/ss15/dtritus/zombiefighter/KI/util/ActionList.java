package de.uks.se1.ss15.dtritus.zombiefighter.KI.util;

import java.util.LinkedList;

public class ActionList implements Cloneable {
	LinkedList<ZFAction> ActionHistory = new LinkedList<>();

	public LinkedList<ZFAction> getActionHistory() {
		return ActionHistory;
	}

	long rating = 1000;

	public long getRating() {
		return rating;
	}

}
