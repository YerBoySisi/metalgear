package explorer;

import java.util.Arrays;
import java.util.Scanner;

public class tempMain {
	public static boolean playing;
	
	private static int[][] lvl;
	public static Thing[][] olvl;
	private static Scanner in;
	public static int currentlvl;

	public static entity.Player p;
	public static Camera c;
	private static int[] startingPsn = {3,3};
	
	public static void main(String[] args) {
		//print(new Wall(1,1));
		currentlvl=0;
		
		playing = true;
		
		in = new Scanner(System.in);
		
		p = new entity.Player(startingPsn[0],startingPsn[1],"tempPlayerss");
		c = new Camera(-1,-1);
		
		lvl = setLevel1();
		convertLevel();
		
		
		print("olvl: ");
		
		displayOLevel();
		
		print("\n\n\n");
		//brief();
		playGame();
		
	}

	public static void playGame() {
		int psn;
		int dirFacing;
		int[] convertedDir;
		String[][] render;
		
		int tempPlayerR;
		int tempPlayerC;
		
		while(playing) {
			
			//DISPLAY:
			render = new String[olvl.length][olvl[0].length];
			for (String[] row: render)
			    Arrays.fill(row, ".");
			
			render = rayCast(render);
			
			if(c.isCameraPlaced()) {
				//temporarily move player to camer locastion for raycast;
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
			
			if(c.isCameraPlaced()) {
				psn = getInput("wasd");
			}else {
				psn = getInput("wasdc");	
			}
			
			//place camera button pushed
			if(psn == 4) {
				print("select direction for camera");
				dirFacing = getInput("wasd");
				convertedDir = convertDir(dirFacing);
				while(!olvl[p.getR() + convertedDir[0]][p.getC() + convertedDir[1]].toString().equals(" ")) {
					print("select direction for camera");
					dirFacing = getInput("wasd");
				}
				//olvl[p.getR() + convertedDir[0]][p.getC() + convertedDir[1]] = c;
				c.placeCamera(p.getR() + convertedDir[0],p.getC() + convertedDir[1]);
			}
			//print("R "+p.getR());
			convertedDir = convertDir(psn);
			
			print("convertedPos: " + convertedDir[0] + " " + convertedDir[1]);
			updateOlvlPlayer();
			olvl[p.getR() + convertedDir[0]][p.getC() + convertedDir[1]].interact();
			
		}
	}
	
	public static int getInput(String possibilities) {
		int psn;
		String input = in.nextLine();
		
		psn = possibilities.indexOf(input);
		while(psn == -1) {
			psn = possibilities.indexOf(input);
			input = in.nextLine();
		}
		return psn;
	}
	
	public static void print(String s) {System.out.println(s);}
	
	
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
				//System.out.println(j + " " + i);
				tempCount++;
			}
		}
		return borderCoords;
	}
	
	public static double[] getSlopes(int[][] borderCoords) {
		double[] slopes = new double[borderCoords.length];
		
		//print("pRow"+p.getR());
		//print("pCol"+p.getC());
		
		for(int i = 0; i < borderCoords.length; i++) {
			//m = y/x
			//print(borderCoords[i][0] + " ghgu" + borderCoords[i][1]);
			
			slopes[i] = (double) (borderCoords[i][0]-p.getR())/ (double) (borderCoords[i][1]-p.getC());
			
			//System.out.println(slopes[i]);
		}
		return slopes;
	}
	
	public static String[][] updateTopSteep(String[][] render,double slope){
		double checkC;
		//go row by row, decreasing, to get cols
		for(int u = p.getR(); u >= 0; u--) {
			checkC = p.getC()-((p.getR()-u)/slope); 

			//if checkC is an integer:
			if ((checkC == Math.floor(checkC)) && !Double.isInfinite(checkC)) {
			    if(olvl[u][(int) checkC] instanceof Wall) {
			    		
			    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
			    		olvl[u][(int) checkC].makeDiscovered();
			    		break;
			    }else {
			    		render[u][(int) checkC] = " ";
			    }
			 //else check the boxes to left and right
			}else {
				
				if(olvl[u][(int) checkC] instanceof Wall) {
			    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
			    		olvl[u][(int) checkC].makeDiscovered();
			    		break;
			    }else {
			    		render[u][(int) checkC] = " ";
			    }
				
				if(olvl[u][(int) checkC+1] instanceof Wall) {
		    			render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
		    			olvl[u][(int) checkC].makeDiscovered();
		    			break;
				}else {
					render[u][(int) checkC+1] = " ";
				}
			}
		
		}
		return render;
	}
	
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
				    if(olvl[(int) checkR][u] instanceof Wall) {
				    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();
				    		olvl[(int) checkR][u].makeDiscovered();
				    		break;
				    }else {
				    		render[(int) checkR][u] = " ";
				    }
				 //else check the boxes to left and right
				}else {
					
					if(olvl[(int) checkR][u] instanceof Wall) {
				    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();	
				    		olvl[(int) checkR][u].makeDiscovered();
				    		break;
				    }else {
				    		render[(int) checkR][u] = " ";
				    }
					
					if(olvl[(int) checkR+1][u] instanceof Wall) {
			    			render[(int) checkR+1][u] = olvl[(int) checkR][u].toString();
			    			olvl[(int) checkR][u].makeDiscovered();
			    			break;
					}else {
						render[(int) checkR+1][u] = " ";
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
				    if(olvl[(int) checkR][u] instanceof Wall) {
				    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();
				    		olvl[(int) checkR][u].makeDiscovered();
				    		break;
				    }else {
				    		render[(int) checkR][u] = " ";
				    }
				 //else check the boxes to left and right
				}else {
					
					if(olvl[(int) checkR][u] instanceof Wall) {
				    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();
				    		olvl[(int) checkR][u].makeDiscovered();
				    		break;
				    }else {
				    		render[(int) checkR][u] = " ";
				    }
					
					if(olvl[(int) checkR+1][u] instanceof Wall) {
			    			render[(int) checkR+1][u] = olvl[(int) checkR+1][u].toString();
			    			olvl[(int) checkR][u].makeDiscovered();
			    			break;
					}else {
						render[(int) checkR+1][u] = " ";
					}
				}
			}
		}
		return render;
	}

	public static String[][] updateBottomSteep(String[][] render, double slope){
		double checkC;
		for(int u = p.getR(); u < olvl.length; u++ ) {
			checkC = p.getC()-((p.getR()-u)/slope);
			//print("slope: "+ slope);
			//if checkC is an integer:
			if ((checkC == Math.floor(checkC)) && !Double.isInfinite(checkC)) {
			    if(olvl[u][(int) checkC] instanceof Wall) {
			    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
			    		olvl[u][(int) checkC].makeDiscovered();
			    		break;
			    }else {
			    		render[u][(int) checkC] = " ";
			    }
			 //else check the boxes to left and right
			}else {
				
				if(olvl[u][(int) checkC] instanceof Wall) {
			    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
			    		olvl[u][(int) checkC].makeDiscovered();
			    		break;
			    }else {
			    		render[u][(int) checkC] = " ";
			    }
				
				if(olvl[u][(int) checkC+1] instanceof Wall) {
		    			render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
		    			olvl[u][(int) checkC].makeDiscovered();
		    			break;
				}else {
					render[u][(int) checkC+1] = " ";
				}
			}
		}
		return render;
	}
	
	public static String[][] updateBottomShallow(String[][] render, double slope){
		double checkR;
		
		for(int u = p.getC(); u >= 0; u-- ) {
			checkR = p.getR()- (p.getC()-u)*slope;
			
			//print("u: "+ u + " slope: "+slope + " player r, c" + p.getR() + p.getC());
			
			//if checkC is an integer:
			if ((checkR == Math.floor(checkR)) && !Double.isInfinite(checkR)) {
			    if(olvl[(int) checkR][u] instanceof Wall) {
			    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }else {
			    		render[(int) checkR][u] = " ";
			    }
			 //else check the boxes to left and right
			}else {
				
				if(olvl[(int) checkR][u] instanceof Wall) {
			    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }else {
			    		render[(int) checkR][u] = " ";
			    }
				
				if(olvl[(int) checkR+1][u] instanceof Wall) {
		    			render[(int) checkR+1][u] = olvl[(int) checkR+1][u].toString();
		    			olvl[(int) checkR][u].makeDiscovered();
		    			break;
				}else {
					render[(int) checkR+1][u] = " ";
				}
			}
			
		}
		return render;
	}
	
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
				    if(olvl[u][(int) checkC] instanceof Wall) {
				    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }else {
				    		render[u][(int) checkC] = " ";
				    }
				 //else check the boxes to left and right
				}else {
					
					if(olvl[u][(int) checkC] instanceof Wall) {
				    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }else {
				    		render[u][(int) checkC] = " ";
				    }
					
					if(olvl[u][(int) checkC+1] instanceof Wall) {
			    			render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
			    			olvl[u][(int) checkC].makeDiscovered();
			    			break;
					}else {
						render[u][(int) checkC+1] = " ";
					}
				}
			}
		
		//for increasing row
		}else {
			for(int u = p.getC(); u < olvl.length; u++ ) {
				checkC = p.getC()-((p.getR()-u)/slope);
				//print("slop: "+ slope);
				//print("c/: "+checkC);
				//if checkC is an integer:
				if ((checkC == Math.floor(checkC)) && !Double.isInfinite(checkC)) {
				    if(olvl[u][(int) checkC] instanceof Wall) {
				    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }else {
				    		render[u][(int) checkC] = " ";
				    }
				 //else check the boxes to left and right
				}else {
					
					if(olvl[u][(int) checkC] instanceof Wall) {
				    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }else {
				    		render[u][(int) checkC] = " ";
				    }
					
					if(olvl[u][(int) checkC+1] instanceof Wall) {
			    			render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
			    			break;
					}else {
						render[u][(int) checkC+1] = " ";
					}
				}
			}
		}
		
		return render;
	}

	public static String[][] updateLeftShallow(String[][] render, double slope){

		double checkR;
		
		//go decreasing cols to find row
		for(int u = p.getC(); u >= 0; u--) {
			checkR = p.getR()- (p.getC()-u)*slope;
			//print("slopee: "+ slope);
			
			//if checkC is an integer:
			if ((checkR == Math.floor(checkR)) && !Double.isInfinite(checkR)) {
			    if(olvl[(int) checkR][u] instanceof Wall) {
			    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }else {
			    		render[(int) checkR][u] = " ";
			    }
			 //else check the boxes to left and right
			}else {
				
				if(olvl[(int) checkR][u] instanceof Wall) {
			    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }else {
			    		render[(int) checkR][u] = " ";
			    }
				
				if(olvl[(int) checkR+1][u] instanceof Wall) {
		    			render[(int) checkR+1][u] = olvl[(int) checkR+1][u].toString();
		    			olvl[(int) checkR][u].makeDiscovered();
		    			break;
				}else {
					render[(int) checkR+1][u] = " ";
				}
			}
		}
		return render;
	}
	
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
				    if(olvl[u][(int) checkC] instanceof Wall) {
				    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }else {
				    		render[u][(int) checkC] = " ";
				    }
				 //else check the boxes to left and right
				}else {
					
					if(olvl[u][(int) checkC] instanceof Wall) {
				    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }else {
				    		render[u][(int) checkC] = " ";
				    }
					
					if(olvl[u][(int) checkC+1] instanceof Wall) {
			    			render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
			    			olvl[u][(int) checkC+1].makeDiscovered();
			    			break;
					}else {
						render[u][(int) checkC+1] = " ";
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
				    if(olvl[u][(int) checkC] instanceof Wall) {
				    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }else {
				    		render[u][(int) checkC] = " ";
				    }
				 //else check the boxes to left and right
				}else {
					
					if(olvl[u][(int) checkC] instanceof Wall) {
				    		render[u][(int) checkC] = olvl[u][(int) checkC].toString();
				    		olvl[u][(int) checkC].makeDiscovered();
				    		break;
				    }else {
				    		render[u][(int) checkC] = " ";
				    }
					
					if(olvl[u][(int) checkC+1] instanceof Wall) {
			    			render[u][(int) checkC+1] = olvl[u][(int) checkC+1].toString();
			    			olvl[u][(int) checkC+1].makeDiscovered();
			    			break;
					}else {
						render[u][(int) checkC+1] = " ";
					}
				}
			}
		}
		
		return render;
	}
	
	public static String[][] updateRightShallow(String[][] render, double slope){
		double checkR;
		
		//go decreasing cols to find row
		for(int u = p.getC(); u < olvl[0].length; u++) {
			checkR = p.getR()- (p.getC()-u)*slope;
			//print("slopee: "+ slope);
			
			//if checkC is an integer:
			if ((checkR == Math.floor(checkR)) && !Double.isInfinite(checkR)) {
			    if(olvl[(int) checkR][u] instanceof Wall) {
			    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }else {
			    		render[(int) checkR][u] = " ";
			    }
			 //else check the boxes to left and right
			}else {
				
				if(olvl[(int) checkR][u] instanceof Wall) {
			    		render[(int) checkR][u] = olvl[(int) checkR][u].toString();
			    		olvl[(int) checkR][u].makeDiscovered();
			    		break;
			    }else {
			    		render[(int) checkR][u] = " ";
			    }
				
				if(olvl[(int) checkR+1][u] instanceof Wall) {
		    			render[(int) checkR+1][u] = olvl[(int) checkR+1][u].toString();
		    			olvl[(int) checkR+1][u].makeDiscovered();
		    			break;
				}else {
					render[(int) checkR+1][u] = " ";
				}
			}
		}
		return render;
	}
	
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
	
	public static void convertLevel() {
		int temp = 0;
		
		olvl = new Thing[lvl.length][lvl[0].length];
		for(int i = 0; i < lvl.length; i++) {
			for(int j = 0; j< lvl[0].length; j++) {
				temp = lvl[i][j];
				
				if(temp == 0) {
					olvl[i][j] = new Thing(i,j);
				}else if(temp == 2){
					olvl[i][j] = new BreakableWall(i,j);
				}else if(temp ==3){
					olvl[i][j] = new IntelFile(i,j);
				}else if(temp == 4){
					olvl[i][j] = new ExtractionPoint(i,j);
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
				}else if(temp instanceof BreakableWall) {
					System.out.print("◘");
				}else if(temp instanceof Wall) {
					System.out.print("■");
				}else{
					System.out.print(" ");
				}
				//System.out.print(olvl[i][j].toString());
				//•
				//!
				//◘
				
				
			}
			System.out.print("\n");
		}
	}
	
	
	
	
	public static void displayRender(String[][] render) {
		//int temp = 0;
		
		for(int i = 0; i < render.length; i++) {
			for(int j = 0; j< render[0].length; j++) {
				
				if(olvl[i][j].isDiscovered()) {
					System.out.print(olvl[i][j].toString());
				}else {
					System.out.print(render[i][j]);
				}
				
				//System.out.print(render[i][j]);
				
				
			}
			System.out.print("\n");
		}
		
	}

	public static void brief() {
		
		if(currentlvl==0) {
			dialouge("Hey Boss,/ Welocome to the base!/ We just finished the construction of the r&d and intel platforms.//"
					+ " Our intel team intercepted a tranmission from the soviets,= discussing their plans to build a fusion-based "
					+ "metal gear in Afganistan./ We need you to investigate.....");
		}else if(currentlvl == 1) {
			
		}else if(currentlvl == 2) {
			
		}
		
		
	}
	
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
		pause(2000);
	}
	
	public static void pause(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {e.printStackTrace();}
	}

	public static void breakWall(int r, int c) {
		olvl[r][c] = new Thing(r,c);
	}
}
