package Tests;

import java.awt.Color;
import java.awt.Point;

import Game.Board.GameBoard;
import Game.Board.Tile;
import Game.Difficulty.DifficultyCalculator;
import Game.Settings.GameSettings;

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
		GameBoard game = new GameBoard(settings);
		game.createGame();
		double difficulty = DifficultyCalculator.getDfficulty(game.getTiles(), size);
		assert(difficulty == 0) : "starter difficulty is incorrect for the size " + size + ", expected " + 0 + " and got " + difficulty;
	}
	
	private static void testHighestDifficulty(int size)
	{
		GameSettings settings = new GameSettings();
		GameBoard game = new GameBoard(settings);
		game.createGame();
		reverse(game.getTiles(), size);
		double difficulty = DifficultyCalculator.getDfficulty(game.getTiles(), size);
		double maxDifficulty = DifficultyCalculator.getMaxDifficulty(size);
		assert(difficulty == maxDifficulty) : "max difficulty is incorrect for the size " + size + ", expected " + difficulty + " and got " + maxDifficulty;
	}

	private static Tile[] reverse(Tile[] tiles, int size){
		Tile a = tiles[0];
		tiles[0] = null;
		tiles[tiles.length - 1] = new Tile(1, new Point(size - 1, size - 1), Color.BLACK);
		int number = size * size;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] != null) {
				tiles[i] = new Tile(number, tiles[i].getPosition(), Color.BLACK);
			}
			
			number--;
		}
		return tiles;
	}
}
