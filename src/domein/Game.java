package domein;

import java.security.SecureRandom;

public class Game {
	
	SecureRandom sr = new SecureRandom();
	private String affinity;
	private Enemy enemy;
	private Elements el;
	private boolean fullpower = false;
	private int critChance=0;
	private boolean criticalHit=false;
	
	protected double manapool = sr.nextInt(1500) + 250;
	
	public Game(String affinity) {
		this.affinity=affinity;
	}
	
//	private void determineAffinity() {
//		affinity=Elements.ELEMENTS.get(sr.nextInt(Elements.ELEMENTS.size()));
//	}
	
	public void registerEnemy(String enemy) {
		switch (enemy.toLowerCase()) {
		case "dragon":
			this.enemy = new Dragon();
			return;

		case "troll":
			this.enemy = new Troll();
			return;

		case "goblin":
			this.enemy = new Goblin();
			return;
		}
	}
	
	public double attack(String element) {
		if (element.equals("Fire")) {
			el = new Fire(manapool);
		} else if (element.equals("Water")) {
			el = new Water(manapool);
		} else if (element.equals("Lightning")){
			el = new Lightning(manapool);
		} else if (element.equals("Wind")) {
			el = new Wind(manapool);
		} else {
			el= new Earth(manapool);
		}
		if (fullpower) {
			el.goAllOut();
			fullpower = false;
		}
		double damage = el.attack()*activateCrit();
		if (el instanceof Wind) {
			critChance++;
		}
		if (el.getClass().getSimpleName().equals(affinity)) {
			damage *= 2;
		}
		double totalDamage = enemy.takeDamage(el.getClass().getSimpleName(), damage);
		manapool = el.getMana();
		return totalDamage;
	}
	
	public double attackBack() {
		double damage = enemy.attackBack();

		if (damage == 0) {
			return 0;
		}
		el.activateShield(damage);
		manapool = el.getMana();
		return damage;
	}
	
	public void useAllOutAttck() {
		fullpower = true;
	}
	
	public double getEnemyHealth() {
		return enemy.getHealth();
	}
	
	private int activateCrit() {
		criticalHit=false;
		if (critChance == 9) {
			critChance = 0;
			criticalHit=true;
			return 2;
		}
		int chance = sr.nextInt(10 - critChance);
		if (chance == 1) {
			criticalHit=true;
			return 2;
		}

		return 1;
	}
	
	public boolean isCriticalHit() {
		return criticalHit;
	}

	public boolean outOfMana() {
		if (manapool <= 1) {
			return true;
		}
		return false;
	}

	public boolean isDefeated() {
		if (enemy.getHealth() <= 0) {
			return true;
		}
		return false;
	}

	public double getManapool() {
		return manapool;
	}

	public String getEnemy() {
		return enemy.toString();
	}

	public String getAffinity() {
		return affinity;
	}
	
	



}
