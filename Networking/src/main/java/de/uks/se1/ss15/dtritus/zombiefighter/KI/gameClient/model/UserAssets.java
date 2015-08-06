package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class UserAssets implements PropertyChangeInterface {

	// Instance Management
	public static Set<UserAssets> instances = null;

	public UserAssets() {
		if (instances == null) {
			instances = new HashSet<UserAssets>();
		}

		instances.add(this);
	}

	public static UserAssets getInstanceById(String id) {
		if (instances == null)
			return null;

		for (UserAssets currentInstance : instances) {
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

	public UserAssets withId(String value) {
		this.setId(value);
		return this;
	}

	// Property Change
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	// color:String
	public static final String PROPERTY_COLOR = "color";

	String color = null;

	public String getColor() {
		return this.color;
	}

	public void setColor(String value) {
		String oldValue = this.getColor();
		this.color = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_COLOR, oldValue,
				value);
	}

	public UserAssets withColor(String value) {
		this.setColor(value);
		return this;
	}

	// lifes:int
	public static final String PROPERTY_LIFES = "lifes";

	int lifes = 0;

	public int getLifes() {
		return this.lifes;
	}

	public void setLifes(int value) {
		int oldValue = this.getLifes();
		this.lifes = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_LIFES, oldValue,
				value);
	}

	public UserAssets withLifes(int value) {
		this.setLifes(value);
		return this;
	}

	// visitor:boolean
	public static final String PROPERTY_VISITOR = "visitor";

	boolean visitor = false;

	public boolean getVisitor() {
		return this.visitor;
	}

	public void setVisitor(boolean value) {
		boolean oldValue = this.getVisitor();
		this.visitor = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_VISITOR,
				oldValue, value);
	}

	public UserAssets withVisitor(boolean value) {
		this.setVisitor(value);
		return this;
	}

	// balance:int
	public static final String PROPERTY_BALANCE = "balance";

	int balance = 0;

	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int value) {
		int oldValue = this.getBalance();
		this.balance = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_BALANCE,
				oldValue, value);
	}

	public UserAssets withBalance(int value) {
		this.setBalance(value);
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

	public UserAssets withIncome(int value) {
		this.setIncome(value);
		return this;
	}

	// field:0..1 Link to Field
	public static final String PROPERTY_FIELD = "field";

	private Field field = null;

	public Field getField() {
		return this.field;
	}

	public boolean setField(Field value) {
		boolean changed = false;

		if (this.field != value) {
			Field oldValue = this.field;

			if (this.field != null) {
				this.field = null;
				oldValue.setUserAssets(null);
			}

			this.field = value;

			if (value != null) {
				value.withUserAssets(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_FIELD,
					oldValue, value);
			changed = true;

		}
		return changed;
	}

	public UserAssets withField(Field value) {
		this.setField(value);
		return this;
	}

	public Field createField() {
		Field value = new Field();
		this.withField(value);
		return value;
	}

	// game:0..1 Link to ZombieFightGame
	public static final String PROPERTY_GAME = "game";

	private ZombieFightGame game = null;

	public ZombieFightGame getGame() {
		return this.game;
	}

	public boolean setGame(ZombieFightGame value) {
		boolean changed = false;

		if (this.game != value) {
			ZombieFightGame oldValue = this.game;

			if (this.game != null) {
				this.game = null;
				oldValue.withoutUsers(this);
			}

			this.game = value;

			if (value != null) {
				value.withUsers(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME,
					oldValue, value);
			changed = true;
		}

		return changed;

	}

	public UserAssets withGame(ZombieFightGame value) {
		this.setGame(value);
		return this;
	}

	public ZombieFightGame createGame() {
		ZombieFightGame value = new ZombieFightGame();
		this.withGame(value);
		return value;
	}

	// user:0..1 Link to User
	public static final String PROPERTY_USER = "user";

	private GameUser user = null;

	public GameUser getUser() {
		return this.user;
	}

	public boolean setUser(GameUser value) {
		boolean changed = false;

		if (this.user != value) {
			GameUser oldValue = this.user;

			this.user = value;

			getPropertyChangeSupport().firePropertyChange(PROPERTY_USER,
					oldValue, value);
			changed = true;
		}

		return changed;

	}

	public UserAssets withUser(GameUser value) {
		this.setUser(value);
		return this;
	}

	public GameUser createUser() {
		GameUser value = new GameUser();
		this.withUser(value);
		return value;
	}

	// zombie:0..n Link to Zombie
	public static final String PROPERTY_ZOMBIE = "zombie";

	private Set<Zombie> zombie = null;

	public Set<Zombie> getZombie() {
		if (this.zombie == null) {
			return Collections.emptySet();
		}

		return this.zombie;
	}

	public UserAssets withZombie(Zombie... newValue) {
		if (newValue == null) {
			return this;
		}
		for (Zombie item : newValue) {
			if (item != null) {
				if (this.zombie == null) {
					this.zombie = new HashSet<Zombie>();
				}

				boolean changed = this.zombie.add(item);

				if (changed) {
					item.withUserAssets(this);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_ZOMBIE, null, item);
				}
			}
		}
		return this;
	}

	public UserAssets withoutZombie(Zombie... newValue) {
		for (Zombie item : newValue) {
			if ((this.zombie != null) && (item != null)) {
				if (this.zombie.remove(item)) {
					item.withUserAssets(null);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_ZOMBIE, item, null);
				}
			}

		}
		return this;
	}

	// Due to PropChangeListener unuusable
	// public Zombie createZombie() {
	// Zombie newValue = new Zombie();
	// this.withZombie(newValue);
	// return newValue;
	// }

	// UserAssets -> Defense : 0..n
	public static final String PROPERTY_DEFENSE = "defense";

	private LinkedHashSet<Defense> defenses = new LinkedHashSet<Defense>();

	public LinkedHashSet<Defense> getDefenses() {
		return this.defenses;
	}

	public boolean addDefense(Defense newValue) {
		boolean changed = false;

		if (newValue != null) {
			if (!this.defenses.contains(newValue)) {
				changed = this.defenses.add(newValue);

				if (changed) {
					newValue.setUserAsset(this);

					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_DEFENSE, null, newValue);
				}
			}
		}


		return changed;
	}

	public boolean removeDefense(Defense oldValue) {
		boolean changed = false;

		if (this.defenses.contains(oldValue)) {
			changed = this.defenses.remove(oldValue);

			if (changed) {
				oldValue.setUserAsset(null);
				getPropertyChangeSupport().firePropertyChange(PROPERTY_DEFENSE,
						oldValue, null);

			}
		}

		return changed;
	}

	public UserAssets withDefenses(Defense... newValues) {
		if (newValues == null) {
			return this;
		}
		for (Defense tower : newValues) {
			this.addDefense(tower);
		}
		return this;
	}

	public UserAssets withoutDefenses(Defense... oldValues) {
		for (Defense tower : oldValues) {
			this.removeDefense(tower);
		}
		return this;
	}

	public Defense createDefense() {
		Defense newValue = new Defense();
		this.withDefenses(newValue);
		return newValue;
	}

}
