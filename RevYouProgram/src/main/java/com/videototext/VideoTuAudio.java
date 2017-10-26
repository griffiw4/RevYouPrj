package com.videototext;

import java.io.File;
import it.sauronsoftware.jave.*;

public class VideoTuAudio {
	
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
