package application;

import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;

import com.sun.javafx.tk.Toolkit;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.beans.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.beans.property.adapter.*;


public class SampleController implements Initializable {
	// global variable to open a path of a file 
	private String filePath;
	//media paler 
	private double[] list = {};
	private MediaPlayer mp;
	@FXML
	private MediaView mv;

	
	
	@FXML 
	private void handleButtonAction(ActionEvent event){
		FileChooser OpenFile=new FileChooser();
		//to select kind of file we want to open 
		FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select the file .mp4 file","*.mp4");
		OpenFile.getExtensionFilters().add(filter);
		File of=OpenFile.showOpenDialog(null);
		filePath=of.toURI().toString();
		Dimension screenSize = new Dimension(); 
		

		//add files to an array
		if(filePath != null)
		{
		
			
			
		
		}
	}

	@FXML
		private void handleExit(ActionEvent event){
			
System.exit(0);
		}
		
		
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
}