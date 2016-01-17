import Game.Control.GameEngine.GameEngine;
import Game.Model.Settings.GameSettings;
import Menu.MenuController;

public class GameDriver {
	public static void main(String[] args) {
		if (args.length == 0) {
			MenuController menuController = new MenuController("GOTY-2016", 1040, 1040);
			menuController.showWindow();
		} else if (args.length == 1) {
			String arg = args[0];
			if (arg.matches("\\d+")) {//one or more numbers in sequence
				int number = Integer.valueOf(arg);
				if (number >= GameSettings.SIZE_MIN &&
					number <= GameSettings.SIZE_MAX) {
					GameSettings settings = new GameSettings();
					settings.setGameSize(number);
					settings.setHasGUI(false);
					settings.setConsoleMode(true);
					GameEngine gameEngine = new GameEngine(settings);
					gameEngine.startGameAsync();
				}
			}
		}
	}	
}
