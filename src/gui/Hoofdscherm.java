package gui;

import java.util.Arrays;
import java.util.List;

import Exceptions.TeVeelFichesInBezitException;
import domein.DomeinController;
import domein.Edelsteenfiche;
import domein.Ontwikkelingskaart;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Hoofdscherm extends GridPane {
	private DomeinController dc;
	private WelkomScherm ws;
	private EdelenScherm edelenScherm;
	private EdelsteenFicheScherm edelsteenFicheScherm;
	private TafelScherm tafelscherm;
	private LinkerInfoScherm linkerInfoScherm;
	private ScoreBordScherm scoreBordScherm;

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

		this.add(linkerInfoScherm, 0, 1, 1, 2);
		this.add(edelenScherm, 2, 0);
		this.add(edelsteenFicheScherm, 1, 1);
		this.add(tafelscherm, 2, 1);
		this.add(scoreBordScherm, 3, 0, 1, 2);

		tafelscherm.maakKaartenOnKlikbaar();
		edelsteenFicheScherm.maakFichesOnklikbaar();
		;
	}

	public void bepaalVolgendeSpeler() {
		dc.bepaalVolgendeSpeler();
		scoreBordScherm.markeerVolgendeSpeler();
		linkerInfoScherm.stelVolgendeSpelerIn();
	}

	// alles in verband met Ontwikkelingskaarten:
	public void verplaatsKaartNaarLinkerInfoScherm(FXOntwikkelingskaart kaart) {
		linkerInfoScherm.voegOntwikkelingskaartToe(kaart);
	}

	public void maakKaartenKlikbaar() {
		tafelscherm.maakKaartenKlikbaar();
	}

	public void maakKaartenOnKlikbaar() {
		tafelscherm.maakKaartenOnKlikbaar();
	}

	public void verplaatsOntwikkelingskaartVanTafelNaarSpeler(FXOntwikkelingskaart fxKaart) {
		try {
			int[] indexKaart = fxKaart.getIndex();
			/*
			 * dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
			 * tafelscherm.geefOntwikkelingskaartVolgensIndex(indexKaart));
			 */
			// Brecht: getter toegevoegd aan FXOntwikkelingskaart
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(fxKaart.getKaart());
			voegEdelsteenfichesTerugToeAanStapels(Arrays.asList(fxKaart.getKaart().edelsteenfiches()));

			Ontwikkelingskaart nieuweKaart = dc.geefNieuweKaartVanStapel(indexKaart[1], indexKaart[0]);
			if (nieuweKaart != null) {
				tafelscherm.legNieuweKaartOpTafel(nieuweKaart, indexKaart);
			}
			linkerInfoScherm.verwijderKaart(fxKaart);
			bepaalVolgendeSpeler();

		} catch (IllegalArgumentException e) {
			linkerInfoScherm.toonFoutmelding(e.getMessage());
			tafelscherm.voegFouteKaartTerugToeVanLinkerInfoScherm(fxKaart);
			linkerInfoScherm.deactiveerBevestigKnop();
			// linkerInfoScherm.verwijderBevestigKnop();
		}
		linkerInfoScherm.zetKeuzeMenuTerug();
	}

	// alles in verband met fiches:
	public void maakFichesKlikbaar() {
		edelsteenFicheScherm.maakFichesKlikbaar();
	}

	public void maakFichesOnKlikbaar() {
		edelsteenFicheScherm.maakFichesOnklikbaar();
	}

	public void voegEdelsteenficheToeAanLinkerInfoScherm(FXEdelsteenFiche edelsteenfiche) {
		linkerInfoScherm.voegFicheToe(edelsteenfiche);
	}

	public void verplaatsEdelsteenFichesNaarSpeler(List<Edelsteenfiche> edelsteenfiches) {
		try {
			dc.verplaatsEdelsteenfichesNaarSpeler(edelsteenfiches);
			linkerInfoScherm.verwijderFiches();
			bepaalVolgendeSpeler();
		} catch (TeVeelFichesInBezitException e) {
			linkerInfoScherm.toonFoutmelding(e.getMessage());
			linkerInfoScherm.verwijderFiches();
			linkerInfoScherm.zetKlaarOmFichesTerugTeGeven();

		} catch (IllegalArgumentException e) {
			voegEdelsteenfichesTerugToeAanStapels(edelsteenfiches);
			linkerInfoScherm.verwijderFiches();
			linkerInfoScherm.toonFoutmelding(e.getMessage());
		}

		linkerInfoScherm.zetKeuzeMenuTerug();
	}

	public void voegEdelsteenfichesTerugToeAanStapels(List<Edelsteenfiche> edelsteenfiches) {
		for (Edelsteenfiche ef : edelsteenfiches) {
			edelsteenFicheScherm.voegEdelsteenficheTerugToe(ef);
		}
	}

	public void verplaatsEdelsteenFichesVanSpelerNaarSpel(List<Edelsteenfiche> edelsteenfiches) {
		try {
			dc.verplaatsEdelsteenfichesVanSpelerNaarSpel(edelsteenfiches);
		} catch (IllegalArgumentException e) {
			linkerInfoScherm.toonFoutmelding(e.getMessage());
		}
	}


}