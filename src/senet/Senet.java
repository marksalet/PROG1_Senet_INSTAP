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
		movePlayer(playerOne, 10, 1);

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
					playerOne = new Player(nameTwo, "x");
					playerTwo = new Player(nameOne, "o");
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
	
	public int movePlayer(Player player, int oldValue, int diceThrow) {
		List<BoardTile> boardTiles = board.getBoardTiles();
		
		int value = oldValue + diceThrow;
		
		BoardTile oldTile = boardTiles.stream().filter(t -> t.getValue() == oldValue).findFirst().orElse(null);
		BoardTile tile = boardTiles.stream().filter(t -> t.getValue() == value).findFirst().orElse(null);
		BoardTile tileLeft = boardTiles.stream().filter(t -> t.getValue() == value - 1).findFirst().orElse(null);
		BoardTile tileRight = boardTiles.stream().filter(t -> t.getValue() == value + 1).findFirst().orElse(null);
		BoardTile firstEmpty = boardTiles.stream().filter(t -> t.getColor() == ".").findFirst().orElse(null);
		
		String oldTileColor = oldTile.getColor();
		String newTileColor = tile.getColor();
		int newTileValue = tile.getValue();
		String playerColor = player.getColor();
		String oppositePlayerColor = player.getOppositeColor();
		String newTileLeftColor = tileLeft.getColor();
		String newTileRightColor = tileRight.getColor();

		if (playerColor ==  oldTileColor && oldTileColor != oppositePlayerColor && playerColor != newTileColor) {
			if (newTileColor != "." && (newTileColor == newTileLeftColor || newTileColor == newTileRightColor)) {
				System.out.println("This tile is protected.");
				
				return 0;
			} else if (newTileValue == 27) { 
				oldTile.setColor(".");
				firstEmpty.setColor(playerColor);
				
				return 1;
			} else if ((newTileValue == 26) || (newTileValue == 28) || (newTileValue == 29)) { 
				System.out.println("This tile can't be attacked.");
				
				return 0;
			} else {
				oldTile.setColor(newTileColor);
				tile.setColor(oldTileColor);
				
				return 1;
			}
		} else if (playerColor !=  oldTileColor) {
			System.out.println("You have no piece on the tile " + oldValue + ".");
			
			return 0;
		} else if (playerColor ==  newTileColor) {	
			System.out.println("You have your own piece on the tile " + value + ".");
			
			return 0;
		} else {
			System.out.println("ERROR: Couldn't move your piece.");
			
			return 0;
		}
	}
	
	public void newMove(Player player) {
		try {
			System.out.println(player.getName() + " (" + player.getColor() + "), press <ENTER> to throw the dice");
			String enter = br.readLine();
			
			if(enter != null) {
				int newNumber = dice.getStickNumber();
				System.out.println(player.getName() + " (" + player.getColor() + "), rolled a " + newNumber);
				
				System.out.println(player.getName() + " (" + player.getColor() + "), which piece do you want to move?");
				String line = br.readLine();
				int move = 0;
				if (checkUserInputInt(line)) {
					move = Integer.parseInt(line);
					System.out.println(move);
				} else if (!checkUserInputInt(line)) {
					
				}
				
				int moveCode = movePlayer(player, move, newNumber);
				
				if (moveCode != 1) {
					tryAgain(player, newNumber);
				}
				System.out.println(board.showBoard());
				
				if ((newNumber == 1) || (newNumber == 4) || (newNumber == 6)) {
					newMove(player);
				} else {
					if (getCurrentMove() == "pOne") {
						setCurrentMove("pTwo");
					} else if (getCurrentMove() == "pTwo") {
						setCurrentMove("pOne");
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tryAgain(Player player, int diceThrow) {
		try {
			System.out.println(player.getName() + " (" + player.getColor() + "), which piece do you want to move?");
			int move = Integer.parseInt(br.readLine());
			
			int moveCode = movePlayer(player, move, diceThrow);
			
			if (moveCode == 2) {
				tryAgain(player, diceThrow);
			} else if (moveCode == 3) {
				tryAgain(player, diceThrow);
			} else if (moveCode == 0) {
				tryAgain(player, diceThrow);
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public boolean checkUserInputInt(String input) {
		if (isNumeric(input)) {
			if (board.checkValueWithString(input)) {
				return true;
			} else {
				System.out.println("Give an answer from 1 to 30");
				return false;
			}
		} else {
			System.out.println("Give an answer from 1 to 30 as numbers");
			return false;
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
	
	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
		    return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}
}
