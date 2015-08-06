package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class Field implements PropertyChangeInterface {
	
	// Instance Management
	public static Set<Field> instances = null;

	public Field() {
		if (instances == null) {
			instances = new HashSet<Field>();
		}

		instances.add(this);
	}

	public static Field getInstanceById(String id) {
		if (instances == null)
			return null;
		
		for (Field currentInstance : instances) {
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

	public Field withId(String value) {
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

	// position:String
	public static final String PROPERTY_POSITION = "position";

	String position = null;

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String value) {
		String oldValue = this.getPosition();
		this.position = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_POSITION,
				oldValue, value);
	}

	public Field withPosition(String value) {
		this.setPosition(value);
		return this;
	}

	// map:0..1 Link to Map
	public static final String PROPERTY_MAP = "map";

	private Map map = null;

	public Map getMap() {
		return this.map;
	}

	public boolean setMap(Map value) {
		boolean changed = false;

		if (this.map != value) {
			Map oldValue = this.map;

			if (this.map != null) {
				this.map = null;
				oldValue.setField(null);
			}

			this.map = value;

			if (value != null) {
				value.withField(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP,
					oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Field withMap(Map value) {
		this.setMap(value);
		return this;
	}

	public Map createMap() {
		Map value = new Map();
		this.withMap(value);
		return value;
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
				oldValue.setField(null);
			}

			this.userAssets = value;

			if (value != null) {
				value.withField(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_USERASSETS,
					oldValue, value);
			changed = true;

		}
		return changed;

	}

	public Field withUserAssets(UserAssets value) {
		this.setUserAssets(value);
		return this;
	}

	public UserAssets createUserAssets() {
		UserAssets value = new UserAssets();
		this.withUserAssets(value);
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
				oldValue.withoutFields(this);
			}
			
			this.game = value;
			
			if (value != null) {
				value.withFields(this);
			}
			
			getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME,
					oldValue, value);
			changed = true;
			
		}
		return changed;
		
	}
	
	public Field withGame(ZombieFightGame value) {
		this.setGame(value);
		return this;
	}
	
	public ZombieFightGame createGame() {
		ZombieFightGame value = new ZombieFightGame();
		this.withGame(value);
		return value;
	}
}