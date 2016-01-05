package Menu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Starter {
	private static JFrame mainMenu;
	
	private static final GOTYMainPage MAIN_PAGE = new GOTYMainPage();
	
	private static SuperPage currentPage;
	
	public static void main(String[] args) {
		createFrame();
		startFrame();
	}
	
	private static void createFrame()
	{
		mainMenu = new JFrame("GOTY2016");
		mainMenu.setSize(400, 600);
		mainMenu.addWindowListener((new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	             System.exit(0);
	         }        
	    	}));
	}
	
	private static void startFrame()
	{
		mainMenu.add(MAIN_PAGE.getPage());
		currentPage = MAIN_PAGE;
		mainMenu.setVisible(true);
	}
	
	public static void switchPage(SuperPage toSwitchTo)
	{
		currentPage.closePage();
		mainMenu.getContentPane().removeAll();
		mainMenu.add(toSwitchTo.getPage());
		toSwitchTo.startPage(currentPage);// måske problemer med hvor pointeren fører hen her
		currentPage = toSwitchTo;
		mainMenu.repaint();
		mainMenu.setVisible(true);
	}
}
