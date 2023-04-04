package resources;

import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Taal {
	// public static Taal taal = null;

	private static ResourceBundle resBundel;
	private static final String STANDAARDTAAL = "nl";

	public String tekst;

	private Taal(String voorkeurTaal) {
		// voorkeurtalen ["nl", "en",, "fr", "de"]
		// setVoorkeurTaal(voorkeurTaal);

	}

	public static void setVoorkeurTaal(String voorkeurTaal) {
		String talen[] = { "nl", "en", "fr", "de" };
		Locale landstaal;
//		int indexTaal;

		if (Arrays.asList(talen).indexOf(voorkeurTaal) < 0)
			throw new IllegalArgumentException("Deze taal is niet gedefinieerd in deze applicatie!");
//		else
//			indexTaal = Arrays.asList(talen).indexOf(voorkeurTaal);

		landstaal = switch (voorkeurTaal) {
		case "nl" -> new Locale(voorkeurTaal);
		case "en" -> Locale.ENGLISH;
		case "fr" -> Locale.FRENCH;
		case "de" -> Locale.GERMAN;
		default -> {
			new Locale(voorkeurTaal);
			throw new IllegalArgumentException("Unexpected value: " + voorkeurTaal);
		}
		};

		try {
			resBundel = ResourceBundle.getBundle(Taal.class.getPackageName() + ".woordenschat", landstaal);
		} catch (MissingResourceException e) {
			System.err.println(e.getClassName() + ": " + resBundel.getBaseBundleName() + " is niet gevonden");
		}
	}

	public static String vertaling(String tekst) {
		if (resBundel == null)
			setVoorkeurTaal(STANDAARDTAAL);

		try {
			return resBundel.getString(tekst);
		} catch (MissingResourceException e) {
			System.err.println(e.getClassName() + ": " + resBundel.getBaseBundleName() + " is niet gevonden");
			return tekst + ": niet gevonden";
		}
	}

}