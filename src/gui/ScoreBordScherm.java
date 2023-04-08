package gui;

import java.util.ArrayList;
import java.util.Arrays;

import domein.DomeinController;
import domein.Edelsteen;
import domein.Edelsteenfiche;
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

	protected SpelerScoreScherm[] getSpelerScoreSchermen() {
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
		Button btnVolgendeSpeler = new Button("5x2fiches toevoegen");
		btnVolgendeSpeler.minWidth(150);
		btnVolgendeSpeler.setOnAction(this::vijfx2fichesToevoegen);
		this.getChildren().add(btnVolgendeSpeler);

	}

	private void vijfx2fichesToevoegen(ActionEvent e) {

		// BEGIN binding testen
		// MAX 10 FICHES IN KLASSE SPELER STAAT NOG IN COMMENTAAR!
		// 5x2 fiches van elk toevoegen
		for (Edelsteen ed : Edelsteen.values()) {
			dc.verplaatsEdelsteenfichesNaarSpeler(
					new ArrayList<>(Arrays.asList(new Edelsteenfiche(ed), new Edelsteenfiche(ed))));
		}

		// EINDE testen binding

	}

	protected void markeerVolgendeSpeler() {
		// achtergrond scorebord speler aan de beurt aanpassen
		for (SpelerScoreScherm sss : spelerScoreSchermen) {
			sss.getStyleClass().clear();
			sss.getStyleClass().add("scoreKaart");
			sss.getStyleClass().add(
					String.format("%s", sss.isSpelerAanDeBeurt() ? "scoreKaartAanBeurt" : "scoreKaartNietAanBeurt"));
		}
	}
}
