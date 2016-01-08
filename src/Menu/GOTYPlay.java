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

		gPanel = new GraphicsPanel(400,400);
		screen = new Screen(gPanel.getGImage(), new Rectangle(gPanel.getImageBounds()));

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

		settings.setGameSize(5);
		game = new GameEngine(settings, screen, gPanel);
		gPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	game.windowResized(gPanel.getBounds());
            }
        });
	}

	@Override
	public void closePage() {
		// TODO do something that stops the game
	}
}
