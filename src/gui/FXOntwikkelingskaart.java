package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FXOntwikkelingskaart extends GridPane {
	private String prestigePunten;
	private String bonus;
	private List<String> edelsteenfiches;
	private String[] kaartInfo;


	public FXOntwikkelingskaart(String Info) {
		kaartInfo = Info.split(",");

		prestigePunten = this.kaartInfo[0];
		bonus = kaartInfo[1];

		edelsteenfiches = new ArrayList<>();

		for (int i = 2; i < kaartInfo.length; i++) {
			edelsteenfiches.add(kaartInfo[i]);
		}

		Label lblBonus = new Label(bonus);

		if (prestigePunten != "0") {
			Label lblPrestigePunten = new Label(prestigePunten);
			this.add(lblPrestigePunten, 0, 0);
		}
		this.add(lblBonus, 1, 0);

		this.setMaxWidth(100);
		this.setPrefSize(500, 100);

		this.getStyleClass().add("ontwikkelingskaart");
	}

}
