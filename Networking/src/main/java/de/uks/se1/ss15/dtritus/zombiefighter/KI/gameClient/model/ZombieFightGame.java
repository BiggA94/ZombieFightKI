package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class ZombieFightGame implements PropertyChangeInterface {

	// Instance Management
	public static Set<ZombieFightGame> instances = null;

	public ZombieFightGame() {
		if (instances == null) {
			instances = new HashSet<ZombieFightGame>();
		}

		instances.add(this);
	}

	public static ZombieFightGame getInstanceById(String id) {
		if (instances == null)
			return null;

		for (ZombieFightGame currentInstance : instances) {
			if (currentInstance.getId() != null && currentInstance.getId().equals(id)) {
				return currentInstance;
			}
		}
		return null;
	}

	public static boolean removeInstanceById(String id) {
		LinkedList<ZombieFightGame> list = new LinkedList<ZombieFightGame>();
		list.add(getInstanceById(id));
		return instances.removeAll(list);
	}

	// id:String
	public static final String PROPERTY_ID = "id";

	String id = null;

	public String getId() {
		return this.id;
	}

	public void setId(String value) {
		String oldValue = this.getId();
		this.id = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_ID, oldValue, value);
	}

	public ZombieFightGame withId(String value) {
		this.setId(value);
		return this;
	}

	// PropertyChange
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	// name:String
	public static final String PROPERTY_NAME = "name";

	String name = null;

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		String oldValue = this.getName();
		this.name = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
	}

	public ZombieFightGame withName(String value) {
		this.setName(value);
		return this;
	}

	// numPlayers:int
	public static final String PROPERTY_NUM_PLAYERS = "numPlayers";

	int numPlayers = 0;

	public int getNumPlayers() {
		return this.numPlayers;
	}

	public void setNumPlayers(int value) {
		int oldValue = this.getNumPlayers();
		this.numPlayers = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_NUM_PLAYERS, oldValue, value);
	}

	public ZombieFightGame withNumPlayers(int value) {
		this.setNumPlayers(value);
		return this;
	}

	// running:boolean
	public static final String PROPERTY_RUNNING = "running";

	boolean running = false;

	public boolean getRunning() {
		return this.running;
	}

	public void setRunning(boolean value) {
		boolean oldValue = this.getRunning();
		this.running = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_RUNNING, oldValue, value);
	}

	public ZombieFightGame withRunning(boolean value) {
		this.setRunning(value);
		return this;
	}

	// testGame:boolean
	public static final String PROPERTY_TEST_GAME = "testGame";

	boolean testGame = false;

	public boolean getTestGame() {
		return this.testGame;
	}

	public void setTestGame(boolean value) {
		boolean oldValue = this.getTestGame();
		this.testGame = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_TEST_GAME, oldValue, value);
	}

	public ZombieFightGame withTestGame(boolean value) {
		this.setTestGame(value);
		return this;
	}

	// nextIncome:double
	public static final String PROPERTY_NEXT_INCOME = "nextIncome";

	double nextIncome = 0;

	public double getNextIncome() {
		return this.nextIncome;
	}

	public void setNextIncome(double value) {
		double oldValue = this.getNextIncome();
		this.nextIncome = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT_INCOME, oldValue, value);
	}

	public ZombieFightGame withNextIncome(double value) {
		this.setNextIncome(value);
		return this;
	}

	// speed:double
	public static final String PROPERTY_SPEED = "speed";

	double speed = 0.0;

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double value) {
		double oldValue = this.getSpeed();
		this.speed = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SPEED, oldValue, value);
	}

	public ZombieFightGame withSpeed(double value) {
		this.setSpeed(value);
		return this;
	}

	// users:0..n Link to UserAssets
	public static final String PROPERTY_USERS = "users";

	private Set<UserAssets> users = null;

	public Set<UserAssets> getUsers() {
		if (this.users == null) {
			return Collections.emptySet();
		}

		return this.users;
	}

	public ZombieFightGame withUsers(UserAssets... newValue) {
		if (newValue == null) {
			return this;
		}
		for (UserAssets item : newValue) {
			if (item != null) {
				if (this.users == null) {
					this.users = new HashSet<UserAssets>();
				}

				boolean changed = this.users.add(item);

				if (changed) {
					item.withGame(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_USERS, null, item);
				}
			}
		}
		return this;
	}

	public ZombieFightGame withoutUsers(UserAssets... newValue) {
		for (UserAssets item : newValue) {
			if ((this.users != null) && (item != null)) {
				if (this.users.remove(item)) {
					item.withGame(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_USERS, item, null);
				}
			}
		}
		return this;
	}

	public UserAssets createUsers() {
		UserAssets newValue = new UserAssets();
		this.withUsers(newValue);
		return newValue;
	}

	// fields:0..n Link to Field
	public static final String PROPERTY_FIELDS = "fields";

	private Set<Field> fields = null;

	public Set<Field> getFields() {
		if (this.fields == null) {
			return Collections.emptySet();
		}

		return this.fields;
	}

	public ZombieFightGame withFields(Field... newValue) {
		if (newValue == null) {
			return this;
		}
		for (Field item : newValue) {
			if (item != null) {
				if (this.fields == null) {
					this.fields = new HashSet<Field>();
				}

				boolean changed = this.fields.add(item);

				if (changed) {
					item.withGame(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_USERS, null, item);
				}
			}
		}
		return this;
	}

	public ZombieFightGame withoutFields(Field... newValue) {
		for (Field item : newValue) {
			if ((this.fields != null) && (item != null)) {
				if (this.fields.remove(item)) {
					item.withGame(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_USERS, item, null);
				}
			}
		}
		return this;
	}

	public Field createFields() {
		Field newValue = new Field();
		this.withFields(newValue);
		return newValue;
	}

	// guide:0..1 Link to Guide
	public static final String PROPERTY_GUIDE = "guide";

	private Guide guide = null;

	public Guide getGuide() {
		return this.guide;
	}

	public boolean setGuide(Guide value) {
		boolean changed = false;

		if (this.guide != value) {
			Guide oldValue = this.guide;

			this.guide = value;

			getPropertyChangeSupport().firePropertyChange(PROPERTY_GUIDE, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ZombieFightGame withGuide(Guide value) {
		this.setGuide(value);
		return this;
	}

	public Guide createGuide() {
		Guide newValue = new Guide();
		this.withGuide(newValue);
		return newValue;
	}

	// winner:0..1 Link to Winner
	public static final String PROPERTY_WINNER = "winner";

	private GameUser winner = null;

	public GameUser getWinner() {
		return this.winner;
	}

	public boolean setWinner(GameUser value) {
		boolean changed = false;

		if (this.winner != value) {
			GameUser oldValue = this.winner;

			this.winner = value;

			getPropertyChangeSupport().firePropertyChange(PROPERTY_WINNER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ZombieFightGame withWinner(GameUser value) {
		this.setWinner(value);
		return this;
	}

	public GameUser createWinner() {
		GameUser newValue = new GameUser();
		this.withWinner(newValue);
		return newValue;
	}
}