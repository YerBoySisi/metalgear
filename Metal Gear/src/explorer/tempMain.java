package explorer;

import java.util.Arrays;
import java.util.Scanner;

public class tempMain {
	private static boolean playing;
	
	private static int[][] lvl;
	public static Thing[][] olvl;
	private static Scanner in;

	public static entity.Player p;
	private static int[] startingPsn = {1,1};
	
	public static void main(String[] args) {
		playing = true;
		
		in = new Scanner(System.in);
		
		p = new entity.Player(startingPsn[0],startingPsn[1],"tempPlayerss");
		
		lvl = setLevel1();
		convertLevel();
		displayOLevel();
		
		playGame();
		
	}

	public static void playGame() {
		int psn;
		int[] convertedDir;
		
		while(playing) {
			String input = in.nextLine();
			
			psn = "wasd".indexOf(input);
			while(psn == -1) {
				psn = "wasd".indexOf(input);
				input = in.nextLine();
				
			}
			
			
			convertedDir = convertDir(psn);
			

			updateOlvlPlayer();
			olvl[p.getR() + convertedDir[0]][p.getC() + convertedDir[1]].interact();
			
			displayOLevel();
			
			
		}
	}
	
	public static String[][] rayCast() {
		
		String[][] render = new String[olvl.length][olvl[0].length];
		Arrays.fill(render, "*");
		
		/* use helper method:
		 * 	given a green dot, find slope of line between dot and x
		 * 		check all boxes along the line
		 * 
		 * 		make a helper method that given a line, returns a list of all block on that ling
		 * 			ex:
		 * 			
		 * 			■■■■		■■■■		■■■■	
		 * 			■X ■		■X ■		■X ■
		 * 			■  ■  ->    ■**■  ->    ■12■
		 * 			■ P■		■ *■		■ 3■
		 *          ■ ■■		■ **        ■ 45
		 *             P		   *		   6
		 * 
		 * 				  (R,C)
		 *		X coords: (1,1)
		 *		P coords: (3,2)
		 *		
		 *		boxesTouches -> {(1,1), (2,1), (2,2), (3,2)}  -> X: (1,1) P: (3,2)
		 *			
		 *			*with that information, you loop through the array until you find a point that is a wall
		 *
		 *  _______________________________________________________________
		 *			
		 *			ex2:
		 * 			
		 * 			■■■■		■■■■		■■■■	
		 * 			■X ■		■X*■		■X1■
		 * 			■  ■P ->    ■ *** ->    ■ 234
		 * 			■  ■		■  ■		■  ■
		 *          ■ ■■		■ ■■        ■ ■■
		 *              		   		   
		 * 
		 * 				  (R,C)
		 *		X coords: (1,1)
		 *		P coords: (2,4)
		 *		
		 *		boxesTouches -> {(1,1), (1,2), (2,2), (2,3),(2,4)}  -> X: (1,1) P: (2,4)
		 *			
		 *			*with that information, you loop through the array until you find a point that is a wall
		 *
		 *			line:  
		 *				y =  
		 *				
		 *				
		 * 		
		 * */
		
	}
	
	
	
	
	
	
	
	
	public static void updateOlvlPlayer() {
		olvl[p.getR()][p.getC()] = new Thing(p.getR(),p.getC());
	}
	
	
	public static int[] convertDir(int dir) {
		int[][] temp = {{-1,0},{0,-1},{1,0},{0,1},{0,0}};
		return temp[dir];
	}
	
	
	
	public static int[][] setLevel1() {
		
		//blank = 0 
		//wall = 1
		//player = 2

		int[][] temp= {
				{1,1,1,1,1,1,1,1,1,1},
				{1,0,0,1,0,0,0,0,0,1},
				{1,0,0,1,0,0,0,0,0,1},
				{1,0,0,1,0,0,1,0,0,1},
				{1,0,1,1,0,0,1,0,0,1},
				{1,0,0,0,0,0,1,0,0,1},
				{1,0,0,0,0,0,1,0,0,1},
				{1,0,0,1,1,1,1,0,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1}
		};
		
		return temp;
	}
	
	public static void convertLevel() {
		int temp = 0;
		
		olvl = new Thing[lvl.length][lvl[0].length];
		for(int i = 0; i < lvl.length; i++) {
			for(int j = 0; j< lvl[0].length; j++) {
				temp = lvl[i][j];
				
				if(temp == 0) {
					olvl[i][j] = new Thing(i,j);
				}else {
					olvl[i][j] = new Wall(i,j);
				}
				
			}
		}
		
		olvl[p.getR()][p.getC()] = p;
		
	}
	
	public static void displayOLevel() {
		Thing temp;
		
		for(int i = 0; i < lvl.length; i++) {
			for(int j = 0; j< lvl[0].length; j++) {
				temp = olvl[i][j];
				
				if(temp instanceof entity.Player) {
					System.out.print("X");
				}else if(temp instanceof Wall) {
					System.out.print("■");
				}else{
					System.out.print(" ");
				}
				
			}
			System.out.print("\n");
		}
	}
	
	
	
	
	public static void displayRender(String[][] render) {
		//int temp = 0;
		
		for(int i = 0; i < render.length; i++) {
			for(int j = 0; j< render[0].length; j++) {
				//temp = render[i][j];
				System.out.print(render[i][j]);
				/*if(temp == 0) {
					System.out.print(" ");
				}else {
					System.out.print("■");
				}*/
				
			}
			System.out.print("\n");
		}
		
	}
	
	

}
