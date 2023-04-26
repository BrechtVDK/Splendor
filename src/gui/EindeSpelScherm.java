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
import resources.Taal;

public class EindeSpelScherm extends VBox {
	private String winnaars;
	private boolean eenWinnaar;
	private Hoofdscherm hs;

	public EindeSpelScherm(List<String> lijstWinnaars, Hoofdscherm hs) {
		StringBuilder sbWinaars = new StringBuilder();
		sbWinaars.append(lijstWinnaars.stream().limit(lijstWinnaars.size() - 1).collect(Collectors.joining(", "))) //$NON-NLS-1$
				.append(Taal.vertaling("EindeSpelScherm.1")).append(" ") //$NON-NLS-1$
				.append(lijstWinnaars.get(lijstWinnaars.size() - 1));
		eenWinnaar = lijstWinnaars.size() == 1;
		winnaars = eenWinnaar ? lijstWinnaars.get(0) : sbWinaars.toString();
		this.hs = hs;
		buildGui();
	}

	private void buildGui() {
		this.setPadding(new Insets(5));
		this.setSpacing(10);
		this.setAlignment(Pos.TOP_CENTER);
		Image imgSplendor = new Image(getClass().getResourceAsStream("/images/eindeSpel.jpg")); //$NON-NLS-1$
		this.setBackground(new Background(new BackgroundImage(imgSplendor, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		Label lblEindeSpel = new Label(Taal.vertaling("EindeSpelScherm_titel")); //$NON-NLS-1$
		lblEindeSpel.setId("EindeSpelScherm_titel"); //$NON-NLS-1$
		// marge onder = 90 -> winnaars in het midden
		VBox.setMargin(lblEindeSpel, new Insets(0, 0, 90, 0));

		Label lblWinnaars = new Label();
		lblWinnaars.setText(String.format("%s %s", winnaars, //$NON-NLS-1$
				eenWinnaar ? Taal.vertaling("EindeSpelScherm.6") : Taal.vertaling("EindeSpelScherm.7")));
		lblWinnaars.setId("EindeSpelScherm_winnaars"); //$NON-NLS-1$
		lblWinnaars.setTextAlignment(TextAlignment.CENTER);
		lblWinnaars.setWrapText(true);
		// marge onder = 100 -> knoppen onderaan
		VBox.setMargin(lblWinnaars, new Insets(0, 0, 100, 0));

		Button btnSpeelOpnieuw = new Button(Taal.vertaling("EindeSpelScherm.9")); //$NON-NLS-1$
		btnSpeelOpnieuw.setId("EindeSpelScherm.9"); //$NON-NLS-1$
		btnSpeelOpnieuw.setMinWidth(50);
		btnSpeelOpnieuw.setOnAction(this::speelOpnieuwGeklikt);
		Button btnAfsluiten = new Button(Taal.vertaling("EindeSpelScherm.10")); //$NON-NLS-1$
		btnAfsluiten.setId("EindeSpelScherm.10"); //$NON-NLS-1$
		btnAfsluiten.setMinWidth(50);
		btnAfsluiten.setOnAction(this::afsluitenGeklikt);

		HBox hboxButtons = new HBox();
		hboxButtons.setAlignment(Pos.CENTER);
		hboxButtons.setSpacing(75);
		hboxButtons.getChildren().addAll(btnSpeelOpnieuw, btnAfsluiten);

		this.getChildren().addAll(lblEindeSpel, lblWinnaars, hboxButtons);
		this.lookupAll(".label").forEach(label -> label.getStyleClass().add("eindeSpelSchermLabels")); //$NON-NLS-1$ //$NON-NLS-2$
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
