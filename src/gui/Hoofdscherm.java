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
		
		this.setPrefSize(1080, 800);

		this.setPadding(new Insets(25));

		edelenScherm = new EdelenScherm(dc, aantalSpelers);
		edelsteenFicheScherm = new EdelsteenFicheScherm(dc, aantalSpelers);
		tafelscherm = new TafelScherm(dc);

		this.add(edelenScherm, 1, 0, 5, 1);
		this.add(edelsteenFicheScherm, 0, 1, 1, 3);
		this.add(tafelscherm, 1, 1, 5, 3);
		
		

	}

}
