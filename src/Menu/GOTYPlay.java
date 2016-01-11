package Menu;

import javax.swing.JPanel;
import Control.GameEngine.GameEngine;
import Model.DifficultyLevel;
import Model.GameSettings;
import Model.SuperPage;


public class GOTYPlay extends SuperPage {
	private GameEngine game;
	private GameSettings settings;

	@Override
	public JPanel createPage() {
		settings.setGameSize(8);
		settings.setDifficultyLevel(DifficultyLevel.HARD);
		game = new GameEngine(settings);
		page = game.getScreen();
		return page;
	}
	
	public void setGameSettings(GameSettings settings){
		this.settings = settings;
	}

	@Override
	public void closePage() {
		// TODO do something that stops the game
	}
}
