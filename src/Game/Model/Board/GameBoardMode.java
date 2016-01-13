package Game.Model.Board;

import Game.Model.Score.ScoreChangedListener;
import Game.View.RenderInfo;

public interface GameBoardMode {

	public abstract void createGame();

	public abstract void makeRandom();

	public abstract void resetGame();

	public abstract Tile[] getTiles(int playerIndex);

	public abstract int getSize();

	public abstract GameState getGameState(int playerIndex);

	public abstract void addBoardChangedListener(BoardChangedListener listener);
	
	public abstract void addGameStateChangedListener(GameStateChangedListener listener);
	
	public abstract void addScoreChangedListener(ScoreChangedListener listener);
	
	public abstract void keyPressed(String key);
	
	public abstract void pause();
	
	public abstract void unpause();
	
	public abstract String[] getKeysToSubscribeTo(int playerIndex);
	
	public abstract RenderInfo getRenderInfo(int playerIndex);
	
	public abstract int getNumberOfPlayers();
}
