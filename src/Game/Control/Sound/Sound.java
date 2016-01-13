package Game.Control.Sound;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.swing.SwingUtilities;

import Game.Control.GameEngine.Log;


public class Sound implements LineListener{
	
	//public static Sound tileMovedSound = new Sound("res/bossdeath.wav");
	
	//Tager ikke h�jde for at loops skal forts�tte efter
	
	//Alle tr�de bliver m�ske ikke fjernet n�r programmet lukker?
	
	/*
	 * Baseret p�:
	 * Notch ludom dare.
	 * Oracles sound artikler: https://docs.oracle.com/javase/tutorial/sound/
	 * 		Herunder specielt disse artikler: 
	 * 				https://docs.oracle.com/javase/tutorial/sound/controls.html
	 * 				https://docs.oracle.com/javase/tutorial/sound/playing.html (*)
	 * http://stackoverflow.com/questions/8425481/play-wav-file-from-jar-as-resource-using-java
	 * http://www.erpgreat.com/java/coding-for-playing-a-wav-file.htm
	 * http://stackoverflow.com/questions/4560291/what-clip-and-dataline-info-represents	
	 */
	
	private static int globalCurrentSoundID = 0;
	private final int soundID;
	private ArrayList<SoundFinishedListener> soundFinishedListeners;
	private Clip clip;
	private static ArrayList<FloatControl> volumeControls;
	private static boolean isPaused = false;
	
	public Sound(String path) {
		if (volumeControls == null) {
			setVolumeControls();
		}
		soundID = globalCurrentSoundID;
		globalCurrentSoundID++;
		soundFinishedListeners = new ArrayList<SoundFinishedListener>();
		try {					
			File audioFile = new File(path);			
			if(audioFile.exists() && audioFile.isFile() && audioFile.canRead()){	
				AudioInputStream streamOfSound = AudioSystem.getAudioInputStream(audioFile);
				clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, streamOfSound.getFormat()));
				clip.open(streamOfSound);
				streamOfSound.close();
			}
			else {
				Log.writeln("Sound file not available: " + path);
			}
		}catch (LineUnavailableException e) {
			Log.writeln(("Line unavailable, sound = " + path));
		}catch (Exception e) {
			Log.writeError(e);
		} 
	}
	
	/**
	 * found code example from http://stackoverflow.com/posts/17502340/revisions
	 */
	private void setVolumeControls()
	{
		volumeControls = new ArrayList<FloatControl>();
		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo(); 
		for (Info mixerInfo : mixerInfos) {
	        Mixer mixer = AudioSystem.getMixer(mixerInfo);
	        Line.Info[] lineInfos = mixer.getTargetLineInfo();
	        for(Line.Info lineinfo : lineInfos){
	            try {
	                Line line = mixer.getLine(lineinfo);
	                line.open();
	                if(line.isControlSupported(FloatControl.Type.VOLUME)){
	                    volumeControls.add((FloatControl) line.getControl(FloatControl.Type.VOLUME));
	                } else {
						Log.writeln("Mixer: " + mixerInfo.getName() + " doesn't support volume control");
					}
	            } catch (LineUnavailableException e) {
	                Log.writeError(e);
	            }
	        }
		}
	}
	
	/**
	 * Starts playing the sound. 
	 * This is done in a new thread, so that the program using the sound, can keep running while it is played.
	 */
	public void playSound(){
		try {
			clip.addLineListener(this);
			isPaused = false; //Placer den efter clip.start()?
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
	public void loopSound(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);		
	}

	/** Makes the sound loop the given times. 
	 * @param numberOfLoops The number of times the sound should loop
	 */	
	public void loopSound(int numberOfLoops){
		clip.loop(numberOfLoops);
	}
	
	/** Sets the volume that the sound is played at, to a given percentage of the maximum volume
	 */
	public void setVolume(float newVolumeInPercents){
		try {
			if (clip != null) {
				for (FloatControl volumeControl : volumeControls) {
					if (volumeControl != null) {
						volumeControl.setValue(newVolumeInPercents);
					}
				}
			} else {
				Log.writeln("Tried to change volume when clip was null");
			}			
		} catch (Exception e) {
			Log.writeError(e);
		}
		
	}
	
	public boolean equals(Sound sound){
		if (sound != null) {
			if (this.soundID == sound.soundID) {
				return true;
			}	
		} 
		return false;		
	}
	
	public boolean equals(Object o){
		if (o != null && o.getClass() == Sound.class) {
			return equals((Sound) o);
		}
		return false;
	}

	
	public void update(LineEvent event) {
		if (LineEvent.Type.STOP == event.getType() && !isPaused) {
			/*
			SwingUtilities.invokeLater(new Runnable() {			
				@Override
				public void run() {
					closeClip();			
					System.out.println("It has closed!");
				}
			}); */
			
			for (int i = 0; i < soundFinishedListeners.size(); i++) {
				soundFinishedListeners.get(i).soundFinished(this);
			}
		} 
	}
	
	public void closeClip(){
		clip.close();
	}
	
	public void addSoundFinishedListener(SoundFinishedListener listener) {
		soundFinishedListeners.add(listener);
	}

	public void resetSound(){
		clip.setFramePosition(0);
	}

	public void removeSoundFinishedListener(SoundFinishedListener listener){
		for (int i = 0; i < soundFinishedListeners.size(); i++) {
			if (listener == soundFinishedListeners.get(i)) {
				soundFinishedListeners.remove(i);
				return; //Assumes that there are only one copy of the same listener in the list.
			}
		}
	}
	
}