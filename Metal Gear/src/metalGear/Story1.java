package metalGear;

public class Story1 extends StoryRoom {

	public Story1(String description) {
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
			dialouge("Hey Boss,/ Welcome to the base!/ We just finished the construction of the r&d and intel platforms.//"
					+ " Our intel team intercepted a tranmission from the soviets,= discussing their plans to build a fusion-based "
					+ "metal gear in Afganistan./ We need you to investigate..../"
					+ "TUTORIAL:/"
					+ "Use WASD to move around. Your vision is limited,so move around to reveal your surroundings./"
					+ "You can place down a camera to keep a section of the map in view. Press c and then a direction with WASD to place one down. (only one can be placed at a time.)/"
					+ "Watch out for guards! If they catch you, it's game over./"
					+ "If you manage to sneak up on a guard, you can assassinate and place his dead body somewhere else./"
					+ "Good luck!");
		}
	}
}
