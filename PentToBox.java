/*
	PentToBox converts a pentomino to a box.
	This is used to display the pentominos in the Display class.
 */

import java.util.ArrayList;

public class PentToBox {
	public static ArrayList<PlacedParcel> convert(ArrayList<PlacedPentomino> pentArray){
		double spacing = 1.05; //empty space between the pentominoes to see each seperate pentominoes
		ArrayList<PlacedParcel> boxArray = new ArrayList<PlacedParcel>();
		System.out.println("Converting to boxes ");

		for (int p = 0; p < pentArray.size(); p++) {
			PlacedPentomino pent = pentArray.get(p);
			char[][][] array = pent.toArray();

			for(int i = 0; i < array.length; i++){
				for(int j = 0; j < array[0].length; j++){
					for(int	k = 0; k < array[0][0].length; k++){
						if(array[i][j][k] == 'p'){
							boxArray.add(new PlacedParcel(1, 1, 1, 'A', pent.getX() * spacing + ((double) i / 2), pent.getY() * spacing  + ((double) j / 2), pent.getZ() * spacing  + ((double) k / 2)));
						}
						if(array[i][j][k] == 'l'){
							boxArray.add(new PlacedParcel(1, 1, 1, 'B', pent.getX() * spacing  + ((double) i / 2), pent.getY() * spacing  + ((double) j / 2), pent.getZ() * spacing  + ((double) k / 2)));
						}
						if(array[i][j][k] == 't'){
							boxArray.add(new PlacedParcel(1, 1, 1, 'C', pent.getX() * spacing  + ((double) i / 2), pent.getY() * spacing  + ((double) j / 2), pent.getZ() * spacing  + ((double) k / 2)));
						}
					}
				}
			}
		}
		return boxArray;
	}
}
