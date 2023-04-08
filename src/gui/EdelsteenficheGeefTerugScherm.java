package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import domein.Edelsteen;
import domein.Edelsteenfiche;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class EdelsteenficheGeefTerugScherm extends GridPane {
	private DomeinController dc;
	private FXEdelsteenFicheKlikbaar[] fichesInBezit;
	// Jonas: fichesTerug aangepast naar lijst ipv Array: gaf een fout omdat de
	// array een vaste grootte had en een lijst niet
	private List<FXEdelsteenFiche> fichesTerug;
	private IntegerProperty totaalInBezit;

	public EdelsteenficheGeefTerugScherm(DomeinController dc) {
		this.dc = dc;
		totaalInBezit = new SimpleIntegerProperty(0);
		buildGui();
	}

	private void buildGui() {
		this.setHgap(8);
		this.setVgap(8);
		this.setPadding(new Insets(5));

		Label lblInBezit = new Label("In\nbezit");
		lblInBezit.textProperty().bind(Bindings.concat("In bezit\n", totaalInBezit.asString()));
		lblInBezit.setTextAlignment(TextAlignment.CENTER);

		Label lblGeefTerug = new Label("Geef\nterug");
		lblGeefTerug.setTextAlignment(TextAlignment.CENTER);

		this.add(lblInBezit, 0, 0);
		this.add(lblGeefTerug, 1, 0);

		bouwFiches();

	}

	private void bouwFiches() {
		ObservableMap<Edelsteen, Integer> fichesSpelerAanDeBeurt = dc.geefSpelerAanDeBeurt()
				.getAantalEdelsteenfichesPerTypeInBezit();
		int rij = 1;
		fichesInBezit = new FXEdelsteenFicheKlikbaar[5];
		fichesTerug = new ArrayList<>();
		int teller = 0;

		for (Edelsteen edelsteen : Edelsteen.values()) {
			int aantal = fichesSpelerAanDeBeurt.get(edelsteen);
			totaalInBezit.set(totaalInBezit.get() + aantal);
			if (aantal > 0) {
				fichesInBezit[teller] = new FXEdelsteenFicheKlikbaar(edelsteen, 20, aantal);
				FXEdelsteenFiche nieuweFiche = new FXEdelsteenFiche(edelsteen, 20, 0);
				fichesTerug.add(nieuweFiche);
				nieuweFiche.setVisible(false);
				this.add(fichesInBezit[teller], 0, rij);
				this.add(nieuweFiche, 1, rij++);
			}
		}

	}

	public void verhoogFicheInBezitMetEen(FXEdelsteenFicheKlikbaar ficheInBezit) {
		// indexen van ficheTerug bepalen adhv ficheInBezit
		int kol = GridPane.getColumnIndex(ficheInBezit) + 1;
		int rij = GridPane.getRowIndex(ficheInBezit);
		// ficheTerug zoeken adhv indexen
		Node ficheTerug = this.getChildren().stream()
				.filter(node -> GridPane.getRowIndex(node) == rij && GridPane.getColumnIndex(node) == kol).findFirst()
				.orElse(null);
		// getal aanpassen en evt zichtbaar maken indien 0 was
		int huidigAantal = ((FXEdelsteenFiche) ficheTerug).getAantal();
		if (huidigAantal == 0) {
			((FXEdelsteenFiche) ficheTerug).setVisible(true);
		}
		((FXEdelsteenFiche) ficheTerug).getTxtAantal().setText(Integer.toString(huidigAantal + 1));
		totaalInBezit.set(totaalInBezit.get() - 1);
	}

	public List<Edelsteenfiche> geefTerugTeGevenFiches() {
		List<Edelsteenfiche> lijst = new ArrayList<>();
		for (FXEdelsteenFiche fiche : fichesTerug) {
			int aantal = fiche.getAantal();
			for (int i = 0; i < aantal; i++) {
				lijst.add(new Edelsteenfiche(fiche.getEdelsteen()));
			}
		}
		return lijst;
	}

	public void resetFiches() {
		this.getChildren().removeAll(fichesInBezit);
		this.getChildren().removeAll(fichesTerug);
		totaalInBezit.set(0);
		bouwFiches();
	}

}
