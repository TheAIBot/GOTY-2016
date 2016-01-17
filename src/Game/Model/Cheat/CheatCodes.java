package Game.Model.Cheat;

import java.util.ArrayList;

import javax.swing.JComponent;

import Game.Control.GameEngine.Log;
import Game.Control.Input.InputManager;
import Game.Control.Input.KeyPressListener;

/**
 * @author ELL
 *
 */
public class CheatCodes implements KeyPressListener {

	private final ArrayList<CheatCode> cheatCodes = new ArrayList<CheatCode>();
	private CheatActivatedListener listener;
	private InputManager input = new InputManager();
	private JComponent listenerComponent;

	public static final String KONAMI_CODE = "Konami Code";

	public CheatCodes(CheatActivatedListener cheatListener, JComponent cheatComponent) {
		listener = cheatListener;
		listenerComponent = cheatComponent;
	}

	/**
	 * Adds a new cheat code to the list of cheat codes available. Binds the key
	 * sequence needed to trigger the cheat code to the listener listener
	 * component (typically representing a menu page).
	 * 
	 * @param sequence
	 * @param cheatName
	 * @param isRepeatCheat
	 */
	public void addNewCheatCode(String[] sequence, String cheatName, boolean isRepeatCheat) {
		CheatCode newCheatCode = new CheatCode(sequence, cheatName, isRepeatCheat);
		cheatCodes.add(newCheatCode);
		for (String cheatCodePart : sequence) {
			input.AttachListenerToKey(listenerComponent, this, cheatCodePart);
		}
	}

	/**
	 * Makes the cheat unavailable for further use because it could only be used
	 * once per game session (every game instance).
	 * 
	 * @param cheatName
	 */
	private void removeCheat(String cheatName) {
		for (int i = 0; i < cheatCodes.size(); i++) {
			if (cheatCodes.get(i).getCheatName().equals(cheatName)) {
				cheatCodes.remove(cheatCodes.get(i));
				return;
			}
		}
		Log.writeln("Could not find the cheat: " + cheatName);
	}

	/**
	 * Checks for every cheat available if the sequence has been correctly typed
	 * out and if so activates it
	 */
	@Override
	public void keyPressed(String key) {
		for (int i = 0; i < cheatCodes.size(); i++) {
			CheatCode cheatCode = cheatCodes.get(i);
			if (cheatCode.keyPressed(key)) {
				cheatActivated(cheatCode);
			}
		}
	}

	/**
	 * Activates the cheat by informing the listener.
	 * 
	 * @param cheatCode
	 */
	private void cheatActivated(CheatCode cheatCode) {
		listener.cheatActivated(cheatCode.getCheatName());
		if (!cheatCode.isRepeatCheat) {
			removeCheat(cheatCode.getCheatName());
		}
	}

	/**
	 * Class represents a simple cheat code
	 */
	private class CheatCode {
		private final String cheatName;
		public final boolean isRepeatCheat;
		private final String[] keySequence;
		private int index = 0;

		public CheatCode(String[] sequence, String name, boolean isRepeatCheat) {
			if (sequence == null) {
				throw new NullPointerException();
			}
			if (sequence.length < 2) {
				throw new IllegalArgumentException("key sequence needs to be atleast 2 or more key presses");
			}
			this.cheatName = name;
			this.isRepeatCheat = isRepeatCheat;
			this.keySequence = sequence;
		}

		/**
		 * @param key
		 * @return true if the end of the given cheat code key sequence is
		 *         reached
		 */
		public boolean keyPressed(String key) {
			if (keySequence[index].equals(key)) {
				index++;
				if (index == keySequence.length) {
					index = 0;
					return true;
				}
				return false;
			} else {
				// Start again from the beginning of the key sequence and check
				// if the first typed key is correct
				if (index != 0) {
					index = 0;
					return keyPressed(key);
				}
				return false;
			}
		}

		public String getCheatName() {
			return cheatName;
		}
	}

}
