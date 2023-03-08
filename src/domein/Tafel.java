package domein;

import java.util.List;

public class Tafel {
	// altijd drie stapels --> array van lengte 3
	// of hier HashMap??
	private StapelOntwikkelingskaarten[] stapelsOntwikkelingskaarten;
	// hier List gebruiken (dynamisch)?
	private List<Ontwikkelingskaart> zichtbareOntwikkelingskaarten;
	private List<Edele> edelen;
	// altijd vijf stapels --> array van lengte 5
	// of hier HashMap??
	private StapelEdelsteenfiches[] stapelsEdelsteenfiches;

	public Tafel() {
		maakStapelsOntwikkelingskaartenAan();
	}

	private void maakStapelsOntwikkelingskaartenAan() {
		stapelsOntwikkelingskaarten = new StapelOntwikkelingskaarten[3];

		// TODO

	}

	private void selecteerZichtbareKaarten() {

	}

	private void maakStapelsEdelsteenfichesAan() {

	}

	private void maakEdelenAan() {

	}

	public void organiseerTafelVolgensHetAantalSpelers(int aantalSpelers) {

	}

	private void kiesRandomEdelen() {

	}

}
