package gui;

import domein.DomeinController;
import domein.Spel;
import domein.Speler;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import resources.Taal;

public class SpelerRegistratieScherm extends GridPane {
	private DomeinController dc;
	private WelkomScherm ws;
	private TextField txtGebruikersnaam, txtGeboortejaar;
	private Button btnStartSpel;
	private ListView<Speler> lvSpelers;
	private Label lblFoutmelding;

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

		Label lblGebruikersnaam = new Label(Taal.vertaling("SpelerRegistratieScherm.0")); //$NON-NLS-1$
		lblGebruikersnaam.setId("SpelerRegistratieScherm.0");
		txtGebruikersnaam = new TextField();

		Label lblGeboortejaar = new Label(Taal.vertaling("SpelerRegistratieScherm.1")); //$NON-NLS-1$
		lblGeboortejaar.setId("SpelerRegistratieScherm.1");
		txtGeboortejaar = new TextField();
		// Enter na ingeven geboortejaar = klikken op btnVoegToe
		txtGeboortejaar.setOnAction(this::voegToeGeklikt);

		Button btnVoegToe = new Button(Taal.vertaling("SpelerRegistratieScherm.2")); //$NON-NLS-1$
		btnVoegToe.setId("SpelerRegistratieScherm.2");
		btnVoegToe.setMinWidth(150);
		btnVoegToe.setOnAction(this::voegToeGeklikt);

		btnStartSpel = new Button(Taal.vertaling("SpelerRegistratieScherm.3")); //$NON-NLS-1$
		btnStartSpel.setId("SpelerRegistratieScherm.3");
		btnStartSpel.setMinWidth(150);
		// Pas klikbaar na toevoegen 2 spelers
		btnStartSpel.setDisable(true);
		btnStartSpel.setOnAction(this::startSpelGeklikt);

		Label lblSpelers = new Label(Taal.vertaling("SpelerRegistratieScherm.4")); //$NON-NLS-1$
		lblSpelers.setId("SpelerRegistratieScherm.4");

		lvSpelers = new ListView<Speler>();
		// observableList koppelen aan listView
		lvSpelers.setItems(dc.geefSpelers());
		lvSpelers.setMinWidth(200);
		lvSpelers.setMaxHeight(150);
		// klikken op listView = geen effect
		lvSpelers.setMouseTransparent(true);

		lblFoutmelding = new Label();
		lblFoutmelding.setTextFill(Color.RED);

		FXTaalKeuze chBTaalKeuze = new FXTaalKeuze(this, Taal.getGekozenTaal());
		chBTaalKeuze.setAlignment(Pos.CENTER);

		this.add(lblGebruikersnaam, 0, 1);
		this.add(txtGebruikersnaam, 1, 1);
		this.add(lblGeboortejaar, 0, 2);
		this.add(txtGeboortejaar, 1, 2);
		this.add(btnVoegToe, 1, 3);
		this.add(btnStartSpel, 1, 4);
		this.add(chBTaalKeuze, 1, 6);
		this.add(lblSpelers, 3, 0);
		this.add(lvSpelers, 3, 1, 1, 4);
		this.add(lblFoutmelding, 0, 5, this.getColumnCount(), 1);

	}

	private void voegToeGeklikt(ActionEvent event) {
		lblFoutmelding.setText(""); //$NON-NLS-1$
		try {
			String gebruikersnaam = txtGebruikersnaam.getText();
			int geboortejaar = Integer.parseInt(txtGeboortejaar.getText());
			dc.voegSpelerToeAanSpel(gebruikersnaam, geboortejaar);
			// Vanaf MIN_SPELERS: btnStartSpel klikbaar
			if (dc.geefAantalSpelers() == Spel.MIN_SPELERS) {
				btnStartSpel.setDisable(false);
			}
			// TextFields leegmaken en focus terug instellen
			txtGebruikersnaam.clear();
			txtGeboortejaar.clear();
			txtGebruikersnaam.requestFocus();

		} catch (NumberFormatException e) {
			toonFoutmelding(Taal.vertaling("SpelerRegistratieScherm.6")); //$NON-NLS-1$
			// David 2023/03/19 16:22 Toegevoegd als assistentie wanneer er een fout ingave
			// is
			txtGeboortejaar.requestFocus();
			txtGeboortejaar.selectAll();
		} catch (IllegalArgumentException e) {
			toonFoutmelding(e.getMessage());
			// David 2023/03/19 16:22 Toegevoegd als assistentie wanneer er een fout ingave
			// is
			txtGebruikersnaam.requestFocus();
			txtGebruikersnaam.selectAll();
		}
	}

	private void startSpelGeklikt(ActionEvent event) {
		dc.organiseerSpelVolgensHetAantalSpelers();
		dc.speelSpel();
		toonStartspeler();

		Stage stage = (Stage) this.getScene().getWindow();
		double breedte = this.getScene().getWidth();
		double hoogte = this.getScene().getHeight();
		stage.setTitle("Splendor"); //$NON-NLS-1$

		Scene scene = new Scene(new Hoofdscherm(dc, ws), breedte, hoogte);
		scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm()); //$NON-NLS-1$
		scene.setFill(Color.ALICEBLUE);
		stage.setScene(scene);
	}

	private void toonStartspeler() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(""); //$NON-NLS-1$
		alert.setTitle(Taal.vertaling("SpelerRegistratieScherm.10")); //$NON-NLS-1$
		alert.setContentText(String.format(Taal.vertaling("SpelerRegistratieScherm.11"), dc.geefSpelerAanDeBeurt())); //$NON-NLS-1$
		alert.showAndWait();

	}

	private void toonFoutmelding(String melding) {
		lblFoutmelding.setText(melding);
	}
}