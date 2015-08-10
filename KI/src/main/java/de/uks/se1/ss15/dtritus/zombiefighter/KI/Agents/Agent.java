package de.uks.se1.ss15.dtritus.zombiefighter.KI.Agents;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ActionList;

public abstract class Agent {

	protected ZombieFightGame game;

	public Agent(ZombieFightGame game) {
		this.game = game;
	}

	public Agent() {
		// TODO Auto-generated constructor stub
	}

	public abstract String getName();

	public abstract ActionList calculateAction(ZombieFightGame game);
}
