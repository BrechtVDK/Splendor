package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class Hoofdscherm extends GridPane {
	private DomeinController dc;
	private EdelenScherm edelenScherm;
	private EdelsteenFicheScherm edelsteenFicheScherm;
	private TafelScherm tafelscherm;
	// jonas: aantalspelers voorlopig vastgezet op 4
	private int aantalSpelers = 4;

	public Hoofdscherm(DomeinController dc) {
		this.dc = dc;
		
		dc.startNieuwSpel();
		dc.voegSpelerToeAanSpel("Jonas", 1989);
		dc.voegSpelerToeAanSpel("Brecht", 1993);

		dc.voegSpelerToeAanSpel("David", 1975);
		dc.voegSpelerToeAanSpel("Sonia", 1985);
		dc.organiseerSpelVolgensHetAantalSpelers();

		this.setPrefSize(1080, 800);

		this.setPadding(new Insets(25));
		this.setVgap(25);
		this.setHgap(25);

		edelenScherm = new EdelenScherm(dc, aantalSpelers);
		edelsteenFicheScherm = new EdelsteenFicheScherm(dc, aantalSpelers);
		tafelscherm = new TafelScherm(dc);

		this.add(edelenScherm, 1, 0, 5, 1);
		this.add(edelsteenFicheScherm, 0, 0, 1, 3);
		this.add(tafelscherm, 1, 1, 5, 3);
		
		

	}

}
