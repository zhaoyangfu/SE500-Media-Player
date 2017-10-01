package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;

public class SampleController implements Initializable {
	// global varible to open a path of a file 
	private String filePath;
	//media paler 
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
		
		//to create the media player 
		if(filePath != null)
		{
		Media media =new Media(filePath);
		mp=new MediaPlayer(media);
		mv.setMediaPlayer(mp);
		DoubleProperty w=mv.fitWidthProperty();
		DoubleProperty h=mv.fitHeightProperty();
		w.bind(Bindings.selectDouble(mv.sceneProperty(), "w"));
		h.bind(Bindings.selectDouble(mv.sceneProperty(), "h"));
		mp.play();
		}
		
		
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
}
