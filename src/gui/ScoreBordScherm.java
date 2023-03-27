package gui;

import domein.DomeinController;
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
		dc.bepaalVolgendeSpeler();
		// this.getChildren().clear();
		// buildGui();
		// niet perfect, maar toch performanter
		for (SpelerScoreScherm sss : spelerScoreSchermen) {
			sss.getStyleClass().clear();
			sss.getStyleClass().add("scoreKaart");
			sss.getStyleClass().add(
					String.format("%s", sss.isSpelerAanDeBeurt() ? "scoreKaartAanBeurt" : "scoreKaartNietAanBeurt"));
		}
	}
}
