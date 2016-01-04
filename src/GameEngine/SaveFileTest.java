package GameEngine;

public class SaveFileTest {
	
	public static void main(String[] args)
	{
		GameEngine ge = new GameEngine(20);
		SaveFileManager sfm = new SaveFileManager();
		sfm.saveGame("save_test1", ge);
	}
	
}
