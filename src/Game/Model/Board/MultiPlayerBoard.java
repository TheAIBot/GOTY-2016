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

public class MultiPlayerBoard implements GameBoardMode, GameStateChangedListener, ScoreChangedListener, PlaySoundListener, Serializable {
	private static final long serialVersionUID = 1474947852904108736L;
	private final ArrayList<GameStateChangedListener> gameStateChangedListeners = new ArrayList<GameStateChangedListener>();
	private final ArrayList<PlaySoundListener> playSoundListeners = new ArrayList<PlaySoundListener>();
	private final SinglePlayerBoard[] boards;
	private ScoreChangedListener scoreListener;
	
	public MultiPlayerBoard(GameSettings settings, int playerCount) {
		boards = new SinglePlayerBoard[playerCount];
		for (int i = 0; i < boards.length; i++) {
			boards[i] = new SinglePlayerBoard(settings, i);
			boards[i].addGameStateChangedListener(this);
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
		if (boards[0].settings.isRandomized()) {
			randomizeGame();
		} else {
			defaultGame();
		}
	}
	
	private void randomizeGame()
	{
		final int NumberOfDirections = 4;
		Random randomGenerator = new Random();
		do {
			for (int i = 0; i < boards[0].settings.getGameSize() * 100; i++) {
				Directions direction;
				switch (randomGenerator.nextInt(NumberOfDirections)) {
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
					Log.writeln("Random generator in makeRandom game a number that was higher than the number of directions");
					direction = Directions.LEFT;
				}
				for (int j = 0; j < boards.length; j++) {
					boards[j].moveVoidTile(direction);
				}
			}
		} while (boards[0].settings.getDifficultyLevel() != DifficultyCalculator.getDifficultyLevel(boards[0].getTiles(0), boards[0].settings.getGameSize()) ||
				   DifficultyCalculator.getDfficulty(boards[0].getTiles(0), boards[0].settings.getGameSize()) == 0);
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
		for (int i = 0; i < boards.length; i++) {
			boards[i].addBoardChangedListener(listener);
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
	}

	
	@Override
	public void gameStateChanged(GameState newGameState, int playerIndex) {
		if (newGameState == GameState.WON) {
			boolean anyoneAlreadyWon = false;
			for (int i = 0; i < boards.length; i++) {
				if (i != playerIndex && boards[i].getGameState(i) == GameState.WON) {
					anyoneAlreadyWon = true;
					break;
				}
			}
			if (!anyoneAlreadyWon) {
				pause();
				Highscore.newScore(boards[playerIndex].settings.getPlayers()[playerIndex].getName(), boards[playerIndex].getScore());
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
}
