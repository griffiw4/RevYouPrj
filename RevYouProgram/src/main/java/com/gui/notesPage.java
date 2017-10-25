package com.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Lana Adams
 * @version 1.2
 */
public class notesPage extends Application {


	//pane for class buttons..center
	/**
	 * 
	 */
	private GridPane announcePane;

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

	/*menu items for 'Options' */
	/**
	 * 
	 */
	private MenuItem  miLogout;

	//create the login page
	/**
	 * 
	 */
	public notesPage() {

		//////////////////////////////////////////////////////////////////////////////////////////////////

		//create the border pane for menubar
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #3A6470");//cadetblue

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
		//video display area
		//rectangle for video
		Rectangle r = new Rectangle();
		r.setWidth(250);
		r.setHeight(90);
		

		//description display
		

		//////////////////////////////////////////////////////////////////////////////////////////////////
		//video display pane



		/////////////////////////////////////////////////////////////////////////////////////////////////
		//comments <enter new>



		////////////////////////////////////////////////////////////////////////////////////////////////
		//comments <display>


		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//comment display pane
		
		

		/////////////////////////////////////////////////////////////////////////////////////////////////
		//
	}



	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage) {

		// PUT EVERYTHING TOGETHER 
		Scene scene = new Scene(borderPane, 800, 500);

		// Add the menubar and shapes to the borderpane
		borderPane.setTop(menuBar);
		borderPane.setCenter(announcePane);

		// Configure and display the stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("RevYou");
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




