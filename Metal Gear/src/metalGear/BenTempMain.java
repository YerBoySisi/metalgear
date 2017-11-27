package metalGear;

import java.util.Arrays;
import java.util.Scanner;

import entity.BenBreakableWall;
import entity.BenCamera;
import entity.SisiGuard;
import entity.BenThing;
import entity.BenWall;
import explorer.CaveExplorer;

public class BenTempMain {
	
	public static boolean playing;
	
	private static int[][] lvl; // level represented by ints
	public static BenThing[][] olvl; // actually level; made of objects
	private static Scanner in;
	

	public static entity.SidPlayer p; // Player is represented by p
	public static BenCamera c; // Camera is represented by c
	public static SisiGuard[] g; // Array of all guards
	
	
	public static void playLevel() {
		if(CaveExplorer.currentlvl == 0) {
			playBen();
		}else if(CaveExplorer.currentlvl == 1) {
			playSid();
		}else {
			playSisi();
		}
	}
	
	public static void playSid() {
		playing = true;
		
		in = new Scanner(System.in);
		
		p = new entity.SidPlayer(SidLevel.PLAYERSPAWN[0], SidLevel.PLAYERSPAWN[1]);
		c = new BenCamera(-1,-1);
		SidLevel.createGuards();
		g = SidLevel.GUARDS;
		
		lvl = SidLevel.LEVEL;
		convertLevel();
		
		for(int i = 0; i < g.length; i++) {
			olvl[g[i].getRow()][g[i].getColumn()] = g[i];
		}
		
		print("\n\n\n");
		
		playGame();
	}
	
	public static void playBen() {
		playing = true;
		
		in = new Scanner(System.in);
		
		p = new entity.SidPlayer(BenLevel.PLAYERSPAWN[0], BenLevel.PLAYERSPAWN[1]);
		c = new BenCamera(-1,-1);
		BenLevel.createGuards();
		g = BenLevel.GUARDS;
		
		lvl = BenLevel.LEVEL;
		convertLevel();
		
		for(int i = 0; i < g.length; i++) {
			olvl[g[i].getRow()][g[i].getColumn()] = g[i];
		}
		
		print("\n\n\n");
		
		playGame();
	}
	
	public static void playSisi() {
		
		playing = true;
		
		in = new Scanner(System.in);
		
		p = new entity.SidPlayer(SisiLevel.PLAYERSPAWN[0], SisiLevel.PLAYERSPAWN[1]);
		c = new BenCamera(-1,-1);
		SisiLevel.createGuards();
		g = SisiLevel.GUARDS;
		
		lvl = SisiLevel.LEVEL;
		convertLevel();
		
		for(int i = 0; i < g.length; i++) {
			olvl[g[i].getRow()][g[i].getColumn()] = g[i];
		}
		
		print("\n\n\n");
		
		playGame();
		
		dialouge("Snake... We did it!/ Thank you so much for playing our game!!!/ Game by:/Ben, Sisi, Sid");
	}
	

	public static void playGame() {
		int psn;
		int dirFacing;
		int[] convertedDir;
		String[][] render;
		
		int tempPlayerR;
		int tempPlayerC;
		
		while(playing) {
			
			//GUARDS:
			for(int i = 0; i < g.length; i++) {
				
				if(g[i].isAlive()) {
					g[i].act();
				}
				
			}
			
			
			//DISPLAY:
			render = new String[olvl.length][olvl[0].length];
			
			for (String[] row: render)
			    Arrays.fill(row, ".");
			
			render = rayCast(render);
			
			if(c.isCameraPlaced()) {
				//temporarily move player to camera location for raycast;
				tempPlayerR = p.getR();
				tempPlayerC = p.getC();
				p.rayMove(c.getR(),c.getC());
				render = rayCast(render);
				p.rayMove(tempPlayerR,tempPlayerC);
				render[c.getR()][c.getC()] = "C";
				
			}
			
			render[p.getR()][p.getC()] = "X";
			
			
			displayRender(render);
			
			
			
			
			
//INPUT:
			
			//if the camera is placed, and player can drop gaurd
			if(c.isCameraPlaced() && p.pickedUpGuard()) {
				psn = getInput("wasdg");
			//if the camera is placed, and player cant drop gaurd
			}else if(c.isCameraPlaced()) {
				psn = getInput("wasd");
			//if camera is not placed and player can drop gaurd
			}else if(p.pickedUpGuard()){
				psn = getInput("wasdgc");
			//if camera is not placed and player cant drop gaurd
			}else {
				psn = getInput("wasdwc");	
			}
			
			//place camera button pushed
			if(psn == 5) {
				dialouge("select direction for camera");
				dirFacing = getInput("wasd");
				convertedDir = convertDir(dirFacing);
				while(!olvl[p.getR() + convertedDir[0]][p.getC() + convertedDir[1]].toString().equals(" ")) {
					print("invalid input");
					dialouge("select direction for camera");
					dirFacing = getInput("wasd");
					convertedDir = convertDir(dirFacing);
				}
				c.placeCamera(p.getR() + convertedDir[0],p.getC() + convertedDir[1]);
			}
			
			
			
			//gaurd button pushed
			if(psn==4) {
				
				dialouge("select direction for gaurd");
				dirFacing = getInput("wasd");
				convertedDir = convertDir(dirFacing);
				while(!olvl[p.getR() + convertedDir[0]][p.getC() + convertedDir[1]].toString().equals(" ")) {
					print("invalidd input");
					dialouge("select direction for gaurd");
					dirFacing = getInput("wasd");
					convertedDir = convertDir(dirFacing);
				}

				olvl[p.getR() + convertedDir[0]][p.getC() + convertedDir[1]] = p.getCurrentGuard();
				p.getCurrentGuard().currentRow = p.getR() + convertedDir[0];
				p.getCurrentGuard().currentCol = p.getC() + convertedDir[1];
				
				
				p.setCurrentGuard(null);
				p.pickUpGuard(false);
				
			}
			
			
			convertedDir = convertDir(psn);

			updateOlvlPlayer();
			olvl[p.getR() + convertedDir[0]][p.getC() + convertedDir[1]].interact();
			
			for(SisiGuard guard: g) {
				
				if(guard.isAlive()) {
					guard.setFieldOfView();
				}
				
			}
			
			//IF SPOTTED BY GUARDS:
			
			for(SisiGuard guard: g) {
				
				int tempPR = p.getR();
				int tempPC = p.getC();
				
				for(SisiGuard gg: g) {
					if(!gg.isAlive()) {
						if(gg.seenByGuard(guard)) {
							gg.remove();
							guard.alert();
							dialouge("Snake... a guard just saw a dead body!");
						}
					}
				}
				p.rayMove(tempPR,tempPC);
				
				if(p.seenByGuard(guard)) {
					gameOver();
					break;
				}
				
				if(c.seenByGuard(guard)) {
					c.interact();
					guard.alert();
					dialouge("Snake... a guard just broke your camera!");
				}
				
			}
			
			
			
		}
	}
	
	public static void gameOver() {
		String gameOverSTR = ""
				+ " ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ ="  
				+ "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗=" 
				+ "██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝="
				+ "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗="
				+ "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║="
				+ " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝="
				+ "/...Hit enter to restart the mission";
		
		// placeholder: String gameOverSTR = "GAME OVER";
		dialouge(gameOverSTR,3);
		playLevel();
	}
	
	
	
	public static void dialouge(String s, int speed) {
		String temp = "";
		for(int i =0; i< s.length(); i++) {
			temp = s.substring(i, i+1);
			if(temp.equals("/")) {
				pause(1000);
				print("\n");
			}else if(temp.equals("=")){
				print("");
			}else {
				pause(speed);
				System.out.print(temp);
			}
		}
		print("");
		pause(500);
	}



	/** gets user input with input of possible inputs**/
	public static int getInput(String possibilities) {
		
		int psn;
		String input = in.nextLine();
		psn = possibilities.indexOf(input);
		while(psn == -1) {
			psn = possibilities.indexOf(input);
			print("invalid input");
			input = in.nextLine();
		}
		return psn;
	}
	
	/** quick hand for System.out.println**/
	public static void print(String s) {System.out.println(s);}
	
	/** USED FOR RENDERING: Get coordinates of all border walls**/
	public static int[][] getBorderCoords(){
		int[][] borderCoords = new int[olvl.length*2+olvl[0].length*2][2];
		
		//for all rows
		int tempCount = 0;
		for(int i = 0; i < olvl.length; i+=olvl.length-1) {
			for(int j = 0; j < olvl[0].length; j++) {
				borderCoords[tempCount][0] = i;
				borderCoords[tempCount][1] = j;
				//System.out.println(i + " " + j);
				tempCount++;
			}
		}
		//for all cols
		for(int i = 0; i < olvl[0].length; i+=olvl[0].length-1) {
			for(int j = 0; j < olvl.length; j++) {
				borderCoords[tempCount][0] = j;
				borderCoords[tempCount][1] = i;
				tempCount++;
			}
		}
		return borderCoords;
	}
	
	/** USED FOR RENDERING: Get slopes of all lines from player to each border coord **/
	public static double[] getSlopes(int[][] borderCoords) {
		double[] slopes = new double[borderCoords.length];

		for(int i = 0; i < borderCoords.length; i++) {			
			slopes[i] = (double) (borderCoords[i][0]-p.getR())/ (double) (borderCoords[i][1]-p.getC());

		}
		return slopes;
	}
	
	/** USED FOR RENDERING: RAY CAST**/
	public static String[][] updateTopSteep(String[][] render,double slope){
		double checkC;
		//go row by row, decreasing, to get cols
		for(int u = p.getR(); u >= 0; u--) {
			checkC = p.getC()-((p.getR()-u)/slope); 

			//if checkC is an integer:
			if ((checkC == Math.floor(checkC)) && !Double.isInfinite(checkC)) {
				render[u][(int) checkC] = olvl[u][(int) checkC].toString();
			    if(olvl[u][(int) checkC] instanceof BenWall) {
			    		
			    		
			    		olvl[u][(int) checkC].makeDiscovered();
			    		break;
			    }
			 //else check the boxes to left and right
			}else {
				
				render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				if(olvl[u][(int) checkC] instanceof BenWall) {
			    		
			    		olvl[u][(int) checkC].makeDiscovered();
			    		break;
			    }
				render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
				if(olvl[u][(int) checkC+1] instanceof BenWall) {
		    			
		    			olvl[u][(int) checkC].makeDiscovered();
		    			break;
				}
			}
		
		}
		return render;
	}
	
	/** USED FOR RENDERING: RAY CAST**/
	public static String[][] updateTopShallow(String[][] render, double slope){
		double checkR;
		
		//go col by col, to get rows
		
		//for increasing col
		if(slope < 0) {
			for(int u = p.getC(); u < olvl[0].length; u++) {
				checkR = p.getR()- (p.getC()-u)*slope;
				//print("slopee: "+ slope);
				
				//if checkC is an integer:
				if ((checkR == Math.floor(checkR)) && !Double.isInfinite(checkR)) {
					render[(int) checkR][u] = olvl[(int) checkR][u].toString();
				    if(olvl[(int) checkR][u] instanceof BenWall) {
				    		
				    		olvl[(int) checkR][u].makeDiscovered();
				    		break;
				    }
				 //else check the boxes to left and right
				}else {
					render[(int) checkR][u] = olvl[(int) checkR][u].toString();	
					if(olvl[(int) checkR][u] instanceof BenWall) {
				    		
				    		olvl[(int) checkR][u].makeDiscovered();
				    		break;
				    }
					render[(int) checkR+1][u] = olvl[(int) checkR][u].toString();
					if(olvl[(int) checkR+1][u] instanceof BenWall) {
			    			
			    			olvl[(int) checkR][u].makeDiscovered();
			    			break;
					}
				}
			}
		
		//for decreasing col
		}else {
			for(int u = p.getC(); u >= 0; u--) {
				checkR = p.getR()- (p.getC()-u)*slope;
				//print("slopee: "+ slope);
				
				//if checkC is an integer:
				if ((checkR == Math.floor(checkR)) && !Double.isInfinite(checkR)) {
					render[(int) checkR][u] = olvl[(int) checkR][u].toString();
				    if(olvl[(int) checkR][u] instanceof BenWall) {
				    		
				    		olvl[(int) checkR][u].makeDiscovered();
				    		break;
				    }
				 //else check the boxes to left and right
				}else {
					render[(int) checkR][u] = olvl[(int) checkR][u].toString();
					if(olvl[(int) checkR][u] instanceof BenWall) {
				    		
				    		olvl[(int) checkR][u].makeDiscovered();
				    		break;
				    }
					render[(int) checkR+1][u] = olvl[(int) checkR+1][u].toString();
					if(olvl[(int) checkR+1][u] instanceof BenWall) {
			    			
			    			olvl[(int) checkR][u].makeDiscovered();
			    			break;
					}
				}
			}
		}
		return render;
	}
	
	/** USED FOR RENDERING: RAY CAST**/
	public static String[][] updateBottomSteep(String[][] render, double slope){
		double checkC;
		for(int u = p.getR(); u < olvl.length; u++ ) {
			checkC = p.getC()-((p.getR()-u)/slope);
			//print("slope: "+ slope);
			//if checkC is an integer:
			if ((checkC == Math.floor(checkC)) && !Double.isInfinite(checkC)) {
				render[u][(int) checkC] = olvl[u][(int) checkC].toString();
			    if(olvl[u][(int) checkC] instanceof BenWall) {
			    		
			    		olvl[u][(int) checkC].makeDiscovered();
			    		break;
			    }
			 //else check the boxes to left and right
			}else {
				render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				if(olvl[u][(int) checkC] instanceof BenWall) {
			    		
			    		olvl[u][(int) checkC].makeDiscovered();
			    		break;
			    }
				render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
				if(olvl[u][(int) checkC+1] instanceof BenWall) {
		    			
		    			olvl[u][(int) checkC].makeDiscovered();
		    			break;
				}
			}
		}
		return render;
	}
	
	/** USED FOR RENDERING: RAY CAST**/
	public static String[][] updateBottomShallow(String[][] render, double slope){
		double checkR;
		
		for(int u = p.getC(); u >= 0; u-- ) {
			checkR = p.getR()- (p.getC()-u)*slope;
			
			//print("u: "+ u + " slope: "+slope + " player r, c" + p.getR() + p.getC());
			
			//if checkC is an integer:
			if ((checkR == Math.floor(checkR)) && !Double.isInfinite(checkR)) {
				render[(int) checkR][u] = olvl[(int) checkR][u].toString();
			    if(olvl[(int) checkR][u] instanceof BenWall) {
			    		
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }
			 //else check the boxes to left and right
			}else {
				render[(int) checkR][u] = olvl[(int) checkR][u].toString();
				if(olvl[(int) checkR][u] instanceof BenWall) {
			    		
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }
				render[(int) checkR+1][u] = olvl[(int) checkR+1][u].toString();
				if(olvl[(int) checkR+1][u] instanceof BenWall) {
		    			
		    			olvl[(int) checkR][u].makeDiscovered();
		    			break;
				}
			}
			
		}
		return render;
	}
	
	/** USED FOR RENDERING: RAY CAST**/
	public static String[][] updateLeftSteep(String[][] render, double slope){

		double checkC;
		
		//for decreasing row
		if(slope > 0) {
			for(int u = p.getR(); u >=0; u-- ) {
				checkC = p.getC()-((p.getR()-u)/slope);
				//print("slop: "+ slope);
				//print("c/: "+checkC);
				//if checkC is an integer:
				if ((checkC == Math.floor(checkC)) && !Double.isInfinite(checkC)) {
					render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    if(olvl[u][(int) checkC] instanceof BenWall) {
				    		
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }
				 //else check the boxes to left and right
				}else {
					render[u][(int) checkC] = olvl[u][(int) checkC].toString();
					if(olvl[u][(int) checkC] instanceof BenWall) {
				    		
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }
					render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
					if(olvl[u][(int) checkC+1] instanceof BenWall) {
			    			
			    			olvl[u][(int) checkC].makeDiscovered();
			    			break;
					}
				}
			}
		
		//for increasing row
		}else {
			for(int u = p.getC(); u < olvl.length; u++ ) {
				checkC = p.getC()-((p.getR()-u)/slope);
				//if checkC is an integer:
				if ((checkC == Math.floor(checkC)) && !Double.isInfinite(checkC)) {
					render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    if(olvl[u][(int) checkC] instanceof BenWall) {
				    		
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }
				 //else check the boxes to left and right
				}else {
					render[u][(int) checkC] = olvl[u][(int) checkC].toString();
					if(olvl[u][(int) checkC] instanceof BenWall) {
				    		
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }
					render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
					if(olvl[u][(int) checkC+1] instanceof BenWall) {
							olvl[u][(int) checkC+1].makeDiscovered();
			    			break;
					}
				}
			}
		}
		
		return render;
	}

	/** USED FOR RENDERING: RAY CAST**/
	public static String[][] updateLeftShallow(String[][] render, double slope){

		double checkR;
		
		//go decreasing cols to find row
		for(int u = p.getC(); u >= 0; u--) {
			checkR = p.getR()- (p.getC()-u)*slope;
			//print("slopee: "+ slope);
			
			//if checkC is an integer:
			if ((checkR == Math.floor(checkR)) && !Double.isInfinite(checkR)) {
				render[(int) checkR][u] = olvl[(int) checkR][u].toString();
			    if(olvl[(int) checkR][u] instanceof BenWall) {
			    		
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }
			 //else check the boxes to left and right
			}else {
				render[(int) checkR][u] = olvl[(int) checkR][u].toString();
				if(olvl[(int) checkR][u] instanceof BenWall) {
			    		
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }
				render[(int) checkR+1][u] = olvl[(int) checkR+1][u].toString();
				if(olvl[(int) checkR+1][u] instanceof BenWall) {
		    			
		    			olvl[(int) checkR][u].makeDiscovered();
		    			break;
				}
			}
		}
		return render;
	}
	
	/** USED FOR RENDERING: RAY CAST**/
	public static String[][] updateRightSteep(String[][] render, double slope){
		double checkC;
		
		//for increasing row
		if(slope > 0) {
			for(int u = p.getR(); u < olvl.length; u++ ) {
				checkC = p.getC()-((p.getR()-u)/slope);
				//print("slop: "+ slope);
				//print("c/: "+checkC);
				//if checkC is an integer:
				if ((checkC == Math.floor(checkC)) && !Double.isInfinite(checkC)) {
					render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    if(olvl[u][(int) checkC] instanceof BenWall) {
				    		
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }
				 //else check the boxes to left and right
				}else {
					render[u][(int) checkC] = olvl[u][(int) checkC].toString();
					if(olvl[u][(int) checkC] instanceof BenWall) {
				    		
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }
					render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
					if(olvl[u][(int) checkC+1] instanceof BenWall) {
			    			
			    			olvl[u][(int) checkC+1].makeDiscovered();
			    			break;
					}
				}
			}
		
		//for decreasing row
		}else {
			for(int u = p.getR(); u >=0; u-- ) {
				checkC = p.getC()-((p.getR()-u)/slope);
				//print("slop: "+ slope);
				//print("c/: "+checkC);
				//if checkC is an integer:
				if ((checkC == Math.floor(checkC)) && !Double.isInfinite(checkC)) {
					render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    if(olvl[u][(int) checkC] instanceof BenWall) {
				    		
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }
				 //else check the boxes to left and right
				}else {
					render[u][(int) checkC] = olvl[u][(int) checkC].toString();
					if(olvl[u][(int) checkC] instanceof BenWall) {
				    		
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }
					render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
					if(olvl[u][(int) checkC+1] instanceof BenWall) {
			    			
			    			olvl[u][(int) checkC+1].makeDiscovered();
			    			break;
					}
				}
			}
		}
		
		return render;
	}
	
	/** USED FOR RENDERING: RAY CAST**/
	public static String[][] updateRightShallow(String[][] render, double slope){
		double checkR;
		
		//go decreasing cols to find row
		for(int u = p.getC(); u < olvl[0].length; u++) {
			checkR = p.getR()- (p.getC()-u)*slope;
			//print("slopee: "+ slope);
			
			//if checkC is an integer:
			if ((checkR == Math.floor(checkR)) && !Double.isInfinite(checkR)) {
				render[(int) checkR][u] = olvl[(int) checkR][u].toString();
			    if(olvl[(int) checkR][u] instanceof BenWall) {
			    		
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }
			 //else check the boxes to left and right
			}else {
				render[(int) checkR][u] = olvl[(int) checkR][u].toString();
				if(olvl[(int) checkR][u] instanceof BenWall) {
			    		
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }
				render[(int) checkR+1][u] = olvl[(int) checkR+1][u].toString();
				if(olvl[(int) checkR+1][u] instanceof BenWall) {
		    			
		    			olvl[(int) checkR+1][u].makeDiscovered();
		    			break;
				}
			}
		}
		return render;
	}
	
	/** USED FOR RENDERING: Sends ray cast**/
	public static String[][] rayCast(String[][] render) {

		
		
		
		//STEP 1: for now, check all lines from x to border
		int[][] borderCoords = getBorderCoords();
		
		
		//STEP 2: get all slopes
		double[] slopes = getSlopes(borderCoords);
			

		
		//different thing for each side
		for(int i = 0; i < slopes.length; i++) {
			
			//top, bottom, left, right
			
			//check top
			if((i>= 0&&i<slopes.length/4)) {
				if(Math.abs(slopes[i])>=1) {
					render = updateTopSteep(render, slopes[i]);
				}else {
					render = updateTopShallow(render, slopes[i]);
				}
			}
				
			//check bottom
			if(i>= slopes.length/4 && i<slopes.length*2/4){
				if(Math.abs(slopes[i])>=1) {
					render = updateBottomSteep(render,slopes[i]);
				}else {
					render = updateBottomShallow(render,slopes[i]);
				}
			} 
			
			//check left
			if(i>= slopes.length*2/4&&i<slopes.length*3/4) {
				if(Math.abs(slopes[i])>1) {
					render = updateLeftSteep(render,slopes[i]);
					//render = updateTop
				}else {
					render = updateLeftShallow(render,slopes[i]);
				}
			}
			
			if(i>= slopes.length*3/4) {
				if(Math.abs(slopes[i])>1) {
					render = updateRightSteep(render,slopes[i]);
					//render = updateTop
				}else {
					render = updateRightShallow(render,slopes[i]);
				}
			}

			
		}
		return render;
		
	}
	
	

	/** USED FOR RENDERING: places empty at players old coord**/
	public static void updateOlvlPlayer() {
		olvl[p.getR()][p.getC()] = new BenThing(p.getR(),p.getC());
	}
	
	/** USED FOR RENDERING: RAY CAST**/
	public static int[] convertDir(int dir) {
		int[][] temp = {{-1,0},{0,-1},{1,0},{0,1},{0,0},{0,0}};
		return temp[dir];
	}
	
	
	/** For testing purposes**/
	public static int[][] setLevel1() {
		
		//blank = 0 
		//wall = 1
		//breakable wall = 2
		//IntelFile = 3;
		//extraction point = 4

		int[][] temp= {
				{1,1,1,1,1,1,1,1,1,1},
				{1,2,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,3,0,0,1},
				{1,0,0,0,0,0,1,0,0,1},
				{1,0,0,2,0,0,1,0,0,1},
				{1,0,0,0,0,0,1,0,0,1},
				{1,0,0,0,0,0,1,0,0,1},
				{1,0,0,1,1,1,1,0,0,1},
				{4,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1}
		};
		
		return temp;
	}
	
	/** Showcase shading**/
	public static int[][] setLevel2() {
		
		//blank = 0 
		//wall = 1
		//player = 2

		int[][] temp= {
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
				{1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,1,1,0,0,1},
				{1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
				{1,0,0,0,2,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
				{1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
				{1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
				{1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
				{1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1},
				{1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,0,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		};
		
		return temp;
	}
	
	/** converts int[][] to thing[][]**/
	public static void convertLevel() {
		int temp = 0;
		
		olvl = new BenThing[lvl.length][lvl[0].length];
		for(int i = 0; i < lvl.length; i++) {
			for(int j = 0; j< lvl[0].length; j++) {
				temp = lvl[i][j];
				
				if(temp == 0) {
					olvl[i][j] = new BenThing(i,j);
				}else if(temp == 2) {
					olvl[i][j] = new BenBreakableWall(i,j);
				}else if(temp == 3) {
					olvl[i][j] = new BenIntelFile(i,j);
					olvl[i][j].makeDiscovered();
				}else if(temp == 4) {
					olvl[i][j] = new BenExtractionPoint(i,j);
				} else {
					olvl[i][j] = new BenWall(i,j);
				}
				
			}
		}
		
		olvl[p.getR()][p.getC()] = p;
		
	}
	
	/** Displays without shading**/
	public static void displayOLevel() {
		BenThing temp;
		
		for(int i = 0; i < lvl.length; i++) {
			for(int j = 0; j< lvl[0].length; j++) {
				temp = olvl[i][j];
				
				if(temp instanceof entity.SidPlayer) {
					System.out.print("X");
				}else if(temp instanceof BenBreakableWall) {
					System.out.print("◘");
				}else if(temp instanceof BenWall) {
					System.out.print("■");
				}else{
					System.out.print(" ");
				}
				
				
			}
			System.out.print("\n");
		}
	}
	
	/** Displays finaized render**/
	public static void displayRender(String[][] render) {

		for(int i = 0; i < render.length; i++) {
			for(int j = 0; j< render[0].length; j++) {
				
				if(olvl[i][j].isDiscovered()) {
					System.out.print(olvl[i][j].toString());
				}else {
					System.out.print(render[i][j]);
				}
			}
			System.out.print("\n");
		}
		
	}

	/** brief for story room**/
	public static void brief() {
		
		if(CaveExplorer.currentlvl==0) {
			dialouge("Hey Boss,/ Welocome to the base!/ We just finished the construction of the r&d and intel platforms.//"
					+ " Our intel team intercepted a tranmission from the soviets,= discussing their plans to build a fusion-based "
					+ "metal gear in Afganistan./ We need you to investigate.....");
		}else if(CaveExplorer.currentlvl == 1) {
			dialouge("Research into the intel files you just retrieved shows that not only is there bieng a metal gear being built/"
					+ "but the soviets are making big strides in advanced nuclear warfare. We will not stand for this./"
					+ "Luckily, they love keeping all their info in one package, so confiscating that should do the trick./"
					+ "You know what to do. Good luck.");
			
		}else if(CaveExplorer.currentlvl == 2) {
			dialouge("With those codes compromised, you probably saved millions of lives. Thank you for what you've done so far, snake.../"
					+ "Just do this last thing for us, and you'll be free to go./"
					+ "It's the same thing as before, just locate the startup codes for that dangerous metal gear./"
					+ "Something as powerful as that would be a huge pain in our necks, and cost the lives of many innocent civilians./"
					+ "It's all on you, soldier. Thank you.");
		}else if(CaveExplorer.currentlvl == 3) {
			dialouge("We appreciate all you've done, Snake./"
					+ "You're free to go. Have a good one.");
		}
		
		
	}
	
	/** slowly prints text**/
	public static void dialouge(String s) {
		String temp = "";
		for(int i =0; i< s.length(); i++) {
			temp = s.substring(i, i+1);
			if(temp.equals("/")) {
				pause(1000);
				print("\n");
			}else if(temp.equals("=")){
				print("");
			}else {
				pause(30);
				System.out.print(temp);
			}
		}
		print("");
		pause(500);
	}
	/** for dialouge()**/
	public static void pause(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {e.printStackTrace();}
	}

	/** REPLACES OBJECT IN OLVL AT COORDS WITH EMOTY SPACE **/
	public static void breakWall(int r, int c) {
		olvl[r][c] = new BenThing(r,c);
		//print("breaking wall at: " + r+ c);
		//print("player : " + p.getR() + p.getC());
	}
	
	/** For demonstration purposes**/
	//private static int[] startingPsn = {3,3};
	
	
	/*public static void main(String[] args) {
		//print(new Wall(1,1));
		//print("aaaaaaa");
		
		
		playing = true;
		
		in = new Scanner(System.in);
		
		p = new entity.Player(SisiLevel.PLAYERSPAWN[0], SisiLevel.PLAYERSPAWN[1]);
		c = new Camera(-1,-1);
		SisiLevel.createGuards();
		g = SisiLevel.GUARDS;
		
		lvl = SisiLevel.LEVEL;
		convertLevel();
		
		for(int i = 0; i < g.length; i++) {
			olvl[g[i].getRow()][g[i].getColumn()] = g[i];
		}
		
		print("olvl: ");
		
		displayOLevel();
		
		print("\n\n\n");
		//brief();
		
		playGame();
		
	}*/
}
