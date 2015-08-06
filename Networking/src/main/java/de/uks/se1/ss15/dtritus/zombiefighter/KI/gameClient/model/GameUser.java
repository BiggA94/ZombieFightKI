package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.GameTeam;
import de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model.GameUser;

public class GameUser implements PropertyChangeInterface {

	// Instance Management
	public static Set<GameUser> instances = null;

	public GameUser() {
		if (instances == null) {
			instances = new HashSet<GameUser>();
		}

		instances.add(this);
	}

	public static GameUser getInstanceById(String id) {
		if (instances == null)
			return null;
		
		for (GameUser currentInstance : instances) {
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

	public GameUser withId(String value) {
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
	
	// nickname:String
	public static final String PROPERTY_NICKNAME = "nickname";

	private String nickname = null;

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String value) {
		if (!StrUtil.stringEquals(this.nickname, value)) {
			String oldValue = this.nickname;
			this.nickname = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_NICKNAME,
					oldValue, value);
		}
	}

	public GameUser withNickname(String value) {
		setNickname(value);
		return this;
	}

	// team:0..1 Link to Team
	public static final String PROPERTY_TEAM = "team";

	private GameTeam team = null;

	public GameTeam getTeam() {
		return this.team;
	}

	public boolean setTeam(GameTeam value) {
		boolean changed = false;

		if (this.team != value) {
			GameTeam oldValue = this.team;

			if (this.team != null) {
				this.team = null;
				oldValue.withoutUsers(this);
			}

			this.team = value;

			if (value != null) {
				value.withUsers(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_TEAM,
					oldValue, value);
			changed = true;
		}

		return changed;
	}

	public GameUser withTeam(GameTeam value) {
		setTeam(value);
		return this;
	}

	public GameTeam createTeam() {
		GameTeam value = new GameTeam();
		withTeam(value);
		return value;
	}
}
