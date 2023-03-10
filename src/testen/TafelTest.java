package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domein.Ontwikkelingskaart;
import domein.Tafel;

class TafelTest {

	@Test
	public void maakTafel_4zichtbareOntwikkelingskaartenPerNiveau() {
		Tafel tafel = new Tafel();
		boolean nullKaart = false;

		Ontwikkelingskaart[][] zichtbareKaarten = tafel.getZichtbareOntwikkelingskaarten();

		for (Ontwikkelingskaart[] rij : zichtbareKaarten) {
			for (Ontwikkelingskaart kaart : rij) {
				if (kaart.equals(null)) {
					nullKaart = true;
				}
			}
		}

		Assertions.assertFalse(nullKaart);

	}

}
