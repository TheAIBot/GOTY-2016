package Game.Control.Input.InputMethods;

import java.awt.event.ActionEvent;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import Game.Control.Input.InputListener;
import Game.Control.Input.InputManager;
import Game.Model.Board.PlayerMode;

/**
 * Keyboard input is handled using this class
 */
public class KeyboardInput implements InputMethod {
	private final HashSet<InputListener> listeners = new HashSet<InputListener>();

	@Override
	public void subscribeToPlayerKeys(String[] keys, PlayerMode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {		
	}

	@Override
	public void unpause() {		
	}
	
	/**
	 * Adds the key to the set of KeyPressListeners (i.e. the classes which
	 * reacts on key presses). Further, the key is bound to the InputMap of the
	 * JComponent passed as a parameter and the associated action to fire is set
	 * to forward the key press to the keyPressListeners
	 * @param component
	 * @param listener
	 * @param key
	 */
	public void AttachListenerToKey(JComponent component, InputListener listener, final String key) {
		listeners.add(listener);
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
		component.getActionMap().put(key, new AbstractAction() {
			private static final long serialVersionUID = 6529023994874939582L;

			public void actionPerformed(ActionEvent e) {
				performEvent(key);
			}
		});
	}

	/**
	 * Removes all Listeners associated with the component.
	 * This method is used when the game is switching to the console game mode
	 * @param component
	 */
	public void RemoveAllListeners(JComponent component) {
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
		listeners.clear();
	}

	/**
	 * Forwards the keyEvent to all listeners
	 * @param KeyPressed
	 */
	private void performEvent(String KeyPressed) {
		for (InputListener keyPressListener : listeners) {
			keyPressListener.keyPressed(KeyPressed);
		}
	}
}
