package domein;

import java.util.HashMap;
import java.util.Map;

public class Tafel {
	private Map<Niveau, StapelOntwikkelingskaarten> stapelsOntwikkelingskaarten;
	private Ontwikkelingskaart[][] zichtbareOntwikkelingskaarten;
	private static final int AANTAL_ZICHTBARE_KAARTEN_PER_NIVEAU = 4;

	public Tafel() {
		maakStapelsOntwikkelingskaartenAan();
		// 3 rij: aantal niveas - 4 kol: aantal zichtbare kaarten
		zichtbareOntwikkelingskaarten = new Ontwikkelingskaart[Niveau
				.values().length][AANTAL_ZICHTBARE_KAARTEN_PER_NIVEAU];
		selecteerZichtbareKaarten();
	}

	public Ontwikkelingskaart[][] getZichtbareOntwikkelingskaarten() {
		return zichtbareOntwikkelingskaarten;
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
			for (int i = 0; i < AANTAL_ZICHTBARE_KAARTEN_PER_NIVEAU; i++) {
				// Niveau.DRIE [0][], Niveau.TWEE [1][], Niveau.EEN [2][0]
				zichtbareOntwikkelingskaarten[niveau][i] = stapelsOntwikkelingskaarten.get(Niveau.values()[niveau])
						.haalKaartVanStapel();
			}
		}
	}

	// UC2

	// geef het aantal resterende kaarten per stapel (per niveau) terug
	public Map<Niveau, Integer> geefAantalResterendeKaarten() {
		Map<Niveau, Integer> aantalPerNiveau = new HashMap<Niveau, Integer>();

		for (Niveau n : Niveau.values()) {
			aantalPerNiveau.put(n, stapelsOntwikkelingskaarten.get(n).geefAantalResterendeKaarten());
		}
		return aantalPerNiveau;
	}
}
