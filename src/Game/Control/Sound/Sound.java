package Game.Control.Sound;

import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import Game.Control.GameEngine.Log;

/**
 * Class is based on the following sources: 
 * 					Oracles sound artikler: 
 * 					https://docs.oracle.com/javase/tutorial/sound/
 *  				https://docs.oracle.com/javase/tutorial/sound/controls.html
 * 				   	https://docs.oracle.com/javase/tutorial/sound/playing.html
 * 				  	
 * 					http://stackoverflow.com/questions/8425481/play-wav-file-from
 * 					-jar-as-resource-using-java 
 * 				   	http://www.erpgreat.com/java/coding-for-playing-a-wav-file.htm
 * 					http://stackoverflow.com/questions/4560291/what-clip-and-dataline
 * 					-info-represents
 */
public class Sound implements LineListener {
	private static int globalCurrentSoundID = 0;
	private final int soundID;
	private final ArrayList<SoundFinishedListener> soundFinishedListeners = new ArrayList<SoundFinishedListener>();
	private final Clip clip;
	private boolean isPaused = false;
	private final FloatControl volumeControl;

	public Sound(Clip clip, float soundVolume) {
		this.soundID = globalCurrentSoundID;
		Sound.globalCurrentSoundID++;
		this.clip = clip;
		this.volumeControl = getVolumeControl();
		this.clip.addLineListener(this);
	}
	
	private FloatControl getVolumeControl()
	{
		if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			return (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		} else {
			Log.writeln("Computer does not support FloatControl.Type.MASTER_GAIN");
			return null;
		}
	}

	/**
	 * Starts playing the sound. This is done in a new thread, so that the
	 * program using the sound, can keep running while it is played.
	 */
	public void playSound() {
		try {
			isPaused = false; // Placer den efter clip.start()?
			clip.start();
		} catch (Exception e) {
			Log.writeError(e);
		}
	}

	/**
	 * Pauses the sound.
	 */
	public void pauseSound() {
		isPaused = true;
		clip.stop();
	}

	/**
	 * Makes the sound loop continuously.
	 */
	public void loopSound() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * Makes the sound loop the given times.
	 * @param numberOfLoops
	 *            The number of times the sound should loop
	 */
	public void loopSound(int numberOfLoops) {
		clip.loop(numberOfLoops);
	}

	/**
	 * Sets the volume that the sound is played at, to a given percentage of the
	 * maximum volume. Percentage is calulated so that a reducing the volume
	 * slider in the settings menu page reduces the sound volume with what is percieved like 50 percent
	 */
	public void setVolume(float newVolumeInPercents) {
		try {
			// TODO fix
			if (volumeControl != null) {
				float max = Math.abs(volumeControl.getMinimum()) + Math.abs(volumeControl.getMaximum());
				float dbReduction = (float) ((10 * Math.log(1 / newVolumeInPercents)) / Math.log(2));
				dbReduction = (Float.isInfinite(dbReduction)) ? max : dbReduction;
				dbReduction = (dbReduction > max) ? max : dbReduction;
				float newSoundVolume = max - dbReduction - Math.abs(volumeControl.getMinimum());
				volumeControl.setValue(newSoundVolume);
			}
		} catch (Exception e) {
			Log.writeError(e);
		}

	}

	public boolean equals(Sound sound) {
		if (sound != null) {
			if (this.soundID == sound.soundID) {
				return true;
			}
		}
		return false;
	}

	public boolean equals(Object o) {
		if (o != null && o.getClass() == Sound.class) {
			return equals((Sound) o);
		}
		return false;
	}

	@Override
	public void update(LineEvent event) {
		if (LineEvent.Type.STOP == event.getType() && !isPaused) {
			for (int i = 0; i < soundFinishedListeners.size(); i++) {
				soundFinishedListeners.get(i).soundFinished(this);
			}
		}
	}

	public void closeClip() {
		clip.close();
	}

	public void addSoundFinishedListener(SoundFinishedListener listener) {
		soundFinishedListeners.add(listener);
	}

	public void resetSound() {
		clip.setFramePosition(0);
	}

	public void removeSoundFinishedListener(SoundFinishedListener listener) {
		for (int i = 0; i < soundFinishedListeners.size(); i++) {
			if (listener == soundFinishedListeners.get(i)) {
				soundFinishedListeners.remove(i);
				return; // Assumes that there are only one copy of the same
						// listener in the list.
			}
		}
	}

}