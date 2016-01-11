package Control.Sound;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;


public class Sound implements LineListener{
	
	//public static Sound tileMovedSound = new Sound("res/bossdeath.wav");
	
	//Tager ikke højde for at loops skal fortsætte efter
	
	//Alle tråde bliver måske ikke fjernet når programmet lukker?
	
	/*
	 * Baseret på:
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
	
	private int soundID;
	private ArrayList<SoundFinishedListener> soundFinishedListeners;
	private Clip clip;
	private FloatControl volumeControl;
	
	public Sound(String path) {
		try {					
			soundID = globalCurrentSoundID;
			globalCurrentSoundID++;
			soundFinishedListeners = new ArrayList<SoundFinishedListener>();
			File audioFile = new File(path);			
			if(audioFile.exists()){				
				AudioInputStream streamOfSound = AudioSystem.getAudioInputStream(audioFile);
				clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, streamOfSound.getFormat()));
				System.out.println(streamOfSound.getFormat());
				System.out.println(clip.getFormat());
				clip.open(streamOfSound);		
				volumeControl = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);				
			}			
			soundFinishedListeners = new ArrayList<SoundFinishedListener>();
		}catch (LineUnavailableException e) {
			System.out.println("Line unavailable, sound = " + path);
			//Tilføj hertil og fiks problemmet
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Starts playing the sound. 
	 * This is done in a new thread, so that the program using the sound, can keep running while it is played.
	 */
	public void playSound(){
		try {
			//Allerede ny tråd?(*)
			clip.addLineListener(this);
			new Thread(){
				@Override
				public void run() {
					clip.start();
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	/**
	 * Pauses the sound.
	 */
	public void pauseSound() {
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
			if (clip != null && volumeControl != null) {
				volumeControl.setValue(volumeControl.getMaximum() * newVolumeInPercents);
			} else {
				System.out.println("TheFuck?!");
			}			
		} catch (Exception e) {
			e.printStackTrace();
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
		if (o.getClass() == Sound.class) {
			return equals((Sound) o);
		} else return false;
	}

	@Override
	public void update(LineEvent event) {
		if (LineEvent.Type.STOP == event.getType()) {
			for (SoundFinishedListener listener : soundFinishedListeners) {
				listener.soundClosed(this);
			}
			new Thread(){
				public void run() {
					clip.close();
					System.out.println("closeStart");
				}
			}.start();
		} else if (LineEvent.Type.CLOSE == event.getType()) {
			System.out.println("closeEnd");
		}
	}
	
	public void addSoundFinishedListener(SoundFinishedListener listener) {
		soundFinishedListeners.add(listener);
	}
}
