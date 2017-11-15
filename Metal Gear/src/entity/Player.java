package entity;

public class Player extends explorer.Thing{

	private String name;
	private boolean pickedUpguard;
	private boolean objectiveReached;
	private int r;
	private int c;
	
	public Player(int r, int c, String name) {
		super(r,c);
		
		this.r = r;
		this.c = c;
		
		
		
		setName(name);
	}
	
	public void playerMove(int r, int c) {
		this.r = r;
		this.c = c;
		
		explorer.tempMain.olvl[r][c] = this;
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
