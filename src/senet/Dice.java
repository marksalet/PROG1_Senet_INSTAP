package senet;

import java.util.Random;

public class Dice {
	
	public int getStickNumber() {
		int number = this.getRandomStickNumber();
		
		if(number == 1) {
			return 1;
		} else if(number == 2) {
			return 2;
		} else if (number == 3){
			return 3;
		} else if (number == 4){
			return 4;
		} else if (number == 0){
			return 6;
		} else {
			return 0;
		}
	}
	
	public int getRandomStickNumber() {
		int white = 0;
		for (int i = 0; i < 4; i++) {
			Random rand = new Random();
			int randomNumber = rand.nextInt(2);
			
			if (randomNumber == 1) {
				white++;
			}
		}
		
		return white;
	}
}
