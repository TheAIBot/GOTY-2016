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

public class GOTYPlay extends SuperPage{
	private GameEngine game;

	@Override
	public JPanel createPage() {
		page.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
		page.getActionMap().put("up", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	game.moveVoidTile(Directions.UP);
		    	//TODO: move input handling to KeyBoard class
		    }
		});
		page.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
		page.getActionMap().put("down", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	game.moveVoidTile(Directions.DOWN);
		    }
		});
		page.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
		page.getActionMap().put("left", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	game.moveVoidTile(Directions.LEFT);
		    }
		});
		page.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
		page.getActionMap().put("right", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	game.moveVoidTile(Directions.RIGHT);
		    }
		});
		GraphicsPanel gPanel = new GraphicsPanel(400,400);
		Screen screen = new Screen(gPanel.getGImage(), gPanel.getImageBounds());
		
		return gPanel;
		//return page;
	}

	@Override
	public void startPage(SuperPage prevPage) {
		super.startPage(prevPage);
		game = new GameEngine(10);
		ConsoleControl.startGameInConsole(10);
	}
	

	@Override
	public void closePage() {
		// TODO do something that stops the game
	}

}
