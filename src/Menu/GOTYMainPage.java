package Menu;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Control.Game;
import Model.SuperPage;

public class GOTYMainPage extends SuperPage {
<<<<<<< HEAD
	
	private final GOTYHighscore HIGHSCORE_PAGE = new GOTYHighscore();
	private final GOTYPlay PLAY = new GOTYPlay();
=======
	private final SuperPage HIGHSCORE_PAGE = new GOTYHighscore();
	private final SuperPage PLAY_SETTINGS = new GOTYPlayGameSettings();
>>>>>>> Andreas
	
	public JPanel createPage() {
		page.setLayout(new GridLayout(3, 1));
		return page;
	}
	
	private void addButtons(){
		
		JButton playButton = new JButton("Play");		
		playButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
	            Game.switchPage(PLAY);
=======
	        	 MenuController.switchPage(PLAY_SETTINGS);
>>>>>>> Andreas
	         }
	      });
		
		JButton highscoreButton = new JButton("Highscore");
		highscoreButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
	            Game.switchPage(HIGHSCORE_PAGE);
=======
	        	 MenuController.switchPage(HIGHSCORE_PAGE);
>>>>>>> Andreas
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
