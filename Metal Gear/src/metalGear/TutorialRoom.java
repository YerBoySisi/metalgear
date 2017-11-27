package metalGear;

import explorer.tempMain;

public class TutorialRoom extends CaveRoom {

	public TutorialRoom(String description) {
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
		tempMain.print(""+direction);
		if (direction==4) {
			tempMain.dialouge("Snake... seems you need a refresher on things.../"
					+ "You can walk around each level by using the wasd keys.../="
					+ "There will be guards (▲) in every level/"
					+ "You can kill them by walking up to them while you are behind them/"
					+ "Make sure you dont get spotted though or else its GAME OVER.../="
					+ "Dead guards look like 'D'/"
					+ "You can pick up by walking into them"
					+ "Put down a picked up gaurd with 'g'...="
					+ "Cameras allow you to see more of a level./"
					+ "Use 'c' to place a camera./="
					+ "Now lets get you back out there...");
		}else {
			System.err.println("Invalid input.");
		}
	}

}
