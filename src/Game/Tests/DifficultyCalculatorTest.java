package Game.Tests;

import java.awt.Color;
import java.awt.geom.Point2D;

import Game.Model.Board.SinglePlayerBoard;
import Game.Model.Board.Tile;
import Game.Model.Difficulty.DifficultyCalculator;
import Game.Model.Settings.GameSettings;

public class DifficultyCalculatorTest {
	public static void TestDifficultyCalculator()
	{
		for (int i = 2; i < 100; i+= 11) {
			testStartDifficulty(i);
		}
		
		testHighestDifficulty(4);
		testHighestDifficulty(10);
		testHighestDifficulty(35);
		testHighestDifficulty(65);
		testHighestDifficulty(100);
		
		System.out.println("DifficultyCalculatorTest finished testing");
	}
	
	private static void testStartDifficulty(int size)
	{
		GameSettings settings = new GameSettings();
		SinglePlayerBoard game = new SinglePlayerBoard(settings, 0);
		game.createGame();
		double difficulty = DifficultyCalculator.getDifficulty(game.getTiles(0), size);
		assert(difficulty == 0) : "starter difficulty is incorrect for the size " + size + ", expected " + 0 + " and got " + difficulty;
	}
	
	private static void testHighestDifficulty(int size)
	{
		GameSettings settings = new GameSettings();
		SinglePlayerBoard game = new SinglePlayerBoard(settings, 0);
		game.createGame();
		reverse(game.getTiles(0), size);
		double difficulty = DifficultyCalculator.getDifficulty(game.getTiles(0), size);
		double maxDifficulty = DifficultyCalculator.getMaxDifficulty(size);
		assert(difficulty == maxDifficulty) : "max difficulty is incorrect for the size " + size + ", expected " + difficulty + " and got " + maxDifficulty;
	}

	private static Tile[] reverse(Tile[] tiles, int size){
		tiles[0] = null;
		tiles[tiles.length - 1] = new Tile(1, new Point2D.Double(size - 1, size - 1), Color.BLACK);
		int number = size * size;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] != null) {
				tiles[i] = new Tile(number, tiles[i].getGoingTowardsPosition(), Color.BLACK);
			}
			
			number--;
		}
		return tiles;
	}
}
