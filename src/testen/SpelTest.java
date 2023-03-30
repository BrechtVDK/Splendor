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

		Assertions.assertThrows(IllegalArgumentException.class, () -> spel.voegSpelerToe(null));
	}

	@Test
	public void voegSpelerToe_teVeelSpelers_Exception() {
		spel.voegSpelerToe(new Speler("abc", 1950));
		spel.voegSpelerToe(new Speler("abcd", 1951));
		spel.voegSpelerToe(new Speler("abcde", 1952));
		spel.voegSpelerToe(new Speler("abcdef", 1953));
		Assertions.assertThrows(IllegalArgumentException.class, () -> spel.voegSpelerToe(new Speler("abcdefg", 1954)));
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 3, 4 })
	public void organiseerSpelVolgensHetAantalSpelers_correctAantal_correctAantalEdelenInSpel(int aantalSpelers) {
		spel.voegSpelerToe(new Speler("abc", 1980));
		spel.voegSpelerToe(new Speler("abd", 1981));
		switch (aantalSpelers) {
		case 3 -> spel.voegSpelerToe(new Speler("abe", 1982));

		case 4 -> {
			spel.voegSpelerToe(new Speler("abe", 1982));
			spel.voegSpelerToe(new Speler("abf", 1983));
		}
		}
		spel.organiseerSpelVolgensHetAantalSpelers();
		Assertions.assertEquals(aantalSpelers + 1, spel.getEdelen().size());
	}

	@Test
	public void getSpelerAanDeBeurt_SpelersMetVerschillendeGeboortejaren_correcteStartSpelerWerdGekozen() {
		spel.voegSpelerToe(new Speler("Jan", 1980));
		spel.voegSpelerToe(new Speler("Piet", 1990));
		spel.voegSpelerToe(new Speler("Joris", 2000));
		spel.voegSpelerToe(new Speler("Corneel", 2005));
		spel.organiseerSpelVolgensHetAantalSpelers();

		Assertions.assertEquals("Corneel", spel.getSpelerAanDeBeurt().toString());
	}

	@Test
	public void getSpelerAanDeBeurt_SpelersMetGelijkeGeboortejaren_correcteStartSpelerWerdGekozen() {
		spel.voegSpelerToe(new Speler("Jan", 1980));
		spel.voegSpelerToe(new Speler("Piet", 1980));
		spel.voegSpelerToe(new Speler("Joris", 1980));
		spel.voegSpelerToe(new Speler("Corneel", 1980));
		spel.organiseerSpelVolgensHetAantalSpelers();

		Assertions.assertEquals("Corneel", spel.getSpelerAanDeBeurt().toString());
	}

	@Test
	public void getSpelerAanDeBeurt_SpelersMetGelijkeGeboortejarenEnLengteNaam_correcteStartSpelerWerdGekozen() {
		spel.voegSpelerToe(new Speler("Filip", 1980));
		spel.voegSpelerToe(new Speler("Karel", 1980));
		spel.voegSpelerToe(new Speler("Joris", 1980));
		spel.voegSpelerToe(new Speler("David", 1980));
		spel.organiseerSpelVolgensHetAantalSpelers();

		Assertions.assertEquals("Karel", spel.getSpelerAanDeBeurt().toString());
	}

	@Test
	public void spelersMetGelijkeGeboortejarenEnLengteNaam_oorspronkelijkeVolgordeSpelersBlijftGelijk() {
		spel.voegSpelerToe(new Speler("Filip", 1980));
		spel.voegSpelerToe(new Speler("Karel", 1980));
		spel.voegSpelerToe(new Speler("Joris", 1980));
		spel.voegSpelerToe(new Speler("David", 1980));

		Assertions.assertEquals("Filip", spel.getSpelers().get(0).toString());
	}

	// TODO spel.isEindeSpel()
	// Momenteel nog niet te testen door de protected methodes in Speler

	// TODO spel.geefWinnaars()
	// Momenteel nog niet te testen door de protected methodes in Speler

	// UC3
	// TODO
	// UC4
	// TODO
}
