package application;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;

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
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select the file .mp4 file", "*.mp4");
		OpenFile.getExtensionFilters().add(filter);
		File of = OpenFile.showOpenDialog(null);
		filePath = of.toURI().toString();

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

	@FXML
	private void handlePause(ActionEvent event) {
		mp.pause();
	}

	@FXML
	private void handleStop(ActionEvent event) {
		mp.stop();
	}

	//Zhaoyang modified:
	@FXML
	private void handlePlay(ActionEvent event) {
		mp.currentTimeProperty().addListener(new InvalidationListener() {
			public void invalidated(javafx.beans.Observable observable) {
				Platform.runLater(new Runnable() {
					public void run() {
						timeSlider.setValue(mp.getCurrentTime().toMillis() / mp.getTotalDuration().toMillis() * 100);
					}
				});
			}
		});
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
		// write the function here (Adam)
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

}

