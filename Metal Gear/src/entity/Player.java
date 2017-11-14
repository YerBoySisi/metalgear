package entity;

public class Player {

	private String name;
	private boolean pickedUpguard;
	private boolean objectiveReached;
	private int currentRow;
	private int currentCol;
	
	public Player(String name) {
		setName(name);
	}
	
	public void setName(String name) {
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public void setPosition(int row, int col) {
		this.currentCol = col;
		this.currentRow = row;
		
	}

	public void act() {
		
		
	}

	public boolean isPickedUpguard() {
		return pickedUpguard;
	}

	public void setPickedUpguard(boolean pickedUpguard) {
		this.pickedUpguard = pickedUpguard;
	}

	public boolean isObjectiveReached() {
		return objectiveReached;
	}

	public void setObjectiveReached(boolean objectiveReached) {
		this.objectiveReached = objectiveReached;
	}

}
