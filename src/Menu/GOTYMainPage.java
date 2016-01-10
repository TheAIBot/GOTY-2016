package Menu;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.PageRequestsListener;
import Model.SuperPage;

public class GOTYMainPage extends SuperPage {
	private final SuperPage highscores;
	private final SuperPage playSettings;
	
	public GOTYMainPage(PageRequestsListener listener)
	{
		super(listener);
		playSettings = new GOTYPlayGameSettings(listener);
		highscores = new GOTYHighscore(listener);
	}
	
	public JPanel createPage() {	
		page.setLayout(new GridLayout(3, 1));
		addButtons();
		return page;
	}
	
	private void addButtons(){
		
		JButton playButton = new JButton("Play");		
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
		
		JButton settingsButton = new JButton("Settings");
		settingsButton.setPreferredSize(new Dimension(200, 100));

		page.add(playButton);
		page.add(highscoreButton);
		page.add(settingsButton);
	}

	public void closePage() {
	}

	@Override
	public void startPage() {
	}
}
