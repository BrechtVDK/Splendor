package domein;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.binding.IntegerBinding;

public class Tafel {
	private Map<Niveau, StapelOntwikkelingskaarten> stapelsOntwikkelingskaarten;
	private Ontwikkelingskaart[][] zichtbareOntwikkelingskaarten;
	private static final int AANTAL_ZICHTBARE_KAARTEN_PER_NIVEAU = 4;

	/**
	 * Maakt een nieuwe tafel aan waar de stapels met kaarten en de zichtbarekaarten
	 * op liggen.
	 */
	public Tafel() {
		maakStapelsOntwikkelingskaartenAan();
		// 3 rij: aantal niveas - 4 kol: aantal zichtbare kaarten
		zichtbareOntwikkelingskaarten = new Ontwikkelingskaart[Niveau
				.values().length][AANTAL_ZICHTBARE_KAARTEN_PER_NIVEAU];
		selecteerZichtbareKaarten();
	}

	/**
	 * Geeft de zichtbare ontwikkelingskaarten terug.
	 * 
	 * @return Een tweedimensionale array met ontwikkelingskaartobjecten.
	 */
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

	//
	/**
	 * Geeft het aantal resterende kaarten per stapel (per niveau) terug.
	 * 
	 * @return Een Map met: (key: Niveau, value: IntegerBinding met resterende
	 *         kaarten per niveau.
	 */
	public Map<Niveau, IntegerBinding> geefAantalResterendeKaarten() {
		Map<Niveau, IntegerBinding> aantalPerNiveau = new HashMap<Niveau, IntegerBinding>();

		for (Niveau n : Niveau.values()) {
			aantalPerNiveau.put(n, stapelsOntwikkelingskaarten.get(n).geefAantalResterendeKaarten());
		}
		return aantalPerNiveau;
	}

	/**
	 * Zorgt ervoor dat een nieuwe kaart van de stapel op de plaats van de opgegeven
	 * kaart wordt gelegd.
	 * 
	 * @param kaart Het ontwikkelingskaartobject dat vervangen wordt door een nieuwe
	 *              kaart van de stapel.
	 */
	public void verwijderKaartEnVervang(Ontwikkelingskaart kaart) {
		// kaart zoeken op tafel
		int rijIndex = -1;
		int kolIndex = -1;
		// outer zorgt dat break statement uit de beide forloops ontsnapt
		outer: for (int rij = 0; rij < zichtbareOntwikkelingskaarten.length; rij++) {
			for (int kol = 0; kol < zichtbareOntwikkelingskaarten[rij].length; kol++) {

				if (zichtbareOntwikkelingskaarten[rij][kol] != null
						&& zichtbareOntwikkelingskaarten[rij][kol].equals(kaart)) {
					rijIndex = rij;
					kolIndex = kol;
					break outer;
				}
			}
		}
		// kaart gevonden = vervangen, kaart niet gevonden = niks => nodig om testen te
		// kunnen uitvoeren
		if (rijIndex != -1 && kolIndex != -1) {
			// vervangen door kaart van stapel
			try {
				zichtbareOntwikkelingskaarten[rijIndex][kolIndex] = stapelsOntwikkelingskaarten
						.get(Niveau.values()[rijIndex]).haalKaartVanStapel();
				// IndexOutOfBoundsException = geen kaarten meer op de stapel
			} catch (IndexOutOfBoundsException e) {
				zichtbareOntwikkelingskaarten[rijIndex][kolIndex] = null;
			}
		}
	}

	/**
	 * Geeft een ontwikkelingskaart terug volgens een opgegeven index.
	 * 
	 * @param rij   De rij waar de ontwikkelingskaart zich bevindt.
	 * @param kolom De kolom waar de ontwikkelingskaart zich bevindt.
	 * @return Het gevraagde ontwikkelingskaartobject.
	 */
	// om nieuwe kaart weer te geven in gui
	public Ontwikkelingskaart geefKaartVolgensIndex(int rij, int kolom) {
		return zichtbareOntwikkelingskaarten[rij][kolom];
	}
}
