package gui;

import domein.Edelsteen;
import domein.Speler;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//Circle en evt Text in StackPane stoppen
public class FXEdelsteenFiche extends StackPane {
	private final static Paint TEKSTKLEUR = Color.web("F8BD7F");
	private final static int GROOT = 30;
	private final double radius;
	private Text txtAantal;

	public FXEdelsteenFiche(Edelsteen edelsteen, double radius) {
		this.radius = radius;
		Circle cirkel = new Circle(radius, Color.web(String.format("rgb%s", edelsteen.getRgb())));
		// zwarte rand
		cirkel.setStroke(Color.BLACK);
		// grote fiche = dikkere rand + schaduw
		if (radius >= GROOT) {
			cirkel.setStrokeWidth(2);
			cirkel.setEffect(new DropShadow());
		}
		this.getChildren().add(cirkel);
	}

	// aantal fixed
	public FXEdelsteenFiche(Edelsteen edelsteen, double radius, int aantal) {
		this(edelsteen, radius);
		txtAantal = new Text(Integer.toString(aantal));
		stelTekstLayOutIn(txtAantal);

		this.getChildren().add(txtAantal);
	}

	// binding: aantal via Speler en boolean
	public FXEdelsteenFiche(Edelsteen edelsteen, double radius, Speler speler, boolean isBonus) {
		this(edelsteen, radius);
		txtAantal = new Text();
		ObjectBinding<Integer> aantalFichesBinding;

		// Parameters Bindings.valueAt: (ObservableMap, key)
		aantalFichesBinding = Bindings.valueAt(
				!isBonus ? speler.getAantalEdelsteenfichesPerTypeInBezit() : speler.getAantalBonussenPerTypeInBezit(),
				edelsteen);

		txtAantal.textProperty().bind(aantalFichesBinding.asString());
		stelTekstLayOutIn(txtAantal);
		this.getChildren().add(txtAantal);
	}

	private void stelTekstLayOutIn(Text txtAantal) {
		txtAantal.setFill(TEKSTKLEUR);
		// BOLD en size afh. van de radius instellen
		txtAantal.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD,
				radius >= GROOT ? radius / 1.5 : radius / 0.9));
	}
}
