package senet;

import java.util.Random;

public class Dice {
	
	public int getStickNumber() {
		int number = this.getRandomStickNumber(5);
		
		if(number == 5) {
			return 6;
		} else {
			return number;
		}
	}
	
	public int getRandomStickNumber(int range) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(range);
		
		return randomNumber + 1;
	}
}
