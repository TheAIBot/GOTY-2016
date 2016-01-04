package GameEngine;

import java.io.*;

public class SaveFileManager<T> {

	// The directory which contains the save files.
	private String saveFileDirectory;

	/**
	 * The constructor creates the folder to save the files if it does not already
	 * exists.
	 */
	public SaveFileManager(String saveFolder) {
		saveFileDirectory = saveFolder;
		File saveDir = new File(saveFileDirectory);
		saveDir.mkdir();

	}

	/**
	 * Saves the current state of the given object as byte code in the corresponding
	 * folder with the given name. Returns true if saving the state of the game
	 * engine went successfully and returns false otherwise.
	 */
	public boolean save(String saveName, T ge) {
		try {
			// Create the file to contain the state of the object state.
			try (FileOutputStream saveFileOut = new FileOutputStream(saveFileDirectory + "/" + saveName + ".ser")) {
				try (ObjectOutputStream saveObjOut = new ObjectOutputStream(saveFileOut)) {

					// Write the object state as byte code to the file
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
	 * Loads the state of the saved object state from byte code in the
	 * folder with the given name. Returns the object if reading from the
	 * file went succesfully, and returns null otherwise.
	 */
	public T load(String loadName) {
		// Deserializes object state
		try {
			// Get file which contains the object state to be loaded.
			try (FileInputStream loadFileIn = new FileInputStream(saveFileDirectory + "/" + loadName + ".ser")) {
				try (ObjectInputStream loadObjIn = new ObjectInputStream(loadFileIn)) {

					// Read object state data
					return (T) loadObjIn.readObject();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
