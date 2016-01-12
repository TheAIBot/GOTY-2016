package Game.Control.GameEngine;

import javax.swing.JPanel;

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
	private GameEngine gEngine;
	private GraphicsPanel[] gPanels;
	private Animator animator = new Animator(this);
	private CreateGamePanel gamePanelCreater = new CreateGamePanel();
	private JPanel gamePanel;
	RenderInfo renderInfo;
	
	public GraphicsManager(GameEngine gEngine, int numberOfScreens) {
		this.gEngine = gEngine;
		gPanels = new GraphicsPanel[numberOfScreens];
		for (int i = 0; i < gPanels.length; i++) {
			gPanels[i] = new GraphicsPanel(this, i);
		}
	}
	
	public void renderTiles(Tile[] tiles, RenderInfo renderInfo, int screenIndex){
		gPanels[screenIndex].setRenderInfo(tiles, renderInfo);
		this.renderInfo = renderInfo;
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
	
	private void animate(){
		animator.startAnimation(renderInfo.toAnimate);
	}
	
	public Displayable[] getDisplayablesToRender(int screenIndex){
		if (!renderInfo.renderColor) {
			animate();
			return gEngine.getTiles(screenIndex);
		} else return null;
	}
	
	public Numreable[] getNumreablesToRender(int screenIndex){
		return gEngine.getTiles(screenIndex);
	}
	
	public Colorfull[] getColorfullsToRender(int screenIndex){
		if (renderInfo.renderColor) {
			animate();
			animator.startAnimation(renderInfo.toAnimate);
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
}
