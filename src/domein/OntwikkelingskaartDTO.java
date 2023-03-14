package domein;

public class OntwikkelingskaartDTO {
	public int prestigePunten;
	public Edelsteenfiche bonus;
	public Edelsteenfiche[] edelsteenfiches;

	public OntwikkelingskaartDTO(int prestigePunten, Edelsteenfiche bonus, Edelsteenfiche[] edelsteenfiches) {

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
