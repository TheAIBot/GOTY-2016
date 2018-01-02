package Game.Model.Board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import Game.Control.GameEngine.Log;
import Game.Control.Sound.PlaySoundListener;
import Game.Model.Difficulty.DifficultyCalculator;
import Game.Model.Score.Highscore;
import Game.Model.Score.ScoreChangedListener;
import Game.Model.Settings.GameSettings;
import Game.View.RenderInfo;

/**
 * Class used to maintain and manage multiple player boards. This class uses
 * most of the same principles as the SinglePlayerBoard class. For more
 * information about specific methods see the description in SinglePlayerBoard.
 */
public class MultiPlayerBoard implements GameStateChangedListener,
		ScoreChangedListener, PlaySoundListener, Serializable, BoardChangedListener {
	private static final long serialVersionUID = 1474947852904108736L;
	private final ArrayList<GameStateChangedListener> gameStateChangedListeners = new ArrayList<GameStateChangedListener>();
	private final ArrayList<PlaySoundListener> playSoundListeners = new ArrayList<PlaySoundListener>();
	private final ArrayList<BoardChangedListener> boardChangedListeners = new ArrayList<BoardChangedListener>();
	private final SinglePlayerBoard[] boards;
	private ScoreChangedListener scoreListener;
	private GameSettings settings;
	
	/**
	 * Creates a multiplayerBoard that allows multiple players to play the game
	 * Therefor create an array of SinglePlayerBoards.
	 * @param settings the settings the board should use
	 * @param playerCount the amount of playerss
	 */
	public MultiPlayerBoard(GameSettings settings, int playerCount) {
		this.settings = settings;
		this.boards = new SinglePlayerBoard[playerCount];
		// a multiplayerboard consists of multiple SinglePlayerBoards
		// so there are initialised here in the constructor with the same settings
		// as all boards should start out the same or else it wouldn't be fair
		for (int i = 0; i < boards.length; i++) {
			boards[i] = new SinglePlayerBoard(settings, i);
		}
	}

	/**
	 * creates the board
	 * @Override
	 */
	public void createGame() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].createGame();
		}
	}

	/** randomizes the board according to the GameStettings
	 * @Override
	 */
	public void makeRandom() {
		if (settings.isRandomized()) {
			randomizeGame();
		} else {
			defaultGame();
		}
	}
	
	/**
	 * Randomizes the game using the same algorithm as in the SinglePlayerBoard class.
	 * The difference below is that the multiple player boards are taken into  account.
	 * The game is randomized the same way for both players.
	 */
	private void randomizeGame()
	{		
		//The max difficulty of a board the same size as this board.
		final double maxDifficulty = DifficultyCalculator.getMaxDifficulty(settings.getGameSize());
		double difficultyInPercent; //The difficulty of the board in percents.
		Random randomGenerator = new Random();
		boolean firstRender = true;
		final Directions[] directions = new Directions[] {Directions.LEFT, Directions.RIGHT, Directions.UP, Directions.DOWN};
		do {
			Directions direction; //The direction that the void tile should move.
			
			//It moves the void tile in a random direction 100 times the game board size
			//before it checks if the difficulty level is attained.
			//This is not done for every move, as it requires to many calculations per time it is done, 
			//and would make the randomization process take to long.
			//It is at the same time not checked every a 1000 times the game board size, 
			//as it makes it almost impossible to attain a board of a lower difficulty.	
			for (int i = 0; i < settings.getGameSize() * 100; i++) {
				direction = directions[randomGenerator.nextInt(directions.length)];
				//It moves the nullTile on every board in the same direction, so the same board layout is attained on every board, 
				//when the randomization is finished.
				for (int j = 0; j < boards.length; j++) {
					boards[j].moveVoidTile(direction);
				}				
			}
			//only needed to ask for render once as the animation will
			//update the display until there is nothing to animate
			//which is when this method is done running
			if (firstRender) {
				for (int i = 0; i < boards.length; i++) {
					boardChanged(i);
				}
				firstRender = false;
			}
			
			difficultyInPercent = DifficultyCalculator.getDifficultyPercentage(getTiles(0), settings.getGameSize(), maxDifficulty);
		
		} while (settings.getDifficultyLevel() != DifficultyCalculator.getDifficultyLevel(difficultyInPercent)
				 || difficultyInPercent == 0);
	}

	/**
	 * Creates a default game as specified in the basic game requirements
	 */
	private void defaultGame() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].defaultGame();
		}
	}

	/**
	 * get tiles from the specified board
	 */
	public Tile[] getTiles(int playerIndex) {
		return boards[playerIndex].getTiles();
	}

	/**
	 * gets the size of the board
	 */
	public int getSize() {
		return boards[0].getSize();
	}

	public GameState getGameState(int playerIndex) {
		return boards[playerIndex].getGameState();
	}

	/**
	 * Makes the SinglePlayerBoards able to listen for the board to change
	 */
	public void addBoardChangedListener(BoardChangedListener listener) {
		boardChangedListeners.add(listener);
		for (int i = 0; i < boards.length; i++) {
			boards[i].addBoardChangedListener(this);
		}
	}

	/**
	 * Handles keypresses to all boards
	 */
	public void keyPressed(String key) {
		for (int i = 0; i < boards.length; i++) {
			boards[i].keyPressed(key);
		}
	}

	/**
	 * pauses the game
	 */
	public void pause() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].pause();
		}
	}

	/**
	 * unpauses the game
	 */
	public void unpause() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].unpause();
		}
	}

	public String[] getKeysToSubscribeTo(int playerIndex) {
		return boards[playerIndex].getKeysToSubscribeTo(playerIndex);
	}

	public RenderInfo getRenderInfo(int playerIndex) {
		return boards[playerIndex].getRenderInfo();
	}

	/**
	 * returns the number of players that board is handling
	 */
	public int getNumberOfPlayers() {
		return boards.length;
	}
	
	public void addGameStateChangedListener(GameStateChangedListener listener) {
		gameStateChangedListeners.add(listener);
		for (int i = 0; i < boards.length; i++) {
			boards[i].addGameStateChangedListener(this);
		}
	}
	
	/**
	 * Updates the game state for each player and adds the score of the winning player to the highscore list
	 */
	@Override
	public void gameStateChanged(GameState newGameState, int playerIndex) {
		//if the game state changed to WON, then that means a player won and when
		//the first player wins all other players lose.
		if (newGameState == GameState.WON) {
			//if another player already won then the player that changed
			if (!didAnyoneAlreadyWin(playerIndex)) {
				Highscore.newScore(settings.getPlayers()[playerIndex].getName(),
						boards[playerIndex].getScore());
				for (int i = 0; i < boards.length; i++) {
					if (i != playerIndex) {
						boards[i].setGameState(GameState.LOST);
					}
				}
				for (int j = 0; j < gameStateChangedListeners.size(); j++) {
					for (int playerIndex2 = 0; playerIndex2 < boards.length; playerIndex2++) {
						gameStateChangedListeners.get(j).gameStateChanged(
								boards[playerIndex2].getGameState(), playerIndex2);
					}
				}
			}
		}
	}

	private boolean didAnyoneAlreadyWin(int playerIndexThatWon) {
		boolean anyoneAlreadyWon = false;
		for (int i = 0; i < boards.length; i++) {
			if (i != playerIndexThatWon && boards[i].getGameState() == GameState.WON) {
				anyoneAlreadyWon = true;
				break;
			}
		}
		return anyoneAlreadyWon;
	}

	@Override
	public void scoreChanged(int score, int seconds, int screenIndex) {
		scoreListener.scoreChanged(score, seconds, screenIndex);

	}

	public void addScoreChangedListener(ScoreChangedListener listener) {
		scoreListener = listener;
		for (int i = 0; i < boards.length; i++) {
			boards[i].addScoreChangedListener(this);
		}

	}

	@Override
	public void playSound(String name) {
		for (PlaySoundListener playSoundListener : playSoundListeners) {
			playSoundListener.playSound(name);
		}
	}

	public void addPlaySoundListener(PlaySoundListener listener) {
		playSoundListeners.add(listener);
		for (int i = 0; i < boards.length; i++) {
			boards[i].addPlaySoundListener(listener);
		}
	}

	@Override
	public void boardChanged(int playerIndex) {
		for (BoardChangedListener boardChangedListener : boardChangedListeners) {
			boardChangedListener.boardChanged(playerIndex);
		}
	}

	public void Stop() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].Stop();
		}
	}
}
