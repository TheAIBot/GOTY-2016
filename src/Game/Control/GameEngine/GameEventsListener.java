package Game.Control.GameEngine;

public interface GameEventsListener {
	public abstract void gameEnded();
	
	public abstract void hideWindow();
	
	public abstract void showWindow();
}
