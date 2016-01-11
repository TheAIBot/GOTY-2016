package Control.GameEngine;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import Control.Sound.Sound;
import Control.Sound.SoundFinishedListener;

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
		sounds.removeAll(Collections.singletonList(sound));
	}


}
