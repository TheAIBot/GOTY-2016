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
import Game.Model.Animation.AnimationInfo;
import Game.Model.Animation.ToAnimateListener;
import Game.Model.Difficulty.DifficultyCalculator;
import Game.Model.Resources.ResourceAudio;
import Game.Model.Resources.ResourceImages;
import Game.Model.Score.ScoreChangedListener;
import Game.Model.Score.ScoreManager;
import Game.Model.Settings.GameSettings;
import Game.Model.Settings.PlayerSettings;
import Game.View.RenderInfo;

public class SinglePlayerBoard implements java.io.Serializable, ToAnimateListener, ScoreChangedListener, PlaySoundListener {
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
	
	private final double ZOOM_INCREMENTATION = 0.02; //The constant for how much the image scaling should change, when one zooms in or out.

	/**
	 * @param settings the settings this board should use
	 * @param playerindex the playerindex this board has
	 */
	public SinglePlayerBoard(GameSettings settings, int playerindex) {
		this.playerIndex = playerindex;
		this.settings = settings;
		this.renderInfo = new RenderInfo(false, settings.getGameSize());
		//when the game starts the game state is changed
		gameStateChanged(GameState.NOT_DECIDED_YET);
		scoreManager = new ScoreManager(1, 2, true, this);
	}
	
	/**
	 * returns the current game state
	 */
	public GameState getGameState() {
		return currentGameState;
	}

	/**
	 * Moves a tile according to the direction given
	 * @param toMove the tile to move
	 * @param direction the direction to move the tile in
	 * @return returns the new position
	 */
	public Point2D.Double moveWithDirection(Tile toMove, Directions direction) {
		toMove.translatePosition(direction.translateX, direction.translateY);
		return toMove.getGoingTowardsPosition();
	}

	/**
	 * Moves the point according to the direction given
	 * @param toMove the point to translate
	 * @param direction the direction to move the point in
	 * @return the new translated position
	 */
	public void moveWithDirection(Point2D.Double toMove, Directions direction) {
		toMove.setLocation(toMove.x + direction.translateX, toMove.y + direction.translateY);
	}

	/**converts a point into a number by using the formula x + y * boardSize
	 * @param p the point to convert
	 * @return the index in the Tile array for the given position
	 */
	public int getIndexFromPoint(Point2D.Double p) {
		return (int) (p.x + p.y * settings.getGameSize());
	}

	/**converts a number to a point
	 * @param number the number to convert
	 * @return the position of the Tile at the given number
	 */
	public Point2D.Double getPosition(int number) {
		//this is the opposite formula of getIndexFromPoint
		//as methods to go both ways in needed
		int row = number / settings.getGameSize();
		int col = number % settings.getGameSize();

		return new Point2D.Double(col, row);
	}

	/**
	 * changes the gameState of this board and sends an event to all its listeners
	 * @param newGameState the new GameState
	 */
	public void gameStateChanged(GameState newGameState) {
		setGameState(newGameState);
		for (GameStateChangedListener listener : gameStateChangedListeners) {
			listener.gameStateChanged(newGameState, playerIndex);
		}
	}

	/**
	 * used to subscribe to board changed events
	 */
	public void addBoardChangedListener(BoardChangedListener listener) {
		listeners.add(listener);
	}

	/**
	 * sends an event to all its listeners about the board changed
	 */
	public void boardChanged() {
		for (BoardChangedListener listener : listeners) {
			listener.boardChanged(playerIndex);
		}
	}

	/**
	 * recreates all tile positions from the position in the tilePlacements array
	 * @return the void tile position
	 */
	private Point2D.Double recreateTilePositions() {
		Point2D.Double voidPos = null;
		for (int i = 0; i < tilePlacements.length; i++) {
			if (tilePlacements[i] != null) {
				tilePlacements[i].goingTowardsPosition = getPosition(i);
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
	public void createGame() {
		tilePlacements = new Tile[settings.getGameSize() * settings.getGameSize()];
		for (int i = 0; i < tilePlacements.length - 1; i++) {
			//the tile that has the number 1 has the most green  color
			//the tile with the biggest number has the most red color
			//the two calculations below uses a tiles number to calculate the tiles color
			//according to the two rules above.
			//255 is the max value a color can have as it's saved as a byte
			int red = 		  (int) Math.round(255 / (double) ((tilePlacements.length - 1)) * (i + 1));
			int green = 255 - (int) Math.round(255 / (double) ((tilePlacements.length - 1)) * (i + 1));
			tilePlacements[i] = new Tile(this, 
										 i + 1, 
										 getPosition(i), 
										 new Color(red, green, 0), 
										 settings.getTileImage());
		}
		//the void tile always has the last position on the board
		voidTilePosition = new Point2D.Double(settings.getGameSize() - 1, settings.getGameSize() - 1);
	}

	/** Sets up the default board layout as described in the basic assignment
	 */
	public void defaultGame() {
		tilePlacements[0].setNumber(2);
		tilePlacements[1].setNumber(3);
		tilePlacements[2].setNumber(1);
	}

	/** Gets all the tiles of the board.
	 */
	public Tile[] getTiles() {
		return tilePlacements;
	}

	/**
	 * Triggered by a key press on the keyboard. 
	 * Handles the movement of the voidTile and camera, as well as the zooming action of the camera.
	 */
	public void keyPressed(String key) {
		PlayerSettings playerSettings = settings.getPlayers()[playerIndex];
		// --- Movement controls
		//if the move is possible then the boards state has to be updated
		//because it can have changed
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
		} else if (key.equals(playerSettings.getToggleColorKeyName())) {
			renderInfo.toggleRenderColor();
			//The camera controls.
			//The movement of the camera. Add an offset to the display in renderInfo,
			//equal to a tile movement in a given direction.
		} else if (key.equals(playerSettings.getCameraUpKeyName())) {
			renderInfo.addOffset(0, -1);
		} else if (key.equals(playerSettings.getCameraDownKeyName())) {
			renderInfo.addOffset(0, 1);
		} else if (key.equals(playerSettings.getCameraLeftKeyName())) {
			renderInfo.addOffset(1, 0);
		} else if (key.equals(playerSettings.getCameraRightKeyName())) {
			renderInfo.addOffset(-1, 0);
			//The zoom control. Works by adding the constant zoom Incrementation value to the scaling in the graphical display.
		} else if (key.equals(playerSettings.getZoomInKeyName())) {
			renderInfo.addImageScale(ZOOM_INCREMENTATION);
		} else if (key.equals(playerSettings.getZoomOutKeyName())) {
			renderInfo.addImageScale(-ZOOM_INCREMENTATION);
		}
		boardChanged();
	}

	/**
	 * updates the boards state after a valid move have been made
	 */
	private void updateBoardStateAfterMove() {
		scoreManager.incrementNumMoves();
		playSound(ResourceAudio.TILE_MOVED_SOUND);
		
		//if the game is now won then the game state of this board changes
		if (hasWonGame()) {
			gameStateChanged(GameState.WON);
		}
	}

	/**
	 * moves the void tile on the board if possible in the given direction
	 * @param direction the direction to move the void tile in
	 * @return returns true if it was possible to move the void tile in the given direction
	 */
	public boolean moveVoidTile(Directions direction) {
		if (isMoveAllowed(direction)) {
			swapVoidTile(direction);
			return true;
		}
		return false;
	}

	/**
	 * gets the size of the board
	 */
	public int getSize() {
		return settings.getGameSize();
	}

	/**
	 * checks wether the board is solved or not
	 * @return
	 */
	private boolean hasWonGame() {
		//if the difficulty is 0 then that means that the board has been solves
		//as all tiles are where they are supposed to be
		return DifficultyCalculator.getDifficulty(tilePlacements, settings.getGameSize()) == 0;
	}

	/**
	 * Checks if the move is valid
	 * @param direction
	 * @return
	 */
	private boolean isMoveAllowed(Directions direction) {
		//A move is only allowed when the game is not paused and while the game is stil running
		if (!settings.isPaused() && isRunning) {
			//border check
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
	 * @param direction the direction to move the void tile in
	 */
	private void swapVoidTile(Directions direction) {
		//move the void tile
		moveWithDirection(voidTilePosition, direction);
		//now move the tile that has the same position as the void tile
		final Tile tileToMove = tilePlacements[getIndexFromPoint(voidTilePosition)];
		//move the position of the tile that the void tile in on top of
		moveWithDirection(tileToMove, direction.getOppositeDirection());
		//move the void tiles and the tiles positions in the tilePlacements array so they are still sorted
		moveTileIndexes(getIndexFromPoint(tileToMove.getGoingTowardsPosition()), getIndexFromPoint(voidTilePosition));
	}

	/**
	 * switches two tiles in the tilePlacements array
	 * @param index of the first tile to move
	 * @param index of the second tile to move
	 */
	private void moveTileIndexes(int tileAIndex, int tileBIndex) {
		final Tile tileA = tilePlacements[tileAIndex];
		tilePlacements[tileAIndex] = tilePlacements[tileBIndex];
		tilePlacements[tileBIndex] = tileA;
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, NotFound {
		in.defaultReadObject();
		//Point can't be serialized so when the class is remade the points also has to be ramde
		voidTilePosition = recreateTilePositions();
		//A bufferedImage is not serializable so it was saved manually
		//and now it's loaded manually
		Tile.setTileImage(ImageIO.read(in));
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		//BufferedImage can't be serialized so has to be manually saved
		ImageIO.write(Tile.getTileImage(), ResourceImages.ACCEPTED_EXTENSION, out);
	}

	
	/**
	 * pauses the game
	 * @Override
	 */
	public void pause() {
		scoreManager.stopClock();
	}

	/**
	 * stops the game which prevents the game from being unpaused
	 * @Override
	 */
	public void Stop() {
		pause();
		isRunning = false;
	}

	/**
	 * unpauses the game
	 * @Override
	 */
	public void unpause() {
		//can only start the score clock if there is a GUI to show it in
		//else the clock serves no purpose
		if (isRunning && settings.hasGUI()) {
			scoreManager.startClock();
		}
	}

	/**
	 * Gets the keys this boards player needs to play the game with
	 * @Override
	 */
	public String[] getKeysToSubscribeTo(int playerIndex) {
		PlayerSettings playerSettings = settings.getPlayers()[playerIndex];
		return new String[] { playerSettings.getUpKeyName(), 
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

	public RenderInfo getRenderInfo() {
		return renderInfo;
	}

	/**
	 * Adds the specified tile to an animation
	 * @Override
	 */
	public void toAnimate(AnimationInfo tile) {
		renderInfo.toAnimate.add(tile);
	}

	public void addGameStateChangedListener(GameStateChangedListener listener) {
		gameStateChangedListeners.add(listener);
	}

	public void setGameState(GameState newGameState) {
		currentGameState = newGameState;
	}

	public void addScoreChangedListener(ScoreChangedListener listener) {
		scoreListener = listener;
	}

	public void scoreChanged(int score, int seconds, int screenIndex) {
		scoreListener.scoreChanged(score, seconds, playerIndex);

	}

	public int getScore() {
		return scoreManager.getTotalScore();
	}
	
	public void playSound(String name) {
		for (PlaySoundListener playSoundListener : playSoundListeners) {
			playSoundListener.playSound(name);
		}
	}
	
	
	public void addPlaySoundListener(PlaySoundListener listener) {
		playSoundListeners.add(listener);
	}
}
