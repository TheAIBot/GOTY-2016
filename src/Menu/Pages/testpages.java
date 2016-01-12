package Menu.Pages;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class testpages {
	private JTextField textField;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createWindow()
	{
		JFrame frame = new JFrame("test");
		frame.getContentPane().setLayout(null);
		
		JToggleButton p1MoveUp = new JToggleButton("Up");
		p1MoveUp.setBounds(153, 405, 137, 25);
		frame.getContentPane().add(p1MoveUp);
		
		JToggleButton p1MoveDown = new JToggleButton("Down");
		p1MoveDown.setBounds(153, 434, 137, 25);
		frame.getContentPane().add(p1MoveDown);
		
		JToggleButton p1MoveRight = new JToggleButton("Right");
		p1MoveRight.setBounds(294, 434, 137, 25);
		frame.getContentPane().add(p1MoveRight);
		
		JToggleButton p1MoveLeft = new JToggleButton("Left");
		p1MoveLeft.setBounds(12, 434, 137, 25);
		frame.getContentPane().add(p1MoveLeft);
		
		JButton btnNewButton = new JButton("PLAY");
		btnNewButton.setBounds(750, 31, 117, 54);
		frame.getContentPane().add(btnNewButton);
		
		JToggleButton p1ViewUp = new JToggleButton("Up");
		p1ViewUp.setBounds(153, 512, 137, 25);
		frame.getContentPane().add(p1ViewUp);
		
		JToggleButton p1ViewDown = new JToggleButton("Down");
		p1ViewDown.setBounds(153, 541, 137, 25);
		frame.getContentPane().add(p1ViewDown);
		
		JToggleButton p1ViewLeft = new JToggleButton("Left");
		p1ViewLeft.setBounds(12, 541, 137, 25);
		frame.getContentPane().add(p1ViewLeft);
		
		JToggleButton p1ViewRight = new JToggleButton("Right");
		p1ViewRight.setBounds(294, 541, 137, 25);
		frame.getContentPane().add(p1ViewRight);
		
		JToggleButton p2MoveUp = new JToggleButton("Up");
		p2MoveUp.setBounds(609, 405, 137, 25);
		frame.getContentPane().add(p2MoveUp);
		
		JToggleButton p2MoveDown = new JToggleButton("Down");
		p2MoveDown.setBounds(609, 434, 137, 25);
		frame.getContentPane().add(p2MoveDown);
		
		JToggleButton p2MoveLeft = new JToggleButton("Left");
		p2MoveLeft.setBounds(468, 434, 137, 25);
		frame.getContentPane().add(p2MoveLeft);
		
		JToggleButton p2MoveRight = new JToggleButton("Right");
		p2MoveRight.setBounds(750, 434, 137, 25);
		frame.getContentPane().add(p2MoveRight);
		
		JToggleButton p2ViewUp = new JToggleButton("Up");
		p2ViewUp.setBounds(609, 512, 137, 25);
		frame.getContentPane().add(p2ViewUp);
		
		JToggleButton p2ViewLeft = new JToggleButton("Left");
		p2ViewLeft.setBounds(468, 541, 137, 25);
		frame.getContentPane().add(p2ViewLeft);
		
		JToggleButton p2ViewDown = new JToggleButton("Down");
		p2ViewDown.setBounds(609, 541, 137, 25);
		frame.getContentPane().add(p2ViewDown);
		
		JToggleButton p2ViewRight = new JToggleButton("Right");
		p2ViewRight.setBounds(750, 541, 137, 25);
		frame.getContentPane().add(p2ViewRight);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBounds(32, 31, 117, 54);
		frame.getContentPane().add(btnBack);
		
		textField = new JTextField();
		textField.setBounds(395, 195, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JSlider slider = new JSlider();
		slider.setBounds(353, 264, 200, 26);
		frame.getContentPane().add(slider);
		
		JSlider slider_1 = new JSlider();
		slider_1.setBounds(353, 335, 200, 26);
		frame.getContentPane().add(slider_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(395, 125, 31, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblGameMode = new JLabel("Game mode");
		lblGameMode.setBounds(394, 105, 83, 16);
		frame.getContentPane().add(lblGameMode);
		
		JLabel lblMapSize = new JLabel("Map size");
		lblMapSize.setBounds(395, 175, 56, 16);
		frame.getContentPane().add(lblMapSize);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setBounds(357, 246, 56, 16);
		frame.getContentPane().add(lblDifficulty);
		
		JLabel lblSoundLevel = new JLabel("Sound level");
		lblSoundLevel.setBounds(353, 316, 98, 16);
		frame.getContentPane().add(lblSoundLevel);
		
		JLabel lblPlayerControls = new JLabel("Player 1 movement");
		lblPlayerControls.setBounds(172, 376, 116, 16);
		frame.getContentPane().add(lblPlayerControls);
		
		JLabel lblPlayerView = new JLabel("Player 1 view");
		lblPlayerView.setBounds(172, 483, 116, 16);
		frame.getContentPane().add(lblPlayerView);
		
		JLabel lblPlayerMovement = new JLabel("Player 2 movement");
		lblPlayerMovement.setBounds(623, 376, 116, 16);
		frame.getContentPane().add(lblPlayerMovement);
		
		JLabel lblPlayerView_1 = new JLabel("Player 2 view");
		lblPlayerView_1.setBounds(630, 483, 116, 16);
		frame.getContentPane().add(lblPlayerView_1);
		
		JToggleButton p1ColorMode = new JToggleButton("Color mode:");
		p1ColorMode.setBounds(12, 579, 137, 25);
		frame.getContentPane().add(p1ColorMode);
		
		JToggleButton p1ZoomIn = new JToggleButton("Zoom in:");
		p1ZoomIn.setBounds(153, 579, 137, 25);
		frame.getContentPane().add(p1ZoomIn);
		
		JToggleButton p1ZoomOut = new JToggleButton("Zoom out:");
		p1ZoomOut.setBounds(294, 579, 137, 25);
		frame.getContentPane().add(p1ZoomOut);
		
		JToggleButton p2ColorMode = new JToggleButton("Color mode:");
		p2ColorMode.setBounds(468, 579, 137, 25);
		frame.getContentPane().add(p2ColorMode);
		
		JToggleButton p2ZoomIn = new JToggleButton("Zoom in:");
		p2ZoomIn.setBounds(609, 579, 137, 25);
		frame.getContentPane().add(p2ZoomIn);
		
		JToggleButton p2ZoomOut = new JToggleButton("Zoom out:");
		p2ZoomOut.setBounds(750, 579, 137, 25);
		frame.getContentPane().add(p2ZoomOut);
		
		JButton saveButton = new JButton("Save settings");
		saveButton.setBounds(32, 101, 117, 54);
		frame.getContentPane().add(saveButton);
		
		JCheckBox setRandomize = new JCheckBox("Randomize");
		setRandomize.setBounds(750, 96, 117, 25);
		frame.getContentPane().add(setRandomize);
	}
}
