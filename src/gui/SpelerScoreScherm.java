package gui;


import domein.DomeinController;
import domein.Edelsteen;
import domein.Speler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private Label lblSpelerNaam, lblAantalPrestigepunten, 
			lblTitelTotaal, lblAantalOntwikkelingskaartenInBezit, lblAantalFichesInBezit, lblAantalEdelenInBezit,
			lblTitelBonus, lblTitelFiche;
	private EdelsteenPerSpelerScherm aantalEdelsteenFichesPerTypeInBezit;
	private EdelsteenPerSpelerScherm aantalBonussenPerTypeInBezit;

	public SpelerScoreScherm(DomeinController dc, Speler speler) {

		this.dc = dc;
		this.speler = speler;
		this.isStartSpeler = speler.isStartSpeler();
		this.aantalBonussenPerTypeInBezit = new EdelsteenPerSpelerScherm(speler, true);
		this.aantalEdelsteenFichesPerTypeInBezit = new EdelsteenPerSpelerScherm(speler, false);

		buildGui();

	}

	private void buildGui() {
		this.setMinWidth(minBreedte);
		this.setVgap(5);
		this.setHgap(5);
		this.getStyleClass().add("scoreKaart");
		if (dc.geefSpelerAanDeBeurt().equals(speler))
			this.getStyleClass().add("scoreKaartAanBeurt");
		else
			this.getStyleClass().add("scoreKaartNietAanBeurt");
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20));

		// Labels
		lblSpelerNaam = new Label(speler.getGebruikersnaam());
		lblAantalPrestigepunten = new Label(String.format("%s: %d", "Prestigepunten", speler.getPrestigepunten()));
		lblTitelTotaal = new Label(String.format("%s", "TOTAAL"));
		lblTitelTotaal.setRotate(-90);
		lblAantalEdelenInBezit = new Label(String.format("%s:%n%d", "Edelen", speler.getEdelenInBezit().size()));
		lblAantalOntwikkelingskaartenInBezit = new Label(
				String.format("%d", geefTotaalAantalMap(aantalBonussenPerTypeInBezit)));
		lblAantalOntwikkelingskaartenInBezit.setAlignment(Pos.CENTER_RIGHT);
		lblAantalFichesInBezit = new Label(
				String.format("%d", geefTotaalAantalMap(aantalEdelsteenFichesPerTypeInBezit)));
		lblAantalFichesInBezit.setAlignment(Pos.CENTER_RIGHT);
		lblTitelBonus = new Label(String.format("%s", "Bonussen"));
		lblTitelFiche = new Label(String.format("%s", "Fiches"));

		this.add(lblSpelerNaam, 0, 0, 1, 2);
		this.add(lblAantalPrestigepunten, 1, 0, 1, 2);
		this.add(lblTitelTotaal, 2, 0, 1, 2);
		this.add(lblAantalEdelenInBezit, 3, 0, 1, 2);
		this.add(aantalBonussenPerTypeInBezit, 0, 2, 2, 1);
		this.add(lblAantalOntwikkelingskaartenInBezit, 2, 2, 1, 1);
		this.add(lblTitelBonus, 3, 2, 1, 1);
		this.add(aantalEdelsteenFichesPerTypeInBezit, 0, 3, 2, 1);
		this.add(lblAantalFichesInBezit, 2, 3, 1, 1);
		this.add(lblTitelFiche, 3, 3, 1, 1);

	}

	private int geefTotaalAantalMap(EdelsteenPerSpelerScherm mijnStapel) {
		int totaalSom = 0;
		for (Edelsteen e : Edelsteen.values()) {
			totaalSom += mijnStapel.getAantalFichesPerStapel().get(e);
		}
		return totaalSom;

	}

	public void setVoorgrond(double breedte, double hoogte) {



	}

}
