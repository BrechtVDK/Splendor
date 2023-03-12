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

	public Ontwikkelingskaart[][] getZichtbareOntwikkelingskaarten() {
		return zichtbareOntwikkelingskaarten;
	}


	// Per niveau (3) stapelOntwikkelingskaarten aanmaken en verzamelen in HashMap
	private void maakStapelsOntwikkelingskaartenAan() {
		stapelsOntwikkelingskaarten = new HashMap<Niveau, StapelOntwikkelingskaarten>();
		stapelsOntwikkelingskaarten.put(Niveau.EEN, new StapelOntwikkelingskaarten(Niveau.EEN));
		stapelsOntwikkelingskaarten.put(Niveau.TWEE, new StapelOntwikkelingskaarten(Niveau.TWEE));
		stapelsOntwikkelingskaarten.put(Niveau.DRIE, new StapelOntwikkelingskaarten(Niveau.DRIE));
	}

	// bij start spel 4 kaarten per niveau van stapel halen en op tafel zichtbaar
	// leggen
	private void selecteerZichtbareKaarten() {
		for (int i = 0; i < 4; i++) {
			zichtbareOntwikkelingskaarten[2][i] = stapelsOntwikkelingskaarten.get(Niveau.EEN).haalKaartVanStapel();
		}
		for (int i = 0; i < 4; i++) {
			zichtbareOntwikkelingskaarten[1][i] = stapelsOntwikkelingskaarten.get(Niveau.TWEE).haalKaartVanStapel();
		}
		for (int i = 0; i < 4; i++) {
			zichtbareOntwikkelingskaarten[0][i] = stapelsOntwikkelingskaarten.get(Niveau.DRIE).haalKaartVanStapel();
		}
	}

	// Jonas: geeft array van strings terug, nodig voor aanmaken zichtbare kaarten
	// gui
	public String[][] geefStringOntwikkelingskaarten() {
		String[][] kaarten = new String[3][4];
		for (int rij = 0; rij < 3; rij++) {
			for (int kolom = 0; kolom < 4; kolom++) {
				kaarten[rij][kolom] = zichtbareOntwikkelingskaarten[rij][kolom].toString();
			}
		}

		return kaarten;
	}


}
