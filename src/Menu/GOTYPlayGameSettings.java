package Menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Control.MenuController;
import Model.SuperPage;

public class GOTYPlayGameSettings extends SuperPage {
	private static final GOTYPlay PLAY_GAME = new GOTYPlay();
	
	@Override
	public JPanel createPage() {
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 MenuController.switchPage(PLAY_GAME);
	         }
	      });
		page.add(playButton);
		return page;
	}

	@Override
	public void closePage() {
	}

}
