package caveExplorer;

/**
 * NPC means Non-Playable Character
 * @author Teacher
 *
 */
public class NPC {
	
	//fields relating to navigation
	private CaveRoom[][] floor;//where the NPC roams
	private int currentRow;
	private int currentCol;
	private NPCRoom currentRoom;
	
	//fields relating to character
	private boolean active;
	private String activeDescription;
	private String inactiveDescription;
	

	//default constructor
	public NPC() {
		this.floor = CaveExplorer.caves;
		this.activeDescription = "There is a person waiting to talk to you.";
		this.inactiveDescription = "The person you spoke to earlier is standing here.";
		//to indicate the NPC doesn't have a position yet, use coordinates -1,-1
		this.currentCol = -1;
		this.currentRow = -1;
		this.currentRoom = null;
		this.active = true;
	}
	/**
	 * Note: you can make custom constructors later that use different parameters
	 * for example:
	 * public NPC(String description, String inactiveDescription)
	 *
	 */
	
	
	public void setActiveDesc(String s) {
		this.activeDescription = s;
	}

	public boolean isActive() {
		return active;
	}

	public void setposition(int row, int col) {
		if(row >=0 && row < floor.length && col >= 0 &&
				col < floor[row].length && floor[row][col] instanceof NPCRoom) {
			//remove the npc from current room
			if(currentRoom != null) {
				currentRoom.leaveNPC();
			}
			currentRow = row;
			currentCol = col;
			currentRoom = (NPCRoom)floor[row][col];
			currentRoom.enterNPC(this);
		}
	}
	
	public void interact() {
		CaveExplorer.print("Hello there traveller, I am an NPC. I don't have much to say, so go away.");
		String s = CaveExplorer.in.nextLine();
		while(!s.equalsIgnoreCase("bye")){
			CaveExplorer.print("...");
			s = CaveExplorer.in.nextLine();
		}
		CaveExplorer.print("Okay, bye.");
		active = false;
	}

	public String getInactiveDescription() {
		return inactiveDescription;
	}

	public String getActiveDescription() {
		return activeDescription;
	}
	public void act() {
		if(active) {
			int[] move = calculateMovement();
			int newRow = move[0];
			int newCol = move[1];
			setposition(newRow, newCol);
		}
	}
	
	
	public int[] calculateMovement() {
		int[] moves = new int[2];
		int[][] possibleMoves = {{-1,0},{0,1},{1,0},{0,-1}};
		int rand = (int)(Math.random()*possibleMoves.length);
		moves[0] = possibleMoves[rand][0]+currentRow;
		moves[1] = possibleMoves[rand][1]+currentCol;
		while(currentRoom.getDoor(rand) == null ||
				!(CaveExplorer.caves[moves[0]][moves[1]] instanceof NPCRoom)) {
			rand = (int)(Math.random()*possibleMoves.length);
			moves[0] = possibleMoves[rand][0]+currentRow;
			moves[1] = possibleMoves[rand][1]+currentCol;
		}
		return moves;
	}

}
