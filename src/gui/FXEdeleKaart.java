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

public class FXEdeleKaart extends GridPane implements Clickable {
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

		// interface Clickable
		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.15);
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

	@Override
	public void onLeftClicked() {
		System.out.println("FXEdeleKaart");

	}
}
