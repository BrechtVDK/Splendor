package main;

import domein.DomeinController;
import gui.WelkomScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application {

	// om dit te kunne runnen moet in de spelerRepo nog iets uit commentaar gehaald
	// worden;

	@Override
	public void start(Stage primaryStage) {
		// DomeinController dc = new DomeinController();

		// Scene scene = new Scene(new Hoofdscherm(dc));

		Scene scene = new Scene(new WelkomScherm(new DomeinController()));
		// Brecht: verplaatst naar SpelerRegistratieScherm
		// scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
		primaryStage.setTitle("Splendor");
		primaryStage.setScene(scene);
		primaryStage.show();
		// Brecht: scherm werd niet mooi geopend bij mij, onderstaande zorgt voor
		// fullscreen
		primaryStage.setMaximized(true);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
