package Tests;

import Control.GameEngine.GameEngine;
import Control.GameEngine.SaveFileManager;
import Model.GameSettings;

public class SaveFileTest {
	
	public static void TestSaveFileManager()
	{
		GameSettings settings = new GameSettings();
		GameEngine ge = new GameEngine(settings);
		ge.save();
		
		ge.load();
		assert(ge.getBoardSize() == ge.getBoardSize()) : "st�rrelserne p� de to gameEngines er ikke ens";
		
		System.out.println("SaveFileTest finished testing");
	}
}