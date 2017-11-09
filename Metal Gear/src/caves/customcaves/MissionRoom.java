package caves.customcaves;

import explorer.ExplorerMain;

public class MissionRoom extends caves.CaveRoom{

	public MissionRoom(String description) {
		super(description);
		String message = "There's a mission to be done here, press 'e' to start.";
		setDescription(message);
	}
	
	public String validKeys() {
		return "wdsae";
	}

	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's' 'd' or 'e'.");
	}
	
	public void performAction (int direction) {
		if (direction==4) {
			beginMission();
		}
		else {
			System.err.println("Invalid input.");
		}
	}
	
	public void beginMission() {
		//TODO
	}
}
