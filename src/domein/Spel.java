package domein;

import java.util.ArrayList;
import java.util.Collections;
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
		if (speler != null) {
			spelers.add(speler);
		}
	}

	public void organiseerTafelVolgensHetAantalSpelers(int aantalSpelers) {

	}

	public int geefSpelerAanDeBeurt() {
		int index = spelers.indexOf(spelerAanDeBeurt);
		if (index == spelers.size() - 1) {
			index = 0;
		} else {
			index += 1;
		}
		return index;
	}

	private void kiesStartSpeler() {
		Collections.sort(spelers, new SpelerComparator());
		spelerAanDeBeurt = spelers.get(0);
	}


}
