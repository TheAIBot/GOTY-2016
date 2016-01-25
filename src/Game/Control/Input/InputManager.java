package Game.Control.Input;

import java.util.ArrayList;

import Game.Control.Input.InputMethods.KeyboardInput;
import Game.Model.Board.PlayerMode;

public class InputManager implements InputListener {
	private ArrayList<InputListener> inputListeners = new ArrayList<InputListener>();
	private ArrayList<InputManager> inputs = new ArrayList<InputManager>();
	
	public void subscribeToPlayerKeys(String[] keys, PlayerMode mode)
	{
		
	}
	
	public void addInputListener(InputListener listener)
	{
		inputListeners.add(listener);
	}

	@Override
	public void keyPressed(String KeyPressed) {
		// TODO Auto-generated method stub
		
	}
	
	private InputManager getInputManagerFromPlayerMode(PlayerMode mode)
	{
		switch (mode) {
		case HUMAN:
			return new KeyboardInput();
		default:
			break;
		}
	}
}
