package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TafelScherm extends GridPane {
	private DomeinController dc;

	private Label lblStapelNiveau1;
	private Label lblStapelNiveau2;
	private Label lblStapelNiveau3;

	public TafelScherm(DomeinController dc) {
		this.dc = dc;

		this.setVgap(25);
		this.setHgap(25);

		stelOpmaakStapelsIn();

		String[] test = { "test", "test", "test" };

		this.add(new FXOntwikkelingskaart("1", "robijn", test), 1, 0);
		this.add(new FXOntwikkelingskaart("0", "diamant", test), 2, 0);

	}

	private void stelOpmaakStapelsIn() {
		lblStapelNiveau1 = new Label("*");
		lblStapelNiveau2 = new Label("* *");
		lblStapelNiveau3 = new Label("* * *");

		lblStapelNiveau1.setPrefSize(500, 100);
		lblStapelNiveau2.setPrefSize(500, 100);
		lblStapelNiveau3.setPrefSize(500, 100);

		lblStapelNiveau1.setMaxWidth(100);
		lblStapelNiveau2.setMaxWidth(100);
		lblStapelNiveau3.setMaxWidth(100);

		lblStapelNiveau1.getStyleClass().add("ontwikkelingskaart");
		lblStapelNiveau2.getStyleClass().add("ontwikkelingskaart");
		lblStapelNiveau3.getStyleClass().add("ontwikkelingskaart");

		this.add(lblStapelNiveau1, 0, 0);
		this.add(lblStapelNiveau2, 0, 1);
		this.add(lblStapelNiveau3, 0, 2);
	}

}
