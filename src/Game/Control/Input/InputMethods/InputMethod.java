package Game.Control.Input.InputMethods;

import Game.Model.Board.PlayerMode;

public interface InputMethod {
	public void subscribeToPlayerKeys(String[] keys, PlayerMode mode);
	
	public void pause();
	
	public void unpause();
}