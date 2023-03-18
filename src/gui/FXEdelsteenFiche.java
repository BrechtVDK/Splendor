package gui;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class FXEdelsteenFiche extends Button implements Clickable {
	public FXEdelsteenFiche(String aantalFiches, String rgb) {
		// tekst in Button
		super(aantalFiches);
		buildGui(rgb);
	}

	private void buildGui(String rgb) {
		this.getStyleClass().add("edelsteenfiche");
		this.setStyle(String.format("-fx-background-color: rgb%s", rgb));
		// interface
		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);

	}

	@Override
	public void onClicked(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			System.out.println("FXEdelsteenFiche");
		}

	}
}
