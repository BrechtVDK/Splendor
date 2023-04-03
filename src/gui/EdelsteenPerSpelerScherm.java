package gui;

import domein.Edelsteen;
import domein.Speler;
import javafx.scene.layout.HBox;

public class EdelsteenPerSpelerScherm extends HBox {
	private Speler speler;
	private boolean isBonus;

	public EdelsteenPerSpelerScherm(Speler speler, boolean isBonus) {
		this.speler = speler;
		this.isBonus = isBonus;
		buildGui();
	}

	private void buildGui() {
		this.setSpacing(5);

		for (Edelsteen e : Edelsteen.values()) {
			FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFiche(e, 14, speler, isBonus);
			this.getChildren().add(fxEdelsteenFiche);
		}
	}

}
