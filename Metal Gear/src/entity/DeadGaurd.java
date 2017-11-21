package entity;

import explorer.tempMain;

public class DeadGaurd extends Thing{

	public DeadGaurd(int r, int c) {
		super(r, c);
	}
	
	
	public void interact() {
		//check to see if player already has a dead body
		if(tempMain.p.isPickedUpGuard()) {
			tempMain.dialouge("Snake, you can't pick up 2 dead gaurds!");
		}else {
			tempMain.breakWall(this.r,this.c);//olvl[this.r][this.c] = new Thing(this.r,this.c);
			this.r = -1;
			this.c = -1;
			tempMain.p.setPickedUpGuard(true);
		}
	}
	
	public String toString() {
		return "G";
	}
	
	public void putDownCamera(int r, int c){
		
		this.r = r;
		this.c = c;
		
		tempMain.olvl[this.r][this.c] = this;
	}
}
