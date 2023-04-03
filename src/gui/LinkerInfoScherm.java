package gui;


import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class LinkerInfoScherm extends VBox {
	private DomeinController dc;
	private Hoofdscherm hs;
	private FXOntwikkelingskaart gekozenKaart;
	private List<FXEdelsteenFiche> edelsteenfiches;

	private Label lblSpelerAanDeBeurt, lblKeuze, lblInfo;
	private Button btnKaart, btnFiche, btnPas, btnBevestig;

	public LinkerInfoScherm(DomeinController dc, Hoofdscherm hs) {
		this.dc = dc;
		this.hs = hs;
		this.edelsteenfiches = new ArrayList();

		buildGui();
	}

	private void buildGui() {
		this.setSpacing(10);
		this.setAlignment(Pos.TOP_CENTER);

		lblSpelerAanDeBeurt = new Label(
				String.format("Speler aan de beurt: %s", dc.geefSpelerAanDeBeurt().getGebruikersnaam()));
		lblKeuze = new Label("Wat wil je doen in deze beurt?");
		lblInfo = new Label("");

		btnKaart = new Button("Ik koop een ontwikkelingskaart");
		btnFiche = new Button("Ik neem edelsteenfiches");
		btnPas = new Button("Ik pas en sla deze ronde over");
		btnBevestig = new Button("Bevestig keuze");
		btnBevestig.setVisible(false);

		btnKaart.setOnAction(this::kiesKaartGeklikt);
		btnFiche.setOnAction(this::kiesFicheGeklikt);
		btnPas.setOnAction(this::pasGeklikt);

		this.getChildren().addAll(lblSpelerAanDeBeurt, lblKeuze, btnKaart, btnFiche, btnPas, btnBevestig, lblInfo);
	}

	private void pasGeklikt(ActionEvent e) {
		hs.bepaalVolgendeSpeler();
		stelVolgendeSpelerIn();
		lblInfo.setText(
				String.format("Je besliste om te passen, de volgende speler is %s.", dc.geefSpelerAanDeBeurt()));

		maakInfoLabelLeeg(2);
	}

	public void stelVolgendeSpelerIn() {
		lblSpelerAanDeBeurt
				.setText(String.format("Speler aan de beurt: %s", dc.geefSpelerAanDeBeurt().getGebruikersnaam()));
	}

	private void maakInfoLabelLeeg(double seconden) {
		// tijdlijn om pasboodschap na x seconden te wissen (andere speler is dan reeds
		// aan de beurt)
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(seconden), ev -> {
			lblInfo.setText("");
		}));
		timeline.play();
	}

	private void kiesKaartGeklikt(ActionEvent e) {
		hs.maakKaartenKlikbaar();
		lblKeuze.setText("Kies een kaart van de tafel");
		btnBevestig.setOnAction((event) -> bevestigGeklikt(event, "kaart"));
	}

	private void kiesFicheGeklikt(ActionEvent e) {
		hs.maakFichesKlikbaar();
		verbergKeuzeknoppen();
		lblKeuze.setText("Kies 2 fiches van dezelfde kleur of 3 verschillende.");
		btnBevestig.setOnAction((event) -> bevestigGeklikt(event, "fiche"));
	}

	public void toonFoutmelding(String foutmelding) {
		lblInfo.setText(foutmelding);
	}

	private void verbergKeuzeknoppen() {
		btnKaart.setVisible(false);
		btnFiche.setVisible(false);
		btnPas.setVisible(false);
		btnBevestig.setVisible(true);
		deactiveerBevestigKnop();
	}

	public void activeerBevestigKnop() {
		btnBevestig.setDisable(false);
	}

	public void deactiveerBevestigKnop() {
		btnBevestig.setDisable(true);
	}

	private void bevestigGeklikt(ActionEvent e, String spelerKeuze) {


		if (spelerKeuze.equals("kaart")) {
				hs.verplaatsOntwikkelingskaartVanTafelNaarSpeler(gekozenKaart);
		}
	}


	public void zetKeuzeMenuTerug() {
		lblKeuze.setText("Wat wil je doen in deze beurt?");
		btnBevestig.setVisible(false);
		btnKaart.setVisible(true);
		btnFiche.setVisible(true);
		btnPas.setVisible(true);
	}

	// Ontwikkelingskaarten:
	public void voegOntwikkelingskaartToe(FXOntwikkelingskaart kaart) {
		this.gekozenKaart = kaart;
		this.getChildren().add(gekozenKaart);

	}

	public void verwijderKaart(FXOntwikkelingskaart kaart) {
		this.getChildren().remove(kaart);
	}

	// Fiches
	public void voegFicheToe(FXEdelsteenFiche edelsteenfiche) {
		if (edelsteenfiches.size() < 3) {
			edelsteenfiches.add(edelsteenfiche);
			this.getChildren().add(edelsteenfiche);
		} else
			lblInfo.setText("Je mag maximum 3 fiches kiezen");
	}

}

