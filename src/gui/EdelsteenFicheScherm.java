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

	protected void maakFichesKlikbaar() {
		this.setMouseTransparent(false);
	}

	protected void maakFichesOnklikbaar() {
		this.setMouseTransparent(true);
	}

	protected void voegEdelsteenficheToeAanLinkerInfoScherm(FXEdelsteenFiche edelsteenfiche) {
		hs.voegEdelsteenficheToeAanLinkerInfoScherm(edelsteenfiche);
	}

	protected void voegEdelsteenficheTerugToe(Edelsteenfiche e) {
		dc.voegEdelsteenfichesTerugToeAanStapelsSpel(Arrays.asList(e));

	}

}
