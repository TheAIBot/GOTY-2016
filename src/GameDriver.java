import Menu.MenuController;

public class GameDriver {
	public static void main(String[] args) {
		MenuController menuController = new MenuController("GOTY-2016", 800, 700);
		menuController.showWindow();
	}	
}
