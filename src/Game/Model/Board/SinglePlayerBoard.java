package Game.Model.Board;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import Game.Control.Sound.PlaySoundListener;
import Game.Model.Difficulty.DifficultyCalculator;
import Game.Model.Resources.ResourceAudio;
import Game.Model.Resources.ResourceImages;
import Game.Model.Score.ScoreChangedListener;
import Game.Model.Score.ScoreManager;
import Game.Model.Settings.GameSettings;
import Game.Model.Settings.PlayerSettings;
import Game.View.RenderInfo;
import Game.View.Animation.AnimationInfo;
import Game.View.Animation.ToAnimateListener;

public class SinglePlayerBoard implements GameBoardMode, java.io.Serializable, ToAnimateListener, ScoreChangedListener, PlaySoundListener {
	private static final long serialVersionUID = 8970617298465598945L;
	private transient Point2D.Double voidTilePosition;
	
	//ArrayLists for different listeners
	private final ArrayList<BoardChangedListener> listeners = new ArrayList<BoardChangedListener>();
	private final ArrayList<GameStateChangedListener> gameStateChangedListeners = new ArrayList<GameStateChangedListener>();
	private final ArrayList<PlaySoundListener> playSoundListeners = new ArrayList<PlaySoundListener>();
	
	private Tile[] tilePlacements;
	private GameState currentGameState;
	private final GameSettings settings;
	private RenderInfo renderInfo;
	private final int playerIndex;
	private Random randomGenerator = new Random();
	private ScoreChangedListener scoreListener;
	private ScoreManager scoreManager;
	private boolean isRunning = true;

	/**
	 * @param settings
	 * @param playerindex
	 */
	public SinglePlayerBoard(GameSettings settings, int playerindex) {
		this.playerIndex = playerindex;
		this.settings = settings;
		this.renderInfo = new RenderInfo(false, settings.getGameSize());
		gameStateChanged(GameState.NOT_DECIDED_YET);
		scoreManager = new ScoreManager(1, 2, true, this);
	}

	@Override
	public GameState getGameState(int playerIndex) {
		return currentGameState;
	}

	/**
	 * Moves the tile the voidTile moved into to the voidTile's previous position
	 * @param toMove
	 * @param direction
	 * @return
	 */
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

	/**
	 * Move the point at which the void tile is located
	 * @param toMove
	 * @param direction
	 * @return
	 */
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

	/**
	 * @param p
	 * @return the index in the Tile array for the given position
	 */
	public int getIndexFromPoint(Point2D.Double p) {
		return (int) (p.x + p.y * settings.getGameSize());
	}

	/**
	 * @param number
	 * @return the position of the Tile at the given index number
	 */
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

	@Override
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

	/**
	 * Creates the game by setting up the standard grid of tiles (the puzzle
	 * with solved layout) and assigning the relevant variables (Tile array,
	 * colors and BufferedImage)
	 */
	@Override
	public void createGame() {
		tilePlacements = new Tile[settings.getGameSize() * settings.getGameSize()];
		for (int i = 0; i < tilePlacements.length - 1; i++) {
			int red = (int) Math.round(255 / (double) ((tilePlacements.length - 1)) * (i + 1));
			int green = 255 - (int) Math.round(255 / (double) ((tilePlacements.length - 1)) * (i + 1));
			tilePlacements[i] = new Tile(this, i + 1, getPosition(i), new Color(red, green, 0), settings.getTileImage());
		}
		voidTilePosition = new Point2D.Double(settings.getGameSize() - 1, settings.getGameSize() - 1);
	}

	@Override
	public void makeRandom() {
		if (settings.isRandomized()) {
			randomizeGame();
		} else {
			defaultGame();
		}
	}

	@Override
	public void resetGame() {
		createGame();
		randomizeGame();
	}

	/**
	 * Sets up the default board layout as described in the basis assignment
	 */
	public void defaultGame() {
		tilePlacements[0].setNumber(2);
		tilePlacements[1].setNumber(3);
		tilePlacements[2].setNumber(1);
		// moveWithDirection(tilePlacements[0], Directions.RIGHT);
		// moveWithDirection(tilePlacements[1], Directions.LEFT);
		// moveWithDirection(tilePlacements[2], Directions.RIGHT);
		// moveWithDirection(tilePlacements[2], Directions.RIGHT);
		// moveTileIndexes(1, 2);
		// moveTileIndexes(0, 1);
	}

	@Override
	public Tile[] getTiles(int playerIndex) {
		return tilePlacements;
	}

	/**
	 * Triggered by a key press on the keyboard. Handles the movement of the
	 * voidTile and camera
	 */
	@Override
	public void keyPressed(String key) {
		PlayerSettings playerSettings = settings.getPlayers()[playerIndex];
		// --- Movement controls
		if (key.equals(playerSettings.getDownKeyName())) {
			if (moveVoidTile(Directions.DOWN)) {
				updateBoardStateAfterMove();
			}
		} else if (key.equals(playerSettings.getLeftKeyName())) {
			if (moveVoidTile(Directions.LEFT)) {
				updateBoardStateAfterMove();
			}
		} else if (key.equals(playerSettings.getRightKeyName())) {
			if (moveVoidTile(Directions.RIGHT)) {
				updateBoardStateAfterMove();
			}
		} else if (key.equals(playerSettings.getUpKeyName())) {
			if (moveVoidTile(Directions.UP)) {
				updateBoardStateAfterMove();
			}
			// --- Camera controls
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
			renderInfo.addImageScale(0.02);
		} else if (key.equals(playerSettings.getZoomOutKeyName())) {
			renderInfo.addImageScale(-0.02);
		}
		boardChanged();
	}

	private void updateBoardStateAfterMove() {
		scoreManager.incrementNumMoves();
		playSound(ResourceAudio.TILE_MOVED_SOUND);

		if (hasWonGame()) {
			gameStateChanged(GameState.WON);
		}
	}

	public boolean moveVoidTile(Directions direction) {
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

	private boolean hasWonGame() {
		return DifficultyCalculator.getDfficulty(tilePlacements, settings.getGameSize()) == 0;
	}

	/**
	 * Checks if the move is going outside the borad
	 * @param direction
	 * @return
	 */
	private boolean isMoveAllowed(Directions direction) {
		if (!settings.isPaused() && isRunning) {
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
		} else {
			return false;
		}
	}

	/**
	 * Swaps the position of the void tile with the tile it moves into
	 * @param direction
	 */
	private void swapVoidTile(Directions direction) {
		moveWithDirection(voidTilePosition, direction);
		final Tile tileToMove = tilePlacements[getIndexFromPoint(voidTilePosition)];
		moveWithDirection(tileToMove, direction.getOppositeDirection());
		moveTileIndexes(getIndexFromPoint(tileToMove.getPosition()), getIndexFromPoint(voidTilePosition));
	}

	/**
	 * Updates the tile indexes in the array
	 * @param tileAIndex
	 * @param tileBIndex
	 */
	private void moveTileIndexes(int tileAIndex, int tileBIndex) {
		final Tile tileA = tilePlacements[tileAIndex];
		tilePlacements[tileAIndex] = tilePlacements[tileBIndex];
		tilePlacements[tileBIndex] = tileA;
	}

	/**
	 * Randomizes the game by moving the voidTile in random directions a number
	 * of times determined by the difficulty
	 */
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
			boardChanged();
		} while (settings.getDifficultyLevel() != DifficultyCalculator.getDifficultyLevel(tilePlacements, settings.getGameSize()) || DifficultyCalculator.getDfficulty(tilePlacements, settings.getGameSize()) == 0);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, NotFound {
		in.defaultReadObject();
		voidTilePosition = recreateTilePositions();
		Tile.setTileImage(ImageIO.read(in));
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		ImageIO.write(Tile.getTileImage(), ResourceImages.ACCEPTED_EXTENSION, out);
	}

	@Override
	public void pause() {
		scoreManager.stopClock();
	}

	@Override
	public void Stop() {
		pause();
		isRunning = false;
	}

	@Override
	public void unpause() {
		if (isRunning) {
			scoreManager.startClock();
		}
	}

	/**
	 * Subscribe to the keys specified in the settings so that the gameBoard can
	 * get input from KeyEvents from these keys
	 */
	@Override
	public String[] getKeysToSubscribeTo(int playerIndex) {
		PlayerSettings playerSettings = settings.getPlayers()[playerIndex];
		return new String[] { playerSettings.getUpKeyName(), playerSettings.getDownKeyName(), playerSettings.getLeftKeyName(), playerSettings.getRightKeyName(), playerSettings.getToggleColorKeyName(),

				playerSettings.getCameraUpKeyName(), playerSettings.getCameraDownKeyName(), playerSettings.getCameraLeftKeyName(), playerSettings.getCameraRightKeyName(),

				playerSettings.getZoomInKeyName(), playerSettings.getZoomOutKeyName() };
	}

	@Override
	public RenderInfo getRenderInfo(int playerIndex) {
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

	public void setRandom(Random random) {
		randomGenerator = random;
	}

	@Override
	public void addGameStateChangedListener(GameStateChangedListener listener) {
		gameStateChangedListeners.add(listener);
	}

	public void setGameState(GameState newGameState) {
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

	public int getScore() {
		return scoreManager.getTotalScore();
	}

	@Override
	public void playSound(String name) {
		for (PlaySoundListener playSoundListener : playSoundListeners) {
			playSoundListener.playSound(name);
		}
	}

	@Override
	public void addPlaySoundListener(PlaySoundListener listener) {
		playSoundListeners.add(listener);
	}
}
