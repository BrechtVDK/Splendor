package gui;

import java.util.Map;

import domein.DomeinController;
import domein.Niveau;
import domein.Ontwikkelingskaart;
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

		buildGui();
	}

	private void buildGui() {
		geefZichtbareKaartenWeer();
		geefStapelsWeer();
	}

	private void geefZichtbareKaartenWeer() {
		Ontwikkelingskaart[][] kaarten = dc.geefZichtbareOntwikkelingskaarten();

		for (int rij = 0; rij < kaarten.length; rij++) {
			for (int kolom = 0; kolom < kaarten[rij].length; kolom++) {
				FXOntwikkelingskaart kaart = new FXOntwikkelingskaart(kaarten[rij][kolom]);
				this.add(kaart, kolom + 1, rij);
			}
		}

	}

	private void geefStapelsWeer() {
		Map<Niveau, Integer> aantalResterendeKaarten = dc.geefAantalResterendeKaarten();
		lblStapels = new Label[Niveau.values().length];
		for (int i = 0; i < lblStapels.length; i++) {
			StringBuilder aantal = new StringBuilder(Integer.toString(aantalResterendeKaarten.get(Niveau.values()[i])));
			aantal.append("\n").append(" • ".repeat(lblStapels.length - i));
			lblStapels[i] = new Label(aantal.toString());
			lblStapels[i].setAlignment(Pos.BOTTOM_CENTER);
			lblStapels[i].setMinHeight(150);
			lblStapels[i].setMaxHeight(150);
			lblStapels[i].setMinWidth(100);
			lblStapels[i].setMaxWidth(100);
			lblStapels[i].getStyleClass().add(String.format("stapelniveau%d", i + 1));
			lblStapels[i].getStyleClass().add("stapel");
			this.add(lblStapels[i], 0, i);
		}

	}

}
