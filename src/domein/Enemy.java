package domein;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public abstract class Enemy {

	protected static SecureRandom sr = new SecureRandom();
	private double health;
	private double defence;
	private String type;
	private double tickDamage = 0;
	private int freeze = 0;
	private boolean frozen = false;
	protected boolean evolved = false;
	public final static List<String> ENEMIES = Arrays.asList("Dragon","Troll","Goblin");

	public Enemy(double health, double defence, String type) {
		setHealth(health);
		setDefence(defence);
		setType(type);
	}
	
	public boolean isStrongAgainst(String damageType) {
		if (type.equals("Fire") && damageType.equals("Wind")) {
			return true;
		}
		if (type.equals("Wind") && damageType.equals("Earth")) {
			return true;
		}
		if (type.equals("Earth") && damageType.equals("Lightning")) {
			return true;
		}
		if (type.equals("Lightning") && damageType.equals("Water")) {
			return true;
		}
		if (type.equals("Water") && damageType.equals("Fire")) {
			return true;
		}
		return false;
	}

	public boolean isWeakness(String damageType) {

		if (type.equals("Fire") && damageType.equals("Water")) {
			return true;
		}
		if (type.equals("Wind") && damageType.equals("Fire")) {
			return true;
		}
		if (type.equals("Earth") && damageType.equals("Wind")) {
			return true;
		}
		if (type.equals("Lightning") && damageType.equals("Earth")) {
			return true;
		}
		if (type.equals("Water") && damageType.equals("Lightning")) {
			return true;
		}
		return false;
	}

	public String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	public double getHealth() {
		return health;
	}

	protected void registerDamage(double totalDamage) {
		health -= totalDamage;
	}

	public abstract double attackBack();
	
	public abstract void evolve();

	protected void lowerDefence(double shred) {
		if (defence - shred > 0) {
			defence -= shred;
			return;
		}
		defence = 0;
	}

	protected void setHealth(double health) {
		if (health < 100) {
			throw new IllegalArgumentException("A monster always has at least 100 health!");
		}
		this.health = health;
	}

	public double getDefence() {
		return defence;
	}

	public double getTickDamage() {
		return tickDamage;
	}

	protected void setDefence(double defence) {
		this.defence = defence;
	}

	public double takeDamage(String damageType, double damage) {
		if (damageType.equals(type)) {
			registerDamage(damage * 0.5);
			return damage * 0.5;
		}
		if (damageType.equals("Water") && !type.equals("Water")) {
			freeze++;
			if (freeze == 3) {
				freeze = 0;
				frozen = true;
			}
		}

		if (damageType.equals("Earth") && !type.equals("Earth")) {
			lowerDefence(0.05);
		}
		double totalDamage = (damage - damage * getDefence()) + tickDamage;
		registerDamage(totalDamage);

		if (damageType.equals("Lightning") && !type.equals("Lightning")) {
			tickDamage = damage * 0.25;

		}

		return totalDamage;
	}

	public String toString() {
		return String.format("%s %s", type, this.getClass().getSimpleName());
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void breakFrozen() {
		frozen = false;
	}

}
