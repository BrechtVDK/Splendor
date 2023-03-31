package gui;


import domein.DomeinController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class LinkerInfoScherm extends VBox {
	private DomeinController dc;
	private Hoofdscherm hs;
	private SpelerScoreScherm[] spelerScoreSchermen;

	private Label lblSpelerAanDeBeurt, lblKeuze, lblInfo;
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
		lblInfo = new Label("");

		btnKaart = new Button("Ik koop een ontwikkelingskaart");
		btnFiche = new Button("Ik neem edelsteenfiches");
		btnPas = new Button("Ik pas en sla deze ronde over");

		btnKaart.setOnAction(this::kiesKaartGeklikt);
		btnFiche.setOnAction(this::kiesFicheGeklikt);
		btnPas.setOnAction(this::pasGeklikt);

		this.getChildren().addAll(lblSpelerAanDeBeurt, lblKeuze, btnKaart, btnFiche, btnPas, lblInfo);
	}

	private void pasGeklikt(ActionEvent e) {
		dc.bepaalVolgendeSpeler();
		lblInfo.setText(
				String.format("Je besliste om te passen, de volgende speler is %s.", dc.geefSpelerAanDeBeurt()));

		// tijdlijn om pasboodschap na 5 seconden te wissen (andere speler is dan reeds
		// aan de beurt)
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
			lblInfo.setText("");
		}));
		timeline.play();
	}

	private void kiesKaartGeklikt(ActionEvent e) {
		hs.maakKaartenKlikbaar();
	}

	private void kiesFicheGeklikt(ActionEvent e) {

	}

	public void voegOntwikkelingskaartToe(FXOntwikkelingskaart kaart) {
		this.getChildren().add(kaart);
	}

	}

