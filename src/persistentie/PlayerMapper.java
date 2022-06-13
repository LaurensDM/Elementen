package persistentie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domein.Player;

public class PlayerMapper {
	private List<Player> players;
	
	
	
	public PlayerMapper() {
		players = new ArrayList<>();
		players.add(new Player("Guy1","EerstePwd123", "Fire"));
		players.add(new Player("Guy2","EerstePwd123", "Water"));
		players.add(new Player("Guy3","EerstePwd123","Lightning"));
		players.add(new Player("Guy4","EerstePwd123", "Wind"));
		players.add(new Player("Guy5","EerstePwd123","Earth"));
	}



	public List<Player> returnList(){
		return players;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public Player returnPlayer(String name, String password) {
		for (Player player : players) {
			if (player.getName().equals(name) && player.getWachtwoord().equals(password)) {
				return player;
			}
		}
		return null;
	}

}
