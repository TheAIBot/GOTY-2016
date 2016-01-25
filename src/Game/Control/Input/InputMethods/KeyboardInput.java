package Game.Control.Input.InputMethods;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import Game.Control.Input.InputListener;

/**
 * Keyboard input is handled using this class
 */
public class KeyboardInput implements InputMethod {
	private final ArrayList<InputListener> listeners = new ArrayList<InputListener>();
	private final JComponent component;
	
	public KeyboardInput(JComponent component) {
		this.component = component;
	}

	@Override
	public void subscribeToPlayerKeys(String[] keys) {
		for (int i = 0; i < keys.length; i++) {
			AttachListenerToKey(keys[i]);
		}
	}	

	@Override
	public void addInputListener(InputListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void unSubscribeToAllPlayerKeys() {
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
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
	private void AttachListenerToKey(final String key) {
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
		component.getActionMap().put(key, new AbstractAction() {
			private static final long serialVersionUID = 6529023994874939582L;

			public void actionPerformed(ActionEvent e) {
				performEvent(key);
			}
		});
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
