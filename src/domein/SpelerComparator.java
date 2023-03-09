package domein;

import java.util.Comparator;

public class SpelerComparator implements Comparator<Speler> {

	@Override
	public int compare(Speler s1, Speler s2) {
		// Brecht: s2 en s1 omgewisseld --> jongste speler
		int vergelijkGeboortejaar = s2.getGeboortejaar() - s1.getGeboortejaar();
		// Brecht: s2 en s1 omgewisseld --> langste gebruikersnaam
		int vergelijkGebruikersnaam = s2.getGebruikersnaam().length() - s1.getGebruikersnaam().length();

		StringBuilder st1r = new StringBuilder(s1.getGebruikersnaam());
		StringBuilder st2r = new StringBuilder(s2.getGebruikersnaam());

		String speler1Omgekeerd = st1r.reverse().toString();
		String speler2Omgekeerd = st2r.reverse().toString();

		return (vergelijkGeboortejaar != 0 ? vergelijkGeboortejaar
				: vergelijkGebruikersnaam != 0 ? vergelijkGebruikersnaam
						: speler1Omgekeerd.compareTo(speler2Omgekeerd));
	}

}
