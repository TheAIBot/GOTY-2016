package Game.Control.Input;

import java.util.Scanner;

import Game.Control.GameEngine.GameEngine;
import Game.Model.Board.GameState;
import Game.Model.Board.GameStateChangedListener;
import Game.Model.Settings.GameSettings;

public class ConsoleControl implements GameStateChangedListener {
	private boolean run = true;
	private GameEngine game;
	private GameSettings settings;
	
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
		try (Scanner scan = new Scanner(System.in)) {
			while (settings.isConsoleMode()) {
				do {
					//Prints the game to the console every time a command is passed
					// and at the start of the game so the user can see the games initial position
					String comand = scan.nextLine();
					//Only commands of the size 1 char is allowed as there is
					//no command that uses more than 1 char and at least 1 char
					//is needed for it to be a valid command.
					if (comand.length() == 1) {
						//The command will always be the first char as the
						//String has the length 1
						game.keyPressed(comand);
					}
					//The game win run until the the player either lost, won or tied
				} while (run);
				game.resetGame();
			}
		}
	}

	@Override
	public void gameStateChanged(GameState newGameState, int playerIndex) {
		if (newGameState != GameState.NOT_DECIDED_YET) {
			run = false;
		}
	}
}
