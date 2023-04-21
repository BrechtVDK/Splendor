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
import resources.Taal;

public class LinkerInfoScherm extends VBox {
	private DomeinController dc;
	private Hoofdscherm hs;
	private FXOntwikkelingskaart gekozenKaart;
	private List<FXEdelsteenFiche> edelsteenfiches;
	private HBox hboxEdelsteenficheGeefTerugScherm;
	private EdelsteenficheGeefTerugScherm edelsteenficheGeefTerugScherm;
	private int indexBtnBevestig, indexBtnAnnuleer;

	private Label lblSpelerAanDeBeurt, lblSpelerAanDeBeurtTekst, lblKeuze, lblInfoOfFout;
	private Button btnKaart, btnFiche, btnPas, btnBevestig, btnAnnuleer;

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

		lblSpelerAanDeBeurtTekst = new Label(Taal.vertaling("LinkerInfoScherm.0")); // $NON-NLS-1$
		lblSpelerAanDeBeurtTekst.setId("LinkerInfoScherm.0");
		lblSpelerAanDeBeurt = new Label(dc.geefSpelerAanDeBeurt().getGebruikersnaam());
		lblSpelerAanDeBeurt.getStyleClass().add("spelerAanDeBeurt");

		lblKeuze = new Label(Taal.vertaling("LinkerInfoScherm.1")); //$NON-NLS-1$
		lblKeuze.setId("LinkerInfoScherm.1"); //$NON-NLS-1$
		lblKeuze.setAccessibleHelp("LinkerInfoScherm.1"); //$NON-NLS-1$
		lblInfoOfFout = new Label();

		btnKaart = new Button(Taal.vertaling("LinkerInfoScherm.2")); //$NON-NLS-1$
		btnKaart.setId("LinkerInfoScherm.2"); //$NON-NLS-1$
		btnFiche = new Button(Taal.vertaling("LinkerInfoScherm.3")); //$NON-NLS-1$
		btnFiche.setId("LinkerInfoScherm.3"); //$NON-NLS-1$
		btnPas = new Button(Taal.vertaling("LinkerInfoScherm.4")); //$NON-NLS-1$
		btnPas.setId("LinkerInfoScherm.4"); //$NON-NLS-1$
		btnBevestig = new Button(Taal.vertaling("btnBevestig")); //$NON-NLS-1$
		btnBevestig.setId("btnBevestig"); //$NON-NLS-1$
		btnBevestig.setVisible(false);
		btnBevestig.setMinWidth(70);
		btnAnnuleer = new Button(Taal.vertaling("LinkerInfoScherm.7")); //$NON-NLS-1$
		btnAnnuleer.setId("LinkerInfoScherm.7"); //$NON-NLS-1$
		btnAnnuleer.setVisible(false);
		btnAnnuleer.setMinWidth(70);


		btnKaart.setOnAction(this::kiesKaartGeklikt);
		btnFiche.setOnAction(this::kiesFicheGeklikt);
		btnPas.setOnAction(this::pasGeklikt);
		this.getChildren().addAll(lblSpelerAanDeBeurtTekst, lblSpelerAanDeBeurt, lblKeuze, btnKaart, btnFiche, btnPas,
				lblInfoOfFout,
				btnBevestig, btnAnnuleer);

		// index bijhouden om nodes tussen te voegen / verwijderen en later
		// terug toe te voegen
		indexBtnBevestig = this.getChildren().indexOf(btnBevestig);
		indexBtnAnnuleer = this.getChildren().indexOf(btnAnnuleer);

		// alle labels wrappen
		this.getChildren().forEach(node -> {
			if (node instanceof Label) {
				((Label) node).setWrapText(true);
			}
		});
	}

	private void pasGeklikt(ActionEvent e) {
		hs.eindeBeurt();
		toonInfo(String.format(Taal.vertaling("LinkerInfoScherm.8"), dc.geefSpelerAanDeBeurt())); //$NON-NLS-1$
		maakInfoOfFoutLabelLeegNaXSec(4);
	}

	protected void stelVolgendeSpelerIn() {
		lblSpelerAanDeBeurt
				.setText(dc.geefSpelerAanDeBeurt().getGebruikersnaam()); // $NON-NLS-1$
	}

	private void maakInfoOfFoutLabelLeegNaXSec(double seconden) {
		// tijdlijn om boodschap na x seconden te wissen
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(seconden), ev -> {
			lblInfoOfFout.setText(""); //$NON-NLS-1$
		}));
		timeline.play();
	}

	protected void maakInfoOfFoutLabelLeeg() {
		lblInfoOfFout.setText(""); //$NON-NLS-1$
	}

	private void kiesKaartGeklikt(ActionEvent e) {
		maakInfoOfFoutLabelLeeg();
		hs.maakKaartenKlikbaar();
		verbergKeuzeknoppen();
		lblKeuze.setText(Taal.vertaling("LinkerInfoScherm.12")); //$NON-NLS-1$
		btnBevestig.setOnAction((event) -> bevestigGeklikt(event, "kaart")); //$NON-NLS-1$
		btnAnnuleer.setOnAction((event) -> annuleerGeklikt(event, "kaart")); //$NON-NLS-1$
	}

	private void kiesFicheGeklikt(ActionEvent e) {
		maakInfoOfFoutLabelLeeg();
		hs.maakFichesKlikbaar();
		verbergKeuzeknoppen();
		lblKeuze.setText(Taal.vertaling("LinkerInfoScherm.15")); //$NON-NLS-1$
		btnBevestig.setOnAction((event) -> bevestigGeklikt(event, "fiche")); //$NON-NLS-1$
		btnAnnuleer.setOnAction((event) -> annuleerGeklikt(event, "fiche")); //$NON-NLS-1$
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
		btnBevestig.requestFocus();
		btnAnnuleer.setVisible(true);
	}

	protected void deactiveerBevestigKnop() {
		btnBevestig.setVisible(false);
		btnAnnuleer.setVisible(false);
	}

	private void bevestigGeklikt(ActionEvent e, String spelerKeuze) {

		switch (spelerKeuze) {
		case ("kaart") -> { //$NON-NLS-1$
			hs.verplaatsOntwikkelingskaartVanTafelNaarSpeler(gekozenKaart);
			deactiveerBevestigKnop();
		}
		case ("fiche") -> { //$NON-NLS-1$
			List<Edelsteenfiche> teVerplaatsenFiches = new ArrayList<>();
			for (FXEdelsteenFiche ef : edelsteenfiches) {
				teVerplaatsenFiches.add(new Edelsteenfiche(ef.getEdelsteen()));
			}
			deactiveerBevestigKnop();
			hs.verplaatsEdelsteenFichesNaarSpeler(teVerplaatsenFiches);
			hs.maakFichesOnKlikbaar();
		}
		case ("ficheTerug") -> { //$NON-NLS-1$
			maakInfoOfFoutLabelLeeg();
			List<Edelsteenfiche> terugTeGevenFiches = edelsteenficheGeefTerugScherm.geefTerugTeGevenFiches();

			// validatie exact 10 fiches in bezit na teruggave
			try {
				dc.verplaatsEdelsteenfichesVanSpelerNaarSpelNaTeVeelInBezit(terugTeGevenFiches);
				this.getChildren().remove(hboxEdelsteenficheGeefTerugScherm);
				// btnBevestig, btnAnnuleer terug toevoegen op oorspronkelijke plaats, volgorde
				// belangrijk!
				this.getChildren().add(indexBtnBevestig, btnBevestig);
				this.getChildren().add(indexBtnAnnuleer, btnAnnuleer);
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

	private void annuleerGeklikt(ActionEvent e, String spelerkeuze) {
		switch (spelerkeuze) {
		case ("kaart") -> { //$NON-NLS-1$
			gekozenKaart.onLeftClicked();
			hs.maakKaartenOnKlikbaar();
		}
		case ("fiche") -> { //$NON-NLS-1$
			for (FXEdelsteenFiche ef : edelsteenfiches) {
				voegEdelsteenficheTerugToeAanStapel(new Edelsteenfiche(ef.getEdelsteen()));
			}
			verwijderFiches();
			hs.maakFichesOnKlikbaar();
		}
		}

		zetKeuzeMenuTerug();

	}

	protected void zetKeuzeMenuTerug() {
		lblKeuze.setText(Taal.vertaling("LinkerInfoScherm.1")); //$NON-NLS-1$
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
		// volgorde verwijderen belangrijk!
		this.getChildren().remove(indexBtnAnnuleer);
		this.getChildren().remove(btnBevestig);
		activeerBevestigKnop();

		lblKeuze.setText(""); //$NON-NLS-1$

		hboxEdelsteenficheGeefTerugScherm = new HBox();
		hboxEdelsteenficheGeefTerugScherm.setAlignment(Pos.CENTER);

		edelsteenficheGeefTerugScherm = new EdelsteenficheGeefTerugScherm(dc);
		// btnBevestig in hbox stoppen (boven of onder edelsteensteficheGeefTerugScherm
		// was te weinig plaats)
		hboxEdelsteenficheGeefTerugScherm.getChildren().addAll(edelsteenficheGeefTerugScherm, btnBevestig);
		this.getChildren().add(hboxEdelsteenficheGeefTerugScherm);

		btnBevestig.setOnAction((event) -> bevestigGeklikt(event, "ficheTerug")); //$NON-NLS-1$
	}

	protected void maakFichesKlikbaarInEdelsteenficheScherm() {
		hs.maakFichesKlikbaar();

	}

}
