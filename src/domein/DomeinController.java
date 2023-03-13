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
			throws IllegalArgumentException, NullPointerException {

		spel.voegSpelerToe(spelerRepo.geefSpeler(gebruikersnaam, geboortejaar));

	}

	public void organiseerSpelVolgensHetAantalSpelers() throws IllegalArgumentException {
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

	// David gemaakt om de naam van een speler terug te krijgen naar gui of ui
	public String geefNaamSpeler(int index) {
		Speler spelerOpIndex = spel.getSpelers().get(index);
		return spelerOpIndex.getGebruikersnaam();
	}

	// Jonas: om ontwikkelingskaarten te kunnen weergeven in gui
	public String[][] geefZichtbareOntwikkelingskaarten() {
		Ontwikkelingskaart[][] zichtbareOntwikkelingskaarten = spel.geefZichtbareOntwikkelingskaarten();
		String[][] kaarten = new String[3][4];
		for (int rij = 0; rij < 3; rij++) {
			for (int kolom = 0; kolom < 4; kolom++) {
				kaarten[rij][kolom] = zichtbareOntwikkelingskaarten[rij][kolom].toString();
			}
		}
		return kaarten;
	}

}