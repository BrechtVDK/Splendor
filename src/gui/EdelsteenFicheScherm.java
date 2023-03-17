package gui;

import java.util.Map;

import domein.DomeinController;
import domein.Edelsteen;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class EdelsteenFicheScherm extends VBox {
	private DomeinController dc;

	private Button[] btnEdelsteenfiches;
	private Map<Edelsteen, Integer> aantalFichesPerStapel;

	public EdelsteenFicheScherm(DomeinController dc) {
		this.dc = dc;
		this.aantalFichesPerStapel = dc.geefAantalFichesPerStapel();

		buildGui();


//Jonas: heb het toch aangepast, denk niet dat volgorde hier echt belangrijk is.

//		btnEdelsteenfiches = new Button[Edelsteen.values().length];
//		for (int i = 0; i < btnEdelsteenfiches.length; i++) {
//			String soort = Edelsteen.values()[i].getSoort();
//			String rgb = Edelsteen.values()[i].getRgb();
//			btnEdelsteenfiches[i] = new Button(soort);
//			btnEdelsteenfiches[i].getStyleClass().add("edelsteenfiche");
//			// Brecht: overbodig momenteel. Aangepast dat de kleuren uit de enum Edelsteen
//			// komen
//			// btnEdelsteenfiches[i].setId(soort);
//			btnEdelsteenfiches[i].setStyle(String.format("-fx-background-color: rgb%s", rgb));
//			btnEdelsteenfiches[i].setPrefSize(75, 75);
//		}

		// Volgorde: WIT/DIAMANT - ROOD/ROBIJN - BLAUW/SAFFIER - GROEN/SMARAGD -
		// ZWART/ONYX
		// Idealiter via add.All, maar dan moet volgorde in enum aangepast worden.
		// Bijgevolg ook de volgorde van aantallen in Spel.maakEdelenAan() en in
		// StapelOntwikkelingskaarten.maakOntwikkelingskaartenAan(); ==> veel werk en
		// kans op fouten
//		this.getChildren().add(btnEdelsteenfiches[1]);
//		this.getChildren().add(btnEdelsteenfiches[4]);
//		this.getChildren().add(btnEdelsteenfiches[2]);
//		this.getChildren().add(btnEdelsteenfiches[0]);
//		this.getChildren().add(btnEdelsteenfiches[3]);

	}

	private void buildGui() {
		this.setSpacing(10);

		for (Edelsteen e : Edelsteen.values()) {
			String rgb = e.getRgb();
			Button btnEdelsteenfiche = new Button(Integer.toString(aantalFichesPerStapel.get(e)));
			btnEdelsteenfiche.getStyleClass().add("edelsteenfiche");
			btnEdelsteenfiche.getStyleClass().add("edelsteenfichegroot");
			btnEdelsteenfiche.setStyle(String.format("-fx-background-color: rgb%s", rgb));
			btnEdelsteenfiche.setPrefSize(75, 75);
			eventsEdelsteenFicheInstellen(btnEdelsteenfiche);
			this.getChildren().add(btnEdelsteenfiche);
		}
	}

	private void eventsEdelsteenFicheInstellen(Button edelsteenFiche) {
		// transities instellen
		ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), edelsteenFiche);
		scaleUp.setToX(1.2);
		scaleUp.setToY(1.2);
		ScaleTransition scaleDown = new ScaleTransition(Duration.millis(50), edelsteenFiche);
		scaleDown.setToX(1);
		scaleDown.setToY(1);

		// timeline zorgt ervoor dat als er snel met de muis bewogen wordt, de kaart
		// toch een normale grote wordt
		// Oplossing dankzij chatGPT
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
			scaleDown.play();
		}));

		// cursor aanpassen + groter bij hoveren
		edelsteenFiche.setOnMouseEntered(event -> {
			edelsteenFiche.setCursor(Cursor.HAND);
			scaleUp.play();
			timeline.stop();
		});
		// terug normale grootte
		edelsteenFiche.setOnMouseExited(event -> {
			timeline.play();
		});

		edelsteenFiche.setOnMouseClicked(this::edelsteenFicheGeklikt);
	}

	private void edelsteenFicheGeklikt(MouseEvent event) {
		// TODO
		// Enkel linker muisknop heeft effect
		if (event.getButton() == MouseButton.PRIMARY) {
			System.out.println("test");
		}

	}

}
