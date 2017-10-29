package com.videototext;

import java.io.File;
import it.sauronsoftware.jave.*;

/**
 * Project: RevYou --  
 * Sub-Project: Downloadable transcripts --  
 * Class: VideoTuAudio --  
 * Description: Video to audio converter 
 * @author Juan Ortiz Couder
 * @version 1.0
 * 
 */

public class VideoTuAudio {
	
	/**
	 * gets selected input video and using the most common parameters for .wav files, it converts it to a audio file and saves it.
	 * @param video - a video that wants to be changed into transcripts
	 * @return Audio - an audio file that will later be converted into transcripts
	 */
	
	public static void ConvertToAudio(File video){
		
		System.out.println("Converting");
		File Audio = new File("F:\\Default user\\Documents\\5 semester Fall 2017\\5th semester Fall 2017\\Juancho\\SE300\\Audio.wav");
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(new Integer(50000));
		audio.setChannels(new Integer(2));
		audio.setSamplingRate(new Integer(44100));
		
		EncodingAttributes attr=new EncodingAttributes();
		attr.setFormat("wav");
		attr.setAudioAttributes(audio);
		
		Encoder encode=new Encoder();
		try {
		encode.encode(video, Audio, attr);
		System.out.println("Conversion done");
		} catch(Exception e){
			System.out.println(e);
		}
		
	}
}
