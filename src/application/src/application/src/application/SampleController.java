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
	// global varible to open a path of a file 
	private String filePath;
	//media paler 
	private MediaPlayer mp;
	@FXML
	private MediaView mv;
	
	//create object for the slider 
	@FXML 
	private  Slider volSlider;
	@FXML 
	private Slider timeSlider;
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
		mv.autosize();
		DoubleProperty w=mv.fitWidthProperty();
		DoubleProperty h=mv.fitHeightProperty();
		w.bind(Bindings.selectDouble(mv.sceneProperty(), "w"));
		h.bind(Bindings.selectDouble(mv.sceneProperty(), "h"));
		
		volSlider.setValue(mp.getVolume()* 100);
		volSlider.valueProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(javafx.beans.Observable observable) {
				mp.setVolume(volSlider.getValue() / 100);
					}});
		
		
/*mp.currentTimeProperty().addListener(new ChangeListener<Duration>()
	{
	@Overrride
	public void changed(ObservableValue<? extends Duration> observable, Duration oldv, Duration newV) {
		timeSlider.setValue(newValue.toMilly());
	}
});*/ //(ZHAOYANG)
		mp.play();
		}
	}
	
	@FXML
	private void handlePause(ActionEvent event){
		mp.pause();
		}
		@FXML
		private void handleStop(ActionEvent event){
			mp.stop();
			}
		@FXML
		private void handlePlay(ActionEvent event){
			
			mp.play();
			}
		@FXML
		private void handleBack(ActionEvent event){
			
		mp.setRate(.75);
			}
		@FXML
		private void handleForward(ActionEvent event){
			mp.setRate(1.5);
			}
		@FXML
		private void handleMute(ActionEvent event){
			mp.setMute(true);
	
			
			}
		
		@FXML
		private void handleExit(ActionEvent event){
			
System.exit(0);
		}
		
		@FXML
		private void handleConvertFile(ActionEvent event){
			//write the function here (Adam)
			}
			@FXML
			private void handleSpeed(ActionEvent event){
				
				}
			@FXML
			private void handlePlayList(ActionEvent event){
				
				//write the function here (Matthew)
				}
			@FXML
			private void handleFullScreen(ActionEvent event){
				// wwrite the function here (Faisal) 
			
				}
			@FXML
			private void handleBrightness(ActionEvent event){
				//HODA
				}
			@FXML
			private void handlBackgroundColor(ActionEvent event){
				//HODA
		
				
				}
			@FXML
			private void handleHelp(ActionEvent event){
				
				}
			@FXML
			private void handlAbout(ActionEvent event){
				//HODA
		
				
				}
			
		
		
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
}