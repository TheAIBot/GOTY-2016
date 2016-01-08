package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicsPanel extends JPanel{	
	private static final long serialVersionUID = 1L; //autogenerated
	
	private Point imageBounds;
	private JLabel label;
	private ImageIcon displayImageIcon;
	private BufferedImage displayImage;
	private Graphics2D gImage;
	
	public GraphicsPanel(int height, int width) {
		
		//Basseret på bogen				
		super();
		this.setBackground(Color.WHITE);
		label = new JLabel();
		this.add(label);		
		imageBounds = new Point(width, height);
		displayImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR); //Er det den rigtige type? (*) det er det hvis filen er .png format
		gImage = displayImage.createGraphics();	
		gImage.setBackground(Color.WHITE);
		displayImageIcon = new ImageIcon(displayImage);
		label.setIcon(displayImageIcon);
	}
	
	public Graphics2D getGImage() {
		return gImage;
	}	
	
	public Point getImageBounds(){
		return imageBounds;
	}
	
	public void windowResized(Point newSize)
	{
		imageBounds = newSize;
		displayImage = new BufferedImage(newSize.x, newSize.y, BufferedImage.TYPE_4BYTE_ABGR);
		displayImageIcon = new ImageIcon(displayImage);
		label.setIcon(displayImageIcon);
		gImage.dispose();
		gImage = displayImage.createGraphics();
		gImage.setBackground(Color.WHITE);
	}
}
