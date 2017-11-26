package entity;

public class Player extends Thing{

	private String name;
	private boolean pickedUpGuard;
	private boolean objectiveReached;
	private boolean isCaught = false;
	private int r;
	private int c;

	
	public Player(int r, int c) {
		
		super(r,c);
		
		this.r = r;
		this.c = c;

		//pickedUpguard = true;
		
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


	public boolean pickedUpGuard() {
		return pickedUpGuard;
	}

	public void pickUpGuard(boolean pickedUpGuard) {
		this.pickedUpGuard = pickedUpGuard;
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

	/**
	 * Pass in every Guard in the current level every turn (use a for loop), end game when true
	 * @param g
	 * @return
	 */
	public boolean seenByGuard(Guard g) {
		
		for(int i = 0; i < g.getFieldOfView().length; i++) {
			
			if(g.getFieldOfView()[i][0] == this.r && g.getFieldOfView()[i][1] == this.c) {
				isCaught = true;
			}
			
		}
		
		return isCaught;

	}
	
	public String toString() {
		return "X";
	}

}
//