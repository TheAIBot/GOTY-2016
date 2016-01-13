package Game.Model.Board;

import Game.Control.GameEngine.Log;
import Game.Model.Settings.GameSettings;
import Game.View.RenderInfo;

public class MultiPlayerBoard implements GameBoardMode {
	private final SinglePlayerBoard[] boards;
	
	public MultiPlayerBoard(GameSettings settings) {
		boards = new SinglePlayerBoard[settings.getPlayers().length];
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
		Thread[] threads = new Thread[boards.length];
		for (int i = 0; i < boards.length; i++) {
			final int index = i;
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
	
}
