package gui;


import domein.DomeinController;
import domein.Speler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ScoreBordScherm extends VBox {
	private DomeinController dc;
	private double breedte;

	public ScoreBordScherm(DomeinController dc, double breedte) {
		this.dc = dc;
		this.breedte = breedte / 5;
		buildGui();
	}

	private void buildGui() {
		this.setMinWidth(breedte);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(2);
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
		buildGui();
	}

}
