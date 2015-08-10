package de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFActionParameters;

import java.util.Set;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.UserAssets;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieTypeToZombieMapping;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;

public class ZFParamSendZombie {

	public ZFParamSendZombie(ZombieFightGame game) {
		// Finds the Best solution to find income
		Set<ZombieTypeToZombieMapping> zombieDescriptions = game.getGuide().getZombieDescriptions();
		ZombieTypeToZombieMapping zombieType = null;
		UserAssets user = null;
		Set<UserAssets> users = game.getUsers();
		for (UserAssets userAssets : users) {
			if (userAssets.getUser().getNickname()
					.equals(Mediator.getInstance().getZombieFighter().getCurrentUser().getNick())) {
				user = userAssets;
			}
		}
		if (user == null) {
			System.err.println("Couldn't find current user!");
			return;
		}
		for (ZombieTypeToZombieMapping zombieTypeToZombieMapping : zombieDescriptions) {
			if (zombieType == null) {
				if (zombieTypeToZombieMapping.getValue().iterator().next().getPrice() < user.getBalance()) {
					zombieType = zombieTypeToZombieMapping;
				}
			} else {
				if (zombieTypeToZombieMapping.getValue().iterator().next().getPrice() < user.getBalance()) {
					if (zombieTypeToZombieMapping.getValue().iterator().next().getIncome() > zombieType.getValue()
							.iterator().next().getIncome()) {
						zombieType = zombieTypeToZombieMapping;
					}
				}
			}
		}

		this.zombieType = zombieType;
	}

	ZombieTypeToZombieMapping zombieType = null;

	public ZombieTypeToZombieMapping getZombieType() {
		return zombieType;
	}
}
