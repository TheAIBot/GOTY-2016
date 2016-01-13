package Menu.Pages;

import javax.swing.JPanel;

import Game.Control.GameEngine.GameEngine;
import Game.Control.GameEngine.GameEventsListener;
import Game.Model.Board.GameState;
import Game.Model.Difficulty.DifficultyLevel;
import Game.Model.Settings.GameSettings;


public class GOTYPlay extends SuperPage implements GameEventsListener {
	private GameEngine game;
	private GameSettings settings;

	public GOTYPlay(PageRequestsListener listener)
	{
		super(listener);
	}
	
	@Override
	public JPanel createPage() {
		//settings.setDifficultyLevel(DifficultyLevel.EASY);
		page = game.getScreen();
		return page;
	}
	
	public void setGameSettings(GameSettings settings){
		this.settings = settings;
	}

	@Override
	public void closePage() {
		if (game != null && game.getGameState() == GameState.NOT_DECIDED_YET) {
			game.save();
		}
		settings = null;
	}

	@Override
	public void startPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canShowPage() {
		if (settings == null) {
			game = GameEngine.load();
			if (game == null) {
				return false;
			}
		} else {
			settings.setGameSize(2);
			game = new GameEngine(settings);
		}
		return true;
	}

	@Override
	public void gameEnded() {
	}

	@Override
	public void hideWindow() {
	}

	@Override
	public void showWindow() {
	}

	@Override
	public void closeGame() {
		backPage();
	}
}
