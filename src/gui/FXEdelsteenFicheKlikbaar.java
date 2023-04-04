package gui;

import domein.Edelsteen;
import domein.Edelsteenfiche;

public class FXEdelsteenFicheKlikbaar extends FXEdelsteenFiche implements Clickable {




	public FXEdelsteenFicheKlikbaar(Edelsteen edelsteen, double radius, int aantal) {
		super(edelsteen, radius, aantal);



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
		int nieuwAantal = Integer.parseInt(txtAantal.getText()) + aantal;
		txtAantal.setText(Integer.toString(nieuwAantal));
	}

	public void checkVisibility() {
		boolean visible = (Integer.parseInt(txtAantal.getText()) == 0) ? false : true;
		this.setVisible(visible);
	}


	@Override
	public void onLeftClicked() {
		String parent = this.getParent().getClass().getSimpleName();
		if (parent.equals("EdelsteenFicheScherm")) {
			// pasAantalAanMet(-1);
			checkVisibility();
			FXEdelsteenFicheKlikbaar nieuweFiche = new FXEdelsteenFicheKlikbaar(super.getEdelsteen(), 20,
					0);
			nieuweFiche.getTxtAantal().setText("");
			((EdelsteenFicheScherm) this.getParent()).voegEdelsteenficheToeAanLinkerInfoScherm(nieuweFiche);
		} else if (parent.equals("LinkerInfoScherm")) {
			if (txtAantal.getText().equals("")) {
				((EdelsteenFicheScherm) this.getParent())
						.voegFoutieveEdelsteenficheTerugToe(new Edelsteenfiche(this.getEdelsteen()));
				((LinkerInfoScherm) this.getParent()).verwijderEnkeleFiche(this);
				((EdelsteenFicheScherm) this.getParent()).maakFichesKlikbaar();
			} else if (!txtAantal.getText().equals("0")) {
				pasAantalAanMet(-1);
			}
		}
		
	}
}
