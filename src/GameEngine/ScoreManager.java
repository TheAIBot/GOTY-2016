package GameEngine;

public class ScoreManager extends Thread{

	//Score parameters
	private int totalScore;
	private int scorePerSecond;
	private int scorePerMove;
	
	//Time parameters
	private long startTime;
	private long endTime;
	private long timeElapsed;
	//Getting temporary time difference in seconds to update time score
	private int previousSeconds;
	private int timeDiffSeconds;
	
	//Move parameter
	private int numMoves;
	
	//Boolean value controlling if score manager should be running
	private boolean running = false;
	
	/**
	 * Use this method to begin (or restart) an instance of ScoreManager.
	 * If you do not wish to restart the manager, use the continue() method.
	 * @param scoreSecond
	 * @param scoreMove
	 */
	public void begin(int scoreSecond, int scoreMove)
	{
		totalScore = 0;
		startTime = System.nanoTime();
		endTime = startTime;
		timeElapsed = 0;
		previousSeconds = 0;
		timeDiffSeconds = 0;
		scorePerSecond = scoreSecond;
		scorePerMove = scoreMove;
		numMoves = 0;
		running = true;
		
		start();
	}
	
	/**
	 * Stops the manager from running.
	 */
	public void stopRunning()
	{
		//Reset time difference monitoring when continuing the ScoreManager.
		running = false;
		
	}
	
	/**
	 * Lets the manager continue to run.
	 */
	public void continueRunning()
	{
		startTime = System.nanoTime();
		endTime = startTime;
		running = true;
	}
	
	/**
	 * The main loop of ScoreManager which updates the time and score.
	 */
	public void run()
	{
		while(true)
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
			try{
				Thread.sleep(50);
			}
			catch(Exception e){}
		}
	}
	
	/**
	 * Updates the time elapsed
	 */
	private void updateTime()
	{
		if(timeDiffSeconds > 0)
			previousSeconds = getTimeElapsedSeconds();
		
		endTime = System.nanoTime();
		timeElapsed += endTime - startTime;
		startTime = endTime;
		
		timeDiffSeconds = getTimeElapsedSeconds() - previousSeconds;
		
	}
	
	/**
	 * Updates the score corresponding the the time passed and moves made
	 */
	private void updateScore()
	{
		int timeScore = timeDiffSeconds * scorePerSecond;
		int moveScore = numMoves * scorePerMove;
		
		addToScore(timeScore);;
		
	}
	
	/**
	 * Adds some value to the total score.
	 * @param score
	 */
	public void addToScore(int score)
	{
		totalScore+= score;
	}
	
	public void incrementScore()
	{
		totalScore+=1;
	}
	
	//Get-methods
	
	/**
	 * Returns the elapsed time in seconds
	 */
	public int getTimeElapsedSeconds()
	{
		int nanotimeToSec = 1000000000;
		return (int) (timeElapsed / nanotimeToSec);
	}
	
	/**
	 * Returns the total score earned.
	 */
	public int getTotalScore()
	{
		return totalScore;
	}
	/**
	 * Returns the current amount of score added per second.
	 */
	public int getScorePerSecond()
	{
		return scorePerSecond;
	}
	
	/**
	 * Returns number of moves.
	 */
	public int getNumMoves()
	{
		return numMoves;
	}
	
	/**
	 * Returns the current amount of score added per move.
	 */
	public int getScorePerMove()
	{
		return scorePerMove;
	}
	/**
	 * Return the elapsed time in nanoseconds.
	 */
	public long getTimeElapsedNano()
	{
		return timeElapsed;
	}
	
	//Set-methods
	
	/**
	 * Sets the boolean value running. If false the loop stops and will only continue running 
	 * afterwards if the method continue() is called.
	 * @param isRunning
	 */
	public void setRunning(boolean isRunning)
	{
		running = isRunning;
	}
	
	/**
	 * Sets the total score.
	 * @param score
	 */
	public void setTotalScore(int score)
	{
		totalScore = score;
	}
	
	/**
	 * Sets the score added per second.
	 * @param score
	 */
	public void setScorePerSecond(int score)
	{
		scorePerSecond = score;
	}
	
	/**
	 * Sets the score added per move.
	 * @param score
	 */
	public void setScorePerMove(int score)
	{
		scorePerMove = score;
	}
	
	/**
	 * Sets the time elapsed.
	 * @param seconds
	 */
	public void setTimeElapsedInSeconds(int seconds)
	{
		int secToNano = 1000000000;
		timeElapsed = (long)seconds * secToNano;
		
		previousSeconds = getTimeElapsedSeconds();
	}
	
	/**
	 * Sets the number of moves.
	 * @param moves
	 */
	public void setNumMoves(int moves)
	{
		numMoves = moves;
	}
	/**
	 * Increments the number of moves by 1.
	 */
	
	public void incrementMoves()
	{
		numMoves++;
	}
	/**
	 * Adds the specified number of moves to the total number of moves.
	 * @param moves
	 */
	public void addNumMoves(int moves)
	{
		numMoves+=moves;
	}
}
