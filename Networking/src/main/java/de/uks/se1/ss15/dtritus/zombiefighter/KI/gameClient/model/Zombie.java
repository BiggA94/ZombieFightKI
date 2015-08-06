package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class Zombie implements PropertyChangeInterface {

	// Instance Management
	public static Set<Zombie> instances = null;

	public Zombie() {
		if (instances == null) {
			instances = new HashSet<Zombie>();
		}

		instances.add(this);
	}

	public static Zombie getInstanceById(String id) {
		if (instances == null)
			return null;

		for (Zombie currentInstance : instances) {
			if (currentInstance.getId() != null
					&& currentInstance.getId().equals(id)) {
				return currentInstance;
			}
		}
		return null;
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
		getPropertyChangeSupport().firePropertyChange(PROPERTY_ID, oldValue,
				value);
	}

	public Zombie withId(String value) {
		this.setId(value);
		return this;
	}

	// PropertyChange
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	// healt:int
	public static final String PROPERTY_HEALTH = "health";

	int health = 0;

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int value) {
		int oldValue = this.getHealth();
		this.health = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_HEALTH,
				oldValue, value);
	}

	public Zombie withHealth(int value) {
		this.setHealth(value);
		return this;
	}

	// slowed:boolean
	public static final String PROPERTY_SLOWED = "slowed";

	boolean slowed = false;

	public boolean getSlowed() {
		return this.slowed;
	}

	public void setSlowed(boolean value) {
		boolean oldValue = this.getSlowed();
		this.slowed = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SLOWED,
				oldValue, value);
	}

	public Zombie withSlowed(boolean value) {
		this.setSlowed(value);
		return this;
	}

	// slowTime:double
	public static final String PROPERTY_SLOWTIME = "slowTime";

	double slowTime = 0;

	public double getSlowTime() {
		return this.slowTime;
	}

	public void setSlowTime(double value) {
		double oldValue = this.getSlowTime();
		this.slowTime = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SLOWTIME,
				oldValue, value);
	}

	public Zombie withSlowTime(double value) {
		this.setSlowTime(value);
		return this;
	}

	// speed:double
	public static final String PROPERTY_SPEED = "speed";

	double speed = 0;

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double value) {
		double oldValue = this.getSpeed();
		this.speed = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SPEED, oldValue,
				value);
	}

	public Zombie withSpeed(double value) {
		this.setSpeed(value);
		return this;
	}

	// type:String
	public static final String PROPERTY_TYPE = "type";

	String type = "";

	public String getType() {
		return this.type;
	}

	public void setType(String value) {
		String oldValue = this.getType();
		this.type = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue,
				value);
	}

	public Zombie withType(String value) {
		this.setType(value);
		return this;
	}

	// userAssets:0..1 Link to UserAssets
	public static final String PROPERTY_USERASSETS = "userAssets";

	private UserAssets userAssets = null;

	public UserAssets getUserAssets() {
		return this.userAssets;
	}

	public boolean setUserAssets(UserAssets value) {
		boolean changed = false;

		if (this.userAssets != value) {
			UserAssets oldValue = this.userAssets;

			if (this.userAssets != null) {
				this.userAssets = null;
				oldValue.withoutZombie(this);
			}

			this.userAssets = value;

			if (value != null) {
				value.withZombie(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_USERASSETS,
					oldValue, value);
			changed = true;
		}

		return changed;

	}

	public Zombie withUserAssets(UserAssets value) {
		this.setUserAssets(value);
		return this;
	}

	public UserAssets createUserAssets() {
		UserAssets value = new UserAssets();
		this.withUserAssets(value);
		return value;
	}

	// currentWaypoint:0..1 Link to Waypoint
	public static final String PROPERTY_CURRENTWAYPOINT = "currentWaypoint";

	private Waypoint currentWaypoint = null;

	public Waypoint getCurrentWaypoint() {
		return this.currentWaypoint;
	}

	public boolean setCurrentWaypoint(Waypoint value) {
		boolean changed = false;

		if (this.currentWaypoint != value) {
			Waypoint oldValue = this.currentWaypoint;

			if (this.currentWaypoint != null) {
				this.currentWaypoint = null;
				oldValue.withoutZombie(this);
			}

			this.currentWaypoint = value;

			if (value != null) {
				value.withZombie(this);
			}

			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_CURRENTWAYPOINT, oldValue, value);
			changed = true;
		}

		return changed;

	}

	public Zombie withCurrentWaypoint(Waypoint value) {
		this.setCurrentWaypoint(value);
		return this;
	}

	public Waypoint createCurrentWaypoint() {
		Waypoint value = new Waypoint();
		this.withCurrentWaypoint(value);
		return value;
	}

	// cell:0..1 Link to Cell
	public static final String PROPERTY_CELL = "cell";

	private Cell cell = null;

	public Cell getCell() {
		return this.cell;
	}

	public boolean setCell(Cell value) {
		boolean changed = false;

		if (this.cell != value) {
			Cell oldValue = this.cell;

			if (this.cell != null) {
				this.cell = null;
				oldValue.withoutZombie(this);
			}

			this.cell = value;

			if (value != null) {
				value.withZombie(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_CELL,
					oldValue, value);
			changed = true;
		}

		return changed;

	}

	public Zombie withCell(Cell value) {
		this.setCell(value);
		return this;
	}

	public Cell createCell() {
		Cell value = new Cell();
		this.withCell(value);
		return value;
	}
}
