package com.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Lana Adams
 * @version 1.20
 */
public class ApplicationGUI extends Application {


	//gridpane for the login
	/**
	 * 
	 */
	private GridPane loginPane;

	//text fields for user inputs
	/**
	 * 
	 */
	static TextField userID = new TextField();
	/**
	 * 
	 */
	static PasswordField userPassword = new PasswordField();

	//the buttons 
	/**
	 * 
	 */
	/**
	 * 
	 */
	private Button 
	loginBt, helpBt;

	//login labels
	/**
	 * 
	 */
	/**
	 * 
	 */
	private Label 
	pwLabel, userLabel;

	//catch input
	/**
	 * 
	 */
	static String userName = userID.getText();
	/**
	 * 
	 */
	static String password = userPassword.getText();

	//create the login page
	/**
	 * 
	 */
	public ApplicationGUI() {

		//instantiate the login pane
		loginPane = new GridPane();
		loginPane.setVgap(20);
		loginPane.setHgap(20);


		//login labels
		pwLabel = new Label("Password:");
		pwLabel.setStyle("-fx-text-fill: #DAEBF2 ; -fx-font-family: \"Cambria Math\"; -fx-font-size: 16pt;" );
		userLabel = new Label("User Name:");
		userLabel.setStyle("-fx-text-fill: #DAEBF2; -fx-font-family: \"Cambria Math\"; -fx-font-size: 16pt; ");


		//instantiate the buttons and text fields 
		loginBt = new Button("          Login          ");
		helpBt = new Button("           Help          ");
		//ToolTips for buttons
		Tooltip tt1 = new Tooltip("Click to log in");
		Tooltip.install(loginBt, tt1);
		Tooltip tt2 = new Tooltip("Click for more information");
		Tooltip.install(helpBt, tt2);
		//layout
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setOpacity(100);
		loginPane.add(userLabel , 0, 0);
		loginPane.add(userID, 1, 0);
		loginPane.add(pwLabel, 0, 1);
		loginPane.add(userPassword, 1, 1);
		loginPane.add(loginBt, 1, 3);
		loginPane.add(helpBt, 1, 4);
	}



	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage ApplicationGUI) {


		BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #012C40");//cadetblue
		
		//log in
		//homepage
		borderPane.setCenter(loginPane);

		//////////////////////////////////////////////////////////////////////////////////////////////////
		//button actions
		final homePage homePage = new homePage();
		final Stage APP_GUI = ApplicationGUI;
		loginBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					homePage.start(APP_GUI);
				} catch (Exception e1) {
					homePage.start(APP_GUI);
				} 
			}
		});
		
		
		Scene scene = new Scene(borderPane, 800,500);
		scene.setFill(Color.BISQUE);
		ApplicationGUI.setTitle("RevYou"); // Set the stage title
		ApplicationGUI.setScene(scene); // Place the scene in the stage
		ApplicationGUI.show(); // Display the stage
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}




}