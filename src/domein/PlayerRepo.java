package domein;

import java.util.ArrayList;
import java.util.List;

import persistentie.PlayerMapper;

public class PlayerRepo {
	
	List<Player> players;
	private PlayerMapper mapper;

	public PlayerRepo() {
		mapper = new PlayerMapper();
		players = mapper.returnList();
	}
	
	public void registerPlayer(Player player) {
		
	}
	
	public Player selectPlayer(String name, String password) {
		Player player = givePlayerDetails(name, password);
		

		return player;
	}
	
	private Player givePlayerDetails(String name, String password)
	{
		return mapper.returnPlayer(name, password);
	}
}
