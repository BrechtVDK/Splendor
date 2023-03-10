package testen;

import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Speler;

class SpelerTest {

	private Speler speler;
	private final int GELDIG_GEBOORTEJAAR = Calendar.getInstance().get(Calendar.YEAR) - 6;
	private final String GELDIGE_GEBRUIKERSNAAM = "Jan_0 3";



	// jonas: weet niet goed of je 2x mag testen binnen één methode
	@ParameterizedTest
	@ValueSource(strings = { "abc", "a bc", "abc ", "a_b", "ABc", "a12", "Aa 1_3", GELDIGE_GEBRUIKERSNAAM })
	public void maakSpeler_geldigGeboortejaar_GeldigeGebruikersnaam_maaktSpeler(String naam) {
		speler = new Speler(naam, GELDIG_GEBOORTEJAAR);
		Assertions.assertEquals(naam, speler.getGebruikersnaam());
		Assertions.assertEquals(GELDIG_GEBOORTEJAAR, speler.getGeboortejaar());
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { " ab", "1bc", "&ab", "ab%", "A B€" })
	public void maakSpeler_geldigGeboortejaar_foutieveGebruikersnaam_Exception(String naam) {

		Assertions.assertThrows(IllegalArgumentException.class, () -> new Speler(naam, GELDIG_GEBOORTEJAAR));
	}

	@ParameterizedTest
	@ValueSource(ints = { 123, 0123 })
	public void maakSpeler_foutiefFormaatGeboortejaarCorrecteGebruikersnaam_Exeption(int geboortejaar) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Speler(GELDIGE_GEBRUIKERSNAAM, geboortejaar));
	}

	@Test
	public void maakSpeler_spelerJongerDan6JaarCorrecteGebruikersnaam_Exeption()
	{
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Speler(GELDIGE_GEBRUIKERSNAAM, GELDIG_GEBOORTEJAAR + 1));
	}
}
