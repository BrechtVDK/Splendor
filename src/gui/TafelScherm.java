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
		geefHuidigeTafelWeer();





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

		this.add(lblStapelNiveau3, 0, 0);
		this.add(lblStapelNiveau2, 0, 1);
		this.add(lblStapelNiveau1, 0, 2);

	}

	private void geefHuidigeTafelWeer() {
		String[][] kaarten = dc.geefZichtbareOntwikkelingskaarten();

		for (int rij = 0; rij < kaarten.length; rij++) {
			for (int kolom = 0; kolom < kaarten[rij].length; kolom++) {
				this.add(new FXOntwikkelingskaart(kaarten[rij][kolom]), kolom + 1, rij);
			}
		}

	}
}
