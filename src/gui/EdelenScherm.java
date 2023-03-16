package gui;

import domein.DomeinController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EdelenScherm extends HBox {
	private DomeinController dc;
	private Label[] lblEdelen;

	public EdelenScherm(DomeinController dc) {
		this.dc = dc;
		this.setSpacing(25);

		lblEdelen = new Label[dc.geefAantalSpelers() + 1];

		for (int i = 0; i < lblEdelen.length; i++) {
			lblEdelen[i] = new Label(String.format("Edele%d", i + 1));
			lblEdelen[i].setAlignment(Pos.CENTER);
			lblEdelen[i].setMaxWidth(100);
			lblEdelen[i].setPrefSize(500, 100);
			lblEdelen[i].getStyleClass().add("edele");
		}

		this.getChildren().addAll(lblEdelen);

	}

}
