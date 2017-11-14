package caves.customcaves;

import explorer.ExplorerMain;

public class StoryRoom extends caves.CaveRoom{

	public StoryRoom(String description) {
		super(description);
		String message = "There's a note here, press 'e' to take a look.";
		setDescription(message);
	}
}
