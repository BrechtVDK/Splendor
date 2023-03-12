package gui;

import java.util.List;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class Hoofdscherm extends GridPane {
	private DomeinController dc;
	private WelkomScherm ws;
	private EdelenScherm edelenScherm;
	private EdelsteenFicheScherm edelsteenFicheScherm;
	private TafelScherm tafelscherm;
	// jonas: aantalspelers voorlopig vastgezet op 4
	// Brecht: vervangen door lijst van spelers (komende van
	// SpelerRegistratieScherm)
	// private int aantalSpelers = 4;
	private List<String> spelers;


	public Hoofdscherm(DomeinController dc, WelkomScherm ws, List<String> spelers) {
		this.dc = dc;
		this.ws = ws;
		this.spelers = spelers;
		
		dc.organiseerSpelVolgensHetAantalSpelers();

		// Brecht: Fullscreen ingesteld in StartUpGui
		// this.setPrefSize(1080, 800);

		this.setPadding(new Insets(25));
		this.setVgap(25);
		this.setHgap(25);

		// aantalSpelers geschrapt, --> methode dc.geefAantalSpelers()
		edelenScherm = new EdelenScherm(dc);
		edelsteenFicheScherm = new EdelsteenFicheScherm(dc);
		tafelscherm = new TafelScherm(dc);

		this.add(edelenScherm, 1, 0, 5, 1);
		this.add(edelsteenFicheScherm, 0, 0, 1, 3);
		this.add(tafelscherm, 1, 1, 5, 3);
		
		

	}

}
