package Menu.Pages;

import javax.swing.JPanel;

import Game.Control.GameEngine.GameEngine;
import Game.Control.GameEngine.GameEventsListener;
import Game.Model.Board.GameState;
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
	}

	@Override
	public void startPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canShowPage() {
		if (settings == null) {
			game = GameEngine.load();
			game.addGameEventListener(this);
			page = game.getScreen();
			setFullScreen();
			if (game == null) {
				return false;
			}
		} else {
			game = new GameEngine(settings);
			game.addGameEventListener(this);
			page = game.getScreen();
			game.startGameAsync();
		}
		return true;
	}

	@Override
	public void gameEnded() {
	}

	@Override
	public void hideWindow() {
		hideScreen();
	}

	@Override
	public void showWindow() {
		showScreen();
	}

	@Override
	public void closeGame() {
		settings = null;
		showScreen();
		backPage();
	}

	@Override
	public void gameStarted() {
		setFullScreen();
	}
}
