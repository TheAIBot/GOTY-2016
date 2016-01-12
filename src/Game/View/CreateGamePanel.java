package Game.View;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		gPanelContainer.setLayout(new GridLayout(1, gPanels.length));
		for (int i = 0; i < gPanels.length; i++) {
			gPanelContainer.add(createSingleGamePanel(gPanels[i]));
		}
		return gPanelContainer;
	}
	
	private JPanel createSingleGamePanel(GraphicsPanel gPanel)
	{
		JPanel panelGame = new JPanel();
		panelGame.setLayout(new GridBagLayout());
		
		panelGame.add(gPanel, createConstraint(0, 1, 5, 0, GridBagConstraints.SOUTH, true, GridBagConstraints.BOTH));
		
		JLabel scoreTextLabel = new JLabel("Score: ");
		panelGame.add(scoreTextLabel, createConstraint(1, 0, 1, 1, GridBagConstraints.NORTHEAST, false, GridBagConstraints.NONE));
		
		JLabel scoreLabel = new JLabel("0");
		panelGame.add(scoreLabel, createConstraint(2, 0, 1, 1, GridBagConstraints.NORTHWEST, false, GridBagConstraints.NONE));
		
		JLabel timeTextLabel = new JLabel("Time: ");
		panelGame.add(timeTextLabel, createConstraint(3, 0, 1, 1, GridBagConstraints.NORTHEAST, false, GridBagConstraints.NONE));
		
		JLabel timeLabel = new JLabel("0 s");
		panelGame.add(timeLabel, createConstraint(4, 0, 1, 1, GridBagConstraints.NORTHWEST, false, GridBagConstraints.NONE));		
		
		return panelGame;
	}
	
	private GridBagConstraints createConstraint(int gridX, int gridY, int gridWidth, int gridHeight, int anchor, boolean extraSpace, int fill)
	{
		GridBagConstraints contraint = new GridBagConstraints();
		contraint.gridx = gridX;
		contraint.gridy = gridY;
		if (!extraSpace) {
			contraint.weightx = 0;
			contraint.weighty = 0;
		} else {
			contraint.weightx = 1;
			contraint.weighty = 1;
		}
		contraint.fill = fill;
		contraint.insets = new Insets(5, 5, 5, 5);
		contraint.gridwidth = gridWidth;
		contraint.gridheight = gridHeight;
		contraint.anchor = anchor;
		return contraint;
	}
}
