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
	
 	public static Sound loadSound(String name, float soundVolume)
	{
		Sound[] sound = loadSounds(name, 1, soundVolume);
		return (sound == null) ? null : sound[0];
	}

	public static Sound[] loadSounds(String name, int amount, float soundVolume) {
		Sound[] sounds = new Sound[amount];
		Clip[] clips = getSoundCopys(name, amount);
		for (int i = 0; i < sounds.length; i++) {
			sounds[i] = new Sound(clips[i], soundVolume);
			sounds[i].setVolume(soundVolume);
		}
		return sounds;
	}

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
