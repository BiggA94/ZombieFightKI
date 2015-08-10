package de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFActionParameters;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieTypeToZombieMapping;

public class ZFParamSendZombie {

	public ZFParamSendZombie(ZombieFightGame game) {

	}

	ZombieTypeToZombieMapping zombieType = null;

	public ZombieTypeToZombieMapping getZombieType() {
		return zombieType;
	}

	public void setZombieType(ZombieTypeToZombieMapping zombieType) {
		this.zombieType = zombieType;
	}
}
