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
 	 * @param name the sounds  name to load
 	 * @param soundVolume  the volume to preset the sound at
 	 * @return the sound with the specified name at the given volume
 	 */
 	public static Sound loadSound(String name, float soundVolume)
	{
		Sound[] sound = loadSounds(name, 1, soundVolume);
		// if no sound was loaded then return null
		return (sound == null) ? null : sound[0];
	}

	/**
	 * Loads the sound and returns it as a Sound array with the specified copys of the sound
	 * @param name the sound name to load
	 * @param amount amount of copys of the sound
	 * @param soundVolume the volume to preset the sound at
	 * @return a Sound array an array of sounds that all have the same sound loaded with the name
	 */
	public static Sound[] loadSounds(String name, int amount, float soundVolume) {
		Sound[] sounds = new Sound[amount];
		Clip[] clips = getSoundCopys(name, amount);
		if (clips == null) {
			return null;
		}
		for (int i = 0; i < sounds.length; i++) {
			sounds[i] = new Sound(clips[i], soundVolume);
			sounds[i].setVolume(soundVolume);
		}
		return sounds;
	}

	/**
	 * Gets an array of audio clips by accessing the file looking for the specified clip name.
	 * Opens the clip readying it for use.
	 * @param name the sound name to load
	 * @param amount amount of copys of the sound
	 * @return an array of audio clips
	 */
	private static Clip[] getSoundCopys(String name, int amount) {
		String audioPath = AUDIO_DIRECTORY_PATH + File.separator + name;
		try {
			File audioFile = new File(audioPath);
			//Check if the file can be loaded
			if (audioFile.exists() && audioFile.isFile() && audioFile.canRead()) {
				//Get an audio stream o the sound file that is used to load the sound into memory as bytes
				try (AudioInputStream streamOfSound = AudioSystem.getAudioInputStream(audioFile)) {
					Clip[] clips = new Clip[amount];
					//the whole sound clip as bytes is loaded so every clip can copy the bytes
					//which is faster and safer than loading the file multiple times
					byte[] soundBytes = getAudioBytes(streamOfSound);
					//fill the Clip[] with Clips that uses the byte[] as their sound
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
				// buffer the AudioStream writes to from the file
				byte[] buffer = new byte[65536];
				int readBytes = 0;
				// reads all the bytes in the stream by continiue reading untill read returns -1
				//which means that the stream hasreached the end.
				// thebytes a written to a byte buffer to make sure the buffer doesn't overflow
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
