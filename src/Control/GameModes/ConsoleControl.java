package Control.GameModes;

import java.util.Scanner;
import View.*;
import Model.*;

import Control.Directions;
import Control.GameEngine.GameEngine;

public class ConsoleControl {
	public static void startGameInConsole(int size)
	{
		GameEngine game = new GameEngine(size);
		ConsoleGraphics.printGame(game);
		try (Scanner scan = new Scanner(System.in)) {
		while (true) {
			String line = scan.nextLine();
			if (line.length() == 1) {
				char pressed = line.charAt(0);
				switch (pressed) {
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
				ConsoleGraphics.printGame(game);
			}
		}
	}
	}
}
