package Menu;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Control.Game;
import Model.SuperPage;

public class GOTYMainPage extends SuperPage {
	
	private final GOTYHighscore HIGHSCORE_PAGE = new GOTYHighscore();
	private final GOTYPlay PLAY = new GOTYPlay();
	
	public JPanel createPage() {
		page.setLayout(new GridLayout(3, 1));
		return page;
	}
	
	private void addButtons(){
		
		JButton playButton = new JButton("Play");		
		playButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            Game.switchPage(PLAY);
	         }
	      });
		
		JButton highscoreButton = new JButton("Highscore");
		highscoreButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            Game.switchPage(HIGHSCORE_PAGE);
	         }
	      });
		
		JButton settingsButton = new JButton("Settings");

		page.add(playButton);
		page.add(highscoreButton);
		page.add(settingsButton);
	}

	public void closePage() {
	}
}
