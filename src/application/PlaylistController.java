package application;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class PlaylistController implements Initializable {
	public String filePath;
	
  @FXML 
  private Button BtnAdd;
  
  @FXML 
  private Button BtnExit;
  
  @FXML
  private Button BtnDelete;
  
  @FXML 
  private HBox HBox4Btns; 

  @FXML
  private Label LblAddText; 

  @FXML 
  private ListView<String> listBoxMain;

  @FXML 
  private Label TitleLbl; 

  @FXML 
  private VBox VBoxMain;

  @FXML
  private TextField txtAddItem; 
  
  public static class LISTLIST {
  static ObservableList<String> listItems = FXCollections.observableArrayList();
  }
  public static ObservableList<String> main(String [] args) {

      // The name of the file to open.
      String fileName = "Playlist.txt";

      // This will reference one line at a time
      String line = null;
      
      try {
          // FileReader reads text files in the default encoding.
          FileReader fileReader = 
              new FileReader(fileName);

          // Always wrap FileReader in BufferedReader.
          BufferedReader bufferedReader = 
              new BufferedReader(fileReader);

          while((line = bufferedReader.readLine()) != null) {
              System.out.println(line);
              LISTLIST.listItems.add(line);
             

          }   
          // Always close files.
          bufferedReader.close();         
      }
      catch(FileNotFoundException ex) {
          System.out.println(
              "Unable to open file '" + 
              fileName + "'");                
      }
      catch(IOException ex) {
          System.out.println(
              "Error reading file '" 
              + fileName + "'");                  
          // Or we could just do this: 
          // ex.printStackTrace();
      }return LISTLIST.listItems;
	
  }
        
  
  // Add event handlers                      
  @FXML
  private void addAction(ActionEvent action){
	  								
    FileChooser OpenFile=new FileChooser();
	//to select kind of file we want to open 
	FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select the file .mp4 file","*.mp4");
	OpenFile.getExtensionFilters().add(filter);
	File of=OpenFile.showOpenDialog(null);
	filePath=of.toURI().toString();
	 
	

	//add files to an array
	if(filePath != null)
	{
		LISTLIST.listItems.add(filePath);
	//filenames = filePath;
	

	}
    
    
    
  }
  
  
  @FXML
  private void deleteAction(ActionEvent action){
    int selectedItem = listBoxMain.getSelectionModel().getSelectedIndex();
    LISTLIST.listItems.remove(selectedItem);
  }
  
  @FXML
  private void exitAction(ActionEvent action){
	  try(  PrintWriter out = new PrintWriter( "Playlist.txt" )  ){
		    out.println( LISTLIST.listItems );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  
	  
	  
	  Stage stage = (Stage) BtnExit.getScene().getWindow();
	    // do what you have to do
	    stage.close();
  }
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    listBoxMain.setItems(LISTLIST.listItems);
    
    
    

    
       
    
  }  

}
