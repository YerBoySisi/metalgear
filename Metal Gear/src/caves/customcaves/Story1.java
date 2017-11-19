package caves.customcaves;

import explorer.ExplorerMain;

public class Story1 extends StoryRoom {
	
	//DIALOGUE PATHS
	//start = 0,0
	//	"I'm already aware." -> 1,0
	//		END
	//  "Yes, sir." -> 1,1
	//		"Yes, let me start now." -> 2,0
	//			END
	//		"Why are we doing this?" -> 2,1
	private String[][] dialogue = {{"Morning, soldier. Want to hear about your mission?"},
						   		  {"I see. Proceed","Your task is simple - obtain the intel file without being spotted. \nThe guards' lines of sight are pathetic, so this should be a breeze for you. Good luck."},
						   		  {"Go on ahead","That business simply doesnt't concern you, if I were you I'd get a move on."}
								  };
	private String validkeys = "wdsae";
	private String input = null;
	private String temp = "";
	private boolean inDialogue = false;
	private int position = 0;
	
	public Story1(String description) {
		super(description);
	}

	public String validKeys() {
		return validkeys;
	}
	

	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's' 'd' or 'e', or an option choice if you're talking to someone.");
	}
	
	public void performAction(int direction) {
		if (direction==4) {
			inDialogue = true;
			while(inDialogue) {
				talk();
			}
		}
	}

	private void talk() {
		temp = "";
		if (input == null) {
			for (int i = 0; i < dialogue[position].length; i++) {
				//ask how to change validKeys
			}
			setDescription(dialogue[position][0]);
		} else {
			input = ExplorerMain.in.nextLine();
			
		}
	}
}
