package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domein.DomeinController;
import domein.Edelsteenfiche;
import domein.Spel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class LinkerInfoScherm extends VBox {
	private DomeinController dc;
	private Hoofdscherm hs;
	private FXOntwikkelingskaart gekozenKaart;
	private List<FXEdelsteenFiche> edelsteenfiches;
	private HBox hboxEdelsteenficheGeefTerugScherm;
	private EdelsteenficheGeefTerugScherm edelsteenficheGeefTerugScherm;
	private int indexBtnBevestig;

	private Label lblSpelerAanDeBeurt, lblKeuze, lblInfo;
	private Button btnKaart, btnFiche, btnPas, btnBevestig;

	public LinkerInfoScherm(DomeinController dc, Hoofdscherm hs) {
		this.dc = dc;
		this.hs = hs;
		this.edelsteenfiches = new ArrayList<>();

		buildGui();
	}

	private void buildGui() {
		// Ook een optie:
		// Neemt dynamisch 1/6 breedte in van Hoofdscherm
		// super.prefWidthProperty().bind(hs.widthProperty().divide(6));
		this.setMinWidth(225);
		this.setMaxWidth(225);
		this.setSpacing(10);
		this.setAlignment(Pos.TOP_LEFT);

		lblSpelerAanDeBeurt = new Label(
				String.format("Speler aan de beurt: %s", dc.geefSpelerAanDeBeurt().getGebruikersnaam()));
		lblKeuze = new Label("Wat wil je doen in deze beurt?");
		lblInfo = new Label();

		btnKaart = new Button("Ik koop een ontwikkelingskaart");
		btnFiche = new Button("Ik neem edelsteenfiches");
		btnPas = new Button("Ik pas en sla deze ronde over");
		btnBevestig = new Button("Bevestig keuze");
		btnBevestig.setId("btnBevestig");
		btnBevestig.setVisible(false);

		btnKaart.setOnAction(this::kiesKaartGeklikt);
		btnFiche.setOnAction(this::kiesFicheGeklikt);
		btnPas.setOnAction(this::pasGeklikt);
		// Brecht: btnBevestig geschrapt: onderaan scherm toevoegen (onder kaart of
		// fiches)
		this.getChildren().addAll(lblSpelerAanDeBeurt, lblKeuze, btnKaart, btnFiche, btnPas, lblInfo, btnBevestig);
		// alle labels wrappen
		this.getChildren().forEach(node -> {
			if (node instanceof Label) {
				((Label) node).setWrapText(true);
			}
		});
	}

	private void pasGeklikt(ActionEvent e) {
		hs.bepaalVolgendeSpeler();
		// Brecht: wordt in methode hierboven aangeroepen
		// stelVolgendeSpelerIn();
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
		verbergKeuzeknoppen();
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
		deactiveerBevestigKnop();
	}

	public void activeerBevestigKnop() { // Brecht aangepast naar visible
		btnBevestig.setVisible(true); // btnBevestig.setDisable(false);
	}

	public void deactiveerBevestigKnop() { // Brecht aangepast naar visible
		btnBevestig.setVisible(false); // btnBevestig.setDisable(true);
	}

//	public void verwijderBevestigKnop() {
//		if (this.getChildren().contains(btnBevestig)) {
//			this.getChildren().remove(btnBevestig);
//		}
//
//	}

	private void bevestigGeklikt(ActionEvent e, String spelerKeuze) {

		switch (spelerKeuze) {
		case ("kaart") -> {
			hs.verplaatsOntwikkelingskaartVanTafelNaarSpeler(gekozenKaart);
			// verwijderBevestigKnop();
			deactiveerBevestigKnop();
		}
		case ("fiche") -> {
			List<Edelsteenfiche> teVerplaatsenFiches = new ArrayList<>();
			for (FXEdelsteenFiche ef : edelsteenfiches) {
				teVerplaatsenFiches.add(new Edelsteenfiche(ef.getEdelsteen()));
			}
			deactiveerBevestigKnop();
			hs.verplaatsEdelsteenFichesNaarSpeler(teVerplaatsenFiches);
			hs.maakFichesOnKlikbaar();
		}
		case ("ficheTerug") -> {
			List<Edelsteenfiche> terugTeGevenFiches = edelsteenficheGeefTerugScherm.geefTerugTeGevenFiches();

			this.getChildren().remove(hboxEdelsteenficheGeefTerugScherm);
			// btnBevestig terug toevoegen op oorspronkelijke plaats
			this.getChildren().add(indexBtnBevestig, btnBevestig);

			// validatie exact 10 fiches in bezit na teruggave
			try {
				dc.verplaatsEdelsteenfichesVanSpelerNaarSpelNaTeVeelInBezit(terugTeGevenFiches);
				zetKeuzeMenuTerug();
				hs.bepaalVolgendeSpeler();
			} catch (IllegalArgumentException ex) {
				toonFoutmelding(ex.getMessage());
				zetKlaarOmFichesTerugTeGeven();
			}
		}
		}

		maakInfoLabelLeeg(3);

	}

	public void zetKeuzeMenuTerug() {
		lblKeuze.setText("Wat wil je doen in deze beurt?");
		btnKaart.setVisible(true);
		btnFiche.setVisible(true);
		btnPas.setVisible(true);
		deactiveerBevestigKnop();
		// verwijderBevestigKnop();
	}

	// Ontwikkelingskaarten:
	public void voegOntwikkelingskaartToe(FXOntwikkelingskaart kaart) {
		this.gekozenKaart = kaart;
		// Brecht: btnBevestig onder kaart toevoegen
		this.getChildren().addAll(gekozenKaart);
		activeerBevestigKnop();
	}

	public void verwijderKaart(FXOntwikkelingskaart kaart) {
		this.getChildren().remove(kaart);
	}

	// Fiches
	public void voegFicheToe(FXEdelsteenFiche edelsteenfiche) {
		// Brecht: lijnen in commentaar lijken me overbodig aangezien fiches onklikbaar
		// worden vanaf MAX_FICHES_PER_BEURT
		// Jonas: correct
		// if (edelsteenfiches.size() < Spel.MAX_FICHES_PER_BEURT) {
		edelsteenfiches.add(edelsteenfiche);
		this.getChildren().add(edelsteenfiche);
		if (edelsteenfiches.size() == Spel.MAX_FICHES_PER_BEURT) {
			hs.maakFichesOnKlikbaar();
			// enkel bij eerste fiche activeren
		} else if (edelsteenfiches.size() == 1) {
			activeerBevestigKnop();
		}
		/*
		 * } else { lblInfo.setText("Je mag maximum 3 fiches kiezen"); }
		 */
	}

	public void verwijderFiches() {
		for (FXEdelsteenFiche ef : edelsteenfiches) {
			this.getChildren().remove(ef);
		}
		edelsteenfiches.clear();
	}

	public void verwijderEnkeleFiche(FXEdelsteenFiche ef) {
		this.getChildren().remove(ef);
		edelsteenfiches.remove(ef);

		if (edelsteenfiches.size() == 0) {
			deactiveerBevestigKnop();
		}
	}

	public void voegEdelsteenficheTerugToeAanStapel(Edelsteenfiche e) {
		hs.voegEdelsteenfichesTerugToeAanStapels(Arrays.asList(e));
	}

	public void zetKlaarOmFichesTerugTeGeven() {
		// index btnBevestig bijhouden om later terug toe te voegen
		indexBtnBevestig = this.getChildren().indexOf(btnBevestig);
		this.getChildren().remove(btnBevestig);
		activeerBevestigKnop();

		hboxEdelsteenficheGeefTerugScherm = new HBox();
		hboxEdelsteenficheGeefTerugScherm.setAlignment(Pos.CENTER);

		edelsteenficheGeefTerugScherm = new EdelsteenficheGeefTerugScherm(dc);
		// btnBevestig in hbox stoppen (boven of onder edelsteensteficheGeefTerugScherm
		// was te weinig plaats)
		hboxEdelsteenficheGeefTerugScherm.getChildren().addAll(edelsteenficheGeefTerugScherm, btnBevestig);
		this.getChildren().add(hboxEdelsteenficheGeefTerugScherm);

		btnBevestig.setOnAction((event) -> bevestigGeklikt(event, "ficheTerug"));
	}

	public void maakFichesKlikbaarInEdelsteenficheScherm() {
		hs.maakFichesKlikbaar();

	}

}
