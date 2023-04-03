package gui;

import domein.DomeinController;
import domein.Edelsteen;
import javafx.scene.layout.VBox;

public class EdelsteenFicheScherm extends VBox {
	private DomeinController dc;
	private Hoofdscherm hs;

	public EdelsteenFicheScherm(DomeinController dc, Hoofdscherm hs) {
		this.dc = dc;
		this.hs = hs;

		buildGui();
	}

	private void buildGui() {
		this.setSpacing(10);

		for (Edelsteen e : Edelsteen.values()) {
			int aantal = dc.geefAantalFichesPerStapel().get(e);
			FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFicheKlikbaar(e, 38, aantal, this);
			this.getChildren().add(fxEdelsteenFiche);
		}
	}

	public void maakFichesKlikbaar() {
		this.setDisable(false);
	}

	public void maakFichesOnklikbaar() {
		this.setDisable(true);
	}

	public void voegEdelsteenficheToeAanLinkerInfoScherm(FXEdelsteenFiche edelsteenfiche) {
		hs.voegEdelsteenficheToeAanLinkerInfoScherm(edelsteenfiche);
	}

}
