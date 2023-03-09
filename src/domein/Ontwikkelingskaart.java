package domein;

public class Ontwikkelingskaart {
	private int prestigePunten;
	private Edelsteenfiche bonus;
	private Edelsteenfiche[] edelsteenfiches;

	public Ontwikkelingskaart(int prestigePunten, Edelsteenfiche bonus, Edelsteenfiche[] edelsteenfiches) {

		this.prestigePunten = prestigePunten;
		this.bonus = bonus;
		this.edelsteenfiches = edelsteenfiches;
	}

	public int getPrestigePunten() {
		return prestigePunten;
	}

	public Edelsteenfiche getBonus() {
		return bonus;
	}

	public Edelsteenfiche[] getEdelsteenfiches() {
		return edelsteenfiches;
	}

}
