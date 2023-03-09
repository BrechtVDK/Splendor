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
		return spelerMap.geefSpeler(gebruikersnaam, geboortejaar);

		// Brecht: om TestApp te gebruiken onderstaande uit commentaar halen,
		// bovenstaande in commentaar
		// return (new Speler(gebruikersnaam, geboortejaar));
	}

}
