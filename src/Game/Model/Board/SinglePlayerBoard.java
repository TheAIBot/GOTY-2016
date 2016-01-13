package Game.Model.Board;

import java.awt.Color;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import Game.Model.Difficulty.DifficultyCalculator;
import Game.Model.Score.ScoreChangedListener;
import Game.Model.Score.ScoreManager;
import Game.Model.Settings.GameSettings;
import Game.Model.Settings.PlayerSettings;
import Game.Control.GameEngine.Log;
import Game.Model.Board.GameModes;
import Game.View.RenderInfo;
import Game.View.Animation.AnimationInfo;
import Game.View.Animation.ToAnimateListener;

public class SinglePlayerBoard implements GameBoardMode, java.io.Serializable, ToAnimateListener, ScoreChangedListener {
	private transient Point2D.Double voidTilePosition;
	private final ArrayList<BoardChangedListener> listeners = new ArrayList<BoardChangedListener>();
	private final ArrayList<GameStateChangedListener> gameStateChangedListeners = new ArrayList<GameStateChangedListener>();
	protected Tile[] tilePlacements;
	protected GameState currentGameState;
	protected final GameSettings settings;
	protected RenderInfo renderInfo;
	protected final int playerIndex;
	private Random randomGenerator = new Random();
	private ScoreChangedListener scoreListener;
	private ScoreManager scoreManager;
	
 	public SinglePlayerBoard(GameSettings settings, int playerindex) {
		this.playerIndex = playerindex;
		this.settings = settings;
		this.renderInfo = new RenderInfo(false, settings.getGameSize());
		gameStateChanged(GameState.NOT_DECIDED_YET);
		scoreManager = new ScoreManager(1, 2, true, this);
	}
	
	public GameState getGameState(int playerIndex) {
		return currentGameState;
	}

	public Point2D.Double moveWithDirection(Tile toMove, Directions direction) {
		switch (direction) {
		case RIGHT:
			toMove.translatePosition(1, 0);
			break;
		case LEFT:
			toMove.translatePosition(-1, 0);
			break;
		case UP:
			toMove.translatePosition(0, -1);
			break;
		case DOWN:
			toMove.translatePosition(0, 1);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return toMove.getPosition();
	}
	
	public Point2D.Double moveWithDirection(Point2D.Double toMove, Directions direction) {
		switch (direction) {
		case RIGHT:
			toMove.setLocation(toMove.x + 1, toMove.y);
			break;
		case LEFT:
			toMove.setLocation(toMove.x - 1, toMove.y);
			break;
		case UP:
			toMove.setLocation(toMove.x, toMove.y - 1);
			break;
		case DOWN:
			toMove.setLocation(toMove.x, toMove.y + 1);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return toMove;
	}

	public int getIndexFromPoint(Point2D.Double p) {
		// x + y * width (width = size)
		return (int)(p.x + p.y * settings.getGameSize());
	}

	public Point2D.Double getPosition(int number) {
		int row = number / settings.getGameSize();
		int col = number % settings.getGameSize();

		return new Point2D.Double(col, row);
	}

	public void gameStateChanged(GameState newGameState) {
		setGameState(newGameState);
		for (GameStateChangedListener listener : gameStateChangedListeners) {
			listener.gameStateChanged(newGameState, playerIndex);
		}
	}

	public void addBoardChangedListener(BoardChangedListener listener) {
		listeners.add(listener);
	}
	
	public void boardChanged() {
		for (BoardChangedListener listener : listeners) {
			listener.boardChanged(playerIndex);
		}
	}

	private Point2D.Double recreateTilePositions() {
		Point2D.Double voidPos = null;
		for (int i = 0; i < tilePlacements.length; i++) {
			if (tilePlacements[i] != null) {
				tilePlacements[i].position = getPosition(i);
			} else {
				voidPos = getPosition(i);
			}
		}
		return voidPos;
	}

	@Override
	public void createGame() {
		tilePlacements = new Tile[settings.getGameSize() * settings.getGameSize()];
		for (int i = 0; i < tilePlacements.length - 1; i++) {
			int red = 		  (int)Math.round(255 / (double)((tilePlacements.length - 1)) * (i + 1));
			int green = 255 - (int)Math.round(255 / (double)((tilePlacements.length - 1)) * (i + 1));
			tilePlacements[i] = new Tile(this, i + 1, getPosition(i), new Color(red, green, 0), settings.getTileImage());
		}
		voidTilePosition = new Point2D.Double(settings.getGameSize() - 1, settings.getGameSize() - 1);
	}

	@Override
	public void makeRandom() {
		randomizeGame();
		scoreManager.startClock();
	}

	@Override
	public void resetGame() {
		createGame();
		randomizeGame();
	}

	@Override
	public Tile[] getTiles(int playerIndex) {
		return tilePlacements;
	}

	@Override
	public void keyPressed(String key) {
		PlayerSettings playerSettings = settings.getPlayers()[playerIndex];
		if (key.equals(playerSettings.getDownKeyName())) {
			if(moveVoidTile(Directions.DOWN))
			{
				scoreManager.incrementNumMoves();	
			}
		} else if (key.equals(playerSettings.getLeftKeyName())) {
			if(moveVoidTile(Directions.LEFT))
			{
				scoreManager.incrementNumMoves();	
			}
		} else if (key.equals(playerSettings.getRightKeyName())) {
			if(moveVoidTile(Directions.RIGHT))
			{
				scoreManager.incrementNumMoves();	
			}
		} else if (key.equals(playerSettings.getUpKeyName())) {
			if(moveVoidTile(Directions.UP))
			{
				scoreManager.incrementNumMoves();	
			}
		} else if (key.equals(playerSettings.getToggleColorKeyName())) {
			renderInfo.toggleRenderColor();
		} else if (key.equals(playerSettings.getCameraUpKeyName())) {
			renderInfo.addOffset(0, -1);
		} else if (key.equals(playerSettings.getCameraDownKeyName())) {
			renderInfo.addOffset(0, 1);
		} else if (key.equals(playerSettings.getCameraLeftKeyName())) {
			renderInfo.addOffset(1, 0);
		} else if (key.equals(playerSettings.getCameraRightKeyName())) {
			renderInfo.addOffset(-1, 0);
		} else if (key.equals(playerSettings.getZoomInKeyName())) {
			renderInfo.addImageScale(0.1);
		} else if (key.equals(playerSettings.getZoomOutKeyName())) {
			renderInfo.addImageScale(-0.1);
		}
		boardChanged();
		if (hasWonGame()) {
			gameStateChanged(GameState.WON);
		}
	}

	private boolean moveVoidTile(Directions direction) {
		if (isMoveAllowed(direction)) {
			swapVoidTile(direction);
			return true;
		}
		return false;
	}

	@Override
	public int getSize() {
		return settings.getGameSize();
	}

	private boolean hasWonGame()
	{
		return DifficultyCalculator.getDfficulty(tilePlacements, settings.getGameSize()) == 0;
	}
	
	private boolean isMoveAllowed(Directions direction) {
		switch (direction) {
		case RIGHT:
			return voidTilePosition.getX() < settings.getGameSize() - 1;
		case LEFT:
			return voidTilePosition.getX() > 0;
		case UP:
			return voidTilePosition.getY() > 0;
		case DOWN:
			return voidTilePosition.getY() < settings.getGameSize() - 1;
		default:
			throw new IllegalArgumentException();
		}
	}

	private void swapVoidTile(Directions direction) {
		moveWithDirection(voidTilePosition, direction);
		final Tile tileToMove = tilePlacements[getIndexFromPoint(voidTilePosition)];
		moveWithDirection(tileToMove, direction.getOppositeDirection());
		moveTileIndexes(getIndexFromPoint(tileToMove.getPosition()), getIndexFromPoint(voidTilePosition));
	}

	private void moveTileIndexes(int tileAIndex, int tileBIndex) {
		final Tile tileA = tilePlacements[tileAIndex];
		tilePlacements[tileAIndex] = tilePlacements[tileBIndex];
		tilePlacements[tileBIndex] = tileA;
	}

	private void randomizeGame() {
		final int NumberOfDirections = 4;
		do {
			for (int i = 0; i < settings.getGameSize() * 100; i++) {
				switch (randomGenerator.nextInt(NumberOfDirections)) {
				case 0:
					moveVoidTile(Directions.LEFT);
					break;
				case 1:
					moveVoidTile(Directions.RIGHT);
					break;
				case 2:
					moveVoidTile(Directions.UP);
					break;
				case 3:
					moveVoidTile(Directions.DOWN);
					break;
				}
			}
		} while (settings.getDifficultyLevel() != DifficultyCalculator.getDifficultyLevel(tilePlacements, settings.getGameSize()) ||
				   DifficultyCalculator.getDfficulty(tilePlacements, settings.getGameSize()) == 0);
	}
	
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

	@Override
	public String[] getKeysToSubscribeTo(int playerIndex) {
		PlayerSettings playerSettings = settings.getPlayers()[playerIndex];
		return new String[] {
			playerSettings.getUpKeyName(),
			playerSettings.getDownKeyName(),
			playerSettings.getLeftKeyName(),
			playerSettings.getRightKeyName(),
			playerSettings.getToggleColorKeyName(),
			
			playerSettings.getCameraUpKeyName(),
			playerSettings.getCameraDownKeyName(),
			playerSettings.getCameraLeftKeyName(),
			playerSettings.getCameraRightKeyName(),
			
			playerSettings.getZoomInKeyName(),
			playerSettings.getZoomOutKeyName()
		};		
	}

	@Override
	public RenderInfo getRenderInfo(int playerIndex)
	{
		return renderInfo;
	}

	@Override
	public void toAnimate(AnimationInfo tile) {
		renderInfo.toAnimate.add(tile);
		
	}

	@Override
	public int getNumberOfPlayers() {
		switch (settings.getGameMode()) {
		case SINGLE_PLAYER:
			return 1;
		case MULTI_PLAYER:
			return 2;
		default:
			throw new IllegalArgumentException();
		}
	}

	public void setRandom(Random random)
	{
		randomGenerator = random;
	}

	
	@Override
	public void addGameStateChangedListener(GameStateChangedListener listener) {
		gameStateChangedListeners.add(listener);		
	}
	
	public void setGameState(GameState newGameState)
	{
		currentGameState = newGameState;
	}

	@Override
	public void addScoreChangedListener(ScoreChangedListener listener) {
		scoreListener = listener;
	}

	@Override
	public void scoreChanged(int score, int seconds, int screenIndex) {
		scoreListener.scoreChanged(score, seconds, playerIndex);
		
	}
}
