
package com.revyouviddb;

//Fx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.adapter.JavaBeanBooleanPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

//Persistance and other  imports
import java.beans.Beans;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Persistence;
import java.util.List;
import javax.persistence.RollbackException;
import org.jdesktop.observablecollections.ObservableCollections;



/**
 * Project: RevYou --  
 * Sub-Project: DBControls For Video --  
 * Class: VidDBManager --  
 * Description: Graphical access for database management using JavaFX as main graphics provider
 * @author Otto O. Legon
 * @version 2.0
 * 
 */
public class VidDBManager extends Application {
    
    //Persistance Attributes
    /**
     * Represents the entity manager for the relational database table
     */
    private EntityManager entityManager;
    /**
     * Represents the current query
     */
    private Query query;
    /**
     * Contains the rows/entities in relational database table, which are VideoDb objects
     */
    private List<VideoDb> list;
    
    //FX Attributes
        //Layout vars
    /**
     * The scene for the admin gui
     */
    private Scene primaryScene;
    /**
     * The root for the primary scene
     */
    private BorderPane primaryPane;
        //Table vars
    /**
     * Observable list of entities for table to display
     */
    ObservableList<VideoDb> entityList;
    /**
     * The gui table representing the table in relational database
     */
    @FXML private TableView<VideoDb> masterTable;
    /**
     * The primary key/ID value for the next row/entity/video to be added
     */
    private short nextIDValue;
        //Column vars
    /**
     * Binded column for ID 
     */
    private TableColumn<VideoDb, Integer> idField;
    /**
     * Binded column for owner
     */
    private TableColumn<VideoDb, String> ownerField;
    /**
     * Binded column for URL
     */
    private TableColumn<VideoDb, String> videoURLField;
    /**
     * Binded column for visibility
     */
    private TableColumn<VideoDb, Boolean> visibilityField;
    /**
     * Binded column for description
     */
    private TableColumn<VideoDb, String> descriptionField;
    /**
     * Binded column for video name
     */
    private TableColumn<VideoDb, String> videoNameField;
    /**
     * Binded column for tags
     */
    private TableColumn<VideoDb, String> tagsField;
    /**
     * Binded column for comments
     */
    private TableColumn<VideoDb, String> commentsURLField;
        //Button vars
    /**
     * Button to edit selected row
     */
    /**
     * Button to add new row
     */
    /**
     * Button to delete selected row
     */
    /**
     * Button to refresh gui and entity list
     */
    /**
     * Button to save changes done in program to relational database 
     */
    private Button 
            editButton,
            newButton, 
            deleteButton, 
            refreshButton, 
            saveButton;
    
    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    // Application start. Sets up and displays application
    public void start(Stage primaryStage) {
        setup();
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
    
    /**
     * Constructor which calls setup
     */
    public VidDBManager(){
    	setup();
    }
    
    //Application setup. Defines aplication attributes, calls main GUI construction method
    /**
     * Connects to database and defines fields
     */
    private void setup(){
        //Persistance setup
        entityManager = Beans.isDesignTime() ? null :
                Persistence.createEntityManagerFactory("dd0cstm5kg94aePU").createEntityManager(); 
        query = Beans.isDesignTime() ? null : 
                entityManager.createQuery("SELECT v FROM VideoDb v");
        list = Beans.isDesignTime() ? Collections.emptyList() : 
                ObservableCollections.observableList(query.getResultList());
        
        //FX setup
            //Layout vars
        primaryPane = new BorderPane();
        primaryScene = new Scene(primaryPane);
            //Table vars
        masterTable = new TableView();
        entityList = FXCollections.observableArrayList(list);
        masterTable.setItems(entityList);
            //Column vars
        idField = new TableColumn("ID");
            idField.setCellValueFactory(new Callback<CellDataFeatures<VideoDb, Integer>, ObservableValue<Integer>>() {
				public ObservableValue<Integer> call(CellDataFeatures<VideoDb, Integer> cellData) {
				    try {
				        return JavaBeanObjectPropertyBuilder.create()
				            .bean(cellData.getValue())
				            .name("id")
				            .build();
				    } catch (NoSuchMethodException exc) {
				        throw new RuntimeException(exc);
				    }
				}
			}); 
        ownerField = new TableColumn("OWNER");
            ownerField.setCellValueFactory(new Callback<CellDataFeatures<VideoDb, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<VideoDb, String> cellData) {
				    try {
				        return JavaBeanStringPropertyBuilder.create()
				            .bean(cellData.getValue())
				            .name("owner")
				            .build();
				    } catch (NoSuchMethodException exc) {
				        throw new RuntimeException(exc);
				    }
				}
			});
        videoURLField = new TableColumn("URL");
            videoURLField.setCellValueFactory(new Callback<CellDataFeatures<VideoDb, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<VideoDb, String> cellData) {
				    try {
				        return JavaBeanStringPropertyBuilder.create()
				            .bean(cellData.getValue())
				            .name("videoUrl")
				            .build();
				    } catch (NoSuchMethodException exc) {
				        throw new RuntimeException(exc);
				    }
				}
			});
        visibilityField = new TableColumn("VISIBILITY");
            visibilityField.setCellValueFactory(new Callback<CellDataFeatures<VideoDb, Boolean>, ObservableValue<Boolean>>() {
				public ObservableValue<Boolean> call(CellDataFeatures<VideoDb, Boolean> cellData) {
				    try {
				        return JavaBeanBooleanPropertyBuilder.create()
				            .bean(cellData.getValue())
				            .name("visibility")
				            .build();
				    } catch (NoSuchMethodException exc) {
				        throw new RuntimeException(exc);
				    }
				}
			});
        descriptionField = new TableColumn("DESCRIPTION");
            descriptionField.setCellValueFactory(new Callback<CellDataFeatures<VideoDb, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<VideoDb, String> cellData) {
				    try {
				        return JavaBeanStringPropertyBuilder.create()
				            .bean(cellData.getValue())
				            .name("description")
				            .build();
				    } catch (NoSuchMethodException exc) {
				        throw new RuntimeException(exc);
				    }
				}
			});
        videoNameField = new TableColumn("VIDEONAME");
            videoNameField.setCellValueFactory(new Callback<CellDataFeatures<VideoDb, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<VideoDb, String> cellData) {
				    try {
				        return JavaBeanStringPropertyBuilder.create()
				            .bean(cellData.getValue())
				            .name("videoName")
				            .build();
				    } catch (NoSuchMethodException exc) {
				        throw new RuntimeException(exc);
				    }
				}
			});      
        tagsField = new TableColumn("TAGS");
            tagsField.setCellValueFactory(new Callback<CellDataFeatures<VideoDb, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<VideoDb, String> cellData) {
				    try {
				        return JavaBeanStringPropertyBuilder.create()
				            .bean(cellData.getValue())
				            .name("tags")
				            .build();
				    } catch (NoSuchMethodException exc) {
				        throw new RuntimeException(exc);
				    }
				}
			});
        commentsURLField = new TableColumn("COMMENTS");
        	commentsURLField.setCellValueFactory(new Callback<CellDataFeatures<VideoDb, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<VideoDb, String> cellData) {
				    try {
				        return JavaBeanStringPropertyBuilder.create()
				            .bean(cellData.getValue())
				            .name("commentsUrl")
				            .build();
				    } catch (NoSuchMethodException exc) {
				        throw new RuntimeException(exc);
				    }
				}
			});
            //Button vars
        editButton = new Button("Edit");
        newButton = new Button("New");
        deleteButton = new Button("Delete");
        refreshButton = new Button("Refresh");
        saveButton = new Button("Save");
        
        //Button Listeners
        editButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			    int row = masterTable.getSelectionModel().getFocusedIndex();
			    if(row>=0)
			    editRow(row);
			}
		});
        newButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			    newRow();
			}
		});
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			    int row = masterTable.getSelectionModel().getFocusedIndex();
			    if(row>=0)
			    deleteRow(row);
			}
		});
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			    refresh();
			}
		});
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			    save();
			}
		});
        
        //Main GUI Creation
        createGUI();

        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }
    }//End of setup
    
    //**************private aditional setup methods
    /**
     * Updates what the next primary key/ID value should be
     */
    private void setNextIDValue(){
        refresh();
        nextIDValue = 0;
        for (VideoDb v : list){
            if (nextIDValue <= v.getId()){
                nextIDValue = (short)(v.getId()+1);
            }
        }
    }
    
    /**
     * Creates a visible gui for this application
     */
    private void createGUI(){ 
        //Top
        HBox topPane = new HBox();
        topPane.setAlignment(Pos.CENTER);
        Label appTitle = new Label("RevYou Video Database Manager");
        appTitle.setFont(Font.font("PRISTINA",60));
        topPane.getChildren().add(appTitle);
        primaryPane.setTop(topPane);
        
        //Bottom
        HBox bottomPane = new HBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().addAll(editButton,newButton,deleteButton,refreshButton,saveButton);
        primaryPane.setBottom(bottomPane);
        
        //Center
        ScrollPane centerPane = new ScrollPane();
        centerPane.setFitToHeight(true);
        centerPane.setFitToWidth(true);
        centerPane.setContent(masterTable);
        primaryPane.setCenter(centerPane);
        
        //Table components
        masterTable.getColumns().addAll(idField, ownerField, videoURLField, visibilityField, descriptionField, videoNameField, tagsField, commentsURLField);
    }
    
    /**
     * Refreshes table in gui with the values saved in relational database table
     */
    private void resetTable(){
        entityList = FXCollections.observableArrayList(list);
        masterTable.setItems(entityList);     
        masterTable.getSelectionModel().clearSelection();
    }
    //**************end of aditional setup methods
    
    //**************private actions methods to update real database
    /**
     * Called to edit information of a video when an new video is added from another class.
     * Requires the URL to be specified before hand.
     * @param row an integer representing the index of the new row to be created
     * @param url a String representing URL location of video file in cloudinary server
     * 
     */
    private void editRow(int row, String url){
        final VideoDb v = list.get(row);
        
        //Editor GUI Declarations
            //Editor Popup Window and Pane
        Alert rowEditorPopUp = new Alert(AlertType.INFORMATION);
        VBox rowEditorPane = new VBox();
            //Editor Fields
        final TextField owner = new TextField(v.getOwner());
        final CheckBox visibility = new CheckBox();
        visibility.setSelected(v.getVisibility());
        final TextField description = new TextField(v.getDescription());
        final TextField videoName = new TextField(v.getVideoName());
        final TextField tags = new TextField(v.getTags());
        final TextField commentsURL = new TextField(v.getCommentsUrl());
            //Editor Button and Button Listener
        Button restorePrevButton = new Button("Undo Changes");
        restorePrevButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			    owner.setText(v.getOwner());
			    visibility.setSelected(v.getVisibility());
			    description.setText(v.getDescription());
			    videoName.setText(v.getVideoName());
			    tags.setText(v.getTags());
			    commentsURL.setText(v.getCommentsUrl());
			}
		});
        
        //Editor GUI Generation
            //Adding components into Editor Pane
        rowEditorPane.getChildren().addAll(
                new Label("Owner"), owner,
                new Label("Visibility"), visibility, 
                new Label("Description"), description,
                new Label("Video Name"), videoName, 
                new Label("Tags"), tags, 
                new Label("Comments URL"), commentsURL, 
                restorePrevButton);
            //Defining Popup Window properties
        rowEditorPopUp.setTitle("Editor");
        rowEditorPopUp.setHeaderText("Exiting Editor Will Save All Changes");
        rowEditorPopUp.setGraphic(rowEditorPane);
        rowEditorPopUp.initOwner(primaryScene.getWindow());
        rowEditorPopUp.showAndWait();
        
        //Passing and Saving Data
        if(v.getId()==null){ v.setId(nextIDValue);}
        v.setOwner(owner.getText());
        v.setVideoUrl(url);
        v.setVisibility(visibility.isSelected());
        v.setDescription(description.getText());
        v.setVideoName(videoName.getText());
        v.setTags(tags.getText());
        v.setCommentsUrl(commentsURL.getText());
        save();
        
        //Refreshing Main GUI
        deleteRow(row);
        refresh();
        resetTable();
    }
    
    /**
     * Called to enter information of new video or to edit info of existing video
     * @param row an integer representing the index of the new row to be created or the index of the currently selected row
     * 
     */
    private void editRow(int row){
        final VideoDb v = list.get(row);
        
        //Editor GUI Declarations
            //Editor Popup Window and Pane
        Alert rowEditorPopUp = new Alert(AlertType.INFORMATION);
        VBox rowEditorPane = new VBox();
            //Editor Fields
        final TextField owner = new TextField(v.getOwner());
        final TextField videoURL = new TextField(v.getVideoUrl());
        final CheckBox visibility = new CheckBox();
        visibility.setSelected(v.getVisibility());
        final TextField description = new TextField(v.getDescription());
        final TextField videoName = new TextField(v.getVideoName());
        final TextField tags = new TextField(v.getTags());
        final TextField commentsURL = new TextField(v.getCommentsUrl());
            //Editor Button and Button Listener
        Button restorePrevButton = new Button("Undo Changes");
        restorePrevButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			    owner.setText(v.getOwner());
			    videoURL.setText(v.getVideoUrl());
			    visibility.setSelected(v.getVisibility());
			    description.setText(v.getDescription());
			    videoName.setText(v.getVideoName());
			    tags.setText(v.getTags());
			    commentsURL.setText(v.getCommentsUrl());
			}
		});
        
        //Editor GUI Generation
            //Adding components into Editor Pane
        rowEditorPane.getChildren().addAll(
                new Label("Owner"), owner,
                new Label("URL"), videoURL, 
                new Label("Visibility"), visibility, 
                new Label("Description"), description,
                new Label("Video Name"), videoName, 
                new Label("Tags"), tags, 
                new Label("Comments URL"), commentsURL, 
                restorePrevButton);
            //Defining Popup Window properties
        rowEditorPopUp.setTitle("Editor");
        rowEditorPopUp.setHeaderText("Exiting Editor Will Save All Changes");
        rowEditorPopUp.setGraphic(rowEditorPane);
        rowEditorPopUp.initOwner(primaryScene.getWindow());
        rowEditorPopUp.showAndWait();
        
        //Passing and Saving Data
        if(v.getId()==null){ v.setId(nextIDValue);}
        v.setOwner(owner.getText());
        v.setVideoUrl(videoURL.getText());
        v.setVisibility(visibility.isSelected());
        v.setDescription(description.getText());
        v.setVideoName(videoName.getText());
        v.setTags(tags.getText());
        v.setCommentsUrl(commentsURL.getText());
        save();
        
        //Refreshing Main GUI
        deleteRow(row);
        refresh();
        resetTable();
    }
    
    /**
     * Called to add video to database
     */
    private void newRow(){
        setNextIDValue();
        VideoDb v = new VideoDb();
        entityManager.persist(v);
        list.add(v);
        editRow(masterTable.getItems().size());
        resetTable();
    }
    
    /**
     * Deletes selected row/video in application.
     * Does not persist changes into database.
     * @param row an integer represents index of the selected row/video
     * 
     */
    private void deleteRow(int row){
        VideoDb v = list.get(row);
        entityManager.remove(v);
        list.remove(v);
        resetTable();
    }
    
    /**
     * Persists changes in application into database
     */
    private void save(){
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            Alert error = new Alert(AlertType.ERROR);//Visible Error Message
            error.setHeaderText("Do not leave anything null (except tags and comments) and do not have duplicate URL");
            error.showAndWait();
            entityManager.getTransaction().begin();
            List<VideoDb> merged = new ArrayList<VideoDb>(list.size());
            for (VideoDb v : list) {
                merged.add(entityManager.merge(v));
            }
            list.clear();
            list.addAll(merged);
        }
    }
    
    /**
     * Refreshes application gui to reflect database table
     */
    private void refresh(){
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
        resetTable();
    }
    //**************end of action methods
    
    //**************Getters and Setters and public methods
    /**
     * @return list - a List containing video entities in database 
     */
    public List getList(){
    	return list;
    }
    
    /**
     * Adds a video that has been uploaded to cloudinary server to database
     * @param url JavaNET URL representing location of video in cloudinary server
     * 
     */
    public void newVideo(String url){
        setNextIDValue();
        VideoDb v = new VideoDb();
        entityManager.persist(v);
        list.add(v);
        editRow(masterTable.getItems().size(), url);
        resetTable();
    }
    //**************End of Getters and Setters and public methods
    
    //Application launch
    /**
     * Runs application for Admins
     * @param args - Arguments for execution
     * 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
