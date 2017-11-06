package metalGear;

import caveExplorer.CaveExplorer;
//import caveExplorer.NPC;

public class BensCafe extends caveExplorer.CaveRoom{

	private String msg = "Welcome to Bens Cafe";
	private String profit;
	
	public BensCafe(String description) {
		super(description);
		setDescription(description + msg);
		
		//NPC barista = new NPC();
		//barista.setActiveDesc("Hi. Welcome to Bens Cafe. What can I get you");
		
		//setPresentNPC(barista);
	}
	
	public String validKeys() {
		return "wdsae";
	}
	
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', 'e', or 'd'.");
	}
	
	public void performAction(int direction) {
		System.out.println("The Barista Gives you a cup of Joe");
	}
}
