package gui;

import domein.DomeinController;
import domein.Edelsteen;
import javafx.scene.layout.VBox;

public class EdelsteenFicheScherm extends VBox {
	private DomeinController dc;

	public EdelsteenFicheScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		this.setSpacing(10);

		for (Edelsteen e : Edelsteen.values()) {
			int aantal = dc.geefAantalFichesPerStapel().get(e);
			FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFicheKlikbaar(e, 38, aantal);
			this.getChildren().add(fxEdelsteenFiche);
		}
	}

}
