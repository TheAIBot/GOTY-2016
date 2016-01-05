package ConsoleControl;

import java.util.Scanner;

import GameEngine.Directions;
import GameEngine.GameEngine;

public class ConsoleControl {
	/**
	 * Starts and shows the game in the console 
	 * Controlled by using w, a, s, d as the controls
	 * @param size The size of the game board
	 */
	public static void startGameInConsole(int size) {
		GameEngine game = new GameEngine(size);
		ConsoleGraphics.printGame(game);
		try (Scanner scan = new Scanner(System.in)) {
			while (true) {
				String line = scan.nextLine();
				if (line.length() == 1) {
					char keyPressed = line.charAt(0);
					doGameMove(keyPressed, game);
					ConsoleGraphics.printGame(game);
				}
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
