package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	Player player;
	FileChooser fileChooser;
	
	public void start(final Stage primaryStage) {
		
		MenuItem open = new MenuItem("open");
		Menu file = new Menu("File");
		MenuBar menu = new MenuBar();
		
		file.getItems().add(open);
		menu.getMenus().add(file);
		
		fileChooser = new FileChooser();
		
		open.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				player.player.pause();
				player.bar.playButton.setText(">");
				File filec = fileChooser.showOpenDialog(primaryStage);
				if(filec != null) {
					try {
						player = new Player(filec.toURI().toURL().toExternalForm());
						player.setTop(menu);
						Scene scene = new Scene(player, 640, 480);
						primaryStage.setScene(scene);
						player.player.setOnReady(new Runnable() {
							@Override
							public void run() {
								primaryStage.setWidth(player.player.getMedia().getWidth()+15);
								primaryStage.setHeight(player.player.getMedia().getHeight()+100);
								primaryStage.setMinWidth(player.player.getMedia().getWidth());
								primaryStage.setMinHeight(player.player.getMedia().getHeight());
							}
						});
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		player = new Player("file:///C:/Users/Borghs/Desktop/big_buck_bunny.mp4");
		player.setTop(menu);
		Scene scene = new Scene(player, 640, 380);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
