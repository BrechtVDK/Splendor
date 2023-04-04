package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import domein.Edelsteenfiche;
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
		// btnBevestig.setVisible(false);

		btnKaart.setOnAction(this::kiesKaartGeklikt);
		btnFiche.setOnAction(this::kiesFicheGeklikt);
		btnPas.setOnAction(this::pasGeklikt);
		// Brecht: btnBevestig geschrapt: onderaan scherm toevoegen (onder kaart of
		// fiches)
		this.getChildren().addAll(lblSpelerAanDeBeurt, lblKeuze, btnKaart, btnFiche, btnPas, lblInfo);
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
		// btnBevestig.setVisible(true);
		// deactiveerBevestigKnop();
	}

	/*
	 * public void activeerBevestigKnop() { // Brecht aangepast naar visible
	 * btnBevestig.setVisible(true); // btnBevestig.setDisable(false); }
	 * 
	 * public void deactiveerBevestigKnop() { // Brecht aangepast naar visible
	 * btnBevestig.setVisible(false); // btnBevestig.setDisable(true); }
	 */

	public void verwijderBevestigKnop() {
		if (this.getChildren().contains(btnBevestig)) {
			this.getChildren().remove(btnBevestig);
		}

	}

	private void bevestigGeklikt(ActionEvent e, String spelerKeuze) {

		if (spelerKeuze.equals("kaart")) {
			hs.verplaatsOntwikkelingskaartVanTafelNaarSpeler(gekozenKaart);
			verwijderBevestigKnop();
		}
		else if (spelerKeuze.equals("fiche")) {
			List<Edelsteenfiche> teVerplaatsenFiches = new ArrayList<>();
			for (FXEdelsteenFiche ef : edelsteenfiches) {
				teVerplaatsenFiches.add(new Edelsteenfiche(ef.getEdelsteen()));
			}
			hs.verplaatsEdelsteenFichesNaarSpeler(teVerplaatsenFiches);
			verwijderBevestigKnop();

		}

		maakInfoLabelLeeg(3);
	}

	public void zetKeuzeMenuTerug() {
		lblKeuze.setText("Wat wil je doen in deze beurt?");
		// btnBevestig.setVisible(false);
		btnKaart.setVisible(true);
		btnFiche.setVisible(true);
		btnPas.setVisible(true);
		verwijderBevestigKnop();
	}

	// Ontwikkelingskaarten:
	public void voegOntwikkelingskaartToe(FXOntwikkelingskaart kaart) {
		this.gekozenKaart = kaart;
		// Brecht: btnBevestig onder kaart toevoegen
		this.getChildren().addAll(gekozenKaart, btnBevestig);
		// activeerBevestigKnop();
	}

	public void verwijderKaart(FXOntwikkelingskaart kaart) {
		this.getChildren().remove(kaart);
	}

	// Fiches
	public void voegFicheToe(FXEdelsteenFiche edelsteenfiche) {
		if (edelsteenfiches.size() < 3) {
			edelsteenfiches.add(edelsteenfiche);
			this.getChildren().add(edelsteenfiche);
			if (edelsteenfiches.size() == 3) {
				hs.maakFichesOnKlikbaar();
			} else if (edelsteenfiches.size() == 2) {
				this.getChildren().add(btnBevestig);
			}
		} else {
			lblInfo.setText("Je mag maximum 3 fiches kiezen");
		}
	}

	public void verwijderFiches() {
		for (FXEdelsteenFiche ef : edelsteenfiches) {
			this.getChildren().remove(ef);
		}
		edelsteenfiches.clear();
	}

}
