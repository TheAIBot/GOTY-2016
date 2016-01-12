package Game.Control.GameEngine;

import java.util.ArrayList;
import java.util.Collections;

import Game.Control.Sound.Sound;
import Game.Control.Sound.SoundFinishedListener;

public class AudioManager implements SoundFinishedListener {
	
	private boolean paused = false;
	private float currentVolumeInPercents = 1F;
	ArrayList<Sound> sounds;
	
	public AudioManager() {
		sounds = new ArrayList<Sound>();
	}
	
	private void makeSound(String path) {
		Sound sound = new Sound(path);
		sounds.add(sound);
		sound.setVolume(currentVolumeInPercents);
		if (!paused) {
			sound.pauseSound();
		}
	}
	
	public void makeSwooshSound(){
		makeSound("res/Swoosh");
	}
	
	public void pause(){
		if (!paused) {
			for (Sound sound : sounds) {
				sound.pauseSound();
			}
		}
	}
	
	public void unPause() {
		if (paused) {
			for (Sound sound : sounds) {
				sound.playSound();
			}
		}
	}
	
	public void setVolumeInPercents(float newVolumeInPercents){
		for (Sound sound : sounds) {
			sound.setVolume(newVolumeInPercents);
		}
	}

	
	
	public void soundClosed(Sound sound) {
		System.out.println("sounds size = " + sounds.size());
		for (int i = 0; i < sounds.size(); i++) {
			if (sounds.get(i).equals(sound)) {
				sounds.remove(i);
				i--;
			}
		}
		//sounds.removeAll(Collections.singletonList(sound));
		sounds.removeAll(Collections.singletonList(sound));
	}


}
