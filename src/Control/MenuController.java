package Control;


import javax.swing.JFrame;

import Menu.GOTYMainPage;
import Model.SuperPage;

public class MenuController {
	private final JFrame mainMenu;
	private final GOTYMainPage MAIN_PAGE = new GOTYMainPage();
	private SuperPage currentPage;
	
	public MenuController(String windowName, int startWidth, int startHeight)
	{
		mainMenu = new JFrame(windowName);
		mainMenu.setSize(startWidth, startHeight);
	}
	
	public void startWindow()
	{
		mainMenu.add(MAIN_PAGE.getPage());
		currentPage = MAIN_PAGE;
		mainMenu.setVisible(true);	
	}
	
	private void switchPage(SuperPage toSwitchTo)
	{
		currentPage.closePage();
		mainMenu.getContentPane().removeAll();
		mainMenu.add(toSwitchTo.getPage());
		toSwitchTo.startPage(currentPage);
		currentPage = toSwitchTo;
		mainMenu.repaint();
		mainMenu.setVisible(true);
	}
}
