package gui;

import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.StartUpGui;

public class EindeSpelScherm extends VBox {
	String winnaars;
	boolean eenWinnaar;
	Hoofdscherm hs;

	public EindeSpelScherm(List<String> lijstWinnaars, Hoofdscherm hs) {
		StringBuilder sbWinaars = new StringBuilder();
		sbWinaars.append(lijstWinnaars.stream().limit(lijstWinnaars.size() - 1).collect(Collectors.joining(", ")))
				.append(" en ").append(lijstWinnaars.get(lijstWinnaars.size() - 1));
		eenWinnaar = lijstWinnaars.size() == 1;
		winnaars = eenWinnaar ? lijstWinnaars.get(0) : sbWinaars.toString();
		this.hs = hs;
		buildGui();
	}

	private void buildGui() {
		this.setPadding(new Insets(5));
		this.setSpacing(10);
		this.setAlignment(Pos.TOP_CENTER);
		Image imgSplendor = new Image(getClass().getResourceAsStream("/images/eindeSpel.jpg"));
		this.setBackground(new Background(new BackgroundImage(imgSplendor, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));


		Label lblEindeSpel = new Label("EINDE SPEL");
		lblEindeSpel.setId("titel");
		// marge onder = 90 -> winnaars in het midden
		VBox.setMargin(lblEindeSpel, new Insets(0, 0, 90, 0));

		Label lblWinnaars = new Label();
		lblWinnaars.setText(String.format("%s %s gewonnen!", winnaars, eenWinnaar ? "is" : "hebben"));
		lblWinnaars.setId("winnaars");
		lblWinnaars.setTextAlignment(TextAlignment.CENTER);
		lblWinnaars.setWrapText(true);
		// marge onder = 100 -> knoppen onderaan
		VBox.setMargin(lblWinnaars, new Insets(0, 0, 100, 0));

		Button btnSpeelOpnieuw = new Button("Speel opnieuw");
		btnSpeelOpnieuw.setOnAction(this::speelOpnieuwGeklikt);
		Button btnAfsluiten = new Button("Afsluiten");
		btnAfsluiten.setOnAction(this::afsluitenGeklikt);

		HBox hboxButtons = new HBox();
		hboxButtons.setAlignment(Pos.CENTER);
		hboxButtons.setSpacing(75);
		hboxButtons.getChildren().addAll(btnSpeelOpnieuw, btnAfsluiten);

		this.getChildren().addAll(lblEindeSpel, lblWinnaars, hboxButtons);
		this.lookupAll(".label").forEach(label -> label.getStyleClass().add("eindeSpelSchermLabels"));
	}

	private void speelOpnieuwGeklikt(ActionEvent event) {
		// huidige vensters sluiten
		Stage stage = (Stage) this.getScene().getWindow();
		stage.close();
		Stage stageHs = (Stage) hs.getScene().getWindow();
		stageHs.close();

		// nieuwe app starten
		StartUpGui newApp = new StartUpGui();
		newApp.start(new Stage());

	}

	private void afsluitenGeklikt(ActionEvent event) {
		Platform.exit();
	}

}
