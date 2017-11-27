package explorer;

import entity.Wall;
import metalGear.CaveExplorer;

public class ExtractionPoint extends Wall{

	public ExtractionPoint(int r, int c) {
		super(r, c);
		this.makeDiscovered();
	}
	
	/** Acts as a standard wall until objective is reached**/
	public void interact() {
		if(tempMain.p.isObjectiveReached()) {
			tempMain.playing = false;
			CaveExplorer.currentlvl++;
			tempMain.dialouge("Mission Complete/ Returning to Mother Base");
		}
	}
	
	public String toString() {
		if(tempMain.p.isObjectiveReached()) {
			return "«";
		}else {
			return "■";
		}
	}

}
