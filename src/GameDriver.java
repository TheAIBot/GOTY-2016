import Game.Control.GameEngine.GameEngine;
import Game.Model.Settings.GameSettings;
import Menu.MenuController;

public class GameDriver {
	public static void main(String[] args) {
		if (args.length == 0) {
			MenuController menuController = new MenuController("GOTY-2016", 1040, 1040);
			menuController.showWindow();
		} else if (args.length == 1 && isParameterAcceptableAsBoardSize(args[0])) {
			startGameInConsole(Integer.valueOf(args[0]));
		}
	}
	
	/**
	 * @param arg
	 * @return Returns true if the argument given is a number and is within the limits
	 * set by the game settings or else false
	 */
	private static boolean isParameterAcceptableAsBoardSize(String arg)
	{
		if (arg.matches("\\d+")) {//one or more numbers in sequence
			int number = Integer.valueOf(arg);
			if (number >= GameSettings.SIZE_MIN &&
				number <= GameSettings.SIZE_MAX) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 *Runs the game in the console
	 * @param number the size of the board
	 */
	private static void startGameInConsole(int number)
	{
		GameSettings settings = new GameSettings();
		settings.setGameSize(number);
		settings.setHasGUI(false);
		settings.setConsoleMode(true);
		GameEngine gameEngine = new GameEngine(settings);
		gameEngine.startGameAsync();
	}
}
