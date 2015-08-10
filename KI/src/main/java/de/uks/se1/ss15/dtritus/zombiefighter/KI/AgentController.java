package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.util.LinkedHashMap;
import java.util.Set;

import org.reflections.Reflections;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.Agents.Agent;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFAction;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFActions;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFActionParameters.ZFParamSendZombie;

public class AgentController {

	public AgentController() {
		// TODO Auto-generated constructor stub
	}
	LinkedHashMap<String, Agent> Agents = new LinkedHashMap<>();

	public LinkedHashMap<String, Agent> getAgents() {
		return Agents;
	}

	public Object removeAgent(String key) {
		return getAgents().remove(key);
	}

	public Object addAgent(String key, Agent value) {
		return getAgents().put(key, value);
	}
	
	/**
	 * Loads all Classes in the Agent package, that are subclasses of Agent
	 */
	private void initializeAgents() {
		// Load All Agent-Classes in the following Package
		Reflections reflections = new Reflections("de.uks.se1.ss15.dtritus.zombiefighter.KI.Agents");
		Set<Class<? extends Agent>> allAgentClasses = reflections.getSubTypesOf(Agent.class);

		// And now Instantiate them
		for (Class<? extends Agent> class1 : allAgentClasses) {
			try {
				// And Put them in Agents
				initializeAgents(class1.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		System.err.println(getAgents());
	}

	private void initializeAgents(Agent... agents) {
		for (Agent agent : agents) {
			addAgent(agent.getName(), agent);
		}
	}

	public static ZFAction calculateAction(ZombieFightGame game) {
		return new ZFAction(ZFActions.SEND_ZOMBIE, new ZFParamSendZombie(game));
	}

}
