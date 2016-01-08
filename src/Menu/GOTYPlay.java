package Menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Control.GameEngine.GameEngine;
import Model.GameSettings;
import Model.GraphicsPanel;
import Model.SuperPage;
import View.Screen;


public class GOTYPlay extends SuperPage {
	private static final SuperPage PLAY_GAME_SETTINGS = new GOTYPlayGameSettings();
	private GameEngine game;
	private GraphicsPanel gPanel;
	private Screen screen;
	private GameSettings settings;

	@Override
	public JPanel createPage() {
		gPanel = new GraphicsPanel(400,400);
		screen = new Screen(gPanel.getGImage(), gPanel.getBounds());
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
		game = new GameEngine(settings, screen, gPanel);
		//ConsoleControl.startGameInConsole(10);
	}

	@Override
	public void closePage() {
		// TODO do something that stops the game
	}
}
