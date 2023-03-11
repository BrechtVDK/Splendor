package gui;

import domein.DomeinController;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EdelsteenFicheScherm extends VBox {
	private DomeinController dc;
	// Jonas: om te testen voorlopig aantalspelers is 4, moet uiteindelijk uit
	// domein komen, zie constructor
	private int aantalspelers = 4;

	private Button btnDiamant;
	private Button btnRobijn;
	private Button btnSaffier;
	private Button btnSmaragd;
	private Button btnOnyx;

	public EdelsteenFicheScherm(DomeinController dc, int aantalSpelers) {
		this.dc = dc;

		this.setSpacing(10);
		

		btnDiamant = new Button("Diamant");
		btnRobijn = new Button("Robijn");
		btnSaffier = new Button("Saffier");
		btnSmaragd = new Button("Smaragd");
		btnOnyx = new Button("Onyx");

		stelOpmaakFichesIn();



		this.getChildren().add(btnDiamant);
		this.getChildren().add(btnRobijn);
		this.getChildren().add(btnSaffier);
		this.getChildren().add(btnSmaragd);
		this.getChildren().add(btnOnyx);
	}

	private void stelOpmaakFichesIn() {
		btnDiamant.setId("diamant");
		btnDiamant.getStyleClass().add("edelsteenfiche");
		btnDiamant.setPrefSize(100, 100);

		btnRobijn.setId("robijn");
		btnRobijn.getStyleClass().add("edelsteenfiche");
		btnRobijn.setPrefSize(100, 100);

		btnSaffier.setId("saffier");
		btnSaffier.getStyleClass().add("edelsteenfiche");
		btnSaffier.setPrefSize(100, 100);

		btnSmaragd.setId("smaragd");
		btnSmaragd.getStyleClass().add("edelsteenfiche");
		btnSmaragd.setPrefSize(100, 100);

		btnOnyx.setId("onyx");
		btnOnyx.getStyleClass().add("edelsteenfiche");
		btnOnyx.setPrefSize(100, 100);
	}

}
