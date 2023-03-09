package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StapelOntwikkelingskaarten {
	List<Ontwikkelingskaart> ontwikkelingskaarten;
	Niveau niveau;

	public StapelOntwikkelingskaarten(Niveau niveau) {
		this.niveau = niveau;
		this.ontwikkelingskaarten = new ArrayList<>();
		maakOntwikkelingskaartenAan();
		schudDeStapel();
	}

	private void maakOntwikkelingskaartenAan() {
		switch (niveau) {
		case EEN:
			maakKaartenNiveauEenAan();
			break;
		case TWEE:
			maakKaartenNiveauTweeAan();
			break;
		case DRIE:
			maakKaartenNiveauDrieAan();
		}
	}

	private void maakKaartenNiveauEenAan() {
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(0, 0, 4, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(1, 1, 0, 1, 2)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 1, 0, 2, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(1, 0, 0, 1, 3)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(0, 3, 0, 0, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(1, 2, 0, 2, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 2, 1, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(3, 0, 0, 0, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 0, 2, 2, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 0, 0, 0, 3)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(0, 1, 0, 3, 1)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(1, 0, 2, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(2, 2, 0, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 1, 1, 1, 1)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(1, 0, 1, 1, 1)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(0, 2, 2, 0, 1)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(1, 1, 3, 0, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(2, 0, 1, 1, 1)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(2, 0, 2, 1, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 0, 3, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(1, 1, 1, 0, 1)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 3, 1, 1, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(2, 0, 0, 0, 1)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 0, 1, 2, 2)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 0, 0, 3, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(1, 2, 1, 1, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 1, 1, 2, 1)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 0, 0, 1, 2)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(3, 0, 1, 0, 1)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(2, 1, 0, 0, 2)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(4, 0, 0, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 0, 0, 4, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 0, 2, 0, 2)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(0, 2, 0, 0, 2)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(0, 4, 0, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(2, 0, 0, 2, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(1, 1, 0, 1, 1)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 0, 0, 0, 4)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(1, 1, 1, 1, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(1, 1, 2, 0, 1)));
	}

	private void maakKaartenNiveauTweeAan() {
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(0, 5, 0, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(2, 3, 2, 0, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(0, 0, 3, 3, 2)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 2, 3, 2, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(1, 0, 0, 2, 4)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 2, 3, 0, 3)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(2, 3, 0, 0, 3)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 2, 0, 4, 1)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(0, 0, 0, 5, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(0, 3, 0, 5, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(3, 0, 2, 3, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(0, 2, 0, 3, 2)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(6, 0, 0, 0, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(2, 1, 4, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 0, 5, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(3, 0, 5, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 4, 2, 1, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 0, 0, 0, 5)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 6, 0, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(4, 0, 1, 0, 2)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 0, 6, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(5, 0, 0, 0, 3)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(3, 0, 0, 2, 2)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(2, 0, 2, 0, 3)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(3, 3, 0, 2, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(5, 0, 0, 0, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 0, 0, 3, 5)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(0, 0, 0, 6, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 5, 3, 0, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(0, 0, 0, 0, 6)));
	}

	private void maakKaartenNiveauDrieAan() {
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(6, 0, 3, 0, 3)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(0, 0, 0, 3, 7)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(3, 0, 0, 3, 6)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(3, 3, 6, 0, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(7, 3, 0, 7, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(3, 0, 3, 3, 5)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(7, 0, 0, 0, 3)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(3, 3, 0, 5, 3)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 5, 3, 3, 3)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(7, 0, 0, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 7, 0, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(0, 0, 0, 0, 7)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 0, 0, 7, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(3, 0, 7, 0, 0)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.WIT), maakArrayEdelsteenfiches(0, 3, 0, 6, 3)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 6, 3, 3, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.GROEN),
				maakArrayEdelsteenfiches(0, 0, 7, 0, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.ZWART),
				maakArrayEdelsteenfiches(5, 3, 3, 0, 3)));
		ontwikkelingskaarten.add(
				new Ontwikkelingskaart(3, new Edelsteenfiche(Edelsteen.ROOD), maakArrayEdelsteenfiches(3, 3, 5, 3, 0)));
		ontwikkelingskaarten.add(new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.BLAUW),
				maakArrayEdelsteenfiches(0, 7, 3, 0, 0)));

	}

	// hulpmethode voor maakKaartenNiveauXAan
	private Edelsteenfiche[] maakArrayEdelsteenfiches(int groen, int wit, int blauw, int zwart, int rood) {
		int totaalAantal = groen + wit + blauw + zwart + rood;
		int teller = 0;

		Edelsteenfiche[] edelsteenfiches = new Edelsteenfiche[totaalAantal];

		for (int i = 0; i < groen; i++) {
			edelsteenfiches[teller++] = new Edelsteenfiche(Edelsteen.GROEN);
		}
		for (int i = 0; i < wit; i++) {
			edelsteenfiches[teller++] = new Edelsteenfiche(Edelsteen.WIT);
		}
		for (int i = 0; i < blauw; i++) {
			edelsteenfiches[teller++] = new Edelsteenfiche(Edelsteen.BLAUW);
		}
		for (int i = 0; i < zwart; i++) {
			edelsteenfiches[teller++] = new Edelsteenfiche(Edelsteen.ZWART);
		}
		for (int i = 0; i < rood; i++) {
			edelsteenfiches[teller++] = new Edelsteenfiche(Edelsteen.ROOD);
		}
		return edelsteenfiches;
	}

	private void schudDeStapel() {
		Collections.shuffle(ontwikkelingskaarten);
	}

}
