package domein;

import java.util.Objects;

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;

	public Speler(String gebruikersnaam, int geboortejaar) {
		this.gebruikersnaam = gebruikersnaam;
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
