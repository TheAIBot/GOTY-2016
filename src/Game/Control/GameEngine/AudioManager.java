package Game.Control.GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.istack.internal.FinalArrayList;
import com.sun.javafx.collections.MappingChange.Map;

import Game.Control.Sound.Sound;
import Game.Control.Sound.SoundFinishedListener;
import Game.Model.CirculairList;
import Game.Model.Resources.ResourceAudio;
import javafx.util.Pair;

/** Unlimited sounds work!
 */
public class AudioManager implements SoundFinishedListener {
	
	private static int MAX_PARALLEL_SOUNDS = 10; //-1 means that as many sounds as one wants can be played at the same time.
	private boolean paused = false;
	private float currentVolumeInPercents;
	private ArrayList<Sound> playingSounds = new ArrayList<Sound>();
	private HashMap<String, CirculairList<Sound>> soundMap = new  HashMap<String, CirculairList<Sound>>();
	private static Sound backgroundMusic;
	
	public AudioManager(float volumeInPercent) {
		this.currentVolumeInPercents = volumeInPercent;
		loadSounds();
	}
	
	private void loadSounds()
	{
		loadSound(ResourceAudio.TILE_MOVED_SOUND, MAX_PARALLEL_SOUNDS);
	}
	
	private void loadSound(String name, int amount)
	{
		Sound[] sounds = ResourceAudio.loadSounds(name, amount, currentVolumeInPercents);
		for (int i = 0; i < sounds.length; i++) {
			sounds[i].addSoundFinishedListener(this);
		}
		if (sounds != null) {
			soundMap.put(name, new CirculairList<>(sounds));
		}
	}
	
	public void playSound(String name){
		if (soundMap.containsKey(name)) {
			Sound sound = soundMap.get(name).getNext();
			playingSounds.add(sound);
			sound.resetSound();
			if (paused) {
				sound.pauseSound();
			} else {
				sound.playSound();
			}
		}
	}
	
	public void pause(){
		if (!paused) {
			for (Sound sound : playingSounds) {
				sound.pauseSound();
			}
		}
		paused = true;
	}
	
	public void unPause() {
		if (paused) {
			for (Sound sound : playingSounds) {
				sound.playSound();
			}
		}
		paused = false;
	}
	
	public void setVolumeInPercents(float newVolumeInPercents){
		currentVolumeInPercents = newVolumeInPercents;
		for (CirculairList<Sound> sounds : soundMap.values()) {
			for (Sound sound : sounds.getArray()) {
				sound.setVolume(currentVolumeInPercents);
			}
		}
	}
		
	public void soundFinished(Sound sound) {
		Sound soundInList;
		for (int i = 0; i < playingSounds.size(); i++) {
			soundInList = playingSounds.get(i);
			if (soundInList.equals(sound)) {
				playingSounds.remove(i);
				return; //assumes the sound is not found in the list twice;
			}
		}
	}
	
	public static void setbackgroundMusic(Sound sound)
	{
		backgroundMusic = sound;
	}

	public void close()
	{
		for (CirculairList<Sound> sounds : soundMap.values()) {
			for (Sound sound : sounds.getArray()) {
				sound.closeClip();
			}
		}
		closeBackgroundMusic();
	}
	
	public static void closeBackgroundMusic()
	{
		if (backgroundMusic != null) {
			backgroundMusic.closeClip();
			backgroundMusic = null;
		}
	}
}
