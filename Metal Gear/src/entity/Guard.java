package entity;

import caves.CaveRoom;

public class Guard implements Person {
	
	//fields relating to navigation
	private CaveRoom[][] floor;
	private int currentRow;
	private int currentCol;
	private CaveRoom currentRoom;
	private int[] path;
	private int direction;
	
	//fields relating to character
	private String name;
	private CaveRoom[][] fieldOfView;
	

	public Guard() {

		

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getName() {
		
		return name;
		
	}

	public void setPosition(int row, int col) {

		if(row >=0 && row < floor.length && col >= 0 &&
				col < floor[row].length && floor[row][col] instanceof CaveRoom) {
			//remove the npc from current room
			if(currentRoom != null) {
				currentRoom.leave();
			}
			currentRow = row;
			currentCol = col;
			currentRoom = (CaveRoom)floor[row][col];
		}

	}

	public void act() {

		

	}
	
	public void setPath() {
		
		
		
	}

}
