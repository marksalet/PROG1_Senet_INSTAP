package senet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Senet {
	List<Player> players = new ArrayList<Player>();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Board board = new Board();
	Dice dice = new Dice();
	
	private String currentMove = "pOne";
	private Player playerOne;
	private Player playerTwo;
	
	public void startGame(String nameOne, String nameTwo) {
		this.setTurnOne(nameOne, nameTwo);
		board.initateTiles();
		board.movePlayer("x", 10, 11);

		System.out.println(board.showBoard());
		
		while (this.gameGoing()) {
			if (currentMove == "pOne") {
				newMove(playerOne);
			} else if (currentMove == "pTwo") {
				newMove(playerTwo);
			}
		}
	}
	
	public void setTurnOne(String nameOne, String nameTwo) {
		Dice dice = new Dice();
		
		Boolean firstTurn = false;
		int roll;
		while (firstTurn == false) {
			if (currentMove == "pOne") {
				roll = dice.getStickNumber();
				System.out.println(nameOne + " rolled a " + roll);
				if (roll == 1) {
					playerOne = new Player(nameOne, "x");
					playerTwo = new Player(nameTwo, "o");
					players.add(playerOne);
					players.add(playerTwo);
					System.out.println(playerOne);
					firstTurn = true;
				}
				setCurrentMove("pTwo");
			} else if (currentMove == "pTwo") {
				roll = dice.getStickNumber();
				System.out.println(nameTwo + " rolled a " + roll);
				if (roll == 1) {
					playerOne = new Player(nameOne, "o");
					playerTwo = new Player(nameTwo, "x");
					players.add(playerOne);
					players.add(playerTwo);
					System.out.println(playerOne);
					System.out.println(playerTwo);
					firstTurn = true;
				}
				setCurrentMove("pOne");
			}
		}
	}
	
	public boolean newMove(Player player) {
		try {
			System.out.println(player.getName() + " (" + player.getColor() + "), press <ENTER> to throw the dice");
			String enter = br.readLine();
			
			if(enter != null) {
				int newNumber = dice.getStickNumber();
				System.out.println(player + " (" + player.getColor() + "), rolled a " + newNumber);
				
				System.out.println(player + " (" + player.getColor() + "), which piece do you want to move?");
				int move = Integer.parseInt(br.readLine());
				
				board.movePlayer(player.getColor(), move, move + newNumber);
				System.out.println(board.showBoard());
				
				if ((newNumber == 1) || (newNumber == 4) || (newNumber == 6)) {
					newMove(player);
				} else {
					return true;
				}
			} else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean gameGoing() {
		if (playerOne.getFinishedPieces() == 5) {
			return false;
		} else if (playerTwo.getFinishedPieces() == 5) {
			return false;
		} else {
			return true;
		}
	}
	
	public void printPlayers() {
		players.forEach((item) -> {System.out.println(item.name);});
	}
	
	public void setCurrentMove(String player) {
		currentMove = player;
	}
	
	public String getCurrentMove() {
		return currentMove;
	}
}
