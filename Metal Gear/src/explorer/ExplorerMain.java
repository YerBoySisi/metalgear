package explorer;

import java.util.Scanner;

import caves.CaveRoom;
import entity.NPC;

public class ExplorerMain {

	public static CaveRoom[][] caves;//every room in the cave
	public static Scanner in;//for user input
	public static CaveRoom currentRoom;//changes based on how the user navigated
	public static Inventory inventory;//where all objects found in cave are kept
	public static boolean playing = true;
	public static NPC[] npcs;
	public static explorer.Thing[][] level;
	
	
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		CaveRoom.setUpCaves(5, 5);
		
		inventory = new Inventory();
		startExploring();
	}

	public explorer.Thing[][] makeLevel(int[][] input) {
		explorer.Thing[][] temp = new explorer.Thing[input.length][input[0].length];
		
		/*
		 * 0 - air / blank spot
		 * 1 - wall
		 * 2 - breakable wall
		 * 3 - gaurd
		 * 4 - door
		 * 5 - locked door
		 * 
		 * */
		
		for(int i =0; i< input.length; i++) {
			for(int j = 0; j < input[0].length; j++) {
				if(input[i][j] == 1) 
					temp[i][j] = new Wall(i,j);
				else if(input[i][j] == 2)
					temp[i][j] = new BreakableWall(i,j);
			}
		}
		
		
		
	
		return temp;
	
	
	}
	
	private static void startExploring() {
		while(playing) {
			npcActions();
			print(inventory.getDescription());
			print(currentRoom.getDescription());
			print("What would you like to do?");
			String input = in.nextLine();
			currentRoom.interpretInput(input);
		}
	}
	
	private static void npcActions() {
		for(NPC n: npcs) {
			n.act();
		}
		inventory.updateMap();
	}


	public static void print(String s) {
		//NOTE: later, you can replace this line with the more sophistocated "multiLinePrint" from Chatbot
		System.out.println(s);
	}

}
