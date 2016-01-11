package Tests;

import java.util.ArrayList;

import Control.GameEngine.SaveFileManager;
import Game.Score.Highscore;
import javafx.util.Pair;;

public class HighscoreTest {
	public static void TestHighscore()
	{
		SaveFileManager.deleteFile("Highscore", "highscore");
		assert(Highscore.newScore("fisk", 12)) : "score not added";
		assert(!Highscore.newScore("derp", 10)) : "score not added";
		assert(!Highscore.newScore("kage", 11)) : "score not added";
		ArrayList<Pair<String, Integer>> scores = Highscore.getHighscores();
		checkScore(scores.get(0), "fisk", 12);
		checkScore(scores.get(1), "kage", 11);
		checkScore(scores.get(2), "derp", 10);
		
		System.out.println("HighscoreTest finished testing");
	}
	
	private static void checkScore(Pair<String, Integer> score, String expectedName, int expectedScore)
	{
		assert(score.getKey().equals(expectedName)) : "wrong name received, expected: " + expectedName + " and got " + score.getKey();
		assert(score.getValue().intValue() == expectedScore) : "wrong score received, expected: " + expectedScore + " and got " + score.getValue().intValue();
	}
}
