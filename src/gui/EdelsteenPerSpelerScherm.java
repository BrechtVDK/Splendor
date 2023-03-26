package gui;

import java.util.Map;

import domein.Edelsteen;
import domein.Speler;
import javafx.scene.layout.HBox;

public class EdelsteenPerSpelerScherm extends HBox {
	private Speler speler;
	private Map<Edelsteen, Integer> aantalFichesPerStapel;

	public EdelsteenPerSpelerScherm(Speler speler, Boolean isBonus) {
		this.speler = speler;
		if (!isBonus)
			setAantalFichesPerStapel(speler.getAantalEdelsteenfichesPerTypeInBezit());
		else
			setAantalFichesPerStapel(speler.getAantalBonussenPerTypeInBezit());

		buildGui();
	}

	public Map<Edelsteen, Integer> getAantalFichesPerStapel() {
		return aantalFichesPerStapel;
	}

	public void setAantalFichesPerStapel(Map<Edelsteen, Integer> aantalFichesPerStapel) {
		this.aantalFichesPerStapel = aantalFichesPerStapel;
	}

	private void buildGui() {
		this.setSpacing(5);

		for (Edelsteen e : Edelsteen.values()) {
			int aantal = aantalFichesPerStapel.get(e);
			FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFiche(e, 15, aantal);
			this.getChildren().add(fxEdelsteenFiche);
		}
	}

}

