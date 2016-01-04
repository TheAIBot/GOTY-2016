import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JPanel;

import GameEngine.GameEngine;
import GameEngine.GameEngine.Directions;
import GameEngine.Tile;

public class GOTYPlay extends SuperPage {
	private GameEngine game;

	@Override
	public JPanel createPage() {
		return page;
	}

	@Override
	public void startPage(SuperPage prevPage) {
		game = new GameEngine(3);
		try (Scanner scan = new Scanner(System.in)) {
			while (true) {
				char pressed = scan.nextLine().charAt(0);
				switch (pressed) {
				case 'h':
					game.moveVoidTile(Directions.RIGHT);
					break;
				case 'v':
					game.moveVoidTile(Directions.LEFT);
					break;
				case 'o':
					game.moveVoidTile(Directions.UP);
					break;
				case 'n':
					game.moveVoidTile(Directions.DOWN);
					break;
				}
			}
		}
	}
	
	public void printGame()
	{
		Point fisk = new Point(0,0);
		for (int x = 0; x < game.size; x++) {
			for (int y = 0; y < game.size; y++) {
				fisk.move(x, y);
				Tile tile = game.getTileAtPoisition(fisk);
			}
		}
	}

	@Override
	public void closePage() {
		// TODO do something thatstops the game
	}

}
