package domein;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tafel {
	private Map<Niveau, StapelOntwikkelingskaarten> stapelsOntwikkelingskaarten;
	// 2d array 3rij 4kol
	private Ontwikkelingskaart[][] zichtbareOntwikkelingskaarten;

	public Tafel() {
		maakStapelsOntwikkelingskaartenAan();
		zichtbareOntwikkelingskaarten = new Ontwikkelingskaart[3][4];
		selecteerZichtbareKaarten();
	}

	public OntwikkelingskaartRecord[][] getZichtbareOntwikkelingskaarten() {
		OntwikkelingskaartRecord[][] oRecord = new OntwikkelingskaartRecord[3][4];
		for (int i = 0; i < 3; i++) {
			oRecord[i] = Arrays.stream(zichtbareOntwikkelingskaarten[i]).map(o -> o.toRecord())
					.toArray(OntwikkelingskaartRecord[]::new);
		}

		return oRecord;
	}

	// Per niveau (3) stapelOntwikkelingskaarten aanmaken en verzamelen in HashMap
	private void maakStapelsOntwikkelingskaartenAan() {
		stapelsOntwikkelingskaarten = new HashMap<Niveau, StapelOntwikkelingskaarten>();
		for (Niveau niveau : Niveau.values()) {
			stapelsOntwikkelingskaarten.put(niveau, new StapelOntwikkelingskaarten(niveau));
		}

	}

	// bij start spel 4 kaarten per niveau van stapel halen en op tafel zichtbaar
	// leggen
	private void selecteerZichtbareKaarten() {
		for (int niveau = 0; niveau < Niveau.values().length; niveau++) {
			for (int i = 0; i < 4; i++) {
				// Niveau.DRIE [0][], Niveau.TWEE [1][], Niveau.EEN [2][0]
				zichtbareOntwikkelingskaarten[niveau][i] = stapelsOntwikkelingskaarten.get(Niveau.values()[niveau])
						.haalKaartVanStapel();
			}
		}

	}

}
