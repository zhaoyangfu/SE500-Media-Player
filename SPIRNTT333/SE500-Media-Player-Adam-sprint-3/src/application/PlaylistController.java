package application;

import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


public class PlaylistController implements Initializable {
	public String filePath;
	
  @FXML 
  private Button BtnAdd;
  
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
    
  final ObservableList<String> listItems = FXCollections.observableArrayList();        
  
  // Add event handlers                      //= FXCollections.observableArrayList("Add Items here")
  @FXML
  private void addAction(ActionEvent action){
	  								//listItems.add(txtAddItem.getText());
    FileChooser OpenFile=new FileChooser();
	//to select kind of file we want to open 
	FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select the file .mp4 file","*.mp4");
	OpenFile.getExtensionFilters().add(filter);
	File of=OpenFile.showOpenDialog(null);
	filePath=of.toURI().toString();
	 
	

	//add files to an array
	if(filePath != null)
	{
		listItems.add(filePath);
	//filenames = filePath;
	

	}
    
    
    
  }
  
  
  @FXML
  private void deleteAction(ActionEvent action){
    int selectedItem = listBoxMain.getSelectionModel().getSelectedIndex();
    listItems.remove(selectedItem);
  }
  
  
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    listBoxMain.setItems(listItems);
    
    

    
       
    
  }  
}
