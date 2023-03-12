package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
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
	private List<String> spelers;
	private ListView<String> lvSpelers;

	public SpelerRegistratieScherm(DomeinController dc, WelkomScherm ws) {
		this.dc = dc;
		this.ws = ws;
		spelers = new ArrayList<>();
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
		// Enter na ingeven geboortejaar = klikken op voeg toe
		txtGeboortejaar.setOnAction(this::voegToeGeklikt);

		Button btnVoegToe = new Button("voeg toe");
		btnVoegToe.setOnAction(this::voegToeGeklikt);

		btnStartSpel = new Button("start spel");
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
			// >=2 spelers: btnStartSpel klikbaar
			if (dc.geefAantalSpelers() >= 2) {
				btnStartSpel.setDisable(false);
			}
			// speler toevoegen aan lijst
			spelers.add(String.format("%s - %d", gebruikersnaam, geboortejaar));
			// speler zichtbaar in listview maken
			updateSpelersLijst();
		} catch (NumberFormatException e) {
			toonFoutmelding("Geboortejaar is niet juist ingevuld");
		} catch (NullPointerException e) {
			toonFoutmelding("Speler is niet geregistreerd in de databank");
		} catch (IllegalArgumentException e) {
			toonFoutmelding(e.getMessage());
		}

	}

	private void updateSpelersLijst() {
		lvSpelers.setItems(FXCollections.observableArrayList(spelers));
	}

	private void startSpelGeklikt(ActionEvent event) {
		toonStartspeler();

		Stage stage = (Stage) this.getScene().getWindow();
		double breedte = this.getScene().getWidth();
		double hoogte = this.getScene().getHeight();
		stage.setTitle("Splendor");

		Scene scene = new Scene(new Hoofdscherm(dc, ws, spelers), breedte, hoogte);
		scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
		stage.setScene(scene);
	}

	private void toonStartspeler() {
		String startspeler = spelers.get(dc.geefSpelerAanDeBeurt());
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Startspeler");
		alert.setContentText("Speler \"" + startspeler + "\" mag starten");
		alert.showAndWait();

	}

	private void toonFoutmelding(String melding) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Foutmelding");
		alert.setContentText(melding);
		alert.showAndWait();
	}
}
