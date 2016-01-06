package Control.GameEngine;

import java.awt.event.ActionEvent;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import View.Screen;

public class InputManager {
	
	private final HashSet<KeyPressListener> listeners = new HashSet<KeyPressListener>();
	
	public void AttachListenerToKey(Screen screen, KeyPressListener listener, final String key)
	{
		listeners.add(listener);
		//TODO when screen is fixed the below code has to subscribe to the graphics panel
		JPanel gPanel = new JPanel();
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
		gPanel.getActionMap().put(key, new AbstractAction() {
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
