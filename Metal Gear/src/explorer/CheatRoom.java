package explorer;

import metalGear.BenTempMain;

public class CheatRoom extends CaveRoom{

	public CheatRoom(String description) {
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
			BenTempMain.dialouge("What's do you need snake");
			String input = CaveExplorer.in.nextLine();
			if(input.equals("Ben is a god")) {
				CaveExplorer.currentlvl++;
				BenTempMain.dialouge("Can't disagree");
			}else {
				BenTempMain.dialouge("Don't waste my time");
			}
		}else {
			System.err.println("Invalid input.");
		}
	}
}
