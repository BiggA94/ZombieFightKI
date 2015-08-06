package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class Guide implements PropertyChangeInterface {

	// Instance Management
	public static Set<Guide> instances = null;

	public Guide() {
		if (instances == null) {
			instances = new HashSet<Guide>();
		}

		instances.add(this);
	}

	public static Guide getInstanceById(String id) {
		if (instances == null)
			return null;
		
		for (Guide currentInstance : instances) {
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

	public Guide withId(String value) {
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

	// defenseDescriptions:0..n Link to DefenseTypeToDefenseMapping
	public static final String PROPERTY_DEFENSE_DESCRIPTION = "defenseDescriptions";

	private Set<DefenseTypeToDefenseMapping> defenseDescriptions = null;

	public Set<DefenseTypeToDefenseMapping> getDefenseDescriptions() {
		if (this.defenseDescriptions == null) {
			return Collections.emptySet();
		}

		return this.defenseDescriptions;
	}

	public Guide withDefenseDescriptions(DefenseTypeToDefenseMapping... newValue) {
		if (newValue == null) {
			return this;
		}
		for (DefenseTypeToDefenseMapping item : newValue) {
			if (item != null) {
				if (this.defenseDescriptions == null) {
					this.defenseDescriptions = new HashSet<DefenseTypeToDefenseMapping>();
				}

				boolean changed = this.defenseDescriptions.add(item);

				if (changed) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_DEFENSE_DESCRIPTION, null, item);
				}
			}
		}
		return this;
	}

	public Guide withoutDefenseDescriptions(
			DefenseTypeToDefenseMapping... newValue) {
		for (DefenseTypeToDefenseMapping item : newValue) {
			if ((this.defenseDescriptions != null) && (item != null)) {
				if (this.defenseDescriptions.remove(item)) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_DEFENSE_DESCRIPTION, item, null);
				}
			}
		}
		return this;
	}

	public DefenseTypeToDefenseMapping createDefenseDescription() {
		DefenseTypeToDefenseMapping newValue = new DefenseTypeToDefenseMapping();
		this.withDefenseDescriptions(newValue);
		return newValue;
	}

	// zombieDescriptions:0..n Link to ZombieTypeToZombieMapping
	public static final String PROPERTY_ZOMBIE_DESCRIPTIONS = "zombieDescriptions";

	private Set<ZombieTypeToZombieMapping> zombieDescriptions = null;

	public Set<ZombieTypeToZombieMapping> getZombieDescriptions() {
		if (this.zombieDescriptions == null) {
			return Collections.emptySet();
		}

		return this.zombieDescriptions;
	}

	public Guide withZombieDescriptions(ZombieTypeToZombieMapping... newValue) {
		if (newValue == null) {
			return this;
		}
		for (ZombieTypeToZombieMapping item : newValue) {
			if (item != null) {
				if (this.zombieDescriptions == null) {
					this.zombieDescriptions = new HashSet<ZombieTypeToZombieMapping>();
				}

				boolean changed = this.zombieDescriptions.add(item);

				if (changed) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_ZOMBIE_DESCRIPTIONS, null, item);
				}
			}
		}
		return this;
	}

	public Guide withoutZombieDescriptions(ZombieTypeToZombieMapping... newValue) {
		for (ZombieTypeToZombieMapping item : newValue) {
			if ((this.zombieDescriptions != null) && (item != null)) {
				if (this.zombieDescriptions.remove(item)) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_ZOMBIE_DESCRIPTIONS, item, null);
				}
			}
		}
		return this;
	}

	public ZombieTypeToZombieMapping createZombieDescription() {
		ZombieTypeToZombieMapping newValue = new ZombieTypeToZombieMapping();
		this.withZombieDescriptions(newValue);
		return newValue;
	}
}
