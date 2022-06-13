package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Elements {
	private static SecureRandom sr = new SecureRandom();

	protected double manapool = sr.nextInt(1500) + 250;
	private double totalDamage = 0;
	private boolean allOut = false;
	public final static List<String> ELEMENTS = new ArrayList<>(
			Arrays.asList("Fire", "Water", "Lightning", "Earth", "Wind"));

	public Elements(double manapool) {
		this.manapool = manapool;
	}

	public double attack(double value) {
		if (allOut) {
			double damage = manapool * attackFails() * value;
			useAttack(manapool);
			return damage;
		} else {
			double power = power();
			useAttack(power);
			return power * attackFails() * value;
		}
	};

	protected void addToTotalDamage(double damage) {
		totalDamage += damage;
	}

	public int power() {
		return sr.nextInt(101) + 50;
	}

	protected void useAttack(double power) {

		if (manapool - power <=0) {
			manapool=0;
		}
		if (allOut) {
			manapool = 0;
			allOut = false;
			return;
		}
		changeMana(power);
	}

	private int attackFails() {
		int chance = 0;
		if (allOut) {
			chance = sr.nextInt(5);
		} else {
			chance = sr.nextInt(100);
		}

		if (chance == 1) {
			return 0;
		}
		return 1;
	}

	public void activateShield(double damage) {
		int chance = sr.nextInt(10);
		if (chance == 1) {
			changeMana(damage / 1.5);
		} else {
			changeMana(damage);
		}

	}

	public void goAllOut() {
		allOut = true;
	}

	public boolean isAllOut() {
		return allOut;
	}

	private void changeMana(double value) {
		if (manapool - value > 0) {
			manapool -= value;
		} else {
			manapool = 0;
		}
	}

	public double getTotalDamage() {
		return totalDamage;
	}

	public double getMana() {
		return manapool;
	}

	public String toString() {
		return String.format("Your element is %s", this.getClass().getSimpleName());
	}

	protected abstract double attack();

}
