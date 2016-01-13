package Menu;


import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Game.Control.GameEngine.Log;
import Menu.Pages.GOTYMainPage;
import Menu.Pages.PageRequestsListener;
import Menu.Pages.SuperPage;

public class MenuController implements PageRequestsListener {
	private final JFrame mainMenu;
	private final GOTYMainPage MAIN_PAGE = new GOTYMainPage(this);
	private SuperPage currentPage;
	private final Stack<SuperPage> previousPages = new Stack<SuperPage>();
	
	public MenuController(String windowName, int startWidth, int startHeight)
	{
		mainMenu = new JFrame(windowName);
		mainMenu.setSize(startWidth, startHeight);
		mainMenu.addWindowListener(new WindowAdapter() {
			@Override
	        public void windowClosing(WindowEvent e) {
	            super.windowClosing(e);
	            currentPage.closePage();
	        }
		});
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
		if (toSwitchTo.canShowPage()) {
			previousPages.add(currentPage);
			currentPage.closePage();
			currentPage = toSwitchTo;
			
			mainMenu.getContentPane().removeAll();
			mainMenu.add(toSwitchTo.getPage());
			mainMenu.repaint();
			
			currentPage.startPage();
		}
	}

	@Override
	public void resize(Dimension dim) {
		mainMenu.setSize(dim.width, dim.height);
	}

	@Override
	public void canResize(boolean canResize) {
		mainMenu.setResizable(canResize);
		
	}
}
