package Tests;

import java.awt.Color;
import java.awt.Point;

import com.sun.javafx.scene.control.skin.TitledPaneSkin;

import Model.DifficultyCalculator;
import Model.GameBoard;
import Model.Tile;

public class DifficultyCalculatorTest {
	public static void TestDifficultyCalculator()
	{
		for (int i = 2; i < 100; i+= 11) {
			testStartDifficulty(i);
		}
		
		testHighestDifficulty(2, 8);
		testHighestDifficulty(10, 840);
		testHighestDifficulty(35, 40532);
		testHighestDifficulty(65, 266372);
		testHighestDifficulty(100, 980400);
		
		System.out.println("DifficultyCalculatorTest finished testing");
	}
	
	private static void testStartDifficulty(int size)
	{
		GameBoard game = new GameBoard(size);
		game.createGame();
		double difficulty = DifficultyCalculator.getDfficulty(game.getTiles(), size);
		assert(difficulty == 0) : "starter difficulty is incorrect for the size " + size + ", expected " + size + " and got " + difficulty;
	}
	
	private static void testHighestDifficulty(int size, int expectedDifficulty)
	{
		GameBoard game = new GameBoard(size);
		game.createGame();
		reverse(game.getTiles(), size);
		double difficulty = DifficultyCalculator.getDfficulty(game.getTiles(), size);
		assert(difficulty == expectedDifficulty) : "starter difficulty is incorrect for the size " + size + ", expected " + expectedDifficulty + " and got " + difficulty;
	}

	private static Tile[] reverse(Tile[] tiles, int size){
		Tile a = tiles[0];
		tiles[0] = null;
		tiles[tiles.length - 1] = new Tile(2, new Point(size - 1, size - 1), Color.BLACK);
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
