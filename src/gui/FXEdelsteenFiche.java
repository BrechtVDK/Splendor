package gui;

import domein.Edelsteen;
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

	public FXEdelsteenFiche(Edelsteen edelsteen, double radius) {
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

	public FXEdelsteenFiche(Edelsteen edelsteen, double radius, int aantal) {
		this(edelsteen, radius);
		Text txtAantal = new Text(Integer.toString(aantal));
		txtAantal.setFill(TEKSTKLEUR);
		// BOLD en size afh. van de radius instellen
		txtAantal.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD,
				radius >= GROOT ? radius / 1.5 : radius / 0.9));

		this.getChildren().add(txtAantal);

	}

}
