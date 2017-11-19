package explorer;

public class Wall extends Thing{
	//boolean hasBeenDiscovered;
	
	public Wall(int x, int y) {
		super(x, y);
		//hasBeenDiscovered = false;
		// TODO Auto-generated constructor stub
	}

	
	public void interact() {
		//interacting with a wall does nothing: leave blank
		
	}
	
	
	public void makeDiscovered() {
		hasBeenDiscovered = true;
	}
	
}
