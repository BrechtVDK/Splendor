package gui;

import domein.Edelsteenfiche;
import domein.OntwikkelingskaartRecord;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FXOntwikkelingskaart extends GridPane {
	private int prestigePunten;
	private Edelsteenfiche bonus;
	private Edelsteenfiche edelsteenfiches[];



	public FXOntwikkelingskaart(OntwikkelingskaartRecord info) {

		prestigePunten = info.prestigePunten();
		bonus = info.bonus();
		edelsteenfiches = info.edelsteenfiches();

		Label lblBonus = new Label(bonus.toString());

		if (prestigePunten != 0) {
			Label lblPrestigePunten = new Label(Integer.toString(prestigePunten));
			this.add(lblPrestigePunten, 0, 0);
		}
		this.add(lblBonus, 1, 0);

		this.setMaxWidth(100);
		this.setPrefSize(500, 100);


		this.getStyleClass().add("ontwikkelingskaart");
	}

}
