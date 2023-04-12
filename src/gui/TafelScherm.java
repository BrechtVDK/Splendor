package gui;

import domein.DomeinController;
import domein.Niveau;
import domein.Ontwikkelingskaart;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TafelScherm extends GridPane {
	private DomeinController dc;
	private Label[] lblStapels;
	private Ontwikkelingskaart[][] kaarten;
	private Hoofdscherm hs;

	public TafelScherm(DomeinController dc, Hoofdscherm hs) {
		this.dc = dc;
		this.kaarten = dc.geefZichtbareOntwikkelingskaarten();
		this.hs = hs;

		this.setVgap(25);
		this.setHgap(25);
		buildGui();
	}

	private void buildGui() {
		geefZichtbareKaartenWeer();
		geefStapelsWeer();
		this.setMouseTransparent(true);
	}

	private void geefZichtbareKaartenWeer() {

		for (int rij = 0; rij < kaarten.length; rij++) {
			for (int kolom = 0; kolom < kaarten[rij].length; kolom++) {
				// volgorde gridpane: kol, rij!
				int[] index = { kolom, rij };
				FXOntwikkelingskaart kaart = new FXOntwikkelingskaart(kaarten[rij][kolom], index, this);
				this.add(kaart, kolom + 1, rij);
			}
		}

	}

	private void geefStapelsWeer() {
		lblStapels = new Label[Niveau.values().length];
		for (int i = 0; i < lblStapels.length; i++) {
			IntegerBinding aantalKaarten = dc.geefAantalResterendeKaarten().get(Niveau.values()[i]);
			StringBuilder bollen = new StringBuilder("\n");
			bollen.append(" • ".repeat(lblStapels.length - i));
			lblStapels[i] = new Label();
			// binding
			lblStapels[i].textProperty().bind(Bindings.concat(aantalKaarten.asString(), bollen));
			lblStapels[i].setAlignment(Pos.BOTTOM_CENTER);
			lblStapels[i].setMinHeight(142);
			lblStapels[i].setMaxHeight(142);
			lblStapels[i].setMinWidth(94);
			lblStapels[i].setMaxWidth(94);
			lblStapels[i].getStyleClass().add("stapel");
			lblStapels[i].setId(String.format("niveau%d", i + 1));
			this.add(lblStapels[i], 0, i);
		}

	}

	protected void verplaatsKaartNaarLinkerInfoScherm(FXOntwikkelingskaart fxKaart) {
		hs.verplaatsKaartNaarLinkerInfoScherm(fxKaart);
	}

	protected void voegFouteKaartTerugToeVanLinkerInfoScherm(FXOntwikkelingskaart terugTeLeggenKaart) {
		this.add(terugTeLeggenKaart, terugTeLeggenKaart.getIndex()[0] + 1, terugTeLeggenKaart.getIndex()[1]);
	}

	protected void maakKaartenKlikbaar() {
		this.setMouseTransparent(false);
	}

	protected void maakKaartenOnKlikbaar() {
		this.setMouseTransparent(true);
	}

	protected Ontwikkelingskaart geefOntwikkelingskaartVolgensIndex(int[] index) {
		return kaarten[index[1]][index[0]];
	}

	protected void legNieuweKaartOpTafel(Ontwikkelingskaart kaart, int[] kaartIndex) {

		kaarten[kaartIndex[1]][kaartIndex[0]] = kaart;
		this.add(new FXOntwikkelingskaart(kaart, kaartIndex, this), kaartIndex[0] + 1, kaartIndex[1]);
	}

}
