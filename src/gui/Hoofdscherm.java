package gui;

import domein.DomeinController;
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
		this.getStyleClass().add("hoofddscherm");

		// this.setPadding(new Insets(25));
		this.setVgap(25);
		this.setHgap(25);
		this.setAlignment(Pos.CENTER);
		linkerInfoScherm = new LinkerInfoScherm(dc, this);
		edelenScherm = new EdelenScherm(dc);
		edelsteenFicheScherm = new EdelsteenFicheScherm(dc);
		tafelscherm = new TafelScherm(dc, this);
		scoreBordScherm = new ScoreBordScherm(dc);


		this.add(linkerInfoScherm, 0, 1, 1, 2);
		this.add(edelenScherm, 2, 0);
		this.add(edelsteenFicheScherm, 1, 1);
		this.add(tafelscherm, 2, 1);
		this.add(scoreBordScherm, 3, 0, 1, 2);

		tafelscherm.setDisable(true);
	}


	public void bepaalVolgendeSpeler() {
		dc.bepaalVolgendeSpeler();
		scoreBordScherm.markeerVolgendeSpeler();
	}

	public void verplaatsKaartNaarLinkerInfoScherm(FXOntwikkelingskaart kaart) {
		linkerInfoScherm.voegOntwikkelingskaartToe(kaart);
	}


	public void maakKaartenKlikbaar() {
		tafelscherm.maakKaartenKlikbaar();
	}

	public void maakKaartenOnKlikbaar() {
		tafelscherm.maakKaartenOnKlikbaar();
	}

	public void verplaatsOntwikkelingskaartVanTafelNaarSpeler(FXOntwikkelingskaart kaart) {
		try {
			int[] indexKaart = kaart.getIndex();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					tafelscherm.geefOntwikkelingskaartVolgensIndex(indexKaart));
			tafelscherm.legNieuweKaartOpTafel(dc.geefNieuweKaartVanStapel(indexKaart[1], indexKaart[0]), indexKaart);

		} catch (IllegalArgumentException e) {
			linkerInfoScherm.toonFoutmelding(e.getMessage());
			tafelscherm.voegFouteKaartTerugToeVanLinkerInfoScherm(kaart);
			linkerInfoScherm.verwijderOntwikkelingskaart();
		}
	}

}