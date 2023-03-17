package gui;

import domein.DomeinController;
import domein.Niveau;
import domein.OntwikkelingskaartRecord;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class TafelScherm extends GridPane {
	private DomeinController dc;
	private Label[] lblStapels;

	public TafelScherm(DomeinController dc) {
		this.dc = dc;

		this.setVgap(25);
		this.setHgap(25);

		lblStapels = new Label[Niveau.values().length];
		for (int i = 0; i < lblStapels.length; i++) {
			lblStapels[i] = new Label("• ".repeat(lblStapels.length - i));
			lblStapels[i].setAlignment(Pos.BOTTOM_CENTER);
			lblStapels[i].setPrefSize(500, 100);
			lblStapels[i].setMaxWidth(100);
			lblStapels[i].getStyleClass().add("ontwikkelingskaart");
			this.add(lblStapels[i], 0, i);
		}

		geefHuidigeTafelWeer();

	}

	private void geefHuidigeTafelWeer() {
		OntwikkelingskaartRecord[][] kaarten = dc.geefZichtbareOntwikkelingskaarten();

		for (int rij = 0; rij < kaarten.length; rij++) {
			for (int kolom = 0; kolom < kaarten[rij].length; kolom++) {
				FXOntwikkelingskaart kaart = new FXOntwikkelingskaart(kaarten[rij][kolom]);
				eventsOntwikkelingskaartInstellen(kaart);
				this.add(kaart, kolom + 1, rij);
			}
		}

	}

	// Deze code gaan we wss ook voor de edelen en de fiches nodig hebben. ==> nog
	// in aparte klasse (of interface Clickable?) stoppen?
	private void eventsOntwikkelingskaartInstellen(FXOntwikkelingskaart kaart) {
		// transities instellen
		ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), kaart);
		scaleUp.setToX(1.2);
		scaleUp.setToY(1.2);
		ScaleTransition scaleDown = new ScaleTransition(Duration.millis(50), kaart);
		scaleDown.setToX(1);
		scaleDown.setToY(1);

		// timeline zorgt ervoor dat als er snel met de muis bewogen wordt, de kaart
		// toch een normale grote wordt
		// Oplossing dankzij chatGPT
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
			scaleDown.play();
		}));

		// cursor aanpassen + groter bij hoveren
		kaart.setOnMouseEntered(event -> {
			kaart.setCursor(Cursor.HAND);
			scaleUp.play();
			timeline.stop();
		});
		// terug normale grootte
		kaart.setOnMouseExited(event -> {
			timeline.play();
		});

		kaart.setOnMouseClicked(this::ontwikkelingskaartGeklikt);
	}

	private void ontwikkelingskaartGeklikt(MouseEvent event) {
		// TODO
		// Enkel linker muisknop heeft effect
		if (event.getButton() == MouseButton.PRIMARY) {
			System.out.println("test");
		}

	}
}
