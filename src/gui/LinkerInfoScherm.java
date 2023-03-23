package gui;

import domein.DomeinController;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class LinkerInfoScherm extends VBox {
	private DomeinController dc;

	public LinkerInfoScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);

	}
}
