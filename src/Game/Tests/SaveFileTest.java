package Game.Tests;

import Game.Control.GameEngine.GameEngine;
import Game.Model.Settings.GameSettings;

public class SaveFileTest {
	
	public static void TestSaveFileManager()
	{
		GameSettings settings = new GameSettings();
		GameEngine ge = new GameEngine(settings);
		ge.save();
		
		ge.load();
		assert(ge.getBoardSize() == ge.getBoardSize()) : "størrelserne på de to gameEngines er ikke ens";
		
		System.out.println("SaveFileTest finished testing");
	}
}
