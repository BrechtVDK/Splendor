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
		int radius = 35;
		FXFicheGroen = new FXEdelsteenFicheKlikbaar(Edelsteen.GROEN, radius, dc);
		FXFicheWit = new FXEdelsteenFicheKlikbaar(Edelsteen.WIT, radius, dc);
		FXFicheBlauw = new FXEdelsteenFicheKlikbaar(Edelsteen.BLAUW, radius, dc);
		FXFicheZwart = new FXEdelsteenFicheKlikbaar(Edelsteen.ZWART, radius, dc);
		FXFicheRood = new FXEdelsteenFicheKlikbaar(Edelsteen.ROOD, radius, dc);
		this.getChildren().addAll(FXFicheGroen, FXFicheWit, FXFicheBlauw, FXFicheZwart, FXFicheRood);
	}

	public void maakFichesKlikbaar() {
		this.setMouseTransparent(false);
	}

	public void maakFichesOnklikbaar() {
		this.setMouseTransparent(true);
	}

	public void voegEdelsteenficheToeAanLinkerInfoScherm(FXEdelsteenFiche edelsteenfiche) {
		hs.voegEdelsteenficheToeAanLinkerInfoScherm(edelsteenfiche);
	}

	public void voegEdelsteenficheTerugToe(Edelsteenfiche e) {
		dc.voegEdelsteenfichesTerugToeAanStapelsSpel(Arrays.asList(e));
		// Overbodig geworden: Listener toegevoegd aan FXEdelsteenFicheKlikbaar
		/*
		 * switch (e.edelsteen()) { case GROEN -> FXFicheGroen.checkVisibility(); case
		 * WIT -> FXFicheWit.checkVisibility(); case BLAUW ->
		 * FXFicheBlauw.checkVisibility(); case ZWART -> FXFicheZwart.checkVisibility();
		 * case ROOD -> FXFicheRood.checkVisibility(); }
		 */
	}

}
