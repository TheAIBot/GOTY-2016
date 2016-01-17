package Game.Control.Input;

import java.util.Scanner;
import org.omg.CORBA.COMM_FAILURE;

import Game.Control.GameEngine.GameEngine;
import Game.Model.Board.GameState;
import Game.Model.Board.GameStateChangedListener;
import Game.Model.Settings.GameSettings;

public class ConsoleControl implements GameStateChangedListener {
	private boolean run = true;
	private GameEngine game;
	private GameSettings settings;
	private static Scanner scan = new Scanner(System.in);
	
	public ConsoleControl(GameEngine game, GameSettings settings) {
		this.game = game;
		this.settings = settings;
	}
	
	/**
	 * Starts and shows the game in the console 
	 * Controlled by using w, a, s, d as the controls
	 * @param size The size of the game board
	 */
	public void startGameInConsole() {
		run = true;
		do {
			//Prints the game to the console every time a command is passed
			// and at the start of the game so the user can see the games initial position
				String command = scan.nextLine();
				//Commands of the size 1 char is allowed, as this is the length of the move commands.
				//Other than that, the speciel pause and escape command is allowed.
				command = command.toUpperCase();
				if (command.length() == 1) {
					game.keyPressed(command);
				} else if (command.equals("EXIT")) {
					game.keyPressed(SpecialKeys.EXIT_GAME);
				} else if (command.equals("PAUSE")) {
					game.keyPressed(SpecialKeys.TOGGLE_PAUSE);
				} else if (command.equals("SCREEN")) {
					game.keyPressed(SpecialKeys.TOGGLE_CONSOLE_MODE);
				}
			//}
			//The game will run until it is finished (no shit) - meaning until the player has solved the puzzle
		} while (settings.isConsoleMode());
	}

	@Override
	public void gameStateChanged(GameState newGameState, int playerIndex) {
		if (newGameState != GameState.NOT_DECIDED_YET) {
			run = false;
		}
	}
}
