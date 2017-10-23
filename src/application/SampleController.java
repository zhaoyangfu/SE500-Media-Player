package application;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;

import com.sun.javafx.tk.Toolkit;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.beans.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.beans.property.adapter.*;

public class SampleController implements Initializable {
	// global varible to open a path of a file
	private String filePath;
	// media paler
	private MediaPlayer mp;
	@FXML
	private MediaView mv;
	//create text field for current time
	@FXML
	private Label text1;
	//create text field for total duration
	@FXML
	private Label text2;
	// create object for the slider
	@FXML
	private Slider volSlider;
	@FXML
	private Slider timeSlider;
	


	@FXML
	private void handleButtonAction(ActionEvent event) {
		FileChooser OpenFile = new FileChooser();
		// to select kind of file we want to open
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select the media file to play", "*.mp4", "*.mkv", "*.flv", "*.3gp", "*.avi", "*.mp3", "*.wmv");
		OpenFile.getExtensionFilters().add(filter);
		File of = OpenFile.showOpenDialog(null);
		filePath = of.toURI().toString();
		
		//Check to see if the file is an mp4 or mp3 and if not, convert the file first
		if(filePath.endsWith(".mp4") || filePath.endsWith(".mp3")) {
			//Play the file
			PlayFile(filePath);
		}else {
			//If there is already a converted file on the desktop, delete it first to ensure the system doesnt lock up
			try {
				Files.deleteIfExists(Paths.get(System.getProperty("user.home")+"\\Desktop\\convertedfile.mp4"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Convert the file to MP4 and process the file path to the correct format for the PlayFile function
			String convfilepath = ConvertFile2MP4(filePath);
			convfilepath = convfilepath.replace("\\", "/");
			convfilepath = "file:/" + convfilepath;
			//Play the file
			PlayFile(convfilepath);
		}
	}	
	@FXML
	private void handlePause(ActionEvent event) {
		mp.pause();
	}

	@FXML
	private void handleStop(ActionEvent event) {
		mp.stop();
	}

	@FXML
	private void handlePlay(ActionEvent event) {

		mp.play();
	}

	@FXML
	private void handleBack(ActionEvent event) {

		mp.setRate(.75);
	}

	@FXML
	private void handleForward(ActionEvent event) {
		mp.setRate(1.5);
	}

	@FXML
	private void handleMute(ActionEvent event) {
		if (mp.isMute() == false) {
			mp.setMute(true);
		} else {
			mp.setMute(false);
		}

	}

	@FXML
	private void handleExit(ActionEvent event) {

		System.exit(0);
		}

	@FXML
	private void handleConvertFile(ActionEvent event) {
		// FUNCTION TO CONVERT FILES TO MP4
		FileChooser ConvertFile = new FileChooser();
		// to select the file we want to convert

		File of = ConvertFile.showOpenDialog(null);
		//filepath of file to be converted
		String ipfilePath = of.toURI().toString();
		
		
		
		
	}

	@FXML
	private void handleSpeed(ActionEvent event) {

	}

	@FXML
	private void handlePlayList(ActionEvent event) {

		// write the function here (Matthew)
	}

	@FXML
	private void handleFullScreen(ActionEvent event) {
		// wwrite the function here (Faisal)
		 Stage stage = (Stage) volSlider.getScene().getWindow();
		 if(stage.isFullScreen()) {
			 stage.setFullScreen(false);
		 }else {
			 stage.setFullScreen(true);
		 }
	}

	@FXML
	private void handleBrightness(ActionEvent event) {
		// HODA
	}

	@FXML
	private void handlBackgroundColor(ActionEvent event) {
		// HODA

	}

	@FXML
	private void handleHelp(ActionEvent event) {

	}

	@FXML
	private void handlAbout(ActionEvent event) {
		// HODA

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}
	
	public void PlayFile(String filePath) {
		// to create the media player
		if (filePath != null) {
			Media media = new Media(filePath);
			mp = new MediaPlayer(media);
			mv.setMediaPlayer(mp);
			mv.autosize();
			DoubleProperty w = mv.fitWidthProperty();
			DoubleProperty h = mv.fitHeightProperty();
			w.bind(Bindings.selectDouble(mv.sceneProperty(), "w"));
			h.bind(Bindings.selectDouble(mv.sceneProperty(), "h"));

			volSlider.setValue(mp.getVolume() * 100);
			volSlider.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(javafx.beans.Observable observable) {
					mp.setVolume(volSlider.getValue() / 100);
				}
			});
					
			// time slider function (Zhaoyang added)		
			mp.currentTimeProperty().addListener(new InvalidationListener() {
				public void invalidated(javafx.beans.Observable observable) {
					Platform.runLater(new Runnable() {
						public void run() {
							double currentTime = mp.getCurrentTime().toSeconds();
							double totalTime = mp.getTotalDuration().toSeconds();
							DecimalFormat df = new DecimalFormat("#.##");
							timeSlider.setValue(mp.getCurrentTime().toMillis() / mp.getTotalDuration().toMillis() * 100);
							text1.setText(df.format(currentTime));
							text2.setText("/ " + df.format(totalTime));
						}
					});
				}
			});
					
			timeSlider.valueProperty().addListener(new InvalidationListener() {
				public void invalidated(javafx.beans.Observable observable) {
					if (timeSlider.isPressed()) {
						mp.seek(mp.getMedia().getDuration().multiply(timeSlider.getValue() / 100));
					}
				}
			});
				mp.play();
		}
	}
	
	public String ConvertFile2MP4(String ipfilePath) {
		//convert ipfilePath string to correct format to use in conversion
		ipfilePath = ipfilePath.replaceAll("file:/", "");
		
		//set filepath of converted file to the current directory
		String opfilePath = System.getProperty("user.home")+"\\Desktop\\convertedfile.mp4";
				
		//set filepath of the ffmpeg and ffprobe executables (must be installed in the run directory of the application)
		String ffmpegdir = System.getProperty("user.dir")+"\\ffmpeg.exe";
		String ffprobedir = System.getProperty("user.dir")+"\\ffprobe.exe";
			
		//use the MP4Converter class to convert the file
		MP4Converter mp4Converter = new MP4Converter(ffmpegdir, ffprobedir);
		try {
			mp4Converter.convert(ipfilePath, opfilePath);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return opfilePath;
	}

}

