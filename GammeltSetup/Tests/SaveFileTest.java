package Tests;

import Control.GameEngine.GameEngine;
import Control.GameEngine.SaveFileManager;

public class SaveFileTest {
	
	public static void TestSaveFileManager()
	{
		GameEngine ge = new GameEngine(20);
		ge.save();
		
		ge.load();
		assert(ge.getBoardSize() == ge.getBoardSize()) : "størrelserne på de to gameEngines er ikke ens";
		
		System.out.println("SaveFileTest finished testing");
	}
}
