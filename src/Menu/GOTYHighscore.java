package Menu;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Control.Game;
import Control.GameEngine.Highscore;
import Model.SuperPage;
import javafx.util.Pair;

public class GOTYHighscore extends SuperPage {
	
	public JPanel createPage()
	{
		JLabel topLabel = new JLabel("HIGHSCORE", SwingConstants.CENTER);
		JPanel scoreList = new JPanel();
		scoreList.setLayout(new BoxLayout(scoreList, BoxLayout.Y_AXIS));
		JLabel testScore = new JLabel("adasd");
		JLabel testScore2 = new JLabel("asdadas");
		scoreList.add(testScore);
		scoreList.add((testScore2));
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
<<<<<<< HEAD
		            Game.switchPage(previousPage);
=======
	        	 	backPage();
>>>>>>> 47dd71f3ef5f026d9d4b167e0285e75a6e90bfdd
=======
	        	 	backPage();
>>>>>>> origin/Emil
		         }
		      });
		page.setLayout(new GridLayout(3, 1));
		page.add(topLabel);
		page.add(scoreList);
		page.add(back);
		page.setVisible(true);
		
		return page;
	}
	
	public void startPage(SuperPage prevPage)
	{
		super.startPage(prevPage);
		showHighScores(Highscore.getHighscores());
	}
	
	private void showHighScores(ArrayList<Pair<String, Integer>> scores)
	{
		//TODO fill this out to show scores
	}
	
	public void closePage()
	{
	}
}
