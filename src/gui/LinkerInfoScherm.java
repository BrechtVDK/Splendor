package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LinkerInfoScherm extends VBox {
	private DomeinController dc;
	private Button btnSpeelSpel;

	public LinkerInfoScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);

		btnSpeelSpel = new Button("speel spel");
		btnSpeelSpel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 18));
		btnSpeelSpel.setOnAction(this::speelSpelGeklikt);
		this.getChildren().add(btnSpeelSpel);
	}

	private void speelSpelGeklikt(ActionEvent event) {
		dc.speelSpel();
		btnSpeelSpel.setVisible(false);
	}
}
