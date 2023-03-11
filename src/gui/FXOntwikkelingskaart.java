package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FXOntwikkelingskaart extends GridPane {
	private int prestigePunten;
	private String bonus;

	// Jonas: ter info;
	// -edelsteenfiches[] = alle edelstenen die verdiend kunnen worden 1 per 1 in de
	// array
	// -bonus = edelsteenfiche = rgb + soort
	public FXOntwikkelingskaart(String prestigePunten, String bonus, String edelsteenfiches[]) {
		Label lblBonus = new Label(bonus);
		if (prestigePunten != "0") {
			Label lblPrestigePunten = new Label(prestigePunten);
			this.add(lblPrestigePunten, 0, 0);
		}
		this.add(lblBonus, 1, 0);
	}

}
