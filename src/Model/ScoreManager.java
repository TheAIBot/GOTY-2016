package Model;

import java.io.Serializable;
import javax.swing.Timer;
import java.awt.event.*;

public class ScoreManager implements Serializable{
	
	int delay = 1000; //1000 ms = 1 s
	
	ActionListenerSerialize task = new ActionListenerSerialize();
	
	Timer clock = new Timer(delay, task);
	
	//Score parameters
	private int totalScore;
	private int scorePerSecond;
	private int scorePerMove;
	
	//Time elapsed in seconds
	private int timeInSeconds;
	
	//Move parameter
	private int numMoves;
	//Wether or not to update the number of moves and the corresponding addition to the score.
	boolean detectMoves;
	
	/**
	 * 
	 * @param scoreSecond
	 * @param scoreMove
	 */
	public ScoreManager(int scoreSecond, int scoreMove)
	{
		totalScore = 0;
		scorePerSecond = scoreSecond;
		scorePerMove = scoreMove;
		numMoves = 0;
		detectMoves = true;
		timeInSeconds = 0;
		
	}
	
	/**
	 * Starts the manager. Can also be used after calling stop().
	 * @param scoreSecond
	 * @param scoreMove
	 */
	public void begin()
	{
		detectMoves = false;
		clock.start();
		
	}
	
	/**
	 * Stops the ScoreManager from adding to the score when time passes and incrementMoves() or addNumMoves() are called.
	 */
	public void stop()
	{
		detectMoves = false;
		clock.stop();
	}
	
	
	/**
	 * Updates the score and increments the time count in seconds.
	 */
	private void updateTimeScore()
	{
		timeInSeconds++;
		totalScore+=scorePerSecond;
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
	
	public void incrementNumMoves()
	{
		if (detectMoves) {
			numMoves++;
			addToScore(scorePerMove);
		}
	}
	
	/**
	 * Adds the specified number of moves to the total number of moves.
	 * @param moves
	 */
	public void addNumMoves(int moves)
	{
		if (detectMoves) {
			numMoves += moves;
			addToScore(moves * scorePerMove);
		}
		
	}
	
	//Get-methods
	
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
	
	public int getTimeElapsedSeconds()
	{
		return timeInSeconds;
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
		timeInSeconds = seconds;
	}
	
	/**
	 * Increments the number of moves by 1.
	 */

	private class ActionListenerSerialize implements ActionListener, Serializable
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			updateTimeScore();
			
		}
		
	}
}

