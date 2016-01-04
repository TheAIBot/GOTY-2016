package GameEngine;
import java.io.*;

import javax.annotation.processing.FilerException;

public class SaveFileManager {
	
	//The directory which contains the save files.
	private String saveFileDirectory;
	
	/**
	 * The constructor creates the saveFiles folder if it does not already exists.
	 */
	public SaveFileManager()
	{
		saveFileDirectory = "saveFiles";
		File saveDir = new File(saveFileDirectory);
		saveDir.mkdir();
		
	}
	
	/**
	 * Saves the current state of the game engine as byte code in the saveFiles folder with the given name.
	 * Returns true if saving the state of the game engine went successfully.
	 */
	public boolean saveGame(String saveName, GameEngine ge)
	{
		try{
			
			FileOutputStream saveFileOut = new FileOutputStream(saveFileDirectory + "/" + saveName + ".ser");
			ObjectOutputStream saveObjOut = new ObjectOutputStream(saveFileOut);
			saveObjOut.writeObject(ge);
			saveObjOut.close();
			saveFileOut.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * Loads the state of the saved game engine state from byte code in the saveFiles folder with the given name.
	 * Returns true if loading the state of the game engine went successfully and returns false otherwise.
	 */
	public boolean loadGame(String loadName,GameEngine ge)
	{
		//Deserializes game engine state and overwrites the given GameEngine
		//returns true if loading went successfully
		try{
			
			FileInputStream loadFileIn = new FileInputStream(saveFileDirectory + "/" + loadName + ".ser");
			ObjectInputStream loadObjIn = new ObjectInputStream(loadFileIn);
			ge = (GameEngine) loadObjIn.readObject();
			loadObjIn.close();
			loadFileIn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
