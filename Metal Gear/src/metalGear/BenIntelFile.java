package metalGear;

import entity.BenWall;

public class BenIntelFile extends BenWall{

	public BenIntelFile(int r, int c) {
		super(r, c);
	}
	
	//changes extraction point
	public void interact() {
		BenTempMain.p.setObjectiveReached(true);
		BenTempMain.breakWall(this.r, this.c);
		BenTempMain.dialouge("Nice job Boss./ Now get to the extration point.");
	}
	
	public String toString() {
		return "#";
	}

}
