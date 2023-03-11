package gui;

import domein.DomeinController;
import javafx.scene.layout.GridPane;

public class TafelScherm extends GridPane {
	private DomeinController dc;

	public TafelScherm(DomeinController dc) {
		this.dc = dc;
	}

}
