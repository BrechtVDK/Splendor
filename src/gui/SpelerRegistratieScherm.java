package gui;

import domein.DomeinController;
import domein.Spel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SpelerRegistratieScherm extends GridPane {
	private DomeinController dc;
	private WelkomScherm ws;
	private TextField txtGebruikersnaam, txtGeboortejaar;
	private Button btnStartSpel;
	private ListView<String> lvSpelers;

	public SpelerRegistratieScherm(DomeinController dc, WelkomScherm ws) {
		this.dc = dc;
		this.ws = ws;
		buildGui();
	}

	private void buildGui() {
		this.setHgap(25);
		this.setVgap(25);
		this.setPadding(new Insets(25));
		this.setAlignment(Pos.CENTER);

		Label lblGebruikersnaam = new Label("Gebruikersnaam");
		txtGebruikersnaam = new TextField();

		Label lblGeboortejaar = new Label("Geboortejaar");
		txtGeboortejaar = new TextField();
		// Enter na ingeven geboortejaar = klikken op btnVoegToe
		txtGeboortejaar.setOnAction(this::voegToeGeklikt);

		Button btnVoegToe = new Button("voeg toe");
		btnVoegToe.setMinWidth(150);
		btnVoegToe.setOnAction(this::voegToeGeklikt);

		btnStartSpel = new Button("start spel");
		btnStartSpel.setMinWidth(150);
		// Pas klikbaar na toevoegen 2 spelers
		btnStartSpel.setDisable(true);
		btnStartSpel.setOnAction(this::startSpelGeklikt);

		Label lblSpelers = new Label("Spelers toegevoegd aan spel:");

		lvSpelers = new ListView<String>();
		lvSpelers.setMinWidth(200);
		lvSpelers.setMaxHeight(150);

		this.add(lblGebruikersnaam, 0, 1);
		this.add(txtGebruikersnaam, 1, 1);
		this.add(lblGeboortejaar, 0, 2);
		this.add(txtGeboortejaar, 1, 2);
		this.add(btnVoegToe, 1, 3);
		this.add(btnStartSpel, 1, 4);
		this.add(lblSpelers, 3, 0);
		this.add(lvSpelers, 3, 1, 1, 4);

	}

	private void voegToeGeklikt(ActionEvent event) {
		try {
			String gebruikersnaam = txtGebruikersnaam.getText();
			int geboortejaar = Integer.parseInt(txtGeboortejaar.getText());
			dc.voegSpelerToeAanSpel(gebruikersnaam, geboortejaar);
			// Vanaf MIN_SPELERS: btnStartSpel klikbaar
			if (dc.geefAantalSpelers() == Spel.MIN_SPELERS) {
				btnStartSpel.setDisable(false);
			}
			// speler zichtbaar in listview maken
			updateLvSpelers();
			// TextFields leegmaken en focus terug instellen
			txtGebruikersnaam.clear();
			txtGeboortejaar.clear();
			txtGebruikersnaam.requestFocus();

		} catch (NumberFormatException e) {
			toonFoutmelding("Geboortejaar is niet juist ingevuld");
		} catch (IllegalArgumentException e) {
			toonFoutmelding(e.getMessage());
		}

	}

	private void updateLvSpelers() {
		lvSpelers.setItems(FXCollections.observableArrayList(dc.geefSpelers()));

	}

	private void startSpelGeklikt(ActionEvent event) {
		dc.organiseerSpelVolgensHetAantalSpelers();
		toonStartspeler();

		Stage stage = (Stage) this.getScene().getWindow();
		double breedte = this.getScene().getWidth();
		double hoogte = this.getScene().getHeight();
		stage.setTitle("Splendor");

		Scene scene = new Scene(new Hoofdscherm(dc, ws), breedte, hoogte);
		scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
		stage.setScene(scene);
	}

	private void toonStartspeler() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Startspeler");
		alert.setContentText(String.format("Speler %s mag starten", dc.geefSpelerAanDeBeurt()));
		alert.showAndWait();

	}

	private void toonFoutmelding(String melding) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Foutmelding");
		alert.setContentText(melding);
		alert.showAndWait();
	}
}
