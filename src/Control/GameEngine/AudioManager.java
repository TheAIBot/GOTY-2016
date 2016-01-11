package Control.GameEngine;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioManager implements SoundFinishedListener {
	
	private boolean paused = false;
	private float currentVolumeInPercents = 1F;
	ArrayList<Sound> sounds;
	
	public AudioManager() {
		sounds = new ArrayList<Sound>();
	}
	
	private void makeSound(String path) {
		if (sounds.size() <= 6) {
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
	}


}
