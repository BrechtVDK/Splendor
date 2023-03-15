package domein;

import java.util.List;

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

		spel.voegSpelerToe(spelerRepo.geefSpeler(gebruikersnaam, geboortejaar));

	}

	public void organiseerSpelVolgensHetAantalSpelers() throws IllegalArgumentException {
		spel.organiseerSpelVolgensHetAantalSpelers();
	}

	public String geefSpelerAanDeBeurt() {
		return spel.getSpelerAanDeBeurt().toString();
	}

//	David: ik heb deze nodig voor mijn menu te kunnen doen stoppen 
	// Brecht: kan nu evt vervangen worden door geefSpelers().size();
	public int geefAantalSpelers() {
		return spel.geefAantalSpelers();

	}

	// David gemaakt om de naam van een speler terug te krijgen naar gui of ui
	public String geefNaamSpeler(int index) {
		Speler spelerOpIndex = spel.getSpelers().get(index);
		return spelerOpIndex.getGebruikersnaam();
	}

	// Jonas: om ontwikkelingskaarten te kunnen weergeven in gui
	public OntwikkelingskaartDTO[][] geefZichtbareOntwikkelingskaarten() {

		return spel.geefZichtbareOntwikkelingskaarten();
	}

	public int geefMinAantalSpelers() {
		return spel.MIN_SPELERS;
	}

	public int geefMaxAantalSpelers() {
		return spel.MAX_SPELERS;
	}

	public List<String> geefSpelers() {
		return spel.getSpelers().stream().map(s -> s.toString()).toList();
	}

}
