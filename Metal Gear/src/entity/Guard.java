package entity;

import java.util.Arrays;

import explorer.tempMain;

public class Guard extends Thing {
	
	//constants
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	public static final int[] directions = {-1, 1, 1, -1};
	
	//fields relating to navigation
	private int currentRow;
	private int currentCol;
	private int[][] path;
	private int direction;
	private int currentPos;
	
	//fields relating to character
	private int[][] fieldOfView;
	private boolean active;
	private boolean alerted;
	

	public Guard(int[][] path, int row, int col) {
		
		super(row, col);
		this.currentRow = row;
		this.currentCol = col;
		this.path = new int[path.length][2];
		
		for(int i = 0; i < path.length; i++) {
			
			currentRow += path[i][0];
			currentCol += path[i][1];
			this.path[i][0] = currentRow;
			this.path[i][1] = currentCol;
			
		}
		
		this.currentPos = path.length - 1;
		setDirection();
		setFieldOfView();
		this.active = true;

	}

	public void act() {
		
		if(!alerted) {
	
			if(active) {
				System.out.println(Arrays.deepToString(path));
				System.out.println(Arrays.deepToString(fieldOfView));
				System.out.println(direction);
				System.out.println(currentRow);
				System.out.println(currentCol);
				move(currentPos);
				setDirection();
				setFieldOfView();
				currentPos++;
				
				if(currentPos == path.length) {
					currentPos = 0;
				}
				
			}
		
		} else {
			
		}

	}

	public void setPosition(int row, int col) {
		
		currentRow = row;
		currentCol = col;
		
	}
	
	public void move(int pos) {
		
		setPosition(path[pos][0], path[pos][1]);
		
	}
	
	public void setPath(int[][] path) {
		
		this.path = path;
		
	}
	
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
	
	public void setFieldOfView() {
		
		fieldOfView = new int[4][2];
		
		if(direction == NORTH || direction == SOUTH) {
			
			fieldOfView[0][0] = currentRow + directions[direction];
			fieldOfView[1][0] = fieldOfView[0][0] + directions[direction];
			fieldOfView[2][0] = fieldOfView[1][0];
			fieldOfView[3][0] = fieldOfView[1][0];
			fieldOfView[0][1] = currentCol;
			fieldOfView[1][1] = currentCol;
			fieldOfView[2][1] = currentCol + 1;
			fieldOfView[3][1] = currentCol - 1;
			
		
		}
		
		if(direction == EAST || direction == WEST) {
			
			fieldOfView[0][1] = currentCol + directions[direction];
			fieldOfView[1][1] = fieldOfView[0][1] + directions[direction];
			fieldOfView[2][1] = fieldOfView[1][1];
			fieldOfView[3][1] = fieldOfView[1][1];
			fieldOfView[0][0] = currentRow;
			fieldOfView[1][0] = currentRow;
			fieldOfView[2][0] = currentRow + 1;
			fieldOfView[3][0] = currentRow - 1;
		
		}
		
		for(int i = 0; i < fieldOfView.length; i++) {
			
			if(fieldOfView[i][0] < 0 || fieldOfView[i][1] < 0 || tempMain.olvl[fieldOfView[i][0]][fieldOfView[i][1]] instanceof Wall) {
				fieldOfView[i][0] = currentRow;
				fieldOfView[i][1] = currentCol;
			}
			
		}
		
	}
	
	public int[][] getFieldOfView() {
		
		return fieldOfView;
		
	}
	
	public int getRow() {
		
		return currentRow;
		
	}
	
	public int getColumn() {
		
		return currentCol;
		
	}
	
	public int getDirection() {
		
		return direction;
		
	}
	
	public int[] getPosition() {
		
		return path[currentPos];
		
			
	}
	
	public int[] getNextPosition() {
		
		if(currentPos == path.length - 1) {
			return path[0];
		}
		
		return path[currentPos + 1];
			
	}
	
	public void alert() {
		
		alerted = true;
		
	}
	
	public boolean isAlerted() {
		
		return alerted;
		
	}

}