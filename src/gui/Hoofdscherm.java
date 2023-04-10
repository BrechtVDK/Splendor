package gui;

import java.util.List;

import Exceptions.TeVeelFichesInBezitException;
import domein.DomeinController;
import domein.Edele;
import domein.Edelsteenfiche;
import domein.Ontwikkelingskaart;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Hoofdscherm extends GridPane {
	private DomeinController dc;
	private WelkomScherm ws;
	private EdelenScherm edelenScherm;
	private EdelsteenFicheScherm edelsteenFicheScherm;
	private TafelScherm tafelscherm;
	private LinkerInfoScherm linkerInfoScherm;
	private ScoreBordScherm scoreBordScherm;
	private FXTaalKeuze chBoxTaalKeuze;

	public Hoofdscherm(DomeinController dc, WelkomScherm ws) {
		this.dc = dc;
		this.ws = ws;

		buildGui();

	}

	private void buildGui() {
		// this.getStyleClass().add("hoofdscherm");

		this.setPadding(new Insets(5));
		this.setVgap(25);
		this.setHgap(25);
		this.setAlignment(Pos.CENTER);
		linkerInfoScherm = new LinkerInfoScherm(dc, this);
		edelenScherm = new EdelenScherm(dc);
		edelsteenFicheScherm = new EdelsteenFicheScherm(dc, this);
		tafelscherm = new TafelScherm(dc, this);
		scoreBordScherm = new ScoreBordScherm(dc);
		chBoxTaalKeuze = new FXTaalKeuze();

		this.add(chBoxTaalKeuze, 0, 0);
		this.add(linkerInfoScherm, 0, 1, 1, 2);
		this.add(edelenScherm, 2, 0);
		this.add(edelsteenFicheScherm, 1, 1);
		this.add(tafelscherm, 2, 1);
		this.add(scoreBordScherm, 3, 0, 1, 2);
		tafelscherm.maakKaartenOnKlikbaar();
		edelsteenFicheScherm.maakFichesOnklikbaar();

	}

	protected void bepaalVolgendeSpeler() {

		dc.bepaalVolgendeSpeler();
		scoreBordScherm.markeerVolgendeSpeler();
		linkerInfoScherm.stelVolgendeSpelerIn();
	}

	// alles in verband met Ontwikkelingskaarten:
	protected void verplaatsKaartNaarLinkerInfoScherm(FXOntwikkelingskaart kaart) {
		linkerInfoScherm.voegOntwikkelingskaartToe(kaart);
	}

	protected void maakKaartenKlikbaar() {
		tafelscherm.maakKaartenKlikbaar();
	}

	protected void maakKaartenOnKlikbaar() {
		tafelscherm.maakKaartenOnKlikbaar();
	}

	protected void verplaatsOntwikkelingskaartVanTafelNaarSpeler(FXOntwikkelingskaart fxKaart) {
		try {
			// index gridpane:[kol][rij]
			int[] indexKaart = fxKaart.getIndex();
			// Brecht: getter toegevoegd aan FXOntwikkelingskaart
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(fxKaart.getKaart());
			// index tafel: [rij][kol]
			Ontwikkelingskaart nieuweKaart = dc.geefNieuweKaartVanStapel(indexKaart[1], indexKaart[0]);
			if (nieuweKaart != null) {
				tafelscherm.legNieuweKaartOpTafel(nieuweKaart, indexKaart);
			}
			linkerInfoScherm.verwijderKaart(fxKaart);
			eindeBeurt();
		} catch (IllegalArgumentException e) {
			linkerInfoScherm.toonFoutmelding(e.getMessage());
			tafelscherm.voegFouteKaartTerugToeVanLinkerInfoScherm(fxKaart);
			linkerInfoScherm.deactiveerBevestigKnop();
			linkerInfoScherm.zetKeuzeMenuTerug();
		}

	}

	// alles in verband met fiches:
	protected void maakFichesKlikbaar() {
		edelsteenFicheScherm.maakFichesKlikbaar();
	}

	protected void maakFichesOnKlikbaar() {
		edelsteenFicheScherm.maakFichesOnklikbaar();
	}

	protected void voegEdelsteenficheToeAanLinkerInfoScherm(FXEdelsteenFiche edelsteenfiche) {
		linkerInfoScherm.voegFicheToe(edelsteenfiche);
	}

	protected void verplaatsEdelsteenFichesNaarSpeler(List<Edelsteenfiche> edelsteenfiches) {
		try {
			dc.verplaatsEdelsteenfichesNaarSpeler(edelsteenfiches);
			linkerInfoScherm.verwijderFiches();
			eindeBeurt();
		} catch (TeVeelFichesInBezitException e) {
			linkerInfoScherm.verwijderFiches();
			linkerInfoScherm.toonFoutmelding(e.getMessage());
			linkerInfoScherm.zetKlaarOmFichesTerugTeGeven();
		} catch (IllegalArgumentException e) {
			voegEdelsteenfichesTerugToeAanStapels(edelsteenfiches);
			linkerInfoScherm.verwijderFiches();
			linkerInfoScherm.toonFoutmelding(e.getMessage());
			linkerInfoScherm.zetKeuzeMenuTerug();
		}
	}

	protected void voegEdelsteenfichesTerugToeAanStapels(List<Edelsteenfiche> edelsteenfiches) {
		for (Edelsteenfiche ef : edelsteenfiches) {
			edelsteenFicheScherm.voegEdelsteenficheTerugToe(ef);
		}
	}

	// einde beurt = controle DR_BEURT_SPECIALE_TEGEL en controle op einde ronde
	protected void eindeBeurt() {
		List<Edele> edelen = dc.geefBeschikbareEdelen();
		if (!edelen.isEmpty()) {
			edelenScherm.markeerEnMaakBeschikbareEdelenKlikbaar(edelen);
			linkerInfoScherm.verbergKeuzeknoppen();
			linkerInfoScherm.toonInfo("Selecteer één beschikbare edele!");
		} else {
			controleEindeRonde();
		}
	}

	// einde ronde = controle DR_SPEL_EINDE
	protected void controleEindeRonde() {
		if (dc.geefSpelerAanDeBeurt().equals(dc.geefLaatsteSpelerVanRonde()) && dc.isEindeSpel()) {
			EindeSpelScherm eindespelscherm = new EindeSpelScherm(dc.geefNamenWinnaars(), this);
			Scene scene = new Scene(eindespelscherm, 400, 400);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Einde spel");
			stage.setResizable(false);
			scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
			// kruisje klikken = volledige applicatie afsluiten
			stage.setOnCloseRequest(event -> Platform.exit());
			stage.show();

		} else {
			bepaalVolgendeSpeler();
			linkerInfoScherm.zetKeuzeMenuTerug();
		}

	}

	protected void maakInfoOfFoutLabelLeeg() {
		linkerInfoScherm.maakInfoOfFoutLabelLeeg();
	}

}