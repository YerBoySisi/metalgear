package entity;

public interface Person {
	
	//Sets the name of the Person
	//People will be referred to by their name
	//	EXAMPLE:
	//		Guard.name = Special Op
	//		A Special Op is patrolling the room.
	void setName(String name);
	
	//Returns the Person's name
	String getName();
	
	//Sets the coordinates (row and column) of the Person
	void setPosition(int row, int col);
	
	//Performs whatever actions can be performed, such as movement
	void act();
	
}
