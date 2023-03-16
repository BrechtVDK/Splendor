package main;

import domein.DomeinController;
import gui.WelkomScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application {


	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new WelkomScherm(new DomeinController()));
		// Brecht: verplaatst naar SpelerRegistratieScherm
		// scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
		primaryStage.setTitle("Splendor");
		primaryStage.setScene(scene);
		primaryStage.show();
		// fullscreen
		primaryStage.setMaximized(true);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
