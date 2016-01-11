package Game.Tests;
import Game.Control.Sound.Sound;

public class TestSound {
	
	public static void main(String[] args) {
		
		Sound meh = new Sound("res/bossdeath.wav");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sound meh2 = new Sound("res/01 The Vampire From Nazareth.wav");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		meh.playSound();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		meh2.playSound();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
