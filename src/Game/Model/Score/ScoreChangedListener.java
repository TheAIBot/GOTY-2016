package Game.Model.Score;

import javax.swing.plaf.metal.OceanTheme;

public interface ScoreChangedListener {
	public abstract void scoreChanged(int score, int seconds, int screenIndex);
}
