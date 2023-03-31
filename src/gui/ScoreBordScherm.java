package gui;

import java.util.ArrayList;

import domein.DomeinController;
import domein.Edelsteen;
import domein.Edelsteenfiche;
import domein.Ontwikkelingskaart;
import domein.Speler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ScoreBordScherm extends VBox {
	private DomeinController dc;
	private SpelerScoreScherm[] spelerScoreSchermen;

	public ScoreBordScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	public SpelerScoreScherm[] getSpelerScoreSchermen() {
		return spelerScoreSchermen;
	}

	private void buildGui() {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(25);
		this.spelerScoreSchermen = new SpelerScoreScherm[dc.geefAantalSpelers()];
		int i = 0;
		for (Speler speler : dc.geefSpelers()) {
			spelerScoreSchermen[i] = new SpelerScoreScherm(dc, speler);
			this.getChildren().add(spelerScoreSchermen[i++]);
		}

		// voorlopig om te changeren van speler
		Button btnVolgendeSpeler = new Button("Volgende speler");
		btnVolgendeSpeler.minWidth(150);
		btnVolgendeSpeler.setOnAction(this::volgendeSpelerGeklikt);
		this.getChildren().add(btnVolgendeSpeler);

	}

	private void volgendeSpelerGeklikt(ActionEvent e) {

		// BEGIN binding testen
		ArrayList<Edelsteenfiche> lijst = new ArrayList<>();
		// 10 fiches van elk toevoegen
		for (int i = 0; i < 10; i++) {
			for (Edelsteen ed : Edelsteen.values()) {
				lijst.add(new Edelsteenfiche(ed));
			}
		}
		// Exceptions staan momenteel in commentaar om te testen!
		dc.verplaatsEdelsteenfichesNaarSpeler(lijst);
		Ontwikkelingskaart kaart = dc.geefZichtbareOntwikkelingskaarten()[1][0];
		dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(kaart);


		// EINDE testen binding

	}

	public void markeerVolgendeSpeler() {
		// achtergrond scorebord speler aan de beurt aanpassen
		for (SpelerScoreScherm sss : spelerScoreSchermen) {
			sss.getStyleClass().clear();
			sss.getStyleClass().add("scoreKaart");
			sss.getStyleClass().add(
					String.format("%s", sss.isSpelerAanDeBeurt() ? "scoreKaartAanBeurt" : "scoreKaartNietAanBeurt"));
		}
	}
}
