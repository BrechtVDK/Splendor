package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelkomScherm extends VBox {
	private DomeinController dc;

	public WelkomScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		ImageView ivSplendor = new ImageView(new Image(getClass().getResourceAsStream("/images/splendor.jpg")));
		Button btnStart = new Button("Start spel");
		btnStart.setMinWidth(200);

		btnStart.setOnAction(this::startGeklikt);

		this.setSpacing(25);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(ivSplendor, btnStart);
	}

	private void startGeklikt(ActionEvent evt) {
		dc.startNieuwSpel();

		Stage stage = (Stage) this.getScene().getWindow();
		double breedte = this.getScene().getWidth();
		double hoogte = this.getScene().getHeight();
		stage.setTitle("Registratie spelers");

		Scene scene = new Scene(new SpelerRegistratieScherm(dc, this), breedte, hoogte);
		stage.setScene(scene);
	}
}
