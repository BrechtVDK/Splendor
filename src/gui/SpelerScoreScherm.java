package gui;

import domein.DomeinController;
import domein.Speler;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import resources.Taal;

public class SpelerScoreScherm extends GridPane {
	private DomeinController dc;
	private Speler speler;
	// private int totaalAantalPrestigepunten;
	private final boolean isStartSpeler;

	// fx elementen
	private Label lblSpelerNaam, lblAantalPrestigepunten, lblAantalEdelenInBezit, lblTitelBonus, lblTitelFiche,
			lblIsStartspeler, lblAantalOntwikkelingskaarten;
	private Label lblAantalPrestigepuntenTekst, lblAantalEdelenInBezitTekst, lblAantalOntwikkelingskaartenTekst;
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
		this.getStyleClass().add("scoreKaart"); //$NON-NLS-1$
		this.setId(String.format("%s", isSpelerAanDeBeurt() ? "aanBeurt" : "nietAanBeurt")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.setPadding(new Insets(5));

		// Labels
		lblIsStartspeler = new Label(String.format("%s", isStartSpeler ? Taal.vertaling("SpelerScoreScherm.5") : "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		lblIsStartspeler.setId(isStartSpeler ? "SpelerScoreScherm.5" : null); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		// zorgt voor zelfde uitlijning bij startspeler als niet-startspeler
		lblIsStartspeler.setMinWidth(90);
		lblSpelerNaam = new Label(speler.getGebruikersnaam());
		
		// binding ok
		lblAantalPrestigepuntenTekst = new Label(Taal.vertaling("SpelerScoreScherm.7"));
		lblAantalPrestigepuntenTekst.setId("SpelerScoreScherm.7");//$NON-NLS-1$
//		StringExpression prestigepuntenTekst = Bindings.format("%s %d", Taal.vertaling("SpelerScoreScherm.7"),
//				speler.prestigepuntenProperty());// $NON-NLS-1$
//		lblAantalPrestigepunten.textProperty()
//				.bind(prestigepuntenTekst);
//		lblAantalPrestigepunten.textProperty()
//				.bind(Bindings.concat(Taal.vertaling("SpelerScoreScherm.7"), " ", speler.prestigepuntenProperty())); //$NON-NLS-1$
		lblAantalPrestigepunten = new Label();
		lblAantalPrestigepunten.textProperty().bind(Bindings.concat(": ", speler.prestigepuntenProperty())); //$NON-NLS-1$
		HBox hboxAantalPrestigepunten = new HBox();
		hboxAantalPrestigepunten.getChildren().addAll(lblAantalPrestigepuntenTekst, lblAantalPrestigepunten);
		// binding ok
		lblAantalEdelenInBezit = new Label();
		lblAantalEdelenInBezit.textProperty().bind(Bindings.concat(Taal.vertaling("SpelerScoreScherm.8"), " ", //$NON-NLS-1$
				Bindings.size(speler.getEdelenInBezit())));
		lblAantalEdelenInBezit.setId("SpelerScoreScherm.8"); //$NON-NLS-1$
		lblTitelBonus = new Label(String.format("%s", Taal.vertaling("SpelerScoreScherm.10"))); //$NON-NLS-1$ //$NON-NLS-2$
		lblTitelBonus.setId("SpelerScoreScherm.10"); //$NON-NLS-1$
		lblTitelFiche = new Label(String.format("%s", Taal.vertaling("SpelerScoreScherm.12"))); //$NON-NLS-1$ //$NON-NLS-2$
		lblTitelFiche.setId("SpelerScoreScherm.12"); //$NON-NLS-1$
		// binding ok
		lblAantalOntwikkelingskaarten = new Label();
		lblAantalOntwikkelingskaarten.textProperty().bind(Bindings.concat(Taal.vertaling("SpelerScoreScherm.13"), " ", //$NON-NLS-1$
				Bindings.size(speler.getOntwikkelingskaartenInBezit())));
		lblAantalOntwikkelingskaarten.setId("SpelerScoreScherm.13"); //$NON-NLS-1$
		this.add(lblIsStartspeler, 0, 1);
		this.add(lblSpelerNaam, 0, 0);
//		this.add(lblAantalPrestigepunten, 1, 0);
		this.add(hboxAantalPrestigepunten, 1, 0);
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
