package story;

import metalGear.StoryRoom;

public class Story2 extends StoryRoom {

	public Story2(String description) {
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
			dialouge("Research into the intel files you just retrieved shows that not only is there bieng a metal gear being built/"
					+ "but the soviets are making big strides in advanced nuclear warfare. We will not stand for this./"
					+ "Luckily, they love keeping all their info in one package, so confiscating that should do the trick./"
					+ "You know what to do. Good luck.");
		}
	}
}
