package ConsoleControl;

import Control.GameEngine.GameEngine;
import Model.Tile;



class ConsoleGraphics {
	/**
	 * Prints the game board to the console
	 * @param game The game to print
	 */
	public static void printGame(GameEngine game)
	{
		final String emptyString = "";
		final int boardSize = game.getBoardSize();
		//Decides how many spaces there is between two numbers that represents two different tiles
		final int spacesBetweenTileNumbers = 2;
		//Because the size of the game can differ, the size of the idividual tile numbers differ from game to game
		//so here the length of the biggeest tile number is calculated and the space between tiles is added
		//here so two tile numbers will always have spaces between them because the tileLength is 
		//always bigger than the max tile number length
		final int tileLength = (int)Math.log10((boardSize * boardSize) - 1) + 1 + spacesBetweenTileNumbers;
		final String sTileLength = String.valueOf(tileLength);
		final Tile[] tiles = game.getTiles();
		//the game consists of rows and columns equal the the game board size
		//so two loops goes through each row and then column and prints the tile number to the console
		for (int y = 0; y < boardSize; y++) {
			for (int x = 0; x < boardSize; x++) {
				Tile tile = tiles[x + y * boardSize];
				//the void tile is represented by the value null in the array 
				//and String.format throws an error when null is passed
				if (tile == null) {
					System.out.print(String.format("%" + sTileLength + "s", emptyString));
				}
				else {
					final int tileNumber = tile.getNumber();
					//String-format makes sure each number uses the same amount of chars 
					//so every number is aligned by column
					System.out.print(String.format("%" + sTileLength + "d", tileNumber));
				}
			}
			System.out.println(emptyString);			
		}
		System.out.println(emptyString);
		System.out.println(emptyString);
	}
}
