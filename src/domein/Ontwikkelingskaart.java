package domein;

public class Ontwikkelingskaart {
	private int prestigePunten;
	private Niveau niveau;
	private Edelsteenfiche bonus;
	private Edelsteenfiche[] edelsteenfiches;

	public Ontwikkelingskaart(int prestigePunten, Niveau niveau, Edelsteenfiche bonus, Edelsteenfiche[] edelsteenfiches) {

		this.prestigePunten = prestigePunten;
		this.niveau = niveau;
		this.bonus = bonus;
		this.edelsteenfiches = edelsteenfiches;
	}

}
