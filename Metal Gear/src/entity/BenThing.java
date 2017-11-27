package entity;

import metalGear.BenTempMain;

public class BenThing {
	protected int c;
	protected int r;
	protected boolean hasBeenDiscovered;
	
	public BenThing(int r, int c) {
		this.c = c;
		this.r = r;
		hasBeenDiscovered = false;
	}
	
	//whenever a plaer moves, interact is called on the object in the olvlv he is moving to
	//Thing is a default: blank space -> moves player to space
	public void interact() {
		BenTempMain.p.playerMove(r, c);
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
	
	
	
