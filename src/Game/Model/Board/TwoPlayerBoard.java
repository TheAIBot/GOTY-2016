package Game.Model.Board;

import Game.Model.Settings.GameSettings;
import Game.View.RenderInfo;

public class TwoPlayerBoard implements GameBoardMode {
	private final SinglePlayerBoard[] boards;
	
	public TwoPlayerBoard(GameSettings settings) {
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
