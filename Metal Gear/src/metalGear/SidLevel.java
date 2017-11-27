package metalGear;

import entity.SisiGuard;

public class SidLevel {

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

		public static int[][] LEVEL = { {W,W,W,W,W,W,W,W,W,W,W},
										{W,0,W,0,0,0,0,0,0,0,W},
										{W,B,W,W,0,0,0,0,0,0,W},
										{W,0,W,0,0,W,B,W,W,0,W},
										{W,B,0,0,0,0,0,0,0,0,W},
										{W,0,0,W,0,0,0,W,0,0,W},
										{W,W,B,W,W,0,0,0,0,0,W},
										{W,0,0,0,0,0,0,0,0,0,W},
										{W,0,0,0,W,0,0,0,0,0,W},
										{W,0,W,0,B,W,W,0,0,0,W},
										{W,0,W,0,W,0,0,0,0,E,W},
										{W,0,0,0,0,0,B,W,0,0,W},
										{W,W,W,W,W,W,W,W,W,W,W}};
		public static void createGuards() {
			
			//guards spawn
			GUARDS[0] = new SisiGuard(new int[][] {RIGHT,RIGHT,RIGHT,RIGHT,DOWN,DOWN,UP,UP,LEFT,LEFT,LEFT}, 1, 5);
			
			GUARDS[1] = new SisiGuard(new int[][] {RIGHT,DOWN,LEFT,UP}, 8, 7);
			
			GUARDS[2] = new SisiGuard(new int[][] {RIGHT,RIGHT,RIGHT,LEFT,LEFT,LEFT}, 7, 1);
			
			
			
		}

	}


