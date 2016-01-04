import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class Highscore {

	private static final String SCORE_FILE_PATH = "highscore.txt";
	private static final String NAME_SCORE_SPLITTER = "\t";

	public static ArrayList<Pair<String, Integer>> getHighscores() {
		String[] allLines = getAllLinesFromFile(SCORE_FILE_PATH);
		if (allLines != null) {
			ArrayList<Pair<String, Integer>> scores = new ArrayList<Pair<String, Integer>>(allLines.length);
			for (String line : allLines) {
				String[] splitted = line.split(NAME_SCORE_SPLITTER);
				Pair<String, Integer> score = new Pair<String, Integer>(splitted[0], Integer.valueOf(splitted[1]));
				scores.add(score);
			}
			return scores;
		} else {
			return new ArrayList<Pair<String, Integer>>();
		}
	}

	public static boolean newScore(String name, int newScore) {
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

	public static void save(ArrayList<Pair<String, Integer>> scores) {
		try (PrintWriter scoreFile = new PrintWriter(SCORE_FILE_PATH)) {
			for (Pair<String, Integer> score : scores) {
				scoreFile.println(score.getKey() + NAME_SCORE_SPLITTER + score.getValue());
			}
		} catch (FileNotFoundException e) {
			Log.writeln("Error when saving highscore");
			Log.writeError(e);
		}
	}
	
	/**DENNE METODE ER KOPIERET FRA EN AF VORES TIDELIGERE PROJEKTER, 
	 * SÅ DER SKAL LIGE TJEKKES FOR OM DER BLIVER PROBLEMER MED SELVPLAGIERING
	 * Gets all the lines from a given text file described by its path, and returns a string array of all the lines.
	 * @param filepath The path to the text file.
	 * @return A string array of all the lines in the file. If no file is found, or an error occurs, null is returned.
	 */
	private static String[] getAllLinesFromFile(String filepath) {
		if (filepath != null) {
			File currentFile = new File(filepath);
			//If no file is found, it returns null.
			if (currentFile.exists()) {
				//The scanner is in a try, catch block, for error catching and so that i closes after use.
				//If an error is caught, it returns null.
				try (java.util.Scanner scanner = new java.util.Scanner(currentFile)) {
					List<String> fileLines = new ArrayList<String>();
					while (scanner.hasNextLine()) {
						fileLines.add(scanner.nextLine());
					}
					return fileLines.toArray(new String[fileLines.size()]);
				} catch (Exception e) {
					return null;
				}
			}
		}
		return null;
	}	
}