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
import resources.Taal;

public class WelkomScherm extends VBox {
	private DomeinController dc;

	public WelkomScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		ImageView ivSplendor = new ImageView(new Image(getClass().getResourceAsStream("/images/splendor.jpg"))); //$NON-NLS-1$
		FXTaalKeuze chBTaalKeuze = new FXTaalKeuze(this, Taal.getGekozenTaal());
		Button btnStart = new Button(Taal.vertaling("WelkomScherm.1"));//$NON-NLS-1$
		btnStart.setId("WelkomScherm.1");
		btnStart.setMinWidth(200);


		btnStart.setOnAction(this::startGeklikt);

		this.setSpacing(25);
		this.setAlignment(Pos.CENTER);
		chBTaalKeuze.setAlignment(Pos.CENTER);
		this.getChildren().addAll(ivSplendor, btnStart, chBTaalKeuze);
	}

	private void startGeklikt(ActionEvent evt) {
		dc.startNieuwSpel();

		// SpelerRegistratieScherm overslaan
		Stage stage = (Stage) this.getScene().getWindow();
		double breedte = this.getScene().getWidth();
		double hoogte = this.getScene().getHeight();
		stage.setTitle(Taal.vertaling("WelkomScherm.2")); //$NON-NLS-1$

		Scene scene = new Scene(new SpelerRegistratieScherm(dc, this), breedte, hoogte);
		stage.setScene(scene);

		// Te verbergen bij ingave opdracht
//		dc.voegSpelerToeAanSpel("Jonas", 1989);
//		dc.voegSpelerToeAanSpel("Brecht", 1993);
//		dc.voegSpelerToeAanSpel("David", 1975);
//		dc.organiseerSpelVolgensHetAantalSpelers();
//		dc.speelSpel();
//		Stage stage = (Stage) this.getScene().getWindow();
//		Scene scene = new Scene(new Hoofdscherm(dc, this), this.getScene().getWidth(), this.getScene().getHeight());
//		scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
//		scene.setFill(Color.ALICEBLUE);
//		stage.setScene(scene);
		// Tot hier
	}
}
