package senet;

import java.util.ArrayList;
import java.util.List;

public class Senet {
	List<Player> players = new ArrayList<Player>();
	
	public void startGame(String playerOne, String playerTwo) {
		Board board = new Board();	
		
		this.setTurnOne(playerOne, playerTwo);
		
		System.out.println(board.showBoard());
	}
	
	public void setTurnOne(String playerOne, String playerTwo) {
		Dice dice = new Dice();
		
		String turn = playerOne;
		Boolean firstTurn = false;
		int roll;
		while (firstTurn == false) {
			if (turn == playerOne) {
				roll = dice.getStickNumber();
				System.out.println(playerOne + " rolled a " + roll);
				if (roll == 1) {
					this.addPlayer(playerOne, "x");
					this.addPlayer(playerTwo, "o");
					System.out.println(playerOne);
					firstTurn = true;
				}
				turn = playerTwo;
			} else if (turn == playerTwo) {
				roll = dice.getStickNumber();
				System.out.println(playerTwo + " rolled a " + roll);
				if (roll == 1) {
					this.addPlayer(playerOne, "o");
					this.addPlayer(playerTwo, "x");
					System.out.println(playerTwo);
					firstTurn = true;
				}
				turn = playerOne;
			}
		}
	}
	
	public void addPlayer(String name, String color) {
		Player playerName = new Player(name, color);
		players.add(playerName);
	}
	
	public void printPlayers() {
		players.forEach((item) -> {System.out.println(item.name);});
	}
}
