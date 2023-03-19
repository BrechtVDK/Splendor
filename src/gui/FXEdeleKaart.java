package gui;

import domein.Edele;

public class FXEdeleKaart extends FXKaart {

	public FXEdeleKaart(Edele info) {
		super(Edele.PRESTIGE_PUNTEN, info.bonussen());

		super.buildGui();

	}

}
