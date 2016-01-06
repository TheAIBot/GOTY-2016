package Tests;

import GameEngine.*;

public class ScoreManagerTest {
	
	public static void main(String[] args){
		
		boolean b = false;
		ScoreManager sm = new ScoreManager();
		sm.begin(1, 1);
		while(true)
		{
			try{
				Thread.sleep(100);
			}
			catch(Exception e){}
			
			System.out.println(sm.getTimeElapsedSeconds() + " - " + sm.getTotalScore());
			
			if(sm.getTimeElapsedSeconds() == 5 && b == false)
			{
				sm.setTotalScore(0);
				sm.stopRunning();
				break;
			}
			
		}
		try{
			Thread.sleep(2000);
		}catch(Exception e){}
		sm.continueRunning();
		while(true)
		{
			try{
				Thread.sleep(100);
			}
			catch(Exception e2){}
			
			System.out.println(sm.getTimeElapsedSeconds() + " - " + sm.getTotalScore());
			
			
		}
		
	}
	
}
