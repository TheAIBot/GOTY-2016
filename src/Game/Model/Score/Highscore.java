package Game.Model.Score;

import java.util.ArrayList;

import Game.Control.GameEngine.SaveFileManager;
import javafx.util.Pair;

public class Highscore {

	private static final String SCORE_FILE_NAME = "highscore"; 
	private static final String SCORE_FOLDER_NAME = "Highscore";
	private static final SaveFileManager<ArrayList<Pair<String, Integer>>> SAVE_MANAGER =
			new SaveFileManager<ArrayList<Pair<String, Integer>>>(SCORE_FOLDER_NAME);

	/** Returns the ArrayList of highscores, by loading it from the high score file. 
	 *  If no such file exists,	a clean list of highscores are returned.
	 * @return The ArrayList of highscores
	 */
 	public static ArrayList<Pair<String, Integer>> getHighscores() {
 		ArrayList<Pair<String, Integer>> scores = SAVE_MANAGER.load(SCORE_FILE_NAME);
		if (scores != null) {
			return scores;
		} else {
			return new ArrayList<Pair<String, Integer>>();
		}
	}

 	/** Adds a new score pair (name + score) to the highscore list.
 	 * @param name The name associated with the score.
 	 * @param newScore the score.
 	 * @return
 	 */
	public static boolean newScore(String name, int newScore) {
		if (name == null) {
			throw new NullPointerException("newScore name is null");
		}
		
		ArrayList<Pair<String, Integer>> scores = getHighscores();
		//If there are no other scores in the list, it is simply added to it.
		if (scores.size() == 0) {
			scores.add(new Pair<String, Integer>(name, newScore));
			save(scores);
			return true;
		//If the score is a new highscore, it is added as the new highscore.
		} else if (scores.get(0).getValue() < newScore) {
			scores.add(0, new Pair<String, Integer>(name, newScore));
			save(scores);
			return true;
		//Finds the place amongst the highscores, where, if the new score is placed there, 
		//the list is still ordered, and places it there.
		} else {
			for (int i = 0; i < scores.size(); i++) {
				Pair<String, Integer> score = scores.get(i);
				if (score.getValue() < newScore) {
					scores.add(i, new Pair<String, Integer>(name, newScore));
					save(scores);
					return false;
				}
			}
			//If the score is lower than all other scores, it simply adds it to the list.
			scores.add(new Pair<String, Integer>(name, newScore));
			save(scores);
			return false;
		}
	}

	/**	Saves the highscore using the save file manager.
	 * @param scores The highscore list.
	 * @return True if the saving succeded, false otherwise.
	 */
	public static boolean save(ArrayList<Pair<String, Integer>> scores) {
		if (scores == null) {
			throw new NullPointerException("Trying to save null");
		}
		return SAVE_MANAGER.save(SCORE_FILE_NAME, scores);
	}
}