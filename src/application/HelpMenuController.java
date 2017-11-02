package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HelpMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextArea TextArea;
    
    @FXML 
    private Button closeButton;

    @FXML
    private void handlePlayMediaFilesLink(ActionEvent event) {
    	TextArea.setText("To Play a media file in the JavaCoffee Media Player:" + '\n' + '\n' + "1. Select File->Open" + '\n' + "2. Select the desired File from the File Chooser dialog box" + '\n' + "3. Press 'Open'" + '\n' + "4. Your file will play!" + '\n' + '\n' + "Note: JavaCoffee only supports .mp4 video and .mp3 audio natively. If any other file type is selected, JavaCoffee will first convert the selected file to .mp4 which may take some time");
    }
    
    @FXML
    private void handleConvertingMediaFilesLink(ActionEvent event) {
    	TextArea.setText("To Convert a media file to .mp4 format using JavaCoffee Media Player:" + '\n' + '\n' + "1. Select File->Convert" + '\n' + "2. Select the desired File from the File Chooser dialog box" + '\n' + "3. Press 'Open'" + '\n' + "4. Your file will be converted and output to the desktop with filename 'convertedfile'" + '\n' + '\n' + "Note: Conversion of files uses the ffmpeg toolset. Conversion of large files will take a significant amount of time");
    }
    
    @FXML
    private void handleOpenNetworkMediaLink(ActionEvent event) {
    	TextArea.setText("To Open a video from online sources:"+ '\n' + '\n' + "1. Select File->Open Network Video" + '\n' +"2. Enter or paste the video URL in the 'Please enter a network URL' input field"+ '\n' +"3. Press 'Submit' Button"+ '\n' + "4. Depending upon your internet bandwidth, the Media Player will begin playing the online video right from the local player");
    }
    
    @FXML
    private void handlePlaybackMenuLink(ActionEvent event) {
    	TextArea.setText("The Playback menu has the following options:"  + '\n'  + '\n' + "1. Speed. When this item is selected... ???"  + '\n' + '\n' + "2. Increase Speed. When this item is selected the currently playing media file has its playback speed increased to 1.5X normal. Repeatedly clicking this option does not further increase the playback speed." + '\n'  + '\n' + "3. Decrease Speed. When this item is selected the currently playing media file has its playback speed decreased to 0.75X normal. Repeatedly clicking this option does not further decrease the playback speed." + '\n'  + '\n' + "4. Jump to Specific Time. Selecting this option opens a dialog box prompting the user to enter a time. When a time is entered, the media file will jump to the selected time in the playback. Note that if a time is entered outside of the limits of the media file, the time will default to 0:00" + '\n'  + '\n' + "5. Play. Selecting this option will play the currently opened file. If no file is opened nothing will occur." + '\n' + '\n' + "6. Stop. Selecting this option will stop playback of the currently opened file and will return the time slider to 0:00. Selecting this option when no file is open will do nothing.");
    }
    
    @FXML
    private void handleViewMenuLink(ActionEvent event) {
    	TextArea.setText("The View menu has the following options:" + '\n' +  '\n' + "1. Playlist. Selected the Playlist option will open a new window allowing the user to add multiple media files to a playlist. When play is then selected, the playlist will play all of the media files in the list in order" + '\n'  + '\n' + "2. Fullscreen. Selecting this option will make the JavaCoffee media player fullscreen. Note that this will also stretch the media file currently playing to fill the space." + '\n'  + '\n' + "3. Brightness. Selecting this option will change the brightness of the media file currently open. Selecting this option when no file is open will do???" + '\n' + '\n' + "4. Background color. Selecting the drop down menu will allow the user to select the background color for JavaCoffee media player.");
    }
    
    @FXML
    private void handleHelpMenuLink(ActionEvent event) {
    	TextArea.setText("The Help menu has the following options:" + '\n' + '\n' + "1. Help. Selecting the Help option will open the help menu. But you already know this, otherwise how did you get here?" + '\n'  + '\n' + "2. About. Selecting the about option will display the current JavaCoffee media player version number along with the names of the genius creators.");
    }
    
    @FXML
    private void handleCloseButton(ActionEvent event) {
    	// get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
       
    @FXML
    void initialize() {

    }
}