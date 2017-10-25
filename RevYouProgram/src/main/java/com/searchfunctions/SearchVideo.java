package com.searchfunctions;

import java.util.ArrayList;
import java.util.List;

import com.revyouviddb.VidDBManager;
import com.revyouviddb.VideoDb;

/**
 * @author Otto
 *
 */
public class SearchVideo {

	//create list of videos
	/**
	 * 
	 */
	private static List <VideoDb> allVids;
	/**
	 * 
	 */
	private static VidDBManager mang = new VidDBManager();
	
	/**
	 * @param searchedWord
	 * @return
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