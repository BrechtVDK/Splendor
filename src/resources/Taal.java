package resources;

import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Taal {
	// public static Taal taal = null;

	private static ResourceBundle resBundel;
	private static final String STANDAARDTAAL = "Nederlands";
	public static final String TALEN[] = { "Nederlands", "Français", "Deutsch", "English" };

	public String tekst;

	private Taal(String voorkeurTaal) {

	}

	public static void setVoorkeurTaal(String voorkeurTaal) {
		// String talen[] = {"Nederlands", "Français", "Deutsch", "English"};
		Locale landstaal;
//		int indexTaal;

		if (Arrays.asList(TALEN).indexOf(voorkeurTaal) < 0)
			throw new IllegalArgumentException("Deze taal wordt niet ondersteund in deze applicatie!");
//		else
//			indexTaal = Arrays.asList(talen).indexOf(voorkeurTaal);

		landstaal = switch (Arrays.asList(TALEN).indexOf(voorkeurTaal)) {
		case 0 -> new Locale("nl");
		case 1 -> Locale.FRENCH;
		case 2 -> Locale.GERMAN;
		case 3 -> Locale.ENGLISH;
		default -> {
			new Locale("nl");
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