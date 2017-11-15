package explorer;

import java.util.Scanner;

public class tempMain {
	private static boolean playing;
	
	private static int[][] lvl;
	private static Thing[][] olvl;
	private static Scanner in;

	private entity.Player p;
	
	public static void main(String[] args) {
		playing = true;
		
		in = new Scanner(System.in);
		
		lvl = setLevel1();
		convertLevel();
		displayOLevel();
	}

	public void getInput() {
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
			
			olvl[p.getR() + convertedDir[0]][p.getC() + convertedDir[1]].interact();
			//intereact with thing at direction ____
		}
	}
	
	public static int[] convertDir(int dir) {
		int[][] temp = {{-1,0},{0,-1},{1,0},{0,1}};
		return temp[dir];
	}
	
	
	
	public static int[][] setLevel1() {
		
		//blank = 0 
		//wall = 1

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
					olvl[i][j] = new Thing(j,i);
				}else {
					olvl[i][j] = new Wall(j,i);
				}
				
			}
		}
		
	}
	
	public static void displayOLevel() {
		Thing temp;
		
		for(int i = 0; i < lvl.length; i++) {
			for(int j = 0; j< lvl[0].length; j++) {
				temp = olvl[i][j];
				
				if(temp instanceof Wall) {
					System.out.print("■");
				}else{
					System.out.print(" ");
				}
				
			}
			System.out.print("\n");
		}
	}
	
	
	
	
	public static void displayLevel() {
		int temp = 0;
		
		for(int i = 0; i < lvl.length; i++) {
			for(int j = 0; j< lvl[0].length; j++) {
				temp = lvl[i][j];
				
				if(temp == 0) {
					System.out.print(" ");
				}else {
					System.out.print("■");
				}
				
			}
			System.out.print("\n");
		}
		
	}
	
	

}
