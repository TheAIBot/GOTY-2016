package Game.Control.Input.InputMethods;

import Game.Control.Input.InputListener;

public interface InputMethod {
	public void subscribeToPlayerKeys(String[] keys);
	
	public void addInputListener(InputListener listener);
	
	public void unSubscribeToAllPlayerKeys();
	
	public void pause();
	
	public void unpause();
}