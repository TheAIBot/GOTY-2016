import javax.swing.*;

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
}
