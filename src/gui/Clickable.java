package gui;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public interface Clickable {

	default void onHovered(Region region, double scale) {
		// transities instellen
		ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), region);
		scaleUp.setToX(scale);
		scaleUp.setToY(scale);
		ScaleTransition scaleDown = new ScaleTransition(Duration.millis(50), region);
		scaleDown.setToX(1);
		scaleDown.setToY(1);

		// timeline zorgt ervoor dat als er heel snel met de muis bewogen wordt, de pane
		// toch terugschaalt
		// Oplossing dankzij chatGPT
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
			scaleDown.play();
		}));

		// cursor aanpassen + groter
		region.setOnMouseEntered(event -> {
			region.setCursor(Cursor.HAND);
			scaleUp.play();
			timeline.stop();
		});
		// terug normale grootte
		region.setOnMouseExited(event -> {
			timeline.play();
		});
	}

	default void onClicked(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			onLeftClicked();
		}
	}

	// abstracte methode
	void onLeftClicked();
}
