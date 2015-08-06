package de.uks.se1.ss15.dtritus.zombiefighter.KI.global.util;

/**
 * Shows the State of the Application
 * 
 * @author Alexander
 */
public enum ZFState {
	/**
	 * After the Program is started <br/>
	 * Set at Mediator.setRunning()
	 */
	STARTED,

	/**
	 * After the Connection is established <br/>
	 * Set at Mediator.connect(ZombieFighter)
	 */
	CONNECTED,

	/**
	 * After Login
	 */
	LOGGED_IN,

	/**
	 * After Logout
	 */
	LOGGED_OUT,

	/**
	 * After Loading and before Selection of Sector <br/>
	 * Set at JsonZombieFightGameHandler
	 */
	FIELD_SELECTION,

	/**
	 * After Selecting a Sector and while the Game isn't running
	 */
	INGAME_WAITING,

	/**
	 * Ingame and Playing
	 */
	INGAME_RUNNING,

	/**
	 * Ingame and Running changed from true to false
	 */
	INGAME_STOPPED,

	/**
	 * After Leaving a Game
	 */
	GAME_LEFT,

	/**
	 * After closing the Program and before opening it <br/>
	 * Set at Mediator.setRunning()
	 */
	STOPPED,
}
