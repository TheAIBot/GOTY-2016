package graphics;

import java.awt.Color;
import java.awt.Point;

import javax.swing.*;
public class MainFrame {
	
	GraphicsPanel gPanel;
	JFrame zaFrame;
	
	public MainFrame() {		
		zaFrame = new JFrame();		
		zaFrame.setSize(400,400);
		zaFrame.getContentPane().setBackground(Color.BLACK);
		zaFrame.setTitle("Kore Dio da!");
		zaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    zaFrame.setLocation(new Point(10, 50));
		zaFrame.setVisible(true);
		gPanel = new GraphicsPanel(400,400);
		zaFrame.add(gPanel);		
		Screen screen = new Screen(gPanel.getGImage(), gPanel.getImageBounds());
		
		FakeTile zaTile = new FakeTile(7, new Point(0,0), Color.RED);
		
		zaTile.render(screen);
				
		
	}
	
}
