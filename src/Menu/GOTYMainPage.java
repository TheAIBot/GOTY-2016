package Menu;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GOTYMainPage extends SuperPage {
	private final GOTYHighscore HIGHSCORE_PAGE = new GOTYHighscore();
	private final GOTYPlay PLAY = new GOTYPlay();
	
	public JPanel createPage() {
		page.setLayout(new GridLayout(3, 1));
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            Starter.switchPage(PLAY);
	         }
	      });
		JButton highscoreButton = new JButton("Highscore");
		highscoreButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            Starter.switchPage(HIGHSCORE_PAGE);
	         }
	      });
		JButton settingsButton = new JButton("Settings");

		page.add(playButton);
		page.add(highscoreButton);
		page.add(settingsButton);
		return page;
	}

	public void closePage() {
	}
}
