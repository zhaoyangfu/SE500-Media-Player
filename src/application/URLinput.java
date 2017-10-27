package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.stage.*;

public class URLinput {

	public static void display() {
		Stage popupwindow1 = new Stage();
		Stage popupwindow2 = new Stage();
		popupwindow1.setTitle("Open Network Stream");
		popupwindow1.setTitle("Network Stream");

		GridPane rootNode = new GridPane();
		rootNode.setPadding(new Insets(50));
		rootNode.setHgap(5);
		rootNode.setVgap(5);
		rootNode.setAlignment(Pos.CENTER_LEFT);

		Scene myScene = new Scene(rootNode, 500, 200);
		rootNode.add(new Label("Please input a network URL:"), 0, 0);

		TextField url = new TextField();
		rootNode.add(url, 1, 0);

		Button aButton = new Button("Submit");
		rootNode.add(aButton, 1, 3);
		aButton.setOnAction(e -> {
			popupwindow1.close();
			String link = url.getText();
			WebView webview = new WebView();
			webview.getEngine().load(link);
			webview.setPrefSize(640, 390);
			popupwindow2.setScene(new Scene(webview));
			popupwindow2.show();
		});

		popupwindow1.setScene(myScene);
		popupwindow1.show();

}

}
