package Control;


import java.util.Stack;

import javax.swing.JFrame;

import Control.GameEngine.Log;
import Menu.GOTYMainPage;
import Model.PageRequestsListener;
import Model.SuperPage;

public class MenuController implements PageRequestsListener {
	private final JFrame mainMenu;
	private final GOTYMainPage MAIN_PAGE = new GOTYMainPage(this);
	private SuperPage currentPage;
	private final Stack<SuperPage> previousPages = new Stack<SuperPage>();
	
	public MenuController(String windowName, int startWidth, int startHeight)
	{
		mainMenu = new JFrame(windowName);
		mainMenu.setSize(startWidth, startHeight);
	}
	
	public void showWindow()
	{
		currentPage = MAIN_PAGE;
		mainMenu.add(MAIN_PAGE.getPage());
		currentPage = MAIN_PAGE;
		mainMenu.setVisible(true);	
	}

	@Override
	public void back() {
		if (!previousPages.isEmpty()) {
			switchPage(previousPages.pop());
		}
		else {
			Log.writeln("Tried to go back a page when there is no previous page to go back to");
		}
		
	}
	
	@Override
	public void switchPage(SuperPage toSwitchTo)
	{
		previousPages.add(currentPage);
		currentPage.closePage();
		currentPage = toSwitchTo;
		
		mainMenu.getContentPane().removeAll();
		mainMenu.add(toSwitchTo.getPage());
		mainMenu.repaint();
		mainMenu.setVisible(true);
		
		currentPage.startPage();
	}
}
