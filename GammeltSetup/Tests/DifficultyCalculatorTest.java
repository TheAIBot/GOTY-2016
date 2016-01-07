package Tests;

import Model.DifficultyCalculator;
import Model.GameBoard;

public class DifficultyCalculatorTest {
	public static void TestDifficultyCalculator()
	{
		GameBoard game = new GameBoard(3);
		game.createGame();
		assert(DifficultyCalculator.getDifficulty(game.getTiles(), 3)) == 3);
	}
}
