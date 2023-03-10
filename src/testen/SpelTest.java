package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Spel;
import domein.Speler;

class SpelTest {

	private Spel spel;

	@BeforeEach
	public void beforeEach() {
		spel = new Spel();
	}

	@Test
	public void voegSpelerToe_spelerReedsToegevoegd_Exception() {
		spel.voegSpelerToe(new Speler("abc", 1950));
		Assertions.assertThrows(IllegalArgumentException.class, () -> spel.voegSpelerToe(new Speler("abc", 1950)));
	}

	@Test
	public void voegSpelerToe_spelerBestaatNietInDB_Exception() {

		Assertions.assertThrows(NullPointerException.class, () -> spel.voegSpelerToe(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 3, 4 })
	public void organiseerSpelVolgensHetAantalSpelers_correctAantal_correctAantalEdelenInSpel(int aantalSpelers) {
		spel.voegSpelerToe(new Speler("abc", 1980));
		spel.voegSpelerToe(new Speler("abd", 1981));
		switch (aantalSpelers) {
		case 3:
			spel.voegSpelerToe(new Speler("abe", 1982));
			break;
		case 4:
			spel.voegSpelerToe(new Speler("abe", 1982));
			spel.voegSpelerToe(new Speler("abf", 1983));
			break;
		}
		spel.organiseerSpelVolgensHetAantalSpelers();
		Assertions.assertEquals(aantalSpelers + 1, spel.getEdelen().size());
	}

	@Test
	public void geefSpelerAanDeBeurt_SpelersMetVerschillendeGeboortejaren_correcteStartSpelerWerdGekozen() {
		spel.voegSpelerToe(new Speler("Jan", 1980));
		spel.voegSpelerToe(new Speler("Piet", 1990));
		spel.voegSpelerToe(new Speler("Joris", 2000));
		spel.voegSpelerToe(new Speler("Corneel", 2005));

		int index = spel.geefSpelerAanDeBeurt();

		Assertions.assertEquals("Corneel", spel.getSpelerAanDeBeurt().getGebruikersnaam());
	}

	@Test
	public void geefSpelerAanDeBeurt_SpelersMetGelijkeGeboortejaren_correcteStartSpelerWerdGekozen() {
		spel.voegSpelerToe(new Speler("Jan", 1980));
		spel.voegSpelerToe(new Speler("Piet", 1980));
		spel.voegSpelerToe(new Speler("Joris", 1980));
		spel.voegSpelerToe(new Speler("Corneel", 1980));

		int index = spel.geefSpelerAanDeBeurt();

		Assertions.assertEquals("Corneel", spel.getSpelerAanDeBeurt().getGebruikersnaam());
	}

	@Test
	public void geefSpelerAanDeBeurt_SpelersMetGelijkeGeboortejarenEnLengteNaam_correcteStartSpelerWerdGekozen() {
		spel.voegSpelerToe(new Speler("Filip", 1980));
		spel.voegSpelerToe(new Speler("Karel", 1980));
		spel.voegSpelerToe(new Speler("Joris", 1980));
		spel.voegSpelerToe(new Speler("David", 1980));

		int index = spel.geefSpelerAanDeBeurt();

		Assertions.assertEquals("Karel", spel.getSpelerAanDeBeurt().getGebruikersnaam());
	}

}
