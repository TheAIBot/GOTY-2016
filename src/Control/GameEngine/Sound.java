package Control.GameEngine;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
	
	//public static Sound tileMovedSound = new Sound("res/bossdeath.wav");
	
	/*
	 * Baseret på:
	 * Notch ludom dare.
	 * Oracles sound artikler: https://docs.oracle.com/javase/tutorial/sound/
	 * 		Herunder specielt disse artikler: 
	 * 				https://docs.oracle.com/javase/tutorial/sound/controls.html
	 * 				https://docs.oracle.com/javase/tutorial/sound/playing.html (*)
	 * http://stackoverflow.com/questions/8425481/play-wav-file-from-jar-as-resource-using-java
	 */
	
	private Clip clip;
	private FloatControl volumeControl;
	private BooleanControl muteControl;
	
	public Sound(String path) {
		try {
			
			/*			
			AudioClip audioClip = Applet.newAudioClip(new File(path).toURI().toURL()); 
			audioClip.play(); 			
			*/
			
			//Laver et clip som tager input fra en lydfil.			
			//http://www.erpgreat.com/java/coding-for-playing-a-wav-file.htm
			//http://stackoverflow.com/questions/4560291/what-clip-and-dataline-info-represents
			//			
			
			File audioFile = new File(path);
			System.out.println(audioFile.getAbsolutePath());
			
			if(audioFile.exists()){
				
				clip = AudioSystem.getClip();
				System.out.println(clip.getFormat().toString());;
						
				
				AudioInputStream streamOfSound = AudioSystem.getAudioInputStream(audioFile);
				clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, streamOfSound.getFormat()));
				System.out.println(clip.getFormat().toString());
				clip.open(streamOfSound);				
				clip.start();			
				
				
				//clip.open(streamOfSound);
	            //clip.start();				
				volumeControl = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
				muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Plays the sound file.
	 */
	public void playSound(){
		clip.start();
	}

	/**
	 * 
	 */
	public void pauseSound() {
		clip.stop();
	}
	
	/**
	 * 
	 */
	public void loopSound(){
		while (clip.isActive()) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);			
		}
	}
	
	/**
	 * 
	 */
	public void muteSound(){
		muteControl.setValue(false);
	}
	
	/**
	 * 
	 */
	public void unMuteSound(){
		muteControl.setValue(true);
	}
	
	/**
	 * https://docs.oracle.com/javase/tutorial/sound/controls.html
	 */
	public void setVolume(float newVolume){
		volumeControl.setValue(newVolume);
	}
	
}
