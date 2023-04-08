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
		fxEdelen.stream().filter(fxe -> beschikbareEdelen.contains(fxe.getEdele())).forEach(fxe -> fxe.highlight());
	}

	public void verplaatsEdeleNaarSpeler(FXEdeleKaart fxEdele) {
		dc.verplaatsEdeleVanSpelNaarSpeler(fxEdele.getEdele());
		this.getChildren().remove(fxEdele);
		zetEdelenTerugNormaal();
	}

	private void zetEdelenTerugNormaal() {
		for (FXEdeleKaart fxE : fxEdelen) {
			fxE.setEffect(null);
			fxE.setMouseTransparent(true);
		}
	}
}