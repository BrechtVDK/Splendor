package testen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.DomeinController;
import domein.Edele;
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

	// spel met 3 spelers, 10 beheersbare edelen en 12 beheersbare kaarten aanmaken
	// (niet random)
	@Nested
	class NestedTests {
		private DomeinController dc;
		private List<Ontwikkelingskaart> ontwikkelingskaarten;
		private ArrayList<Edelsteenfiche> fiches3BlauwGroenRood, fiches3RoodWitZwart, fiches2Blauw, fiches2Groen,
				fiches2Rood, fiches2Wit, fiches2Zwart;
		private Ontwikkelingskaart[][] kaarten;
		private Edele edele1, edele2, edele3, edele4, edele5, edele6, edele7, edele8, edele9, edele10;

		@BeforeEach
		public void beforeEach2() {
			dc = new DomeinController();
			dc.startNieuwSpel();
			// kaarten tafel zelf instellen
			kaarten = new Ontwikkelingskaart[3][4];
			// volgorde int[] {GROEN, WIT, BLAUW, ZWART, ROOD}
			kaarten[0][0] = new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ROOD),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 6, 0, 3, 0, 3 }));
			kaarten[0][1] = new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.ZWART),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 0, 3, 7 }));
			kaarten[0][2] = new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.ZWART),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 0, 3, 6 }));
			kaarten[0][3] = new Ontwikkelingskaart(4, new Edelsteenfiche(Edelsteen.GROEN),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 3, 6, 0, 0 }));
			kaarten[1][0] = new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.ZWART),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 5, 0, 0, 0 }));
			kaarten[1][1] = new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ZWART),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 2, 3, 2, 0, 0 }));
			kaarten[1][2] = new Ontwikkelingskaart(1, new Edelsteenfiche(Edelsteen.ROOD),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 0, 3, 3, 2 }));
			kaarten[1][3] = new Ontwikkelingskaart(2, new Edelsteenfiche(Edelsteen.WIT),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 0, 0, 2, 4 }));
			kaarten[2][0] = new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 3, 0, 0, 0 }));
			kaarten[2][1] = new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ROOD),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 1, 2, 0, 2, 0 }));
			kaarten[2][2] = new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.GROEN),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 0, 2, 1, 0, 0 }));
			kaarten[2][3] = new Ontwikkelingskaart(0, new Edelsteenfiche(Edelsteen.ZWART),
					EdelsteenficheFactory.maakArrayEdelsteenfiches(new int[] { 3, 0, 0, 0, 0 }));

			// edelen zelf maken
			// volgorde int[] {GROEN, WIT, BLAUW, ZWART, ROOD}
			int[][] aantalFichesPerSoortPerEdele = { { 0, 4, 0, 4, 0 }, { 0, 3, 0, 3, 3 }, { 0, 3, 3, 3, 0 },
					{ 3, 3, 3, 0, 0 }, { 3, 0, 0, 3, 3 }, { 3, 0, 3, 0, 3 }, { 4, 0, 4, 0, 0 }, { 0, 0, 0, 4, 4 },
					{ 0, 4, 4, 0, 0 }, { 4, 0, 0, 0, 4 } };
			edele1 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[0]));
			edele2 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[1]));
			edele3 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[2]));
			edele4 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[3]));
			edele5 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[4]));
			edele6 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[5]));
			edele7 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[6]));
			edele8 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[7]));
			edele9 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[8]));
			edele10 = new Edele(EdelsteenficheFactory.maakArrayEdelsteenfiches(aantalFichesPerSoortPerEdele[9]));

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
			try {
				// 3pt
				dc.verplaatsEdeleVanSpelNaarSpeler(edele4);
			} catch (Exception e) {
				// niks doen als edele niet in spel zit om te verwijderen
			}
			try {
				// 3pt
				dc.verplaatsEdeleVanSpelNaarSpeler(edele10);
			} catch (Exception e) {
				// niks doen als edele niet in spel zit om te verwijderen
			}

			// spelsituatie: Brecht: 6pt, Jonas: 0pt, Davis: 0pt
			Assertions.assertEquals(false, dc.isEindeSpel());
		}

		@Test
		public void isEindeSpel_NetGenoegPunten_geeftTrue() {
			// TODO
		}

		@Test
		public void isEindeSpel_GenoegPunten_geeftTrue() {
			// TODO
		}

// TODO spel.geefWinnaars()
// Momenteel nog niet te testen door de protected methodes in Speler

// UC3
// TODO
// UC4
// TODO
	}
}