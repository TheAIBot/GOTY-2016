package Game.Control.Input;

import java.util.ArrayList;

import javax.swing.JComponent;

import Game.Control.Input.InputMethods.InputMethod;
import Game.Control.Input.InputMethods.KeyboardInput;
import Game.Model.Board.PlayerMode;

public class InputManager implements InputListener {
	private ArrayList<InputListener> inputListeners = new ArrayList<InputListener>();
	private ArrayList<InputMethod> inputs = new ArrayList<InputMethod>();
	private JComponent component;
	
	public void subscribeToPlayerKeys(String[] keys, PlayerMode mode)
	{
		InputMethod inputMethod = getInputManagerFromPlayerMode(mode);
		inputMethod.subscribeToPlayerKeys(keys);
		inputMethod.addInputListener(this);
		inputs.add(inputMethod);
	}
	
	public void setKeyboardComponent(JComponent component)
	{
		this.component = component;
	}
	
	public void addInputListener(InputListener listener)
	{
		inputListeners.add(listener);
	}
	
	public void removeAllInputs()
	{
		for (InputMethod inputMethod : inputs) {
			inputMethod.unSubscribeToAllPlayerKeys();
		}
	}

	@Override
	public void keyPressed(String KeyPressed) {
		for (InputListener inputListener : inputListeners) {
			inputListener.keyPressed(KeyPressed);
		}
	}
	
	private InputMethod getInputManagerFromPlayerMode(PlayerMode mode)
	{
		switch (mode) {
		case HUMAN:
			return new KeyboardInput(component);
		default:
			throw new IllegalArgumentException();
		}
	}
}
