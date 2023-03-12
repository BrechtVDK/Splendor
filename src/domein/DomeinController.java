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

	public void voegSpelerToeAanSpel(String gebruikersnaam, int geboortejaar) throws IllegalArgumentException {
		// Brecht: try catch voor correcte input gebruikersnaam en int voorzien in cui
		// en gui
		// TODO try catch voorzien voor dubbele speler
		spel.voegSpelerToe(spelerRepo.geefSpeler(gebruikersnaam, geboortejaar));

	}

	public void organiseerSpelVolgensHetAantalSpelers() {
		spel.organiseerSpelVolgensHetAantalSpelers();
	}

	public int geefSpelerAanDeBeurt() {
		return spel.geefSpelerAanDeBeurt();
	}

//	David: ik heb deze nodig voor mijn menu te kunnen doen stoppen 
	public int geefAantalSpelers() {
		try {
			return spel.geefAantalSpelers();
		} catch (Exception e) {
			return 0;
		}
	}

	// Jonas: om ontwikkelingskaarten te kunnen weergeven in gui
	public String[][] geefStringOntwikkelingskaarten() {
		return spel.geefStringOntwikkelingskaarten();
	}

}