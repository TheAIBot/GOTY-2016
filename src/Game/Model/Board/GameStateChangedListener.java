package Game.Model.Board;

public interface GameStateChangedListener {
	
	public void gameStateChanged(GameState newGameState, int playerIndex);

}
