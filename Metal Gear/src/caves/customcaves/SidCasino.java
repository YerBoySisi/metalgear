package caves.customcaves;

import explorer.ExplorerMain;

public class SidCasino extends caves.CaveRoom {
	int profit;

	public SidCasino(String description) {
		super(description);
		String message = "Hey, welcome to Sid's Casino! At this casino we believe losing sucks, so you win no matter what! \n "
				+ "Your roll multiplied by 10 will be added to your wallet.\n"
				+ "Press 'e' to roll!";
		setDescription(message);
	}
	
	public int rollDice() {
		return (int)(Math.random()*6+1);
	}

	public String validKeys() {
		return "wdsae";
	}

	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's' 'd' or 'e'.");
	}
	
	public void performAction(int direction) {
		if (direction==4) {
			profit = rollDice()*10;
			System.out.println("Looks like you've just made "+profit+"! Nice going! You now have  $"+ExplorerMain.inventory.getMoney()+"!");
			ExplorerMain.inventory.addMoney(profit);
		}
		else {
			System.err.println("Invalid input.");
		}
	}
}
