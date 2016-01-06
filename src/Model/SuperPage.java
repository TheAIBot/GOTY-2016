package Model;
import javax.swing.*;

import Control.MenuController;
import Control.GameEngine.Log;


public abstract class SuperPage {
	protected JPanel page;
	protected SuperPage previousPage;
	
	public JPanel getPage()
	{
		if (isPageCreated()) {
			return page;
		}
		page = new JPanel();
		return createPage();
	}
	
	public abstract JPanel createPage();
	
	public void startPage(SuperPage prevPage)
	{
		previousPage = prevPage;
	}
	
	public abstract void closePage();
	
	private boolean isPageCreated()
	{
		return page != null;
	}
	
	protected void backPage()
	{
		if (previousPage != null) {
			MenuController.switchPage(previousPage);
		}
		else
		{
			Log.writeln("tried to go back when previousPage was null");
		}
	}
}
