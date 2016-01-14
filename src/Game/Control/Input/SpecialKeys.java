package Game.Control.Input;

public class SpecialKeys {
	public static final String EXIT_GAME = "ESCAPE";
	public static final String TOGGLE_PAUSE = "SPACE";
	public static final String TOGGLE_CONSOLE_MODE = "F1";
	
	public static boolean isSpecialKey(String key)
	{
		switch (key) {
		case EXIT_GAME:
		case TOGGLE_PAUSE:
		case TOGGLE_CONSOLE_MODE:
			return true;
		default:
			return false;
		}
	}
}
