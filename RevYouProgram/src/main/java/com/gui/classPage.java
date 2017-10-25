package com.gui;

import java.util.List;

import com.cloudinary.uploader.VideoUpload;
import com.revyouviddb.VideoDb;
import com.searchfunctions.SearchVideo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Lana Adams ft. Otto O. Legon
 * @version 1.2
 */
public class classPage extends Application {


	//panes for the Announcement section, the center of the screen
	//and the lecture/notes panes for the center pane to hold
	/**
	 * 
	 */
	/**
	 * 
	 */
	private GridPane announcePane, centerPane;
	/**
	 * 
	 */
	/**
	 * 
	 */
	private VBox videoSearchPane, notesSearchPane;

	//pane for menu bar
	/**
	 * 
	 */
	private BorderPane borderPane;

	//menu bar
	/**
	 * 
	 */
	private MenuBar menuBar;
	/**
	 * 
	 */
	/**
	 * 
	 */
	private Menu menuFile, menuHelp;		

	// Menus

	// Close MenuItem
	/**
	 * 
	 */
	/**
	 * 
	 */
	private MenuItem miClose, miAbout;		

	//menu items for 'Options' 
	/**
	 * 
	 */
	private MenuItem  miLogout;
	
	//Video Vars
	/**
	 * 
	 */
	private List <VideoDb> videosFound = null;
	/**
	 * 
	 */
	private ListView<VideoDb> returnedVFilesList = new ListView<VideoDb>();
	/**
	 * 
	 */
	private Button viewButton = new Button("View");

	//create the login page
	/**
	 * 
	 */
	public classPage() {

		//////////////////////////////////////////////////////////////////////////////////////////////////
		//create the border pane for menubar
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #3A6470");

		//create the menu items for the menubar
		miClose = new MenuItem("Close");
		miLogout = new MenuItem("Logout");
		miAbout = new MenuItem("About...");

		//create the menus
		menuFile = new Menu("File");
		menuHelp = new Menu("Help");	

		//create the menu bar
		menuBar = new MenuBar();	
		menuBar.setStyle("-fx-background-color: #DAEBF2;");

		//add menu items
		menuFile.getItems().addAll(miLogout, new SeparatorMenuItem(), miClose);
		menuHelp.getItems().add(miAbout);

		// Add menus to menuBar
		menuBar.getMenus().addAll(menuFile, menuHelp);

		//////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 * TODO:
		 * >>add the announcements
		 * >>set on actions for the announcements to pop out to read
		 * >>add new announcements for prof only
		 */
		//Set up the Announcements section
		announcePane = new GridPane();
		announcePane.setVgap(10);
		announcePane.setHgap(20);
		announcePane.setAlignment(Pos.BOTTOM_CENTER);		
		Label annLabel = new Label("\t\t\t\tAnnouncements");
		Label blankLabel1 = new Label(" ");//visual spacing
		announcePane.add(blankLabel1, 1, 0);
		annLabel.setStyle("-fx-text-fill: #DAEBF2 ; -fx-font-family: \"Cambria Math\"; -fx-font-size: 18pt;");
		announcePane.add(annLabel, 1, 1);
		ListView<String> list = new ListView<String>();
		ObservableList<String> items =FXCollections.observableArrayList (" ", " ", " ", " ");

		//////////make the list better - ask otto
		
		list.setItems(items);
		list.setPrefWidth(500);
		list.setPrefHeight(90);
		announcePane.add(list, 1, 2);
		Label blankLabel2 = new Label(" ");//visual spacing
		announcePane.add(blankLabel2, 1, 3);

		/////////////////////////////////////////////////////////////////////////////////////////////////
		//set up lecture search pane
		viewButton.setDisable(true);
		borderPane.setLeft(viewButton);
		viewButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (borderPane.getRight() != null)
					if (borderPane.getRight() instanceof MediaView){
						((MediaView)borderPane.getRight()).getMediaPlayer().dispose();
					}
				VideoDb videoObject = (VideoDb) returnedVFilesList.getSelectionModel().getSelectedItem();
		        Media video=new Media(VideoUpload.downloadFile(videoObject).toString());
		        MediaPlayer player=new MediaPlayer(video);
		        player.setAutoPlay(true);
		        MediaView mediaView=new MediaView(player);
		        borderPane.setRight(mediaView);
			}
		});
		videoSearchPane = new VBox(8);
		final TextField searchBarV = new TextField();
		searchBarV.setPadding(new Insets(10.0));
		Button vsGoBt = new Button("Search through lectures...");
		vsGoBt.setAlignment(Pos.CENTER);
		vsGoBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (searchBarV.getText() != null){
					if (borderPane.getRight() != null)
						if (borderPane.getRight() instanceof MediaView){
							((MediaView)borderPane.getRight()).getMediaPlayer().dispose();
							((MediaView)borderPane.getRight()).setVisible(false);
						}
				    videosFound = SearchVideo.search(searchBarV.getText());
				    if(SearchVideo.search(searchBarV.getText()) == null){
				    	Alert noVid = new Alert(AlertType.WARNING);
				    	noVid.setTitle("No Videos Found");
				    	noVid.setHeaderText("No video found, please search something else");
				    	noVid.showAndWait();
				    }
				    if(videosFound != null){
				    	returnedVFilesList.setItems(FXCollections.observableList(videosFound));
						returnedVFilesList.setPrefWidth(300);
						returnedVFilesList.setPrefHeight(90);
						returnedVFilesList.getSelectionModel().select(0);
						viewButton.setDisable(false);
				    }
				}
			}
		});
		//returnedVFilesList.setItems(FXCollections.observableList(videosFound));
		returnedVFilesList.setPrefWidth(300);
		returnedVFilesList.setPrefHeight(90);
		Label vLabel = new Label("Enter key word:");//user instruction
		videoSearchPane.getChildren().addAll(vLabel, searchBarV, vsGoBt, returnedVFilesList);
		Label blankLabel3 = new Label(" ");//visual spacing
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////
		//set up notes search pane
		notesSearchPane = new VBox(8);	
		TextField searchBarN = new TextField();
		searchBarN.setPadding(new Insets(10.0));
		Button nsGoBt = new Button("Search through notes...");
		nsGoBt.setAlignment(Pos.CENTER);
		ListView<String> returnedNFilesList = new ListView<String>();
		ObservableList<String> nFiles =FXCollections.observableArrayList (" ", " ", " ", " ");
		returnedNFilesList.setItems(nFiles);
		returnedNFilesList.setPrefWidth(300);
		returnedNFilesList.setPrefHeight(90);
		Label nLabel = new Label("Enter key word:");
		notesSearchPane.getChildren().addAll(nLabel, searchBarN, nsGoBt, returnedNFilesList);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		//establish gridpane for center
		centerPane = new GridPane();
		centerPane.setVgap(10);
		centerPane.setHgap(20);
		centerPane.setStyle("-fx-background-color: #DAEBF2");
		centerPane.add(videoSearchPane, 0, 0);
		centerPane.add(blankLabel3, 1, 0);
		centerPane.add(notesSearchPane, 2,0);
		centerPane.setAlignment(Pos.CENTER);

		//select video and follow "ok" to video page	
		//select notes and follow "ok" to notes page

	}



	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage) {

		// PUT EVERYTHING TOGETHER 
		Scene scene = new Scene(borderPane, 800, 500);

		// Add the menubar and shapes to the borderpane
		borderPane.setTop(menuBar);
		borderPane.setCenter(centerPane);
		borderPane.setBottom(announcePane);

		// Configure and display the stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("RevYou");
		primaryStage.setMaximized(false);
		primaryStage.setMaximized(true);


		primaryStage.show();

		//close using the drop down
		final Stage NEW_STAGE = primaryStage; 
		miClose.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent exit) {Platform.exit();}
		});
		

		//logout and return to login page
		final ApplicationGUI ApplicationGUI = new ApplicationGUI();
		miLogout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					ApplicationGUI.start(NEW_STAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}




