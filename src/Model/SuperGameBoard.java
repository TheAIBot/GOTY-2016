package Model;

import java.awt.Point;
import java.util.ArrayList;

import Control.*;

public abstract class SuperGameBoard {
	private final ArrayList<BoardChangedListener> listeners = new ArrayList<BoardChangedListener>();
	private final ArrayList<GameStateChangedListener> gameStateChangedListeners;
	protected Tile[] tilePlacements;
<<<<<<< HEAD
	public final int size;
	
	public SuperGameBoard(int startSize)
	{
=======
	protected GameState currentGameState;
	protected final int size;

	public SuperGameBoard(int startSize) {
>>>>>>> refs/remotes/origin/Dev
		gameStateChangedListeners = new ArrayList<GameStateChangedListener>();
		size = startSize;
	}

	public abstract void createGame();

	public abstract void makeRandom();

	public abstract void resetGame();

	public abstract Tile[] getTiles();

	public abstract boolean moveVoidTile(Directions direction);

	public abstract int getSize();

	public GameState getGameState() {
		return currentGameState;
	}

	protected Point moveWithDirection(Point toMove, Directions direction) {
		switch (direction) {
		case RIGHT:
			toMove.translate(1, 0);
			break;
		case LEFT:
			toMove.translate(-1, 0);
			break;
		case UP:
			toMove.translate(0, -1);
			break;
		case DOWN:
			toMove.translate(0, 1);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return toMove;
	}

	protected int getIndexFromPoint(Point p) {
		// x + y * width (width = size)
		return p.x + p.y * size;
	}

	protected Point getPosition(int number) {
		int row = number / size;
		int col = number % size;

		return new Point(col, row);
	}

	protected void GameStateChanged(GameState newGameState) {
		for (GameStateChangedListener listener : gameStateChangedListeners) {
			listener.gameStateChanged(newGameState);
		}
	}

	public void addBoardChangedListener(BoardChangedListener listener) {
		listeners.add(listener);
	}

	protected void boardChanged() {
		for (BoardChangedListener listener : listeners) {
			listener.boardChanged();
		}
	}

}
