package de.uks.se1.ss15.dtritus.zombiefighter.KI.gameClient.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.serialization.PropertyChangeInterface;

public class DefenseDescription implements PropertyChangeInterface {

	// Instance Management
	public static Set<DefenseDescription> instances = null;

	public DefenseDescription() {
		if (instances == null) {
			instances = new HashSet<DefenseDescription>();
		}

		instances.add(this);
	}

	public static DefenseDescription getInstanceById(String id) {
		if (instances == null)
			return null;
		
		for (DefenseDescription currentInstance : instances) {
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

	public DefenseDescription withId(String value) {
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

	// description:String
	public static final String PROPERTY_DESCRIPTION = "description";
	
	String description = null;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String value) {
		String oldValue = this.getDescription();
		this.description = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_DESCRIPTION,
				oldValue, value);
	}

	public DefenseDescription withDescription(String value) {
		this.setDescription(value);
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

	public DefenseDescription withName(String value) {
		this.setName(value);
		return this;
	}

	// price:int
	public static final String PROPERTY_PRICE = "price";
	
	int price = 0;

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int value) {
		int oldValue = this.getPrice();
		this.price = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_PRICE, oldValue,
				value);
	}

	public DefenseDescription withPrice(int value) {
		this.setPrice(value);
		return this;
	}

	// coolDownMax:int
	public static final String PROPERTY_COOL_DOWN_MAX = "coolDownMax";
	
	int coolDownMax = 0;

	public int getCoolDownMax() {
		return this.coolDownMax;
	}

	public void setCoolDownMax(int value) {
		int oldValue = this.getCoolDownMax();
		this.coolDownMax = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_COOL_DOWN_MAX,
				oldValue, value);
	}

	public DefenseDescription withCoolDownMax(int value) {
		this.setCoolDownMax(value);
		return this;
	}

	// damage:int
	public static final String PROPERTY_DAMAGE = "damage";
	
	int damage = 0;

	public int getDamage() {
		return this.damage;
	}

	public void setDamage(int value) {
		int oldValue = this.getDamage();
		this.damage = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_DAMAGE,
				oldValue, value);
	}

	public DefenseDescription withDamage(int value) {
		this.setDamage(value);
		return this;
	}

	// range:double
	public static final String PROPERTY_RANGE = "range";
	
	double range = 0.0;

	public double getRange() {
		return this.range;
	}

	public void setRange(double value) {
		double oldValue = this.getRange();
		this.range = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_RANGE, oldValue,
				value);
	}

	public DefenseDescription withRange(double value) {
		this.setRange(value);
		return this;
	}

	// sellTime:int
	public static final String PROPERTY_SELL_TIME = "sellTime";
	
	int sellTime = 0;

	public int getSellTime() {
		return this.sellTime;
	}

	public void setSellTime(int value) {
		int oldValue = this.getSellTime();
		this.sellTime = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SELL_TIME,
				oldValue, value);
	}

	public DefenseDescription withSellTime(int value) {
		this.setSellTime(value);
		return this;
	}

	// sellValue:int
	public static final String PROPERTY_SELL_VALUE = "sellValue";
	
	int sellValue = 0;

	public int getSellValue() {
		return this.sellValue;
	}

	public void setSellValue(int value) {
		int oldValue = this.getSellValue();
		this.sellValue = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SELL_VALUE,
				oldValue, value);
	}

	public DefenseDescription withSellValue(int value) {
		this.setSellValue(value);
		return this;
	}

	// slowRate:double
	public static final String PROPERTY_SLOW_RATE = "slowRate";
	
	double slowRate = 0.0;

	public double getSlowRate() {
		return this.slowRate;
	}

	public void setSlowRate(double value) {
		double oldValue = this.getSlowRate();
		this.slowRate = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SLOW_RATE,
				oldValue, value);
	}

	public DefenseDescription withSlowRate(double value) {
		this.setSlowRate(value);
		return this;
	}

	// slowTime:int
	public static final String PROPERTY_SLOW_TIME = "slowTime";
	
	int slowTime = 0;

	public int getSlowTime() {
		return this.slowTime;
	}

	public void setSlowTime(int value) {
		int oldValue = this.getSlowTime();
		this.slowTime = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SLOW_TIME,
				oldValue, value);
	}

	public DefenseDescription withSlowTime(int value) {
		this.setSlowTime(value);
		return this;
	}

	// upgradeable:boolean
	public static final String PROPERTY_UPGRADEABLE = "upgradeable";
	
	boolean upgradeable = false;

	public boolean getUpgradeable() {
		return this.upgradeable;
	}

	public void setUpgradeable(boolean value) {
		boolean oldValue = this.getUpgradeable();
		this.upgradeable = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_UPGRADEABLE,
				oldValue, value);
	}

	public DefenseDescription withUpgradeable(boolean value) {
		this.setUpgradeable(value);
		return this;
	}

	// upgradeTime:int
	public static final String PROPERTY_UPGRADE_TIME = "upgradeTime";
	
	int upgradeTime = 0;

	public int getUpgradeTime() {
		return this.upgradeTime;
	}

	public void setUpgradeTime(int value) {
		int oldValue = this.getUpgradeTime();
		this.upgradeTime = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_UPGRADE_TIME,
				oldValue, value);
	}

	public DefenseDescription withUpgradeTime(int value) {
		this.setUpgradeTime(value);
		return this;
	}

	// level:int
	public static final String PROPERTY_LEVEL = "level";
	
	int level = 0;

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int value) {
		int oldValue = this.getLevel();
		this.level = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_LEVEL, oldValue,
				value);
	}

	public DefenseDescription withLevel(int value) {
		this.setLevel(value);
		return this;
	}

	// type:String
	public static final String PROPERTY_TYPE = "type";
	
	String type = null;

	public String getType() {
		return this.type;
	}

	public void setType(String value) {
		String oldValue = this.getType();
		this.type = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue,
				value);
	}

	public DefenseDescription withType(String value) {
		this.setType(value);
		return this;
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

	public DefenseDescription withColor(String value) {
		this.setColor(value);
		return this;
	}

	// splashRadius:double
	public static final String PROPERTY_SPLASH_RADIUS = "splashRadius";
	
	double splashRadius = 0.0;

	public double getSplashRadius() {
		return this.splashRadius;
	}

	public void setSplashRadius(double value) {
		double oldValue = this.getSplashRadius();
		this.splashRadius = value;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_SPLASH_RADIUS,
				oldValue, value);
	}

	public DefenseDescription withSplashRadius(double value) {
		this.setSplashRadius(value);
		return this;
	}

	// splashDamageReduction:double
	public static final String PROPERTY_SPLASH_DAMAGE_REDUCTION = "splashDamageReduction";
	
	double splashDamageReduction = 0.0;

	public double getSplashDamageReduction() {
		return this.splashDamageReduction;
	}

	public void setSplashDamageReduction(double value) {
		double oldValue = this.getSplashDamageReduction();
		this.splashDamageReduction = value;
		getPropertyChangeSupport().firePropertyChange(
				PROPERTY_SPLASH_DAMAGE_REDUCTION, oldValue, value);
	}

	public DefenseDescription withSplashDamageReduction(double value) {
		this.setSplashDamageReduction(value);
		return this;
	}
}
