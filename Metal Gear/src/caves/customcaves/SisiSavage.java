package caves.customcaves;

import entity.NPC;
import explorer.ExplorerMain;

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
		ExplorerMain.print("What do you want? A challenge? Well, I don't got nothin for you right now. I'll tell you what though, " 
								+ "for all your troubles, I'll show you a secret.");
		String s = ExplorerMain.in.nextLine();
		while(!s.equalsIgnoreCase("show me") && !s.equalsIgnoreCase("bye")){
			ExplorerMain.print("C'mon, I don't got all day. Either go away or say 'show me'.");
			s = ExplorerMain.in.nextLine();
		}
		
		if(s.equalsIgnoreCase("bye")) {
			ExplorerMain.print("Peace.");
		}
		
		if(s.equalsIgnoreCase("show me")) {
			ExplorerMain.print("Say less, I gotcha.");
			ExplorerMain.currentRoom.leave();
			ExplorerMain.currentRoom = ExplorerMain.caves[0][4];
			ExplorerMain.currentRoom.enter();
			ExplorerMain.inventory.updateMap();
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
