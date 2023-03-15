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

	// UC1
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
		if (spelers.size() < MIN_SPELERS || spelers.size() > MAX_SPELERS) {
			throw new IllegalArgumentException(String.format(
					"Spel moet gespeeld worden door minimum %d en maximum %d spelers", MIN_SPELERS, MAX_SPELERS));
		}
		// Speler.isStartSpeler en spelerAanDeBeurt instellen
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

		// isStartSpeler van Speler op true zetten en instellen als
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

	// instanties van StapelEdelsteenfiches aanmaken en verzamelen in HashMap
	// stapelsEdelsteenfiches
	private void maakStapelsEdelsteenfichesAan() {
		stapelsEdelsteenfiches = new HashMap<Edelsteen, StapelEdelsteenfiches>();
		// enums overlopen
		for (Edelsteen e : Edelsteen.values()) {
			stapelsEdelsteenfiches.put(e, new StapelEdelsteenfiches(e));
		}
	}

	// 10 instanties van Edelen aanmaken en verzamelen in List
	private void maakEdelenAan() {
		edelen = new ArrayList<>();
		// Volgerde: {GROEN, WIT, BLAUW, ZWART, ROOD}
		int[][] aantalFichesPerSoortPerEdele = { { 0, 4, 0, 4, 0 }, { 0, 3, 0, 3, 3 }, { 0, 3, 3, 3, 0 },
				{ 3, 3, 3, 0, 0 }, { 3, 0, 0, 3, 3 }, { 3, 0, 3, 0, 3 }, { 4, 0, 4, 0, 0 }, { 0, 0, 0, 4, 4 },
				{ 0, 4, 4, 0, 0 }, { 4, 0, 0, 0, 4 } };

		for (int edele = 0; edele < aantalFichesPerSoortPerEdele.length; edele++) {
			edelen.add(new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[edele])));
		}
	}

	// edelen eerst shufflen, daarna te veel verwijderen
	private void kiesRandomEdelen(int aantal) {
		Collections.shuffle(edelen);
		edelen.subList(aantal, edelen.size()).clear();
	}

	// UC2

}
