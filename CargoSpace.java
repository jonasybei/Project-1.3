import java.util.*;

/*
	A class meant to be the superclass for all solving algorithms.
	It saves general variables such as the size of the cargo space.
	It is able to find the coordinates for a new box, place it in there, keep track of the steps made, how full the space is, and more.
*/

public class CargoSpace {

	//these variables describe the cargo-space
	protected static final double SPACELENGTH = 11;
	protected static final double SPACEHEIGHT = 4;
	protected static final double SPACEWIDTH = 5;

	protected static final double SPACEVOLUME = SPACELENGTH*SPACEWIDTH*SPACEHEIGHT;
	protected static final double stopPercentage = 0.6;
	protected static double cargoVolume = 0;
	protected static double cargoValue = 0;
	protected static ArrayList<PlacedParcel> solution = new ArrayList<PlacedParcel>();

	//space is a three-dimensional array where every single spot acts as a 0.5*0.5*0.5 block.
	protected static char[][][] space = new char[(int)SPACELENGTH][(int)SPACEHEIGHT][(int)SPACEWIDTH];

	/*public static Pentomino[] sortPentominoes(Pentomino[] pentomino) {
		Pentomino[] newPentomino = new Pentomino[pentomino.length];
		if (pentomino[0].getValue() >= pentomino[1].getValue() && pentomino[0].getValue() >= pentomino[2].getValue()) {
			newPentomino[0] = pentomino[0];
			if (pentomino[1].getValue() >= pentomino[2].getValue()) {
				newPentomino[1] = pentomino[1];
				newPentomino[2] = pentomino[2];
			} else {
				newPentomino[1] = pentomino[2];
				newPentomino[2] = pentomino[1];
			}
		} else if (pentomino[1].getValue() >= pentomino[0].getValue() && pentomino[1].getValue() >= pentomino[2].getValue()) {
			newPentomino[0] = pentomino[1];
			if (pentomino[0].getValue() >= pentomino[2].getValue()) {
				newPentomino[1] = pentomino[0];
				newPentomino[2] = pentomino[2];
			} else {
				newPentomino[1] = pentomino[2];
				newPentomino[2] = pentomino[0];
			}
		} else if (pentomino[2].getValue() >= pentomino[0].getValue() && pentomino[2].getValue() >= pentomino[1].getValue()) {
			newPentomino[0] = pentomino[2];
			if (pentomino[0].getValue() >= pentomino[1].getValue()) {
				newPentomino[1] = pentomino[0];
				newPentomino[2] = pentomino[1];
			} else {
				newPentomino[1] = pentomino[1];
				newPentomino[2] = pentomino[0];
			}
		}
		return newPentomino;
	}*/

	//computes the coordinates for the next box/pentomino to be placed
	/*public static void computeNewCoordinates() {
		for (int i = x; i < space.length; i++) { //height
			for (int j = y; j < space[0].length; j++) { //width
				for (int k = z; k < space[0][0].length; k++) { //length
					if (space[i][j][k] == '\u0000' || space[i][j][k] == '0') {
						setCoordinates(i, j, k);
						return;
					}
				}
			}
		}
	}*/

	/*public static void setCoordinates(int coordinateX, int coordinateY, int coordinateZ) {
		x = coordinateX;
		y = coordinateY;
		z = coordinateZ;
	}*/

	//checks if a specific box//pentomino fits at a specific place with the coordinates x, y, and z
	public static boolean fits(Parcel box, int x, int y, int z) {
		//checks for out-of-bound-errors
		if (x + box.getLength() > space.length) {
			//System.out.println(box.getName() + " x " + x + " Length " + box.getLength());
			return false;
		}
		if (y + box.getHeight() > space[0].length) {
			//System.out.println(box.getName() + " y " + y + " height " + box.getHeight());
			return false;
		}
		if (z + box.getWidth() > space[0][0].length) {
			//System.out.println(box.getName() + " z " + z + " Width " + box.getWidth());
			return false;
		}
		//checks for every single spot in the array to be empty
		for(int i = 0; i < box.getLength(); i++) {
			for (int j = 0; j < box.getHeight(); j++) {
				for (int k = 0; k < box.getWidth(); k++) {
					//System.out.println((space[x + i][y + j][z + k]+ " " + x + i + " " + y + j + " " + z + k));
					if (space[x + i][y + j][z + k] != '\u0000' && space[x + i][y + j][z + k] != '0') {
						//System.out.println(false);
						return false;
					}
				}
			}
		}

		return true;
	}

	//checks if a specific box//pentomino fits at a specific place with the coordinates x, y, and z
	public static boolean fits(char[][][] pentomino, int x, int y, int z) {
		//checks for out-of-bound-errors
		if (x + pentomino.length > space.length) {
			//System.out.println(box.getName() + " x " + x + " Length " + box.getLength());
			return false;
		}
		if (y + pentomino[0].length > space[0].length) {
			//System.out.println(box.getName() + " y " + y + " height " + box.getHeight());
			return false;
		}
		if (z + pentomino[0][0].length > space[0][0].length) {
			//System.out.println(box.getName() + " z " + z + " Width " + box.getWidth());
			return false;
		}
		//checks for every single spot in the array to be empty
		for(int i = 0; i < pentomino.length; i++) {
			for (int j = 0; j < pentomino[0].length; j++) {
				for (int k = 0; k < pentomino[0][0].length; k++) {
					//System.out.println((space[x + i][y + j][z + k]+ " " + x + i + " " + y + j + " " + z + k));
					if (space[x + i][y + j][z + k] != '\u0000' && space[x + i][y + j][z + k] != '0' && pentomino[i][j][k] != '\u0000' && pentomino[i][j][k] != '0') {
						//System.out.println(false);
						return false;
					}
				}
			}
		}

		return true;
	}

	//places a box at a given point with coordinates x, y, and z
	public static void placeBoxAt(Parcel box, int x, int y, int z) { //CHECK IF FITS FIRST!
		for (int i = 0; i < box.getLength(); i++) {
			for (int j = 0; j < box.getHeight(); j++) {
				for (int k = 0; k < box.getWidth(); k++) {
					space[x + i][y + j][z + k] = box.getName();
				}
			}
		}
		cargoVolume = cargoVolume + box.getVolume(); //update the volume
		cargoValue = cargoValue + box.getValue(); //update the value

		PlacedParcel newBox = new PlacedParcel(box.getLength(), box.getHeight(), box.getWidth(), box.getName(), (double) x / 2, (double) y / 2, (double) z / 2); //create new PlacedParcel-Object to add to the solution
		solution.add(newBox);
	}

	//places a pentomino at a certain positon x, y, z
	/*public static void placePentominoAt(Pentomino pent, int rotation, int version, int x, int y, int z) {
		pent.setFliptation(version);
		pent.setRotation(rotation);
		char[][][] pentArray = Pentomino.toArray();
		for(int i = 0; i < pentArray.length; i++){
			for(int j = 0; j < pentArray[0].length; j++) {
				for(int k = 0; k < pentArray[0][0].length; k++) {
					if(pentArray[i][j][k] != '\u0000' || space[i][j][k] == '0') {
						space[x + i][y + j][z + k] = pent[i][j][k];
					}
				}
			}
		}
	}*/

	//deletes the last box in the solution-Array and updates the space accordingly
	public static void deleteBox(int index) {
		//update the space
		PlacedParcel deleteBox = solution.get(index);
		//System.out.println("deletebox " + deleteBox.getName() + " " + deleteBox.getLength() + " " + deleteBox.getWidth() +  " " + deleteBox.getHeight());
		for (int i = 0; i < deleteBox.getLength(); i++) {
			for (int j = 0; j < deleteBox.getHeight(); j++) {
				for (int k = 0; k < deleteBox.getWidth(); k++) {
					//System.out.println(i + " " + j + " " + k + " " + solution.size() + " " + index);
					space[(int) (deleteBox.getX()*2 + i)][(int) (deleteBox.getY()*2 + j)][(int) (deleteBox.getZ()*2 + k)] = '\u0000';
				}
			}
		}
        cargoVolume = cargoVolume - deleteBox.getVolume(); //update the volume
				cargoValue = cargoValue - deleteBox.getValue(); //update the value
		//update the arrayList
		solution.remove(index);
	}

	//returns true if a certain percentage of the cargo-space is full (for exercise b and d)
	public static boolean isFullEnough() {
		if (cargoVolume >= stopPercentage*SPACEVOLUME) {
			return true;
		}
		return false;
	}

	//returns true if the entire cargo-space is full (for exercise a and c)
	public static boolean isFull() {
		for (int i = 0; i < space.length; i++) { //height
			for (int j = 0; j < space[0].length; j++) { //width
				for (int k = 0; k < space[0][0].length; k++) { //length
					if (space[i][j][k] == '\u0000' || space[i][j][k] == '0') {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void print() {
		for(int j = 0; j < space[0].length; j++) {
			for(int i = 0; i < space.length; i++) {
				for(int k = 0; k < space[0][0].length; k++) {
					System.out.print(space[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("length " + space.length + " heigth " + space[0].length + " width " + space[0][0].length);
	}
}
