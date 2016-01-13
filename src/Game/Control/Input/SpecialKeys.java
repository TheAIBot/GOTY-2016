package Game.Control.Input;

public class SpecialKeys {
	public static final String EXIT_GAME = "ESCAPE";
	public static final String TOGGLE_PAUSE = "SPACE";
	
	public static boolean isSpecialKey(String key)
	{
		switch (key) {
		case EXIT_GAME:
		case TOGGLE_PAUSE:
			return true;
		default:
			return false;
		}
	}
}
