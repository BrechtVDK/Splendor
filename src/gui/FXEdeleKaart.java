package gui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import domein.Edele;
import domein.Edelsteen;
import domein.Edelsteenfiche;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class FXEdeleKaart extends GridPane {
	private int prestigePunten;
	private Edelsteenfiche edelsteenfiches[];

	public FXEdeleKaart(Edele info) {

		prestigePunten = Edele.PRESTIGE_PUNTEN;
		edelsteenfiches = info.bonussen();

		buildGui();

	}

	private void buildGui() {
		plaatsPrestigePunten();
		voegEdelsteenfichesToe();

		this.setHgap(25);
		this.setPadding(new Insets(5));
		this.setMinWidth(100);
		this.setMaxWidth(100);
		this.setMinHeight(150);
		this.setMaxHeight(150);
		this.getStyleClass().add("edele");
	}

	private void plaatsPrestigePunten() {
		Label lblPrestigePunten = new Label(Integer.toString(prestigePunten));
		lblPrestigePunten.getStyleClass().add("prestigePunten");
		this.add(lblPrestigePunten, 0, 0);

		if (lblPrestigePunten.getText().equals("0")) {
			lblPrestigePunten.setVisible(false);
		}
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
