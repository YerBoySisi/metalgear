package explorer;

public class tempMain {
	private static boolean playing;
	
	private static int[][] lvl;
	private static Thing[][] olvl;

	private entity.Player p;
	
	public static void main(String[] args) {
		playing = true;
		
		
		lvl = setLevel1();
		convertLevel();
		displayOLevel();
	}

	public getInput() {
		while(playing) {
			String input = System.in.nextLine();
			
			while("wasd".indexOf(input) == -1) {
				input = ExplorerMain.in.nextLine();
			}
			
			olvl[p.getR()][p.getC()].interact();
			//interact with thing at direction ____
		}
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
