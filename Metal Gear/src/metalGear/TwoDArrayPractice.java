package metalGear;

public class TwoDArrayPractice {

	public static void main(String[] args) {
		String[][] pic = new String[6][10];
		for (int row = 0; row < pic.length; row++) {
			for (int col = 0; col < pic[row].length; col++) {
				pic[row][col] = " ";
			}
		}
		drawBox(pic,1,4);
		print(pic);
	}

	
	/**
	 * A box looks like this
	 * 		___
	 * 		|*|
	 * 		|_|
	 * 
	 * but the * marks the coordinates of the box
	 * CATCH: no ArrayIndexoutOfBoundsException
	 * @param pic
	 * @param i
	 * @param j
	 */
	private static void drawBox(String[][] pic, int i, int j) {
		/*
		if (i-1>0) {
			if(j+1<pic[i].length-1) {
				pic[i-1][j-1] = "_";
				pic[i-1][j] = "_";
				pic[i-1][j+1] = "_";
			}
		}
		if (i<pic.length) {
			if (j+1<pic[i].length-1) {
				pic[i][j-1] = "|";
				pic[i][j] = "*";
				pic[i][j+1] = "|";
			}
		}
		if (i+1<pic.length) {
			if (j+1<pic[i].length) {
				pic[i+1][j-1] = "|";
				pic[i+1][j] = "_";
				pic[i+1][j+1] = "|";
			}
		}
		*/
		drawSlot(pic, i, j);
		drawSlot(pic, i+1, j);
		drawIfInBounds("_", pic, i+1, j);
		for (int col = j-1; col <= j+1; col++) {
			drawIfInBounds("_", pic, i-1, col);
		}
		
		
	}

	private static void drawIfInBounds(String string, String[][] pic, int i, int j) {
		//NOTE: Always check row before column
		//In other words, check row exists before checking if column exists in the row
		if (i >= 0 && i < pic.length && j >= 0 && j < pic[i].length) {
			pic[i][j] = string;
		}
		
	}


	/**
	 * A slot looks like this:
	 *     | |
	 * It is 2 vertical lines with a space in between them
	 * The coordinates, (i,j), are the coordinates of the space
	 * In other words, a slot is a vertical line in
	 * front and behind (i,j)
	 * CATCH: no ArrayIndexOutOfBoundExceptions
	 * @param pic
	 * @param i
	 * @param j
	 */
	private static void drawSlot(String[][] pic, int i, int j) {
		if (i>=0 && i < pic.length) {
			if (j> 0) {
				pic[i][j-1] = "|";
			}
			if (j < pic[i].length-1) {
			pic[i][j+1] = "|";
			}
	
		}
	}

	private static void drawVerticalLine(String[][] pic, int col) {
		for (int row = 0; row < pic.length; row++) {
		pic[row][col] = "|";	
		}
	}


	private static void drawHorizontalLine(String[][] pic, int row) {
		for (int col=0; col < pic[row].length; col++) {
			pic[row][col] = "-";
		}
		
	}

	/**
	 * prints contents of pic, row by row
	 * NO BRACKETS OR COMMAS
	 * @param pic
	 */
	private static void print(String[][] pic) {
		String result = "";
		for (int row = 0; row < pic.length; row++) {
			for (int col = 0; col < pic[row].length; col++) {
				result += pic[row][col];
			}
			result +=  "\n";
		}
		System.out.println(result);
	}
}
