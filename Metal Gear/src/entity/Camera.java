package entity;

import explorer.tempMain;

public class Camera extends Thing{
	private boolean cameraPlaced;
	
	public Camera(int r, int c) {
		super(r, c);
		
		
	}
	
	public int getR() {
		return this.r;
	}
	public int getC() {
		return this.c;
	}
	
	public void interact() {
		
		cameraPlaced = false;
		tempMain.breakWall(this.r,this.c);//olvl[this.r][this.c] = new Thing(this.r,this.c);
		this.r = -1;
		this.c = -1;
	}
	
	public String toString() {
		return "C";
	}
	
	public void placeCamera(int r, int c){
		cameraPlaced = true;
		
		this.r = r;
		this.c = c;
		
		tempMain.olvl[this.r][this.c] = this;
	}
	
	public boolean isCameraPlaced() {
		return cameraPlaced;
	}
	
	public boolean seenByGuard(Guard g) {
		
		for(int i = 0; i < g.getFieldOfView().length; i++) {
			
			if(g.getFieldOfView()[i][0] == this.r && g.getFieldOfView()[i][1] == this.c) {
				return true;
			}
			
		}
		
		return false;

	}
	
	public void breakCamera() {
		
		this.r = -1;
		this.c = -1;
		
	}

}
