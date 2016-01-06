package GameEngine;

public class ScoreManager extends Thread{

	private int totalScore;
	private int scorePerSecond;
	private int scorePerMove;
	
	
	private long startTime;
	private long endTime;
	private long timeElapsed;
	
	private int numMoves;
	
	private boolean running = false;
	
	public void begin(int scoreSecond, int scoreMove)
	{
		totalScore = 0;
		startTime = System.nanoTime();
		endTime = startTime;
		timeElapsed = 0;
		scorePerSecond = scoreSecond;
		scorePerMove = scoreMove;
		numMoves = 0;
		
		running = true;
		
		this.start();
	}
	
	public void stopRunning()
	{
		running = false;
		
	}
	
	public void continueRunning()
	{
		running = true;
		
		//Reset time difference monitoring when continuing the ScoreManager.
		startTime = System.nanoTime();
		endTime = startTime;
	}
	
	public void run()
	{
		while(running)
		{
			updateTime();
			
			updateScore();
			
			try{
				Thread.sleep(50);
			}
			catch(Exception e){}
		}
	}
	
	
	private void updateTime()
	{
		endTime = System.nanoTime();
		timeElapsed += endTime - startTime;
		startTime = endTime;
	}
	
	private void updateScore()
	{
		int timeScore = timeInSeconds() * scorePerSecond;
		int moveScore = numMoves * scorePerMove;
		
		totalScore = timeScore + moveScore;
		
	}
	
	public void setRunning(boolean isRunning)
	{
		running = isRunning;
	}
	
	public int timeInSeconds()
	{
		int nanotimeToSec = 1000000000;
		return (int) (timeElapsed / nanotimeToSec);
	}
	
	public void testPrintTime()
	{
		System.out.println(timeInSeconds());
	}
}
