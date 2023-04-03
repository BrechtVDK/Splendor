package gui;

import domein.Edelsteenfiche;
import domein.Ontwikkelingskaart;
import javafx.geometry.Pos;

public class FXOntwikkelingskaart extends FXKaart implements Clickable {

	private Edelsteenfiche bonus;
	// index bevat {kolom, rij}
	private int[] index;
	private TafelScherm tafel;

	public FXOntwikkelingskaart(Ontwikkelingskaart info, int[] index, TafelScherm tafel) {
		super(info.prestigePunten(), info.edelsteenfiches());
		bonus = info.bonus();
		this.index = index;
		this.tafel = tafel;
		buildExtras();
	}

	public int[] getIndex() {
		return index;
	}

	private void buildExtras() {
		plaatsBonus();
		String rgb = bonus.edelsteen().getRgb();
		this.getStyleClass().add("ontwikkelingskaart");
		// achtergrond instellen adhv kleur bonus. Witte bonus => rgb(192,192,192)
		this.setStyle(String.format("-fx-background-color: linear-gradient(rgba(255,255,255), rgba%s);",
				rgb.equals("(255,255,255)") ? "(192,192,192)" : rgb));

		// interface
		this.backgroundProperty();
		this.setOnMouseClicked(this::onClicked);
		this.onHovered(this, 1.1);
	}

	private void plaatsBonus() {
		FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFiche(bonus.edelsteen(), 15);
		fxEdelsteenFiche.setAlignment(Pos.TOP_RIGHT);
		this.add(fxEdelsteenFiche, 1, 0);
	}

	@Override
	public void onLeftClicked() {
		// Eerst kijken waar de kaart zich bevindt, dan naar respectievelijke plaats
		// verplaatsen.
		String parent = this.getParent().getClass().getSimpleName();
		if (parent.equals("TafelScherm")) {
			tafel.verplaatsKaartNaarLinkerInfoScherm(this);
			tafel.maakKaartenOnKlikbaar();
			((LinkerInfoScherm) this.getParent()).activeerBevestigKnop();
		}
		else {
			((LinkerInfoScherm) this.getParent()).deactiveerBevestigKnop();
			tafel.voegFouteKaartTerugToeVanLinkerInfoScherm(this);
			tafel.maakKaartenKlikbaar();

		}


	}


}