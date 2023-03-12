package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EdelenScherm extends HBox {
	private DomeinController dc;

	private Label lblEdele1;
	private Label lblEdele2;
	private Label lblEdele3;
	private Label lblEdele4;
	private Label lblEdele5;

	public EdelenScherm(DomeinController dc) {
		this.dc = dc;
		this.setSpacing(25);



		List<Label> edelen = new ArrayList<>();

		for (int i = 0; i < dc.geefAantalSpelers() + 1; i++) {
			edelen.add(new Label(String.format("Edele%d", i + 1)));
		}

		for (Label edele : edelen) {
			edele.setMaxWidth(100);
			edele.setPrefSize(500, 100);
			this.getChildren().add(edele);
			edele.getStyleClass().add("edele");
		}
	}

}
