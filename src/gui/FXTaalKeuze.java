package gui;

import java.util.Arrays;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import resources.Taal;

public class FXTaalKeuze extends HBox {

	ChoiceBox<String> talenLijst = new ChoiceBox<>();
	Pane rootPane;
	String gekozenTaal = null;
	private static final String HOOFDSCHERMCHILDREN_TE_VERTALEN[] = { "SpelerScoreScherm", "LinkerInfoScherm",
			"ScoreBordScherm", "hboxAantalPrestigepunten" };
	private static final String LIJST_ONDERSTEUNDE_TALEN[] = Taal.TALEN;

	public FXTaalKeuze(Pane rootPane, String gekozenTaal) {
		// Verwijzing naar moederbord die deze HBox implementeerd
		this.rootPane = rootPane;
		// Indien in vorig scherm reeds een taal is gekozen
		this.gekozenTaal = gekozenTaal;
		this.getChildren().add(setTalenlijst());
	}

	private ChoiceBox<String> setTalenlijst() {
		// talenLijst.getItems().add(s));
		talenLijst.getItems().addAll(Arrays.asList(LIJST_ONDERSTEUNDE_TALEN));
		// set default
		talenLijst.setValue(Taal.getGekozenTaal() == null ? LIJST_ONDERSTEUNDE_TALEN[0] : Taal.getGekozenTaal());
		// listener
		talenLijst.getSelectionModel().selectedItemProperty().addListener((v, oudeTaal, nieuweTaal) -> {
			Taal.setVoorkeurTaal(nieuweTaal);
			// doorlopen van alle children van de moederpane
			// for (Node n : rootPane.getChildren()) {
					labelsEnButtonsDoorlopen(rootPane);
			// }
			// System.out.println(nieuweTaal);
		});

		return talenLijst;

	}

	private void labelsEnButtonsDoorlopen(Pane pane) {
		for (Node n : pane.getChildren()) {
			if (n instanceof Label) {
				Label l = (Label) n;
				// nullpointers opvangen (labels zonder standaardmelding en labels met
				// properties (binding))
//				if (l.getId() != null && !l.hasProperties()) {
				if (l.getId() != null) {
					Platform.runLater(() -> l.setText(Taal.vertaling(l.getId())));
					System.out.println(l.getText());
				}
			} else if (n instanceof Button) {
				Button b = (Button) n;
				Platform.runLater(() -> b.setText(Taal.vertaling(b.getId())));
				// child = pane -> recursief werken
			} else if (n instanceof Pane) {
				if (Arrays.stream(HOOFDSCHERMCHILDREN_TE_VERTALEN)
						.noneMatch(res -> res.equals(n.getClass().getSimpleName())))
					continue;
				else
					labelsEnButtonsDoorlopen((Pane) n);
			}
		}
	}
}
