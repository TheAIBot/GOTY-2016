package Menu.Pages;

import javax.swing.JPanel;

import Game.Control.GameEngine.GameEngine;
import Game.Model.Difficulty.DifficultyLevel;
import Game.Model.Settings.GameSettings;


public class GOTYPlay extends SuperPage {
	private GameEngine game;
	private GameSettings settings;

	public GOTYPlay(PageRequestsListener listener)
	{
		super(listener);
	}
	
	@Override
	public JPanel createPage() {
		settings.setGameSize(2);
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

	@Override
	public void startPage() {
		// TODO Auto-generated method stub
		
	}
}
