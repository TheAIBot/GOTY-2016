package Game.Control.GameEngine;

import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

import Game.Control.Sound.Sound;
import Game.Control.Sound.SoundFinishedListener;

public class AudioManager implements SoundFinishedListener {
	
	private static int MAX_PARALLEL_SOUNDS = 6;
	private boolean paused = false;
	private float currentVolumeInPercents;
	ArrayList<Sound> sounds;
	
	public AudioManager(float volumeInPercent) {
		sounds = new ArrayList<Sound>();
		currentVolumeInPercents = volumeInPercent;
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
		currentVolumeInPercents = newVolumeInPercents;
		for (Sound sound : sounds) {
			sound.setVolume(currentVolumeInPercents);
		}
	}

	
	
	public void soundClosed(Sound sound) {
<<<<<<< HEAD
<<<<<<< HEAD:src/Control/GameEngine/AudioManager.java
		System.out.println("sounds size = " + sounds.size());
=======
>>>>>>> refs/remotes/origin/Jesper
		for (int i = 0; i < sounds.size(); i++) {
			if (sounds.get(i).equals(sound)) {
				sounds.remove(i);
				i--;
			}
		}
		//sounds.removeAll(Collections.singletonList(sound));
=======
		sounds.removeAll(Collections.singletonList(sound));
>>>>>>> 4c5c6838b65622434c625728c9f598f3c916b18c:src/Game/Control/GameEngine/AudioManager.java
	}


}