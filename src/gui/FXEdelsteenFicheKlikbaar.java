package gui;

import domein.Edelsteen;

public class FXEdelsteenFicheKlikbaar extends FXEdelsteenFiche implements Clickable {

	public FXEdelsteenFicheKlikbaar(Edelsteen edelsteen, double radius, int aantal) {
		super(edelsteen, radius, aantal);
		// interface
		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);
	}

	@Override
	public void onLeftClicked() {
		System.out.println("FXEdelsteenFiche");
	}
}
