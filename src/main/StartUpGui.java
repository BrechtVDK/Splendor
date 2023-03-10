package main;

import domein.DomeinController;
import gui.Hoofdscherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application {

	@Override
	public void start(Stage primaryStage) {
		DomeinController dc = new DomeinController();

		Scene scene = new Scene(new Hoofdscherm(dc));

		primaryStage.setTitle("Splendor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
