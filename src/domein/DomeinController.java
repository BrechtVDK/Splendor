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
	public DomeinController() {
		spelerRepo = new SpelerRepository();
	}

	public void startNieuwSpel() {
		spel = new Spel();
	}

	public void voegSpelerToeAanSpel(String gebruikersnaam, int geboortejaar) throws IllegalArgumentException {
		// validatie correcte gebruikersnaam en geboortejaar
		new Speler(gebruikersnaam, geboortejaar);
		spel.voegSpelerToe(spelerRepo.geefSpeler(gebruikersnaam, geboortejaar));

	}

	public void organiseerSpelVolgensHetAantalSpelers() throws IllegalArgumentException {
		spel.organiseerSpelVolgensHetAantalSpelers();
	}

	// Speler teruggeven, gedrag is afgeschermd
	public Speler geefSpelerAanDeBeurt() {
		return spel.getSpelerAanDeBeurt();
	}

//	David: ik heb deze nodig voor mijn menu te kunnen doen stoppen 
	// Brecht: kan nu evt vervangen worden door geefSpelers().size();
	public int geefAantalSpelers() {
		return spel.geefAantalSpelers();

	}

	// David gemaakt om de naam van een speler terug te krijgen naar gui of ui
//	public String geefNaamSpeler(int index) {
//		Speler spelerOpIndex = spel.getSpelers().get(index);
//		return spelerOpIndex.getGebruikersnaam();
//	}

	// Jonas: om ontwikkelingskaarten te kunnen weergeven in gui
	public Ontwikkelingskaart[][] geefZichtbareOntwikkelingskaarten() {

		return spel.geefZichtbareOntwikkelingskaarten();
	}

	// gedrag Speler is afgeschermd via private en protected setters
	public ObservableList<Speler> geefSpelers() {
		return spel.getSpelers();
	}

	public List<Edele> geefEdelen() {
		return spel.getEdelen();
	}

	// UC2

	public Map<Edelsteen, IntegerBinding> geefAantalFichesPerStapel() {
		return spel.geefAantalFichesPerStapel();
	}

	public Map<Niveau, IntegerBinding> geefAantalResterendeKaarten() {
		return spel.geefAantalResterendeKaarten();
	}

	public void speelSpel() {
		spel.speelSpel();
	}

	public boolean isEindeSpel() {
		return spel.isEindeSpel();
	}

	public List<String> geefNamenWinnaars() {
		return spel.geefWinnaars().stream().map(s -> s.toString()).toList();
	}

	// UC3


	public void bepaalVolgendeSpeler() {
		spel.bepaalVolgendeSpeler();
	}

	public List<Edele> geefBeschikbareEdelen() {
		return spel.geefBeschikbareEdelen();
	}

	public void verplaatsEdeleVanSpelNaarSpeler(Edele edele) {
		spel.verplaatsEdeleVanSpelNaarSpeler(edele);
	}

	public Speler geefLaatsteSpelerVanRonde() {
		return spel.geefLaatsteSpelerVanRonde();
	}

	// UC4
	public void verplaatsEdelsteenfichesNaarSpeler(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException, TeVeelFichesInBezitException {
		spel.verplaatsEdelsteenfichesNaarSpeler(edelsteenfiches);
	}

	public void verwijderEdelsteenficheVanStapel(Edelsteenfiche edelsteenfiche) throws IllegalArgumentException {
		spel.verwijderEdelsteenficheVanStapel(edelsteenfiche);
	}

	public void verplaatsEdelsteenfichesVanSpelerNaarSpel(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException {
		spel.verplaatsEdelsteenfichesVanSpelerNaarSpel(edelsteenfiches);
	}

	public void verplaatsEdelsteenfichesVanSpelerNaarSpelNaTeVeelInBezit(List<Edelsteenfiche> edelsteenfiches)
			throws IllegalArgumentException {
		spel.verplaatsEdelsteenfichesVanSpelerNaarSpelNaTeVeelInBezit(edelsteenfiches);
	}

	public void verplaatsOntwikkelingskaartVanTafelNaarSpeler(Ontwikkelingskaart kaart)
			throws IllegalArgumentException {
		spel.verplaatsOntwikkelingskaartVanTafelNaarSpeler(kaart);
	}

	public Ontwikkelingskaart geefNieuweKaartVanStapel(int rij, int kolom) {
		return spel.geefKaartVolgensIndex(rij, kolom);
	}

	public void voegEdelsteenfichesTerugToeAanStapelsSpel(List<Edelsteenfiche> edelsteenfiches) {
		spel.voegEdelsteenfichesTerugToeAanStapelsSpel(edelsteenfiches);
	}
	// extra
	public void registreerSpeler(String gebruikersnaam, int geboortejaar) throws IllegalArgumentException {
		// validatie correcte gebruikersnaam en geboortejaar
		new Speler(gebruikersnaam, geboortejaar);
		spelerRepo.registreerSpeler(gebruikersnaam, geboortejaar);
	}
}
