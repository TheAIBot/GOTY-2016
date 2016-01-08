package Menu;

import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import Control.GameEngine.GameEngine;
import Model.GameSettings;
import Model.GraphicsPanel;
import Model.SuperPage;
import View.Screen;


public class GOTYPlay extends SuperPage {
	private GameEngine game;
	private GraphicsPanel gPanel;
	private Screen screen;
	private GameSettings settings;

	@Override
	public JPanel createPage() {
<<<<<<< HEAD
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

=======
		gPanel = new GraphicsPanel(400,400);
		screen = new Screen(gPanel.getGImage(), new Rectangle(gPanel.getImageBounds()));
>>>>>>> refs/remotes/origin/Dev
		page = gPanel;
		return gPanel;
		//return page;
	}
	
	public void setGameSettings(GameSettings settings){
		this.settings = settings;
	}

	@Override
	public void startPage(SuperPage prevPage) {
		super.startPage(prevPage);
<<<<<<< HEAD
		game = new GameEngine(5, screen, gPanel); //TODO: flyt
		//ConsoleControl.startGameInConsole(10);
		game.render();
		//gPanel.repaint();
=======
		settings.setGameSize(5);
		game = new GameEngine(settings, screen, gPanel);
		gPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	game.windowResized(gPanel.getBounds());
            }
        });
>>>>>>> refs/remotes/origin/Dev
	}

	@Override
	public void closePage() {
		// TODO do something that stops the game
	}
}
