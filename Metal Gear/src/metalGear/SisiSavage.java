package metalGear;

import caveExplorer.CaveExplorer;
import caveExplorer.NPC;

public class SisiSavage extends NPC {
	
	//fields relating to character
		private boolean active;
		private String activeDescription;
		private String inactiveDescription;
	
	public SisiSavage() {
		
		this.activeDescription = "There's a Savage in the room. He might have something cool!";
		this.inactiveDescription = "It's the savage from earlier. Wait, how did you even get back here?";
		
	}
	
	public void interact() {
		CaveExplorer.print("What do you want? A challenge? Well, I don't got nothin for you right now. I'll tell you what though, " 
								+ "for all your troubles, I'll show you a secret.");
		String s = CaveExplorer.in.nextLine();
		while(!s.equalsIgnoreCase("show me") && !s.equalsIgnoreCase("bye")){
			CaveExplorer.print("C'mon, I don't got all day. Either go away or say 'show me'.");
			s = CaveExplorer.in.nextLine();
		}
		
		if(s.equalsIgnoreCase("bye")) {
			CaveExplorer.print("Peace.");
		}
		
		if(s.equalsIgnoreCase("show me")) {
			CaveExplorer.print("Say less, I gotcha.");
			CaveExplorer.currentRoom.leave();
			CaveExplorer.currentRoom = CaveExplorer.caves[0][4];
			CaveExplorer.currentRoom.enter();
			CaveExplorer.inventory.updateMap();
			System.err.print("You've teleported to another room!");
		}
		
		active = false;
	}

	public String getInactiveDescription() {
		
		return inactiveDescription;
		
	}

	public String getActiveDescription() {
		
		return activeDescription;
		
	}
	
	public void act() {}
	
}
