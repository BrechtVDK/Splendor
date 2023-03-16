package gui;

import domein.DomeinController;
import domein.Edelsteen;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EdelsteenFicheScherm extends VBox {
	private DomeinController dc;

	private Button[] btnEdelsteenfiches;

	public EdelsteenFicheScherm(DomeinController dc) {
		this.dc = dc;

		this.setSpacing(10);

		btnEdelsteenfiches = new Button[Edelsteen.values().length];
		for (int i = 0; i < btnEdelsteenfiches.length; i++) {
			String soort = Edelsteen.values()[i].getSoort();
			String rgb = Edelsteen.values()[i].getRgb();
			btnEdelsteenfiches[i] = new Button(soort);
			btnEdelsteenfiches[i].getStyleClass().add("edelsteenfiche");
			// Brecht: overbodig momenteel. Aangepast dat de kleuren uit de enum Edelsteen
			// komen
			// btnEdelsteenfiches[i].setId(soort);
			btnEdelsteenfiches[i].setStyle(String.format("-fx-background-color: rgb%s", rgb));
			btnEdelsteenfiches[i].setPrefSize(100, 100);
		}

		// Volgorde: WIT/DIAMANT - ROOD/ROBIJN - BLAUW/SAFFIER - GROEN/SMARAGD -
		// ZWART/ONYX
		// Idealiter via add.All, maar dan moet volgorde in enum aangepast worden.
		// Bijgevolg ook de volgorde van aantallen in Spel.maakEdelenAan() en in
		// StapelOntwikkelingskaarten.maakOntwikkelingskaartenAan(); ==> veel werk en
		// kans op fouten
		this.getChildren().add(btnEdelsteenfiches[1]);
		this.getChildren().add(btnEdelsteenfiches[4]);
		this.getChildren().add(btnEdelsteenfiches[2]);
		this.getChildren().add(btnEdelsteenfiches[0]);
		this.getChildren().add(btnEdelsteenfiches[3]);

	}

}
