package domein;

import persistentie.SpelerMapper;

public class SpelerRepository {

	private final SpelerMapper spelerMap;

	public SpelerRepository() {
		spelerMap = new SpelerMapper();
	}


	public Speler geefSpeler(String gebruikersnaam, int geboortejaar) {

		// Geen try and catch. als de Speler niet bestaat in de DB wordt er null
		// gereturned. Op te lossen bij de aanroeper als nullpointException.
		// Jonas: heb dit in klasse spel bijgevoegd; speler kan niet toegevoegd worden
		// aan spel als deze niet bestaat

		return spelerMap.geefSpeler(gebruikersnaam, geboortejaar);

		// Jonas: onderstaade moet uit commentaar om de gui te kunnen gebruiken;
		// return new Speler(gebruikersnaam, geboortejaar);

	}

}
