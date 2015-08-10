package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.reflections.Reflections;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.Agents.Agent;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.ZombieFightGame;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ActionList;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ThreadPool;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.util.ZFAction;

public class AgentController {

	private ThreadPool threadPool;
	private int iterations = 1;

	public AgentController(int iterations) {
		this.iterations = iterations;
		initializeAgents();
		threadPool = new ThreadPool(getAgents().size(), getAgents().size() * this.iterations, 5000,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	LinkedHashMap<String, Agent> Agents = new LinkedHashMap<>();

	public LinkedHashMap<String, Agent> getAgents() {
		return Agents;
	}

	public Agent removeAgent(String key) {
		return getAgents().remove(key);
	}

	public Agent addAgent(String key, Agent value) {
		return getAgents().put(key, value);
	}

	/**
	 * Loads all Classes in the Agent package, that are subclasses of Agent
	 */
	private void initializeAgents() {
		// Load All Agent-Classes in the following Package
		Reflections reflections = new Reflections("de.uks.se1.ss15.dtritus.zombiefighter.KI.Agents");
		Set<Class<? extends Agent>> allAgentClasses = reflections.getSubTypesOf(Agent.class);

		ClassLoader classLoader = this.getClass().getClassLoader();

		// And now Instantiate them
		for (Class<? extends Agent> class1 : allAgentClasses) {
			try {
				// And Put them in Agents
				try {
					initializeAgents(class1.getConstructor().newInstance());
				} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					e.printStackTrace();
				}
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

	public ActionList calculateAction(ZombieFightGame game) {
		LinkedHashMap<String, Agent> agents = getAgents();
		Collection<Agent> values = agents.values();

		// Default Return Value
		ActionList actionList = new ActionList();

		for (Agent agent : values) {
			actionList = agent.calculateAction(calculateModel(new ActionList()));
		}

		return actionList;
	}

	private ZombieFightGame calculateModel(ActionList actions) {

		return Mediator.getInstance().getZombieFighter().getCurrentGame();
	}

	public void shutdown() {
		threadPool.shutdown();
	}

}
