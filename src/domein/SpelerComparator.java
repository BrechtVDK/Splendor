package domein;

import java.util.Comparator;

public class SpelerComparator implements Comparator<Speler> {

	@Override
	public int compare(Speler s1, Speler s2) {
		// Brecht: s2 en s1 omgewisseld --> jongste speler
		int vergelijkGeboortejaar = s2.getGeboortejaar() - s1.getGeboortejaar();
		// Brecht: s2 en s1 omgewisseld --> langste gebruikersnaam
		int vergelijkGebruikersnaam = s2.getGebruikersnaam().length() - s1.getGebruikersnaam().length();



		return (vergelijkGeboortejaar != 0 ? vergelijkGeboortejaar
				: vergelijkGebruikersnaam != 0 ? vergelijkGebruikersnaam
						: s2.getGebruikersnaam().compareTo(s1.getGebruikersnaam()));
	}

}
