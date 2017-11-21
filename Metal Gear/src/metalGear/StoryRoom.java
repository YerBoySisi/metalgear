package metalGear;

import explorer.tempMain;

public class StoryRoom extends CaveRoom{

	public StoryRoom(String description) {
		super(description);
	}
	public String validKeys() {
		return "wdsae";
	}

	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's' 'd', or 'e'.");
	}
	public void performAction (int direction) {
		tempMain.print(""+direction);
		if (direction==4) {
			tempMain.brief();
			tempMain.print("asdasd");
		}else {
			System.err.println("Invalid input.");
		}
	}
//	/tempMain.brief();
}
