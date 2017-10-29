package com.videototext;

import java.io.File;

/**
 * Project: RevYou --  
 * Sub-Project: Downloadable transcripts --  
 * Class: main --  
 * Description: Video to audio converter 
 * @author Juan Ortiz Couder
 * @version 1.0
 * 
 */

public class main {
	
	/**
	 * gets selected input video and using the most common parameters for .wav files, it converts it to a audio file and saves it.
	 * @param video - a video that wants to be changed into transcripts
	 * @return Audio - an audio file that will later be converted into transcripts
	 */
	
	 public static void main(String[] args) {
		 File Video=new File("F:\\Default user\\Documents\\5 semester Fall 2017\\5th semester Fall 2017\\Juancho\\SE300\\IMG_3362.mp4");
		 VideoTuAudio.ConvertToAudio(Video);
	 }
 
}
