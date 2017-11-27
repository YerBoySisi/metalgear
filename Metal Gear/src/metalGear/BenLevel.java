package metalGear;

import entity.SisiGuard;

public class BenLevel {
	
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
	
	public static final int[] PLAYERSPAWN = {1, 1};
	public static final SisiGuard[] GUARDS = new SisiGuard[3];

	public static int[][] LEVEL = { 
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,4},
			{1,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,1},
			{1,2,1,1,0,0,0,0,0,1,1,0,0,1,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1},
			{1,0,0,0,0,0,1,0,0,0,0,0,0,1,2,1,1},
			{1,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,0,0,1,0,0,0,0,0,1,0,0,0,1},
			{1,3,0,1,0,0,1,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,2,0,0,2,0,0,0,1,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
			};
								   
	public static void createGuards() {
		
		//guards spawn
		GUARDS[0] = new SisiGuard(new int[][] {DOWN,DOWN,DOWN,LEFT, LEFT, RIGHT, RIGHT, UP, UP, UP}, 1, 4);
		
		GUARDS[1] = new SisiGuard(new int[][] {LEFT,UP,UP,RIGHT,RIGHT,RIGHT,DOWN,DOWN,DOWN,LEFT,LEFT,UP}, 4, 9);
		
		GUARDS[2] = new SisiGuard(new int[][] {UP,LEFT,DOWN,RIGHT}, 8, 5);
		
		
	}

}
