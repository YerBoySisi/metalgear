package explorer;

public class BreakableWall extends Wall {

	public BreakableWall(int r, int c) {
		super(r, c);
	}
	
	

	public void interact() {
		tempMain.breakWall(this.r,this.c);
	}
	public void makeDiscovered() {
		hasBeenDiscovered = true;
	}
	
	public String toString() {
		return "■";
	}

}
