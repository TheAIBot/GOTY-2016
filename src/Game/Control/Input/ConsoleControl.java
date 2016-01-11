package Control.Input;

import java.util.Scanner;

import Control.GameEngine.GameEngine;
import Game.Board.Directions;
import Game.Board.GameState;
import Game.Board.GameStateChangedListener;
import Game.Settings.GameSettings;

public class ConsoleControl implements GameStateChangedListener {
	private static boolean run = true;
	
	/**
	 * Starts and shows the game in the console 
	 * Controlled by using w, a, s, d as the controls
	 * @param size The size of the game board
	 */
	public static void startGameInConsole(int size) {
		GameSettings settings = new GameSettings();
		GameEngine game = new GameEngine(settings);
		run = true;
		try (Scanner scan = new Scanner(System.in)) {
			while (true) {
				do {
					//print the game to the console every time a command is passed
					// and at the start of the game so the user can the game
					//ConsoleGraphics.printGame(game);
					String comand = scan.nextLine();
					//only commands of the size 1 char is allowed as there is
					//no command that uses more than 1 char and atleast 1 char
					//is needed for it to be a valid command
					if (comand.length() == 1) {
						//the command will always be the first char as the
						//string has the length 1
						game.keyPressed(comand);
					}
					//The game win run until the the player either lost, won or tied
				} while (run);
				game.resetGame();
			}
		}
	}

	public void gameStateChanged(GameState newGameState) {
		if (newGameState != GameState.NOT_DECIDED_YET) {
			run = false;
		}
	}
}
