package Tests;

import GameEngine.GameEngine;
import GameEngine.SaveFileManager;

public class SaveFileTest {
	
	public static void TestSaveFileManager()
	{
		GameEngine ge = new GameEngine(20);
		SaveFileManager<GameEngine> sfm = new SaveFileManager<GameEngine>("saveFiles");
		sfm.save("save_test1", ge);
		
		GameEngine ge_load = sfm.load("save_test1");
		assert(ge.getBoardSize() == ge_load.getBoardSize()) : "størrelserne på de to gameEngines er ikke ens";
		//TODO add mode checks
		
	}
	
}
