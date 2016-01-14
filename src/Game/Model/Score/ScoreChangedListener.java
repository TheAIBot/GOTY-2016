package Game.Model.Score;

public interface ScoreChangedListener {
	public abstract void scoreChanged(int score, int seconds, int screenIndex);
}
