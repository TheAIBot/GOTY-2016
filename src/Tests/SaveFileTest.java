package Tests;

import GameEngine.GameEngine;
import GameEngine.SaveFileManager;

public class SaveFileTest {
	
	public static void main(String[] args)
	{
		GameEngine ge = new GameEngine(20);
		SaveFileManager<GameEngine> sfm = new SaveFileManager<GameEngine>("saveFiles");
		sfm.save("save_test1", ge);
		
		GameEngine ge_load = sfm.load("save_test1");
		System.out.println(ge_load.size);
		
	}
	
}
