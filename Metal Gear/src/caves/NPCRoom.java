package caves;

import entity.NPC;
import explorer.ExplorerMain;

public class NPCRoom extends CaveRoom {

	private NPC presentNPC;
	
	public NPCRoom(String description) {
		super(description);
		setPresentNPC(null);
	}
	
	/**
	 * NPCs can enter a room if no other NPC is there
	 * @return
	 */
	public boolean canEnter() {
		return getPresentNPC() == null;
	}
	
	public void enterNPC(NPC m) {
		setPresentNPC(m);
	}
	
	public void leaveNPC() {
		setPresentNPC(null);
	}
	
	/**
	 * there is already a method like this, but to me it is helpful
	 * to have this other way of referring to it, ESPECIALLY
	 * if I decide to change the rules of "canEnter"
	 * @return
	 */
	public boolean containsNPC() {
		return getPresentNPC() != null;
	}
	
	//The above methods are NEW features to a CaveRoom,
	//the methods below REPLACE CaveRoom methods (override)
	
	public String validKeys() {
		return "wdsae";
	}
	
	public void printAllowedEntry() {
		ExplorerMain.print("You can only enter 'w', 'a', 's' or 'd' to move or"
				+ " you can type 'e' to interact.");
	}
	
	
	public void performAction(int direction) {
		if(direction == 4) {
			if(containsNPC() && getPresentNPC().isActive()) {
				getPresentNPC().interact();
			}else {
				ExplorerMain.print("There is nothing to interact with.");
			}
		}else {
			ExplorerMain.print("That key does nothing.");
		}
	}
	
	public String getContents() {
		if(containsNPC() && getPresentNPC().isActive()) {
			return "M";
		}else {
			//return what would be returned otherwise
			return super.getContents();
		}
	}
	
	public String getDescription() {
		if(containsNPC() && !getPresentNPC().isActive()) {
			return super.getDescription() +"\n"+getPresentNPC().getInactiveDescription();
		}else {
			String npcDesc = "";
			if(getPresentNPC() != null) {
				npcDesc = getPresentNPC().getActiveDescription();
			}
			return super.getDescription() + "\n"+npcDesc;
		}
	}

	public NPC getPresentNPC() {
		return presentNPC;
	}

	public void setPresentNPC(NPC presentNPC) {
		this.presentNPC = presentNPC;
	}
	
	
	
	
	
	
}
