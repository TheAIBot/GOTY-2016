package Tests;

import java.util.ArrayList;

import Highscore.Highscore;
import javafx.util.Pair;;

public class HighscoreTest {
	public static void HighscoreTest()
	{
		assert(Highscore.newScore("fisk", 12)) : "score not added";
		assert(Highscore.newScore("derp", 10)) : "score not added";
		assert(Highscore.newScore("kage", 11)) : "score not added";
		ArrayList<Pair<String, Integer>> scores = Highscore.getHighscores();
		checkScore(scores.get(0), "fisk", 12);
		checkScore(scores.get(0), "kage", 11);
		checkScore(scores.get(0), "derp", 10);
	}
	
	private static void checkScore(Pair<String, Integer> score, String expectedName, int expectedScore)
	{
		assert(score.getKey() == expectedName) : "wrong name received, expected: " + expectedName + " and got " + score.getKey();
		assert(score.getValue().intValue() == expectedScore) : "wrong score received, expected: " + expectedScore + " and got " + score.getValue().intValue();
	}
}
