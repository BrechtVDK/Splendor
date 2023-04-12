package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import exceptions.TeVeelFichesInBezitException;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import resources.Taal;

public class Spel {
	private ObservableList<Speler> spelers;
	private int spelerAanDeBeurt;
	private int laatsteSpelerVanRonde;
	private Tafel tafel;
	private List<Edele> edelen;
	// hashmap: sleutel = enum Edelsteen, waarde = list Edelsteenfiches
	private ObservableMap<Edelsteen, StapelEdelsteenfiches> stapelsEdelsteenfiches;
	public static final int MIN_SPELERS = 2;
	public static final int MAX_SPELERS = 4;
	private static final int EINDE_SPEL_SCORE = 15;
	public static final int MAX_FICHES_PER_BEURT = 3;

	// UC1
	public Spel() {
		maakStapelsEdelsteenfichesAan();
		tafel = new Tafel();
		maakEdelenAan();
		spelers = FXCollections.observableArrayList();
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

	public ObservableList<Speler> getSpelers() {
		return spelers;
	}

	public void voegSpelerToe(Speler speler) throws IllegalArgumentException {
		if (speler == null) {
			throw new IllegalArgumentException(Taal.vertaling("Spel.0"));
			// $NON-NLS-1$
//			throw new IllegalArgumentException("De speler is niet geregistreerd in de databank");
		}
		if (spelers.indexOf(speler) != -1) {
			throw new IllegalArgumentException(Taal.vertaling("Spel.1"));
			// $NON-NLS-1$
//			throw new IllegalArgumentException("Speler reeds aan spel toegevoegd");
		}

		if (spelers.size() >= MAX_SPELERS) {
			throw new IllegalArgumentException(String.format(Taal.vertaling("Spel.2"), MAX_SPELERS)); // $NON-NLS-1$
//			throw new IllegalArgumentException(String.format("Max %d spelers!", MAX_SPELERS));
		}
		spelers.add(speler);

	}

	public void organiseerSpelVolgensHetAantalSpelers() throws IllegalArgumentException {
		if (spelers.size() < MIN_SPELERS || spelers.size() > MAX_SPELERS) {
			throw new IllegalArgumentException(String.format(Taal.vertaling("Spel.3"), MIN_SPELERS, MAX_SPELERS)); //$NON-NLS-1$
//			throw new IllegalArgumentException(String.format(
//					"Spel moet gespeeld worden door minimum %d en maximum %d spelers", MIN_SPELERS, MAX_SPELERS));
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
		Function<Speler, Integer> byYear = Speler::getGeboortejaar;
		Function<Speler, Integer> byNaamLength = speler -> speler.getGebruikersnaam().length();
		Function<Speler, String> byNaam = Speler::getGebruikersnaam;

		Speler startSpeler = spelers.stream()
				.sorted(Comparator.comparing(byYear).thenComparing(byNaamLength).thenComparing(byNaam).reversed())
				.toList().get(0);

		// isStartSpeler van Speler op true zetten en instellen als
		// spelerAanDeBeurt + laatsteSpelerVanRonde vastleggen
		spelerAanDeBeurt = spelers.indexOf(startSpeler);
		startSpeler.setStartSpeler(true);
		laatsteSpelerVanRonde = spelerAanDeBeurt == 0 ? spelers.size() - 1 : spelerAanDeBeurt - 1;
	}

	public int geefAantalSpelers() {
		return spelers.size();
	}

	public Ontwikkelingskaart[][] geefZichtbareOntwikkelingskaarten() {
		return tafel.getZichtbareOntwikkelingskaarten();
	}

	// instanties van StapelEdelsteenfiches aanmaken en verzamelen in HashMap
	// stapelsEdelsteenfiches
	private void maakStapelsEdelsteenfichesAan() {
		stapelsEdelsteenfiches = FXCollections.observableHashMap();
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

	// geeft het aantal fiches per soort terug
	public Map<Edelsteen, IntegerBinding> geefAantalFichesPerStapel() {
		Map<Edelsteen, IntegerBinding> aantalPerSoort = new HashMap<Edelsteen, IntegerBinding>();

		for (Edelsteen edelsteen : Edelsteen.values()) {
			aantalPerSoort.put(edelsteen, stapelsEdelsteenfiches.get(edelsteen).geefAantalFiches());
		}

		return aantalPerSoort;
	}

	// geef het aantal resterende kaarten per stapel (per niveau) terug
	public Map<Niveau, IntegerBinding> geefAantalResterendeKaarten() {
		return tafel.geefAantalResterendeKaarten();
	}

	public void speelSpel() {
		for (Speler s : spelers) {
			s.stelSpelAttributenIn();
		}
	}

	public boolean isEindeSpel() {
		return spelers.stream().anyMatch(s -> s.getPrestigepunten() >= EINDE_SPEL_SCORE);
	}

	public List<Speler> geefWinnaars() {
		if (!isEindeSpel()) {
			// lege lijst
			return new ArrayList<Speler>();
		}
		int hoogstePrestigepunten = spelers.stream().mapToInt(Speler::getPrestigepunten).max()
				.orElse(Integer.MIN_VALUE);
		List<Speler> spelersHoogstePrestigepunten = spelers.stream()
				.filter(s -> s.getPrestigepunten() == hoogstePrestigepunten).toList();
		// winaar gevonden: hoogste prestigepunten
		if (spelersHoogstePrestigepunten.size() == 1) {
			return spelersHoogstePrestigepunten;
		} else {
			int minstAantalOntwikkelingskaarten = spelersHoogstePrestigepunten.stream()
					.mapToInt(s -> s.getOntwikkelingskaartenInBezit().size()).min().orElse(Integer.MIN_VALUE);
			// 1 of meerdere winnaars gevonden: hoogste prestigepunten en minst aantal
			// ontwikkelingskaarten
			return spelersHoogstePrestigepunten.stream()
					.filter(s -> s.getOntwikkelingskaartenInBezit().size() == minstAantalOntwikkelingskaarten).toList();
		}
	}

	// UC3
	public void speelRonde() {
		// TODO Auto-generated method stub

	}

	public void bepaalVolgendeSpeler() {
		spelerAanDeBeurt = (spelerAanDeBeurt + 1) % spelers.size();
	}

	// methode geeft edelen terug waar spelerAanDeBeurt recht op heeft
	public List<Edele> geefBeschikbareEdelen() {
		Map<Edelsteen, Integer> spelerBonussen = spelers.get(spelerAanDeBeurt).getAantalBonussenPerTypeInBezit();
		List<Edele> beschikbareEdelen = new ArrayList<>();

		for (Edele edele : edelen) {
			Map<Edelsteen, Integer> edeleBonussen = new HashMap<>();
			for (Edelsteen edelsteen : Edelsteen.values()) {
				edeleBonussen.put(edelsteen, 0);
			}
			Edelsteenfiche[] ebonussen = edele.bonussen();
			for (Edelsteenfiche ef : ebonussen) {
				edeleBonussen.put(ef.edelsteen(), edeleBonussen.get(ef.edelsteen()) + 1);
			}
			if (vergelijkBonussenSpelerMetEdelen(spelerBonussen, edeleBonussen))
				beschikbareEdelen.add(edele);
		}
		return beschikbareEdelen;
	}

	private boolean vergelijkBonussenSpelerMetEdelen(Map<Edelsteen, Integer> spelerBonussen,
			Map<Edelsteen, Integer> edeleBonussen) {
		for (Edelsteen edelsteen : Edelsteen.values()) {
			if (spelerBonussen.get(edelsteen) < edeleBonussen.get(edelsteen))
				return false;
		}
		return true;
	}

	// methode verplaats gekozen Edele van Spel naar spelerAanDeBeurt
	public void verplaatsEdeleVanSpelNaarSpeler(Edele edele) {
		this.getSpelerAanDeBeurt().voegEdeleToe(edele);
		edelen.remove(edele);
	}

	public Speler geefLaatsteSpelerVanRonde() {
		return spelers.get(laatsteSpelerVanRonde);
	}

	// UC4

	// Brecht: ik ga ervan uit dat we bij selecteren van edelsteenfiches deze direct
	// verwijderen uit de stapels zodat de gui direct update. Indien validatie
	// faalt, voegen we ze opnieuw toe => dit schrijven in de 2 catchen van de gui
	// Eerst TeVeelFichesInBezitException opvangen voor IllegalArgumentException!!
	// Afwijkend van UC4 maar lijkt me logischer
	public void verplaatsEdelsteenfichesNaarSpeler(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException, TeVeelFichesInBezitException {
		// In commentaar om binding te testen
		validatieDR_BEURT_AANTAL_FICHES(edelsteenfiches);
		// validatie max 10 in klasse Speler
		this.getSpelerAanDeBeurt().voegEdelsteenfichesToe(edelsteenfiches);

	}

	private void validatieDR_BEURT_AANTAL_FICHES(List<Edelsteenfiche> edelsteenfiches) throws IllegalArgumentException {

		if (edelsteenfiches.size() > MAX_FICHES_PER_BEURT) {
			throw new IllegalArgumentException(String.format(Taal.vertaling("Spel.4"), MAX_FICHES_PER_BEURT)); //$NON-NLS-1$
//			throw new IllegalArgumentException(
//					String.format("Maximum %d edelsteenfiches per beurt nemen!", MAX_FICHES_PER_BEURT));
		} else if (edelsteenfiches.size() == MAX_FICHES_PER_BEURT
				&& edelsteenfiches.stream().distinct().count() != edelsteenfiches.size()) {
			throw new IllegalArgumentException(Taal.vertaling("Spel.5")); //$NON-NLS-1$
//			throw new IllegalArgumentException("De 3 edelsteenfiches moeten verschillend zijn van elkaar!");
		} else if (edelsteenfiches.size() == 2 && edelsteenfiches.stream().distinct().count() == 1
				&& stapelsEdelsteenfiches.get(edelsteenfiches.get(0).edelsteen()).getAantalFiches() < 2) {
			throw new IllegalArgumentException(Taal.vertaling("Spel.6")); //$NON-NLS-1$
//			throw new IllegalArgumentException(
//					"Er moeten minstens 2 edelsteenfiches op de stapel overblijven als je 2 dezelfde edelsteenfiches neemt!");
		}

	}

	public void verwijderEdelsteenficheVanStapel(Edelsteenfiche edelsteenfiche) throws IllegalArgumentException {
		stapelsEdelsteenfiches.get(edelsteenfiche.edelsteen()).verwijderFiche();
	}

	// list gebruikt omdat aantal fiches niet vastligt
	public void verplaatsEdelsteenfichesVanSpelerNaarSpel(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException {
		// verwijderen uit speler
		this.getSpelerAanDeBeurt().verwijderEdelsteenfiches(edelsteenfiches);
		// toevoegen aan spel
		voegEdelsteenfichesTerugToeAanStapelsSpel(edelsteenfiches);

	}

	public void verplaatsEdelsteenfichesVanSpelerNaarSpelNaTeVeelInBezit(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException {
		if (this.getSpelerAanDeBeurt().geefAantalFichesInBezit()
				- edelsteenfiches.size() != Speler.MAX_FICHES_IN_BEZIT) {
			throw new IllegalArgumentException(
					String.format("Na teruggave dien je exact %d fiches overhouden!", Speler.MAX_FICHES_IN_BEZIT));
		}
		verplaatsEdelsteenfichesVanSpelerNaarSpel(edelsteenfiches);
	}

	public void voegEdelsteenfichesTerugToeAanStapelsSpel(List<Edelsteenfiche> edelsteenfiches) {
		for (StapelEdelsteenfiches stapel : stapelsEdelsteenfiches.values()) {
			int aantal = Math.toIntExact(
					edelsteenfiches.stream().filter(e -> e.edelsteen().equals(stapel.getEdelsteen())).count());
			stapel.voegEdelsteenfichesToe(aantal);
		}
	}

	// valideert of speler genoeg fiches/bonussen bezit om kaart te kopen
	// kaart wordt op tafel vervangen door kaart van de stapel
	// edelsteenfiches speler worden verminderd
	// kaart wordt aan speler toegevoegd
	public void verplaatsOntwikkelingskaartVanTafelNaarSpeler(Ontwikkelingskaart kaart)
			throws IllegalArgumentException {
		// validatie DR_BEURT_KOOP_KAART
		if (kaart == null) {
			throw new IllegalArgumentException(Taal.vertaling("Spel.7")); //$NON-NLS-1$
//			throw new IllegalArgumentException("Geen kaart geselecteerd");
		}

		Map<Edelsteen, Integer> fichesSpeler = this.getSpelerAanDeBeurt().getAantalEdelsteenfichesPerTypeInBezit();
		Map<Edelsteen, Integer> bonussenSpeler = this.getSpelerAanDeBeurt().getAantalBonussenPerTypeInBezit();
		int[] afTeTrekkenFichesPerSoort = new int[5];
		int i = 0;
		for (Edelsteen e : Edelsteen.values()) {
			int aantalEdelsteenfichesOpKaartVanEdelsteenE = Math
					.toIntExact(Arrays.stream(kaart.edelsteenfiches()).filter(k -> k.edelsteen().equals(e)).count());
			int aantalBonussenSpelerVanEdelsteenE = bonussenSpeler.get(e);
			// fiches nodig = fiches kaart - bonussen
			int fichesNodigVanEdelsteenE = aantalEdelsteenfichesOpKaartVanEdelsteenE
					- aantalBonussenSpelerVanEdelsteenE;
			afTeTrekkenFichesPerSoort[i++] = fichesNodigVanEdelsteenE < 0 ? 0 : fichesNodigVanEdelsteenE;
			// controle
			if (fichesNodigVanEdelsteenE > fichesSpeler.get(e)) {
				throw new IllegalArgumentException(Taal.vertaling("Spel.8")); //$NON-NLS-1$
//				throw new IllegalArgumentException("Te weinig edelsteenfiches in bezit om kaart te kopen!"); //$NON-NLS-1$
			}
		}

		// kaart van tafel verwijderen en nieuwe kaart van stapel halen
		tafel.verwijderKaartEnVervang(kaart);

		// edelsteenfiches verplaatsen van speler naar spel
		verplaatsEdelsteenfichesVanSpelerNaarSpel(
				Arrays.stream(EdelsteenficheFactory.maakArrayEdelsteenfiches(afTeTrekkenFichesPerSoort)).toList());

		// kaart toevoegen aan speler
		this.getSpelerAanDeBeurt().voegOntwikkelingskaartToe(kaart);
	}

	// om nieuwe kaart weer te geven in gui
	public Ontwikkelingskaart geefKaartVolgensIndex(int rij, int kolom) {
		return tafel.geefKaartVolgensIndex(rij, kolom);
	}

}
