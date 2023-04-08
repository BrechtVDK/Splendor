package gui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import domein.Edelsteen;
import domein.Edelsteenfiche;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public abstract class FXKaart extends GridPane {

	private int prestigePunten;
	private Edelsteenfiche edelsteenfiches[];

	public FXKaart(int prestigePunten, Edelsteenfiche edelsteenfiches[]) {
		this.prestigePunten = prestigePunten;
		this.edelsteenfiches = edelsteenfiches;
		buildGui();
	}

	private void buildGui() {
		plaatsPrestigePunten();
		voegEdelsteenfichesToe();

		this.setHgap(25);
		this.setPadding(new Insets(5));
		this.setMinWidth(94);
		this.setMaxWidth(94);
		this.setMinHeight(142);
		this.setMaxHeight(142);
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

		Map<Edelsteen, Long> aantalFichesPerEdelsteen = new HashMap<Edelsteen, Long>();
		for (Edelsteen edelsteen : Edelsteen.values()) {
			aantalFichesPerEdelsteen.put(edelsteen, Arrays.stream(edelsteenfiches)
					.filter(e -> e.edelsteen().getRgb().equals(edelsteen.getRgb())).count());
		}
		// Hier wordt over de Map geïtereerd om de nodige info te bekomen (manier werd
		// gevonden op internet)
		for (Map.Entry<Edelsteen, Long> set : aantalFichesPerEdelsteen.entrySet()) {
			if (set.getValue() != 0) {
				FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFiche(set.getKey(), 12, set.getValue().intValue());
				fpFiches.getChildren().add(fxEdelsteenFiche);
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
