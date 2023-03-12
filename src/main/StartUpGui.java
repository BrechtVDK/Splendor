package main;

import domein.DomeinController;
import gui.Hoofdscherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application {

	// om dit te kunne runnen moet in de spelerRepo nog iets uit commentaar gehaald
	// worden;

	@Override
	public void start(Stage primaryStage) {
		DomeinController dc = new DomeinController();

		Scene scene = new Scene(new Hoofdscherm(dc));

		scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
		primaryStage.setTitle("Splendor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
