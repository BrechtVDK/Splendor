package gui;

import domein.DomeinController;
import domein.Edelsteen;
import domein.Edelsteenfiche;
import javafx.beans.binding.IntegerBinding;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class FXEdelsteenFicheKlikbaar extends FXEdelsteenFiche implements Clickable {
	private DomeinController dc;

	public FXEdelsteenFicheKlikbaar(Edelsteen edelsteen, double radius) {
		super(edelsteen, radius);
		// interface
		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);

	}

	public FXEdelsteenFicheKlikbaar(Edelsteen edelsteen, double radius, int aantal) {
		super(edelsteen, radius, aantal);

		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);
	}

	// constructor voor stapels
	public FXEdelsteenFicheKlikbaar(Edelsteen edelsteen, double radius, DomeinController dc) {
		super(edelsteen, radius);
		this.dc = dc;
		// binding
		super.txtAantal = new Text();
		IntegerBinding aantal = dc.geefAantalFichesPerStapel().get(edelsteen);
		super.txtAantal.textProperty().bind(aantal.asString());
		// als aantal wijzigt -> visibility checken
		aantal.addListener((observable, oldValue, newValue) -> {
			checkVisibility();
		});

		super.stelTekstLayOutIn();
		this.getChildren().add(txtAantal);

		// interface
		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);

	}

	// Brecht: heb deze gekoppeld aan IntegerBinding aantal via listener, zo moet je
	// deze
	// niet meer ergens anders expliciet aanroepen bij wijziging
	protected void checkVisibility() {
		boolean visible = (getAantal() == 0) ? false : true;
		this.setVisible(visible);
	}

	@Override
	public void onLeftClicked() {
		String parent = this.getParent().getClass().getSimpleName();
		switch (parent) {
		case "EdelsteenFicheScherm" -> verplaatsFichesVanEdelsteenFicheSchermNaarLinkerInfoScherm();
		case "LinkerInfoScherm" -> verplaatsFicheVanLinkerInfoSchermNaarStapel();
		case "EdelsteenficheGeefTerugScherm" -> {
			if (GridPane.getColumnIndex(this) == 0) {
				verlaagFicheInBezitVerhoogFicheGeefTerug();
			} else {
				verhoogFicheInBezitVerlaagFicheGeefTerug();
			}
		}
		}
	}

	private void verplaatsFichesVanEdelsteenFicheSchermNaarLinkerInfoScherm() {
		dc.verwijderEdelsteenficheVanStapel(new Edelsteenfiche(super.getEdelsteen()));
		FXEdelsteenFicheKlikbaar nieuweFiche = new FXEdelsteenFicheKlikbaar(super.getEdelsteen(), 20);
		((EdelsteenFicheScherm) this.getParent()).voegEdelsteenficheToeAanLinkerInfoScherm(nieuweFiche);
	}

	private void verplaatsFicheVanLinkerInfoSchermNaarStapel() {
		// fiche bevat getal: bij validatie >10 fiches in bezit
		// fiche uit spelervoorraad nemen en terugleggen op stapel
		if (this.getChildren().contains(txtAantal)) {
			// ((LinkerInfoScherm) this.getParent())
			// .verplaatsEdelsteenfichesVanSpelerNaarSpel
			((LinkerInfoScherm) this.getParent())
					.voegEdelsteenficheTerugToeAanStapel(new Edelsteenfiche(super.getEdelsteen()));

		}
		// fiche bevat geen getal -> klikken = terugleggen op stapel
		else {
			((LinkerInfoScherm) this.getParent())
					.voegEdelsteenficheTerugToeAanStapel(new Edelsteenfiche(super.getEdelsteen()));
			((LinkerInfoScherm) this.getParent()).maakFichesKlikbaarInEdelsteenficheScherm();
			((LinkerInfoScherm) this.getParent()).verwijderEnkeleFiche(this);
		}
	}

	private void verlaagFicheInBezitVerhoogFicheGeefTerug() {
		int aantal = getAantal() - 1;
		// aantal aanpassen tem 0
		if (aantal >= 0) {
			getTxtAantal().setText(Integer.toString(aantal));
			((EdelsteenficheGeefTerugScherm) this.getParent()).verhoogFicheTerug(this);
		}
		// gelijk aan nul, niet meer klikbaar
		if (aantal == 0) {
			this.setMouseTransparent(true);
		}
	}

	private void verhoogFicheInBezitVerlaagFicheGeefTerug() {
		int aantal = getAantal() - 1;
		// aantal aanpassen tem 0
		if (aantal >= 0) {
			getTxtAantal().setText(Integer.toString(aantal));
			((EdelsteenficheGeefTerugScherm) this.getParent()).verhoogFicheInBezit(this);
		}
		// gelijk aan nul, niet meer zichtbaar
		if (aantal == 0) {
			this.setVisible(false);
		}

	}

}
