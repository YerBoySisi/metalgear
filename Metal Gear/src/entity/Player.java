package entity;

public class Player extends Thing{

	private String name;
	private boolean pickedUpguard;
	private boolean objectiveReached;
	private boolean isCaught = false;
	private int r;
	private int c;

	
	public Player(int r, int c) {
		
		super(r,c);
		
		this.r = r;
		this.c = c;

		pickedUpguard = true;
		
		setName(name);
	}
	
	public void rayMove(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public void playerMove(int r, int c) {
		this.r = r;
		this.c = c;
		
		//explorer.tempMain.updateOlvlPlayer();
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


	public boolean isPickedUpGuard() {
		return pickedUpguard;
	}

	public void setPickedUpGuard(boolean pickedUpguard) {
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

	// pass in the field of view, guard row, and guard column coordinates
	public boolean seenByGuard (int[][] fov, int gRow, int gCol) {
		for (int row = fov[0][0]; row < fov.length + fov [0][0]; row++) {
			for (int col = fov[row][0]; col < fov[row].length + fov[row][0]; col++) {
				if (r == row && c == col) {
					isCaught = true;
					return isCaught;
				}
			}
		}
		return isCaught;
	}
	
	public String toString() {
		return "D";
	}

}
//