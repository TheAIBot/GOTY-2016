package Game.Control.GameEngine;

import javax.swing.JPanel;

import Game.Model.Board.GameState;
import Game.Model.Board.Tile;
import Game.View.Colorfull;
import Game.View.CreateGamePanel;
import Game.View.Displayable;
import Game.View.GraphicsPanel;
import Game.View.Numreable;
import Game.View.RenderInfo;
import Game.View.Animation.AnimateUpdateListener;
import Game.View.Animation.Animator;
import Game.View.Numreable;

public class GraphicsManager implements AnimateUpdateListener {
	//private ConsoleGraphics console;
	private final GameEngine gEngine;
	private final GraphicsPanel[] gPanels;
	private final Animator animator = new Animator(this);
	private final CreateGamePanel gamePanelCreater = new CreateGamePanel();
	private JPanel gamePanel;
	private final RenderInfo[] renderInfos;
	
	public GraphicsManager(GameEngine gEngine, int numberOfScreens) {
		this.gEngine = gEngine;
		this.gPanels = new GraphicsPanel[numberOfScreens];
		this.renderInfos = new RenderInfo[numberOfScreens];
		for (int i = 0; i < gPanels.length; i++) {
			this.renderInfos[i] = gEngine.getRenderInfo(i);
			gPanels[i] = new GraphicsPanel(this, renderInfos[i], i);
		}
	}
	
	public void renderTiles(Tile[] tiles, RenderInfo renderInfo, int screenIndex){		
		checkForNewAnimations(renderInfo);
		gPanels[screenIndex].repaint();	
	}
	
	public void renderScreen(int screenIndex){
		gPanels[screenIndex].render();
	}
	
	public JPanel getGraphicsPanel(){
		if (gamePanel == null) {
			gamePanel = gamePanelCreater.getGamePanel(gPanels);
		}
		return gamePanel;
	}
	
	private void animate(int screenIndex){
		animator.startAnimation(renderInfos[screenIndex].toAnimate);
	}
	
	public Displayable[] getDisplayablesToRender(int screenIndex){
		if (renderInfos[screenIndex] != null && !renderInfos[screenIndex].renderColor) {
			animate(screenIndex);
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
		} else return null;
	}
	
	public void repaint()
	{
		for (int i = 0; i < gPanels.length; i++) {
			gPanels[i].repaint();
		}
	}

	public void checkForNewAnimations(RenderInfo renderInfo) {
		if (renderInfo.toAnimate.size() > 0) {
			animator.startAnimation(renderInfo.toAnimate);
			renderInfo.toAnimate.clear();
		}
	}

	@Override
	public void animateUpdate() {
		repaint();
	}

	public void setScoreAndTime(int score, int time, int screenIndex)
	{
		gamePanelCreater.setTimeAndScore(score, time, screenIndex);
	}

	public void setGameState(GameState newGameState, int screenIndex)
	{
		gamePanelCreater.setGameState(newGameState, screenIndex);
	}
}
