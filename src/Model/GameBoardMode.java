package Model;

import Control.Directions;
import Control.GameEngine.GameEngine;

public interface GameBoardMode {

	public abstract void createGame();

	public abstract void makeRandom();

	public abstract void resetGame();

	public abstract Tile[] getTiles();

	public abstract boolean moveVoidTile(Directions direction);

	public abstract int getSize();

	public abstract GameState getGameState();

	public abstract void addBoardChangedListener(BoardChangedListener gameEngine);
}
