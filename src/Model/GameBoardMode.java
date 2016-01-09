package Model;

import Model.Directions;
import Control.GameEngine.GameEngine;
import Control.GameEngine.KeyPressListener;

public interface GameBoardMode {

	public abstract void createGame();

	public abstract void makeRandom();

	public abstract void resetGame();

	public abstract Tile[] getTiles();

	public abstract boolean moveVoidTile(Directions direction);

	public abstract int getSize();

	public abstract GameState getGameState();

	public abstract void addBoardChangedListener(BoardChangedListener gameEngine);
	
	public abstract void keyPressed(String key);
	
	public abstract void pause();
	
	public abstract void restart();
	
	public abstract String[] getKeysToSubscribeTo();
	
	public abstract RenderInfo getRenderInfo();
}
