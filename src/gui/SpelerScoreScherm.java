package gui;


import domein.DomeinController;
import domein.Speler;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpelerScoreScherm extends GridPane {
	private DomeinController dc;
	private Speler sp;
	private double minBreedte;

	public SpelerScoreScherm(DomeinController dc, Speler sp, double minBreedte) {

		this.dc = dc;
		this.sp = sp;
		this.minBreedte = minBreedte;

		buildGui();

	}

	private void buildGui() {
		this.setMinWidth(minBreedte);
		this.getStyleClass().add("scoreKaart");
		Rectangle voorgrond = new Rectangle(40, minBreedte);
		voorgrond.setFill(Color.BROWN);
		if (dc.geefSpelerAanDeBeurt() == sp.toString())
			voorgrond.setOpacity(0);
		else
			voorgrond.setOpacity(0.20);
		this.add(voorgrond, 0, 0, 6, 4);
	}

}
