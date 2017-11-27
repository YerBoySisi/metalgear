package metalGear;

import entity.BenWall;
import explorer.CaveExplorer;

public class BenExtractionPoint extends BenWall{

	public BenExtractionPoint(int r, int c) {
		super(r, c);
		this.makeDiscovered();
	}
	
	/** Acts as a standard wall until objective is reached**/
	public void interact() {
		if(BenTempMain.p.isObjectiveReached()) {
			BenTempMain.playing = false;
			CaveExplorer.currentlvl++;
			BenTempMain.dialouge("Mission Complete/ Returning to Mother Base");
		}
	}
	
	public String toString() {
		if(BenTempMain.p.isObjectiveReached()) {
			return "«";
		}else {
			return "■";
		}
	}

}
