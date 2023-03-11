package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class EdelenScherm extends HBox {
	private DomeinController dc;

	private Label lblEdele1;
	private Label lblEdele2;
	private Label lblEdele3;
	private Label lblEdele4;
	private Label lblEdele5;

	public EdelenScherm(DomeinController dc, int aantalSpelers) {
		this.dc = dc;
		this.setSpacing(10);

		Region spring = new Region();
		HBox.setHgrow(spring, Priority.ALWAYS);

		List<Label> edelen = new ArrayList<>();

		for (int i = 0; i < aantalSpelers + 1; i++) {
			edelen.add(new Label(String.format("Edele%d", i + 1)));
		}

		for (Label edele : edelen) {
			edele.setMaxWidth(100);
			edele.setPrefSize(500, 150);
			this.getChildren().add(edele);
			edele.getStyleClass().add("edele");
		}
	}

}
