package domein;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import Exceptions.TeVeelFichesInBezitException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;
	private final static int MINIMUMLEEFTIJD = 6;
	private final static int MINIMUMGEBOORTEJAAR = 1900;
	public static final int MAX_FICHES_IN_BEZIT = 10;
	private boolean isStartSpeler;
	private ObservableList<Ontwikkelingskaart> ontwikkelingskaartenInBezit;
	private IntegerProperty prestigepunten;
	private ObservableMap<Edelsteen, Integer> aantalEdelsteenfichesPerTypeInBezit;
	private ObservableMap<Edelsteen, Integer> aantalBonussenPerTypeInBezit;
	private ObservableList<Edele> edelenInBezit;


	// UC1
	public Speler(String gebruikersnaam, int geboortejaar) {
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
	}

	private void setGebruikersnaam(String gebruikersnaam) throws IllegalArgumentException {
		String REGEX = "^[a-z,A-Z][\\w|\\s]*";
		if (gebruikersnaam == null || !gebruikersnaam.matches(REGEX)) {
			throw new IllegalArgumentException(
					"Gebruikersnaam mag enkel cijfers, letters, spaties of _ bevatten. De gebruikersnaam moet altijd starten met een letter (groot of klein)");
		}
		this.gebruikersnaam = gebruikersnaam;
	}

	private void setGeboortejaar(int geboortejaar) throws IllegalArgumentException {
		int huidigJaar = Calendar.getInstance().get(Calendar.YEAR);
		if (geboortejaar < MINIMUMGEBOORTEJAAR) {
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

	protected final void setStartSpeler(boolean isStartSpeler) {
		this.isStartSpeler = isStartSpeler;
	}

	// UC2

	protected void stelSpelAttributenIn() {
		this.prestigepunten = new SimpleIntegerProperty(0);
		this.edelenInBezit = FXCollections.observableArrayList();
		this.ontwikkelingskaartenInBezit = FXCollections.observableArrayList();
		this.aantalEdelsteenfichesPerTypeInBezit = FXCollections.observableHashMap();
		this.aantalBonussenPerTypeInBezit = FXCollections.observableHashMap();
		for (Edelsteen e : Edelsteen.values()) {
			aantalEdelsteenfichesPerTypeInBezit.put(e, 0);
			aantalBonussenPerTypeInBezit.put(e, 0);
		}
	}

	public ObservableList<Ontwikkelingskaart> getOntwikkelingskaartenInBezit() {
		return ontwikkelingskaartenInBezit;
	}

	protected void voegOntwikkelingskaartToe(Ontwikkelingskaart kaart) {
		ontwikkelingskaartenInBezit.add(kaart);
		prestigepunten.set(prestigepunten.get() + kaart.prestigePunten());
		voegBonusToe(kaart.bonus());
	}

	public int getPrestigepunten() {
		return prestigepunten.get();
	}

	public IntegerProperty prestigepuntenProperty() {
		return prestigepunten;
	}

	public ObservableMap<Edelsteen, Integer> getAantalEdelsteenfichesPerTypeInBezit() {
		return aantalEdelsteenfichesPerTypeInBezit;
	}

	protected void voegEdelsteenfichesToe(List<Edelsteenfiche> edelsteenfiches) throws TeVeelFichesInBezitException {
		for (Edelsteenfiche e : edelsteenfiches) {
			int huidigAantal = aantalEdelsteenfichesPerTypeInBezit.get(e.edelsteen());
			aantalEdelsteenfichesPerTypeInBezit.put(e.edelsteen(), huidigAantal + 1);
		}
		if (aantalEdelsteenfichesPerTypeInBezit.values().stream().reduce((i1, i2) -> i1 + i2)
				.get() > MAX_FICHES_IN_BEZIT) {
			// in commentaar om binding te testen
			throw new TeVeelFichesInBezitException();

		}
	}

	public ObservableMap<Edelsteen, Integer> getAantalBonussenPerTypeInBezit() {
		return aantalBonussenPerTypeInBezit;
	}

	private void voegBonusToe(Edelsteenfiche bonus) {
		int huidigAantal = aantalBonussenPerTypeInBezit.get(bonus.edelsteen());
		aantalBonussenPerTypeInBezit.put(bonus.edelsteen(), huidigAantal + 1);
	}

	public ObservableList<Edele> getEdelenInBezit() {
		return edelenInBezit;
	}

	protected void voegEdeleToe(Edele edele) {
		edelenInBezit.add(edele);
		prestigepunten.set(prestigepunten.get() + Edele.PRESTIGE_PUNTEN);
	}

	protected void verwijderEdelsteenfiches(List<Edelsteenfiche> edelsteenfiches) throws IllegalArgumentException {
		for (Edelsteenfiche e : edelsteenfiches) {
			int huidigAantal = aantalEdelsteenfichesPerTypeInBezit.get(e.edelsteen());
			if (huidigAantal == 0) {
				throw new IllegalArgumentException("Edelsteenfiche niet in bezit, kan niet verwijderd worden!");
			} else {
				aantalEdelsteenfichesPerTypeInBezit.put(e.edelsteen(), huidigAantal - 1);
			}
		}
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
