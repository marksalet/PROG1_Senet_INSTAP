package senet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			Dice dice = new Dice();
			Senet senet = new Senet();
			
			System.out.println("Welcome to Senet!");
			System.out.println("Would you like to play a normal game (0) or start a test position (1-3)?");
			
			String type = br.readLine();
			
			if(type.equals("0")) {
				System.out.println("Enter the name of the fist player:");
				String nameOne = br.readLine();
				
				if(nameOne != null) {

					System.out.println("Enter the name of the second player:");
					String nameTwo = br.readLine();
					
					if(nameTwo != null) {
						senet.startGame(nameOne, nameTwo);
					}
				}
			} else if (type.equals("1") || type.equals("2") || type.equals("3")) {
				System.out.println("You choose: " + type);
			} else {
				System.out.println("Error selecting game type");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
