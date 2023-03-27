package gui;


import domein.DomeinController;
import domein.Speler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ScoreBordScherm extends VBox {
	private DomeinController dc;

	// private double breedte;
	// Brecht: breedte zou ik weglaten. Als je het in commentaar zet zie ik geen
	// verschil
	// zie:
	// https://docs.oracle.com/javafx/2/api/javafx/scene/layout/Region.html#setMinWidth(double)
	public ScoreBordScherm(DomeinController dc, double breedte) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		this.setAlignment(Pos.CENTER);
		// Brecht: aangepast naar 25
		this.setSpacing(25);
		for (Speler speler : dc.geefSpelers()) {
			SpelerScoreScherm spelerScherm = new SpelerScoreScherm(dc, speler);
			this.getChildren().add(spelerScherm);
		}

		// voorlopig om te changeren van speler
		Button btnVolgendeSpeler = new Button("Volgende speler");
		btnVolgendeSpeler.minWidth(150);
		btnVolgendeSpeler.setOnAction(this::volgendeSpelerGeklikt);
		this.getChildren().add(btnVolgendeSpeler);

	}

	private void volgendeSpelerGeklikt(ActionEvent e) {
		dc.bepaalVolgendeSpeler();
		// buildgui verdubbelt de huidge gui constant ==> niet te de bedoeling. Later op
		// te lossen via databinding
		this.getChildren().clear();
		buildGui();
	}

}
