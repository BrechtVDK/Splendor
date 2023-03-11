package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EdelsteenFicheScherm extends VBox {
	private DomeinController dc;
	// Jonas: om te testen voorlopig aantalspelers is 4, moet uiteindelijk uit
	// domein komen, zie constructor
	private int aantalspelers = 4;

	private Label lblDiamant;
	private Label lblRobijn;
	private Label lblSaffier;
	private Label lblSmaragd;
	private Label lblOnyx;

	public EdelsteenFicheScherm(DomeinController dc, int aantalSpelers) {
		this.dc = dc;

		this.setSpacing(10);
		

		lblDiamant = new Label("Diamant");
		lblRobijn = new Label("Robijn");
		lblSaffier = new Label("Saffier");
		lblSmaragd = new Label("Smaragd");
		lblOnyx = new Label("Onyx");

		stelOpmaakFichesIn();



		this.getChildren().add(lblDiamant);
		this.getChildren().add(lblRobijn);
		this.getChildren().add(lblSaffier);
		this.getChildren().add(lblSmaragd);
		this.getChildren().add(lblOnyx);
	}

	private void stelOpmaakFichesIn() {
		lblDiamant.setId("diamant");
		lblDiamant.getStyleClass().add("edelsteenfiche");
		lblDiamant.setPrefSize(100, 100);

		lblRobijn.setId("robijn");
		lblRobijn.getStyleClass().add("edelsteenfiche");
		lblRobijn.setPrefSize(100, 100);

		lblSaffier.setId("saffier");
		lblSaffier.getStyleClass().add("edelsteenfiche");
		lblSaffier.setPrefSize(100, 100);

		lblSmaragd.setId("smaragd");
		lblSmaragd.getStyleClass().add("edelsteenfiche");
		lblSmaragd.setPrefSize(100, 100);

		lblOnyx.setId("onyx");
		lblOnyx.getStyleClass().add("edelsteenfiche");
		lblOnyx.setPrefSize(100, 100);
	}

}
