package Game.Control.GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.istack.internal.FinalArrayList;

import Game.Control.Sound.Sound;
import Game.Control.Sound.SoundFinishedListener;

/** Unlimited sounds work!
 */
public class AudioManager implements SoundFinishedListener {
	
	private static int MAX_PARALLEL_SOUNDS = 30; //-1 means that as many sounds as one wants can be played at the same time.
	private boolean paused = false;
	private float currentVolumeInPercents;
	private ArrayList<Sound> sounds;
	private HashMap<String, Sound[]> soundMap;
	private HashMap<String, Integer> bufferPlacementMap;
	
	public AudioManager(float volumeInPercent) {
		sounds = new ArrayList<Sound>();
		currentVolumeInPercents = volumeInPercent;
		soundMap = new HashMap<String, Sound[]>();
		bufferPlacementMap = new  HashMap<String, Integer>();
	}
	
	public void createSoundBuffer(final String nameOfSound, int bufferSize){
		if (!(soundMap.containsKey(nameOfSound) && bufferPlacementMap.containsKey(nameOfSound))) {
	        ExecutorService soundLoaderService = Executors.newFixedThreadPool(bufferSize);
			final Sound[] soundBuffer = new Sound[bufferSize];	//En masse final for trådene.
			soundMap.put(nameOfSound, soundBuffer);
			bufferPlacementMap.put(nameOfSound, 0);
			for (int i = 0; i < soundBuffer.length; i++) { //(*) Kan komme en fejl hvis lydende bliver loadet lige efter hinadenen for huritigt.
				final int t = i;
				soundBuffer[t] = new Sound("res/" + nameOfSound);
				soundBuffer[t].setVolume(currentVolumeInPercents);
			}
			for (int i = 0; i < soundBuffer.length; i++) {
				if (paused) {
					soundBuffer[i].pauseSound();
				}
			}
		}
	}
	
	public void makeSound(String nameOfSound){
		if (MAX_PARALLEL_SOUNDS - 1 >= sounds.size() || MAX_PARALLEL_SOUNDS == -1) {
			if (soundMap.containsKey(nameOfSound) && bufferPlacementMap.containsKey(nameOfSound)) {
				Sound[] soundBuffer = soundMap.get(nameOfSound);			
				Integer bufferPlacement = bufferPlacementMap.get(nameOfSound);
				Sound currentSound = soundBuffer[bufferPlacement];
				currentSound.setVolume(currentVolumeInPercents);
				sounds.add(currentSound);
				currentSound.addSoundFinishedListener(this); //Fjern bagefter (*)?
				if (paused) {
					currentSound.pauseSound();
				} else currentSound.playSound();
				if (bufferPlacement == soundBuffer.length - 1) {
					bufferPlacement = 0;
				} else bufferPlacement++;
				bufferPlacementMap.put(nameOfSound, bufferPlacement); //For at sikre at der ikke er problmmer med Integers
			} else Log.writeln(nameOfSound + " is to be played, but it does not have a buffer, " + 
							   "or it does not have a buffer placement number");
		}
	}
	
	public void makeSwooshSound(){
		makeSound("bossdeath.wav");
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
		
	public void soundFinished(Sound sound) {
		Sound soundInList;
		for (int i = 0; i < sounds.size(); i++) {
			soundInList = sounds.get(i);
			if (soundInList.equals(sound)) {
				soundInList.removeSoundFinishedListener(this);
				sounds.remove(i);
				return; //assumes the sound is not found in the list twice;
			}
		}
	}

}
