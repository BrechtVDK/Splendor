package gui;

import domein.Edele;

public class FXEdeleKaart extends FXKaart implements Clickable {

	private Edele edele;

	public FXEdeleKaart(Edele info) {
		super(Edele.PRESTIGE_PUNTEN, info.bonussen());
		buildExtras();
		this.edele = info;
	}

	public Edele getEdele() {
		return edele;
	}

	private void buildExtras() {
		this.getStyleClass().add("edele");

		// interface
		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);
		this.setMouseTransparent(true);
	}

	public void highlight() {
		this.setMouseTransparent(false);
	}

	@Override
	public void onLeftClicked() {
		((EdelenScherm) this.getParent()).verplaatsEdeleNaarSpeler(this);
	}

}
