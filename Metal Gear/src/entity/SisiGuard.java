package entity;

import metalGear.BenTempMain;

public class SisiGuard extends BenThing {
	
	//constants
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	public static final int[] DIRECTIONS = {-1, 1, 1, -1}; //-1 means up or left; 1 means right or down
	public static final String[] ICONS = {"▲", "►", "▼", "◄"}; //Guard front-end icons
	
	//fields relating to navigation
	public int currentRow;
	public int currentCol;
	private int[][] path; //Every coordinate in which the Guard will go to is stored here
	private int direction;
	private int currentPos; //The current index of path
	
	//fields relating to character
	private int[][] fieldOfView;
	private boolean isAlive; //When false, the Guard doesn't do anything and can be picked up and put down by Player
	private boolean active; //When false, the Guard doesn't move
	private boolean alerted; //When true, the Guard's FOV increases
	
	/**
	 * Constructor
	 * NOTE: The field path and the parameter path are different
	 * The field stores every coordinate in which the Guard with go to
	 * The parameter path is used to make this.path, and stores the directions for the Guard relative to starting position
	 * E.g. this.path would be {{3,3},{3,2}} while the parameter path would pass in {{0,-1},{0,1}}
	 * @param path
	 * @param row
	 * @param col
	 */
	public SisiGuard(int[][] path, int row, int col) {
		
		super(row, col);
		this.currentRow = row;
		this.currentCol = col;
		this.path = new int[path.length][2];
		
		//Creates Guard's path from instructions given when instantiated
		for(int i = 0; i < path.length; i++) {
			
			currentRow += path[i][0];
			currentCol += path[i][1];
			this.path[i][0] = currentRow;
			this.path[i][1] = currentCol;
			
		}
		
		this.currentPos = path.length - 1;
		setDirection();
		this.isAlive = true;

	}
	
	/**
	 * To be used in conjunction with the Player class
	 * Kills the Guard when it is alive
	 * Picks up the Guard when it is dead
	 */
	public void interact() {
		
		if(isAlive) {
			BenTempMain.print("The guard has been killed");
			this.fieldOfView = new int[0][0];
			kill();
		} else {
			
			if(BenTempMain.p.pickedUpGuard()) {
				BenTempMain.dialouge("Snake, you can't pick up 2 dead gaurds!");
			} else {
				BenTempMain.breakWall(this.currentRow,this.currentCol);
				//tempMain.print("picking up guard");
				this.currentRow = -1;
				this.currentCol = -1;
				BenTempMain.p.pickUpGuard(true);
				BenTempMain.p.setCurrentGuard(this);
			}
			
		}
		
	}
	
	/**
	 * Updates Guard's visuals on the map
	 * Moves the Guard, sets its coordinates, sets its FOV
	 * Updates instructions for Guard path
	 */
	public void act() {
		
		//Remove Guard from level map
		BenTempMain.breakWall(currentRow,currentCol);
		
		if(isAlive) {
	
			if(active) {
				move(currentPos);
				setDirection();
				currentPos++; //Sets currentPos to the next position of the path
				
				//If at the last position of the path, reset back to the first one
				if(currentPos == path.length) {
					currentPos = 0;
				}
				
			}
			
			//If it's active or inactive, set it to the opposite (effectively makes Guard move every OTHER turn)
			active = !active; 
		
		}
		
		//Place Guard in level map
		BenTempMain.olvl[currentRow][currentCol] = this;

	}
	
	/**
	 * Sets the Guard's coordinates
	 * @param row
	 * @param col
	 */
	public void setPosition(int row, int col) {
		
		currentRow = row;
		currentCol = col;
		
	}
	
	/**
	 * Sets the Guard's coordinates to the next coordinate pair in path
	 * @param pos
	 */
	public void move(int pos) {
		
		setPosition(path[pos][0], path[pos][1]);
		
	}
	
	/**
	 * Determines which way the guard is facing
	 */
	public void setDirection() {
		
		if(currentPos > 0) {
			
			if(currentRow < path[currentPos - 1][0]) {
				direction = NORTH;
			} else if(currentRow > path[currentPos - 1][0]) {
				direction = SOUTH;
			} else if(currentRow == path[currentPos - 1][0]) {
				direction = (int)(Math.random() * 4);
			} else if(currentCol < path[currentPos - 1][1]) {
				direction = WEST;
			} else if(currentCol > path[currentPos - 1][1]) {
				direction = EAST;
			} else if(currentCol == path[currentPos - 1][1]) {
				direction = (int)(Math.random() * 4);
			}
		
		} else {
			
			if(path[currentPos][0] > path[currentPos + 1][0]) {
				direction = NORTH;
			} else if(path[currentPos][0] < path[currentPos + 1][0]) {
				direction = SOUTH;
			} else if(path[currentPos][0] == path[currentPos + 1][0]) {
				direction = (int)(Math.random() * 4);
			} else if(path[currentPos][1] > path[currentPos + 1][1]) {
				direction = WEST;
			} else if(path[currentPos][1] < path[currentPos + 1][1]) {
				direction = EAST;
			} else if(path[currentPos][1] == path[currentPos + 1][1]) {
				direction = (int)(Math.random() * 4);
			}
			
		}
		
	}
	
	/**
	 * Sets the Guard's field of view based on direction faced
	 * The FOV of a Guard is always its current coordinates, 1 space forward, 2 spaces forward, and 2 spaces forward to the left/right
	 * When alerted, FOV of the Guard becomes the above coordinates AND the same thing behind the Guard
	 */
	public void setFieldOfView() {
		
		if(!alerted) {
			
			fieldOfView = new int[5][2];
			
			if(direction == NORTH || direction == SOUTH) {
				
				fieldOfView[0][0] = currentRow + DIRECTIONS[direction];
				fieldOfView[1][0] = fieldOfView[0][0] + DIRECTIONS[direction];
				fieldOfView[2][0] = fieldOfView[1][0];
				fieldOfView[3][0] = fieldOfView[1][0];
				fieldOfView[0][1] = currentCol;
				fieldOfView[1][1] = currentCol;
				fieldOfView[2][1] = currentCol + 1;
				fieldOfView[3][1] = currentCol - 1;
				
			
			}
			
			if(direction == EAST || direction == WEST) {
				
				fieldOfView[0][1] = currentCol + DIRECTIONS[direction];
				fieldOfView[1][1] = fieldOfView[0][1] + DIRECTIONS[direction];
				fieldOfView[2][1] = fieldOfView[1][1];
				fieldOfView[3][1] = fieldOfView[1][1];
				fieldOfView[0][0] = currentRow;
				fieldOfView[1][0] = currentRow;
				fieldOfView[2][0] = currentRow + 1;
				fieldOfView[3][0] = currentRow - 1;
			
			}
			
			fieldOfView[4][0] = currentRow;
			fieldOfView[4][1] = currentCol;
	
		} else {
			
			fieldOfView = new int[9][2];
			
			if(direction == NORTH || direction == SOUTH) {
				
				fieldOfView[0][0] = currentRow + DIRECTIONS[NORTH];
				fieldOfView[1][0] = fieldOfView[0][0] + DIRECTIONS[NORTH];
				fieldOfView[2][0] = fieldOfView[1][0];
				fieldOfView[3][0] = fieldOfView[1][0];
				fieldOfView[0][1] = currentCol;
				fieldOfView[1][1] = currentCol;
				fieldOfView[2][1] = currentCol + 1;
				fieldOfView[3][1] = currentCol - 1;
				
				fieldOfView[4][0] = currentRow + DIRECTIONS[SOUTH];
				fieldOfView[5][0] = fieldOfView[4][0] + DIRECTIONS[SOUTH];
				fieldOfView[6][0] = fieldOfView[5][0];
				fieldOfView[7][0] = fieldOfView[5][0];
				fieldOfView[4][1] = currentCol;
				fieldOfView[5][1] = currentCol;
				fieldOfView[6][1] = currentCol + 1;
				fieldOfView[7][1] = currentCol - 1;
				
			
			}
			
			if(direction == EAST || direction == WEST) {
				
				fieldOfView[0][1] = currentCol + DIRECTIONS[EAST];
				fieldOfView[1][1] = fieldOfView[0][1] + DIRECTIONS[EAST];
				fieldOfView[2][1] = fieldOfView[1][1];
				fieldOfView[3][1] = fieldOfView[1][1];
				fieldOfView[0][0] = currentRow;
				fieldOfView[1][0] = currentRow;
				fieldOfView[2][0] = currentRow + 1;
				fieldOfView[3][0] = currentRow - 1;
				
				fieldOfView[4][1] = currentCol + DIRECTIONS[WEST];
				fieldOfView[5][1] = fieldOfView[4][1] + DIRECTIONS[WEST];
				fieldOfView[6][1] = fieldOfView[5][1];
				fieldOfView[7][1] = fieldOfView[6][1];
				fieldOfView[4][0] = currentRow;
				fieldOfView[5][0] = currentRow;
				fieldOfView[6][0] = currentRow + 1;
				fieldOfView[7][0] = currentRow - 1;
				
			
			}
			
			fieldOfView[8][0] = currentRow;
			fieldOfView[8][1] = currentCol;
			
		}
		
		if(facingWall()) {
			
			for(int i = 0; i < fieldOfView.length; i++) {
				
				fieldOfView[i][0] = currentRow;
				fieldOfView[i][1] = currentCol;
				
			}
			
		} else {
		
			for(int i = 0; i < fieldOfView.length; i++) {
				
				if(fieldOfView[i][0] < 0 || fieldOfView[i][1] < 0 || BenTempMain.olvl[fieldOfView[i][0]][fieldOfView[i][1]] instanceof BenWall) {
					fieldOfView[i][0] = currentRow;
					fieldOfView[i][1] = currentCol;
				}
				
			}
		
		}
		
	}
	
	/**
	 * Returns the Guard's FOV
	 * @return
	 */
	public int[][] getFieldOfView() {
		
		return fieldOfView;
		
	}
	
	/**
	 * Returns the Guard's current row
	 * @return
	 */
	public int getRow() {
		
		return currentRow;
		
	}
	
	/**
	 * Return's the Guard's current column
	 * @return
	 */
	public int getColumn() {
		
		return currentCol;
		
	}
	
	/**
	 * Returns the current direction in which the Guard is facing
	 * @return
	 */
	public int getDirection() {
		
		return direction;
		
	}
	
	/**
	 * Returns the current coordinates of the Guard
	 * @return
	 */
	public int[] getPosition() {
		
		return path[currentPos];
		
			
	}
	
	/**
	 * Returns the coordinates of the next position of the Guard
	 * @return
	 */
	public int[] getNextPosition() {
		
		if(currentPos == path.length - 1) {
			return path[0];
		}
		
		return path[currentPos + 1];
			
	}
	
	/**
	 * Sets alerted to true (effectively alerts the Guard)
	 */
	public void alert() {
		
		alerted = true;
		
	}
	
	/**
	 * Returns whether or not the Guard is alive
	 * @return
	 */
	public boolean isAlive() {
	
		return isAlive;
		
	}
	
	/**
	 * Sets isAlive to false (effectively kills the Guard)
	 */
	public void kill() {
		
		isAlive = false;
		
	}
	
	public boolean facingWall() {
		
		if(direction == NORTH || direction == SOUTH) {
			
			if(BenTempMain.olvl[currentRow + DIRECTIONS[direction]][currentCol] instanceof BenWall) {
				return true;
			}
		
		} else {
			
			if(BenTempMain.olvl[currentRow][currentCol + DIRECTIONS[direction]] instanceof BenWall) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * Pass in each Guard
	 * If a dead guard is within any Guard's FOV, this returns true
	 * When true, remove the guard from the map
	 * @param g
	 * @return
	 */
	public boolean seenByGuard(SisiGuard g) {
		
		for(int i = 0; i < g.getFieldOfView().length; i++) {
			
			if(g.getFieldOfView()[i][0] == this.r && g.getFieldOfView()[i][1] == this.c) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * Removes Guard from map
	 */
	public void remove() {
		
		BenTempMain.breakWall(this.r,this.c);
		this.r = -1;
		this.c = -1;
		
	}
	
	/**
	 * Sets the Guard's front-end visuals
	 * If alive, the Guard's icon is an arrow depicting the direction in which it is facing
	 * If dead, the Guard's icon is a 'G'
	 */
	public String toString() {
		
		if(isAlive) {
			return ICONS[direction];
		} else {
			return "D";
		}
		
	}

}