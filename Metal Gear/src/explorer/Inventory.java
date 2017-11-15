package explorer;

import caves.CaveRoom;

public class Inventory {

	private String map;

	public static int cupsCoffee;

	private int money;
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void addMoney(int money) {
		this.money = this.money+money;
	}

	
	public Inventory() {
		updateMap();
		cupsCoffee = 0;

		money = 0;

	}

	public void addCoffee() {
		cupsCoffee++;
	}
	
	public void subCoffee() {
		if(cupsCoffee < 1) {
			System.out.println("You have no coffee to drink");
		}else {
			cupsCoffee--;
			System.out.println("You drank the coffee");
		}
	}
	
	public void updateMap() {
		
		map = " ";
		
		//create line across top:
		for(int i = 0; i < ExplorerMain.caves[0].length -1; i++) {
			map += "____";//4 underscores
		}
		
		map+= "___\n";//3 underscores, makes the corner look symmetrical
		
		for(CaveRoom[] row : ExplorerMain.caves) {
			
			//3 rows of text
			for(int i = 0; i < 3; i++) {
				
				String text = "";
				
				for(CaveRoom cr : row) {
					
					//if door is open, leave open
					if(cr.getDoor(CaveRoom.WEST) != null &&
							cr.getDoor(CaveRoom.WEST).isOpen()) {
						text += " ";
					}else {
						text += "|";
					}
					
					//contents of room depend on what row this is
					if(i==0) {
						text+="   ";//3 spaces
					}else if(i == 1) {
						text += " "+cr.getContents()+" ";
					}else if(i == 2) {
						
						//draw space if door to south is open
						if(cr.getDoor(CaveRoom.SOUTH) != null && 
						cr.getDoor(CaveRoom.SOUTH).isOpen()){
							text+="   ";//3 spaces
						}else {
							text += "___";
						}
						
					}
					
				}//last caveroom in row
				
				text+="|";
				map += text +"\n";
				
			}
			
		}
		
	}

	public String getDescription() {
		return map + "You have "+cupsCoffee+ " cups of coffee";
//		return "You have nothing in your inventory.";
	}

}
