package GameEngine;

import java.io.*;

public class SaveFileManager<T> {

	// The directory which contains the save files.
	private String saveFileDirectory;

	/**
	 * The constructor creates the saveFiles folder if it does not already
	 * exists.
	 */
	public SaveFileManager(String saveFolder) {
		saveFileDirectory = saveFolder;
		File saveDir = new File(saveFileDirectory);
		saveDir.mkdir();

	}

	/**
	 * Saves the current state of the game engine as byte code in the saveFiles
	 * folder with the given name. Returns true if saving the state of the game
	 * engine went successfully.
	 */
	public boolean save(String saveName, T ge) {
		try {
			// Create the file to contain game engine state.
			try (FileOutputStream saveFileOut = new FileOutputStream(saveFileDirectory + "/" + saveName + ".ser")) {
				try (ObjectOutputStream saveObjOut = new ObjectOutputStream(saveFileOut)) {

					// Write the game engine state as byte code to the file
					saveObjOut.writeObject(ge);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Loads the state of the saved game engine state from byte code in the
	 * saveFiles folder with the given name. Returns true if loading the state
	 * of the game engine went successfully and returns false otherwise.
	 */
	public T load(String loadName) {
		// Deserializes game engine state and overwrites the given GameEngine
		// returns true if loading went successfully
		try {
			// Get file which contains the game engine state to be loaded.
			try (FileInputStream loadFileIn = new FileInputStream(saveFileDirectory + "/" + loadName + ".ser")) {
				try (ObjectInputStream loadObjIn = new ObjectInputStream(loadFileIn)) {

					// Read game engine state data
					return (T) loadObjIn.readObject();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
