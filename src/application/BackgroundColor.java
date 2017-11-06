
package application;


import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public abstract class BackgroundColor extends Application {
	@FXML

	private StackPane stack ;

	

	
	public static void BackgroudColor() {
		

Stage primaryStage= new Stage();
StackPane root = new StackPane();
ColorPicker colorPicker = new ColorPicker();
colorPicker.setOnAction(new EventHandler(){

	@Override
public void handle(Event event) {
Paint fill = colorPicker.getValue();
BackgroundFill backgroundFill = 
new BackgroundFill(fill,CornerRadii.EMPTY,Insets.EMPTY);
Background background = new Background(backgroundFill);
root.setBackground(background);

}
});
root.getChildren().add(colorPicker);
Scene scene = new Scene(root, 100, 50);
primaryStage.setTitle("Select Background Color");
primaryStage.setScene(scene);
primaryStage.show();
}}

