package domein;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StapelOntwikkelingskaarten {
	private ObservableList<Ontwikkelingskaart> ontwikkelingskaarten;
	private Niveau niveau;

	public StapelOntwikkelingskaarten(Niveau niveau) {
		this.niveau = niveau;
		this.ontwikkelingskaarten = FXCollections.observableArrayList();
		maakOntwikkelingskaartenAan();
		schudDeStapel();
	}

	// Haalt de laatste kaart van de stapel: perfomanter dan de eerste kaart te
	// verwijderen
	public Ontwikkelingskaart haalKaartVanStapel() {
		return ontwikkelingskaarten.remove(ontwikkelingskaarten.size() - 1);
	}

	private void maakOntwikkelingskaartenAan() {
		switch (niveau) {
		case EEN -> maakKaartenNiveauEenAan();
		case TWEE -> maakKaartenNiveauTweeAan();
		case DRIE -> maakKaartenNiveauDrieAan();
		}
	}

	private void maakKaartenNiveauEenAan() {
		// volgorde int[] {GROEN, WIT, BLAUW, ZWART, ROOD}
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 4, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 1, 0, 1, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 1, 0, 2, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 0, 0, 1, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 3, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 2, 0, 2, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 2, 1, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 2, 2, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 0, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 1, 0, 3, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 0, 2, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 1, 1, 1, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 0, 1, 1, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 2, 2, 0, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 1, 3, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 0, 1, 1, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 0, 2, 1, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 3, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 1, 1, 0, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 3, 1, 1, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 0, 0, 0, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 1, 2, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 3, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 2, 1, 1, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 1, 1, 2, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 1, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 1, 0, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 1, 0, 0, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 4, 0, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 4, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 2, 0, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 2, 0, 0, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 4, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 0, 0, 2, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 1, 0, 1, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 0, 4 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 1, 1, 1, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 1, 2, 0, 1 })));
	}

	private void maakKaartenNiveauTweeAan() {
		// volgorde int[] {GROEN, WIT, BLAUW, ZWART, ROOD}
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 5, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 3, 2, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 3, 3, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 2, 3, 2, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 0, 0, 2, 4 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 2, 3, 0, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 3, 0, 0, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 2, 0, 4, 1 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 5, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 3, 0, 5, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 2, 3, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 2, 0, 3, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 6, 0, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 1, 4, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 5, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 5, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 4, 2, 1, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 0, 5 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 6, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 4, 0, 1, 0, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 6, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 5, 0, 0, 0, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 0, 2, 2 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 0, 2, 0, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 3, 0, 2, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 5, 0, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 3, 5 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 6, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 5, 3, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 0, 6 })));
	}

	private void maakKaartenNiveauDrieAan() {
		// volgorde int[] {GROEN, WIT, BLAUW, ZWART, ROOD}
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 6, 0, 3, 0, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 3, 7 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 0, 3, 6 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 3, 6, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 7, 3, 0, 7, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 3, 3, 5 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 7, 0, 0, 0, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 3, 0, 5, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 5, 3, 3, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 7, 0, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 7, 0, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 0, 7 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 7, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 7, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.WIT),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 3, 0, 6, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 6, 3, 3, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.GROEN),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 7, 0, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.ZWART),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 5, 3, 3, 0, 3 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.ROOD),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 3, 5, 3, 0 })));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.BLAUW),
				EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 7, 3, 0, 0 })));

	}

	private void schudDeStapel() {
		Collections.shuffle(ontwikkelingskaarten);
	}

	// UC2

	public int geefAantalResterendeKaarten() {
		return ontwikkelingskaarten.size();
	}

	public ObservableList<Ontwikkelingskaart> getOntwikkelingskaarten() {
		return ontwikkelingskaarten;
	}

}
