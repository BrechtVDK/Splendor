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
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LinkerInfoScherm extends VBox {
	private DomeinController dc;
	private Hoofdscherm hs;
	private FXOntwikkelingskaart gekozenKaart;
	private List<FXEdelsteenFiche> edelsteenfiches;
	private HBox hboxEdelsteenficheGeefTerugScherm;
	private EdelsteenficheGeefTerugScherm edelsteenficheGeefTerugScherm;
	private int indexBtnBevestig;

	private Label lblSpelerAanDeBeurt, lblKeuze, lblInfoOfFout;
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
		lblInfoOfFout = new Label();

		btnKaart = new Button("Ik koop een ontwikkelingskaart");
		btnFiche = new Button("Ik neem edelsteenfiches");
		btnPas = new Button("Ik pas en sla deze ronde over");
		btnBevestig = new Button("Bevestig keuze");
		btnBevestig.setId("btnBevestig");
		btnBevestig.setVisible(false);

		btnKaart.setOnAction(this::kiesKaartGeklikt);
		btnFiche.setOnAction(this::kiesFicheGeklikt);
		btnPas.setOnAction(this::pasGeklikt);
		this.getChildren().addAll(lblSpelerAanDeBeurt, lblKeuze, btnKaart, btnFiche, btnPas, lblInfoOfFout,
				btnBevestig);

		// index btnBevestig bijhouden om nodes tussen te voegen / verwijderen en later
		// terug toe te voegen
		indexBtnBevestig = this.getChildren().indexOf(btnBevestig);

		// alle labels wrappen
		this.getChildren().forEach(node -> {
			if (node instanceof Label) {
				((Label) node).setWrapText(true);
			}
		});
	}

	private void pasGeklikt(ActionEvent e) {
		hs.eindeBeurt();
		toonInfo(String.format("Je besliste om te passen, de volgende speler is %s.", dc.geefSpelerAanDeBeurt()));
		maakInfoOfFoutLabelLeegNaXSec(2);
	}

	protected void stelVolgendeSpelerIn() {
		lblSpelerAanDeBeurt
				.setText(String.format("Speler aan de beurt: %s", dc.geefSpelerAanDeBeurt().getGebruikersnaam()));
	}

	private void maakInfoOfFoutLabelLeegNaXSec(double seconden) {
		// tijdlijn om boodschap na x seconden te wissen
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(seconden), ev -> {
			lblInfoOfFout.setText("");
		}));
		timeline.play();
	}

	protected void maakInfoOfFoutLabelLeeg() {
		lblInfoOfFout.setText("");
	}

	private void kiesKaartGeklikt(ActionEvent e) {
		maakInfoOfFoutLabelLeeg();
		hs.maakKaartenKlikbaar();
		verbergKeuzeknoppen();
		lblKeuze.setText("Kies een kaart van de tafel");
		btnBevestig.setOnAction((event) -> bevestigGeklikt(event, "kaart"));
	}

	private void kiesFicheGeklikt(ActionEvent e) {
		maakInfoOfFoutLabelLeeg();
		hs.maakFichesKlikbaar();
		verbergKeuzeknoppen();
		lblKeuze.setText("Kies 2 fiches van dezelfde kleur of 3 verschillende.");
		btnBevestig.setOnAction((event) -> bevestigGeklikt(event, "fiche"));
	}

	protected void toonInfo(String info) {
		lblInfoOfFout.setTextFill(Color.BLACK);
		lblInfoOfFout.setText(info);
	}

	protected void toonFoutmelding(String foutmelding) {
		lblInfoOfFout.setTextFill(Color.RED);
		lblInfoOfFout.setText(foutmelding);
	}

	protected void verbergKeuzeknoppen() {
		btnKaart.setVisible(false);
		btnFiche.setVisible(false);
		btnPas.setVisible(false);
		deactiveerBevestigKnop();
	}

	protected void activeerBevestigKnop() {
		btnBevestig.setVisible(true);
	}

	protected void deactiveerBevestigKnop() {
		btnBevestig.setVisible(false);
	}

	private void bevestigGeklikt(ActionEvent e, String spelerKeuze) {

		switch (spelerKeuze) {
		case ("kaart") -> {
			hs.verplaatsOntwikkelingskaartVanTafelNaarSpeler(gekozenKaart);
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
			maakInfoOfFoutLabelLeeg();
			List<Edelsteenfiche> terugTeGevenFiches = edelsteenficheGeefTerugScherm.geefTerugTeGevenFiches();

			// validatie exact 10 fiches in bezit na teruggave
			try {
				dc.verplaatsEdelsteenfichesVanSpelerNaarSpelNaTeVeelInBezit(terugTeGevenFiches);
				this.getChildren().remove(hboxEdelsteenficheGeefTerugScherm);
				// btnBevestig terug toevoegen op oorspronkelijke plaats
				this.getChildren().add(indexBtnBevestig, btnBevestig);
				zetKeuzeMenuTerug();
				hs.eindeBeurt();
			} catch (IllegalArgumentException ex) {
				toonFoutmelding(ex.getMessage());
				edelsteenficheGeefTerugScherm.resetFiches();
			}

		}
		}

		// maakInfoOfFoutLabelLeegNaXSec(5);

	}

	protected void zetKeuzeMenuTerug() {
		lblKeuze.setText("Wat wil je doen in deze beurt?");
		btnKaart.setVisible(true);
		btnFiche.setVisible(true);
		btnPas.setVisible(true);
		deactiveerBevestigKnop();
	}

	// Ontwikkelingskaarten:
	protected void voegOntwikkelingskaartToe(FXOntwikkelingskaart kaart) {
		this.gekozenKaart = kaart;
		this.getChildren().add(indexBtnBevestig, gekozenKaart);
		activeerBevestigKnop();
	}

	protected void verwijderKaart(FXOntwikkelingskaart kaart) {
		this.getChildren().remove(kaart);
	}

	// Fiches
	protected void voegFicheToe(FXEdelsteenFiche edelsteenfiche) {
		edelsteenfiches.add(edelsteenfiche);
		this.getChildren().add(indexBtnBevestig, edelsteenfiche);
		if (edelsteenfiches.size() == Spel.MAX_FICHES_PER_BEURT) {
			hs.maakFichesOnKlikbaar();
			// enkel bij eerste fiche activeren
		} else if (edelsteenfiches.size() == 1) {
			activeerBevestigKnop();
		}
	}

	protected void verwijderFiches() {
		for (FXEdelsteenFiche ef : edelsteenfiches) {
			this.getChildren().remove(ef);
		}
		edelsteenfiches.clear();
	}

	protected void verwijderEnkeleFiche(FXEdelsteenFiche ef) {
		this.getChildren().remove(ef);
		edelsteenfiches.remove(ef);

		if (edelsteenfiches.size() == 0) {
			deactiveerBevestigKnop();
		}
	}

	protected void voegEdelsteenficheTerugToeAanStapel(Edelsteenfiche e) {
		hs.voegEdelsteenfichesTerugToeAanStapels(Arrays.asList(e));
	}

	protected void zetKlaarOmFichesTerugTeGeven() {
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

	protected void maakFichesKlikbaarInEdelsteenficheScherm() {
		hs.maakFichesKlikbaar();

	}

}
