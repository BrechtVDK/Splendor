package domein;

import java.util.Comparator;

public class SpelerComparator implements Comparator<Speler> {

	@Override
	public int compare(Speler s1, Speler s2) {
		int vergelijkGeboortejaar = s1.getGeboortejaar() - s2.getGeboortejaar();
		int vergelijkGebruikersnaam = s1.getGebruikersnaam().length() - s2.getGebruikersnaam().length();

		StringBuilder st1r = new StringBuilder(s1.getGebruikersnaam());
		StringBuilder st2r = new StringBuilder(s2.getGebruikersnaam());

		String speler1Omgekeerd = st1r.reverse().toString();
		String speler2Omgekeerd = st2r.reverse().toString();

		return (vergelijkGeboortejaar != 0 ? vergelijkGeboortejaar
				: vergelijkGebruikersnaam != 0 ? vergelijkGebruikersnaam
						: speler1Omgekeerd.compareTo(speler2Omgekeerd));
	}

}
