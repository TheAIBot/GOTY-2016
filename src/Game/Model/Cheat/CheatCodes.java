package Game.Cheat;

import java.util.ArrayList;

import javax.swing.JComponent;

<<<<<<< HEAD:src/Model/CheatCodes.java
import Control.GameEngine.InputManager;
import Control.GameEngine.KeyPressListener;
=======
import org.omg.CORBA.PRIVATE_MEMBER;

import com.sun.javafx.collections.ArrayListenerHelper;

>>>>>>> refs/remotes/origin/Andreas:src/Game/Model/Cheat/CheatCodes.java
import Control.GameEngine.Log;
import Control.Input.InputManager;
import Control.Input.KeyPressListener;

public class CheatCodes implements KeyPressListener {
	
	private final ArrayList<CheatCode> cheatCodes = new ArrayList<CheatCode>();
	private CheatActivatedListener listener;
	private InputManager input = new InputManager();
	private JComponent listenerComponent;
	
	public static final String KONAMI_CODE = "Konami Code";
	
	public CheatCodes(CheatActivatedListener cheatListener, JComponent cheatComponent)
	{
		listener = cheatListener;
		listenerComponent = cheatComponent;
	}
	
	public void addNewCheatCode(String[] sequence, String cheatName, boolean isRepeatCheat)
	{
		CheatCode newCheatCode = new CheatCode(sequence, cheatName, isRepeatCheat);
		cheatCodes.add(newCheatCode);
		for (String cheatCodePart : sequence) {
			input.AttachListenerToKey(listenerComponent, this, cheatCodePart);
		}
	}
	
	private void removeCheat(String cheatName)
	{
		for (int i = 0; i < cheatCodes.size(); i++) {
			if (cheatCodes.get(i).getCheatName().equals(cheatName)) {
				cheatCodes.remove(cheatCodes.get(i));
				return;
			}
		}
		Log.writeln("Could not find the cheat: " + cheatName);
	}
	
	@Override
	public void keyPressed(String key) {
		for (int i = 0; i < cheatCodes.size(); i++) {
			CheatCode cheatCode = cheatCodes.get(i);
			if (cheatCode.keyPressed(key)) {
				cheatActivated(cheatCode);
			}
		}
	}
	
	private void cheatActivated(CheatCode cheatCode)
	{
		listener.cheatActivated(cheatCode.getCheatName());
		if (!cheatCode.isRepeatCheat) {
			removeCheat(cheatCode.getCheatName());
		}
	}

	
	private class CheatCode
	{
		private final String cheatName;
		public final boolean isRepeatCheat;
		private final String[] keySequence;
		private int index = 0;
		
		public CheatCode(String[] sequence, String name, boolean isRepeatCheat)
		{
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
		
		public boolean keyPressed(String key)
		{
			if (keySequence[index].equals(key)) {
				index++;
				if (index == keySequence.length) {
					index = 0;
					return true;
				}
				return false;
			} else {
				if (index != 0) {
					index = 0;
					return keyPressed(key);
				}
				return false;
			}
		}
		
		public String getCheatName()
		{
			return cheatName;
		}
		}

}
