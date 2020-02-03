package senet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Senet {
	List<Player> players = new ArrayList<Player>();
	
	public void startGame(String playerOne, String playerTwo) {
		Board board = new Board();
		Dice dice = new Dice();
				
		this.setTurnOne(playerOne, playerTwo);
		board.initateTiles();
		board.movePlayer("x", 10, 11);
		
		System.out.println(board.showBoard());
		
		String currentMove = "pTwo";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (this.gameGoing(playerOne, playerTwo)) {
				if (currentMove == "pOne") {
					System.out.println(playerOne + " (x), press <ENTER> to throw the dice");
					String enter = br.readLine();
					
					if(enter != null) {
						int newNumber = dice.getStickNumber();
						System.out.println(playerOne + " (x), rolled a " + newNumber);
						
						System.out.println(playerOne + " (x), which piece do you want to move?");
						int move = Integer.parseInt(br.readLine());
						
						board.movePlayer("x", move, move + newNumber);
						System.out.println(board.showBoard());
						
						currentMove = "pTwo";
					}
				} else if (currentMove == "pTwo") {
					System.out.println(playerTwo + " (o), press <ENTER> to throw the dice");
					String enter = br.readLine();
					
					if(enter != null) {
						int newNumber = dice.getStickNumber();
						System.out.println(playerTwo + " (o), rolled a " + newNumber);
						
						System.out.println(playerTwo + " (o), which piece do you want to move?");
						int move = Integer.parseInt(br.readLine());
						
						board.movePlayer("o", move, move + newNumber);
						System.out.println(board.showBoard());
						
						currentMove = "pOne";
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public boolean gameGoing(String pOne, String pTwo) {
		Player playerOne = players.stream().filter(p -> p.getName().equals(pOne)).findFirst().orElse(null);
		Player playerTwo = players.stream().filter(p -> p.getName().equals(pTwo)).findFirst().orElse(null);
		
		if (playerOne.getFinishedPieces() == 5) {
			return false;
		} else if (playerTwo.getFinishedPieces() == 5) {
			return false;
		} else {
			return true;
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
