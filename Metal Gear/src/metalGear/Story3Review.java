package metalGear;

public class Story3Review extends StoryRoom {

	public Story3Review(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	public static void dialouge(String s) {
		String temp = "";
		for(int i =0; i< s.length(); i++) {
			temp = s.substring(i, i+1);
			if(temp.equals("/")) {
				pause(1000);
				print("\n");
			}else if(temp.equals("=")){
				print("");
			}else {
				pause(30);
				System.out.print(temp);
			}
		}
		print("");
		pause(2000);
	}
	
	private static void print(String string) {
		System.out.println(string);
		
	}

	public static void pause(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {e.printStackTrace();}
	}

	public String validKeys() {
		return "wdsae";
	}

	public void printAllowedEntry() {
		System.out.println("Invalid input.");
	}
	
	public void performAction(int dir) {
		if (dir == 4) {
			dialouge("Mission accomplished. We can't thank you enough, soldier./"
					+ "You've done us well. Your services are no longer needed./"
					+ "..for now.");
		}
	}
}
