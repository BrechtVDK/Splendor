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
	private FXOntwikkelingskaart gekozenKaart;

	private Label lblSpelerAanDeBeurt, lblKeuze, lblInfo;
	private Button btnKaart, btnFiche, btnPas, btnBevestig;

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
		btnBevestig = new Button("Bevestig keuze");
		btnBevestig.setVisible(false);

		btnKaart.setOnAction(this::kiesKaartGeklikt);
		btnFiche.setOnAction(this::kiesFicheGeklikt);
		btnPas.setOnAction(this::pasGeklikt);

		this.getChildren().addAll(lblSpelerAanDeBeurt, lblKeuze, btnKaart, btnFiche, btnPas, btnBevestig, lblInfo);
	}

	private void pasGeklikt(ActionEvent e) {
		hs.bepaalVolgendeSpeler();
		lblSpelerAanDeBeurt
				.setText(String.format("Speler aan de beurt: %s", dc.geefSpelerAanDeBeurt().getGebruikersnaam()));
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
		btnKaart.setVisible(false);
		btnFiche.setVisible(false);
		btnPas.setVisible(false);
		lblKeuze.setText("Kies een kaart van de tafel");
		btnBevestig.setVisible(true);
		btnBevestig.setOnAction((event) -> bevestigGeklikt(event, "kaart"));
	}

	private void kiesFicheGeklikt(ActionEvent e) {
		hs.maakKaartenOnKlikbaar();
	}

	public void voegOntwikkelingskaartToe(FXOntwikkelingskaart kaart) {
		this.gekozenKaart = kaart;
		this.getChildren().add(gekozenKaart);
	}

	public void verwijderOntwikkelingskaart() {
		gekozenKaart = null;
	}

	private void bevestigGeklikt(ActionEvent e, String spelerKeuze) {
		if (spelerKeuze.equals("kaart")) {
			if (gekozenKaart.equals(null))
				lblInfo.setText("Geen kaart geselecteerd");
			else
				hs.verplaatsOntwikkelingskaartVanTafelNaarSpeler(gekozenKaart);
		}
	}

	public void toonFoutmelding(String foutmelding) {
		lblInfo.setText(foutmelding);
	}

	}

