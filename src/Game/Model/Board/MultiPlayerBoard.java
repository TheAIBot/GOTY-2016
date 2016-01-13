package Game.Model.Board;

import java.util.ArrayList;
import java.util.Random;

import Game.Control.GameEngine.Log;
import Game.Model.Score.ScoreChangedListener;
import Game.Model.Settings.GameSettings;
import Game.View.RenderInfo;

public class MultiPlayerBoard implements GameBoardMode, GameStateChangedListener, ScoreChangedListener {
	private final ArrayList<GameStateChangedListener> gameStateChangedListeners = new ArrayList<GameStateChangedListener>();
	private final SinglePlayerBoard[] boards;
	private ScoreChangedListener scoreListener;
	
	public MultiPlayerBoard(GameSettings settings) {
		boards = new SinglePlayerBoard[settings.getPlayers().length];
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
		final Thread[] threads = new Thread[boards.length];
		final long seed = (int)(Math.random() * 1000000000);
		for (int i = 0; i < boards.length; i++) {
			final int index = i;
			boards[i].setRandom(new Random(seed));
			threads[i] = new Thread(() -> {
				boards[index].makeRandom();
			});
			threads[i].start();
		}		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				Log.writeln("error occured when joining thread with main thread in makeRandom");
			}
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
	public void restart() {
		for (int i = 0; i < boards.length; i++) {
			boards[i].restart();
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
}
