package Game.View;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.org.apache.regexp.internal.recompile;

import sun.net.www.content.image.png;

public class CreateGamePanel {
	private JPanel gamePanel;
	
	public JPanel getGamePanel(GraphicsPanel[] gPanels)
	{
		if (gamePanel == null) {
			gamePanel = createGamePanel(gPanels);
		}
		return gamePanel;
	}
	
	private JPanel createGamePanel(GraphicsPanel[] gPanels) {
		JPanel gPanelContainer = new JPanel();
		gPanelContainer.setLayout(new GridLayout(gPanels.length, 1));
		for (int i = 0; i < gPanels.length; i++) {
			gPanelContainer.add(createSingleGamePanel(gPanels[i]));
		}
		return gPanelContainer;
	}
	
	private JPanel createSingleGamePanel(GraphicsPanel gPanel)
	{
		JPanel panelGame = new JPanel();
		panelGame.setLayout(new GridLayout(5, 2));
		
		panelGame.add(gPanel, createConstraint(0, 1, 2, 2, GridBagConstraints.CENTER));
		
		JLabel scoreTextLabel = new JLabel("Score: ");
		panelGame.add(scoreTextLabel, createConstraint(0, 0, 1, 1, GridBagConstraints.NORTHEAST));
		
		JLabel scoreLabel = new JLabel("0");
		panelGame.add(scoreLabel, createConstraint(1, 0, 1, 1, GridBagConstraints.NORTHWEST));
		
		JLabel timeTextLabel = new JLabel("Time: ");
		panelGame.add(timeTextLabel, createConstraint(3, 0, 1, 1, GridBagConstraints.NORTHEAST));
		
		JLabel timeLabel = new JLabel("0 s");
		panelGame.add(timeLabel, createConstraint(4, 0, 1, 1, GridBagConstraints.NORTHWEST));		
		
		return panelGame;
	}
	
	private GridBagConstraints createConstraint(int gridX, int gridY, int gridWidth, int gridHeight, int anchor)
	{
		GridBagConstraints contraint = new GridBagConstraints();
		contraint.gridx = gridX;
		contraint.gridy = gridY;
		contraint.insets = new Insets(5, 5, 5, 5);
		contraint.gridwidth = gridWidth;
		contraint.gridheight = gridHeight;
		contraint.anchor = anchor;
		return contraint;
	}
}
