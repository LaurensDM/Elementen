package domein;


public class DomeinController {
	private Game game;
	private PlayerRepo repo;
	private String affinity;
	private Player player;
	
	
	
	public DomeinController() {
		repo = new PlayerRepo();
	}

	public void startGame() {
		game = new Game(affinity);
	}
	
	public void registerEnemy(String enemy) {
		game.registerEnemy(enemy);
	}

	public String attack(String element) {
		double damage = game.attack(element);
		if (damage==0) {
			return "Your attack failed!\n";
		}
		return String.format("%sYour attack did %.2f damage!%n", game.isCriticalHit()?"Critical hit!\n":"",damage);
	}

	public String attackBack() {
		double damage = game.attackBack();
		if (damage==0) {
			return "The enemy is frozen\n";
		}
		return String.format("Your shield withstood %.2f damage!%n", damage);
	}

	public void useAllOutAttack() {
		game.useAllOutAttck();
	}

	public double getEnemyHealth() {
		return game.getEnemyHealth();
	}

	public String geefEnemyHealth() {
		return String.format("The enemy has %.2f health left%n", game.getEnemyHealth());
	}

	public boolean outOfMana() {
		return game.outOfMana();
	}

	public boolean isDefeated() {
		return game.isDefeated();
	}

	public double getManapool() {
		return game.getManapool();
	}

	public String getEnemy() {
		return game.getEnemy();
	}

	public String geefAffinity() {
		return "Your affinity is " + game.getAffinity();
	}

	public void selectPlayer(String name, String password) {
		player = repo.selectPlayer(name, password);
	}
	
	public void registerPlayer(String name, String password, String affinity) {
		repo.registerPlayer(new Player(name, password, affinity));
	}
	
	public String giveDetailsPlayer() {
		return player.toString();
	}
	
}
