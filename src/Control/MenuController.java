package Control;


import javax.swing.JFrame;

import Menu.GOTYMainPage;
import Model.SuperPage;

public class MenuController {
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
		toSwitchTo.startPage(currentPage);// m�ske problemer med hvor pointeren f�rer hen her
		currentPage = toSwitchTo;
		mainMenu.repaint();
		mainMenu.setVisible(true);
	}
}
