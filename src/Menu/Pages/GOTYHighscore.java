package Menu.Pages;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.sun.management.GarbageCollectorMXBean;

import Game.Model.Score.Highscore;
import javafx.util.Pair;

public class GOTYHighscore extends SuperPage {
	
	public GOTYHighscore(PageRequestsListener listener)
	{
		super(listener);
	}
	
	public JPanel createPage()
	{
		
		page.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		JLabel topLabel = new JLabel("HIGHSCORE");
		
		gc.insets = new Insets(10, 10, 10, 10);
	
		page.add(topLabel,gc);
		
		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPage();
			}
		});
		
		gc.ipadx = 0;
		gc.gridy = 2;
		page.add(backButton, gc);
		
		showHighScores(Highscore.getHighscores());
		
		return page;
	}
	
	public void startPage()
	{
		
	}
	
	private void showHighScores(ArrayList<Pair<String, Integer>> scores)
	{
		String[] columnNames = {"Player name",
        "Score"};

		
		Object[][] scoreElements = new Object[scores.size()][2];
		for(int i = 1; i < scores.size(); i++)
		{
			scoreElements[i][0] = scores.get(i).getKey();
			scoreElements[i][1] = scores.get(i).getValue();
		}
		

		JTable table = new JTable(scoreElements, columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 1;
		gc.ipadx = 100;
		page.add(scrollPane, gc);
	}
	
	public void closePage()
	{
	}
}
