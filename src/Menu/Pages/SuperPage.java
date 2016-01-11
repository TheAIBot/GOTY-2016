package Menu.Pages;
import javax.swing.JPanel;

import Game.Control.GameEngine.Log;


public abstract class SuperPage {
	protected JPanel page;
	protected SuperPage previousPage;
	private PageRequestsListener listener;
	
	public SuperPage(PageRequestsListener listener)
	{
		this.listener = listener;
	}
	
	public JPanel getPage()
	{
		if (isPageCreated()) {
			return page;
		}
		page = new JPanel();
		return createPage();
	}
	
	public abstract JPanel createPage();
	
	public abstract void startPage();
	
	public abstract void closePage();
	
	private boolean isPageCreated()
	{
		return page != null;
	}
	
	protected void backPage()
	{
		if (listener != null) {
			listener.back();
		}
		else
		{
			Log.writeln("PageRequestsListener is null");
		}
	}

	protected void switchPage(SuperPage switchTo)
	{
		listener.switchPage(switchTo);
	}
}
