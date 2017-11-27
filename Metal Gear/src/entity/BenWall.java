package entity;

public class BenWall extends BenThing{

	public BenWall(int r, int c) {
		super(r, c);
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
