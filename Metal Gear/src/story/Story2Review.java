package story;

import metalGear.StoryRoom;

public class Story2Review extends StoryRoom {

	public Story2Review(String description) {
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
			dialouge("That was a close one, but without this intel package they'll be unable to do anything as devastating and stupid./"
					+ "All thats left is to infiltrate the Soviet base and 'borrow' to start codes for the metal gear./"
					+ "Sounds like a walk in the park right?/"
					+ "Proceed.");
		}
	}
}
