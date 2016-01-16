package Game.Control.GameEngine;

import java.util.ArrayList;
import java.util.HashMap;

import Game.Control.Sound.Sound;
import Game.Control.Sound.SoundFinishedListener;
import Game.Model.CirculairList;
import Game.Model.Resources.ResourceAudio;

/**
 * Unlimited sounds works!
 */
public class AudioManager implements SoundFinishedListener {

	private static int MAX_PARALLEL_SOUNDS = 10; // -1 means that as many sounds
													// as one wants can be
													// played at the same time.
	private boolean paused = false;
	private float currentVolumeInPercents;
	private ArrayList<Sound> playingSounds = new ArrayList<Sound>();
	private HashMap<String, CirculairList<Sound>> soundMap = new HashMap<String, CirculairList<Sound>>();
	private static Sound backgroundMusic;

	public AudioManager(float volumeInPercent) {
		this.currentVolumeInPercents = volumeInPercent;
		loadSounds();
	}

	private void loadSounds() {
		loadSound(ResourceAudio.TILE_MOVED_SOUND, MAX_PARALLEL_SOUNDS);
	}

	/**
	 * Loads the sound by adding it to the SoundFinishedListeners, which ensures it can be stopped once it is finished.
	 * Further, by adding it to the soundMap
	 * @param name
	 * @param amount
	 */
	private void loadSound(String name, int amount) {
		Sound[] sounds = ResourceAudio.loadSounds(name, amount, currentVolumeInPercents);
		for (int i = 0; i < sounds.length; i++) {
			sounds[i].addSoundFinishedListener(this);
		}
		if (sounds != null) {
			soundMap.put(name, new CirculairList<Sound>(sounds));
		}
	}

	public void playSound(String name) {
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

	public void pause() {
		if (!paused) {
			for (Sound sound : playingSounds) {
				sound.pauseSound();
			}
		}
		pauseBackgroundMusic();
		paused = true;
	}

	public void unPause() {
		if (paused) {
			for (Sound sound : playingSounds) {
				sound.playSound();
			}
		}
		playBackgroundMusic();
		paused = false;
	}

	public void playBackgroundMusic() {
		if (backgroundMusic != null) {
			backgroundMusic.playSound();
		}
	}

	public void pauseBackgroundMusic() {
		if (backgroundMusic != null) {
			backgroundMusic.pauseSound();
		}
	}

	public void setBackgroundVolumeInPercents(float newVolumeInPercent) {
		if (backgroundMusic != null) {
			backgroundMusic.setVolume(newVolumeInPercent);
		}
	}

	public void setVolumeInPercents(float newVolumeInPercent) {
		currentVolumeInPercents = newVolumeInPercent;
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
				return; // assumes the sound is not found in the list twice;
			}
		}
	}

	public static void setbackgroundMusic(Sound sound) {
		setbackgroundMusic(sound, true);
		backgroundMusic = sound;
	}

	public static void setbackgroundMusic(Sound sound, boolean overrideBackgrundMusic) {
		if (backgroundMusic == null || overrideBackgrundMusic) { //TODO ?????????????????
		}
		backgroundMusic = sound;
	}

	public void close() {
		for (CirculairList<Sound> sounds : soundMap.values()) {
			for (Sound sound : sounds.getArray()) {
				sound.closeClip();
			}
		}
		closeBackgroundMusic();
	}

	public static void closeBackgroundMusic() {
		if (backgroundMusic != null) {
			backgroundMusic.closeClip();
			backgroundMusic = null;
		}
	}
}
