package domein;

import java.util.List;
import java.util.Map;

import exceptions.TeVeelFichesInBezitException;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.ObservableList;

public class DomeinController {
	private SpelerRepository spelerRepo;
	private Spel spel;

	// UC1
	/**
	 * Constructor maakt nieuwe DomeinController aan, maakt ook de spelerRepository
	 * aan.
	 */
	public DomeinController() {
		spelerRepo = new SpelerRepository();
	}

	/**
	 * creëert een nieuw spel
	 */
	public void startNieuwSpel() {
		spel = new Spel();
	}

	/**
	 * Methode geeft een speler door aan spel om deze toe te voegen.
	 * 
	 * @param gebruikersnaam De naam van de toe te voegen speler
	 * @param geboortejaar   Het geboortejaar van de toe te voegen speler
	 * @throws IllegalArgumentException Wanneer een null object wordt opgegeven als
	 *                                  parameter. Wanneer de speler reeds werd
	 *                                  toegevoegd. Wanneer het maximum aantal
	 *                                  spelers bereikt werd in het spel.
	 */
	public void voegSpelerToeAanSpel(String gebruikersnaam, int geboortejaar) throws IllegalArgumentException {
		// validatie correcte gebruikersnaam en geboortejaar
		new Speler(gebruikersnaam, geboortejaar);
		spel.voegSpelerToe(spelerRepo.geefSpeler(gebruikersnaam, geboortejaar));

	}

	/**
	 * Vraagt aan het spel om de nodige attributen in te stellen voglens het aantal
	 * spelers.
	 * 
	 * @throws IllegalArgumentException Bij te weinig of teveel spelers.
	 */
	public void organiseerSpelVolgensHetAantalSpelers() throws IllegalArgumentException {
		spel.organiseerSpelVolgensHetAantalSpelers();
	}

	/**
	 * Vraagt aan het spel welke speler aan de beurt is.
	 * 
	 * @return Spelerobject van de speler die aan de beurt is.
	 */
	public Speler geefSpelerAanDeBeurt() {
		return spel.getSpelerAanDeBeurt();
	}

//	David: ik heb deze nodig voor mijn menu te kunnen doen stoppen 
	// Brecht: kan nu evt vervangen worden door geefSpelers().size();
	/**
	 * Vraagt aan het spel hoeveel spelers er zijn.
	 * 
	 * @return Integer met het aantal spelers.
	 */
	public int geefAantalSpelers() {
		return spel.geefAantalSpelers();

	}



	// Jonas: om ontwikkelingskaarten te kunnen weergeven in gui
	/**
	 * Vraagt aan het spel de zichtbare ontwikkelingskaarten.
	 * 
	 * @return Tweedimensionale array van Ontwikkelingskaartobjecten.
	 */
	public Ontwikkelingskaart[][] geefZichtbareOntwikkelingskaarten() {

		return spel.geefZichtbareOntwikkelingskaarten();
	}

	// gedrag Speler is afgeschermd via private en protected setters
	/**
	 * Vraagt aan het spel een overzicht van de spelers.
	 * 
	 * @return Een ObservableList van Spelerobjecten.
	 */
	public ObservableList<Speler> geefSpelers() {
		return spel.getSpelers();
	}

	/**
	 * Vraagt aan het spel een overzicht van de gebruikte edelen.
	 * 
	 * @return Een list van Edele-objecten.
	 */
	public List<Edele> geefEdelen() {
		return spel.getEdelen();
	}

	// UC2

	/**
	 * Vraagt aan het spel het aantal fiches dat nog aanwezig is op de stapel per
	 * type.
	 * 
	 * @return Een map met: (key: Edelsteen, value: Integerbinding met het aantal
	 *         resterende fiches).
	 */
	public Map<Edelsteen, IntegerBinding> geefAantalFichesPerStapel() {
		return spel.geefAantalFichesPerStapel();
	}

	/**
	 * Vraagt aan het spel het aantal kaarten dat nog op elke stapel ligt.
	 * 
	 * @return Een map met: (key: Niveau, value: Integerbinding met het aantal
	 *         resterende kaarten).
	 */
	public Map<Niveau, IntegerBinding> geefAantalResterendeKaarten() {
		return spel.geefAantalResterendeKaarten();
	}

	/**
	 * Vraagt aan het spel om het spel te spelen.
	 */
	public void speelSpel() {
		spel.speelSpel();
	}

	/**
	 * Vraagt aan het spel of het einde al dan niet bereikt is.
	 * 
	 * @return Boolean die aangeeft of het einde van het spel bereikt is.
	 */
	public boolean isEindeSpel() {
		return spel.isEindeSpel();
	}

	/**
	 * Vraagt aan het spel een overzicht van de winnaar(s)
	 * 
	 * @return Een list van Strings met de naam of namen van de winnaar(s).
	 */
	public List<String> geefNamenWinnaars() {
		return spel.geefWinnaars().stream().map(s -> s.toString()).toList();
	}

	// UC3

	/**
	 * Vraagt aan het spel om de volgende speler in te selecteren.
	 */
	public void bepaalVolgendeSpeler() {
		spel.bepaalVolgendeSpeler();
	}

	/**
	 * Vraagt aan het spel een overzicht van Edelen waaruit de speler die aan de
	 * beurt is kan kiezen aan het einde van zijn beurt.
	 * 
	 * @return Een list van Edele-objecten. Dit kan een lege list zijn indien de
	 *         speler geen recht heeft op één van de aanwezige edelen in het spel.
	 */
	public List<Edele> geefBeschikbareEdelen() {
		return spel.geefBeschikbareEdelen();
	}

	/**
	 * Vraagt aan het spel om de gekozen Edele naar de speler die aan de beurt is te
	 * verplaatsen.
	 * 
	 * @param edele Het door de speler gekozen Edele-object.
	 */
	public void verplaatsEdeleVanSpelNaarSpeler(Edele edele) {
		spel.verplaatsEdeleVanSpelNaarSpeler(edele);
	}

	/**
	 * Vraagt aan het spel de speler die als laatste aan de beurt is.
	 * 
	 * @return Spelerobject van de speler die als laatste aan de beurt is.
	 */
	public Speler geefLaatsteSpelerVanRonde() {
		return spel.geefLaatsteSpelerVanRonde();
	}

	// UC4
	/**
	 * Vraagt aan het spel om de gekozen edelsteenfiches naar de speler te
	 * verplaatsen.
	 * 
	 * @param edelsteenfiches List van de gekozen edelsteenfiche-objecten
	 * @throws IllegalArgumentException     Wanneer er teveel fiches gekozen worden.
	 *                                      Wanneer er meer dan 2 fiches van
	 *                                      dezelfde kleur gekozen worden. Wanneer
	 *                                      er minder dan 2 fiches op de stapel
	 *                                      overblijven als je 2 fiches van dezelfde
	 *                                      kleur neemt.
	 * @throws TeVeelFichesInBezitException Wanneer de speler na het nemen van
	 *                                      fiches meer dan 10 fiches in zijn bezit
	 *                                      heeft.
	 */
	public void verplaatsEdelsteenfichesNaarSpeler(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException, TeVeelFichesInBezitException {
		spel.verplaatsEdelsteenfichesNaarSpeler(edelsteenfiches);
	}

	/**
	 * Vraagt aan het spel om de gekozen edelsteenfiche van de stapel te
	 * verwijderen.
	 * 
	 * @param edelsteenfiche Het gekozen edelsteenfiche-object
	 * @throws IllegalArgumentException Wanneer de betreffende stapel leeg is.
	 */
	public void verwijderEdelsteenficheVanStapel(Edelsteenfiche edelsteenfiche) throws IllegalArgumentException {
		spel.verwijderEdelsteenficheVanStapel(edelsteenfiche);
	}

	/**
	 * Vraagt aan het spel om edelsteenfiches van speler naar spel te verplaatsen.
	 * 
	 * @param edelsteenfiches List van de gekozen edelsteenfiche-objecten.
	 * @throws IllegalArgumentException Wanneer de speler fiches zou willen geven
	 *                                  waar van hij er geen in zijn bezit heeft.
	 */
	public void verplaatsEdelsteenfichesVanSpelerNaarSpel(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException {
		spel.verplaatsEdelsteenfichesVanSpelerNaarSpel(edelsteenfiches);
	}

	/**
	 * Vraagt aan het spel om edelsteenfiches van de speler naar het spel te
	 * verplaatsen wanneer de speler er teveel in bezit heeft.
	 * 
	 * @param edelsteenfiches Een list met de te verplaatsen edelsteenfiche-objecten
	 * @throws IllegalArgumentException Wanneer de speler fiches zou willen geven
	 *                                  waar van hij er geen in zijn bezit heeft.
	 */
	public void verplaatsEdelsteenfichesVanSpelerNaarSpelNaTeVeelInBezit(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException {
		spel.verplaatsEdelsteenfichesVanSpelerNaarSpelNaTeVeelInBezit(edelsteenfiches);
	}

	/**
	 * Vraagt aan het spel om een ontwikkelingskaart van de tafel naar de speler te
	 * verplaatsen wanneer de speler deze gekocht heeft.
	 * 
	 * @param kaart Het gekochte ontwikkelingskaartobject
	 * @throws IllegalArgumentException Wanneer geen ontwikkelingskaart werd
	 *                                  geselecteerd of wanneer de speler te weinig
	 *                                  fiches in bezit heeft om de
	 *                                  ontwikkelingskaart te kopen.
	 */
	public void verplaatsOntwikkelingskaartVanTafelNaarSpeler(Ontwikkelingskaart kaart)
			throws IllegalArgumentException {
		spel.verplaatsOntwikkelingskaartVanTafelNaarSpeler(kaart);
	}

	/**
	 * Vraagt aan het spel om een ontwikkelingskaart terug te geven volgens een
	 * opgegeven index (=rij, kolom).
	 * 
	 * @param rij   Integer die de rij aangeeft waar de kaart genomen moet worden.
	 * @param kolom Integer die de kolom aangeeft waar de kaart genomen moet worden.
	 * @return Het ontwikkelingskaartobject dat zich op de plaats van de opgegeven
	 *         rij en kolom bevindt.
	 */
	public Ontwikkelingskaart geefNieuweKaartVanStapel(int rij, int kolom) {
		return spel.geefKaartVolgensIndex(rij, kolom);
	}

	/**
	 * Vraagt aan het spel om de opgegeven edelsteenfiches terug toe te voegen aan
	 * het spel.
	 * 
	 * @param edelsteenfiches List met de terug toe te voegen
	 *                        edelsteenfiche-objecten
	 */
	public void voegEdelsteenfichesTerugToeAanStapelsSpel(List<Edelsteenfiche> edelsteenfiches) {
		spel.voegEdelsteenfichesTerugToeAanStapelsSpel(edelsteenfiches);
	}

	// extra
	/**
	 * Maakt een nieuw spelerobject aan en vraagt aan de spelerrepository om dit toe
	 * te voegen.
	 * 
	 * @param gebruikersnaam De naam van de nieuwe speler.
	 * @param geboortejaar   Het geboortejaar van de nieuwe speler.
	 * @throws IllegalArgumentException Wanneer de gebruikersnaam reeds bestaat in
	 *                                  de database.
	 */
	public void registreerSpeler(String gebruikersnaam, int geboortejaar) throws IllegalArgumentException {
		// validatie correcte gebruikersnaam en geboortejaar
		new Speler(gebruikersnaam, geboortejaar);
		spelerRepo.registreerSpeler(gebruikersnaam, geboortejaar);
	}
}
