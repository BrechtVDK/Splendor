package gui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import domein.Edelsteen;
import domein.Edelsteenfiche;
import domein.Ontwikkelingskaart;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class FXOntwikkelingskaart extends GridPane {
	private int prestigePunten;
	private Edelsteenfiche bonus;
	private Edelsteenfiche edelsteenfiches[];



	public FXOntwikkelingskaart(Ontwikkelingskaart info) {

		prestigePunten = info.prestigePunten();
		bonus = info.bonus();
		edelsteenfiches = info.edelsteenfiches();

		buildGui();

	}

	private void buildGui() {
		plaatsPrestigePunten();
		plaatsBonus();
		voegEdelsteenfichesToe();

		this.setHgap(25);
		this.setPadding(new Insets(5));
		this.setMinWidth(100);
		this.setMaxWidth(100);
		this.setMinHeight(150);
		this.setMaxHeight(150);
		this.getStyleClass().add("ontwikkelingskaart");
	}

	private void plaatsPrestigePunten() {
		Label lblPrestigePunten = new Label(Integer.toString(prestigePunten));
		lblPrestigePunten.getStyleClass().add("prestigePunten");
		this.add(lblPrestigePunten, 0, 0);

		if (lblPrestigePunten.getText().equals("0")) {
			lblPrestigePunten.setVisible(false);
		}
	}

	private void plaatsBonus() {
		Label lblBonus = new Label();
		lblBonus.setStyle(String.format("-fx-background-color: rgb%s", bonus.edelsteen().getRgb()));
		lblBonus.getStyleClass().add("edelsteenfiche");
		lblBonus.setPrefSize(30, 30);
		lblBonus.setMaxSize(30, 30);
		lblBonus.setAlignment(Pos.TOP_RIGHT);
		this.add(lblBonus, 1, 0);
	}

	private void voegEdelsteenfichesToe() {
		FlowPane fpFiches = new FlowPane();
		fpFiches.setOrientation(Orientation.VERTICAL);

		Map<String, Long> aantalFichesPerKleur = new HashMap<String, Long>();

		for (Edelsteen edelsteen : Edelsteen.values()) {
			aantalFichesPerKleur.put(edelsteen.getRgb(), Arrays.stream(edelsteenfiches)
					.filter(e -> e.edelsteen().getRgb().equals(edelsteen.getRgb())).count());
		}

		for (Map.Entry<String, Long> set : aantalFichesPerKleur.entrySet()) {
			if (set.getValue() != 0) {
				Label lblfiche = new Label(Long.toString(set.getValue()));
				lblfiche.setStyle(String.format("-fx-background-color: rgb%s", set.getKey()));
				lblfiche.getStyleClass().add("edelsteenfiche");
				lblfiche.setPrefSize(25, 25);
				lblfiche.setMaxSize(25, 25);
				lblfiche.setAlignment(Pos.CENTER);
				fpFiches.getChildren().add(lblfiche);
			}
		}
		
		fpFiches.setMaxWidth(70);
		fpFiches.setMaxHeight(100);
		fpFiches.setVgap(4);
		fpFiches.setHgap(4);
		fpFiches.setAlignment(Pos.BOTTOM_LEFT);
		this.add(fpFiches, 0, 1, 2, 1);
	}

}
