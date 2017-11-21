package entity;

public class Wall extends Thing{
	//boolean hasBeenDiscovered;
	
	public Wall(int r, int c) {
		super(r, c);
		//hasBeenDiscovered = false;
		// TODO Auto-generated constructor stub
	}

	
	public void interact() {
		//interacting with a wall does nothing: leave blank
		
	}
	
	
	public void makeDiscovered() {
		hasBeenDiscovered = true;
	}
	
	public String toString() {
		return "■";
	}
	
}
