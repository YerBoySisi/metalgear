package caves.customcaves;

import explorer.ExplorerMain;

public class StoryRoom extends caves.CaveRoom{

	public StoryRoom(String description) {
		super(description);
		String message = "There's someone here, press 'e' to engage in conversation.";
		setDescription(message);
	}
}
