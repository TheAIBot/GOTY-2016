package Model;

import java.awt.Point;

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
				Point expectedPositionForThatNumber = convertIndexToPoint(i, size);
				difficulty += CalculateDistance(size, expectedPositionForThatNumber, tiles[i].position);
			}
		}
		return difficulty;
	}
	
	private static double CalculateDistance(int size, Point a, Point b)
	{
		Point ba = new Point((int)Math.abs(a.getX() - b.getX()),
							 (int)Math.abs(a.getY() - b.getY()));
		return ba.getX() + ba.getY();
	}
	
	private static Point convertIndexToPoint(int index, int size)
	{
			int row = index / size;
			int col = index % size;

			return new Point(col, row);
	}
	
	private static double getMaxDifficulty(int size)
	{
		final int maxNumber = size * size;
		int expectedNumber = maxNumber;
		double difficulty = 0;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				difficulty += CalculateDistance(size, convertIndexToPoint(maxNumber - expectedNumber, size), convertIndexToPoint(expectedNumber, size));
				expectedNumber++;
			}
		}
		return (double)difficulty;
	}
}
