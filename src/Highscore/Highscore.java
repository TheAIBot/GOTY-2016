package Highscore;
import java.util.ArrayList;

import GameEngine.SaveFileManager;
import javafx.util.Pair;

public class Highscore {

	private static final String SCORE_FILE_NAME = "highscore";
	private static final SaveFileManager<ArrayList<Pair<String, Integer>>> SAVE_MANAGER = new SaveFileManager<ArrayList<Pair<String, Integer>>>("Highscore");

 	public static ArrayList<Pair<String, Integer>> getHighscores() {
 		ArrayList<Pair<String, Integer>> scores = SAVE_MANAGER.load(SCORE_FILE_NAME);
		if (scores != null) {
			return scores;
		} else {
			return new ArrayList<Pair<String, Integer>>();
		}
	}

	public static boolean newScore(String name, int newScore) {
		if (name == null) {
			throw new IllegalArgumentException("newScore name was null");
		}
		
		ArrayList<Pair<String, Integer>> scores = getHighscores();
		if (scores.size() == 0) {
			scores.add(new Pair<String, Integer>(name, newScore));
			save(scores);
			return true;
		} else if (scores.get(0).getValue() < newScore) {
			scores.add(0, new Pair<String, Integer>(name, newScore));
			save(scores);
			return true;
		} else {
			for (int i = 0; i < scores.size(); i++) {
				Pair<String, Integer> score = scores.get(i);
				if (score.getValue() < newScore) {
					scores.add(i, new Pair<String, Integer>(name, newScore));
					save(scores);
					return false;
				}
			}
			scores.add(new Pair<String, Integer>(name, newScore));
			save(scores);
			return false;
		}
	}

	public static boolean save(ArrayList<Pair<String, Integer>> scores) {
		if (scores == null) {
			throw new IllegalArgumentException("trying to save null");
		}
		return SAVE_MANAGER.save(SCORE_FILE_NAME, scores);
	}
}