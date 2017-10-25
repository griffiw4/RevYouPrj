
package com.cloudinary.uploader;

//Fx imports
import javafx.stage.Stage;
import javafx.stage.FileChooser;

//IO and NET imports
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

//Cloudinary API imports
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

//DB manager import
import com.revyouviddb.VidDBManager;
import com.revyouviddb.VideoDb;

/**
 * Project: RevYou --  
 * Sub-Project: Video Upload and Download Utilities --  
 * Class: VidDBManager --  
 * Description: Graphical access for database management using JavaFX as main graphics provider
 * @author Otto O. Legon
 * @version 2.0
 * 
 */
public class VideoUpload {
	
	//File Chooser (restricts to mp4 files)
	/**
	 * Allows user to search for and select an mp4 file
	 * @param primarystage a JavaFX Stage where application is running
	 * @return file - a JavaIO File representing chosen file
	 * 
	 */
	public static File getFileToUpload(Stage primarystage){
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(
	        new FileChooser.ExtensionFilter("MP4", "*.mp4")
	    );
		File file = chooser.showOpenDialog(primarystage);
		return file;
	}
	
	//Uploader
	/**
	 * Uploads an mp4 file to cloudinary server
	 * @param file a JavaIO File representing chosen file
	 * 
	 */
	public static void uploadFile(File file){
		//Sets path to cloudinary
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "df7iwmfdk",
				  "api_key", "122162666434355",
				  "api_secret", "OFzIqrKU3zl_UyXJEzPo2D2G65M"));
		//Uploads video file to cloudinary and saves URL to DB
		try {
			VidDBManager vidMang = new VidDBManager();
			Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap("resource_type", "video"));
			String url = uploadResult.toString().substring(
					uploadResult.toString().indexOf("https"), uploadResult.toString().indexOf(".mp4")+4);
			vidMang.newVideo(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//Returns URL 
	/**
	 * Obtains URL of desired video
	 * @param videoObject a VideoDb object representing desired video
	 * @return webLoc - a JavaNET URL object representing video location on cloudinary server
	 * 
	 */
	public static URL downloadFile(VideoDb videoObject){
		String url = videoObject.getVideoUrl();
		URL webLoc = null;
		try {
			webLoc = new URL(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return webLoc;
	}

}
