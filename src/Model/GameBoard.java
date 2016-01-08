package Model;

import java.awt.Color;
import java.awt.Point;
<<<<<<< HEAD

import View.Screen;
=======
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.omg.CosNaming.NamingContextPackage.NotFound;

>>>>>>> refs/remotes/origin/Dev
import Control.*;
import Control.GameEngine.KeyPressListener;
import Control.GameEngine.Log;

public class GameBoard implements GameBoardMode, java.io.Serializable {
	private transient Point voidTilePosition;
	private final ArrayList<BoardChangedListener> listeners = new ArrayList<BoardChangedListener>();
	private final ArrayList<GameStateChangedListener> gameStateChangedListeners = new ArrayList<GameStateChangedListener>();
	protected Tile[] tilePlacements;
	protected GameState currentGameState;
	protected final int size;
	protected final PlayerSettings player1;

	public GameBoard(int startSize, PlayerSettings player1, PlayerSettings player2) {
		this.size = startSize;
		this.currentGameState = GameState.NOT_DECIDED_YET;
		this.player1 = player1;
	}

	public GameState getGameState() {
		return currentGameState;
	}

	public Point moveWithDirection(Point toMove, Directions direction) {
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

	public int getIndexFromPoint(Point p) {
		// x + y * width (width = size)
		return p.x + p.y * size;
	}

	public Point getPosition(int number) {
		int row = number / size;
		int col = number % size;

		return new Point(col, row);
	}

	public void GameStateChanged(GameState newGameState) {
		for (GameStateChangedListener listener : gameStateChangedListeners) {
			listener.gameStateChanged(newGameState);
		}
	}

	public void addBoardChangedListener(BoardChangedListener listener) {
		listeners.add(listener);
	}

	public void boardChanged() {
		for (BoardChangedListener listener : listeners) {
			listener.boardChanged();
		}
	}

<<<<<<< HEAD
public class GameBoard extends SuperGameBoard {
	private Point voidTilePosition;
	private Screen screen;
	
	public GameBoard(int startSize) {
		super(startSize);
		currentGameState = GameState.NOT_DECIDED_YET;
	}
	
	public GameBoard(int startSize, Screen screen) {
		super(startSize);
		this.screen = screen;
	}
	
=======
	private Point recreateTilePositions() {
		Point voidPos = null;
		for (int i = 0; i < tilePlacements.length; i++) {
			if (tilePlacements[i] != null) {
				tilePlacements[i].position = getPosition(i);
			} else {
				voidPos = getPosition(i);
			}
		}
		return voidPos;
	}

>>>>>>> refs/remotes/origin/Dev
	@Override
	public void createGame() {
		tilePlacements = new Tile[size * size];
		for (int i = 0; i < tilePlacements.length - 1; i++) {
			tilePlacements[i] = new Tile(i + 1, getPosition(i), Color.blue);
		}
		voidTilePosition = new Point(size - 1, size - 1);
	}

	@Override
	public void makeRandom() {
		randomizeGame();
	}

	@Override
	public void resetGame() {
		createGame();
		randomizeGame();
	}

	@Override
	public Tile[] getTiles() {
		return tilePlacements;
	}

	@Override
	public void keyPressed(String key) {
		Log.writeln(key + " " + player1.getDownKeyName());
		if (key.equals(player1.getDownKeyName())) {
			moveVoidTile(Directions.DOWN);
		} else if (key.equals(player1.getLeftKeyName())) {
			moveVoidTile(Directions.LEFT);
		} else if (key.equals(player1.getRightKeyName())) {
			moveVoidTile(Directions.RIGHT);
		} else if (key.equals(player1.getUpKeyName())) {
			moveVoidTile(Directions.UP);
		}
	}

	@Override
	public boolean moveVoidTile(Directions direction) {
		if (isMoveAllowed(direction)) {
			swapVoidTile(direction);
			boardChanged();
			return true;
		}
		return false;
	}

	public boolean moveVoidTileNoUpdate(Directions direction) {
		if (isMoveAllowed(direction)) {
			swapVoidTile(direction);
			return true;
		}
		return false;
	}

	@Override
	public int getSize() {
		return size;
	}

	private boolean isMoveAllowed(Directions direction) {
		switch (direction) {
		case RIGHT:
			return voidTilePosition.getX() < size - 1;
		case LEFT:
			return voidTilePosition.getX() > 0;
		case UP:
			return voidTilePosition.getY() > 0;
		case DOWN:
			return voidTilePosition.getY() < size - 1;
		default:
			throw new IllegalArgumentException();
		}
	}

	private void swapVoidTile(Directions direction) {
		moveWithDirection(voidTilePosition, direction);
		Tile tileToMove = tilePlacements[getIndexFromPoint(voidTilePosition)];
		moveWithDirection(tileToMove.position, direction.getOppositeDirection());
		moveTileIndexes(getIndexFromPoint(tileToMove.position), getIndexFromPoint(voidTilePosition));
	}

	private void moveTileIndexes(int tileAIndex, int tileBIndex) {
		Tile tileA = tilePlacements[tileAIndex];
		tilePlacements[tileAIndex] = tilePlacements[tileBIndex];
		tilePlacements[tileBIndex] = tileA;
	}

	private void randomizeGame() {
		final int RANDOM_MOVES = 100;
		for (int i = 0; i < RANDOM_MOVES; i++) {
			switch ((((int) (Math.random() * 10)) % 4)) {
			case 0:
				moveVoidTileNoUpdate(Directions.LEFT);
				break;
			case 1:
				moveVoidTileNoUpdate(Directions.RIGHT);
				break;
			case 2:
				moveVoidTileNoUpdate(Directions.UP);
				break;
			case 3:
				moveVoidTileNoUpdate(Directions.DOWN);
				break;
			}
		}
		boardChanged();
	}
<<<<<<< HEAD
	
=======

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, NotFound {
		in.defaultReadObject();
		voidTilePosition = recreateTilePositions();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

>>>>>>> refs/remotes/origin/Dev
}
