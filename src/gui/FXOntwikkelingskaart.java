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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class FXOntwikkelingskaart extends GridPane implements Clickable {
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

	private void plaatsBonus() {
		FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFiche(bonus.edelsteen(), 15);
		fxEdelsteenFiche.setAlignment(Pos.TOP_RIGHT);

		this.add(fxEdelsteenFiche, 1, 0);
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
	public void onClicked(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			System.out.println("FXOntwikkelingskaart");
		}
	}

}