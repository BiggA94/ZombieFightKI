package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.GameTeam;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.GameUser;

public class GameTeam implements PropertyChangeInterface {

	// Instance Management
	public static Set<GameTeam> instances = null;

	public GameTeam() {
		if (instances == null) {
			instances = new HashSet<GameTeam>();
		}

		instances.add(this);
	}

	public static GameTeam getInstanceById(String id) {
		if (instances == null)
			return null;
		
		for (GameTeam currentInstance : instances) {
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

	public GameTeam withId(String value) {
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

	private String name = null;

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		if (!StrUtil.stringEquals(this.name, value)) {
			String oldValue = this.name;
			this.name = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME,
					oldValue, value);
		}
	}

	public GameTeam withName(String value) {
		setName(value);
		return this;
	}

	// users:0..n Link to User
	public static final String PROPERTY_USERS = "users";

	private Set<GameUser> users = null;

	public Set<GameUser> getUsers() {
		if (this.users == null) {
			return Collections.emptySet();
		}

		return this.users;
	}

	public GameTeam withUsers(GameUser... newValue) {
		if (newValue == null) {
			return this;
		}
		for (GameUser item : newValue) {
			if (item != null) {
				if (this.users == null) {
					this.users = new HashSet<GameUser>();
				}

				boolean changed = this.users.add(item);

				if (changed) {
					item.withTeam(this);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_USERS, null, item);
				}
			}
		}
		return this;
	}

	public GameTeam withoutUsers(GameUser... newValue) {
		for (GameUser item : newValue) {
			if ((this.users != null) && (item != null)) {
				if (this.users.remove(item)) {
					item.withTeam(null);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_USERS, item, null);
				}
			}
		}
		return this;
	}

	public GameUser createUser() {
		GameUser newValue = new GameUser();
		this.withUsers(newValue);
		return newValue;
	}
}
