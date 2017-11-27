package metalGear;

import java.util.Scanner;

public class CaveExplorer {

	public static CaveRoom[][] caves;//every room in the cave
	public static Scanner in;//for user input
	public static CaveRoom currentRoom;//changes based on movement
	public static Inventory inventory;//where all objects found in a cave are kept
	public static boolean playing = true;
	public static int currentlvl;
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		CaveRoom.setUpCaves();
		currentlvl = 0;
		
		inventory = new Inventory();
		startExploring();
	}


	private static void startExploring() {
		while(playing) {
			print(inventory.getDescription());
			print(currentRoom.getDescription());
			print("What would you like to do?");
			String input = in.nextLine();
			currentRoom.interpretInput(input);
		}
		
	}
	
	public static void print(String s){
		//NOTE: later, you can replace this line with the
		//more sophisticated "multiLinePrint" from Chatbot
		System.out.println(s);
	}
	
}
