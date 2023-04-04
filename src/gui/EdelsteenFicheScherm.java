package gui;

import java.util.Arrays;

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
		/*
		 * FXFicheGroen = new FXEdelsteenFicheKlikbaar(Edelsteen.GROEN, 38,
		 * dc.geefAantalFichesPerStapel().get(Edelsteen.GROEN)); FXFicheWit = new
		 * FXEdelsteenFicheKlikbaar(Edelsteen.WIT, 38,
		 * dc.geefAantalFichesPerStapel().get(Edelsteen.WIT)); FXFicheBlauw = new
		 * FXEdelsteenFicheKlikbaar(Edelsteen.BLAUW, 38,
		 * dc.geefAantalFichesPerStapel().get(Edelsteen.BLAUW)); FXFicheZwart = new
		 * FXEdelsteenFicheKlikbaar(Edelsteen.ZWART, 38,
		 * dc.geefAantalFichesPerStapel().get(Edelsteen.ZWART)); FXFicheRood = new
		 * FXEdelsteenFicheKlikbaar(Edelsteen.ROOD, 38,
		 * dc.geefAantalFichesPerStapel().get(Edelsteen.ROOD));
		 */

		FXFicheGroen = new FXEdelsteenFicheKlikbaar(Edelsteen.GROEN, 38, dc);
		FXFicheWit = new FXEdelsteenFicheKlikbaar(Edelsteen.WIT, 38, dc);
		FXFicheBlauw = new FXEdelsteenFicheKlikbaar(Edelsteen.BLAUW, 38, dc);
		FXFicheZwart = new FXEdelsteenFicheKlikbaar(Edelsteen.ZWART, 38, dc);
		FXFicheRood = new FXEdelsteenFicheKlikbaar(Edelsteen.ROOD, 38, dc);
		this.getChildren().addAll(FXFicheGroen, FXFicheWit, FXFicheBlauw, FXFicheZwart, FXFicheRood);
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

	public void voegEdelsteenficheTerugToe(Edelsteenfiche e) {
		dc.voegEdelsteenfichesTerugToeAanStapelsSpel(Arrays.asList(e));
		switch (e.edelsteen()) {
		case GROEN -> FXFicheGroen.checkVisibility();
		case WIT -> FXFicheWit.checkVisibility();
		case BLAUW -> FXFicheBlauw.checkVisibility();
		case ZWART -> FXFicheZwart.checkVisibility();
		case ROOD -> FXFicheRood.checkVisibility();
		}
	}

}
