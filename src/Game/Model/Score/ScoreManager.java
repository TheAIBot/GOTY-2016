package Game.Model.Score;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.Timer;

import org.omg.CosNaming.NamingContextPackage.NotFound;

public class ScoreManager implements Serializable{
	
	private ScoreChangedListener scoreChangedListener;
		
	private final int delay = 1000; //1000 = 1 s
	private transient Timer clock = new Timer(delay, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateTimeScore();
		}
	});
	
	private int totalScore = 0;
	private int scorePerSecond;
	private int scorePerMove;
	private int timeElapsedInSeconds = 0;
	private int numMoves = 0;
	//Wether or not to update the number of moves and the corresponding addition to the score.
	private final boolean detectMoves;
	
	/**
	 * @param scoreSecond
	 * @param scoreMove
	 */
	public ScoreManager(int scoreSecond, int scoreMove, boolean detectMoves, ScoreChangedListener scoreListener)
	{
		this.scorePerSecond = scoreSecond;
		this.scorePerMove = scoreMove;
		this.detectMoves = detectMoves;
		this.scoreChangedListener = scoreListener;
		
	}
	
	/**
	 * Starts the manager. Can also be used after calling stop().
	 * @param scoreSecond
	 * @param scoreMove
	 */
	public void startClock()
	{
		clock.start();
	}
	
	/**
	 * Stops the ScoreManager from adding to the score when time passes and incrementMoves() or addNumMoves() are called.
	 */
	public void stopClock()
	{
		clock.stop();
	}
	
	
	/**
	 * Updates the score and increments the time count in seconds.
	 */
	private void updateTimeScore()
	{
		timeElapsedInSeconds++;
		totalScore += scorePerSecond;
		scoreChangedListener.scoreChanged(totalScore,timeElapsedInSeconds,0);
	}
	
	/**
	 * Adds some value to the total score.
	 * @param score
	 */
	public void addToScore(int score)
	{
		totalScore += score;
		scoreChangedListener.scoreChanged(totalScore,timeElapsedInSeconds,0);
	}
	
	public void incrementScore()
	{
		totalScore += 1;
		scoreChangedListener.scoreChanged(totalScore,timeElapsedInSeconds,0);
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
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, NotFound {
		in.defaultReadObject();
		clock = new Timer(delay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTimeScore();
			}
		});
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
		return timeElapsedInSeconds;
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
	
	
	//set-methods
	
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
		timeElapsedInSeconds = seconds;
	}
}

