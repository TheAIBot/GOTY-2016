package ConsoleControl;

import GameEngine.GameEngine;
import GameEngine.Tile;

class ConsoleGraphics {
	/**
	 * Prints the game board to the console
	 * @param game The game to print
	 */
	public static void printGame(GameEngine game)
	{
		final String emptyString = "";
		final int boardSize = game.getBoardSize();
		final int spacesBetweenTileNumbers = 2;
		final int tileLength = (int)Math.log10((boardSize * boardSize) - 1) + 1 + spacesBetweenTileNumbers;
		final String sTileLength = String.valueOf(tileLength);
		final Tile[] tiles = game.getTiles();
		for (int y = 0; y < boardSize; y++) {
			for (int x = 0; x < boardSize; x++) {
				Tile tile = tiles[x + y * boardSize];
				if (tile == null) {
					System.out.print(String.format("%" + sTileLength + "s", emptyString));
				}
				else {
					final int tileNumber = tile.number;
					System.out.print(String.format("%" + sTileLength + "d", tileNumber));
				}
			}
			System.out.println(emptyString);			
		}
		System.out.println(emptyString);
		System.out.println(emptyString);
	}
}
