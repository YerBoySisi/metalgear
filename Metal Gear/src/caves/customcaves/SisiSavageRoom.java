package caves.customcaves;

import caves.NPCRoom;

public class SisiSavageRoom extends NPCRoom{
	
	public SisiSavageRoom(String description) {
		
		super(description);
		
	}
	
	public String getContents() {
		
		if(containsNPC() && getPresentNPC().isActive()) {
			return "S";
		}else 
			return super.getContents();
		}
	
}
