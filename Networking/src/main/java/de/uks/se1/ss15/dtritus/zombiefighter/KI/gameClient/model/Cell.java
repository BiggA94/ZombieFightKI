package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class Cell implements PropertyChangeInterface {

	// Instance Management
	public static Set<Cell> instances = null;

	public Cell() {
		if (instances == null) {
			instances = new HashSet<Cell>();
		}

		instances.add(this);
	}

	public static Cell getInstanceById(String id) {
		if (instances == null)
			return null;

		for (Cell currentInstance : instances) {
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

	public Cell withId(String value) {
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

	// map:0..1 Link to Map
	public static final String PROPERTY_MAP = "map";

	private Map map = null;

	public Map getMap() {
		return this.map;
	}

	public boolean setMap(Map newValue) {
		boolean changed = false;

		if (this.map != newValue) {
			Map oldValue = this.map;

			if (this.map != null) {
				this.map = null;
				oldValue.withoutCells(this);
			}

			this.map = newValue;

			if (newValue != null) {
				newValue.withCells(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP,
					oldValue, newValue);
			changed = true;
		}

		return changed;
	}

	public Cell withMap(Map newValue) {
		this.setMap(newValue);
		return this;
	}

	public Map createMap() {
		Map newValue = new Map();
		this.withMap(newValue);
		return newValue;
	}

	// type:String
	public static final String PROPERTY_TYPE = "type";

	String type = null;

	public String getType() {
		return this.type;
	}

	public void setType(String newValue) {
		String oldValue = this.getType();
		this.type = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue,
				newValue);
	}

	public Cell withType(String newValue) {
		this.setType(newValue);
		return this;
	}

	// x:Int
	public static final String PROPERTY_X = "x";

	int x = 0;

	public int getX() {
		return this.x;
	}

	public void setX(int newValue) {
		int oldValue = this.getX();
		this.x = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_X, oldValue,
				newValue);
	}

	public Cell withX(int newValue) {
		this.setX(newValue);
		return this;
	}

	// y:Int
	public static final String PROPERTY_Y = "y";

	int y = 0;

	public int getY() {
		return this.y;
	}

	public void setY(int newValue) {
		int oldValue = this.getY();
		this.y = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_Y, oldValue,
				newValue);
	}

	public Cell withY(int newValue) {
		this.setY(newValue);
		return this;
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

	public Cell withZombie(Zombie... newValue) {
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
					item.withCell(this);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_ZOMBIE, null, item);
				}
			}
		}
		return this;
	}

	public Cell withoutZombie(Zombie... newValue) {
		for (Zombie item : newValue) {
			if ((this.zombie != null) && (item != null)) {
				if (this.zombie.remove(item)) {
					item.withCell(null);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_ZOMBIE, item, null);
				}
			}
		}
		return this;
	}
	
	//Due to PropChangeListener unuusable
//	public Zombie createZombie() {
//		Zombie newValue = new Zombie();
//		this.withZombie(newValue);
//		return newValue;
//	}
	
	// Cell -> Defense : 0..1
	public static final String PROPERTY_DEFENSE = "defense";
	
	private Defense defense = null;
	
	public Defense getDefense() {
		return this.defense;
	}
	
	public boolean setDefense(Defense newValue) {
		boolean changed = false;
		
		if ( this.defense != newValue ) {
			Defense oldValue = this.defense;
			
			if ( this.defense != null ) {
				this.defense = null;
				oldValue.setCell(null);
			}
			
			this.defense = newValue;
			
			if ( newValue != null ) {
				newValue.setCell(this);
			}
			
			getPropertyChangeSupport().firePropertyChange(PROPERTY_DEFENSE, oldValue, newValue);
			changed = true;
		}
		
		return changed;
	}
	
	public Cell withDefense(Defense newValue) {
		this.setDefense(newValue);
		return this;
	}
	
	public Defense createDefense() {
		Defense newValue = new Defense();
		this.withDefense(newValue);
		return newValue;
	}
	
}
