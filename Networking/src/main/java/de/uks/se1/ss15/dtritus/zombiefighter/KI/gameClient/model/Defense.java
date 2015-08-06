package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.serialization.PropertyChangeInterface;

public class Defense implements PropertyChangeInterface {
	
	// Instance Management
	public static LinkedHashSet<Defense> instances = null;
	
	public Defense() {
		if (instances == null) {
			instances = new LinkedHashSet<Defense>();
		}
		
		instances.add(this);
	}

	public static Defense getInstanceById(String id) {
		if (instances == null)
			return null;
		
		for (Defense currentInstance : instances) {
			if (currentInstance.getId() != null && currentInstance.getId().equals(id)) {
				return currentInstance;
			}
		}
		return null;
	}
	
	// id:String
	public static final String PROPERTY_ID = "id";
	
	private String id = null;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		String oldValue = this.getId();
		this.id = id;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_ID, oldValue, id);
	}
	
	public Defense withId(String id) {
		this.setId(id);
		return this;
	}
	
	// PropertyChangeSupport
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}
	
	// Defense -> UserAssets : 0..1
	public static final String PROPERTY_USERASSET = "userAsset";
	
	private UserAssets userAsset = null;
	
	public UserAssets getUserAsset() {
		return this.userAsset;
	}
	
	public boolean setUserAsset(UserAssets newValue) {
		boolean changed = false;
		
		if ( this.userAsset != newValue ) {
			UserAssets oldValue = this.userAsset;
			
			if ( this.userAsset != null ) {
				this.userAsset = null;
				oldValue.removeDefense(this);
			}
			
			this.userAsset = newValue;
			
			if ( newValue != null ) {
				newValue.addDefense(this);
			}
			
			getPropertyChangeSupport().firePropertyChange(PROPERTY_USERASSET, oldValue, newValue);
			changed = true;
		}
		
		return changed;
	}
	
	public Defense withUserAsset(UserAssets newValue) {
		this.setUserAsset(newValue);
		return this;
	}
	
	public UserAssets createUserAsset() {
		UserAssets newValue = new UserAssets();
		this.withUserAsset(newValue);
		return newValue;
	}
	
	// Defense -> Cell : 0..1
	public static final String PROPERTY_CELL = "cell";
	
	private Cell cell = null;
	
	public Cell getCell() {
		return this.cell;
	}
	
	public boolean setCell(Cell newValue) {
		boolean changed = false;
		
		if ( this.cell != newValue ) {
			Cell oldValue = this.cell;
			
			if ( this.cell != null ) {
				this.cell = null;
				oldValue.setDefense(null);
			}
			
			this.cell = newValue;
			
			if ( newValue != null ) {
				newValue.setDefense(this);
			}
			
			getPropertyChangeSupport().firePropertyChange(PROPERTY_CELL, oldValue, newValue);
			changed = true;
		}
		
		return changed;
	}
	
	public Defense withCell(Cell newValue) {
		this.setCell(newValue);
		return this;
	}
	
	public Cell createCell() {
		Cell newValue = new Cell();
		this.withCell(newValue);
		return newValue;
	}
	
	// building:boolean
	public static final String PROPERTY_BUILDING = "building";
	
	private boolean building = false;
	
	public boolean getBuilding() {
		return this.building;
	}
	
	public void setBuilding(boolean newValue) {
		boolean oldValue = this.building;
		this.building = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_BUILDING, oldValue, newValue);
	}
	
	public Defense withBuilding(boolean newValue) {
		this.setBuilding(newValue);
		return this;
	}
	
	// buildProgress:double
	public static final String PROPERTY_BUILDPROGRESS = "buildProgress";
	
	private double buildProgress = 0.0;
	
	public double getBuildProgress() {
		return this.buildProgress;
	}
	
	public void setBuildProgress(double newValue) {
		double oldValue = this.buildProgress;
		this.buildProgress = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_BUILDPROGRESS, oldValue, newValue);
	}
	
	public Defense withBuildProgress(double newValue) {
		this.setBuildProgress(newValue);
		return this;
	}
	
	// fireOn -> Zombie : 0..1
	public static final String PROPERTY_FIREON = "fireOn";
	
	private Zombie fireOn = null;
	
	public Zombie getFireOn() {
		return this.fireOn;
	}
	
	public boolean setFireOn(Zombie newValue) {
		boolean changed = false;

		if (this.fireOn != newValue) {
			Zombie oldValue = this.fireOn;

			this.fireOn = newValue;

			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_FIREON, oldValue, newValue);
			changed = true;
		}

		return changed;
	}
	
	public Defense withFireOn(Zombie newValue) {
		this.setFireOn(newValue);
		return this;
	}
	
//	public Zombie createFireOn() {
//		Zombie newValue = new Zombie();
//		this.withFireOn(newValue);
//		return newValue;
//	}
	
	// level:int
	public static final String PROPERTY_LEVEL = "level";
	
	private int level = 0;
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int newValue) {
		int oldValue = this.level;
		this.level = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_LEVEL, oldValue, newValue);
	}
	
	public Defense withLevel(int newValue) {
		this.setLevel(newValue);
		return this;
	}
	
	// type:string
	public static final String PROPERTY_TYPE = "type";
	
	private String type = null;
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String newValue) {
		String oldValue = this.type;
		this.type = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue, newValue);
	}
	
	public Defense withType(String newValue) {
		this.setType(newValue);
		return this;
	}
	
	// upgrading:boolean
	public static final String PROPERTY_UPGRADING = "upgrading";
	
	private boolean upgrading = false;
	
	public boolean getUpgrading() {
		return this.upgrading;
	}
	
	public void setUpgrading(boolean newValue) {
		boolean oldValue = this.upgrading;
		this.upgrading = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_UPGRADING, oldValue, newValue);
	}
	
	public Defense withUpgrading(boolean newValue) {
		this.setUpgrading(newValue);
		return this;
	}
	
	// color:string
	public static final String PROPERTY_COLOR = "color";
	
	private String color = null;
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String newValue) {
		String oldValue = this.color;
		this.color = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_COLOR, oldValue, newValue);
	}
	
	public Defense withColor(String newValue) {
		this.setColor(newValue);
		return this;
	}
	
	// selling:boolean
	public static final String PROPERTY_SELLING = "selling";
	
	private boolean selling = false;
	
	public boolean getSelling() {
		return this.selling;
	}
	
	public void setSelling(boolean newValue) {
		boolean oldValue = this.selling;
		this.selling = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SELLING, oldValue, newValue);
	}
	
	public Defense withSelling(boolean newValue) {
		this.setSelling(newValue);
		return this;
	}
	
	// strategy:string
	public static final String PROPERTY_STRATEGY = "strategy";
	
	private String strategy = null;
	
	public String getStrategy() {
		return this.strategy;
	}
	
	public void setStrategy(String newValue) {
		String oldValue = this.strategy;
		this.strategy = newValue;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_STRATEGY, oldValue, newValue);
	}
	
	public Defense withStrategy(String newValue) {
		this.setStrategy(newValue);
		return this;
	}
}
