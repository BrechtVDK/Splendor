package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spel {
	private List<Speler> spelers;
	private Speler spelerAanDeBeurt;
	private Tafel tafel;
	private List<Edele> edelen;
	// hashmap: sleutel = enum Edelsteen, waarde = list Edelsteenfiches
	private Map<Edelsteen, StapelEdelsteenfiches> stapelsEdelsteenfiches;

	public Spel() {
		maakStapelsEdelsteenfichesAan();
		tafel = new Tafel();
		maakEdelenAan();
		spelers = new ArrayList<>();
	}

	public List<Edele> getEdelen() {
		return edelen;
	}

	public Speler getSpelerAanDeBeurt() {
		return spelerAanDeBeurt;
	}

	public List<Speler> getSpelers() {
		return spelers;
	}

	//Jonas: ben niet zeker van de soort Exception voor de controle op het maximum aantal spelers
	// > Brecht: IllegalArgument ok voor mij, eigen exceptionklasse kan ook maar
	// lijkt me niet echt nodig
	public void voegSpelerToe(Speler speler) throws IllegalArgumentException, NullPointerException {
		if (spelers.indexOf(speler) != -1) {
			throw new IllegalArgumentException("Speler reeds aan spel toegevoegd");
		}
		
		if (speler.equals(null)) {
			throw new NullPointerException("De speler bestaat niet in de database");
		}
		if (spelers.size() >= 4) {
			throw new IllegalArgumentException("Max 4 spelers!");
		}
		spelers.add(speler);
	
			
	}

	//Jonas: ook hier niet zeker van de soort Exception
	public void organiseerSpelVolgensHetAantalSpelers() throws IllegalArgumentException {
		if (spelers.size() <2) {
			throw new IllegalArgumentException("Spel moet gespeeld worden door minimum 2 spelers");
		}
		int aantalSpelers = spelers.size();
		switch (aantalSpelers) {
		// 3 edelen 4 fiches
		case 2:
			kiesRandomEdelen(3);
			break;
		// 4 edelen 4+1fiches
		case 3:
			kiesRandomEdelen(4);
			for (StapelEdelsteenfiches stapel : stapelsEdelsteenfiches.values()) {
				stapel.voegEdelsteenfichesToe(1);
			}
			break;
		// 5 edelen 4+3fiches
		case 4:
			kiesRandomEdelen(5);
			for (StapelEdelsteenfiches stapel : stapelsEdelsteenfiches.values()) {
				stapel.voegEdelsteenfichesToe(3);
			}
		}
	}

	public int geefSpelerAanDeBeurt() {
		if (spelerAanDeBeurt == null) {
			kiesStartSpeler();
		}

		int index = spelers.indexOf(spelerAanDeBeurt);
		// Brecht: onderstaande code niet nodig. Zal voor een andere methode zijn, bv.
		// stelVolgendeSpelerAanDeBeurt In
		/*
		 * if (index == spelers.size() - 1) { index = 0; } else { index += 1; }
		 */
		return index;
	}

	private void kiesStartSpeler() {
		List<Speler> kopieVanSpelers = new ArrayList<>(spelers);
		Collections.sort(kopieVanSpelers, new SpelerComparator());
		spelerAanDeBeurt = kopieVanSpelers.get(0);
	}

	// Ik mis nog deze methode om zo uit het menu te geraken
	public int geefAantalSpelers() {
		return spelers.size();
	}


	public Ontwikkelingskaart[][] geefZichtbareOntwikkelingskaarten() {
		return tafel.getZichtbareOntwikkelingskaarten();
	}

	// 5 instanties van StapelEdelsteenfiches aanmaken en verzamelen in HashMap
	// stapelsEdelsteenfiches
	private void maakStapelsEdelsteenfichesAan() {

		stapelsEdelsteenfiches = new HashMap<Edelsteen, StapelEdelsteenfiches>();
		stapelsEdelsteenfiches.put(Edelsteen.GROEN, new StapelEdelsteenfiches(Edelsteen.GROEN));
		stapelsEdelsteenfiches.put(Edelsteen.WIT, new StapelEdelsteenfiches(Edelsteen.WIT));
		stapelsEdelsteenfiches.put(Edelsteen.BLAUW, new StapelEdelsteenfiches(Edelsteen.BLAUW));
		stapelsEdelsteenfiches.put(Edelsteen.ZWART, new StapelEdelsteenfiches(Edelsteen.ZWART));
		stapelsEdelsteenfiches.put(Edelsteen.ROOD, new StapelEdelsteenfiches(Edelsteen.ROOD));
	}

	// 10 instanties van Edelen aanmaken en verzamelen in List
	private void maakEdelenAan() {
		edelen = new ArrayList<>();
		edelen.add(new Edele(maakArrayEdelsteenfiches(0, 4, 0, 4, 0)));
		edelen.add(new Edele(maakArrayEdelsteenfiches(0, 3, 0, 3, 3)));
		edelen.add(new Edele(maakArrayEdelsteenfiches(0, 3, 3, 3, 0)));
		edelen.add(new Edele(maakArrayEdelsteenfiches(3, 3, 3, 0, 0)));
		edelen.add(new Edele(maakArrayEdelsteenfiches(3, 0, 0, 3, 3)));
		edelen.add(new Edele(maakArrayEdelsteenfiches(3, 0, 3, 0, 3)));
		edelen.add(new Edele(maakArrayEdelsteenfiches(4, 0, 4, 0, 0)));
		edelen.add(new Edele(maakArrayEdelsteenfiches(0, 0, 0, 4, 4)));
		edelen.add(new Edele(maakArrayEdelsteenfiches(0, 4, 4, 0, 0)));
		edelen.add(new Edele(maakArrayEdelsteenfiches(4, 0, 0, 0, 4)));
	}

	// hulpmethode voor maakEdelenAan();
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

	// edelen eerst shufflen, daarna van achter naar voor verwijderen
	private void kiesRandomEdelen(int aantal) {
		Collections.shuffle(edelen);
		for (int i = 9; i >= aantal; i--) {
			edelen.remove(i);
		}
	}

}
