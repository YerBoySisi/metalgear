package metalGear;


public class MissionRoom extends CaveRoom{

	public MissionRoom(String description) {
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
