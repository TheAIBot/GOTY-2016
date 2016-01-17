package Game.Model.Score;

/** A listener for changed scores, see Highscore and ScoreManager.
 */
public interface ScoreChangedListener {
	
	/** Triggers when a score is changed.
	 * @param score The new score.
	 * @param seconds The time of the score in seconds-
	 * @param screenIndex The screen index/the player with the score.
	 */
	public abstract void scoreChanged(int score, int seconds, int screenIndex);
}
