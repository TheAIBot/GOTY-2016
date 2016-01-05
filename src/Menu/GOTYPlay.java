package Menu;
import graphics.GraphicsPanel;
import graphics.Screen;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ConsoleControl.ConsoleControl;
import GameEngine.GameEngine;
import GameEngine.Directions;
import GameEngine.Tile;

public class GOTYPlay extends SuperPage {
	private GameEngine game;
	private GraphicsPanel gPanel;
	private Screen screen;

	@Override
	public JPanel createPage() {
		gPanel = new GraphicsPanel(400,400);
		screen = new Screen(gPanel.getGImage(), gPanel.getImageBounds());
		
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
		gPanel.getActionMap().put("up", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	game.moveVoidTile(Directions.UP);
<<<<<<< HEAD
		    	//TODO: move input handling to KeyBoard class
=======
		    	printGame();
		    	//TODO: move input handling to InputManager class (Model-View-Controller principle)
>>>>>>> Emil
		    }
		});
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
		gPanel.getActionMap().put("down", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	game.moveVoidTile(Directions.DOWN);
		    }
		});
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
		gPanel.getActionMap().put("left", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	game.moveVoidTile(Directions.LEFT);
		    }
		});
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
		gPanel.getActionMap().put("right", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	game.moveVoidTile(Directions.RIGHT);
		    }
		});

		
		return gPanel;
		//return page;
	}

	@Override
	public void startPage(SuperPage prevPage) {
		super.startPage(prevPage);
<<<<<<< HEAD
		game = new GameEngine(10);
		ConsoleControl.startGameInConsole(10);
	}
	
=======
		
		game = new GameEngine(3, screen);		
		printGame();
		
		try (Scanner scan = new Scanner(System.in)) {
			/*while (true) {
				String line = scan.nextLine();
				if (line.length() == 1) {
					char pressed = line.charAt(0);
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
					printGame();
				}
			}*/
		}
	}
	
	public void printGame() {
		Point fisk = new Point(0,0);
		for (int y = 0; y < game.getBoardSize(); y++) {
			for (int x = 0; x < game.getBoardSize(); x++) {
				fisk.move(x, y);
				Tile tile = game.getTileAtPoisition(fisk);
				if (tile == null) {
					System.out.print("   ");
				}
				else {
					System.out.print(" " + String.valueOf(tile.number) + " ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
		game.render();
		gPanel.repaint();
	}
>>>>>>> Emil

	@Override
	public void closePage() {
		// TODO do something that stops the game
	}

}
