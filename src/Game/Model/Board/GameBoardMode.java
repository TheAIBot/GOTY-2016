package Game.Model.Board;

import Game.Control.Sound.PlaySoundListener;
import Game.Model.Score.ScoreChangedListener;
import Game.View.RenderInfo;

public interface GameBoardMode {

	public void createGame();

	public void makeRandom();

	public void resetGame();

	public Tile[] getTiles(int playerIndex);

	public int getSize();

	public GameState getGameState(int playerIndex);

	public void addBoardChangedListener(BoardChangedListener listener);
	
	public void addGameStateChangedListener(GameStateChangedListener listener);
	
	public void addScoreChangedListener(ScoreChangedListener listener);
	
	public void addPlaySoundListener(PlaySoundListener listener);
	
	public void keyPressed(String key);
	
	public void pause();
	
	public void unpause();
	
	public void Stop();
	
	public String[] getKeysToSubscribeTo(int playerIndex);
	
	public RenderInfo getRenderInfo(int playerIndex);
	
	public int getNumberOfPlayers();
}
