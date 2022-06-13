package domein;

import java.util.Objects;

public class Player {
	
	private String name;
	private String wachtwoord;
	private String affinity;
	
	public Player(String name, String wachtwoord, String affinity) {
		setName(name);
		setWachtwoord(wachtwoord);
		setAffinity(affinity);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		if (name==null || name.length()<3) {
			throw new IllegalArgumentException("Your username must have more than 2 characters!");
		}
		this.name = name;
	}
	
	public String getWachtwoord() {
		return wachtwoord;
	}

	private void setWachtwoord(String wachtwoord) {
		if (wachtwoord==null || wachtwoord.length()<3 || !wachtwoord.matches("(\\w*\\d{1}){3,}")) {
			throw new IllegalArgumentException("Uw wachtwoord moet uit minstens 3 characters bestaan en minstens 2 cijfers bevatten!");
		}
		this.wachtwoord = wachtwoord;
	}

	public String getAffinity() {
		return affinity;
	}

	private void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", affinity=" + affinity + "]";
	}
	
	
	

	
	

}
