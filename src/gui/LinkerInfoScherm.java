package gui;


import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LinkerInfoScherm extends VBox {
	private DomeinController dc;
	private Hoofdscherm hs;

	private Label lblSpelerAanDeBeurt, lblKeuze;
	private Button btnKaart, btnFiche, btnPas;

	public LinkerInfoScherm(DomeinController dc, Hoofdscherm hs) {
		this.dc = dc;
		this.hs = hs;
		buildGui();
	}

	private void buildGui() {
		this.setSpacing(10);
		this.setAlignment(Pos.TOP_CENTER);

		lblSpelerAanDeBeurt = new Label(
				String.format("Speler aan de beurt: %s", dc.geefSpelerAanDeBeurt().getGebruikersnaam()));
		lblKeuze = new Label("Wat wil je doen in deze beurt?");

		btnKaart = new Button("Ik koop een ontwikkelingskaart");
		btnFiche = new Button("Ik neem edelsteenfiches");
		btnPas = new Button("Ik pas en sla deze ronde over");
		btnPas.setOnAction(this::pasGeklikt);

		this.getChildren().addAll(lblSpelerAanDeBeurt, lblKeuze, btnKaart, btnFiche, btnPas);
	}

	private void pasGeklikt(ActionEvent e) {

	}
}
