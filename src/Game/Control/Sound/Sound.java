package Control.Sound;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;


public class Sound{
	
	//public static Sound tileMovedSound = new Sound("res/bossdeath.wav");
	
	//Tager ikke højde for at loops skal fortsætte efter
	
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
			File audioFile = new File(path);			
			if(audioFile.exists()){				
				AudioInputStream streamOfSound = AudioSystem.getAudioInputStream(audioFile);
				clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, streamOfSound.getFormat()));
				System.out.println(clip.getFormat());
				clip.open(streamOfSound);				
				volumeControl = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);				
			}
			
			soundFinishedListeners = new ArrayList<SoundFinishedListener>();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		clip.flush();
	}
	
	/**
	 * Starts playing the sound. 
	 * This is done in a new thread, so that the program using the sound, can keep running while it is played.
	 */
	public void playSound(){
		try {
			new Thread(){
				public void run(){
					//Starts the clip, and when it has finished running, it terminates it.
					clip.start();					
					clip.close();					
				}				
			}.start();
			//When it is done playing, and the sound closes, it announces the to the listeners, with itself:
			for (SoundFinishedListener listener : soundFinishedListeners) {
				listener.soundClosed(this);
			}
			
		} catch (Exception e) {
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
		volumeControl.setValue(volumeControl.getMaximum() * newVolumeInPercents);
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
	
}
