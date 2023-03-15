package domein;

import java.util.Calendar;
import java.util.Objects;

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;
	private final static int MINIMUMLEEFTIJD = 6;
	private final static int MINIMUMGEBOORTEJAAR = 1900;
	private boolean isStartSpeler;

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
		if (geboortejaar < 1900) {
			throw new IllegalArgumentException("Gelieve een geldig geboortejaar in te geven");
		}
		if (geboortejaar > huidigJaar - MINIMUMLEEFTIJD) {
			throw new IllegalArgumentException(String.format("Minimum leeftijd is %d jaar;", MINIMUMLEEFTIJD));
		}
		this.geboortejaar = geboortejaar;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public int getGeboortejaar() {
		return geboortejaar;
	}

	public boolean isStartSpeler() {
		return isStartSpeler;
	}

	public final void setStartSpeler(boolean isStartSpeler) {
		this.isStartSpeler = isStartSpeler;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gebruikersnaam);
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
		return Objects.equals(gebruikersnaam, other.gebruikersnaam);
	}

	@Override
	public String toString() {
		return gebruikersnaam;
	}

}
