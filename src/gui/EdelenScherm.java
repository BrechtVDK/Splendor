package gui;

import java.util.List;

import domein.DomeinController;
import domein.Edele;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class EdelenScherm extends HBox {
	private DomeinController dc;

	public EdelenScherm(DomeinController dc) {
		this.dc = dc;
		this.setSpacing(25);
		this.setAlignment(Pos.TOP_RIGHT);

		maakEdeleKaartenAan();




	}


	private void maakEdeleKaartenAan() {
		List<Edele> edelen = dc.geefEdelen();
		for (Edele edele : edelen) {
			FXEdeleKaart kaart = new FXEdeleKaart(edele);
			eventsEdeleKaartInstellen(kaart);
			this.getChildren().add(kaart);
		}
	}

	private void eventsEdeleKaartInstellen(FXEdeleKaart kaart) {
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

		kaart.setOnMouseClicked(this::edeleKaartGeklikt);
	}

	private void edeleKaartGeklikt(MouseEvent event) {
		// TODO
		// Enkel linker muisknop heeft effect
		if (event.getButton() == MouseButton.PRIMARY) {
			System.out.println("test");
		}

	}
}