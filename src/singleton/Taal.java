package singleton;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class Taal {
	private static Taal taal = null;
	private String talen[] = { "nl", "en", "fr", "de" };

	Locale land;
	ResourceBundle labels;

	public String tekst;
	private String voorkeurTaal;
	private int indexTaal;

	private Taal(String voorkeurTaal) {
		// voorkeurtalen ["nl", "en",, "fr", "de"]
		setVoorkeurTaal(voorkeurTaal);
		this.indexTaal = Arrays.asList(talen).indexOf(voorkeurTaal);
	}

	public void setVoorkeurTaal(String voorkeurTaal) {
		if (Arrays.asList(talen).indexOf(voorkeurTaal) < 0)
			throw new IllegalArgumentException("Deze taal is niet gedefinieerd in deze applicatie!");
		this.voorkeurTaal = voorkeurTaal;
	}

	private String geefVertaling(String tekst) {

		return tekst;
	}

	public static String vertaling(String voorkeurTaal, String tekst) {
		if (taal == null)
			taal = new Taal(voorkeurTaal);
		return null;
	}

}