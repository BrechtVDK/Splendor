package gui;

import domein.DomeinController;
import domein.Speler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SpelerScoreScherm extends GridPane {
	private DomeinController dc;
	private Speler speler;
	private double minBreedte;

	private int totaalAantalPrestigepunten;
	private boolean isAanDeBeurt;
	private final boolean isStartSpeler;

	// fx elementen
	private Label lblSpelerNaam, lblAantalPrestigepunten, lblAantalEdelenInBezit, lblTitelBonus, lblTitelFiche,
			lblIsStartspeler,
			lblAantalOntwikkelingskaarten;
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
		this.setMinWidth(minBreedte);
		this.setVgap(5);
		this.setHgap(10);
		this.getStyleClass().add("scoreKaart");
		if (dc.geefSpelerAanDeBeurt().equals(speler))
			this.getStyleClass().add("scoreKaartAanBeurt");
		else
			this.getStyleClass().add("scoreKaartNietAanBeurt");
		// this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(5));

		// Labels
		lblIsStartspeler = new Label(String.format("%s", isStartSpeler ? "Startspeler" : ""));
		// zorgt voor zelfde uitlijning bij startspeler als niet-startspeler
		lblIsStartspeler.setMinWidth(90);
		lblSpelerNaam = new Label(speler.getGebruikersnaam());
		lblAantalPrestigepunten = new Label(String.format("%s: %d", "Prestigepunten", speler.getPrestigepunten()));
		lblAantalEdelenInBezit = new Label(String.format("%s: %d", "Edelen", speler.getEdelenInBezit().size()));
		lblTitelBonus = new Label(String.format("%s", "Bonussen"));
		lblTitelFiche = new Label(String.format("%s", "Fiches"));
		
		lblAantalOntwikkelingskaarten = new Label(
				String.format("Ontwikkelingskaarten: %d", speler.getOntwikkelingskaartenInBezit().size()));
		

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

	/*
	 * private int geefTotaalAantalFiches(Map<Edelsteen, Integer> fiches) { int
	 * totaalSom = 0; for (Edelsteen e : Edelsteen.values()) { totaalSom +=
	 * fiches.get(e); } return totaalSom;
	 * 
	 * }
	 */
	public void setVoorgrond(double breedte, double hoogte) {

	}

}
