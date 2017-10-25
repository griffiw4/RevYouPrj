package com.searchfunctions;

import java.util.ArrayList;
import java.util.List;

import com.revyouviddb.VidDBManager;
import com.revyouviddb.VideoDb;

/**
 * Project: RevYou --  
 * Sub-Project: Search Functions for Video --  
 * Class: SearchVideo --  
 * Description: Utility class for relational database search 
 * @author Otto O. Legon and Will Griffin
 * @version 2.0
 * 
 */
public class SearchVideo {

	//create list of videos
	/**
	 * Contains a local copy of the rows/entities in relational database table, which are VideoDb objects
	 */
	private static List <VideoDb> allVids;
	/**
	 * Video Database Manager object. 
	 * Used to interact with video table in relational database.
	 */
	private static VidDBManager mang = new VidDBManager();
	
	/**
	 * Searches video table in database for videos whose description, name, and or tags contain the searched word.
	 * Returns list of videos found, or null if nothing found.
	 * @param searchedWord a String defined by another class representing the desired content of video
	 * @return foundVideos - a List of VideoDb objects which represent videos containing the searched word
	 * 
	 */
	public static List <VideoDb> search(String searchedWord){
		allVids = mang.getList();
		ArrayList <VideoDb> foundVideos = new ArrayList();
		boolean found = false;
		for (VideoDb vid : allVids){
			if(vid.getVisibility()){
				if(vid.getTags() != null)
					if(vid.getTags().toLowerCase().contains(searchedWord.toLowerCase())){
							found = true;
					}
				if(found == false){
					if(vid.getVideoName().toLowerCase().contains(searchedWord.toLowerCase())){
						found = true;
					}
				}
				if(found == false){
					if(vid.getDescription().toLowerCase().contains(searchedWord.toLowerCase())){
						found = true;
					}
				}
				if(found == true){
					foundVideos.add(vid);
				}
			}
		}
		//Display titles of all videos that were recorded
		if(foundVideos.size()==0){
			System.out.println("nothing found");
			return null;
		}else{
			for(VideoDb vids:foundVideos)
				System.out.println(vids);
		}
		return foundVideos;
	}
	
	
}