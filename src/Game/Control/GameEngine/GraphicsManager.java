package Game.Control.GameEngine;

import javax.swing.JPanel;

import Game.Model.Animation.AnimateUpdateListener;
import Game.Model.Animation.Animator;
import Game.Model.Board.GameState;
import Game.Model.Settings.GameSettings;
import Game.View.ConsoleGraphics;
import Game.View.CreateGamePanel;
import Game.View.GraphicsPanel;
import Game.View.RenderInfo;
import Game.View.ViewTypes.Colorfull;
import Game.View.ViewTypes.Displayable;
import Game.View.ViewTypes.Numreable;

public class GraphicsManager implements AnimateUpdateListener {
	
	private final GameEngine gEngine;
	private final GraphicsPanel[] gPanels;
	private final ConsoleGraphics consoleDisplay;
	private final Animator animator = new Animator(this);
	private final CreateGamePanel gamePanelCreater = new CreateGamePanel();
	private final RenderInfo[] renderInfos;
	private GameSettings settings;
	private JPanel gamePanel;
	
	/**
	 * 
	 * @param gEngine
	 * @param numberOfScreens
	 * @param settings
	 */
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
	
	/**
	 * Renders the tiles on the screen corresponding to the screen index and checks for animation
	 * with the given RenderInfo.
	 * Works with console mode and GUI mode.
	 * @param renderInfo
	 * @param screenIndex
	 */
	public void renderTiles(RenderInfo renderInfo, int screenIndex){
		if (settings.isConsoleMode()) {
			consoleDisplay.render();			
		} else {
			checkForNewAnimations(renderInfo);
			gPanels[screenIndex].repaint();
		}
	}
	
	/**
	 * Render the screen corresponding to the screen index. Does not check for animation.
	 * @param screenIndex
	 */
	public void renderScreen(int screenIndex){
		if (settings.isConsoleMode()) {
			consoleDisplay.render();
		} else gPanels[screenIndex].repaint();
	}
	
	/**
	 * @return gamePanel
	 */
	public JPanel getGraphicsPanel(){
		if (gamePanel == null) {
			gamePanel = gamePanelCreater.getGamePanel(gPanels);
		}
		return gamePanel;
	}
	
	/**
	 * Animates the screen corresponding to the screen index if needed. 
	 * @param screenIndex
	 */
	private void animate(int screenIndex){
		checkForNewAnimations(renderInfos[screenIndex]);
	}
	
	/**
	 * The Displayables are animated if GUI mode is activated with renderColor set to false.
	 * @param screenIndex
	 * @return Displayables (tiles) corresponding to the screen index.
	 */
	public Displayable[] getDisplayablesToRender(int screenIndex){
		if (renderInfos[screenIndex] != null && !renderInfos[screenIndex].renderColor) {
			//It does not animate anything, if it is in console mode.
			if (!settings.isConsoleMode()) {
				animate(screenIndex);
			}
			return gEngine.getTiles(screenIndex);
		} else return null;
	}
	
	/**
	 * 
	 * @param screenIndex
	 * @return All the Numreable's to be rendered.
	 */
	public Numreable[] getNumreablesToRender(int screenIndex){
		return gEngine.getTiles(screenIndex);
	}
	
	/**
	 *
	 * @param screenIndex
	 * @return All the numreables to be rendered.
	 */
	public Colorfull[] getColorfullsToRender(int screenIndex){
		if (renderInfos[screenIndex] != null && renderInfos[screenIndex].renderColor) {
			animate(screenIndex);
			animator.startAnimation(renderInfos[screenIndex].toAnimate);
			return gEngine.getTiles(screenIndex);
		} else {
			return null;
		}
	}
	
	/**
	 * Activates rendering while checking for console mode og GUI mode
	 */
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
	
	/**
	 * 
	 * @param renderInfo
	 */
	public void checkForNewAnimations(RenderInfo renderInfo) {
		if (!settings.isConsoleMode()) {
			animator.startAnimation(renderInfo.toAnimate);
		}
	}

	/**
	 * Redo rendering 
	 */
	public void animateUpdate() {
		render();
	}
	
	/**
	 * Makes the gamePanelCreater or consoleDisplay show the score and time
	 * @param score
	 * @param time
	 * @param screenIndex
	 */
	public void setScoreAndTime(int score, int time, int screenIndex)
	{
		if (!settings.isConsoleMode()) {
			gamePanelCreater.setTimeAndScore(score, time, screenIndex);
		} else {
			consoleDisplay.setScoreAndTime(score, time);
		}
	}
	
	/**
	 * Sets the state of the game.
	 * @param newGameState
	 * @param screenIndex
	 */
	public void setGameState(GameState newGameState, int screenIndex)
	{
		if (!settings.isConsoleMode()) {
			gamePanelCreater.setGameState(newGameState, screenIndex);
		} else {
			consoleDisplay.gameStateChanged(newGameState);
		}
	}
}
