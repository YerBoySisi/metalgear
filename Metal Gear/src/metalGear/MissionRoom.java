package metalGear;


public class MissionRoom extends CaveRoom{

	public MissionRoom(String description) {
		super(description);
	}
	
	public String validKeys() {
		return "wdsaeb";
	}

	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's' 'd', 'e', or 'b'.");
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
