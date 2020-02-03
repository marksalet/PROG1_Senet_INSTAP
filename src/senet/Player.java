package senet;

public class Player {
	String name;
	String color;
	int finishedPieces = 0;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(String name, String color) {
		this.name = name;
		this.color= color;
	}
	
	public boolean equals(Object obj) {
		if (super.equals(obj) && obj instanceof Player) {
			Player p = (Player) obj;
			return this.name == p.name;
		} else {
			return false;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getFinishedPieces() {
		return finishedPieces;
	}
}
