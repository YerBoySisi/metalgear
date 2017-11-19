package caves.customcaves;

import explorer.ExplorerMain;
import explorer.tempMain;

public class MissionRoom extends caves.CaveRoom{

	public MissionRoom(String description) {
		super(description);
		String message = "There's a mission to be done here. Press 'b' for mission briefing. Press 'e' to start mission.";
		setDescription(message);
	}
	
	public String validKeys() {
		return "wdsaeb";
	}

	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's' 'd' or 'e'.");
	}
	
	public void performAction (int direction) {
		if (direction==4) {
			beginMission();
		}else if(direction==5) {
			tempMain.brief();
		}
		else {
			System.err.println("Invalid input.");
		}
	}
	
	public void beginMission() {
		//TODO
	}
}
