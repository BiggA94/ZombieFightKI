package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class ZombieDescription implements PropertyChangeInterface {

	// Instance Management
	public static Set<ZombieDescription> instances = null;

	public ZombieDescription() {
		if (instances == null) {
			instances = new HashSet<ZombieDescription>();
		}

		instances.add(this);
	}

	public static ZombieDescription getInstanceById(String id) {
		if (instances == null)
			return null;

		for (ZombieDescription currentInstance : instances) {
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

	public ZombieDescription withId(String value) {
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

	// description:String
	public static final String PROPERTY_DESCRIPTION = "description";

	String description = null;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String value) {
		String oldValue = this.getDescription();
		this.description = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_DESCRIPTION,
				oldValue, value);
	}

	public ZombieDescription withDescription(String value) {
		this.setDescription(value);
		return this;
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
		getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue,
				value);
	}

	public ZombieDescription withName(String value) {
		this.setName(value);
		return this;
	}

	// price:int
	public static final String PROPERTY_PRICE = "price";

	int price = 0;

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int value) {
		int oldValue = this.getPrice();
		this.price = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_PRICE, oldValue,
				value);
	}

	public ZombieDescription withPrice(int value) {
		this.setPrice(value);
		return this;
	}

	// bounty:int
	public static final String PROPERTY_BOUNTY = "bounty";

	int bounty = 0;

	public int getBounty() {
		return this.bounty;
	}

	public void setBounty(int value) {
		int oldValue = this.getBounty();
		this.bounty = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_BOUNTY,
				oldValue, value);
	}

	public ZombieDescription withBounty(int value) {
		this.setBounty(value);
		return this;
	}

	// slowImmune:boolean
	public static final String PROPERTY_SLOW_IMMUNE = "slowImmune";

	boolean slowImmune = false;

	public boolean getSlowImmune() {
		return this.slowImmune;
	}

	public void setSlowImmune(boolean value) {
		boolean oldValue = this.getSlowImmune();
		this.slowImmune = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SLOW_IMMUNE,
				oldValue, value);
	}

	public ZombieDescription withSlowImmune(boolean value) {
		this.setSlowImmune(value);
		return this;
	}

	// speedMax:double
	public static final String PROPERTY_SPEED_MAX = "speedMax";

	double speedMax = 0.0;

	public double getSpeedMax() {
		return this.speedMax;
	}

	public void setSpeedMax(double value) {
		double oldValue = this.getSpeedMax();
		this.speedMax = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SPEED_MAX,
				oldValue, value);
	}

	public ZombieDescription withSpeedMax(double value) {
		this.setSpeedMax(value);
		return this;
	}

	// type:String
	public static final String PROPERTY_TYPE = "type";

	String type = null;

	public String getType() {
		return this.type;
	}

	public void setType(String value) {
		String oldValue = this.getType();
		this.type = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue,
				value);
	}

	public ZombieDescription withType(String value) {
		this.setType(value);
		return this;
	}

	// healthMax:int
	public static final String PROPERTY_HEALTH_MAX = "healthMax";

	int healthMax = 0;

	public int getHealthMax() {
		return this.healthMax;
	}

	public void setHealthMax(int value) {
		int oldValue = this.getHealthMax();
		this.healthMax = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_HEALTH_MAX,
				oldValue, value);
	}

	public ZombieDescription withHealthMax(int value) {
		this.setHealthMax(value);
		return this;
	}

	// income:int
	public static final String PROPERTY_INCOME = "income";

	int income = 0;

	public int getIncome() {
		return this.income;
	}

	public void setIncome(int value) {
		int oldValue = this.getIncome();
		this.income = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_INCOME,
				oldValue, value);
	}

	public ZombieDescription withIncome(int value) {
		this.setIncome(value);
		return this;
	}
}
