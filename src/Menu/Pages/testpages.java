package Menu.Pages;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class testpages {
	private JTextField textField;
	private JTextField p1Name;
	private JTextField p2Name;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createWindow()
	{
		JFrame frame = new JFrame("test");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{137, 137, 157, 137, 137, 137, 0};
		gridBagLayout.rowHeights = new int[]{31, 54, 25, 30, 16, 22, 16, 26, 16, 26, 16, 25, 25, 16, 25, 25, 25, 34, 16, 22, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnBack = new JButton("BACK");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.BOTH;
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 1;
		frame.getContentPane().add(btnBack, gbc_btnBack);
		
		JButton btnNewButton = new JButton("PLAY");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 1;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton saveButton = new JButton("Save settings");
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.anchor = GridBagConstraints.EAST;
		gbc_saveButton.fill = GridBagConstraints.VERTICAL;
		gbc_saveButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveButton.gridheight = 2;
		gbc_saveButton.gridx = 0;
		gbc_saveButton.gridy = 2;
		frame.getContentPane().add(saveButton, gbc_saveButton);
		
		JLabel lblGameMode = new JLabel("Game mode");
		GridBagConstraints gbc_lblGameMode = new GridBagConstraints();
		gbc_lblGameMode.anchor = GridBagConstraints.SOUTH;
		gbc_lblGameMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblGameMode.gridwidth = 2;
		gbc_lblGameMode.gridx = 2;
		gbc_lblGameMode.gridy = 2;
		frame.getContentPane().add(lblGameMode, gbc_lblGameMode);
		
		JCheckBox setRandomize = new JCheckBox("Randomize");
		GridBagConstraints gbc_setRandomize = new GridBagConstraints();
		gbc_setRandomize.anchor = GridBagConstraints.NORTH;
		gbc_setRandomize.fill = GridBagConstraints.HORIZONTAL;
		gbc_setRandomize.insets = new Insets(0, 0, 5, 0);
		gbc_setRandomize.gridx = 5;
		gbc_setRandomize.gridy = 2;
		frame.getContentPane().add(setRandomize, gbc_setRandomize);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTHEAST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		JLabel lblMapSize = new JLabel("Map size");
		GridBagConstraints gbc_lblMapSize = new GridBagConstraints();
		gbc_lblMapSize.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblMapSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblMapSize.gridx = 2;
		gbc_lblMapSize.gridy = 4;
		frame.getContentPane().add(lblMapSize, gbc_lblMapSize);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridwidth = 2;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		GridBagConstraints gbc_lblDifficulty = new GridBagConstraints();
		gbc_lblDifficulty.anchor = GridBagConstraints.NORTH;
		gbc_lblDifficulty.insets = new Insets(0, 0, 5, 5);
		gbc_lblDifficulty.gridx = 2;
		gbc_lblDifficulty.gridy = 6;
		frame.getContentPane().add(lblDifficulty, gbc_lblDifficulty);
		
		JSlider slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.anchor = GridBagConstraints.NORTH;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridwidth = 2;
		gbc_slider.gridx = 2;
		gbc_slider.gridy = 7;
		frame.getContentPane().add(slider, gbc_slider);
		
		JLabel lblSoundLevel = new JLabel("Sound level");
		GridBagConstraints gbc_lblSoundLevel = new GridBagConstraints();
		gbc_lblSoundLevel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblSoundLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblSoundLevel.gridx = 2;
		gbc_lblSoundLevel.gridy = 8;
		frame.getContentPane().add(lblSoundLevel, gbc_lblSoundLevel);
		
		JSlider slider_1 = new JSlider();
		GridBagConstraints gbc_slider_1 = new GridBagConstraints();
		gbc_slider_1.anchor = GridBagConstraints.NORTH;
		gbc_slider_1.insets = new Insets(0, 0, 5, 5);
		gbc_slider_1.gridwidth = 2;
		gbc_slider_1.gridx = 2;
		gbc_slider_1.gridy = 9;
		frame.getContentPane().add(slider_1, gbc_slider_1);
		
		JLabel lblPlayerControls = new JLabel("Player 1 movement");
		GridBagConstraints gbc_lblPlayerControls = new GridBagConstraints();
		gbc_lblPlayerControls.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblPlayerControls.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerControls.gridx = 1;
		gbc_lblPlayerControls.gridy = 10;
		frame.getContentPane().add(lblPlayerControls, gbc_lblPlayerControls);
		
		JLabel lblPlayerMovement = new JLabel("Player 2 movement");
		GridBagConstraints gbc_lblPlayerMovement = new GridBagConstraints();
		gbc_lblPlayerMovement.anchor = GridBagConstraints.NORTH;
		gbc_lblPlayerMovement.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerMovement.gridx = 4;
		gbc_lblPlayerMovement.gridy = 10;
		frame.getContentPane().add(lblPlayerMovement, gbc_lblPlayerMovement);
		
		JToggleButton p1MoveUp = new JToggleButton("Up");
		GridBagConstraints gbc_p1MoveUp = new GridBagConstraints();
		gbc_p1MoveUp.anchor = GridBagConstraints.NORTH;
		gbc_p1MoveUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1MoveUp.insets = new Insets(0, 0, 5, 5);
		gbc_p1MoveUp.gridx = 1;
		gbc_p1MoveUp.gridy = 11;
		frame.getContentPane().add(p1MoveUp, gbc_p1MoveUp);
		
		JToggleButton p2MoveUp = new JToggleButton("Up");
		GridBagConstraints gbc_p2MoveUp = new GridBagConstraints();
		gbc_p2MoveUp.anchor = GridBagConstraints.NORTH;
		gbc_p2MoveUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2MoveUp.insets = new Insets(0, 0, 5, 5);
		gbc_p2MoveUp.gridx = 4;
		gbc_p2MoveUp.gridy = 11;
		frame.getContentPane().add(p2MoveUp, gbc_p2MoveUp);
		
		JToggleButton p1MoveLeft = new JToggleButton("Left");
		GridBagConstraints gbc_p1MoveLeft = new GridBagConstraints();
		gbc_p1MoveLeft.anchor = GridBagConstraints.NORTH;
		gbc_p1MoveLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1MoveLeft.insets = new Insets(0, 0, 5, 5);
		gbc_p1MoveLeft.gridx = 0;
		gbc_p1MoveLeft.gridy = 12;
		frame.getContentPane().add(p1MoveLeft, gbc_p1MoveLeft);
		
		JToggleButton p1MoveDown = new JToggleButton("Down");
		GridBagConstraints gbc_p1MoveDown = new GridBagConstraints();
		gbc_p1MoveDown.anchor = GridBagConstraints.NORTH;
		gbc_p1MoveDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1MoveDown.insets = new Insets(0, 0, 5, 5);
		gbc_p1MoveDown.gridx = 1;
		gbc_p1MoveDown.gridy = 12;
		frame.getContentPane().add(p1MoveDown, gbc_p1MoveDown);
		
		JToggleButton p1MoveRight = new JToggleButton("Right");
		GridBagConstraints gbc_p1MoveRight = new GridBagConstraints();
		gbc_p1MoveRight.anchor = GridBagConstraints.NORTH;
		gbc_p1MoveRight.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1MoveRight.insets = new Insets(0, 0, 5, 5);
		gbc_p1MoveRight.gridx = 2;
		gbc_p1MoveRight.gridy = 12;
		frame.getContentPane().add(p1MoveRight, gbc_p1MoveRight);
		
		JToggleButton p2MoveLeft = new JToggleButton("Left");
		GridBagConstraints gbc_p2MoveLeft = new GridBagConstraints();
		gbc_p2MoveLeft.anchor = GridBagConstraints.NORTH;
		gbc_p2MoveLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2MoveLeft.insets = new Insets(0, 0, 5, 5);
		gbc_p2MoveLeft.gridx = 3;
		gbc_p2MoveLeft.gridy = 12;
		frame.getContentPane().add(p2MoveLeft, gbc_p2MoveLeft);
		
		JToggleButton p2MoveDown = new JToggleButton("Down");
		GridBagConstraints gbc_p2MoveDown = new GridBagConstraints();
		gbc_p2MoveDown.anchor = GridBagConstraints.NORTH;
		gbc_p2MoveDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2MoveDown.insets = new Insets(0, 0, 5, 5);
		gbc_p2MoveDown.gridx = 4;
		gbc_p2MoveDown.gridy = 12;
		frame.getContentPane().add(p2MoveDown, gbc_p2MoveDown);
		
		JToggleButton p2MoveRight = new JToggleButton("Right");
		GridBagConstraints gbc_p2MoveRight = new GridBagConstraints();
		gbc_p2MoveRight.anchor = GridBagConstraints.NORTH;
		gbc_p2MoveRight.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2MoveRight.insets = new Insets(0, 0, 5, 0);
		gbc_p2MoveRight.gridx = 5;
		gbc_p2MoveRight.gridy = 12;
		frame.getContentPane().add(p2MoveRight, gbc_p2MoveRight);
		
		JLabel lblPlayerView = new JLabel("Player 1 view");
		GridBagConstraints gbc_lblPlayerView = new GridBagConstraints();
		gbc_lblPlayerView.anchor = GridBagConstraints.NORTH;
		gbc_lblPlayerView.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayerView.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerView.gridx = 1;
		gbc_lblPlayerView.gridy = 13;
		frame.getContentPane().add(lblPlayerView, gbc_lblPlayerView);
		
		JLabel lblPlayerView_1 = new JLabel("Player 2 view");
		GridBagConstraints gbc_lblPlayerView_1 = new GridBagConstraints();
		gbc_lblPlayerView_1.anchor = GridBagConstraints.NORTH;
		gbc_lblPlayerView_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayerView_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerView_1.gridx = 4;
		gbc_lblPlayerView_1.gridy = 13;
		frame.getContentPane().add(lblPlayerView_1, gbc_lblPlayerView_1);
		
		JToggleButton p1ViewUp = new JToggleButton("Up");
		GridBagConstraints gbc_p1ViewUp = new GridBagConstraints();
		gbc_p1ViewUp.anchor = GridBagConstraints.NORTH;
		gbc_p1ViewUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1ViewUp.insets = new Insets(0, 0, 5, 5);
		gbc_p1ViewUp.gridx = 1;
		gbc_p1ViewUp.gridy = 14;
		frame.getContentPane().add(p1ViewUp, gbc_p1ViewUp);
		
		JToggleButton p2ViewUp = new JToggleButton("Up");
		GridBagConstraints gbc_p2ViewUp = new GridBagConstraints();
		gbc_p2ViewUp.anchor = GridBagConstraints.NORTH;
		gbc_p2ViewUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2ViewUp.insets = new Insets(0, 0, 5, 5);
		gbc_p2ViewUp.gridx = 4;
		gbc_p2ViewUp.gridy = 14;
		frame.getContentPane().add(p2ViewUp, gbc_p2ViewUp);
		
		JToggleButton p1ViewLeft = new JToggleButton("Left");
		GridBagConstraints gbc_p1ViewLeft = new GridBagConstraints();
		gbc_p1ViewLeft.anchor = GridBagConstraints.NORTH;
		gbc_p1ViewLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1ViewLeft.insets = new Insets(0, 0, 5, 5);
		gbc_p1ViewLeft.gridx = 0;
		gbc_p1ViewLeft.gridy = 15;
		frame.getContentPane().add(p1ViewLeft, gbc_p1ViewLeft);
		
		JToggleButton p1ViewDown = new JToggleButton("Down");
		GridBagConstraints gbc_p1ViewDown = new GridBagConstraints();
		gbc_p1ViewDown.anchor = GridBagConstraints.NORTH;
		gbc_p1ViewDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1ViewDown.insets = new Insets(0, 0, 5, 5);
		gbc_p1ViewDown.gridx = 1;
		gbc_p1ViewDown.gridy = 15;
		frame.getContentPane().add(p1ViewDown, gbc_p1ViewDown);
		
		JToggleButton p1ViewRight = new JToggleButton("Right");
		GridBagConstraints gbc_p1ViewRight = new GridBagConstraints();
		gbc_p1ViewRight.anchor = GridBagConstraints.NORTH;
		gbc_p1ViewRight.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1ViewRight.insets = new Insets(0, 0, 5, 5);
		gbc_p1ViewRight.gridx = 2;
		gbc_p1ViewRight.gridy = 15;
		frame.getContentPane().add(p1ViewRight, gbc_p1ViewRight);
		
		JToggleButton p2ViewLeft = new JToggleButton("Left");
		GridBagConstraints gbc_p2ViewLeft = new GridBagConstraints();
		gbc_p2ViewLeft.anchor = GridBagConstraints.NORTH;
		gbc_p2ViewLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2ViewLeft.insets = new Insets(0, 0, 5, 5);
		gbc_p2ViewLeft.gridx = 3;
		gbc_p2ViewLeft.gridy = 15;
		frame.getContentPane().add(p2ViewLeft, gbc_p2ViewLeft);
		
		JToggleButton p2ViewDown = new JToggleButton("Down");
		GridBagConstraints gbc_p2ViewDown = new GridBagConstraints();
		gbc_p2ViewDown.anchor = GridBagConstraints.NORTH;
		gbc_p2ViewDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2ViewDown.insets = new Insets(0, 0, 5, 5);
		gbc_p2ViewDown.gridx = 4;
		gbc_p2ViewDown.gridy = 15;
		frame.getContentPane().add(p2ViewDown, gbc_p2ViewDown);
		
		JToggleButton p2ViewRight = new JToggleButton("Right");
		GridBagConstraints gbc_p2ViewRight = new GridBagConstraints();
		gbc_p2ViewRight.anchor = GridBagConstraints.NORTH;
		gbc_p2ViewRight.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2ViewRight.insets = new Insets(0, 0, 5, 0);
		gbc_p2ViewRight.gridx = 5;
		gbc_p2ViewRight.gridy = 15;
		frame.getContentPane().add(p2ViewRight, gbc_p2ViewRight);
		
		JToggleButton p1ColorMode = new JToggleButton("Color mode:");
		GridBagConstraints gbc_p1ColorMode = new GridBagConstraints();
		gbc_p1ColorMode.anchor = GridBagConstraints.NORTH;
		gbc_p1ColorMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1ColorMode.insets = new Insets(0, 0, 5, 5);
		gbc_p1ColorMode.gridx = 0;
		gbc_p1ColorMode.gridy = 16;
		frame.getContentPane().add(p1ColorMode, gbc_p1ColorMode);
		
		JToggleButton p1ZoomIn = new JToggleButton("Zoom in:");
		GridBagConstraints gbc_p1ZoomIn = new GridBagConstraints();
		gbc_p1ZoomIn.anchor = GridBagConstraints.NORTH;
		gbc_p1ZoomIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1ZoomIn.insets = new Insets(0, 0, 5, 5);
		gbc_p1ZoomIn.gridx = 1;
		gbc_p1ZoomIn.gridy = 16;
		frame.getContentPane().add(p1ZoomIn, gbc_p1ZoomIn);
		
		JToggleButton p1ZoomOut = new JToggleButton("Zoom out:");
		GridBagConstraints gbc_p1ZoomOut = new GridBagConstraints();
		gbc_p1ZoomOut.anchor = GridBagConstraints.NORTH;
		gbc_p1ZoomOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1ZoomOut.insets = new Insets(0, 0, 5, 5);
		gbc_p1ZoomOut.gridx = 2;
		gbc_p1ZoomOut.gridy = 16;
		frame.getContentPane().add(p1ZoomOut, gbc_p1ZoomOut);
		
		JToggleButton p2ColorMode = new JToggleButton("Color mode:");
		GridBagConstraints gbc_p2ColorMode = new GridBagConstraints();
		gbc_p2ColorMode.anchor = GridBagConstraints.NORTH;
		gbc_p2ColorMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2ColorMode.insets = new Insets(0, 0, 5, 5);
		gbc_p2ColorMode.gridx = 3;
		gbc_p2ColorMode.gridy = 16;
		frame.getContentPane().add(p2ColorMode, gbc_p2ColorMode);
		
		JToggleButton p2ZoomIn = new JToggleButton("Zoom in:");
		GridBagConstraints gbc_p2ZoomIn = new GridBagConstraints();
		gbc_p2ZoomIn.anchor = GridBagConstraints.NORTH;
		gbc_p2ZoomIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2ZoomIn.insets = new Insets(0, 0, 5, 5);
		gbc_p2ZoomIn.gridx = 4;
		gbc_p2ZoomIn.gridy = 16;
		frame.getContentPane().add(p2ZoomIn, gbc_p2ZoomIn);
		
		JToggleButton p2ZoomOut = new JToggleButton("Zoom out:");
		GridBagConstraints gbc_p2ZoomOut = new GridBagConstraints();
		gbc_p2ZoomOut.anchor = GridBagConstraints.NORTH;
		gbc_p2ZoomOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2ZoomOut.insets = new Insets(0, 0, 5, 0);
		gbc_p2ZoomOut.gridx = 5;
		gbc_p2ZoomOut.gridy = 16;
		frame.getContentPane().add(p2ZoomOut, gbc_p2ZoomOut);
		
		JLabel lblPlayerName1 = new JLabel("Player 1 name:");
		GridBagConstraints gbc_lblPlayerName1 = new GridBagConstraints();
		gbc_lblPlayerName1.anchor = GridBagConstraints.NORTH;
		gbc_lblPlayerName1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerName1.gridx = 1;
		gbc_lblPlayerName1.gridy = 18;
		frame.getContentPane().add(lblPlayerName1, gbc_lblPlayerName1);
		
		JLabel lblPlayerName2 = new JLabel("Player 2 name:");
		GridBagConstraints gbc_lblPlayerName2 = new GridBagConstraints();
		gbc_lblPlayerName2.anchor = GridBagConstraints.NORTH;
		gbc_lblPlayerName2.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerName2.gridx = 4;
		gbc_lblPlayerName2.gridy = 18;
		frame.getContentPane().add(lblPlayerName2, gbc_lblPlayerName2);
		
		p1Name = new JTextField();
		p1Name.setText("Player 1");
		GridBagConstraints gbc_p1Name = new GridBagConstraints();
		gbc_p1Name.anchor = GridBagConstraints.NORTH;
		gbc_p1Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1Name.insets = new Insets(0, 0, 0, 5);
		gbc_p1Name.gridx = 1;
		gbc_p1Name.gridy = 19;
		frame.getContentPane().add(p1Name, gbc_p1Name);
		p1Name.setColumns(10);
		
		p2Name = new JTextField();
		p2Name.setText("Player 2");
		p2Name.setColumns(10);
		GridBagConstraints gbc_p2Name = new GridBagConstraints();
		gbc_p2Name.anchor = GridBagConstraints.NORTH;
		gbc_p2Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2Name.insets = new Insets(0, 0, 0, 5);
		gbc_p2Name.gridx = 4;
		gbc_p2Name.gridy = 19;
		frame.getContentPane().add(p2Name, gbc_p2Name);
	}
}
