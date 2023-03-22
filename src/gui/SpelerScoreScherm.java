package gui;

import domein.DomeinController;
import domein.Speler;
import javafx.scene.layout.GridPane;

public class SpelerScoreScherm extends GridPane {
	private DomeinController dc;
	private Speler sp;
	private double minBreedte;

	public SpelerScoreScherm(DomeinController dc, Speler sp, double minBreedte) {

		this.dc = dc;
		this.sp = sp;
		this.minBreedte = minBreedte;
	}

}
