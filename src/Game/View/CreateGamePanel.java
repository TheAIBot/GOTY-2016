package Game.View;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Game.Model.Board.GameState;

public class CreateGamePanel {
	private JPanel gamePanel;
	private JLabel[] scoreLabels;
	private JLabel[] timeLabels;
	private JLabel[] gameStateLabels;
	
	public JPanel getGamePanel(GraphicsPanel[] gPanels)
	{
		if (gamePanel == null) {
			gamePanel = createGamePanel(gPanels);
		}
		return gamePanel;
	}
	
	private JPanel createGamePanel(GraphicsPanel[] gPanels) {
		scoreLabels = new JLabel[gPanels.length];
		timeLabels = new JLabel[gPanels.length];
		gameStateLabels = new JLabel[gPanels.length];
		
		JPanel gPanelContainer = new JPanel();
		gPanelContainer.setLayout(new GridLayout(1, gPanels.length));
		
		for (int i = 0; i < gPanels.length; i++) {
			gPanelContainer.add(createSingleGamePanel(gPanels[i], i));
		}
		return gPanelContainer;
	}
	
	private JPanel createSingleGamePanel(GraphicsPanel gPanel, int index)
	{
		gPanel.setLayout(new GridLayout(1, 1));
		
		JPanel panelGame = new JPanel();
		panelGame.setLayout(new GridBagLayout());
		
		panelGame.add(gPanel, createConstraint(0, 1, 5, 1, GridBagConstraints.SOUTH, true, GridBagConstraints.BOTH));
		
		JLabel gameStateLabel = new JLabel("", SwingConstants.CENTER);
		Font currentGameStateFont = gameStateLabel.getFont();
		gameStateLabel.setFont(new Font(currentGameStateFont.getName(), currentGameStateFont.getStyle(), 50));
		gPanel.add(gameStateLabel);
		gameStateLabels[index] = gameStateLabel;
		
		JLabel scoreTextLabel = new JLabel("Score: ");
		panelGame.add(scoreTextLabel, createConstraint(1, 0, 1, 1, GridBagConstraints.NORTHEAST, false, GridBagConstraints.NONE));
		
		JLabel scoreLabel = new JLabel("0");
		panelGame.add(scoreLabel, createConstraint(2, 0, 1, 1, GridBagConstraints.NORTHWEST, false, GridBagConstraints.NONE));
		scoreLabels[index] = scoreLabel;
		
		JLabel timeTextLabel = new JLabel("Time: ");
		panelGame.add(timeTextLabel, createConstraint(3, 0, 1, 1, GridBagConstraints.NORTHEAST, false, GridBagConstraints.NONE));
		
		JLabel timeLabel = new JLabel("0 s");
		panelGame.add(timeLabel, createConstraint(4, 0, 1, 1, GridBagConstraints.NORTHWEST, false, GridBagConstraints.NONE));		
		timeLabels[index] = timeLabel;
		
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

	public void setTimeAndScore(int score, int time, int screenIndex)
	{
		if (scoreLabels != null) {
			scoreLabels[screenIndex].setText(String.valueOf(score));
			timeLabels[screenIndex].setText(String.valueOf(time));
			scoreLabels[screenIndex].repaint();
			timeLabels[screenIndex].repaint();
		}
	}

	public void setGameState(GameState newGameState, int screenIndex)
	{
		if (gameStateLabels != null) {
			gameStateLabels[screenIndex].setText(newGameState.getText());
			gameStateLabels[screenIndex].repaint();
		}
	}
}
