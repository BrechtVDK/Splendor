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
		linkerInfoScherm = new LinkerInfoScherm(dc);
		edelenScherm = new EdelenScherm(dc);
		edelsteenFicheScherm = new EdelsteenFicheScherm(dc);
		tafelscherm = new TafelScherm(dc);


		this.add(linkerInfoScherm, 0, 0, 1, 5);
		this.add(edelenScherm, 2, 0, 5, 1);
		this.add(edelsteenFicheScherm, 1, 1, 1, 3);
		this.add(tafelscherm, 2, 1, 5, 3);
	
	}
}