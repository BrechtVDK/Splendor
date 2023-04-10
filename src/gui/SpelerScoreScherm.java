package gui;

import domein.DomeinController;
import domein.Speler;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SpelerScoreScherm extends GridPane {
	private DomeinController dc;
	private Speler speler;
	// private int totaalAantalPrestigepunten;
	private final boolean isStartSpeler;

	// fx elementen
	private Label lblSpelerNaam, lblAantalPrestigepunten, lblAantalEdelenInBezit, lblTitelBonus, lblTitelFiche,
			lblIsStartspeler, lblAantalOntwikkelingskaarten;
	private EdelsteenPerSpelerScherm aantalEdelsteenFichesPerTypeInBezitScherm;
	private EdelsteenPerSpelerScherm aantalBonussenPerTypeInBezitScherm;

	public SpelerScoreScherm(DomeinController dc, Speler speler) {

		this.dc = dc;
		this.speler = speler;
		this.isStartSpeler = speler.isStartSpeler();
		this.aantalBonussenPerTypeInBezitScherm = new EdelsteenPerSpelerScherm(speler, true);
		this.aantalEdelsteenFichesPerTypeInBezitScherm = new EdelsteenPerSpelerScherm(speler, false);

		buildGui();

	}

	private void buildGui() {
		this.setVgap(5);
		this.setHgap(10);
		this.getStyleClass().add("scoreKaart");
		this.setId(String.format("%s", isSpelerAanDeBeurt() ? "aanBeurt" : "nietAanBeurt"));
		this.setPadding(new Insets(5));

		// Labels
		lblIsStartspeler = new Label(String.format("%s", isStartSpeler ? "Startspeler" : ""));
		// zorgt voor zelfde uitlijning bij startspeler als niet-startspeler
		lblIsStartspeler.setMinWidth(90);
		lblSpelerNaam = new Label(speler.getGebruikersnaam());
		// binding ok
		lblAantalPrestigepunten = new Label();
		lblAantalPrestigepunten.textProperty()
				.bind(Bindings.concat("Prestigepunten: ", speler.prestigepuntenProperty()));
		// binding ok
		lblAantalEdelenInBezit = new Label();
		lblAantalEdelenInBezit.textProperty()
				.bind(Bindings.concat("Edelen: ", Bindings.size(speler.getEdelenInBezit())));
		lblTitelBonus = new Label(String.format("%s", "Bonussen"));
		lblTitelFiche = new Label(String.format("%s", "Fiches"));
		// binding ok
		lblAantalOntwikkelingskaarten = new Label();
		lblAantalOntwikkelingskaarten.textProperty().bind(
				Bindings.concat("Ontwikkelingskaarten: ", Bindings.size(speler.getOntwikkelingskaartenInBezit())));
		this.add(lblIsStartspeler, 0, 1);
		this.add(lblSpelerNaam, 0, 0);
		this.add(lblAantalPrestigepunten, 1, 0);
		this.add(lblAantalEdelenInBezit, 2, 0);
		this.add(aantalBonussenPerTypeInBezitScherm, 1, 2);
		this.add(lblTitelBonus, 0, 2);
		this.add(aantalEdelsteenFichesPerTypeInBezitScherm, 1, 3);
		this.add(lblTitelFiche, 0, 3);
		this.add(lblAantalOntwikkelingskaarten, 1, 1);

	}

	protected boolean isSpelerAanDeBeurt() {
		return dc.geefSpelerAanDeBeurt().equals(speler);
	}
}
