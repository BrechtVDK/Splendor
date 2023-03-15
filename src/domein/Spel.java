package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Spel {
	private List<Speler> spelers;
	private int spelerAanDeBeurt;
	private Tafel tafel;
	private List<Edele> edelen;
	// hashmap: sleutel = enum Edelsteen, waarde = list Edelsteenfiches
	private Map<Edelsteen, StapelEdelsteenfiches> stapelsEdelsteenfiches;
	public static final int MIN_SPELERS = 2;
	public static final int MAX_SPELERS = 4;

	public Spel() {
		maakStapelsEdelsteenfichesAan();
		tafel = new Tafel();
		maakEdelenAan();
		spelers = new ArrayList<>();
		spelerAanDeBeurt = -1;
	}

	public List<Edele> getEdelen() {
		return edelen;
	}

	public Speler getSpelerAanDeBeurt() {
		// spelerAanDeBeurt wordt 1e x ingesteld in
		// organiseerSpelVolgensHetAantalSpelers()
		return spelers.get(spelerAanDeBeurt);
	}

	public List<Speler> getSpelers() {
		return spelers;
	}

	// Jonas: ben niet zeker van de soort Exception voor de controle op het maximum
	// aantal spelers
	// > Brecht: IllegalArgument ok voor mij, eigen exceptionklasse kan ook maar
	// lijkt me niet echt nodig
	public void voegSpelerToe(Speler speler) throws IllegalArgumentException {
		if (speler == null) {
			throw new IllegalArgumentException("De speler is niet geregistreerd in de databank");
		}
		if (spelers.indexOf(speler) != -1) {
			throw new IllegalArgumentException("Speler reeds aan spel toegevoegd");
		}

		if (spelers.size() >= MAX_SPELERS) {
			throw new IllegalArgumentException(String.format("Max %d spelers!", MAX_SPELERS));
		}
		spelers.add(speler);

	}

	public void organiseerSpelVolgensHetAantalSpelers() throws IllegalArgumentException {
		if (spelers.size() < MIN_SPELERS) {
			throw new IllegalArgumentException(
					String.format("Spel moet gespeeld worden door minimum %d spelers", MIN_SPELERS));
		}

		kiesStartSpeler();

		int aantalSpelers = spelers.size();
		// aantal edelen = aantal spelers +1
		kiesRandomEdelen(aantalSpelers + 1);
		// 3 spelers -> 1 fiche extra, 4 spelers -> 3 fiches extra
		int[] aantal = { 0, 0, 0, 1, 3 };
		if (aantalSpelers >= MAX_SPELERS - 1) {
			for (StapelEdelsteenfiches stapel : stapelsEdelsteenfiches.values()) {
				stapel.voegEdelsteenfichesToe(aantal[aantalSpelers]);
			}
		}
	}

	private void kiesStartSpeler() {
		List<Speler> kopieVanSpelers = new ArrayList<>(spelers);

		kopieVanSpelers = spelers.stream()
				.sorted(Comparator.comparing(Speler::getGeboortejaar)
						.thenComparing(speler -> speler.getGebruikersnaam().length())
						.thenComparing(Speler::getGebruikersnaam).reversed())
				.collect(Collectors.toList());

		// boolean isStartSpeler van Speler op true zetten en instellen als
		// spelerAanDeBeurt;
		spelerAanDeBeurt = spelers.indexOf(kopieVanSpelers.get(0));
		spelers.get(spelerAanDeBeurt).setStartSpeler(true);
	}

	// Ik mis nog deze methode om zo uit het menu te geraken
	public int geefAantalSpelers() {
		return spelers.size();
	}

	public OntwikkelingskaartDTO[][] geefZichtbareOntwikkelingskaarten() {
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
