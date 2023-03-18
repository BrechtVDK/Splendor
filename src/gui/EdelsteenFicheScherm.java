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
			int aantal = aantalFichesPerStapel.get(e);
			FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFicheKlikbaar(e, 38, aantal);
			this.getChildren().add(fxEdelsteenFiche);
		}
	}

}
