package Tests;

import GameEngine.*;

public class ScoreManagerTest {
	
	public static void main(String[] args){
		
		ScoreManager sm = new ScoreManager();
		sm.begin(2, 2);
		for(int i=0; i < 10000; i++)
		{
			System.out.println(sm.timeInSeconds());
			try{
				Thread.sleep(500);
			}
			catch(Exception e){}
		}
		
	}
	
}
