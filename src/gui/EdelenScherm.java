package gui;

import java.util.List;

import domein.DomeinController;
import domein.Edele;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class EdelenScherm extends HBox {
	private DomeinController dc;

	public EdelenScherm(DomeinController dc) {
		this.dc = dc;
		this.setSpacing(25);
		this.setAlignment(Pos.TOP_RIGHT);

		maakEdeleKaartenAan();




	}

	private void maakEdeleKaartenAan() {
		List<Edele> edelen = dc.geefEdelen();
		for (Edele edele : edelen) {
			FXEdeleKaart kaart = new FXEdeleKaart(edele);
			this.getChildren().add(kaart);
		}
	}

}
