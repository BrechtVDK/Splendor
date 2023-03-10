package domein;

public class Edele {
	// Jonas: heb PRESTIGE_PUNTEN public gemaakt, anders kan je deze later niet
	// opvragen
	public static final int PRESTIGE_PUNTEN = 3;
	private Edelsteenfiche[] bonussen;

	public Edele(Edelsteenfiche[] bonussen) {
		this.bonussen = bonussen;
	}

}
