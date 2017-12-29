package Game.View;

import Game.Control.GameEngine.GraphicsManager;
import Game.Model.Board.GameState;
import Game.View.ViewTypes.Numreable;

public class ConsoleGraphics {

	private final int boardSize; //In capital letters? (*)
	//Decides how many spaces there is between two numbers that represents two different tiles.
	private final static int SPACES_BETWEEN_TILE_NUMBERS = 2;
	private final GraphicsManager graphics;
	
	public ConsoleGraphics(int boardSize, GraphicsManager graphics) {
		this.boardSize = boardSize;		
		this.graphics = graphics;		
	}
	
	//private void printHelpMessage()
	//{
	//	System.out.println("Commands:");
	//	System.out.println("exit to quit the game");
	//	System.out.println("screen to return to the GUI");
	//	System.out.println("pause to pause the game");
	//	System.out.println("press enter to submit you command");
	//}
	
	
	/**
	 * Prints the game board to the console
	 * @param game The game to print
	 */
	public void render()
	{
		//Since the size of the game can differ, the size of the individual tile numbers (in digits) differ from game to game.
		//Therefore, the length of the biggest tile number is always found and the space between tiles is added
		//here, so that two tile numbers will always have space in between them. This is because the tileLength is 
		//always bigger than the max tile number length.
		
		final int tileLength = (int)Math.log10((boardSize * boardSize) - 1) + 1 + SPACES_BETWEEN_TILE_NUMBERS; 
		final String sTileLength = String.valueOf(tileLength);
		//The screenindex is always zero, as console mode is not compatible with multiplayer mode
		final Numreable[] numreables = graphics.getNumreablesToRender(0);
		final StringBuilder boardString = new StringBuilder();
		
		//The game consists of rows and columns, with a size equal the the game board size,
		//so two loops goes through each row and their columns, and prints the tile number to the console
		for (int y = 0; y < boardSize; y++) {
			for (int x = 0; x < boardSize; x++) {
				final Numreable num = numreables[x + y * boardSize];
				//the void tile is represented by the value null in the array 
				//and String.format throws an error when null is passed so
				//empty string is used instead
				final String tileNumber = (num == null) ? "" : String.valueOf(num.getNumber());
				boardString.append(String.format("%" + sTileLength + "s", tileNumber));
			}
			boardString.append('\n');		
		}
		boardString.append('\n');
		System.out.println(boardString.toString());
	}
	
	public void gameStateChanged(GameState newGameState)
	{
		System.out.println(newGameState.getText());
	}
	
	public void setScoreAndTime(int score, int time)
	{
		System.out.println("Score: " + score);
	}
}
