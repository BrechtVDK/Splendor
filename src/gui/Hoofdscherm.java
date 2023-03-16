package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class Hoofdscherm extends GridPane {
	private DomeinController dc;
	private WelkomScherm ws;
	private EdelenScherm edelenScherm;
	private EdelsteenFicheScherm edelsteenFicheScherm;
	private TafelScherm tafelscherm;

	public Hoofdscherm(DomeinController dc, WelkomScherm ws) {
		this.dc = dc;
		this.ws = ws;

		this.setPadding(new Insets(25));
		this.setVgap(25);
		this.setHgap(25);

		edelenScherm = new EdelenScherm(dc);
		edelsteenFicheScherm = new EdelsteenFicheScherm(dc);
		tafelscherm = new TafelScherm(dc);

		this.add(edelenScherm, 1, 0, 5, 1);
		this.add(edelsteenFicheScherm, 0, 0, 1, 3);
		this.add(tafelscherm, 1, 1, 5, 3);
		
		

	}

}
