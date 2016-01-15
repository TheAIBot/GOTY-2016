package Game.Control.GameEngine;

import javax.swing.JPanel;

import Game.Model.Board.GameState;
import Game.Model.Settings.GameSettings;
import Game.View.Colorfull;
import Game.View.ConsoleGraphics;
import Game.View.CreateGamePanel;
import Game.View.Displayable;
import Game.View.GraphicsPanel;
import Game.View.Numreable;
import Game.View.RenderInfo;
import Game.View.Animation.AnimateUpdateListener;
import Game.View.Animation.Animator;

public class GraphicsManager implements AnimateUpdateListener {
	
	private final GameEngine gEngine;
	private final GraphicsPanel[] gPanels;
	private final ConsoleGraphics consoleDisplay;
	private final Animator animator = new Animator(this);
	private final CreateGamePanel gamePanelCreater = new CreateGamePanel();
	private final RenderInfo[] renderInfos;
	private GameSettings settings;
	private JPanel gamePanel;
	
	public GraphicsManager(GameEngine gEngine, int numberOfScreens, GameSettings settings) {
		this.gEngine = gEngine;
		this.consoleDisplay = new ConsoleGraphics(gEngine.getBoardSize(), this);		
		this.settings = settings;
		this.gPanels = new GraphicsPanel[numberOfScreens];
		this.renderInfos = new RenderInfo[numberOfScreens];
		for (int i = 0; i < gPanels.length; i++) {
			this.renderInfos[i] = gEngine.getRenderInfo(i);
			gPanels[i] = new GraphicsPanel(this, renderInfos[i], i);
		}
	}
	
	public void renderTiles(RenderInfo renderInfo, int screenIndex){	
		if (settings.isConsoleMode()) {
			consoleDisplay.render();			
		} else {
			checkForNewAnimations(renderInfo);
			gPanels[screenIndex].repaint();
		}
	}
	
	public void renderScreen(int screenIndex){
		if (settings.isConsoleMode()) {
			consoleDisplay.render();
		} else gPanels[screenIndex].repaint();
	}
	
	public JPanel getGraphicsPanel(){
		if (gamePanel == null) {
			gamePanel = gamePanelCreater.getGamePanel(gPanels);
		}
		return gamePanel;
	}
	
	private void animate(int screenIndex){
		checkForNewAnimations(renderInfos[screenIndex]);
	}
	
	public Displayable[] getDisplayablesToRender(int screenIndex){
		if (renderInfos[screenIndex] != null && !renderInfos[screenIndex].renderColor) {
			//It does not animate anything, if it is in console mode.
			if (!settings.isConsoleMode()) {
				animate(screenIndex);
			}
			return gEngine.getTiles(screenIndex);
		} else return null;
	}
	
	public Numreable[] getNumreablesToRender(int screenIndex){
		return gEngine.getTiles(screenIndex);
	}
	
	public Colorfull[] getColorfullsToRender(int screenIndex){
		if (renderInfos[screenIndex] != null && renderInfos[screenIndex].renderColor) {
			animate(screenIndex);
			animator.startAnimation(renderInfos[screenIndex].toAnimate);
			return gEngine.getTiles(screenIndex);
		} else {
			return null;
		}
	}
	
	public void render()
	{
		if (settings.isConsoleMode()) {
			consoleDisplay.render();
		} else {
			for (int i = 0; i < gPanels.length; i++) {
				gPanels[i].render();
			}			
		}
	}

	public void checkForNewAnimations(RenderInfo renderInfo) {
		if (!settings.isConsoleMode()) {
			animator.startAnimation(renderInfo.toAnimate);
		}
	}

	@Override
	public void animateUpdate() {
		render();
	}

	public void setScoreAndTime(int score, int time, int screenIndex)
	{
		if (!settings.isConsoleMode()) {
			gamePanelCreater.setTimeAndScore(score, time, screenIndex);
		} else {
			consoleDisplay.setScoreAndTime(score, time);
		}
	}

	public void setGameState(GameState newGameState, int screenIndex)
	{
		if (!settings.isConsoleMode()) {
			gamePanelCreater.setGameState(newGameState, screenIndex);
		} else {
			consoleDisplay.gameStateChanged(newGameState);
		}
	}
}
