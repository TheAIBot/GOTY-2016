package Game.Tests;

import Game.Control.GameEngine.GameEngine;
import Game.Model.Settings.GameSettings;

public class SaveFileTest {
	
	public static void TestSaveFileManager()
	{
		GameSettings settings = new GameSettings();
		GameEngine ge = new GameEngine(settings);
		ge.save();
		
		GameEngine loaded = GameEngine.load();
		assert(loaded.getBoardSize() == ge.getBoardSize()) : "st�rrelserne p� de to gameEngines er ikke ens";
		
		System.out.println("SaveFileTest finished testing");
	}
}
