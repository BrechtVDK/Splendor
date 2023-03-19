package gui;

import domein.Edelsteenfiche;
import domein.Ontwikkelingskaart;
import javafx.geometry.Pos;

public class FXOntwikkelingskaart extends FXKaart implements Clickable {

	private Edelsteenfiche bonus;


	public FXOntwikkelingskaart(Ontwikkelingskaart info) {
		super(info.prestigePunten(), info.edelsteenfiches());
		bonus = info.bonus();
		buildExtras();

	}

	private void buildExtras() {
		plaatsBonus();
		this.getStyleClass().add("ontwikkelingskaart");
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
		System.out.println("Ontwikkelingskaart");
	}


}