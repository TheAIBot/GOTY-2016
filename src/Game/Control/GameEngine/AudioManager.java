package Game.Control.GameEngine;

import java.util.ArrayList;

import Game.Control.Sound.Sound;
import Game.Control.Sound.SoundFinishedListener;

public class AudioManager implements SoundFinishedListener {
	
	private static int MAX_PARALLEL_SOUNDS = 6;
	private boolean paused = false;
	private float currentVolumeInPercents = 0.3F;
	ArrayList<Sound> sounds;
	
	public AudioManager() {
		sounds = new ArrayList<Sound>();
	}
	
	private void makeSound(String path) {
		if (sounds.size() <= MAX_PARALLEL_SOUNDS) {
			Sound sound = new Sound(path);
			sound.addSoundFinishedListener(this);
			sounds.add(sound);
			sound.setVolume(currentVolumeInPercents);
			if (paused) {
				sound.pauseSound();
			} else {
				sound.playSound();
			}			
		} 
	}
	
	public void makeSwooshSound(){
		makeSound("res/bossdeath.wav");
		//makeSound("res/01 The Vampire From Nazareth.wav");
	}
	
	public void pause(){
		if (!paused) {
			for (Sound sound : sounds) {
				sound.pauseSound();
			}
		}
		paused = true;
	}
	
	public void unPause() {
		if (paused) {
			for (Sound sound : sounds) {
				sound.playSound();
			}
		}
		paused = false;
	}
	
	public void setVolumeInPercents(float newVolumeInPercents){
		for (Sound sound : sounds) {
			sound.setVolume(newVolumeInPercents);
		}
	}

	
	
	public void soundClosed(Sound sound) {
		for (int i = 0; i < sounds.size(); i++) {
			if (sounds.get(i).equals(sound)) {
				sounds.remove(i);
				i--;
			}
		}
		//sounds.removeAll(Collections.singletonList(sound));
	}


}