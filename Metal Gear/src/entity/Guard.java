package entity;

import java.util.Arrays;

import caves.CaveRoom;
import caves.NPCRoom;
import explorer.ExplorerMain;

public class Guard extends NPC implements Person {
	
	//constants
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	public static final int[] directions = {-1, -1, 1, 1};
	
	//fields relating to navigation
	private int currentRow;
	private int currentCol;
	private int[][] path;
	private int direction;
	private int currentPos = 0;
	
	//fields relating to character
	private String name;
	private int[][] fieldOfView;
	private boolean active;
	

	public Guard(String name, int[][] path, int row, int col) {

		super(name);
		
		this.currentRow = row;
		this.currentCol = col;
		this.path = new int[path.length][2];
		
		for(int i = 0; i < path.length; i++) {
			
			currentRow += path[i][0];
			currentCol += path[i][1];
			this.path[i][0] = currentRow;
			this.path[i][1] = currentCol;
			
		}
		
		this.active = true;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getName() {
		
		return name;
		
	}

	public void act() {
		
		if(active) {
			System.out.println(Arrays.deepToString(path));
			move(currentPos);
			setFieldOfView();
			currentPos++;
			
			if(currentPos == path.length) {
				currentPos = 0;
			}
			
		}

	}
	
	public void move(int pos) {
		
		setPosition(path[pos][0], path[pos][1]);
		
	}
	
	public void setPath(int[][] path) {
		
		this.path = path;
		
	}
	
	public void setDirection() {
		
		if(path[currentPos][0] < path[currentPos - 1][0]) {
			direction = NORTH;
		} else if(path[currentPos][0] > path[currentPos - 1][0]) {
			direction = SOUTH;
		} else if(path[currentPos][0] == path[currentPos - 1][0]) {
			direction = (int)(Math.random() * 4);
		}
		
		if(path[currentPos][1] < path[currentPos - 1][1]) {
			direction = WEST;
		} else if(path[currentPos][1] > path[currentPos - 1][1]) {
			direction = EAST;
		} else if(path[currentPos][1] == path[currentPos - 1][1]) {
			direction = (int)(Math.random() * 4);
		} 
		
	}
	
	public void setFieldOfView() {
		
		fieldOfView = new int[6][2];
		
		for(int i = 0; i < fieldOfView.length; i++) {
			fieldOfView[i][0] = path[i][0] + directions[direction];
		}
		
		
	}

}
