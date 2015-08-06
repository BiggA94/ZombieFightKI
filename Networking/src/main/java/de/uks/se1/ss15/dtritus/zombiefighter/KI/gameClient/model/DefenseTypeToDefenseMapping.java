package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class DefenseTypeToDefenseMapping implements PropertyChangeInterface {

	// Instance Management
	public static Set<DefenseTypeToDefenseMapping> instances = null;

	public DefenseTypeToDefenseMapping() {
		if (instances == null) {
			instances = new HashSet<DefenseTypeToDefenseMapping>();
		}

		instances.add(this);
	}

	public static DefenseTypeToDefenseMapping getInstanceById(String id) {
		if (instances == null)
			return null;
		
		for (DefenseTypeToDefenseMapping currentInstance : instances) {
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

	public DefenseTypeToDefenseMapping withId(String value) {
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

	// key:String
	public static final String PROPERTY_KEY = "key";

	String key = null;

	public String getKey() {
		return this.key;
	}

	public void setKey(String value) {
		String oldValue = this.getKey();
		this.key = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_KEY, oldValue,
				value);
	}

	public DefenseTypeToDefenseMapping withKey(String value) {
		this.setKey(value);
		return this;
	}

	// value:0..n Link to DefenseDescription
	public static final String PROPERTY_VALUE = "value";

	private Set<DefenseDescription> value = null;

	public Set<DefenseDescription> getValue() {
		if (this.value == null) {
			return Collections.emptySet();
		}

		return this.value;
	}

	public DefenseTypeToDefenseMapping withValue(DefenseDescription... newValue) {
		if (newValue == null) {
			return this;
		}
		for (DefenseDescription item : newValue) {
			if (item != null) {
				if (this.value == null) {
					this.value = new HashSet<DefenseDescription>();
				}

				boolean changed = this.value.add(item);

				if (changed) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_VALUE, null, item);
				}
			}
		}
		return this;
	}

	public DefenseTypeToDefenseMapping withoutValue(
			DefenseDescription... newValue) {
		for (DefenseDescription item : newValue) {
			if ((this.value != null) && (item != null)) {
				if (this.value.remove(item)) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_VALUE, item, null);
				}
			}
		}
		return this;
	}

	public DefenseDescription createValue() {
		DefenseDescription newValue = new DefenseDescription();
		this.withValue(newValue);
		return newValue;
	}
}
