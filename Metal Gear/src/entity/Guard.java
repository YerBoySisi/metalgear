package entity;

import java.util.Arrays;

import caves.CaveRoom;
import caves.NPCRoom;
import explorer.ExplorerMain;

public class Guard extends NPC implements Person {
	
	//fields relating to navigation
	private CaveRoom[][] floor;
	private int currentRow;
	private int currentCol;
	private CaveRoom currentRoom;
	private int[][] path;
	private int direction;
	
	//fields relating to character
	private String name;
	private CaveRoom[][] fieldOfView;
	private boolean active;
	

	public Guard(String name, int[][] path) {

		super(name);
		
		this.path = new int[path.length][2];
		
		for(int i = 0; i < path.length; i++) {
			
			currentRow += path[i][0];
			currentCol += path[i][1];
			this.path[i][0] = currentRow;
			this.path[i][1] = currentCol;
			
		}
		
		this.currentRoom = null;
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
			move(1);
			
		}

	}
	
	public void move(int pos) {
		
		setPosition(path[pos][0], path[pos][1]);
		
	}
	
	public void setPath() {
		
		
		
	}

}
