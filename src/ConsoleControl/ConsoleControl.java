package ConsoleControl;

import java.util.Scanner;

import GameEngine.Directions;
import GameEngine.GameEngine;
import GameEngine.GameState;

public class ConsoleControl {
	/**
	 * Starts and shows the game in the console 
	 * Controlled by using w, a, s, d as the controls
	 * @param size The size of the game board
	 */
	public static void startGameInConsole(int size) {
		GameEngine game = new GameEngine(size);

		try (Scanner scan = new Scanner(System.in)) {
			while (true) {
				do {
					//print the game to the console every time a command is passed
					// and at the start of the game so the user can the game
					ConsoleGraphics.printGame(game);
					String comand = scan.nextLine();
					//only commands of the size 1 char is allowed as there is
					//no command that uses more than 1 char and atleast 1 char
					//is needed for it to be a valid command
					if (comand.length() == 1) {
						//the command will always be the first char as the
						//string has the length 1
						char keyPressed = comand.charAt(0);
						doGameMove(keyPressed, game);
					}
					//The game win run until the the player either lost, won or tied
				} while (game.getGameState() == GameState.NOT_DECIDED_YET);
				game.resetGame();
			}
		}
	}
	
	/**
	 * moves the game void tile if a move key char is given
	 * @param keyChar The value of this char determines which way the game void tile moves in
	 * @param game The game board the void tile should move in
	 */
	private static void doGameMove(char keyChar, GameEngine game)
	{
		switch (keyChar) {
		case 'd':
			game.moveVoidTile(Directions.RIGHT);
			break;
		case 'a':
			game.moveVoidTile(Directions.LEFT);
			break;
		case 'w':
			game.moveVoidTile(Directions.UP);
			break;
		case 's':
			game.moveVoidTile(Directions.DOWN);
			break;
		}
	}
}
