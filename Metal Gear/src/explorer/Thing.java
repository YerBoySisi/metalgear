package explorer;

public class Thing {
	//public void interact;
	private int x;
	private int y;
	
	public Thing(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Thing by default is a blank space
	public void interact() {
		//default does nothing;
		Main.p.setX(x);
		Main.p.setY(y);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}
	
	
	
