package entity;

public class Player {

	private String name;
	private boolean pickedUpguard;
	private boolean objectiveReached;
	private int r;
	private int c;
	
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
		this.setC(col);
		this.setR(row);
		
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

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

}
