package gui;

import java.util.Map;

import domein.DomeinController;
import domein.Edelsteen;
import javafx.scene.layout.VBox;

public class EdelsteenFicheScherm extends VBox {
	private DomeinController dc;
	private Map<Edelsteen, Integer> aantalFichesPerStapel;

	public EdelsteenFicheScherm(DomeinController dc) {
		this.dc = dc;
		this.aantalFichesPerStapel = dc.geefAantalFichesPerStapel();

		buildGui();
	}

	private void buildGui() {
		this.setSpacing(10);

		for (Edelsteen e : Edelsteen.values()) {
			String rgb = e.getRgb();
			FXEdelsteesteenFiche fxEdelsteenFiche = new FXEdelsteesteenFiche(
					Integer.toString(aantalFichesPerStapel.get(e)), rgb);
			fxEdelsteenFiche.getStyleClass().add("edelsteenfichegroot");
			fxEdelsteenFiche.setPrefSize(75, 75);
			this.getChildren().add(fxEdelsteenFiche);
		}
	}

}
