package gui;

import domein.Edelsteen;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class FXEdelsteenFicheKlikbaar extends FXEdelsteenFiche implements Clickable {

	public FXEdelsteenFicheKlikbaar(Edelsteen edelsteen, double radius, int aantal) {
		super(edelsteen, radius, aantal);
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
