package Game.Model.Score;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.Timer;

import org.omg.CosNaming.NamingContextPackage.NotFound;

/** A score manager, used by objects for keeping and manipulating a score associated with it.
 */
public class ScoreManager implements Serializable{
	//Nessecary for assuring no crashes while saving the object
	private static final long serialVersionUID = 6046970807246670554L; 

	private ScoreChangedListener scoreChangedListener;
	
	//Timer and timer delay used by the score manager managing time-associated scores. Updates every second.
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
	//Whether or not to update the number of moves and the corresponding addition to the score.
	private final boolean detectMoves;
	
	/** Creates a new score manager. Depending on the given parameters, it changes the score faster or slower, 
	 * and add to the score when something moves. Announces a change in score to the given score changed listener.
	 * 
	 * @param scoreSecond The score to add to the total score every second passed.
	 * @param scoreMove The score to add to the total score every time something moves.
	 * @param detectMoves True/false value for if the moves should be detected.
	 * @param scoreListener A listener for listening to a change in the total score.
	 */
	public ScoreManager(int scoreSecond, int scoreMove, boolean detectMoves, ScoreChangedListener scoreListener)
	{
		this.scorePerSecond = scoreSecond;
		this.scorePerMove = scoreMove;
		this.detectMoves = detectMoves;
		this.scoreChangedListener = scoreListener;
		
	}
	
	/** Starts the score manager. Can also be used after calling stop().	
	 */
	public void startClock()
	{
		clock.start();
	}
	
	/** Stops the ScoreManager from adding to the score when time passes and incrementMoves() or addNumMoves() are called.
	 */
	public void stopClock()
	{
		clock.stop();
	}
		
	/** Updates the score and increments the time count in seconds.
	 */
	private void updateTimeScore()
	{
		timeElapsedInSeconds++;
		totalScore += scorePerSecond;
		scoreChangedListener.scoreChanged(totalScore,timeElapsedInSeconds,0);
	}
	
	/** Adds a given value to the total score, and announces the change to the score changed listeners.
	 * @param score The value to be added to the score.
	 */
	public void addToScore(int score)
	{
		totalScore += score;
		scoreChangedListener.scoreChanged(totalScore,timeElapsedInSeconds,0);
	}
	
	/** Increments the total score by 1, and announces the change to the score changed listeners.
	 */
	public void incrementScore()
	{
		totalScore += 1;
		scoreChangedListener.scoreChanged(totalScore,timeElapsedInSeconds,0);
	}
	
	/** Increments the number of moves by 1 and increases the score by the value associated with a move, if a move is detected. 
	 */
	public void incrementNumMoves()
	{
		if (detectMoves) {
			numMoves++;
			addToScore(scorePerMove);
		}
	}
	
	/** Adds the specified number of moves to the total number of moves.
	 * @param moves
	 */
	public void addNumMoves(int moves)
	{
		if (detectMoves) {
			numMoves += moves;
			addToScore(moves * scorePerMove);
		}
	}
	
	/**	Method for loading the score manager from a serialized file, for loading a saved game.
	 * @param in The inputstream of (loaded) data. 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NotFound
	 */
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
	
	/** Returns the time elapsed in seconds since the start of the score manager. 
	 * 	This does not include time while the score manager has been stopped.
	 * @return The time elapsed in secons.
	 */
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

