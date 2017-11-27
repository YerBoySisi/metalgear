package entity;

import explorer.tempMain;

public class Thing {
	protected int c;
	protected int r;
	protected boolean hasBeenDiscovered;
	
	public Thing(int r, int c) {
		this.c = c;
		this.r = r;
		hasBeenDiscovered = true;
	}
	
	//whenever a plaer moves, interact is called on the object in the olvlv he is moving to
	//Thing is a default: blank space -> moves player to space
	public void interact() {
		tempMain.p.playerMove(r, c);
	}
	
	public int getC() {
		return c;
	}
	public int getR() {
		return r;
	}
	
	//spaces should never be discovered
	public void makeDiscovered() {
		
	}

	public boolean isDiscovered() {
		return hasBeenDiscovered;
	}
	
	public String toString() {
		return " ";
	}
}
	
	
	
