package domein;

import java.util.ArrayList;
import java.util.List;

public class Spel {
	private List<Speler> spelers;
	private Speler spelerAanDeBeurt;
	private Tafel tafel;
	
	public Spel() {
		spelers = new ArrayList<>();
		tafel = new Tafel();
	}

	public void voegSpelerToe(Speler speler) {

	}

	public void organiseerTafelVolgensHetAantalSpelers(int aantalSpelers) {

	}

	public int geefSpelerAanDeBeurt() {
		return 0;
	}

	private void kiesStartSpeler() {

	}

}
