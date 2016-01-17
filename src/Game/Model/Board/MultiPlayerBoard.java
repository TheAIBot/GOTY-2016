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

public class MultiPlayerBoard implements GameBoardMode, GameStateChangedListener, ScoreChangedListener, PlaySoundListener, Serializable, BoardChangedListener {
	private static final long serialVersionUID = 1474947852904108736L;
	private final ArrayList<GameStateChangedListener> gameStateChangedListeners = new ArrayList<GameStateChangedListener>();
	private final ArrayList<PlaySoundListener> playSoundListeners = new ArrayList<PlaySoundListener>();
	private final ArrayList<BoardChangedListener> boardChangedListeners = new ArrayList<BoardChangedListener>();
	private final SinglePlayerBoard[] boards;
	private ScoreChangedListener scoreListener;
	private GameSettings settings;
	
	/**
	 * A MultiplayerBoard consists of a number of SinglePlayerBoards.
	 * Therefor create an array of SinglePlayerBoards.
	 * @param settings
	 * @param playerCount
	 */
	public MultiPlayerBoard(GameSettings settings, int playerCount) {
		this.settings = settings;
		this.boards = new SinglePlayerBoard[playerCount];
		for (int i = 0; i < boards.length; i++) {
			boards[i] = new SinglePlayerBoard(settings, i);
		}
	}
	
	@Override
	public void createGame() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].createGame();
		}
	}

	@Override
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
		final int numberOfDirections = 4;
		double difficultyInPercent; //The difficulty of the board in percents.
		Random randomGenerator = new Random();
		do {
			Directions direction; //The direction that the void tile should move.
			
			//It moves the void tile in a random direction 100 times the game board size
			//before it checks if the difficulty level is attained.
			//This is not done for every move, as it requires to many calculations per time it is done, 
			//and would make the randomization process take to long.
			//It is at the same time not checked every a 1000 times the game board size, 
			//as it makes it almost impossible to attain a board of a lower difficulty.	
			for (int i = 0; i < settings.getGameSize() * 100; i++) {
				switch (randomGenerator.nextInt(numberOfDirections)) {
				case 0:
					direction = Directions.LEFT;
					break;
				case 1:
					direction = Directions.RIGHT;
					break;
				case 2:
					direction = Directions.UP;
					break;
				case 3:
					direction = Directions.DOWN;
					break;
				default:
					Log.writeln("Random generator in makeRandom game returned a number that was higher than the number of directions");
					direction = Directions.LEFT;
				}
				//It moves the nullTile on every board in the same direction, so the same board layout is attained on every board, 
				//when the randomization is finished.
				for (int j = 0; j < boards.length; j++) {
					boards[j].moveVoidTile(direction);
				}
			}
			difficultyInPercent = DifficultyCalculator.getDifficultyPercentage(getTiles(0), settings.getGameSize(), maxDifficulty);
			for (int i = 0; i < boards.length; i++) {
				boardChanged(i);
			}
		} while (settings.getDifficultyLevel() != DifficultyCalculator.getDifficultyLevel(difficultyInPercent)
				 || difficultyInPercent == 0);
	}

	private void defaultGame()
	{
		for (int i = 0; i < boards.length; i++) {
			boards[i].makeRandom();
		}
	}
	
	@Override
	public void resetGame() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].resetGame();
		}	
	}

	@Override
	public Tile[] getTiles(int playerIndex) {
		return boards[playerIndex].getTiles(playerIndex);
	}

	@Override
	public int getSize() {
		return boards[0].getSize();
	}

	@Override
	public GameState getGameState(int playerIndex) {
		return boards[playerIndex].getGameState(playerIndex);
	}

	@Override
	public void addBoardChangedListener(BoardChangedListener listener) {
		boardChangedListeners.add(listener);
		for (int i = 0; i < boards.length; i++) {
			boards[i].addBoardChangedListener(this);
		}
	}

	@Override
	public void keyPressed(String key) {
		for (int i = 0; i < boards.length; i++) {
			boards[i].keyPressed(key);
		}
	}

	@Override
	public void pause() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].pause();
		}
	}

	@Override
	public void unpause() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].unpause();
		}
	}

	@Override
	public String[] getKeysToSubscribeTo(int playerIndex) {
		return boards[playerIndex].getKeysToSubscribeTo(playerIndex);
	}

	@Override
	public RenderInfo getRenderInfo(int playerIndex) {
		return boards[playerIndex].getRenderInfo(playerIndex);
	}

	@Override
	public int getNumberOfPlayers() {
		return boards.length;
	}
	
	@Override
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
		if (newGameState == GameState.WON) {
			if (!didAnyoneAlreadyWin(playerIndex)) {
				Highscore.newScore(settings.getPlayers()[playerIndex].getName(), boards[playerIndex].getScore());
				for (int i = 0; i < boards.length; i++) {
					if (i != playerIndex) {
						boards[i].setGameState(GameState.LOST);
					}
				}
				for (int j = 0; j < gameStateChangedListeners.size(); j++) {
					for (int playerIndex2 = 0; playerIndex2 < boards.length; playerIndex2++) {
						gameStateChangedListeners.get(j).gameStateChanged(boards[playerIndex2].getGameState(playerIndex2), playerIndex2);
					}
				}
			}
		}
	}
	
	private boolean didAnyoneAlreadyWin(int playerIndexThatWon)
	{
		boolean anyoneAlreadyWon = false;
		for (int i = 0; i < boards.length; i++) {
			if (i != playerIndexThatWon && boards[i].getGameState(i) == GameState.WON) {
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

	@Override
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

	@Override
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

	@Override
	public void Stop() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].Stop();
		}		
	}
}
