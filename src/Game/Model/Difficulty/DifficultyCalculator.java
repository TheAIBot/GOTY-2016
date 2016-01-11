package Game.Difficulty;

import java.awt.Point;

import Game.Board.Tile;

public class DifficultyCalculator {
	public static DifficultyLevel getDifficultyLevel(Tile[] tiles, int size)
	{
		double difficultyPercentage = getDfficulty(tiles, size) / getMaxDifficulty(size);
		return DifficultyLevel.getDifficultylevelFromPercentDifficult(difficultyPercentage);
	}
	
	public static double getDfficulty(Tile[] tiles, int size)
	{
		double difficulty = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] != null) {
				Point expectedPositionForThatNumber = convertIndexToPoint(tiles[i].getNumber() - 1, size);
				difficulty += calculateDistance(size, expectedPositionForThatNumber, convertIndexToPoint(i, size));
			}
		}
		return difficulty;
	}
	
	private static double calculateDistance(int size, Point a, Point b)
	{
		Point ba = new Point((int)Math.abs(a.x - b.x),
							 (int)Math.abs(a.y - b.y));
		//System.out.println((ba.x + ba.y));
		return ba.x + ba.y;
	}
	
	private static Point convertIndexToPoint(int index, int size)
	{
			int row = index / size;
			int col = index % size;

			return new Point(col, row);
	}
	
	public static double getMaxDifficulty(int size)
	{
		final int maxNumber = size * size;
		int expectedNumber = maxNumber;
		double difficulty = 0;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				difficulty += calculateDistance(size, convertIndexToPoint(maxNumber - expectedNumber, size), 
													  convertIndexToPoint(expectedNumber - 1, size));
				expectedNumber--;
			}
		}
		return (double)difficulty - (size - 1) * 2;
	}
}
