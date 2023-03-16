package gui;

import domein.DomeinController;
import domein.Niveau;
import domein.OntwikkelingskaartRecord;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TafelScherm extends GridPane {
	private DomeinController dc;
	private Label[] lblStapels;

	public TafelScherm(DomeinController dc) {
		this.dc = dc;

		this.setVgap(25);
		this.setHgap(25);

		lblStapels = new Label[Niveau.values().length];
		for (int i = 0; i < lblStapels.length; i++) {
			lblStapels[i] = new Label("• ".repeat(lblStapels.length - i));
			lblStapels[i].setAlignment(Pos.BOTTOM_CENTER);
			lblStapels[i].setPrefSize(500, 100);
			lblStapels[i].setMaxWidth(100);
			lblStapels[i].getStyleClass().add("ontwikkelingskaart");
			this.add(lblStapels[i], 0, i);
		}

		geefHuidigeTafelWeer();

	}

	private void geefHuidigeTafelWeer() {
		OntwikkelingskaartRecord[][] kaarten = dc.geefZichtbareOntwikkelingskaarten();

		for (int rij = 0; rij < kaarten.length; rij++) {
			for (int kolom = 0; kolom < kaarten[rij].length; kolom++) {
				this.add(new FXOntwikkelingskaart(kaarten[rij][kolom]), kolom + 1, rij);
			}
		}

	}
}
