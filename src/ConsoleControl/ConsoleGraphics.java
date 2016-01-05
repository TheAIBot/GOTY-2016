package ConsoleControl;

import GameEngine.GameEngine;
import GameEngine.Tile;

class ConsoleGraphics {
	public static void printGame(GameEngine game)
	{
		final int boardSize = game.getBoardSize();
		final int spacesBetweenTileNumbers = 2;
		final int tileLength = (int)Math.log10((boardSize * boardSize) - 1) + 1 + spacesBetweenTileNumbers;
		final String sTileLength = String.valueOf(tileLength);
		final Tile[] tiles = game.getTiles();
		for (int y = 0; y < boardSize; y++) {
			for (int x = 0; x < boardSize; x++) {
				Tile tile = tiles[x + y * boardSize];
				if (tile == null) {
					System.out.print(String.format("%" + sTileLength + "s", ""));
				}
				else {
					int tileNumber = tile.number;
					System.out.print(String.format("%" + sTileLength + "d", tileNumber));
				}
			}
			System.out.println("");			
		}
		System.out.println("");
		System.out.println("");
	}
}
