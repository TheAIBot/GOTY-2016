package Game.Control.GameEngine;

public interface GameEventsListener {
	public void gameEnded();
	
	public void hideWindow();
	
	public void showWindow();
	
	public void closeGame();
	
	public void gameStarted();
}
