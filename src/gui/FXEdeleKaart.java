package gui;

import domein.Edele;

public class FXEdeleKaart extends FXKaart implements Clickable {

	public FXEdeleKaart(Edele info) {
		super(Edele.PRESTIGE_PUNTEN, info.bonussen());
		buildExtras();

	}

	private void buildExtras() {
		this.getStyleClass().add("edele");

		// interface
		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);

	}

	@Override
	public void onLeftClicked() {
		System.out.println("Edelekaart");

	}

}
