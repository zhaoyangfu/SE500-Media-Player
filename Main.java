package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Main extends Application {
	Player player;
	public void start(Stage primaryStage) {
		
		MenuItem open = new MenuItem("open");
		Menu file = new Menu("File");
		MenuBar menu = new MenuBar();
		
		file.getItems().add(open);
		menu.getMenus().add(file);
		
		player = new Player("file:///C:/Users/Jackie/Downloads/toystory.mp4");
		player.setTop(menu);
		Scene scene = new Scene(player, 640, 380);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
