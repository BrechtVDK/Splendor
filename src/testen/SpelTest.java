package testen;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.DomeinController;
import domein.Edelsteen;
import domein.Edelsteenfiche;
import domein.EdelsteenficheFactory;
import domein.Ontwikkelingskaart;
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

	// hier testen met domeincontroller
	// spel met 3 spelers met elk 10 fiches aanmaken in beforeEach2
	@Nested
	class NestedTests {
		private DomeinController dc;
		private ArrayList<Edelsteenfiche> fiches3BlauwGroenRood, fiches3RoodWitZwart, fiches2Blauw, fiches2Groen,
				fiches2Rood, fiches2Wit, fiches2Zwart;

		@BeforeEach
		public void beforeEach2() {
			dc = new DomeinController();
			dc.startNieuwSpel();

			// fiches maken om te nemen bij een beurt
			fiches3BlauwGroenRood = new ArrayList<>(Arrays.asList(new Edelsteenfiche(Edelsteen.BLAUW),
					new Edelsteenfiche(Edelsteen.GROEN), new Edelsteenfiche(Edelsteen.ROOD)));
			fiches3RoodWitZwart = new ArrayList<>(Arrays.asList(new Edelsteenfiche(Edelsteen.ROOD),
					new Edelsteenfiche(Edelsteen.WIT), new Edelsteenfiche(Edelsteen.ZWART)));
			fiches2Blauw = new ArrayList<>(
					Arrays.asList(new Edelsteenfiche(Edelsteen.BLAUW), new Edelsteenfiche(Edelsteen.BLAUW)));
			fiches2Groen = new ArrayList<>(
					Arrays.asList(new Edelsteenfiche(Edelsteen.GROEN), new Edelsteenfiche(Edelsteen.GROEN)));
			fiches2Rood = new ArrayList<>(
					Arrays.asList(new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche(Edelsteen.ROOD)));
			fiches2Wit = new ArrayList<>(
					Arrays.asList(new Edelsteenfiche(Edelsteen.WIT), new Edelsteenfiche(Edelsteen.WIT)));
			fiches2Zwart = new ArrayList<>(
					Arrays.asList(new Edelsteenfiche(Edelsteen.ZWART), new Edelsteenfiche(Edelsteen.ZWART)));
			// spelers toevoegen
			dc.voegSpelerToeAanSpel("Jonas", 1989);
			dc.voegSpelerToeAanSpel("Brecht", 1993);
			dc.voegSpelerToeAanSpel("David", 1975);
			dc.organiseerSpelVolgensHetAantalSpelers();
			dc.speelSpel();
			// 10 fiches/speler toevoegen
			// {2,2,2,2,2}
			for (int speler = 1; speler <= dc.geefAantalSpelers(); speler++) {
				dc.verplaatsEdelsteenfichesNaarSpeler(fiches2Groen);
				dc.verplaatsEdelsteenfichesNaarSpeler(fiches2Wit);
				dc.verplaatsEdelsteenfichesNaarSpeler(fiches2Blauw);
				dc.verplaatsEdelsteenfichesNaarSpeler(fiches2Zwart);
				dc.verplaatsEdelsteenfichesNaarSpeler(fiches2Rood);
				dc.bepaalVolgendeSpeler();
			}
		}

		@Test
		public void isEindeSpel_teWeinigPunten_geeftFalse() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(6, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));

			// spelsituatie: Brecht: 6pt, Jonas: 0pt, Davi: 0pt
			Assertions.assertEquals(false, dc.isEindeSpel());
		}

		@Test
		public void isEindeSpel_NetGenoegPunten_geeftTrue() {

			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(15, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(12, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			// spelsituatie: Brecht: 15pt, Jonas: 0pt, David: 12pt
			Assertions.assertEquals(true, dc.isEindeSpel());

		}

		@Test
		public void isEindeSpel_GenoegPuntenMeerdereSpelers_geeftTrue() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(16, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(17, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			// spelsituatie: Brecht: 16pt, Jonas: 0pt, David: 17pt
			Assertions.assertEquals(true, dc.isEindeSpel());
		}

		@Test
		public void geefWinnaars_geenWinnaars_legeLijst() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(6, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));

			// spelsituatie: Brecht: 6pt, Jonas: 0pt, David: 0pt
			Assertions.assertEquals(new ArrayList<String>(), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_1speler15punten_juisteWinnaar() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(15, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(14, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			// spelsituatie: Brecht: 15pt, Jonas: 0pt, David: 14pt
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("Brecht")), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_2spelersMeerDan15puntenGelijkAantalKaarten_juisteWinnaarHoogstePunten() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(15, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(17, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			// spelsituatie: Brecht: 15pt 1kaart, Jonas: 0pt, David: 17pt 1kaart
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("David")), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_2spelersMeerDan15puntenOngelijkAantalKaarten_juisteWinnaarHoogstePunten() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(15, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(10, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(7, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 2, 2 })));
			// spelsituatie: Brecht: 15pt 1kaart, Jonas: 0pt, David: 17pt 2kaart
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("David")), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_2spelersGelijkePuntenOngelijkAantalKaarten_juisteWinnaarLaagsteAantalKaarten() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(17, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(10, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(7, new Edelsteenfiche(Edelsteen.BLAUW),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 2, 2, 0 })));
			// spelsituatie: Brecht: 17pt 1kaart, Jonas: 0pt, David: 17pt 2kaart
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("Brecht")), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_2spelersGelijkePuntenGelijkAantalKaarten_2juisteWinnaars() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(10, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(7, new Edelsteenfiche(Edelsteen.WIT),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 2, 2 })));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(10, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(7, new Edelsteenfiche(Edelsteen.BLAUW),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 2, 2, 0 })));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.ROOD),
							EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 2, 0, 0, 0 })));
			// spelsituatie: Brecht: 17pt 2kaart, Jonas: 5pt, David: 17pt 2kaart
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("Brecht", "David")), dc.geefNamenWinnaars());
		}


// UC3
// TODO
// UC4
// TODO
	}
}