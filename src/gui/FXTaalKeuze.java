package gui;

import java.util.Arrays;

import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import resources.Taal;

public class FXTaalKeuze extends HBox {

	ChoiceBox<String> talenLijst = new ChoiceBox<>();
	private static final String LIJST_ONDERSTEUNDE_TALEN[] = Taal.TALEN;

	public FXTaalKeuze() {
		this.getChildren().add(setTalenlijst());
	}

	private ChoiceBox<String> setTalenlijst() {
		// talenLijst.getItems().add(s));
		talenLijst.getItems().addAll(Arrays.asList(LIJST_ONDERSTEUNDE_TALEN));
		// set default
		talenLijst.setValue(LIJST_ONDERSTEUNDE_TALEN[0]);
		// listener
		talenLijst.getSelectionModel().selectedItemProperty()
				.addListener((v, oudeTaal, nieuweTaal) -> {
					Taal.setVoorkeurTaal(nieuweTaal);
					System.out.println(nieuweTaal);
				});
		return talenLijst;

	}
}
