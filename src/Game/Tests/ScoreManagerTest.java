package Tests;


import Control.GameEngine.SaveFileManager;
import Game.Score.ScoreManager;

public class ScoreManagerTest {
	
	public static void TestScoreMangerSaveability(){
		
		SaveFileManager<ScoreManager> sfm = new SaveFileManager<ScoreManager>("sm_test");
		ScoreManager sm = sfm.load("my_sm");
		
		
		/*
		ScoreManager sm = new ScoreManager();
		sm.begin(3, 34);
		try{
			Thread.sleep(2300);
		}catch(Exception e){}
		sm.stop();
		
		SaveFileManager<ScoreManager> sfm = new SaveFileManager<ScoreManager>("sm_test");
		sfm.save("my_sm", sm);
		System.out.println("Saved!");
		System.out.println("time: " + sm.getTimeElapsedSeconds());
		*/
		
	}
	
}
