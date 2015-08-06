package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class Map implements PropertyChangeInterface {

	// Instance Management
	public static Set<Map> instances = null;

	public Map() {
		if (instances == null) {
			instances = new HashSet<Map>();
		}

		instances.add(this);
	}

	public static Map getInstanceById(String id) {
		if (instances == null)
			return null;
		
		for (Map currentInstance : instances) {
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

	public Map withId(String value) {
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

	// background:String
	public static final String PROPERTY_BACKGROUND = "background";

	String background = null;

	public String getBackground() {
		return this.background;
	}

	public void setBackground(String value) {
		String oldValue = this.getBackground();
		this.background = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_BACKGROUND,
				oldValue, value);
	}

	public Map withBackground(String value) {
		this.setBackground(value);
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

	public Map withName(String value) {
		this.setName(value);
		return this;
	}

	// createdBy:String
	public static final String PROPERTY_CREATEDBY = "createdBy";

	String createdBy = null;

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String value) {
		String oldValue = this.getCreatedBy();
		this.createdBy = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_CREATEDBY,
				oldValue, value);
	}

	public Map withCreatedBy(String value) {
		this.setCreatedBy(value);
		return this;
	}

	// version:String
	public static final String PROPERTY_VERSION = "version";
	
	String version = null;

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String value) {
		String oldValue = this.getVersion();
		this.version = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_VERSION,
				oldValue, value);
	}

	public Map withVersion(String newValue) {
		this.setVersion(newValue);
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
				oldValue.setMap(null);
			}

			this.field = value;

			if (value != null) {
				value.withMap(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_FIELD,
					oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Map withField(Field value) {
		this.setField(value);
		return this;
	}

	public Field createField() {
		Field value = new Field();
		this.withField(value);
		return value;
	}

	// cells:0..n Link to Cells
	public static final String PROPERTY_CELLS = "cells";

	private Set<Cell> cells = null;

	public Set<Cell> getCells() {
		if (this.cells == null) {
			return Collections.emptySet();
		}

		return this.cells;
	}

	public Map withCells(Cell... newValue) {
		if (newValue == null) {
			return this;
		}
		for (Cell item : newValue) {
			if (item != null) {
				if (this.cells == null) {
					this.cells = new HashSet<Cell>();
				}

				boolean changed = this.cells.add(item);

				if (changed) {
					item.withMap(this);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_CELLS, null, item);
				}
			}
		}
		return this;
	}

	public Map withoutCells(Cell... newValue) {
		for (Cell item : newValue) {
			if ((this.cells != null) && (item != null)) {
				if (this.cells.remove(item)) {
					item.withMap(null);
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_CELLS, item, null);
				}
			}
		}
		return this;
	}

	public Cell createCell() {
		Cell newValue = new Cell();
		this.withCells(newValue);
		return newValue;
	}

	// waypoints:0..n Link to Waypoint
	public static final String PROPERTY_WAYPOINTS = "waypoints";

	private Set<Waypoint> waypoints = null;

	public Set<Waypoint> getWaypoints() {
		if (this.waypoints == null) {
			return Collections.emptySet();
		}

		return this.waypoints;
	}

	public Map withWaypoints(Waypoint... newValue) {
		if (newValue == null) {
			return this;
		}
		for (Waypoint item : newValue) {
			if (item != null) {
				if (this.waypoints == null) {
					this.waypoints = new HashSet<Waypoint>();
				}

				boolean changed = this.waypoints.add(item);

				if (changed) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_WAYPOINTS, null, item);
				}
			}
		}
		return this;
	}

	public Map withoutWaypoints(Waypoint... newValue) {
		for (Waypoint item : newValue) {
			if ((this.waypoints != null) && (item != null)) {
				if (this.waypoints.remove(item)) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_WAYPOINTS, item, null);
				}
			}
		}
		return this;
	}

	public Waypoint createWaypoint() {
		Waypoint newValue = new Waypoint();
		this.withWaypoints(newValue);
		return newValue;
	}

	// enterWaypoint:0..1 Link to Waypoint
	public static final String PROPERTY_ENTER_WAYPOINT = "enterWaypoint";

	private Waypoint enterWaypoint = null;

	public Waypoint getEnterWaypoint() {
		return this.enterWaypoint;
	}

	public boolean setEnterWaypoint(Waypoint value) {
		boolean changed = false;

		if (this.enterWaypoint != value) {
			Waypoint oldValue = this.enterWaypoint;

			this.enterWaypoint = value;

			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_ENTER_WAYPOINT, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Map withEnterWaypoint(Waypoint value) {
		this.setEnterWaypoint(value);
		return this;
	}

	public Waypoint createEnterWaypoint() {
		Waypoint newValue = new Waypoint();
		this.withEnterWaypoint(newValue);
		return newValue;
	}

	// exitWaypoint:0..1 Link to Waypoint
	public static final String PROPERTY_EXIT_WAYPOINT = "exitWaypoint";

	private Waypoint exitWaypoint = null;

	public Waypoint getExitWaypoint() {
		return this.exitWaypoint;
	}

	public boolean setExitWaypoint(Waypoint value) {
		boolean changed = false;

		if (this.exitWaypoint != value) {
			Waypoint oldValue = this.exitWaypoint;

			this.exitWaypoint = value;

			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_ENTER_WAYPOINT, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Map withExitWaypoint(Waypoint value) {
		this.setExitWaypoint(value);
		return this;
	}

	public Waypoint createExitWaypoint() {
		Waypoint newValue = new Waypoint();
		this.withExitWaypoint(newValue);
		return newValue;
	}
}