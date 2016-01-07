package Menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Control.GameEngine.GameEngine;
import Model.Directions;
import Model.GraphicsPanel;
import Model.SuperPage;
import Model.Tile;
import View.Screen;


public class GOTYPlay extends SuperPage {
	private static final SuperPage PLAY_GAME_SETTINGS = new GOTYPlayGameSettings();
	private GameEngine game;
	private GraphicsPanel gPanel;
	private Screen screen;

	@Override
	public JPanel createPage() {
		gPanel = new GraphicsPanel(500,500); //TODO: flyt
		screen = new Screen(gPanel.getGImage(), gPanel.getImageBounds());
		
		
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
		gPanel.getActionMap().put("up", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	game.moveVoidTile(Directions.UP);
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
		
		//---
		final int size = 100;
		
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "w");
		gPanel.getActionMap().put("w", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	screen.addOffset(0, 1 * size);
		    	game.render();
		    	
		    }
		});
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "s");
		gPanel.getActionMap().put("s", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	screen.addOffset(0, -1 * size);
		    	game.render();
		    }
		});
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "a");
		gPanel.getActionMap().put("a", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	screen.addOffset(1 * size, 0);
		    	game.render();
		    }
		});
		gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "d");
		gPanel.getActionMap().put("d", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	screen.addOffset(-1 * size, 0);
		    	game.render();
		    }
		});

		page = gPanel;
		return gPanel;
		//return page;
	}

	@Override
	public void startPage(SuperPage prevPage) {
		super.startPage(prevPage);
		game = new GameEngine(5, screen, gPanel); //TODO: flyt
		//ConsoleControl.startGameInConsole(10);
		game.render();
		//gPanel.repaint();
	}

	@Override
	public void closePage() {
		// TODO do something that stops the game
	}
}
