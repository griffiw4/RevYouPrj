package com.gui;

import java.io.File;

import com.cloudinary.uploader.VideoUpload;
import com.searchfunctions.SearchVideo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author Lana Adams ft. Otto
 * @version 1.0
 * @created 10-Oct-2017
 */

/**
 * @author Otto
 *
 */
public class homePage extends Application {
	
	/*
	 * TODO:
	 * >>see if we can do the menu only one time
	 */

	//pane for class buttons to go in the center of the main borderpane
	/**
	 * 
	 */
	private GridPane buttonPane;

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

	//menu items
	/**
	 * 
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	private MenuItem miClose, miAbout, miLogout;		

	//buttons
	/**
	 * 
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	private Button classbt1, classbt2, classbt3, classbt4;

	//create the login page
	/**
	 * 
	 */
	public homePage() {

		//////////////////////////////////////////////////////////////////////////////////////////////////
		//create the border pane for menubar
		borderPane = new BorderPane();

		//create the menu items for the menubar
		miClose = new MenuItem("Close");
		miLogout = new MenuItem("Logout");
		miAbout = new MenuItem("About...");

		//create the menus
		menuFile = new Menu("File");
		menuHelp = new Menu("Help");	

		//create the menu bar
		menuBar = new MenuBar();	
		menuBar.setStyle("-fx-background-color: #DAEBF2;-fx-font-family: \"Cambria Math\";");

		//add menu items
		menuFile.getItems().addAll(miLogout, new SeparatorMenuItem(), miClose);
		menuHelp.getItems().add(miAbout);

		// Add menus to menuBar
		menuBar.getMenus().addAll(menuFile, menuHelp);

		//////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 * TODO:
		 * >>set up naming loop for class buttons
		 * >>if statement for number of classes 2-4
		 * 	>>else (1) will just open the classpage
		 * 
		 */
		//create the class buttons
		classbt1 = new Button("Class 1");
		classbt1.setStyle("-fx-font-size: 15pt;-fx-font-family: \"Cambria Math\";");
		classbt2 = new Button("Class 2");
		classbt2.setStyle("-fx-font-size: 15pt;-fx-font-family: \"Cambria Math\";");
		classbt3 = new Button("Class 3");
		classbt3.setStyle("-fx-font-size: 15pt;-fx-font-family: \"Cambria Math\";");
		classbt4 = new Button("Class 4");
		classbt4.setStyle("-fx-font-size: 15pt;-fx-font-family: \"Cambria Math\";");

		//ToolTips for buttons
		Tooltip tt1 = new Tooltip("Click to procede to Class 4");
		Tooltip.install(classbt4, tt1);
		Tooltip tt2 = new Tooltip("Class 3");
		Tooltip.install(classbt3, tt2);
		Tooltip tt3 = new Tooltip("Class 2");
		Tooltip.install(classbt2, tt3);
		Tooltip tt4 = new Tooltip("Class 1");
		Tooltip.install(classbt1, tt4);

		//place the buttons into their own gridpane for the classes
		buttonPane = new GridPane();
		buttonPane.setVgap(50);
		buttonPane.setHgap(20);
		buttonPane.setAlignment(Pos.CENTER);		
		Label selectLable = new Label("Select a class");
		selectLable.setStyle("-fx-font-size: 36pt;-fx-text-fill: #DAEBF2; -fx-font-family: \"Cambria Math\";");
		buttonPane.add(selectLable, 1, 0);
		buttonPane.add(classbt1,  0,1);
		buttonPane.add(classbt2, 2, 1);
		buttonPane.add(classbt3, 0, 2);
		buttonPane.add(classbt4, 2, 2);
		//////////////////////////////////////////////////////////////////////////////////////////////////


	}



	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage) {
		
		// PUT EVERYTHING TOGETHER 
		Scene scene = new Scene(borderPane,800,500);

		// Add the menubar and shapes to the borderpane
		borderPane.setTop(menuBar);
		borderPane.setCenter(buttonPane);
		borderPane.setStyle("-fx-background-color: #3A6470");//cadetblue
		//Upload button
		final Button uploadButton = new Button("Uplaod Video");
		final Stage SEARCH_STAGE = primaryStage;
		uploadButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				File file = VideoUpload.getFileToUpload(SEARCH_STAGE);
		        VideoUpload.uploadFile(file);
			}
		});
		borderPane.setRight(uploadButton);
		// Configure and display the stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("RevYou");
		primaryStage.setMaximized(true);

		primaryStage.show();

		//close using the drop down
		miClose.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent exit) {Platform.exit();}
		});
		
		//class selection
		final classPage classPage = new classPage();
		final Stage NEW_STAGE = primaryStage;
		classbt1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					classPage.start(NEW_STAGE);
				} catch (Exception e1) {
					classPage.start(NEW_STAGE); 
				} 
			}
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




