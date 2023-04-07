package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import domein.Edele;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class EdelenScherm extends HBox {
	private DomeinController dc;
	private List<FXEdeleKaart> fxEdelen;

	public EdelenScherm(DomeinController dc) {
		this.dc = dc;
		this.setSpacing(25);
		this.setAlignment(Pos.TOP_RIGHT);
		this.fxEdelen = new ArrayList<>();

		maakEdeleKaartenAan();
	}


	private void maakEdeleKaartenAan() {

		List<Edele> edelen = dc.geefEdelen();
		for (Edele edele : edelen) {
			FXEdeleKaart kaart = new FXEdeleKaart(edele);
			fxEdelen.add(kaart);
			this.getChildren().add(kaart);
		}
	}

	public void markeerEnMaakBeschikbareEdelenKlikbaar(List<Edele> beschikbareEdelen) {


		for (Edele beschikbareEdele : beschikbareEdelen) {
			for (FXEdeleKaart fxE : fxEdelen) {
				if (fxE.getEdele().equals(beschikbareEdele)) {
					fxE.highlight();
				}
				else
					fxE.setVisible(false);
			}
		}
	}

	public void verplaatsEdeleNaarSpeler(FXEdeleKaart fxEdele) {
		dc.verplaatsEdeleVanSpelNaarSpeler(fxEdele.getEdele());
		this.getChildren().remove(fxEdele);
		zetEdelenTerugZichtbaar();
	}

	private void zetEdelenTerugZichtbaar() {
		for (FXEdeleKaart fxE : fxEdelen) {
			fxE.setVisible(true);
		}
	}
}