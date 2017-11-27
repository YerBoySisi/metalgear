package metalGear;

public class Story3 extends StoryRoom {

	public Story3(String description) {
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
			dialouge("Just do this last thing for us, and you'll be free to go./"
					+ "It's the same thing as before, just locate the startup codes for that dangerous metal gear./"
					+ "Something as powerful as that would be a huge pain in our necks, and cost the lives of many innocent civilians./"
					+ "It's all on you, soldier. Thank you.");
		}
	}
}
