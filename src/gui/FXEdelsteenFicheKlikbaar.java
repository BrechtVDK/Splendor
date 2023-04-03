package gui;

import domein.Edelsteen;

public class FXEdelsteenFicheKlikbaar extends FXEdelsteenFiche implements Clickable {

	private int aantal;
	private EdelsteenFicheScherm es;

	public FXEdelsteenFicheKlikbaar(Edelsteen edelsteen, double radius, int aantal, EdelsteenFicheScherm es) {
		super(edelsteen, radius, aantal);

		this.aantal = aantal;
		this.es = es;
//		txtAantal = new Text();
//		ObjectBinding<Integer> aantalFichesBinding;

//		aantalFichesBinding = Bindings.valueAt(dc.geefAantalFichesPerStapel(), edelsteen);
//		txtAantal.textProperty().bind(aantalFichesBinding.asString());
//		stelTekstLayOutIn();
//		this.getChildren().add(txtAantal);
		// interface

		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);
	}

	@Override
	public void onLeftClicked() {
		txtAantal.setText(Integer.toString(--aantal));

		if (aantal == 0) {
			this.setVisible(false);

		}
	}
}
