package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class Waypoint implements PropertyChangeInterface {

	// Instance Management
	public static Set<Waypoint> instances = null;

	public Waypoint() {
		if (instances == null) {
			instances = new HashSet<Waypoint>();
		}

		instances.add(this);
	}

	public static Waypoint getInstanceById(String id) {
		if (instances == null)
			return null;

		for (Waypoint currentInstance : instances) {
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

	public Waypoint withId(String value) {
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

	// previous:0..1 Link to Waypoint
	public static final String PROPERTY_PREVIOUS = "previous";

	private Waypoint previous = null;

	public Waypoint getPrevious() {
		return this.previous;
	}

	public boolean setPrevious(Waypoint value) {
		boolean changed = false;

		if (this.previous != value) {
			Waypoint oldValue = this.previous;

			if (this.previous != null) {
				this.previous = null;
				oldValue.setNext(null);
			}

			this.previous = value;

			if (value != null) {
				value.setNext(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_PREVIOUS,
					oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Waypoint withPrevious(Waypoint value) {
		this.setPrevious(value);
		return this;
	}

	public Waypoint createPrevious() {
		Waypoint value = new Waypoint();
		this.withPrevious(value);
		return value;
	}

	// next:0..1 Link to Waypoint
	public static final String PROPERTY_NEXT = "next";

	private Waypoint next = null;

	public Waypoint getNext() {
		return this.next;
	}

	public boolean setNext(Waypoint value) {
		boolean changed = false;

		if (this.next != value) {
			Waypoint oldValue = this.next;

			if (this.next != null) {
				this.next = null;
				oldValue.setPrevious(null);
			}

			this.next = value;

			if (value != null) {
				value.setPrevious(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT,
					oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Waypoint withNext(Waypoint value) {
		this.setNext(value);
		return this;
	}

	public Waypoint createNext() {
		Waypoint value = new Waypoint();
		this.withNext(value);
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

			this.cell = value;

			getPropertyChangeSupport().firePropertyChange(PROPERTY_CELL,
					oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Waypoint withCell(Cell value) {
		setCell(value);
		return this;
	}

	public Cell createCell() {
		Cell value = new Cell();
		withCell(value);
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

	public Waypoint withZombie(Zombie... newValue) {
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
					item.withCurrentWaypoint(this);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_ZOMBIE, null, item);
				}
			}
		}
		return this;
	}

	public Waypoint withoutZombie(Zombie... newValue) {
		for (Zombie item : newValue) {
			if ((this.zombie != null) && (item != null)) {
				if (this.zombie.remove(item)) {
					item.withCurrentWaypoint(null);
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
}
