package senet;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<BoardTile> boardTiles = new ArrayList<BoardTile>();	
	
	public void initateTiles() {
		for (int i=1;i<31;i++) {
			if ((i == 1) || (i == 3) || (i == 5) || (i == 7) || (i == 9)) {
				BoardTile boardTile = new BoardTile(i, i, "o");
				boardTiles.add(boardTile);
			} else if ((i == 2) || (i == 4) || (i == 6) || (i == 8) || (i == 10)) {
				BoardTile boardTile = new BoardTile(i, i, "x");
				boardTiles.add(boardTile);
			} else if (i > 10 && i < 21) {
				BoardTile boardTile = new BoardTile(31 - i, i, ".");
				boardTiles.add(boardTile);
			} else {
				BoardTile boardTile = new BoardTile(i, i, ".");
				boardTiles.add(boardTile);
			}
		}
	}
	
	public String showBoard() {
		int count = 0;
		String field = "";
		
		field += "+------------+\n";
		
		for (BoardTile tile : boardTiles) {
			if (count == 9) {
				field += tile.getColor() + " |\n";
				count = 0;
			} else if (count == 0) {
				field +="| " + tile.getColor();
				count++;
			} else {
				field += tile.getColor();
				count++;
			}	
		};
		
		field += "+------------+";
		
		return field;
	}
	
	public void movePlayer(String color, int oldValue, int value) {
		BoardTile oldTile = boardTiles.stream().filter(t -> t.getValue() == oldValue).findFirst().orElse(null);
		oldTile.setColor(".");
		BoardTile tile = boardTiles.stream().filter(t -> t.getValue() == value).findFirst().orElse(null);
		tile.setColor(color);
	}
}
