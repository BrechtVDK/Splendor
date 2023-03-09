package domein;

import java.util.HashMap;
import java.util.Map;

public class Tafel {
	Map<Niveau, StapelOntwikkelingskaarten> stapelsOntwikkelingskaarten;
	// 2d array 3rij 4kol
	private Ontwikkelingskaart[][] zichtbareOntwikkelingskaarten;

	public Tafel() {
		maakStapelsOntwikkelingskaartenAan();
		zichtbareOntwikkelingskaarten = new Ontwikkelingskaart[3][4];
		selecteerZichtbareKaarten();
	}

	// Per niveau (3) stapelOntwikkelingskaarten aanmaken en verzamelen in HashMap
	private void maakStapelsOntwikkelingskaartenAan() {
		stapelsOntwikkelingskaarten = new HashMap<Niveau, StapelOntwikkelingskaarten>();
		stapelsOntwikkelingskaarten.put(Niveau.EEN, new StapelOntwikkelingskaarten(Niveau.EEN));
		stapelsOntwikkelingskaarten.put(Niveau.TWEE, new StapelOntwikkelingskaarten(Niveau.TWEE));
		stapelsOntwikkelingskaarten.put(Niveau.DRIE, new StapelOntwikkelingskaarten(Niveau.DRIE));
	}

	private void selecteerZichtbareKaarten() {
		// TODO
	}

}
