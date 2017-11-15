package explorer;

public class Thing {
	//public void interact;
	private int c;
	private int r;
	
	public Thing(int r, int c) {
		this.c = c;
		this.r = r;
	}
	
	//Thing by default is a blank space
	public void interact() {
		//default does nothing;
		
		//tempMain.p.setC(c);
		//tempMain.p.setR(r);
	
		tempMain.p.playerMove(r, c);
	}
	
	public int getC() {
		return c;
	}
	public int getR() {
		return r;
	}

}
	
	
	
