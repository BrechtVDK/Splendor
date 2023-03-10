package gui;

import domein.DomeinController;
import javafx.scene.layout.GridPane;

public class Hoofdscherm extends GridPane {
	private DomeinController dc;

	public Hoofdscherm(DomeinController dc) {
		this.dc = dc;
	}

}
