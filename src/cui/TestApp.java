package cui;

import domein.DomeinController;

public class TestApp {

	private DomeinController dc;

	public TestApp(DomeinController dc) {

		this.dc = dc;

		dc.startNieuwSpel();
		dc.voegSpelerToeAanSpel("Brecht", 1989);
		dc.voegSpelerToeAanSpel("Jonasa", 1989);
		dc.voegSpelerToeAanSpel("David", 1975);
		dc.voegSpelerToeAanSpel("Sonia_df", 1975);
		dc.organiseerSpelVolgensHetAantalSpelers();
		int speler = dc.geefSpelerAanDeBeurt();
		System.out.println(speler);
	}

}
