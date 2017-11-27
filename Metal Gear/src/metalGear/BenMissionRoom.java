package metalGear;

import explorer.CaveRoom;

public class BenMissionRoom extends CaveRoom{

	public BenMissionRoom(String description) {
		super(description);
	}
	
	public String validKeys() {
		return "wdsae";
	}

	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's' 'd', or 'e'.");
	}
	
	public void performAction (int direction) {
		if (direction==4) {
			beginMission();
		}else {
			System.err.println("Invalid input.");
		}
	}
	
	public void beginMission() {
		//TODO
	}
}
