package entity;

import metalGear.BenTempMain;

public class BenBreakableWall extends BenWall {

	public BenBreakableWall(int r, int c) {
		super(r, c);
	}
	
	

	public void interact() {
		BenTempMain.breakWall(this.r,this.c);
	}
	public void makeDiscovered() {
		hasBeenDiscovered = true;
	}
	
	public String toString() {
		return "◘";
	}

}
