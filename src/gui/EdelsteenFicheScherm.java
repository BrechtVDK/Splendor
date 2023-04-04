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
				dc.geefAantalFichesPerStapel().get(Edelsteen.GROEN), this);
		FXFicheWit = new FXEdelsteenFicheKlikbaar(Edelsteen.WIT, 38, dc.geefAantalFichesPerStapel().get(Edelsteen.WIT),
				this);
		FXFicheBlauw = new FXEdelsteenFicheKlikbaar(Edelsteen.BLAUW, 38,
				dc.geefAantalFichesPerStapel().get(Edelsteen.BLAUW), this);
		FXFicheZwart = new FXEdelsteenFicheKlikbaar(Edelsteen.ZWART, 38,
				dc.geefAantalFichesPerStapel().get(Edelsteen.ZWART), this);
		FXFicheRood = new FXEdelsteenFicheKlikbaar(Edelsteen.ROOD, 38,
				dc.geefAantalFichesPerStapel().get(Edelsteen.ROOD), this);

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
			FXFicheGroen.pasAantalAanMet(1);
			FXFicheGroen.setVisible(true);
		}
		case WIT -> {
			FXFicheWit.pasAantalAanMet(1);
			FXFicheWit.setVisible(true);
		}

		case BLAUW -> {
			FXFicheBlauw.pasAantalAanMet(1);
			FXFicheBlauw.setVisible(true);

		}
		case ZWART -> {
			FXFicheZwart.pasAantalAanMet(1);
			FXFicheZwart.setVisible(true);
		}
		case ROOD -> {
			FXFicheRood.pasAantalAanMet(1);
			FXFicheRood.setVisible(true);
		}
		}
	}

}
