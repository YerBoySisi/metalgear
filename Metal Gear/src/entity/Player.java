package entity;

public class Player extends explorer.Thing{

	private boolean pickedUpGuard;
	private boolean objectiveReached;
	private int r;
	private int c;

	
	public Player(int r, int c) {
		
		super(r,c);
		
		this.r = r;
		this.c = c;

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
	
	



	public void setPosition(int row, int col) {
		this.setC(col);
		this.setR(row);
		
	}


	public boolean isPickedUpGuard() {
		return pickedUpGuard;
	}

	public void setPickedUpGuard(boolean pickedUpGuard) {
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

	

}
