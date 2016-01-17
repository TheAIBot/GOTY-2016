package Menu.Pages;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.scenario.Settings;

import Game.Control.Input.SpecialKeys;
import Game.Model.Board.GameModes;
import Game.Model.Cheat.CheatActivatedListener;
import Game.Model.Cheat.CheatCodes;
import Game.Model.Difficulty.DifficultyLevel;
import Game.Model.Resources.ResourceImages;
import Game.Model.Settings.GameSettings;

public class GOTYPlayGameSettings extends SuperPage implements CheatActivatedListener {
	private final GOTYPlay playGame;
	private SuperPage gameSettingsPage;
	private PageRequestsListener gameSettingsListener;
	private GameSettings theGameSettings = GameSettings.load();
	private CheatCodes cheats;
	
	private JLabel showtileImage;
	private ArrayList<BufferedImage> tileImages;
	private ArrayList<BufferedImage> tileImageThumbNails;
	private int selectedtileImageIndex = 0;
	
	public GOTYPlayGameSettings(PageRequestsListener listener)
	{
		super(listener);
		playGame = new GOTYPlay(listener);
		gameSettingsListener = listener;
	}
	
	//The play button
	@Override
	public JPanel createPage() {
		
		if(theGameSettings == null) {
			theGameSettings = new GameSettings();	
		}
		
		page.setLayout(null);
		
		final JToggleButton p1MoveUp = new JToggleButton("Up: " + theGameSettings.getPlayerOne().getUpKeyName());
		p1MoveUp.setBounds(153, 405, 137, 25);
		page.add(p1MoveUp);
		
		final JToggleButton p1MoveDown = new JToggleButton("Down: " + theGameSettings.getPlayerOne().getDownKeyName());
		p1MoveDown.setBounds(153, 434, 137, 25);
		page.add(p1MoveDown);
		
		final JToggleButton p1MoveRight = new JToggleButton("Right: " + theGameSettings.getPlayerOne().getRightKeyName());
		p1MoveRight.setBounds(294, 434, 137, 25);
		page.add(p1MoveRight);
		
		final JToggleButton p1MoveLeft = new JToggleButton("Left: " + theGameSettings.getPlayerOne().getLeftKeyName());
		p1MoveLeft.setBounds(12, 434, 137, 25);
		page.add(p1MoveLeft);
		
		final JButton playButton = new JButton("PLAY");
		playButton.setBounds(750, 31, 117, 54);
		page.add(playButton);
		
		final JToggleButton p1ViewUp = new JToggleButton("Up: " + theGameSettings.getPlayerOne().getUpViewKeyName());
		p1ViewUp.setBounds(153, 512, 137, 25);
		page.add(p1ViewUp);
		
		final JToggleButton p1ViewDown = new JToggleButton("Down: " + theGameSettings.getPlayerOne().getDownViewKeyName());
		p1ViewDown.setBounds(153, 541, 137, 25);
		page.add(p1ViewDown);
		
		final JToggleButton p1ViewLeft = new JToggleButton("Left: " + theGameSettings.getPlayerOne().getLeftViewKeyName());
		p1ViewLeft.setBounds(12, 541, 137, 25);
		page.add(p1ViewLeft);
		
		final JToggleButton p1ViewRight = new JToggleButton("Right: " + theGameSettings.getPlayerOne().getRightViewKeyName());
		p1ViewRight.setBounds(294, 541, 137, 25);
		page.add(p1ViewRight);
		
		final JToggleButton p2MoveUp = new JToggleButton("Up: " + theGameSettings.getPlayerTwo().getUpKeyName());
		p2MoveUp.setBounds(609, 405, 137, 25);
		page.add(p2MoveUp);
		
		final JToggleButton p2MoveDown = new JToggleButton("Down: " + theGameSettings.getPlayerTwo().getDownKeyName());
		p2MoveDown.setBounds(609, 434, 137, 25);
		page.add(p2MoveDown);
		
		final JToggleButton p2MoveLeft = new JToggleButton("Left: " + theGameSettings.getPlayerTwo().getLeftKeyName());
		p2MoveLeft.setBounds(468, 434, 137, 25);
		page.add(p2MoveLeft);
		
		final JToggleButton p2MoveRight = new JToggleButton("Right: " + theGameSettings.getPlayerTwo().getRightKeyName());
		p2MoveRight.setBounds(750, 434, 137, 25);
		page.add(p2MoveRight);
		
		final JToggleButton p2ViewUp = new JToggleButton("Up: " + theGameSettings.getPlayerTwo().getUpViewKeyName());
		p2ViewUp.setBounds(609, 512, 137, 25);
		page.add(p2ViewUp);
		
		final JToggleButton p2ViewLeft = new JToggleButton("Left: " + theGameSettings.getPlayerTwo().getLeftViewKeyName());
		p2ViewLeft.setBounds(468, 541, 137, 25);
		page.add(p2ViewLeft);
		
		final JToggleButton p2ViewDown = new JToggleButton("Down: " + theGameSettings.getPlayerTwo().getDownViewKeyName());
		p2ViewDown.setBounds(609, 541, 137, 25);
		page.add(p2ViewDown);
		
		final JToggleButton p2ViewRight = new JToggleButton("Right: " + theGameSettings.getPlayerTwo().getRightViewKeyName());
		p2ViewRight.setBounds(750, 541, 137, 25);
		page.add(p2ViewRight);
		
		final JButton backButton = new JButton("BACK");
		backButton.setBounds(32, 31, 117, 54);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPage();
			}
		});
		page.add(backButton);
		
		final JToggleButton p1ColorMode = new JToggleButton("Color mode: " + theGameSettings.getPlayerOne().getToggleColorKeyName());
		p1ColorMode.setBounds(12, 579, 137, 25);
		page.add(p1ColorMode);
		
		final JToggleButton p1ZoomIn = new JToggleButton("Zoom in: " + theGameSettings.getPlayerOne().getZoomInKeyName());
		p1ZoomIn.setBounds(153, 579, 137, 25);
		page.add(p1ZoomIn);
		
		final JToggleButton p1ZoomOut = new JToggleButton("Zoom out: " + theGameSettings.getPlayerOne().getZoomOutKeyName());
		p1ZoomOut.setBounds(294, 579, 137, 25);
		page.add(p1ZoomOut);
		
		final JToggleButton p2ColorMode = new JToggleButton("Color mode: " + theGameSettings.getPlayerTwo().getToggleColorKeyName());
		p2ColorMode.setBounds(468, 579, 137, 25);
		page.add(p2ColorMode);
		
		final JToggleButton p2ZoomIn = new JToggleButton("Zoom in: " + theGameSettings.getPlayerTwo().getZoomInKeyName());
		p2ZoomIn.setBounds(609, 579, 137, 25);
		page.add(p2ZoomIn);
		
		final JToggleButton p2ZoomOut = new JToggleButton("Zoom out: " + theGameSettings.getPlayerTwo().getZoomOutKeyName());
		p2ZoomOut.setBounds(750, 579, 137, 25);
		page.add(p2ZoomOut);
		
		final JButton saveButton = new JButton("Save settings");
		saveButton.setBounds(32, 101, 117, 54);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theGameSettings.save();
				
			}
		});
		
		page.add(saveButton);
		
		final JTextField sizeField = new JTextField();
		sizeField.setBounds(395, 195, 116, 22);
		page.add(sizeField);
		sizeField.setColumns(10);
		
		//The slider which adjusts the difficulty settings
		JSlider diffSlider = new JSlider(JSlider.HORIZONTAL, GameSettings.DIFF_MIN, GameSettings.DIFF_MAX, GameSettings.DIFF_MIN);
		diffSlider.setBounds(300, 244, 300, 46);
		diffSlider.setMajorTickSpacing(1);
		diffSlider.setMinorTickSpacing(1);
		diffSlider.setPaintTicks(true);
		
		diffSlider.setValue(theGameSettings.getDifficultyLevel().getValue());
		
		Hashtable<Integer, JLabel> diffLabels = new Hashtable<Integer,JLabel>();
		diffLabels.put(0, new JLabel(DifficultyLevel.EASY.toString()));
		diffLabels.put(1, new JLabel(DifficultyLevel.NORMAL.toString()));
		diffLabels.put(2, new JLabel(DifficultyLevel.INTERMEDIATE.toString()));
		diffLabels.put(3, new JLabel(DifficultyLevel.HARD.toString()));
		
		diffSlider.setLabelTable(diffLabels);
		diffSlider.setPaintLabels(true);
		
		diffSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider)e.getSource();
				int difficulty = slider.getValue();
				switch(difficulty)
				{
				case 0:
					theGameSettings.setDifficultyLevel(DifficultyLevel.EASY);
					System.out.println(theGameSettings.getDifficultyLevel().toString());
					break;
				case 1:
					theGameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);
					System.out.println(theGameSettings.getDifficultyLevel().toString());
					break;
				case 2: 
					theGameSettings.setDifficultyLevel(DifficultyLevel.INTERMEDIATE);
					System.out.println(theGameSettings.getDifficultyLevel().toString());
					break;
				case 3:
					theGameSettings.setDifficultyLevel(DifficultyLevel.HARD);
					System.out.println(theGameSettings.getDifficultyLevel().toString());
					break;
				}
			}
		});
		
		page.add(diffSlider);
		
		
		JSlider volSlider = new JSlider(JSlider.HORIZONTAL, GameSettings.SOUND_MIN, GameSettings.SOUND_MAX, (int)(theGameSettings.getSoundVolume()*100));
		volSlider.setBounds(300, 335, 300, 40);
		volSlider.setMajorTickSpacing(10);
		volSlider.setPaintTicks(true);
		page.add(volSlider);
		
		volSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider)e.getSource();
				float theVolume = (float)slider.getValue()/100.0f;
				theGameSettings.setSoundVolume(theVolume);
				System.out.println(theGameSettings.getSoundVolume());
			}
		});
		
		final JComboBox modeBox = new JComboBox(theGameSettings.getGameMode().values());
		modeBox.setBounds(395, 125, 125, 22);
		page.add(modeBox);
		
		JLabel lblGameMode = new JLabel("Game mode");
		lblGameMode.setBounds(394, 105, 83, 16);
		page.add(lblGameMode);
		
		JLabel lblMapSize = new JLabel("Map size");
		lblMapSize.setBounds(395, 175, 56, 16);
		page.add(lblMapSize);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setBounds(300, 226, 56, 16);
		page.add(lblDifficulty);
		
		JLabel lblSoundLevel = new JLabel("Sound level");
		lblSoundLevel.setBounds(300, 320, 98, 16);
		page.add(lblSoundLevel);
		
		JLabel lblPlayer1Movement = new JLabel("Player 1 movement");
		lblPlayer1Movement.setBounds(172, 376, 116, 16);
		page.add(lblPlayer1Movement);
		
		JLabel lblPlayer1View = new JLabel("Player 1 view");
		lblPlayer1View.setBounds(172, 483, 116, 16);
		page.add(lblPlayer1View);
		
		JLabel lblPlayer2Movement = new JLabel("Player 2 movement");
		lblPlayer2Movement.setBounds(623, 376, 116, 16);
		page.add(lblPlayer2Movement);
		
		JLabel lblPlayer2View = new JLabel("Player 2 view");
		lblPlayer2View.setBounds(630, 483, 116, 16);
		page.add(lblPlayer2View);
		
		JLabel lblPlayerName1 = new JLabel("Player 1 name:");
		lblPlayerName1.setBounds(174, 638, 96, 16);
		page.add(lblPlayerName1);
		
		JLabel lblPlayerName2 = new JLabel("Player 2 name:");
		lblPlayerName2.setBounds(630, 638, 96, 16);
		page.add(lblPlayerName2);
		
		final JCheckBox setRandomize = new JCheckBox("Randomize");
		setRandomize.setBounds(750, 96, 117, 25);
		setRandomize.setSelected(theGameSettings.isRandomized());
		setRandomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theGameSettings.setIsRandomize(setRandomize.isSelected());
				System.out.println(theGameSettings.isRandomized());
			}
		});
		page.add(setRandomize);
		
		final JTextField p1Name = new JTextField();
		p1Name.setText(theGameSettings.getPlayerOne().getName());
		p1Name.setBounds(153, 667, 137, 22);
		page.add(p1Name);
		p1Name.setColumns(10);
		p1Name.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				theGameSettings.getPlayerOne().setName(p1Name.getText());
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});
		
		final JTextField p2Name = new JTextField();
		p2Name.setText(theGameSettings.getPlayerTwo().getName());
		p2Name.setBounds(609, 667, 137, 22);
		page.add(p2Name);
		p2Name.setColumns(10);
		p2Name.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				theGameSettings.getPlayerTwo().setName(p2Name.getText());
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});
		
		tileImages = ResourceImages.getDefaultImages();
		tileImageThumbNails = ResourceImages.convertToThumbNails(tileImages);
		showtileImage = new JLabel();
		showTileImage();
		
		
		JButton prevImage = new JButton("<-");
		prevImage.setPreferredSize(new Dimension(30, 100));
		prevImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				moveSelectedImageIndex(-1);
				showTileImage();				
			}
		});
		JButton nextImage = new JButton("->");
		nextImage.setPreferredSize(new Dimension(30, 100));
		nextImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				moveSelectedImageIndex(1);
				showTileImage();				
			}
		});
		JPanel setTileImagePanel = new JPanel();
		setTileImagePanel.setLayout(new GridLayout(1, 3));
		setTileImagePanel.add(prevImage);
		setTileImagePanel.add(showtileImage);
		setTileImagePanel.add(nextImage);
		
		
		setTileImagePanel.setBounds(300, 650, 300, 100);
		page.add(setTileImagePanel);
		
		
		playButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 playGame.setGameSettings(theGameSettings);
	        	 switchPage(playGame);
	         }
	      });
		
		modeBox.setSelectedItem(theGameSettings.getGameMode());
		modeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				theGameSettings.setGameMode((GameModes)modeBox.getSelectedItem());
			}
		});
		//All buttons are added into a ButtonGroup to ensure only one button is selected at a time
		final ButtonGroup keyBindingButtons = new ButtonGroup();
		keyBindingButtons.add(p1MoveUp);
		keyBindingButtons.add(p1MoveDown);
		keyBindingButtons.add(p1MoveLeft);
		keyBindingButtons.add(p1MoveRight);
		keyBindingButtons.add(p1ViewUp);
		keyBindingButtons.add(p1ViewDown);
		keyBindingButtons.add(p1ViewLeft);
		keyBindingButtons.add(p1ViewRight);
		keyBindingButtons.add(p2MoveUp);
		keyBindingButtons.add(p2MoveDown);
		keyBindingButtons.add(p2MoveLeft);
		keyBindingButtons.add(p2MoveRight);
		keyBindingButtons.add(p2ViewUp);
		keyBindingButtons.add(p2ViewDown);
		keyBindingButtons.add(p2ViewLeft);
		keyBindingButtons.add(p2ViewRight);
		keyBindingButtons.add(p1ZoomIn);
		keyBindingButtons.add(p1ZoomOut);
		keyBindingButtons.add(p1ColorMode);
		keyBindingButtons.add(p2ZoomIn);
		keyBindingButtons.add(p2ZoomOut);
		keyBindingButtons.add(p2ColorMode);
		
		//The key listener that maps the key bindings
		KeyListener keyBindingsListener = new KeyListener(){
			
			//Methods not used
			public void keyPressed(KeyEvent e){}
			public void keyTyped(KeyEvent e) {}
			
			
			//TODO UPDATE METHOD to handle camera controls
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (!((theGameSettings.getPlayerOne().hasKeyCode(key) && 
						theGameSettings.getGameMode() == GameModes.MULTI_PLAYER)  || 
						((theGameSettings.getPlayerOne().hasKeyCode(key) || 
								theGameSettings.getPlayerTwo().hasKeyCode(key) && 
								theGameSettings.getGameMode() == GameModes.SINGLE_PLAYER))) && 
						!SpecialKeys.isSpecialKey(KeyEvent.getKeyText(key).toUpperCase())) 
				{
					//Determine which button is selected and map the corresponding key.
					if (p1MoveUp.isSelected()) {
						//Update the key code for the corresponding player
						theGameSettings.getPlayerOne().setUpKeyCode(key);
						//Update the text in the corresponding button.
						p1MoveUp.setText("Up: " + theGameSettings.getPlayerOne().getUpKeyName());
					} else if (p1MoveDown.isSelected()) {
						theGameSettings.getPlayerOne().setDownKeyCode(key);
						p1MoveDown.setText("Down: " + theGameSettings.getPlayerOne().getDownKeyName());
					} else if (p1MoveLeft.isSelected()) {
						theGameSettings.getPlayerOne().setLeftKeyCode(key);
						p1MoveLeft.setText("Left: " + theGameSettings.getPlayerOne().getLeftKeyName());
					} else if (p1MoveRight.isSelected()) {
						theGameSettings.getPlayerOne().setRightKeyCode(key);
						p1MoveRight.setText("Right: " + theGameSettings.getPlayerOne().getRightKeyName());
					} else if (p1ViewUp.isSelected()) {
						theGameSettings.getPlayerOne().setUpViewKeyCode(key);
						p1ViewUp.setText("Up: " + theGameSettings.getPlayerOne().getUpViewKeyName());
					} else if (p1ViewDown.isSelected()) {
						theGameSettings.getPlayerOne().setDownViewKeyCode(key);
						p1ViewDown.setText("Down: " + theGameSettings.getPlayerOne().getDownViewKeyName());
					} else if (p1ViewLeft.isSelected()) {
						theGameSettings.getPlayerOne().setLeftViewKeyCode(key);
						p1ViewLeft.setText("Left: " + theGameSettings.getPlayerOne().getLeftViewKeyName());
					} else if (p1ViewRight.isSelected()) {
						theGameSettings.getPlayerOne().setRightViewKeyCode(key);
						p1ViewRight.setText("Right: " + theGameSettings.getPlayerOne().getRightViewKeyName());
					} else if(p1ZoomIn.isSelected()){
						theGameSettings.getPlayerOne().setZoomInKeyCode(key);
						p1ZoomIn.setText("Zoom in: " + theGameSettings.getPlayerOne().getZoomInKeyName());
					} else if(p1ZoomOut.isSelected()){
						theGameSettings.getPlayerOne().setZoomOutKeyCode(key);
						p1ZoomOut.setText("Zoom out: " + theGameSettings.getPlayerOne().getZoomOutKeyName());
					} else if(p1ColorMode.isSelected()){
						theGameSettings.getPlayerOne().setToggleColorKey(key);
						p1ColorMode.setText("Color mode: " + theGameSettings.getPlayerOne().getToggleColorKeyName());
					} else if (p2MoveUp.isSelected()) {
						theGameSettings.getPlayerTwo().setUpKeyCode(key);
						p2MoveUp.setText("Up: " + theGameSettings.getPlayerTwo().getUpKeyName());
					} else if (p2MoveDown.isSelected()) {
						theGameSettings.getPlayerTwo().setDownKeyCode(key);
						p2MoveDown.setText("Down: " + theGameSettings.getPlayerTwo().getDownKeyName());
					} else if (p2MoveLeft.isSelected()) {
						theGameSettings.getPlayerTwo().setLeftKeyCode(key);
						p2MoveLeft.setText("Left: " + theGameSettings.getPlayerTwo().getLeftKeyName());
					} else if (p2MoveRight.isSelected()) {
						theGameSettings.getPlayerTwo().setRightKeyCode(key);
						p2MoveRight.setText("Right: " + theGameSettings.getPlayerTwo().getRightKeyName());
					} else if (p2ViewUp.isSelected()) {
						theGameSettings.getPlayerTwo().setUpViewKeyCode(key);
						p2ViewUp.setText("Up: " + theGameSettings.getPlayerTwo().getUpViewKeyName());
					} else if (p2ViewDown.isSelected()) {
						theGameSettings.getPlayerTwo().setDownViewKeyCode(key);
						p2ViewDown.setText("Down: " + theGameSettings.getPlayerTwo().getDownViewKeyName());
					} else if (p2ViewLeft.isSelected()) {
						theGameSettings.getPlayerTwo().setLeftViewKeyCode(key);
						p2ViewLeft.setText("Left: " + theGameSettings.getPlayerTwo().getLeftViewKeyName());
					} else if (p2ViewRight.isSelected()) {
						theGameSettings.getPlayerTwo().setRightViewKeyCode(key);
						p2ViewRight.setText("Right: " + theGameSettings.getPlayerTwo().getRightViewKeyName());
					} else if(p2ZoomIn.isSelected()){
						theGameSettings.getPlayerTwo().setZoomInKeyCode(key);
						p2ZoomIn.setText("Zoom in: " + theGameSettings.getPlayerTwo().getZoomInKeyName());
					} else if(p2ZoomOut.isSelected()){
						theGameSettings.getPlayerTwo().setZoomOutKeyCode(key);
						p2ZoomOut.setText("Zoom out: " + theGameSettings.getPlayerTwo().getZoomOutKeyName());
					} else if(p2ColorMode.isSelected()){
						theGameSettings.getPlayerTwo().setToggleColorKey(key);
						p2ColorMode.setText("Color mode: " + theGameSettings.getPlayerTwo().getToggleColorKeyName());
					}
					
				}
				//Clear selection of button after binding the key
				keyBindingButtons.clearSelection();
			}
			
			
		};
		

		//Add the key listener to all the buttons
		p1MoveUp.addKeyListener(keyBindingsListener);
		p1MoveDown.addKeyListener(keyBindingsListener);
		p1MoveLeft.addKeyListener(keyBindingsListener);
		p1MoveRight.addKeyListener(keyBindingsListener);
		p1ViewUp.addKeyListener(keyBindingsListener);
		p1ViewDown.addKeyListener(keyBindingsListener);
		p1ViewLeft.addKeyListener(keyBindingsListener);
		p1ViewRight.addKeyListener(keyBindingsListener);
		p1ColorMode.addKeyListener(keyBindingsListener);
		p1ZoomIn.addKeyListener(keyBindingsListener);
		p1ZoomOut.addKeyListener(keyBindingsListener);
		
		p2MoveUp.addKeyListener(keyBindingsListener);
		p2MoveDown.addKeyListener(keyBindingsListener);
		p2MoveLeft.addKeyListener(keyBindingsListener);
		p2MoveRight.addKeyListener(keyBindingsListener);
		p2ViewUp.addKeyListener(keyBindingsListener);
		p2ViewDown.addKeyListener(keyBindingsListener);
		p2ViewLeft.addKeyListener(keyBindingsListener);
		p2ViewRight.addKeyListener(keyBindingsListener);
		p2ColorMode.addKeyListener(keyBindingsListener);
		p2ZoomIn.addKeyListener(keyBindingsListener);
		p2ZoomOut.addKeyListener(keyBindingsListener);
		
		
		//The text field which adjusts the size of the map
		sizeField.setText("" + theGameSettings.getGameSize());
		sizeField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				
				int previousSize = theGameSettings.getGameSize();
				int nextSize;
				try {
					nextSize = Integer.parseInt(sizeField.getText());
					if(nextSize > GameSettings.SIZE_MAX || nextSize < GameSettings.SIZE_MIN){
						throw new Exception();
					}
					theGameSettings.setGameSize(nextSize);
				} catch (Exception e2) {
					sizeField.setText("" + previousSize);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton resetSettingsButton = new JButton("Reset settings");
		resetSettingsButton.setBounds(32, 171, 117, 54);
		resetSettingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameSettingsPage = new GOTYPlayGameSettings(gameSettingsListener);
				theGameSettings = new GameSettings();
				switchPage(gameSettingsPage);
			}
		});
		page.add(resetSettingsButton);
		
		
		addCheats();
		
		return page;
	}
	
	private void addCheats()
	{
		cheats = new CheatCodes(this, page);
		cheats.addNewCheatCode(new String[] {
				"UP",
				"UP",
				"DOWN",
				"DOWN",
				"LEFT",
				"RIGHT",
				"LEFT",
				"RIGHT",
				"B",
				"A"
		}, CheatCodes.KONAMI_CODE, false);
	}
	
	private void showTileImage()
	{
		ImageIcon tileImage = new ImageIcon(tileImageThumbNails.get(selectedtileImageIndex));
		showtileImage.setIcon(tileImage);
		theGameSettings.setTileImage(tileImages.get(selectedtileImageIndex));
	}
	
	private void moveSelectedImageIndex(int move)
	{
		selectedtileImageIndex += move;
		if (selectedtileImageIndex < 0) {
			selectedtileImageIndex = tileImages.size() - 1;
		}else if (selectedtileImageIndex == tileImages.size()) {
			selectedtileImageIndex = 0;
		}
	}
	
	@Override
	public void startPage() {
		resize(new Dimension(920,900));
		setResizeable(false);
		theGameSettings.setPaused(false);
	}

	@Override
	public void closePage() {
		setResizeable(true);
		
	}
	
	@Override
	public void cheatActivated(String cheatName) {
		if (cheatName.equals(CheatCodes.KONAMI_CODE)) {
			ArrayList<BufferedImage> konamiImages = ResourceImages.getAllImagesFromDirectory(ResourceImages.ANIME_DIRECTORY_PATH);
			if (konamiImages != null) {
				tileImages = konamiImages;
				tileImageThumbNails = ResourceImages.convertToThumbNails(tileImages);
				selectedtileImageIndex = tileImages.size() - 1;
				showTileImage();
			}
		}
	}

	@Override
	public boolean canShowPage() {
		return true;		
	}
}
