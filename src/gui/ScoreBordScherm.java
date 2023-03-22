package gui;

import domein.DomeinController;
import domein.Speler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ScoreBordScherm extends VBox {
	private DomeinController dc;
	private double breedte;

	public ScoreBordScherm(DomeinController dc, double breedte) {
		this.dc = dc;
		this.breedte = breedte / 5;
		buildGui();
	}

	private void buildGui() {
		this.setMinWidth(breedte);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(2);
		for (Speler sp : dc.geefSpelers()) {
			this.getChildren().add(new SpelerScoreScherm(dc, sp, breedte));
		}

	}

}
