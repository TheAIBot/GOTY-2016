package Menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.PlainDocument;

import com.sun.glass.ui.Pixels.Format;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.xml.internal.txw2.Document;

import Control.MenuController;
import Model.GameModes;
import Model.GameSettings;
import Model.SuperPage;
import javafx.util.converter.NumberStringConverter;

public class GOTYPlayGameSettings extends SuperPage {
	private static final GOTYPlay PLAY_GAME = new GOTYPlay();
	private GameSettings theGameSettings = new GameSettings();
	
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
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(2,3));
		p2.add(new JPanel());
		p2.add(p2Up);
		p2.add(new JPanel());
		p2.add(p2Left);
		p2.add(p2Down);
		p2.add(p2Right);
		
		JPanel playerControls = new JPanel();
		playerControls.setLayout(new BorderLayout());
		playerControls.add(p1, BorderLayout.WEST);
		playerControls.add(p2, BorderLayout.EAST);
		playerControls.add(playButton, BorderLayout.CENTER);
		
		
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
		
		
		//The text field which adjusts the size of the map
		JTextField sizeField = new JTextField(10);
		sizeField.setText("" + theGameSettings.getGameSize());
		sizeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int previousSize = theGameSettings.getGameSize();
				int nextSize;
				try {
					nextSize = Integer.parseInt(sizeField.getText());
					if(nextSize > GameSettings.SIZE_MAX || nextSize < GameSettings.SIZE_MIN)
						throw new Exception();
					theGameSettings.setGameSize(nextSize);
				} catch (Exception e2) {
					sizeField.setText("" + previousSize);
				}
			}
		});
		
		
		//The panel which contains the size slider and the corresponding label
		JPanel sizeFieldPanel = new JPanel();
		sizeFieldPanel.setLayout(new GridBagLayout());
		JLabel sizeFieldLabel = new JLabel("Game size from 3 to 100 (press enter to confirm)");
		
		GridBagConstraints gcSizePanel = new GridBagConstraints();
		
		gcSizePanel.gridx=0;
		gcSizePanel.gridy=0;
		sizeFieldPanel.add(sizeFieldLabel, gcSizePanel);
		
		gcSizePanel.gridx=0;
		gcSizePanel.gridy=1;
		sizeFieldPanel.add(sizeField, gcSizePanel);
		
		
		//The slider which adjusts the sound level
		JSlider soundLevelSlider = new JSlider(JSlider.HORIZONTAL, GameSettings.SOUND_MIN, GameSettings.SOUND_MAX, (int)(theGameSettings.getSoundVolume()*100));
		soundLevelSlider.setMajorTickSpacing(10);
		soundLevelSlider.setMinorTickSpacing(1);
		
		
		soundLevelSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider)e.getSource();
				float theVolume = (float)slider.getValue()/100.0f;
				theGameSettings.setSoundVolume(theVolume);
				System.out.println(theGameSettings.getSoundVolume());
			}
		});
		
		JPanel soundSliderPanel = new JPanel();
		JLabel soundLevelSliderLabel = new JLabel("Sound level");
		
		soundSliderPanel.setLayout(new GridBagLayout());
		GridBagConstraints gcSoundPanel = new GridBagConstraints();
		
		gcSoundPanel.gridx=0;
		gcSoundPanel.gridy=0;
		soundSliderPanel.add(soundLevelSliderLabel,gcSoundPanel);
		gcSoundPanel.gridx=0;
		gcSoundPanel.gridy=1;
		soundSliderPanel.add(soundLevelSlider,gcSoundPanel);
		
		//The slider which adjusts the difficulty settings
		JSlider diffSlider = new JSlider(JSlider.HORIZONTAL, GameSettings.DIFF_MIN, GameSettings.DIFF_MAX, GameSettings.DIFF_MIN);
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
		
		JPanel diffSliderPanel = new JPanel();
		JLabel diffSliderLabel = new JLabel("Difficulty");
		
		diffSliderPanel.setLayout(new GridBagLayout());
		GridBagConstraints gcDiffPanel = new GridBagConstraints();
		
		gcDiffPanel.gridx=0;
		gcDiffPanel.gridy=0;
		diffSliderPanel.add(diffSliderLabel,gcDiffPanel);
		gcDiffPanel.gridx=0;
		gcDiffPanel.gridy=1;
		diffSliderPanel.add(diffSlider,gcDiffPanel);
		
		
		page.setLayout(new GridBagLayout());
		
		GridBagConstraints gcMenu = new GridBagConstraints();
		gcMenu.insets = new Insets(20,20,20,20);
		
		gcMenu.gridx = 0;
		gcMenu.gridy = 0;
		page.add(playButton,gcMenu);
		
		gcMenu.gridx = 0;
		gcMenu.gridy = 1;
		page.add(gameModeList,gcMenu);
		
		gcMenu.gridx = 0;
		gcMenu.gridy = 2;
		gcMenu.ipadx = 200;
		page.add(sizeFieldPanel,gcMenu);
		
		gcMenu.gridx = 0;
		gcMenu.gridy = 3;
		gcMenu.ipadx = 200;
		page.add(diffSliderPanel,gcMenu);
		
		gcMenu.gridx = 0;
		gcMenu.gridy = 4;
		page.add(soundSliderPanel,gcMenu);
		
		gcMenu.gridx = 0;
		gcMenu.gridy = 5;
		gcMenu.fill = gcMenu.ipadx = 0;
		page.add(p1,gcMenu);
		
		gcMenu.gridx = 0;
		gcMenu.gridy = 6;
		page.add(p2,gcMenu);
		
		
		
		return page;
	}

	@Override
	public void closePage() {
		// TODO Auto-generated method stub
		
	}

}
