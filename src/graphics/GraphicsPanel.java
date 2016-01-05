package graphics;

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
	
	public GraphicsPanel(int heigth, int width) {
		//Basseret på bogen				
		super();
		this.setBackground(Color.WHITE);
		label = new JLabel();
		this.add(label);		
		imageBounds = new Point(width, heigth);
		displayImage = new BufferedImage(heigth, width, BufferedImage.TYPE_4BYTE_ABGR); //Er det den rigtige type? (*)
		gImage = displayImage.createGraphics();	
		displayImageIcon = new ImageIcon(displayImage);
		label.setIcon(displayImageIcon);
	}
	
	public Graphics2D getGImage() {
		return gImage;
	}	
	public Point getImageBounds(){
		return imageBounds;
	}
}