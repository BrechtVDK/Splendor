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
	/**
	 * Integer met het minimum aantal spelers dat aan het spel moet meedoen.
	 */
	public static final int MIN_SPELERS = 2;
	/**
	 * Integer met het maximum aantal spelers dat aan het spel mag meedoen.
	 */
	public static final int MAX_SPELERS = 4;
	private static final int EINDE_SPEL_SCORE = 15;
	/**
	 * Integer met het maximum aantal fiches dat de speler in één beurt van de
	 * stapel mag nemen.
	 */
	public static final int MAX_FICHES_PER_BEURT = 3;

	// UC1
	/**
	 * Constructor zorgt ervoor dat een nieuw spel aangemaakt wordt.
	 */
	public Spel() {
		maakStapelsEdelsteenfichesAan();
		tafel = new Tafel();
		maakEdelenAan();
		spelers = FXCollections.observableArrayList();
		spelerAanDeBeurt = -1;
	}

	/**
	 * Geeft een lijst van de edelen in het spel terug
	 * 
	 * @return Een List van Edele-objecten
	 */
	public List<Edele> getEdelen() {
		return edelen;
	}

	/**
	 * Geeft de speler terug die aan de beurt is
	 * 
	 * @return De Speler die aan de beurt is
	 */
	public Speler getSpelerAanDeBeurt() {
		// spelerAanDeBeurt wordt 1e x ingesteld in
		// organiseerSpelVolgensHetAantalSpelers()
		return spelers.get(spelerAanDeBeurt);
	}

	/**
	 * Geeft een lijst met alle spelers uit het spel
	 * 
	 * @return Een ObservableList van Speler-objecten
	 */
	public ObservableList<Speler> getSpelers() {
		return spelers;
	}

	/**
	 * 
	 * Deze methode voegt een speler toe aan het spel
	 * 
	 * @param speler Een Speler-object dat je wenst toe te voegen
	 * @throws IllegalArgumentException Wanneer een null object wordt opgegeven als
	 *                                  parameter. Wanneer de speler reeds werd
	 *                                  toegevoegd. Wanneer het maximum aantal
	 *                                  spelers bereikt werd.
	 */

	public void voegSpelerToe(Speler speler) throws IllegalArgumentException {
		if (speler == null) {
			throw new IllegalArgumentException(Taal.vertaling("Spel.0")); //$NON-NLS-1$
			// $NON-NLS-1$
//			throw new IllegalArgumentException("De speler is niet geregistreerd in de databank");
		}
		if (spelers.indexOf(speler) != -1) {
			throw new IllegalArgumentException(Taal.vertaling("Spel.1")); //$NON-NLS-1$
			// $NON-NLS-1$
//			throw new IllegalArgumentException("Speler reeds aan spel toegevoegd");
		}

		if (spelers.size() >= MAX_SPELERS) {
			throw new IllegalArgumentException(String.format(Taal.vertaling("Spel.2"), MAX_SPELERS)); // $NON-NLS-1$ //$NON-NLS-1$
//			throw new IllegalArgumentException(String.format("Max %d spelers!", MAX_SPELERS));
		}
		spelers.add(speler);

	}

	/**
	 * Methode stelt volgens de spelregels de startspeler in, zorgt dat elke speler
	 * het juist aantal fiches krijgt en kiest een random aantal edelen uit de
	 * mogelijke edelen.
	 * 
	 * @throws IllegalArgumentException Bij te weinig of teveel spelers.
	 */
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

	/**
	 * geeft het aantal spelers die het spel spelen
	 * 
	 * @return aantal spelers in het spel
	 */
	public int geefAantalSpelers() {
		return spelers.size();
	}

	/**
	 * Vraagt aan de tafel de zichtbare ontwikkelingskaarten
	 * 
	 * @return een 2D array van Ontwikkelingskaarten
	 */
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

	/**
	 * Geeft het aantal fiches per soort terug
	 * 
	 * @return Een HashMap met als Key de Edelsteen van de stapel en als Value het
	 *         aantal fiches dat nog op de stapel ligt
	 */
	public Map<Edelsteen, IntegerBinding> geefAantalFichesPerStapel() {
		Map<Edelsteen, IntegerBinding> aantalPerSoort = new HashMap<Edelsteen, IntegerBinding>();

		for (Edelsteen edelsteen : Edelsteen.values()) {
			aantalPerSoort.put(edelsteen, stapelsEdelsteenfiches.get(edelsteen).geefAantalFiches());
		}

		return aantalPerSoort;
	}

	/**
	 * Haalt bij de tafel het aantal resterende kaarten per stapel, per niveau op
	 * 
	 * @return een HashMap met als key het niveau van de stapel en als value het
	 *         aantal kaarten dat nog op de stapel ligt
	 */
	public Map<Niveau, IntegerBinding> geefAantalResterendeKaarten() {
		return tafel.geefAantalResterendeKaarten();
	}

	/**
	 * Stelt per speler de spelattributen in volgens de spelregels
	 */
	public void speelSpel() {
		for (Speler s : spelers) {
			s.stelSpelAttributenIn();
		}
	}

	/**
	 * Bekijkt of er een speler is die de maximum score behaalt heeft
	 * 
	 * @return Een boolean die aangeeft of het einde van het spel bereikt is
	 */
	public boolean isEindeSpel() {
		return spelers.stream().anyMatch(s -> s.getPrestigepunten() >= EINDE_SPEL_SCORE);
	}

	/**
	 * Geeft een lijst terug met nul, 1 of meerdere spelers
	 * 
	 * @return Een List met Speler-objecten. Indien spel nog niet beëndigd is: een
	 *         lege lijst. In het andere geval wordt een lijst met 1 of meerdere
	 *         Speler-objecten terug gegeven. Meerdere, wanneer het aantal
	 *         prestigepunten van de spelers gelijk is en ze evenveel
	 *         ontwikkelingskaarten hebben.
	 */
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


	/**
	 * Zorgt dat in het spel de volgende speler geselecteerd wordt.
	 */
	public void bepaalVolgendeSpeler() {
		spelerAanDeBeurt = (spelerAanDeBeurt + 1) % spelers.size();
	}


	/**
	 * Geeft edelen terug waar de speler die aan de beurt is uit kan kiezen op het
	 * einde van zijn beurt.
	 * 
	 * @return Een list met Edelen, indien speler geen recht heeft op een edele uit
	 *         het spel wordt een lege lijst terug gegeven.
	 */
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

	/**
	 * Verplaatst de door de speler gekozen Edele van het spel naar de speler die
	 * aan de beurt is
	 * 
	 * @param edele De gekozen Edele
	 */
	public void verplaatsEdeleVanSpelNaarSpeler(Edele edele) {
		this.getSpelerAanDeBeurt().voegEdeleToe(edele);
		edelen.remove(edele);
	}

	/**
	 * Geeft de laatste speler van de ronde.
	 * 
	 * @return Speler die als laatst aan de beurt is.
	 */
	public Speler geefLaatsteSpelerVanRonde() {
		return spelers.get(laatsteSpelerVanRonde);
	}

	// UC4

	// Brecht: ik ga ervan uit dat we bij selecteren van edelsteenfiches deze direct
	// verwijderen uit de stapels zodat de gui direct update. Indien validatie
	// faalt, voegen we ze opnieuw toe => dit schrijven in de 2 catchen van de gui
	// Eerst TeVeelFichesInBezitException opvangen voor IllegalArgumentException!!
	// Afwijkend van UC4 maar lijkt me logischer
	/**
	 * Verplaatst de gekozen edelsteenfiches naar de speler.
	 * 
	 * @param edelsteenfiches De gekozen edelsteenfiches
	 * @throws IllegalArgumentException     Wanneer er teveel fiches gekozen worden.
	 *                                      Wanneer er meer dan 2 fiches van
	 *                                      dezelfde kleur gekozen worden. Wanneer
	 *                                      er minder dan 2 fiches op de stapel
	 *                                      overblijven als je 2 fiches van dezelfde
	 *                                      kleur neemt
	 * @throws TeVeelFichesInBezitException Wanneer de speler na het nemen van
	 *                                      fiches meer dan 10 fiches in zijn bezit
	 *                                      heeft.
	 */
	public void verplaatsEdelsteenfichesNaarSpeler(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException, TeVeelFichesInBezitException {
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

	/**
	 * Geeft de opdracht aan de betreffende stapelEdelsteenfiches om één
	 * edelsteenfiche te verwijderen.
	 * 
	 * @param edelsteenfiche De te verwijderen Edelsteenfiche.
	 * @throws IllegalArgumentException Wanneer de betreffende stapel leeg is.
	 */
	public void verwijderEdelsteenficheVanStapel(Edelsteenfiche edelsteenfiche) throws IllegalArgumentException {
		stapelsEdelsteenfiches.get(edelsteenfiche.edelsteen()).verwijderFiche();
	}

	// list gebruikt omdat aantal fiches niet vastligt
	/**
	 * Gebruikt wanneer de speler fiches terug geeft aan het spel. Kan bij het kopen
	 * van een ontwikkelingskaart, of bij het teruggeven wanneer hij meer dan 10
	 * fiches in bezit heeft.
	 * 
	 * @param edelsteenfiches De terug te geven fiches
	 * @throws IllegalArgumentException Wanneer de speler fiches zou willen geven
	 *                                  waar van hij er geen in zijn bezit heeft.
	 */
	public void verplaatsEdelsteenfichesVanSpelerNaarSpel(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException {
		// verwijderen uit speler
		this.getSpelerAanDeBeurt().verwijderEdelsteenfiches(edelsteenfiches);
		// toevoegen aan spel
		voegEdelsteenfichesTerugToeAanStapelsSpel(edelsteenfiches);

	}

	/**
	 * Enkel gebruikt wanneer de speler na het nemen van nieuwe fiches meer dan 10
	 * fiches in zijn bezit heeft. Via deze methode wordt het teveel aan fiches
	 * terug gegeven
	 * 
	 * @param edelsteenfiches List met gekozen Edelsteenfiche-objecten
	 * @throws IllegalArgumentException Wanneer de speler fiches zou willen geven
	 *                                  waar van hij er geen in zijn bezit heeft.
	 */
	public void verplaatsEdelsteenfichesVanSpelerNaarSpelNaTeVeelInBezit(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException {
		if (this.getSpelerAanDeBeurt().geefAantalFichesInBezit()
				- edelsteenfiches.size() != Speler.MAX_FICHES_IN_BEZIT) {
			throw new IllegalArgumentException(
					String.format(Taal.vertaling("Spel.9"), Speler.MAX_FICHES_IN_BEZIT)); //$NON-NLS-1$
		}
		verplaatsEdelsteenfichesVanSpelerNaarSpel(edelsteenfiches);
	}

	/**
	 * Voegt de betreffende edelsteenfiches terug toe aan de betreffende stapels
	 * 
	 * @param edelsteenfiches List met terug te leggen Edelsteenfiche-objecten
	 */
	public void voegEdelsteenfichesTerugToeAanStapelsSpel(List<Edelsteenfiche> edelsteenfiches) {
		for (StapelEdelsteenfiches stapel : stapelsEdelsteenfiches.values()) {
			int aantal = Math.toIntExact(
					edelsteenfiches.stream().filter(e -> e.edelsteen().equals(stapel.getEdelsteen())).count());
			stapel.voegEdelsteenfichesToe(aantal);
		}
	}

	/**
	 * Valideert of speler genoeg fiches/bonussen bezit om de ontwikkelingskaart te
	 * kopen. De ontwikkelingskaart wordt op tafel vervangen door nieuwe kaart van
	 * de stapel. Edelsteenfiches van de speler worden verminderd. De
	 * ontwikkelingskaart wordt aan de speler toegevoegd
	 * 
	 * @param kaart De Ontwikkelingskaart die de speler wil kopen
	 * @throws IllegalArgumentException Wanneer geen ontwikkelingskaart werd
	 *                                  geselecteerd of wanneer de speler te weinig
	 *                                  fiches in bezit heeft om de
	 *                                  ontwikkelingskaart te kopen.
	 */
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

	/**
	 * Haalt bij de tafel de ontwikkelingskaart op die op de opgegeven rij en kolom
	 * ligt.
	 * 
	 * @param rij   De rij waar de kaart zich bevindt
	 * @param kolom De kolom waar de kaart zich bevindt
	 * @return Ontwikkelingskaart die op de opgegeven rij en kolom ligt
	 */

	public Ontwikkelingskaart geefKaartVolgensIndex(int rij, int kolom) {
		return tafel.geefKaartVolgensIndex(rij, kolom);
	}

}
