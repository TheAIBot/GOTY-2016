package Game.Model.Difficulty;

import java.awt.Point;

import Game.Model.Board.Tile;

public class DifficultyCalculator {

	/** Gets the difficulty level of a gameboard, based on the position of the
	 *  tiles on the board, and the size of it.
	 * @param tiles The array of tile representing the layout of the game board.
	 * @param size The size of the game board.
	 * @return
	 */
	public static DifficultyLevel getDifficultyLevel(Tile[] tiles, int size) {
		double difficultyPercentage = getDifficulty(tiles, size) / getMaxDifficulty(size);
		return DifficultyLevel.getDifficultylevelFromPercentDifficult(difficultyPercentage);
	}
	
	public static DifficultyLevel getDifficultyLevel(double maxDifficulty, double difficulty)
	{
		return DifficultyLevel.getDifficultylevelFromPercentDifficult(difficulty / maxDifficulty);
	}

	/**
	 * Finds the difficulty of a board.
	 *  This is calculated as the total amount of distance 
	 *  (not in pythagorian distance, but as the sum of the difference in x and y coordinates between two points) 
	 *  all tiles on the board are away from the position they should have, for the board to be solved. 
	 * @param tiles The array of tile representing the layout of the game board.
	 * @param size The size of the game board.
	 * @return The difficulty level of a given board.
	 */
	public static double getDifficulty(Tile[] tiles, int size) {
		double difficulty = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] != null) {
				Point expectedPositionForThatNumber = convertIndexToPoint(
						tiles[i].getNumber() - 1, size);
				difficulty += calculateDistance(expectedPositionForThatNumber,
						convertIndexToPoint(i, size));
			}
		}
		return difficulty;
	}
	
	/** Calculates the distance between two points a and b, and returns it. 
	 *  This is not the pythagorian distance, 
	 *  but the sum of the difference in the two points x and y coordinates added together.
	 * @param a Point a
	 * @param b Point b
	 * @return The sum of the difference in the two points x and y coordinates added together.
	 */
	private static double calculateDistance(Point a, Point b) {
		return ((int) Math.abs(a.x - b.x)) + ((int) Math.abs(a.y
				- b.y));
	}

	/** Returns a point which coordinates are equivalent to that of the tile on solved game board of a given size,
	 *  which associated index/number is equivalent to that of the given index.
	 * @param index The index of the the tiles which position should be found.
	 * @param size The size of the board.
	 * @return
	 */
	private static Point convertIndexToPoint(int index, int size) {
		int row = index / size;
		int col = index % size;
		return new Point(col, row);
	}
	
	/** Returns the max difficulty a game board of a given size can have. 
	 *  This is calculated as the total amount of distance 
	 *  (not in pythagorian distance, but as the sum of the difference in x and y coordinates between two points) 
	 *  all tiles on the board are away from the position they should have, for the board to be solved. 
	 * @param size
	 * @return
	 */
	public static double getMaxDifficulty(int size) {
		//To find the max difficulty of the board, the difficulty of a completely inverted board is found.
		//This is the board setup where tile 1 has changed place with the nullTile (the empty place on the board),
		//tile 2 with tile size^2 - 1, tile 3 with tile size^2 - 2 and so on.
		int nullTileDifficulty = (size - 1) * 2; //The difficulty associated with the nullTile
		final int maxNumber = size * size; //The max number a tile can have associated with it.
		//This is practely the tile number of the void tile.
		int expectedNumber = maxNumber; 
		double difficulty = 0;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {				
				//
				difficulty += calculateDistance(convertIndexToPoint(maxNumber - expectedNumber, size),
												convertIndexToPoint(expectedNumber - 1, size)); 
				//Removes 1 from expected number, so it goes from index 1 to index 0. 
				//This is not needed with maxNumber - expectedNumber, as this is index 0.
				expectedNumber--;
			}
		}
		//The difficulty of the nullTile is removed from the result, 
		//as it is not included in the calculation of the difficulty of the "real" board.
		return (double) difficulty - nullTileDifficulty; 
	}
}
