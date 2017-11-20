package explorer;

import entity.Wall;

public class ExtractionPoint extends Wall{

	public ExtractionPoint(int r, int c) {
		super(r, c);
		// TODO Auto-generated constructor stub
	}
	
	public void interact() {
		if(tempMain.p.isObjectiveReached()) {
			tempMain.playing = false;
			tempMain.currentlvl++;
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
