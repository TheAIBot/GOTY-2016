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
		
		//Testing: generate some sample tiles
		FakeTile[] tiles = new FakeTile[9];
		
		tiles[0] = new FakeTile(1, new Point(0,0), Color.RED);
		tiles[1] = new FakeTile(2, new Point(1,0), Color.RED);
		tiles[2] = new FakeTile(3, new Point(2,0), Color.RED);
		
		tiles[3] = new FakeTile(1, new Point(0,1), Color.RED);
		tiles[4] = new FakeTile(2, new Point(1,1), Color.RED);
		tiles[5] = new FakeTile(3, new Point(2,1), Color.RED);
		
		tiles[6] = new FakeTile(1, new Point(0,2), Color.RED);
		tiles[7] = new FakeTile(2, new Point(1,2), Color.RED);
		tiles[8] = new FakeTile(3, new Point(2,2), Color.RED);
		
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].render(screen);
		}				
		
	}
	
}
