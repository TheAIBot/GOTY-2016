package Game.Model.Board;

public interface GameStateChangedListener extends  java.io.Serializable {
	
	public void gameStateChanged(GameState newGameState);

}
