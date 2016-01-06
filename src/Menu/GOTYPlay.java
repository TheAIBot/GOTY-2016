package Menu;
import graphics.GraphicsPanel;
import graphics.Screen;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ConsoleControl.ConsoleControl;
import GameEngine.GameEngine;
import GameEngine.Directions;

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
		    	//TODO: move input handling to InputManager class (Model-View-Controller principle)
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
		game = new GameEngine(10, screen);
		//ConsoleControl.startGameInConsole(10);
	}

	@Override
	public void closePage() {
		// TODO do something that stops the game
	}

}
