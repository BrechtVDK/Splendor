package domein;

import persistentie.SpelerMapper;

public class SpelerRepository {

	private final SpelerMapper spelerMap;

	/**
	 * Constructor maakt een nieuwe spelerrepository aan, maakt ook een nieuwe
	 * SpelerMapper-object als attribuut aan.
	 */
	public SpelerRepository() {
		spelerMap = new SpelerMapper();
	}


	/**
	 * Vraagt aan de spelerMapper om een speler terug te geven aan de hand van het
	 * opgegeven geboortejaar en gebruikersnaam.
	 * 
	 * @param gebruikersnaam String met de gebruikersnaam van de gevraagde speler.
	 * @param geboortejaar   Integer met het geboortejaar van de gevraagde speler.
	 * @return Het gevraagde spelerobject.
	 */
	public Speler geefSpeler(String gebruikersnaam, int geboortejaar) {

		return spelerMap.geefSpeler(gebruikersnaam, geboortejaar);



	}

	/**
	 * Vraagt aan de spelerMapper om een nieuwe speler te registreren.
	 * 
	 * @param gebruikersnaam String met de gebruikersnaam van de nieuwe speler.
	 * @param geboortejaar   Integer met het geboortejaar van de nieuwe speler.
	 * @throws IllegalArgumentException Wanneer de gebruikersnaam reeds bestaat in
	 *                                  de database.
	 */
	public void registreerSpeler(String gebruikersnaam, int geboortejaar) throws IllegalArgumentException {
		spelerMap.registreerSpeler(gebruikersnaam, geboortejaar);
	}

}
