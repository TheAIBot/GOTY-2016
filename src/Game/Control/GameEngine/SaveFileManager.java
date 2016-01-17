package Game.Control.GameEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveFileManager<T> {

	// The directory which contains the save files.
	private String saveFileDirectory;

	/** The constructor creates the folder to save the files if it does not already exists.
	 */
	public SaveFileManager(String saveFolder) {
		saveFileDirectory = saveFolder;
		File saveDir = new File(saveFileDirectory);
		saveDir.mkdir();
	}
	
	/** Saves the current state of the given object as byte code in the corresponding folder with the given name. 
	 *  Returns true if saving the state of the game engine went successfully,
	 *  and returns false otherwise.
	 * 
	 * @param saveName The name that the save file of the object should have (without the file extension) 
	 * @param ge The object which's state should be saved
	 * @return True if the saving succeded, else false.
	 */
	public boolean save(String saveName, T ge) {
		try {
			// Creates the file to contain the state of the object state.
			try (FileOutputStream saveFileOut = new FileOutputStream(saveFileDirectory + File.separator + saveName + ".ser")) {
				try (ObjectOutputStream saveObjOut = new ObjectOutputStream(saveFileOut)) {
					// Write the object state as byte code to the file.
					saveObjOut.writeObject(ge);
				}
			}
		} catch (Exception e) {
			Log.writeError(e);
			return false;
		}
		return true;
	}
	
	/** Deletes a file with a given filename (with the file extension .ser) in the defined save file directory.
	 * 
	 * @param filename  The name of the file. 
	 * 					The file extension .ser is added to it, and should therefore no be included.
	 * @return True if the deletion is successful, else false.
	 */
	public boolean deleteFile(String filename)
	{
		try{
			File theFile = new File(saveFileDirectory + File.separator + filename + ".ser");
			return theFile.delete();
		}
		catch(Exception e)
		{
			Log.writeError(e);
			return false;
		}
	}
	
	/** Static method for deleting a file with a given filename (with the file extension .ser) and directory
	 * @param directory The directory wherin the file is placed.
	 * @param filename The name of the file. The file extension .ser is added to it.
	 * @return True if the deletion is successful, else false.
	 */
	public static boolean deleteFile(String directory, String filename)
	{
		try{
			File theFile = new File(directory + "/" + filename + ".ser");
			return theFile.delete();
		}
		catch(Exception e)
		{
			Log.writeError(e);
			return false;
		}
	}

	/** Loads the state of the saved object state from byte code in the folder with the given name. 
	 *  Returns the object if reading from the file were succedfull, and returns null otherwise.
	 * @param loadName The name of the file. The file extension .ser is added to it.
	 * @return  True if the loading is successful, else false.
	 */
	@SuppressWarnings("unchecked")
	public T load(String loadName) {
		// Deserializes object state.
		try {
			// Gets file which contains the object state to be loaded.
			try (FileInputStream loadFileIn = new FileInputStream(saveFileDirectory + "/" + loadName + ".ser")) {
				try (ObjectInputStream loadObjIn = new ObjectInputStream(loadFileIn)) {
					// Read object state data
					return (T) loadObjIn.readObject();
				}				
			}
		} catch (Exception e) {
			Log.writeError(e);
			return null;
		}
	}

}
