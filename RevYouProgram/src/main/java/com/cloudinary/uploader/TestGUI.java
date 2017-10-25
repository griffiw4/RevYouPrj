package com.cloudinary.uploader;

import java.io.File;
import java.util.List;

import com.revyouviddb.VidDBManager;
import com.revyouviddb.VideoDb;
import com.searchfunctions.SearchVideo;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.*;

public class TestGUI extends Application {

	private List <VideoDb> videosFound = null;
	private TextField searchBar = new TextField();
	private BorderPane root = new BorderPane();
	private Button searchButton = new Button("Search");
	private ComboBox vidList = new ComboBox();
	private Button viewButton = new Button("View");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Uploading file
		/*File file = VideoUpload.getFileToUpload(primaryStage);
        VideoUpload.uploadFile(file);*/
        //Playing file
		viewButton.setVisible(false);
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (searchBar.getText() != null){
				    videosFound = SearchVideo.search(searchBar.getText());
				    if(videosFound != null){
					    vidList.setItems(FXCollections.observableList(videosFound));
					    vidList.getSelectionModel().select(0);
						viewButton.setVisible(true);
				    }
				}
			}
		});
		viewButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				VideoDb videoObject = (VideoDb) vidList.getSelectionModel().getSelectedItem();
		        Media video=new Media(VideoUpload.downloadFile(videoObject).toString());
		        MediaPlayer player=new MediaPlayer(video);
		        player.setAutoPlay(true);
		        MediaView mediaView=new MediaView(player);
		        root.setCenter(mediaView);
			}
		});
		HBox searchPane = new HBox();
		searchPane.getChildren().addAll(searchBar, searchButton); 
		root.setTop(searchPane);
		VBox selectPane = new VBox();
		selectPane.getChildren().addAll(vidList,viewButton);
		root.setLeft(selectPane);
        primaryStage.setScene(new Scene(root,1000,1000));
        primaryStage.show();
	}

	public static void main(String[] args) {
        launch(args);
    }
}
