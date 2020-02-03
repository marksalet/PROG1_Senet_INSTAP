package senet;

public class BoardTile {
	int position;
	int value;
	String content;
	
	public BoardTile(int pos, int val, String con) {
		this.position = pos;
		this.value = val;
		this.content = con;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setColor(String content) {
		this.content = content;
	}
	
	public String getColor() {
		return content;
	}
}
