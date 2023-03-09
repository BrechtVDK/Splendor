package domein;

import java.util.Calendar;
import java.util.Objects;

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;

	public Speler(String gebruikersnaam, int geboortejaar) {
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
	}

	private void setGebruikersnaam(String gebruikersnaam) throws IllegalArgumentException {
		String REGEX = "^[a-z,A-Z][\\w|\\s]*";
		if (gebruikersnaam == null || !gebruikersnaam.matches(REGEX)) {
			throw new IllegalArgumentException(
					"Gebruikersnaam mag enkel cijfers,letters, spaties of _ bevatten. De gebruikersnaam moet altijd starten met een letter (groot of klein)");
		}
		this.gebruikersnaam = gebruikersnaam;
	}

	private void setGeboortejaar(int geboortejaar) throws IllegalArgumentException {
		int huidigJaar = Calendar.getInstance().get(Calendar.YEAR);
		if (geboortejaar < huidigJaar - 6) {
			throw new IllegalArgumentException("Minimum leeftijd is 6 jaar;");
		} else if (geboortejaar > huidigJaar) {
			throw new IllegalArgumentException("Geboortejaar is niet geldig.");
		}
		this.geboortejaar = geboortejaar;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public int getGeboortejaar() {
		return geboortejaar;
	}

	// hier zou ik de vergelijking uitwerken om de startspeler te kunnen selecteren
	// #Jonas
	@Override
	public int hashCode() {
		return Objects.hash(geboortejaar, gebruikersnaam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Speler other = (Speler) obj;
		return geboortejaar == other.geboortejaar && Objects.equals(gebruikersnaam, other.gebruikersnaam);
	}

}
