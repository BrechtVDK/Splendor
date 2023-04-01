package testen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

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

			// fiches maken om te gebruiken als parameter
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
		}

		@Test
		public void isEindeSpel_teWeinigPunten_geeftFalse() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(6, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			// spelsituatie: Brecht: 6pt, Jonas: 0pt, Davi: 0pt
			Assertions.assertFalse(dc.isEindeSpel());
		}

		@Test
		public void isEindeSpel_NetGenoegPunten_geeftTrue() {

			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(15, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(12, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			// spelsituatie: Brecht: 15pt, Jonas: 0pt, David: 12pt
			Assertions.assertTrue(dc.isEindeSpel());

		}

		@Test
		public void isEindeSpel_GenoegPuntenMeerdereSpelers_geeftTrue() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(16, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(17, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			// spelsituatie: Brecht: 16pt, Jonas: 0pt, David: 17pt
			Assertions.assertTrue(dc.isEindeSpel());
		}

		@Test
		public void geefWinnaars_geenWinnaars_legeLijst() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(6, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));

			// spelsituatie: Brecht: 6pt, Jonas: 0pt, David: 0pt
			Assertions.assertEquals(new ArrayList<String>(), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_1speler15punten_juisteWinnaar() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(15, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(14, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			// spelsituatie: Brecht: 15pt, Jonas: 0pt, David: 14pt
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("Brecht")), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_2spelersMeerDan15puntenGelijkAantalKaarten_juisteWinnaarHoogstePunten() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(15, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(17, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			// spelsituatie: Brecht: 15pt 1kaart, Jonas: 0pt, David: 17pt 1kaart
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("David")), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_2spelersMeerDan15puntenOngelijkAantalKaarten_juisteWinnaarHoogstePunten() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(15, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(10, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(7, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			// spelsituatie: Brecht: 15pt 1kaart, Jonas: 0pt, David: 17pt 2kaart
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("David")), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_2spelersGelijkePuntenOngelijkAantalKaarten_juisteWinnaarLaagsteAantalKaarten() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(17, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(10, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(7, new Edelsteenfiche(Edelsteen.BLAUW), new Edelsteenfiche[0]));
			// spelsituatie: Brecht: 17pt 1kaart, Jonas: 0pt, David: 17pt 2kaart
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("Brecht")), dc.geefNamenWinnaars());
		}

		@Test
		public void geefWinnaars_2spelersGelijkePuntenGelijkAantalKaarten_2juisteWinnaars() {
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(10, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(7, new Edelsteenfiche(Edelsteen.WIT), new Edelsteenfiche[0]));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(10, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(7, new Edelsteenfiche(Edelsteen.BLAUW), new Edelsteenfiche[0]));
			dc.bepaalVolgendeSpeler();
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
					new Ontwikkelingskaart(5, new Edelsteenfiche(Edelsteen.ROOD), new Edelsteenfiche[0]));
			// spelsituatie: Brecht: 17pt 2kaart, Jonas: 5pt, David: 17pt 2kaart
			Assertions.assertEquals(new ArrayList<>(Arrays.asList("Brecht", "David")), dc.geefNamenWinnaars());
		}

// UC3
		@Test
		public void bepaalVolgendeSpeler_correcteSpelerAanDeBeurt() {
			dc.bepaalVolgendeSpeler();
			Assertions.assertEquals("David", dc.geefSpelerAanDeBeurt().toString());
		}

		@Test
		public void verplaatsEdeleVanSpelNaarSpeler_edeleUitSpelEdeleBijSpeler() {
			Edele edele = dc.geefEdelen().get(0);
			dc.verplaatsEdeleVanSpelNaarSpeler(edele);
			// niet meer in spel
			Assertions.assertTrue(dc.geefEdelen().indexOf(edele) == -1);
			// wel bij speler
			Assertions.assertTrue(dc.geefSpelerAanDeBeurt().getEdelenInBezit().indexOf(edele) != -1);
		}

//UC4
		@Test
		public void verplaatsEdelsteenfichesNaarSpeler_1zwarte_correctAantalToegevoegdAanSpeler() {
			Map<Edelsteen, Integer> spelerFiches = dc.geefSpelerAanDeBeurt().getAantalEdelsteenfichesPerTypeInBezit();
			int aantalBlauwVoor = spelerFiches.get(Edelsteen.BLAUW);
			int aantalGroenVoor = spelerFiches.get(Edelsteen.GROEN);
			int aantalRoodVoor = spelerFiches.get(Edelsteen.ROOD);
			int aantalWitVoor = spelerFiches.get(Edelsteen.WIT);
			int aantalZwartVoor = spelerFiches.get(Edelsteen.ZWART);
			dc.verplaatsEdelsteenfichesNaarSpeler(
					new ArrayList<Edelsteenfiche>(Arrays.asList(new Edelsteenfiche(Edelsteen.ZWART))));
			int aantalBlauwNa = spelerFiches.get(Edelsteen.BLAUW);
			int aantalGroenNa = spelerFiches.get(Edelsteen.GROEN);
			int aantalRoodNa = spelerFiches.get(Edelsteen.ROOD);
			int aantalWitNa = spelerFiches.get(Edelsteen.WIT);
			int aantalZwartNa = spelerFiches.get(Edelsteen.ZWART);
			Assertions.assertEquals(aantalBlauwVoor, aantalBlauwNa);
			Assertions.assertEquals(aantalGroenVoor, aantalGroenNa);
			Assertions.assertEquals(aantalRoodVoor, aantalRoodNa);
			Assertions.assertEquals(aantalWitVoor, aantalWitNa);
			Assertions.assertEquals(aantalZwartVoor + 1, aantalZwartNa);
		}

		@Test
		public void verplaatsEdelsteenfichesNaarSpeler_2Witte_correctAantalToegevoegdAanSpeler() {
			Map<Edelsteen, Integer> spelerFiches = dc.geefSpelerAanDeBeurt().getAantalEdelsteenfichesPerTypeInBezit();
			int aantalBlauwVoor = spelerFiches.get(Edelsteen.BLAUW);
			int aantalGroenVoor = spelerFiches.get(Edelsteen.GROEN);
			int aantalRoodVoor = spelerFiches.get(Edelsteen.ROOD);
			int aantalWitVoor = spelerFiches.get(Edelsteen.WIT);
			int aantalZwartVoor = spelerFiches.get(Edelsteen.ZWART);
			dc.verplaatsEdelsteenfichesNaarSpeler(fiches2Wit);
			int aantalBlauwNa = spelerFiches.get(Edelsteen.BLAUW);
			int aantalGroenNa = spelerFiches.get(Edelsteen.GROEN);
			int aantalRoodNa = spelerFiches.get(Edelsteen.ROOD);
			int aantalWitNa = spelerFiches.get(Edelsteen.WIT);
			int aantalZwartNa = spelerFiches.get(Edelsteen.ZWART);
			Assertions.assertEquals(aantalBlauwVoor, aantalBlauwNa);
			Assertions.assertEquals(aantalGroenVoor, aantalGroenNa);
			Assertions.assertEquals(aantalRoodVoor, aantalRoodNa);
			Assertions.assertEquals(aantalWitVoor + 2, aantalWitNa);
			Assertions.assertEquals(aantalZwartVoor, aantalZwartNa);
		}

		@Test
		public void verplaatsEdelsteenfichesNaarSpeler_blauwGroenRood_correctAantalToegevoegdAanSpeler() {
			Map<Edelsteen, Integer> spelerFiches = dc.geefSpelerAanDeBeurt().getAantalEdelsteenfichesPerTypeInBezit();
			int aantalBlauwVoor = spelerFiches.get(Edelsteen.BLAUW);
			int aantalGroenVoor = spelerFiches.get(Edelsteen.GROEN);
			int aantalRoodVoor = spelerFiches.get(Edelsteen.ROOD);
			int aantalWitVoor = spelerFiches.get(Edelsteen.WIT);
			int aantalZwartVoor = spelerFiches.get(Edelsteen.ZWART);
			dc.verplaatsEdelsteenfichesNaarSpeler(fiches3BlauwGroenRood);
			int aantalBlauwNa = spelerFiches.get(Edelsteen.BLAUW);
			int aantalGroenNa = spelerFiches.get(Edelsteen.GROEN);
			int aantalRoodNa = spelerFiches.get(Edelsteen.ROOD);
			int aantalWitNa = spelerFiches.get(Edelsteen.WIT);
			int aantalZwartNa = spelerFiches.get(Edelsteen.ZWART);
			Assertions.assertEquals(aantalBlauwVoor + 1, aantalBlauwNa);
			Assertions.assertEquals(aantalGroenVoor + 1, aantalGroenNa);
			Assertions.assertEquals(aantalRoodVoor + 1, aantalRoodNa);
			Assertions.assertEquals(aantalWitVoor, aantalWitNa);
			Assertions.assertEquals(aantalZwartVoor, aantalZwartNa);
		}

		@Test
		public void verplaatsEdelsteenfichesNaarSpeler_1WitteOpStapelNa2WitteTeNemen_Exception() {
			// 4 witte verwijderen = 1 resterend
			for (int i = 0; i < 4; i++) {
				dc.verwijderEdelsteenficheVanStapel(new Edelsteenfiche(Edelsteen.WIT));
			}
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> dc.verplaatsEdelsteenfichesNaarSpeler(fiches2Wit));
		}

		@Test
		public void verplaatsEdelsteenfichesNaarSpeler_3nietUniekeFiches_Exception() {
			ArrayList<Edelsteenfiche> fiches = fiches2Wit;
			fiches.add(new Edelsteenfiche(Edelsteen.BLAUW));
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> dc.verplaatsEdelsteenfichesNaarSpeler(fiches));
		}

		@Test
		public void verplaatsEdelsteenfichesNaarSpeler_meerDanMaxFichesPerBeurt_Exception() {
			ArrayList<Edelsteenfiche> fiches = new ArrayList<>();
			for (int i = 0; i <= Spel.MAX_FICHES_PER_BEURT; i++) {
				fiches.add(new Edelsteenfiche(Edelsteen.values()[i % Edelsteen.values().length]));
			}
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> dc.verplaatsEdelsteenfichesNaarSpeler(fiches));
		}

		@Test
		public void verplaatsEdelsteenfichesNaarSpeler_meerDanMaxFichesInBezit_Exception() {
			// tot aan max toevoegen
			for (int i = 0; i < Speler.MAX_FICHES_IN_BEZIT; i++) {
				dc.verplaatsEdelsteenfichesNaarSpeler(new ArrayList<Edelsteenfiche>(
						Arrays.asList(new Edelsteenfiche(Edelsteen.values()[i % Edelsteen.values().length]))));
			}
			Assertions.assertThrows(IllegalArgumentException.class, () -> dc.verplaatsEdelsteenfichesNaarSpeler(
					new ArrayList<Edelsteenfiche>(Arrays.asList(new Edelsteenfiche(Edelsteen.BLAUW)))));
		}

		@Test
		public void verwijderEdelsteenficheVanStapel_1witte_correctVerwijderd() {
			Map<Edelsteen, Integer> stapels = dc.geefAantalFichesPerStapel();
			int aantalBlauwVoor = stapels.get(Edelsteen.BLAUW);
			int aantalGroenVoor = stapels.get(Edelsteen.GROEN);
			int aantalRoodVoor = stapels.get(Edelsteen.ROOD);
			int aantalWitVoor = stapels.get(Edelsteen.WIT);
			int aantalZwartVoor = stapels.get(Edelsteen.ZWART);
			dc.verwijderEdelsteenficheVanStapel(new Edelsteenfiche(Edelsteen.WIT));
			Map<Edelsteen, Integer> stapelsNa = dc.geefAantalFichesPerStapel();
			int aantalBlauwNa = stapelsNa.get(Edelsteen.BLAUW);
			int aantalGroenNa = stapelsNa.get(Edelsteen.GROEN);
			int aantalRoodNa = stapelsNa.get(Edelsteen.ROOD);
			int aantalWitNa = stapelsNa.get(Edelsteen.WIT);
			int aantalZwartNa = stapelsNa.get(Edelsteen.ZWART);
			Assertions.assertEquals(aantalBlauwVoor, aantalBlauwNa);
			Assertions.assertEquals(aantalGroenVoor, aantalGroenNa);
			Assertions.assertEquals(aantalRoodVoor, aantalRoodNa);
			Assertions.assertEquals(aantalWitVoor, aantalWitNa + 1);
			Assertions.assertEquals(aantalZwartVoor, aantalZwartNa);
		}

		@Test
		public void verwijderEdelsteenficheVanStapel_1witteVerwijderenVanLegeStapel_Exception() {
			// stapel leegmaken
			int aantalWitteOpStapel = dc.geefAantalFichesPerStapel().get(Edelsteen.WIT);
			for (int i = 0; i < aantalWitteOpStapel; i++) {
				dc.verwijderEdelsteenficheVanStapel(new Edelsteenfiche(Edelsteen.WIT));
			}
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> dc.verwijderEdelsteenficheVanStapel(new Edelsteenfiche(Edelsteen.WIT)));
		}

		@Test
		public void verplaatsEdelsteenfichesVanSpelerNaarSpel_roodWitZwart_correctVerplaatst() {
			dc.verplaatsEdelsteenfichesNaarSpeler(fiches3RoodWitZwart);
			dc.verplaatsEdelsteenfichesNaarSpeler(fiches3BlauwGroenRood);
			Map<Edelsteen, Integer> stapelsVoor = dc.geefAantalFichesPerStapel();
			Map<Edelsteen, Integer> spelerVoor = dc.geefSpelerAanDeBeurt().getAantalEdelsteenfichesPerTypeInBezit();
			int aantalBlauwStapelVoor = stapelsVoor.get(Edelsteen.BLAUW);
			int aantalGroenStapelVoor = stapelsVoor.get(Edelsteen.GROEN);
			int aantalRoodStapelVoor = stapelsVoor.get(Edelsteen.ROOD);
			int aantalWitStapelVoor = stapelsVoor.get(Edelsteen.WIT);
			int aantalZwartStapelVoor = stapelsVoor.get(Edelsteen.ZWART);
			int aantalBlauwSpelerVoor = spelerVoor.get(Edelsteen.BLAUW);
			int aantalGroenSpelerVoor = spelerVoor.get(Edelsteen.GROEN);
			int aantalRoodSpelerVoor = spelerVoor.get(Edelsteen.ROOD);
			int aantalWitSpelerVoor = spelerVoor.get(Edelsteen.WIT);
			int aantalZwartSpelerVoor = spelerVoor.get(Edelsteen.ZWART);
			dc.verplaatsEdelsteenfichesVanSpelerNaarSpel(fiches3RoodWitZwart);
			Map<Edelsteen, Integer> stapelsNa = dc.geefAantalFichesPerStapel();
			Map<Edelsteen, Integer> spelerNa = dc.geefSpelerAanDeBeurt().getAantalEdelsteenfichesPerTypeInBezit();
			int aantalBlauwStapelNa = stapelsNa.get(Edelsteen.BLAUW);
			int aantalGroenStapelNa = stapelsNa.get(Edelsteen.GROEN);
			int aantalRoodStapelNa = stapelsNa.get(Edelsteen.ROOD);
			int aantalWitStapelNa = stapelsNa.get(Edelsteen.WIT);
			int aantalZwartStapelNa = stapelsNa.get(Edelsteen.ZWART);
			int aantalBlauwSpelerNa = spelerNa.get(Edelsteen.BLAUW);
			int aantalGroenSpelerNa = spelerNa.get(Edelsteen.GROEN);
			int aantalRoodSpelerNa = spelerNa.get(Edelsteen.ROOD);
			int aantalWitSpelerNa = spelerNa.get(Edelsteen.WIT);
			int aantalZwartSpelerNa = spelerNa.get(Edelsteen.ZWART);
			Assertions.assertEquals(aantalBlauwStapelVoor, aantalBlauwStapelNa);
			Assertions.assertEquals(aantalGroenStapelVoor, aantalGroenStapelNa);
			Assertions.assertEquals(aantalRoodStapelVoor + 1, aantalRoodStapelNa);
			Assertions.assertEquals(aantalWitStapelVoor + 1, aantalWitStapelNa);
			Assertions.assertEquals(aantalZwartStapelVoor + 1, aantalZwartStapelNa);
			Assertions.assertEquals(aantalBlauwSpelerVoor, aantalBlauwSpelerNa);
			Assertions.assertEquals(aantalGroenSpelerVoor, aantalGroenSpelerNa);
			Assertions.assertEquals(aantalRoodSpelerVoor - 1, aantalRoodSpelerNa);
			Assertions.assertEquals(aantalWitSpelerVoor - 1, aantalWitSpelerNa);
			Assertions.assertEquals(aantalZwartSpelerVoor - 1, aantalZwartSpelerNa);
		}

		@Test
		public void verplaatsEdelsteenfichesVanSpelerNaarSpel_ficheNietInBezit_Exception() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> dc.verplaatsEdelsteenfichesVanSpelerNaarSpel(
					new ArrayList<>(Arrays.asList(new Edelsteenfiche(Edelsteen.WIT)))));
		}

		@Test
		public void verplaatsOntwikkelingskaartVanTafelNaarSpeler_spelerHeeftGenoegFichesEnBonussen_kaartCorrectVerplaatst_edelsteenfichesCorrectVerwerkt() {
			Ontwikkelingskaart kaart = dc.geefZichtbareOntwikkelingskaarten()[2][1];
			// aantal te verreken fiches (-1 omdat speler van ieder bonus heeft)
			int aantalBlauw = -1;
			int aantalGroen = -1;
			int aantalRood = -1;
			int aantalWit = -1;
			int aantalZwart = -1;
			// fiches toevoegen aan speler om kaart te kunnen kopen
			for (Edelsteenfiche e : kaart.edelsteenfiches()) {
				dc.verplaatsEdelsteenfichesNaarSpeler(new ArrayList<>(Arrays.asList(e)));
				switch (e.edelsteen().name()) {
				case "BLAUW" -> aantalBlauw++;
				case "GROEN" -> aantalGroen++;
				case "ROOD" -> aantalRood++;
				case "WIT" -> aantalWit++;
				case "ZWART" -> aantalZwart++;
				}
			}
			// bonus van elke soort toevoegen door onbestaande kaart toe te voegen
			for (Edelsteen e : Edelsteen.values()) {
				dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(
						new Ontwikkelingskaart(0, new Edelsteenfiche(e), new Edelsteenfiche[0]));
			}

			Map<Edelsteen, Integer> stapelsVoor = dc.geefAantalFichesPerStapel();
			Map<Edelsteen, Integer> spelerVoor = dc.geefSpelerAanDeBeurt().getAantalEdelsteenfichesPerTypeInBezit();
			int aantalBlauwStapelVoor = stapelsVoor.get(Edelsteen.BLAUW);
			int aantalGroenStapelVoor = stapelsVoor.get(Edelsteen.GROEN);
			int aantalRoodStapelVoor = stapelsVoor.get(Edelsteen.ROOD);
			int aantalWitStapelVoor = stapelsVoor.get(Edelsteen.WIT);
			int aantalZwartStapelVoor = stapelsVoor.get(Edelsteen.ZWART);
			int aantalBlauwSpelerVoor = spelerVoor.get(Edelsteen.BLAUW);
			int aantalGroenSpelerVoor = spelerVoor.get(Edelsteen.GROEN);
			int aantalRoodSpelerVoor = spelerVoor.get(Edelsteen.ROOD);
			int aantalWitSpelerVoor = spelerVoor.get(Edelsteen.WIT);
			int aantalZwartSpelerVoor = spelerVoor.get(Edelsteen.ZWART);
			dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(kaart);
			Map<Edelsteen, Integer> stapelsNa = dc.geefAantalFichesPerStapel();
			Map<Edelsteen, Integer> spelerNa = dc.geefSpelerAanDeBeurt().getAantalEdelsteenfichesPerTypeInBezit();
			int aantalBlauwStapelNa = stapelsNa.get(Edelsteen.BLAUW);
			int aantalGroenStapelNa = stapelsNa.get(Edelsteen.GROEN);
			int aantalRoodStapelNa = stapelsNa.get(Edelsteen.ROOD);
			int aantalWitStapelNa = stapelsNa.get(Edelsteen.WIT);
			int aantalZwartStapelNa = stapelsNa.get(Edelsteen.ZWART);
			int aantalBlauwSpelerNa = spelerNa.get(Edelsteen.BLAUW);
			int aantalGroenSpelerNa = spelerNa.get(Edelsteen.GROEN);
			int aantalRoodSpelerNa = spelerNa.get(Edelsteen.ROOD);
			int aantalWitSpelerNa = spelerNa.get(Edelsteen.WIT);
			int aantalZwartSpelerNa = spelerNa.get(Edelsteen.ZWART);

			Assertions.assertFalse(dc.geefZichtbareOntwikkelingskaarten()[1][1] == kaart);
			Assertions.assertTrue(dc.geefSpelerAanDeBeurt().getOntwikkelingskaartenInBezit().indexOf(kaart) != 1);
			Assertions.assertEquals(aantalBlauwStapelVoor + (aantalBlauw > 0 ? aantalBlauw : 0), aantalBlauwStapelNa);
			Assertions.assertEquals(aantalGroenStapelVoor + (aantalGroen > 0 ? aantalGroen : 0), aantalGroenStapelNa);
			Assertions.assertEquals(aantalRoodStapelVoor + (aantalRood > 0 ? aantalRood : 0), aantalRoodStapelNa);
			Assertions.assertEquals(aantalWitStapelVoor + (aantalWit > 0 ? aantalWit : 0), aantalWitStapelNa);
			Assertions.assertEquals(aantalZwartStapelVoor + (aantalZwart > 0 ? aantalZwart : 0), aantalZwartStapelNa);
			Assertions.assertEquals(aantalBlauwSpelerVoor - (aantalBlauw > 0 ? aantalBlauw : 0), aantalBlauwSpelerNa);
			Assertions.assertEquals(aantalGroenSpelerVoor - (aantalGroen > 0 ? aantalGroen : 0), aantalGroenSpelerNa);
			Assertions.assertEquals(aantalRoodSpelerVoor - (aantalRood > 0 ? aantalRood : 0), aantalRoodSpelerNa);
			Assertions.assertEquals(aantalWitSpelerVoor - (aantalWit > 0 ? aantalWit : 0), aantalWitSpelerNa);
			Assertions.assertEquals(aantalZwartSpelerVoor - (aantalZwart > 0 ? aantalZwart : 0), aantalZwartSpelerNa);
		}

		@Test
		public void verplaatsOntwikkelingskaartVanTafelNaarSpeler_spelerHeeftTeWeinigFiches_Exception() {
			Ontwikkelingskaart kaart = dc.geefZichtbareOntwikkelingskaarten()[2][1];
			// fiches toevoegen aan speler om kaart te kunnen kopen
			for (Edelsteenfiche e : kaart.edelsteenfiches()) {
				dc.verplaatsEdelsteenfichesNaarSpeler(new ArrayList<>(Arrays.asList(e)));
			}
			// eentje verwijderen
			dc.verplaatsEdelsteenfichesVanSpelerNaarSpel(Arrays.asList(kaart.edelsteenfiches()[0]));

			Assertions.assertThrows(IllegalArgumentException.class,
					() -> dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(kaart));
		}

		@Test
		public void verplaatsOntwikkelingskaartVanTafelNaarSpeler_kaartIsNull_Exception() {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> dc.verplaatsOntwikkelingskaartVanTafelNaarSpeler(null));
		}

	}
}