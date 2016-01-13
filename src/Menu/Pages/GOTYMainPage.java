package Menu.Pages;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GOTYMainPage extends SuperPage {
	private final SuperPage highscores;
	private final SuperPage playSettings;

	public GOTYMainPage(PageRequestsListener listener) {
		super(listener);
		playSettings = new GOTYPlayGameSettings(listener);
		highscores = new GOTYHighscore(listener);
	}

	public JPanel createPage() {
		page.setLayout(new GridBagLayout());
		
		//Add title
		Font font = new Font("Verdana", 100, 50);
		JLabel title = new JLabel("Game of the Year 2016");
		title.setFont(font);
		page.add(title, createConstraint(1, 0, 1, 1, GridBagConstraints.CENTER, false, GridBagConstraints.NONE));
		
		//Add menu buttons
		addButtons();
		
		return page;
	}

	private void addButtons() {

		JButton playButton = new JButton("New Game");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPage(playSettings);
			}
		});
		playButton.setPreferredSize(new Dimension(200, 100));

		JButton highscoreButton = new JButton("Highscore");
		highscoreButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 switchPage(highscores);
	         }
	      });
		highscoreButton.setPreferredSize(new Dimension(200, 100));

		JButton loadGameButton = new JButton("Load Game");
		loadGameButton.setPreferredSize(new Dimension(200, 100));

		page.add(playButton, createConstraint(1, 2, 1, 1, GridBagConstraints.CENTER, false, GridBagConstraints.NONE));
		page.add(loadGameButton, createConstraint(1, 3, 1, 1, GridBagConstraints.CENTER, false, GridBagConstraints.NONE));
		page.add(highscoreButton, createConstraint(1, 4, 1, 1, GridBagConstraints.CENTER, false, GridBagConstraints.NONE));
	}

	public void closePage() {
	}

	@Override
	public void startPage() {
	}

	private GridBagConstraints createConstraint(int gridX, int gridY, int gridWidth, int gridHeight, int anchor, boolean extraSpace, int fill) {
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
