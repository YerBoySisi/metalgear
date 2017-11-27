package metalGear;

import entity.Guard;

public class SisiLevel {
	
	//constants
	//guard directions
	public static final int[] UP = {-1, 0};
	public static final int[] DOWN = {1, 0};
	public static final int[] LEFT = {0, -1};
	public static final int[] RIGHT = {0, 1};
	
	//wall, breakable wall, intel file, extraction point
	public static int W = 1;
	public static int B = 2;
	public static int I = 3;
	public static int E = 4;
	
	public static final int[] PLAYERSPAWN = {21, 1};
	public static final Guard[] GUARDS = new Guard[7];

	public static int[][] LEVEL = { {W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W}, //0
									{E,0,B,0,B,0,0,B,0,0,W,0,0,0,0,0,B,0,0,0,0,B,W}, //1
									{W,B,W,W,0,0,0,0,0,0,W,0,0,0,0,0,W,0,0,0,W,0,W}, //2
									{W,0,W,0,0,W,0,W,W,0,0,0,0,W,0,0,0,0,0,W,0,0,W}, //3
									{W,B,0,0,0,0,0,0,0,0,0,B,B,0,0,0,0,0,W,0,0,0,W}, //4
									{W,0,0,B,0,0,0,W,0,0,W,0,0,0,0,B,0,0,0,0,0,0,W}, //5
									{W,W,W,W,W,0,0,0,0,0,W,0,0,0,0,B,0,0,0,W,0,0,W}, //6
									{W,0,0,0,0,0,0,0,0,0,0,B,0,0,0,0,0,0,0,W,0,0,W}, //7
									{W,0,0,0,W,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,W}, //8
									{W,0,W,0,B,W,W,0,0,W,B,B,B,W,0,0,0,0,0,0,0,0,W}, //9
									{W,0,W,0,W,0,0,0,0,B,W,W,B,B,0,0,W,W,B,B,W,W,W}, //10
									{W,0,0,0,0,0,B,W,0,W,B,I,B,W,0,0,0,W,0,0,W,0,W}, //11
									{W,W,0,W,W,0,W,0,0,B,W,W,W,B,0,0,0,0,0,0,0,0,W}, //12
									{W,0,B,B,W,0,0,B,0,W,W,B,B,W,0,0,0,0,0,W,0,0,W}, //13
									{W,0,0,0,W,0,B,W,0,0,0,0,0,0,0,0,0,0,0,W,0,0,W}, //14
									{W,0,0,0,B,0,0,0,B,W,0,B,0,0,0,0,0,0,0,0,0,0,W}, //15
									{W,0,W,W,0,0,0,0,0,0,0,W,0,W,0,W,0,W,0,0,B,0,W}, //16
									{W,0,W,W,0,0,W,0,W,0,0,B,0,0,W,B,0,B,W,0,0,B,W}, //17
									{W,0,W,W,0,0,0,0,0,0,0,0,0,0,W,B,0,B,0,0,0,0,W}, //18
									{W,0,0,0,0,W,0,W,B,W,0,0,0,0,W,B,0,W,0,0,0,0,W}, //19
									{W,B,W,W,0,0,0,W,W,W,W,0,W,0,W,W,0,W,W,B,W,W,W}, //20
									{E,0,B,W,0,0,0,B,0,B,0,0,0,B,0,0,0,0,0,0,B,0,W}, //21
									{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W}};//22 
								   //0 1 2 3 4 5 6 7 8 910111213141516171819202122
	public static void createGuards() {
		
		//guards spawn
		GUARDS[0] = new Guard(new int[][] {RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, 
										   DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
										   LEFT, LEFT, LEFT, LEFT, LEFT, LEFT,
										   UP, UP, UP, UP, UP, UP}, 8, 9);
		
		GUARDS[1] = new Guard(new int[][] {UP, UP, UP, 
										   DOWN, DOWN, DOWN, DOWN, 
										   RIGHT, RIGHT, RIGHT, 
										   DOWN, DOWN,
										   UP, UP,
										   LEFT, LEFT, LEFT, UP}, 18, 1);
		
		GUARDS[2] = new Guard(new int[][] {DOWN, DOWN, DOWN, 
										   UP, UP, UP}, 16, 16);
		
		GUARDS[3] = new Guard(new int[][] {DOWN, LEFT, DOWN, LEFT, DOWN, LEFT, DOWN, LEFT,
										   RIGHT, UP, RIGHT, UP, RIGHT, UP, RIGHT, UP}, 1, 5);
		
		GUARDS[4] = new Guard(new int[][] {UP, UP, UP, UP,
										   RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT,
										   LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT,
										   DOWN, DOWN, DOWN, DOWN}, 11, 3);
		
		GUARDS[5] = new Guard(new int[][] {DOWN, DOWN, DOWN, 
										   RIGHT, RIGHT, 
										   UP, UP, UP,
										   LEFT, LEFT}, 12, 18);
		
		GUARDS[6] = new Guard(new int[][] {DOWN, DOWN, 
										   RIGHT, RIGHT,
										   UP, UP, 
										   RIGHT, RIGHT, RIGHT, RIGHT,
										   DOWN, DOWN,
										   RIGHT, RIGHT,
										   UP, UP,
										   RIGHT, RIGHT, RIGHT,
										   LEFT, LEFT, LEFT,
										   DOWN, DOWN,
										   LEFT, LEFT,
										   UP, UP,
										   LEFT, LEFT, LEFT, LEFT,
										   DOWN, DOWN,
										   LEFT, LEFT,
										   UP, UP}, 9, 1);
		
		
	}

}
