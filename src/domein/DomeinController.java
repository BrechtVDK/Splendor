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

	public void voegSpelerToeAanSpel(String gebruikersnaam, int geboortejaar) {

	}

	public void organiseerTafelVolgensHetAantalSpelers() {

	}

	public int geefSpelerAanDeBeurt() {
		return 0;
	}

}