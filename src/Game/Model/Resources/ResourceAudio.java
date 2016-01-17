package Game.Model.Resources;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;

import Game.Control.GameEngine.Log;
import Game.Control.Sound.Sound;

public class ResourceAudio {

	private static final String AUDIO_DIRECTORY_PATH = "res" + File.separator + "audio";
	
	public static final String TILE_MOVED_SOUND = "badminton_racket_fast_movement_swoosh_005.wav";
	public static final String DEATH_METAL_SONG = "01 The Vampire From Nazareth.wav";
	
 	/**
 	 * @param name
 	 * @param soundVolume
 	 * @return the sound with the specified name at the given volume
 	 */
 	public static Sound loadSound(String name, float soundVolume)
	{
		Sound[] sound = loadSounds(name, 1, soundVolume);
		return (sound == null) ? null : sound[0];
	}

	/**
	 * Loads the sounds by adding the audio clips as sounds to an array of sounds and sets the volume.
	 * @param name
	 * @param amount
	 * @param soundVolume
	 * @return a Sound array
	 */
	public static Sound[] loadSounds(String name, int amount, float soundVolume) {
		Sound[] sounds = new Sound[amount];
		Clip[] clips = getSoundCopys(name, amount);
		for (int i = 0; i < sounds.length; i++) {
			sounds[i] = new Sound(clips[i], soundVolume);
			sounds[i].setVolume(soundVolume);
		}
		return sounds;
	}

	/**
	 * Gets an array of audio clips by accessing the file looking for the specified clip name.
	 * Opens the clip readying it for use.
	 * @param name
	 * @param amount
	 * @return an array of audio clips
	 */
	private static Clip[] getSoundCopys(String name, int amount) {
		String audioPath = AUDIO_DIRECTORY_PATH + File.separator + name;
		try {
			File audioFile = new File(audioPath);
			if (audioFile.exists() && audioFile.isFile() && audioFile.canRead()) {
				try (AudioInputStream streamOfSound = AudioSystem.getAudioInputStream(audioFile)) {
					Clip[] clips = new Clip[amount];
					byte[] soundBytes = getAudioBytes(streamOfSound);
					for (int i = 0; i < amount; i++) {
						clips[i] = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, streamOfSound.getFormat()));
						clips[i].open(streamOfSound.getFormat(), soundBytes, 0, soundBytes.length);
					}
					return clips;
				}
			} else {
				Log.writeln("Sound file not available: " + audioPath);
			}
		} catch (LineUnavailableException e) {
			Log.writeln(("Line unavailable from sound: " + audioPath));
		} catch (Exception e) {
			Log.writeError(e);
		}
		return null;
	}

	/**
	 * Gets the information of the audio clip as a byte array so it can later be used to open and play the clip.
	 * @param streamOfSound
	 * @return a byte array with audio information
	 */
	private static byte[] getAudioBytes(AudioInputStream streamOfSound) {
		try {
			try (ByteArrayOutputStream tempByteStorage = new ByteArrayOutputStream()) {
				byte[] buffer = new byte[65536];
				int readBytes = 0;
				while ((readBytes = streamOfSound.read(buffer, 0, buffer.length)) != -1) {
					tempByteStorage.write(buffer, 0, readBytes);
				}
				return tempByteStorage.toByteArray();
			}
		} catch (IOException e) {
			Log.writeError(e);
		}
		return null;
	}
}
