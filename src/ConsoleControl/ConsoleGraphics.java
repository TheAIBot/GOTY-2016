package ConsoleControl;

import java.awt.Point;
import java.util.Arrays;

import com.sun.xml.internal.ws.util.StringUtils;

import GameEngine.GameEngine;
import GameEngine.Tile;

class ConsoleGraphics {
	public static void printGame(GameEngine game)
	{
		int boardSize = game.getBoardSize();
		int tileLength = (int)Math.log10((boardSize * boardSize) - 1) + 1;
		int spacesBetweenTiles = 2;
		Tile[] tiles = game.getTiles();
		char[] line = new char[tileLength * boardSize + (boardSize - 1) * spacesBetweenTiles];
		Arrays.fill(line, ' ');
		for (int y = 0; y < boardSize; y++) {
			int numberOfSpaces = 0;
			for (int x = 0; x < game.getBoardSize(); x++) {
				Tile tile = tiles[x + y * game.getBoardSize()];
				char[] number;
				if (tile == null) {
					 number = new char[tileLength];
					 Arrays.fill(number, ' ');
				}
				else {
					number = String.valueOf(tile.number).toCharArray();
				}
				int offset = (tileLength * (x + 1)) + 
						 	 (spacesBetweenTiles * numberOfSpaces) - 
						 	  number.length;
				System.arraycopy(number, 0, line, offset, number.length);
				numberOfSpaces++;
			}
			System.out.println(new String(line));
			Arrays.fill(line, ' ');
		}
		System.out.println("");
		System.out.println("");
	}
}
