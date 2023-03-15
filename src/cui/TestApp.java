package cui;

import domein.DomeinController;

public class TestApp {

	private DomeinController dc;

	public TestApp(DomeinController dc) {

		this.dc = dc;

		dc.startNieuwSpel();
		dc.voegSpelerToeAanSpel("Jonas", 1989);
		dc.voegSpelerToeAanSpel("Brecht", 1993);
		dc.voegSpelerToeAanSpel("David", 1975);
		dc.voegSpelerToeAanSpel("Sonia", 1985);
		dc.organiseerSpelVolgensHetAantalSpelers();
		System.out.println(dc.geefSpelerAanDeBeurt());
	}

}
