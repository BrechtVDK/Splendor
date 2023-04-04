package gui;

import domein.DomeinController;
import domein.Edelsteen;
import domein.Edelsteenfiche;
import javafx.scene.layout.VBox;

public class EdelsteenFicheScherm extends VBox {
	private DomeinController dc;
	private Hoofdscherm hs;
	private FXEdelsteenFicheKlikbaar FXFicheGroen, FXFicheWit, FXFicheBlauw, FXFicheZwart, FXFicheRood;

	public EdelsteenFicheScherm(DomeinController dc, Hoofdscherm hs) {
		this.dc = dc;
		this.hs = hs;

		buildGui();
	}

	private void buildGui() {
		this.setSpacing(10);

		FXFicheGroen = new FXEdelsteenFicheKlikbaar(Edelsteen.GROEN, 38,
				dc.geefAantalFichesPerStapel().get(Edelsteen.GROEN));
		FXFicheWit = new FXEdelsteenFicheKlikbaar(Edelsteen.WIT, 38, dc.geefAantalFichesPerStapel().get(Edelsteen.WIT));
		FXFicheBlauw = new FXEdelsteenFicheKlikbaar(Edelsteen.BLAUW, 38,
				dc.geefAantalFichesPerStapel().get(Edelsteen.BLAUW));
		FXFicheZwart = new FXEdelsteenFicheKlikbaar(Edelsteen.ZWART, 38,
				dc.geefAantalFichesPerStapel().get(Edelsteen.ZWART));
		FXFicheRood = new FXEdelsteenFicheKlikbaar(Edelsteen.ROOD, 38,
				dc.geefAantalFichesPerStapel().get(Edelsteen.ROOD));

		this.getChildren().addAll(FXFicheGroen, FXFicheWit, FXFicheBlauw, FXFicheZwart, FXFicheRood);
//		for (Edelsteen e : Edelsteen.values()) {
//			int aantal = dc.geefAantalFichesPerStapel().get(e);
//			FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFicheKlikbaar(e, 38, aantal, this);
//			this.getChildren().add(fxEdelsteenFiche);
//		}
	}

	public void maakFichesKlikbaar() {
		this.setDisable(false);
	}

	public void maakFichesOnklikbaar() {
		this.setDisable(true);
	}

	public void voegEdelsteenficheToeAanLinkerInfoScherm(FXEdelsteenFiche edelsteenfiche) {
		hs.voegEdelsteenficheToeAanLinkerInfoScherm(edelsteenfiche);
	}


	public void voegFoutieveEdelsteenficheTerugToe(Edelsteenfiche e) {
		switch (e.edelsteen()) {
		case GROEN -> {
			FXFicheGroen.txtAantal.setText(Integer.toString(dc.geefAantalFichesPerStapel().get(Edelsteen.GROEN)));
			FXFicheGroen.checkVisibility();
		}
		case WIT -> {
			FXFicheWit.txtAantal.setText(Integer.toString(dc.geefAantalFichesPerStapel().get(Edelsteen.WIT)));
			FXFicheWit.checkVisibility();
		}

		case BLAUW -> {
			FXFicheBlauw.txtAantal.setText(Integer.toString(dc.geefAantalFichesPerStapel().get(Edelsteen.BLAUW)));
			FXFicheBlauw.checkVisibility();
		}
		case ZWART -> {
			FXFicheZwart.txtAantal.setText(Integer.toString(dc.geefAantalFichesPerStapel().get(Edelsteen.ZWART)));
			FXFicheZwart.checkVisibility();
		}
		case ROOD -> {
			FXFicheRood.txtAantal.setText(Integer.toString(dc.geefAantalFichesPerStapel().get(Edelsteen.ROOD)));
			FXFicheRood.checkVisibility();
		}
		}
	}

}
