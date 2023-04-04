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

	public void pasAantalAanMet(int aantal) {
		this.aantal += aantal;
		txtAantal.setText(Integer.toString(this.aantal));
	}


	@Override
	public void onLeftClicked() {
		String parent = this.getParent().getClass().getSimpleName();
		if (parent.equals("EdelsteenFicheScherm")) {
			pasAantalAanMet(-1);
			if (aantal == 0) {
				this.setVisible(false);
			}
			FXEdelsteenFicheKlikbaar nieuweFiche = new FXEdelsteenFicheKlikbaar(super.getEdelsteen(), super.getRadius(),
					0, es);
			nieuweFiche.getTxtAantal().setText("");
			es.voegEdelsteenficheToeAanLinkerInfoScherm(nieuweFiche);
				
			
		}
		
	}
}
