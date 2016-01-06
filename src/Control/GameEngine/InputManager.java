package Control.GameEngine;

import java.awt.event.ActionEvent;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class InputManager {
	
	private final HashSet<KeyPressListener> listeners = new HashSet<KeyPressListener>();
	
	public void AttachListenerToKey(JComponent component, KeyPressListener listener, final String key)
	{
		listeners.add(listener);
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
		component.getActionMap().put(key, new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	performEvent(key);
		    }
		});
	}
	
	private void performEvent(String KeyPressed)
	{
		for (KeyPressListener keyPressListener : listeners) {
			keyPressListener.KeyPressed(KeyPressed);
		}
	}
}
