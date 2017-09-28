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
	FileChooser fileChooser = new FileChooser();

	public void start(final Stage primaryStage) {
		
		MenuItem open = new MenuItem("open");
		Menu file = new Menu("File");
		MenuBar menu = new MenuBar();
		
		file.getItems().add(open);
		menu.getMenus().add(file);
		
		open.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				player.player.pause();
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					try {
						player = new Player(file.toURI().toURL().toExternalForm());
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					Scene scene = new Scene(player, 640, 380);
					primaryStage.setScene(scene);
					player.setTop(menu);
				}

			}
		});

		player = new Player("file:///C:/Users/Jackie/Downloads/test/toystory.mp4");
		player.setTop(menu);
		Scene scene = new Scene(player, 640, 380);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
