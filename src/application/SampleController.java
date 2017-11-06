package application;







import java.io.File;

import java.io.IOException;

import java.net.URL;

import java.nio.file.Files;

import java.nio.file.Paths;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.PlaylistController.LISTLIST;
import javafx.beans.InvalidationListener;

import javafx.beans.binding.Bindings;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.application.Platform;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.ColorPicker;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Background;

import javafx.scene.layout.BackgroundFill;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.CornerRadii;

import javafx.scene.layout.HBox;

import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;

import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;

import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javafx.stage.FileChooser;

import javafx.stage.Stage;

import javafx.stage.StageStyle;

import javafx.fxml.Initializable;

import javafx.geometry.Insets;









public class SampleController implements Initializable {



	// global variable to open a path of a file



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

    private ColorPicker colorChooser;

	

	@FXML

	private VBox vBox ;

	

	@FXML

	private HBox hBox ;

	

	

	@FXML

	private StackPane stack ;

	

	@FXML

	private BorderPane root ;



	@FXML

	private Button openFile ;

	

	@FXML

	private Label ConversionWaitLabel;

	

	@FXML

	private AnchorPane ConversionPopUp;

@FXML
private MenuItem playList; 



	@FXML



	private void handleButtonAction(ActionEvent event) {



		FileChooser OpenFile = new FileChooser();



		// to select kind of file we want to open



		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select the media file to play", "*.mp4", "*.mkv", "*.flv", "*.3gp", "*.avi", "*.mp3", "*.wmv");



		OpenFile.getExtensionFilters().add(filter);



		File of = OpenFile.showOpenDialog(null);



		filePath = of.toURI().toString();



		
		PlayMediaFile(filePath);





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



	private void handleJumpSpecific(ActionEvent event) {



		//Need to update this to do something

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

		

		//Convert the file and output the converted file to the desktop

		ConvertFile2MP4(ipfilePath);

		

	}



	@FXML

	private void handleOpenNetVideo(ActionEvent event) {

		URLinput.display();

	}



	@FXML



	private void handleSpeed(ActionEvent event) {







	}







	@FXML



	private void handlePlayList(ActionEvent event) throws IOException {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Playlist.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Playlist");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception e) {
			System.out.println("Error loading Playlist Menu");
		}
	}
		static ObservableList<String> thelist = FXCollections.observableArrayList();
		ArrayList<String> THELIST;


	@FXML
	private void handleplayplayList(ActionEvent event) throws IOException {

		//String fileName = "Playlist.txt";
		thelist = LISTLIST.listItems;
		//for(int i =0 ; i < thelist.size(); i++)
	     //{
			PlayMediaFile(thelist.get(0));   
	     //}
		//System.out.println(thelist.get(0));
		

	            }

	@FXML



	private void handleBC(ActionEvent event) throws IOException {




		//BackgroundColor.BackgroudColor();
		//Parent root1 = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Paint fill = colorChooser.getValue();
		BackgroundFill backgroundFill = new BackgroundFill(fill,CornerRadii.EMPTY,Insets.EMPTY);

		Background background = new Background(backgroundFill);

		stack.setBackground(background);

		root.getChildren().add(colorChooser);
		colorChooser.resizeRelocate(200, 10, 140, 30);
	//	Scene scene = new Scene(root,140,30);
		//colorChooser.getScene();
	//root.getChildren().remove(colorChooser);
		
		

			

		// write the function here (Matthew)



	}







	@FXML



	private void handleFullScreen(ActionEvent event) {



		// write the function here (Faisal)



		 Stage stage = (Stage) volSlider.getScene().getWindow();



		 if(stage.isFullScreen()) {



			 stage.setFullScreen(false);



			 DoubleProperty w = mv.fitWidthProperty();



			 DoubleProperty h = mv.fitHeightProperty();



			 w.bind(Bindings.selectDouble(mv.sceneProperty(), "w"));



			 h.bind(Bindings.selectDouble(mv.sceneProperty(), "h"));



			 



		 }else {



			 stage.setFullScreen(true);



	         DoubleProperty width = mv.fitWidthProperty();



	         DoubleProperty height = mv.fitHeightProperty();



	         width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));



	         height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));



		 }



	}







	@FXML



	private void handleBrightness(ActionEvent event) {



		// HODA



	}







	@FXML



	private void handlBackgroundColor(ActionEvent event) {

	

		Paint fill = colorChooser.getValue();
		//colorChooser.getCustomColors().addAll();
		
		BackgroundFill backgroundFill = new BackgroundFill(fill,CornerRadii.EMPTY,Insets.EMPTY);

		Background background = new Background(backgroundFill);

		stack.setBackground(background);

		root.getChildren().add(colorChooser);

	
		

		

	}





	







	@FXML



	private void handleHelp(ActionEvent event) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelpMenu.fxml"));

			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();

			stage.setTitle("Help Menu");

			stage.setScene(new Scene(root1));

			stage.show();

		}catch(Exception e) {

			System.out.println("Error loading Help Menu");

		}





	}







	@FXML



	private void handlAbout(ActionEvent event) {



		// HODA

		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutMenu.fxml"));

			Parent root2 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();

			stage.setTitle("About Menu");

			stage.setScene(new Scene(root2));

			stage.show();

		}catch(Exception e) {

			System.out.println("Error loading About Menu");

		}



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

		

		//Open new pop-up dialog box to show user that the conversion is occurring

		try {

			FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("ConversionPopUp.fxml"));

			Parent root2 = (Parent) fxmlLoader1.load();

			Stage stage1 = new Stage();

			stage1.setTitle("Conversion in Progress");

			stage1.setScene(new Scene(root2));

			stage1.show();

			

		}catch(Exception e) {

			System.out.println("Error opening popup");

		}

		

		//If there is already a converted file on the desktop, delete it first to ensure the system doesnt lock up



		try {



			Files.deleteIfExists(Paths.get(System.getProperty("user.home")+"\\Desktop\\convertedfile.mp4"));



		} catch (IOException e) {



			e.printStackTrace();



		}

		

		//Convert the file

		try {



			mp4Converter.convert(ipfilePath, opfilePath);



		} catch (IOException | InterruptedException e) {



			e.printStackTrace();



		}



        

		return opfilePath;



	}

	public void PlayMediaFile(String filePath) {
	
	//Check to see if the file is an mp4 or mp3 and if not, convert the file first



	if(filePath.endsWith(".mp4") || filePath.endsWith(".mp3")) {



		//Play the file



		PlayFile(filePath);



	}else {



		//Convert the file to MP4 and process the file path to the correct format for the PlayFile function



		String convfilepath = ConvertFile2MP4(filePath);

		

		convfilepath = convfilepath.replace("\\", "/");



		convfilepath = "file:/" + convfilepath;



		//Play the file



		PlayFile(convfilepath);



	}
	}}




