package domein;

import persistentie.SpelerMapper;

public class SpelerRepository {

	private final SpelerMapper spelerMap;

	public SpelerRepository() {
		spelerMap = new SpelerMapper();
	}


	public Speler geefSpeler(String gebruikersnaam, int geboortejaar) {

		return spelerMap.geefSpeler(gebruikersnaam, geboortejaar);



	}

}
