package explorer;

public class IntelFile extends Wall{

	public IntelFile(int r, int c) {
		super(r, c);
	}
	
	public void interact() {
		tempMain.p.setObjectiveReached(true);
		tempMain.breakWall(this.r, this.c);
		tempMain.dialouge("Nice job Boss./ Now get to the extration point.");
	}
	
	public String toString() {
		return "#";
	}

}
