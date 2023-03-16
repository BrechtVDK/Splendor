package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EdelenScherm extends HBox {
	private DomeinController dc;
	private Label[] lblEdelen;

	public EdelenScherm(DomeinController dc) {
		this.dc = dc;
		this.setSpacing(25);

		List<Label> edelen = new ArrayList<>();

		lblEdelen = new Label[dc.geefAantalSpelers() + 1];

		for (int i = 0; i < lblEdelen.length; i++) {
			lblEdelen[i] = new Label(String.format("Edele%d", i + 1));
			lblEdelen[i].setMaxWidth(100);
			lblEdelen[i].setPrefSize(500, 100);
			lblEdelen[i].getStyleClass().add("edele");
		}
		this.getChildren().addAll(lblEdelen);
		/*
		 * for (Label edele : edelen) { edele.setMaxWidth(100); edele.setPrefSize(500,
		 * 100); this.getChildren().add(edele); edele.getStyleClass().add("edele"); }
		 */
	}

}
