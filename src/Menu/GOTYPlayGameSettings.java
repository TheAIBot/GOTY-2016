package Menu;

<<<<<<< HEAD
=======
import java.awt.Component;
import java.awt.GridLayout;
>>>>>>> refs/remotes/origin/Niklas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

import Control.MenuController;
import Model.SuperPage;
import Model.GameModes;
import Model.GameSettings;

public class GOTYPlayGameSettings extends SuperPage {
	private static final GOTYPlay PLAY_GAME = new GOTYPlay();
	private GameSettings theGameSettings = new GameSettings();
	private static final int SOUND_MAX = 100;
	private static final int SOUND_MIN = 0;
	private static final int DIFF_MIN = 0;
	private static final int DIFF_MAX = 3;
	
	//The play button
	@Override
	public JPanel createPage() {
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 MenuController.switchPage(PLAY_GAME);
	         }
	      });
		

		
		//For setting the difficulty settings
		JComboBox gameModeList = new JComboBox(GameModes.values());
		gameModeList.setSelectedItem(GameModes.NORMAL);
		gameModeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				theGameSettings.setGameMode((GameModes)gameModeList.getSelectedItem());
			}
		});
		
		//The code below describes the buttons for mapping the key bindings
		
		//Key binding buttons for player 1
		JToggleButton p1Up = new JToggleButton("Up key: " + theGameSettings.getPlayerOne().getUpKeyName());
		JToggleButton p1Down = new JToggleButton("Down key: " + theGameSettings.getPlayerOne().getDownKeyName());
		JToggleButton p1Left = new JToggleButton("Left key: " + theGameSettings.getPlayerOne().getLeftKeyName());
		JToggleButton p1Right = new JToggleButton("Righ key: " + theGameSettings.getPlayerOne().getRightKeyName());
		
		//Key binding buttons for player 1
		JToggleButton p2Up = new JToggleButton("Up key: " + theGameSettings.getPlayerTwo().getUpKeyName());
		JToggleButton p2Down = new JToggleButton("Down key: " + theGameSettings.getPlayerTwo().getDownKeyName());
		JToggleButton p2Left = new JToggleButton("Left key: " + theGameSettings.getPlayerTwo().getLeftKeyName());
		JToggleButton p2Right = new JToggleButton("Right key: " + theGameSettings.getPlayerTwo().getRightKeyName());
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(2,3));
		p1.add(new JPanel());
		p1.add(p1Up);
		p1.add(new JPanel());
		p1.add(p1Left);
		p1.add(p1Down);
		p1.add(p1Right);
		page.add(p1);
		
		
		//All buttons are added into a ButtonGroup to ensure only one button is selected at a time
		ButtonGroup keyBindingButtons = new ButtonGroup();
		keyBindingButtons.add(p1Up);
		keyBindingButtons.add(p1Down);
		keyBindingButtons.add(p1Left);
		keyBindingButtons.add(p1Right);
		keyBindingButtons.add(p2Up);
		keyBindingButtons.add(p2Down);
		keyBindingButtons.add(p2Left);
		keyBindingButtons.add(p2Right);
		
		//The key listener that maps the key bindings
		KeyListener keyBindingsListener = new KeyListener(){
			
			//Methods not used
			public void keyPressed(KeyEvent e){}
			public void keyTyped(KeyEvent e) {}
			
			
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();

				if (!(theGameSettings.getPlayerOne().hasKeyCode(key) || theGameSettings.getPlayerTwo().hasKeyCode(key))) 
				{
					//Determine which button is selected and map the corresponding key.
					if (p1Up.isSelected()) {
						//Update the key code for the corresponding player
						theGameSettings.getPlayerOne().setUpKeyCode(key);
						//Update the text in the corresponding button.
						p1Up.setText("Up key: " + theGameSettings.getPlayerOne().getUpKeyName());

					} else if (p1Down.isSelected()) {
						theGameSettings.getPlayerOne().setDownKeyCode(key);
						p1Down.setText("Down key: " + theGameSettings.getPlayerOne().getDownKeyName());
					} else if (p1Left.isSelected()) {
						theGameSettings.getPlayerOne().setLeftKeyCode(key);
						p1Left.setText("Left key: " + theGameSettings.getPlayerOne().getLeftKeyName());
					} else if (p1Right.isSelected()) {
						theGameSettings.getPlayerOne().setRightKeyCode(key);
						p1Right.setText("Right key: " + theGameSettings.getPlayerOne().getRightKeyName());
					} else if (p2Up.isSelected()) {
						theGameSettings.getPlayerTwo().setUpKeyCode(key);
						p2Up.setText("Up key: " + theGameSettings.getPlayerTwo().getUpKeyName());
					} else if (p2Down.isSelected()) {
						theGameSettings.getPlayerTwo().setDownKeyCode(key);
						p2Down.setText("Down key: " + theGameSettings.getPlayerTwo().getDownKeyName());
					} else if (p2Left.isSelected()) {
						theGameSettings.getPlayerTwo().setLeftKeyCode(key);
						p2Left.setText("Left key: " + theGameSettings.getPlayerTwo().getLeftKeyName());
					} else if (p2Right.isSelected()) {
						theGameSettings.getPlayerTwo().setRightKeyCode(key);
						p2Right.setText("Right key: " + theGameSettings.getPlayerTwo().getRightKeyName());
					} 
				}
				//Clear selection of button after binding the key
				keyBindingButtons.clearSelection();
			}
			
			
		};
		
		//Add the key listener to all the buttons
		p1Up.addKeyListener(keyBindingsListener);
		p1Down.addKeyListener(keyBindingsListener);
		p1Left.addKeyListener(keyBindingsListener);
		p1Right.addKeyListener(keyBindingsListener);
		p2Up.addKeyListener(keyBindingsListener);
		p2Down.addKeyListener(keyBindingsListener);
		p2Left.addKeyListener(keyBindingsListener);
		p2Right.addKeyListener(keyBindingsListener);
		
		
		//The slider which adjusts the sound level
		JSlider soundLevelSlider = new JSlider(JSlider.HORIZONTAL, SOUND_MIN, SOUND_MAX, (int)(theGameSettings.getSoundVolume()*100));
		soundLevelSlider.setMajorTickSpacing(10);
		soundLevelSlider.setMinorTickSpacing(1);
		
		//The slider which adjusts the difficulty settings
		JSlider diffSlider = new JSlider(JSlider.HORIZONTAL, DIFF_MIN, DIFF_MAX, DIFF_MIN);
		diffSlider.setMajorTickSpacing(1);
		diffSlider.setMinorTickSpacing(1);
		diffSlider.setPaintTicks(true);
		
		Hashtable<Integer, JLabel> diffLabels = new Hashtable<Integer,JLabel>();
		diffLabels.put(0, new JLabel("Easy"));
		diffLabels.put(1, new JLabel("Normal"));
		diffLabels.put(2, new JLabel("Hard"));
		diffLabels.put(3, new JLabel("Very hard"));
		
		diffSlider.setLabelTable(diffLabels);
		diffSlider.setPaintLabels(true);
		
		//Add the components the the page
		page.add(playButton);
		page.add(gameModeList);
		
		//page.add(p1Up);
		//page.add(p1Down);
		//page.add(p1Left);
		//page.add(p1Right);
		
		page.add(p2Up);
		page.add(p2Down);
		page.add(p2Left);
		page.add(p2Right);
		
		page.add(soundLevelSlider);
		page.add(diffSlider);
		
		return page;
	}

	@Override
	public void closePage() {
	}

}
