package metalGear;

import caveExplorer.CaveRoom;

public class SavageRoom extends CaveRoom{
	
	private String message;
	
	public SavageRoom(String description) {
		super(description);
		message = "What's up, it's yer boy Sisi.";
		setDescription(message);
	}
	
}
