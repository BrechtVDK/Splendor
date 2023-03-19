package gui;

import domein.Edelsteenfiche;
import domein.Ontwikkelingskaart;
import javafx.geometry.Pos;

public class FXOntwikkelingskaart extends FXKaart {

	private Edelsteenfiche bonus;


	public FXOntwikkelingskaart(Ontwikkelingskaart info) {
		super(info.prestigePunten(), info.edelsteenfiches());
		bonus = info.bonus();
		super.buildGui();
		buildExtras();
	}

	private void buildExtras() {
		plaatsBonus();
	}


	private void plaatsBonus() {
		FXEdelsteenFiche fxEdelsteenFiche = new FXEdelsteenFiche(bonus.edelsteen(), 15);
		fxEdelsteenFiche.setAlignment(Pos.TOP_RIGHT);

		this.add(fxEdelsteenFiche, 1, 0);
	}



}