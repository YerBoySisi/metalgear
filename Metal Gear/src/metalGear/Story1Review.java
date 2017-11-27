package metalGear;

public class Story1Review extends StoryRoom {

	public Story1Review(String description) {
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
			dialouge("Good stuff, soldier. These intel files will allow us to halt the assembly of the metal gear./"
					+ "However, a new threat has emerged that needs to be handled ASAP./"
					+ "Please proceed to the next briefing when you are ready.");
		}
	}
}


