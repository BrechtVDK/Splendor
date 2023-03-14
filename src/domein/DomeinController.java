package domein;

public class DomeinController {
	private SpelerRepository spelerRepo;
	private Spel spel;

	public DomeinController() {
		spelerRepo = new SpelerRepository();
	}

	public void startNieuwSpel() {
		spel = new Spel();
	}

	public void voegSpelerToeAanSpel(String gebruikersnaam, int geboortejaar)
			throws IllegalArgumentException {

		spel.voegSpelerToe(spelerRepo.geefSpeler(gebruikersnaam, geboortejaar));

	}

	public void organiseerSpelVolgensHetAantalSpelers() throws IllegalArgumentException {
		spel.organiseerSpelVolgensHetAantalSpelers();
	}

	public Speler geefSpelerAanDeBeurt() {
		return spel.geefSpelerAanDeBeurt();
	}

//	David: ik heb deze nodig voor mijn menu te kunnen doen stoppen 
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
}