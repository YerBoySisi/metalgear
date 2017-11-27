package metalGear;

import explorer.CaveRoom;

public class SidStoryRoom extends CaveRoom{

	public SidStoryRoom(String description) {
		super(description);
	}
	public String validKeys() {
		return "wdsae";
	}

	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's' 'd', or 'e'.");
	}
	
	/** briefs player**/
	public void performAction (int direction) {
		BenTempMain.print(""+direction);
		if (direction==4) {
			BenTempMain.brief();
		}else {
			System.err.println("Invalid input.");
		}
	}

}
